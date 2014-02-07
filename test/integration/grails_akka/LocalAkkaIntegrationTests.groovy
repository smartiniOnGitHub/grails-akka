/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails_akka

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

import org.junit.*

import akka.actor.*
import akka.testkit.*

import scala.concurrent.duration.Duration

/**
 * LocalAkkaIntegrationTests
 * <br/>
 * Integration tests with a Local Akka System.
 */
class LocalAkkaIntegrationTests extends GroovyTestCase
{
	static transactional = false  // transactional behaviour not needed here ...

	// useful reference to empty sender actor, use this instead of null ...
	static final ActorRef ACTOR_NONE = ActorRef.noSender()

    // global actor system for this test class, to reuse it in all test methods here
    static ActorSystem system
	static Props       props
    ActorRef    actor  // defined here, but generally speaking it's better to define it inside methods ...

    // the supported mode to mock an actor is with something like this:
    TestProbe probe
    ActorRef  mock


	@BeforeClass
	public static void setup() {
        println("setup: start ...")

		// system = ActorSystem.create();
		system = ActorSystem.create("LocalActorSystem");

		props = Props.create(GreetingActor.class)
		println("props: $props")

		ActorRef actor = system.actorOf(props, "greeting_actor")
        println("Get Actor Reference to GreetingActor: $actor")

        println("setup: end.")
	}

    @Before
    void setUp()
    {
        // creates the local actor system
        // system = ActorSystem.create("LocalActorSystem")  // ok but here (it's a Unit Test) I get the reference to Akka System in the @BeforeClass
        println("Local  Actor System instance: $system")

        // get a reference to the greetingActor
		// final Props props = Props.create(GreetingActor.class)  // opt, could be used in getting actor reference in the following statements
        // actor  = TestActorRef.create(system, Props.create(GreetingActor.class), "greeting_actor")  // this is the supported way for testing actors
        // actor = system.actorOf(Props.create(GreetingActor.class), "greeting_actor")  // this is my way (not test-specific) ...
		// new, comment this code because since Akka-2.2.x I get an akka.actor.InvalidActorNameException: actor name is not unique ...
		//      so probably I should use JavaTestKit here ... ok but later
        // println("Get Actor Reference to GreetingActor: $actor")

    }

	@AfterClass
	public static void teardown() {
        println("teardown: start ...")

		JavaTestKit.shutdownActorSystem(system);  // optional here ...
		system = null;

        println("teardown: end.")
	}


    @Test(expected = InstantiationException)
    void testClassAvailable_ActorSystem()
    {
        log.info "testClassAvailable_ActorSystem()"

        def className = "akka.actor.ActorSystem"  // abstract, so not instantiable ...
        def classInstance = Class.forName(className).newInstance()
        println("$className instance is: $classInstance")
        // assertNotNull classInstance
        // here I expect an InstantiationException thrown
    }

    @Test(expected = InstantiationException)
    void testClassAvailable_UntypedActor()
    {
        log.info "testClassAvailable_UntypedActor()"

        def className = "akka.actor.UntypedActor"  // abstract, so not instantiable ...
        def classInstance = Class.forName(className).newInstance()
        println("$className instance is: $classInstance")
        // assertNotNull classInstance
        // here I expect an InstantiationException thrown
    }

    @Test
	void testClassAvailable_JavaTestKit()
    {
        log.info "testClassAvailable_JavaTestKit()"

		// sample usage of JavaTestKit from the outside ...
        println("       Actor System instance: $system")
        assertNotNull system
		final JavaTestKit probe = new JavaTestKit(system);
        println("probe instance is: $probe")
        assertNotNull probe
    }


    @Test
    void testLocalAkkaSystem_Base()
    {
        log.info "testLocalAkkaSystem_Base()"

        // creates the local actor system
        // ActorSystem system = ActorSystem.create("GreetingSystem")  // commented ...
		// reuse the actor system global in this test class
        println("       Actor System instance: $system")
        assertNotNull system

        // get a reference to our greeting actor
        // ActorRef actor = system.actorOf(Props.create(GreetingActor.class), "greeting_actor")  // commented, to reuse the actor reference global in this test class
        // actor = system.actorOf(Props.create(GreetingActor.class), "greeting_actor")
		println("props: $props")
        assertNotNull props
		// actor = system.actorOf(props, "greeting_actor")
		// final ActorRef actor = system.actorOf(props, "greeting_actor")
		// final ActorRef actor = system.actorOf(props);
		actor = system.actorOf(props);
		println("Actor Reference instance is: $actor")
        assertNotNull actor
    }

    @Test
    void testLocalAkkaSystem_Greetings()
    {
        log.info "testLocalAkkaSystem_Greetings()"
        testLocalAkkaSystem_Base()

        // send to the greeting actor a null message
		// since Akka-2.2.x this generate an error, so handle/check the (expected failure) ...
        shouldFail(InvalidMessageException) {
			actor.tell(null, null)
        }

        // send to the greeting actor a Greeting message
        actor.tell(new Greeting("Test Greeting"), null)
        assertNotNull actor  // dummy

        // send to the greeting actor an unknown message
        actor.tell(new String("Test String"), null)
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_OtherCommands()
    {
        log.info "testLocalAkkaSystem_OtherCommands()"
        testLocalAkkaSystem_Base()

        // send to the greeting actor a sample generic message
        actor.tell(new GenericMessage<String>("simple generic message with a String"), null)
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_ActorCommand()
    {
        log.info "testLocalAkkaSystem_ActorCommand()"
        testLocalAkkaSystem_Base()

        // wrap sending message to the actor inside a command
        ActorCommand cmd = new ActorCommand(actor, 
			new GenericMessage<String>("ActorCommand: simple generic message with a String"),
			ACTOR_NONE  // could be null, but this is better ...
		)
        println("ActorCommand    instance is: $cmd")
        cmd.execute()
        assertNotNull cmd  // dummy

        // wrap sending message to the actor inside a command
        ActorCommand cmd2 = new ActorCommand(actor, new String("ActorCommand: Test String"), ACTOR_NONE)
        println("ActorCommand    instance is: $cmd2")
        cmd2.execute()
        assertNotNull cmd2  // dummy
    }

// TODO: add a test method with an Exception returning from the actor, in the catch, send a Failure message to the sender ...


    @Test
    void testLocalAkkaSystem_Wait()
    {
        log.info "testLocalAkkaSystem_Wait()"
        testLocalAkkaSystem_Base()

        // send to the greeting actor a Wait message
        long sleepTime = 2000 // msec
        long startSleep = System.currentTimeMillis()
        actor.tell(new Wait(sleepTime), null)
        long stopSleep = System.currentTimeMillis()
        long delta = stopSleep - startSleep
        // TODO: enable later and make it working ...
        // assertTrue delta >= sleepTime
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_Stop()
    {
        log.info "testLocalAkkaSystem_Stop()"
        testLocalAkkaSystem_Base()

        // send to the greeting actor a stop message
        actor.tell(new Stop(), null)
        assertNotNull actor  // dummy

        // // send to the greeting actor a shutdown message
        // actor.tell(new Shutdown(), null)
        // assertNotNull actor  // dummy

        // send to the greeting actor an unknown message
        actor.tell(new String("Test String"), null)
        assertNotNull actor  // dummy
        // ok, but note that this actor (after the Shutdown message) will not get this message ...
    }


}

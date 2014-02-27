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
 * AkkaSystemIntegrationTests
 * <br/>
 * Integration tests for plugin classes related to Akka System.
 */
class AkkaSystemIntegrationTests extends GroovyTestCase
{
	static transactional = false  // transactional behaviour not needed here ...

    // the plugin service to test
	def akkaService

	static ActorSystem system


	@BeforeClass
	public static void setup() {
        println("setup: start ...")

        println("setup: end.")
	}

    @Before
    void setUp()
    {
		// show details of injected akkaService ...
		println("akkaService: $akkaService")
        // assertNotNull akkaService

        // get the actor system, using the akkaService
		system = akkaService?.akkaSystem()
        println("Actor System instance: $system")
        // assertNotNull system
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

    @Test
    void testAkkaService_Base()
    {
        log.info "testAkkaService_Base()"

		assert akkaService != null  // for completeness ...
		assert akkaService.akkaSystem() != null  // for completeness ...
		assertNotNull system
	}

    @Test
    void testAkkaService_Greetings1()
    {
        log.info "testAkkaService_Greetings1()"
        testAkkaService_Base()

		Props    props = Props.create(GreetingActor)  // it's the same as passing GreetingActor.class
		println("Props props: $props")
		assertNotNull props

		ActorRef actor = akkaService.akkaSystem().actorOf(props)
        println("Actor Reference to GreetingActor: $actor")
		assertNotNull actor
    }

	private ActorRef retrieveActor(Props props) {
		// ActorRef actor = akkaService.akkaSystem().actorOf(props)
		ActorRef actor = akkaService.akkaActorOf(props)
        println("Actor Reference to GreetingActor: $actor")
		return actor
	}

	private ActorRef retrieveActor(Class clazz) {
		ActorRef actor = akkaService.akkaActorOf(clazz)
        println("Actor Reference to GreetingActor: $actor")
		return actor
	}

    @Test
    void testAkkaService_Greetings2()
    {
        log.info "testAkkaService_Greetings2()"
        testAkkaService_Base()

		Props    props = akkaService.akkaProps(GreetingActor)
		println("Props props: $props")
		assertNotNull props

		// ActorRef actor = akkaService.akkaSystem().actorOf(props)
		ActorRef actor = retrieveActor(props)
		assertNotNull actor
    }

    @Test
    void testAkkaService_Greetings3()
    {
        log.info "testAkkaService_Greetings3()"
        testAkkaService_Base()

		ActorRef actor = retrieveActor(GreetingActor)
		assertNotNull actor
    }

    @Test
    void testAkkaService_Greetings()
    {
        log.info "testAkkaService_Greetings()"
        testAkkaService_Base()

		ActorRef actor = retrieveActor(GreetingActor)
		assertNotNull actor


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
    void testAkkaService_OtherCommands()
    {
        log.info "testAkkaService_OtherCommands()"
        testAkkaService_Base()

		ActorRef actor = retrieveActor(GreetingActor)
		assertNotNull actor

        // send to the greeting actor a sample generic message
        actor.tell(new GenericMessage<String>("simple generic message with a String"), null)
        assertNotNull actor  // dummy
    }

    @Test
    void testAkkaService_ActorCommand()
    {
        log.info "testAkkaService_ActorCommand()"
        testAkkaService_Base()

		ActorRef actor = retrieveActor(GreetingActor)
		assertNotNull actor

        // wrap sending message to the actor inside a command
        ActorCommand cmd = new ActorCommand(actor, 
			new GenericMessage<String>("ActorCommand: simple generic message with a String"),
			akkaService.akkaActorNoSender()  // could be null, but this is better ...
		)
        println("ActorCommand    instance is: $cmd")
        cmd.execute()
        assertNotNull cmd  // dummy

        // wrap sending message to the actor inside a command
        ActorCommand cmd2 = new ActorCommand(actor, new String("ActorCommand: Test String"), akkaService.akkaActorNoSender())
        println("ActorCommand    instance is: $cmd2")
        cmd2.execute()
        assertNotNull cmd2  // dummy
    }

// TODO: add a test method with an Exception returning from the actor, in the catch, send a Failure message to the sender ...


}

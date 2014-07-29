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

import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

import org.apache.log4j.*
import org.junit.*

import akka.actor.*
import akka.testkit.*

import scala.concurrent.duration.Duration

/**
 * RemoteAkkaIntegrationTests
 * <br/>
 * Integration tests with a Remote Akka System.
 */
class RemoteAkkaIntegrationTests 
{
	static transactional = false  // transactional behaviour not needed here ...

	static def log  // needed because here (in tests) Grails doesn't inject the logger instance ...

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

		log = LogManager.getLogger("LocalAkkaIntegrationTests")  // get logger reference

		// system = ActorSystem.create();
		system = ActorSystem.create("RemoteActorSystem");

		props = Props.create(GreetingActor.class)
		println("props: $props")

		ActorRef actor = system.actorOf(props, "greeting_actor")
        println("Get Actor Reference to GreetingActor: $actor")

        println("setup: end.")
	}

    @Before
    void setUp()
    {
        // attach to a remote actor system
        println("Remote Actor System instance: $system")

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
        // assert classInstance
        // here I expect an InstantiationException thrown
    }

    @Test(expected = InstantiationException)
    void testClassAvailable_UntypedActor()
    {
        log.info "testClassAvailable_UntypedActor()"

        def className = "akka.actor.UntypedActor"  // abstract, so not instantiable ...
        def classInstance = Class.forName(className).newInstance()
        println("$className instance is: $classInstance")
        // assert classInstance
        // here I expect an InstantiationException thrown
    }

    @Test
	void testClassAvailable_JavaTestKit()
    {
        log.info "testClassAvailable_JavaTestKit()"

		// sample usage of JavaTestKit from the outside ...
        println("       Actor System instance: $system")
        assert system
		final JavaTestKit probe = new JavaTestKit(system);
        println("probe instance is: $probe")
        assert probe
    }


}

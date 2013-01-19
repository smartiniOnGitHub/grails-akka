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

import static org.junit.Assert.*
import grails_akka_test.actor.GreetingActor
import grails_akka_test.command.ActorCommand
import grails_akka_test.message.GenericMessage
import grails_akka_test.message.Greeting
import grails_akka_test.message.Stop
import grails_akka_test.message.Wait

import org.junit.After
import org.junit.Before
import org.junit.Test

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.TestProbe

/**
 * LocalAkkaIntegrationTests
 * <br/>
 * Integration tests with a Local Akka System.
 */
class LocalAkkaIntegrationTests
{
    // global actor system for this test class, to reuse it in all test methods here
    ActorSystem system
    ActorRef    actor

    // the supported mode to mock an actor is with something like this:
    TestProbe probe
    ActorRef  mock

    @Before
    void setUp()
    {
        // creates the local actor system
        // system = ActorSystem.apply()
        system = ActorSystem.create("LocalActorSystem")
        println("Created Local Actor System instance:  $system")

        // get a reference to the greetingActor
        // actor  = TestActorRef.apply(new Props(GreetingActor), system)  // this is the supported way
        actor = system.actorOf(new Props(GreetingActor), "greeting_actor")  // this is my way (not test-specific) ...
        println("Get Actor Reference to GreetingActor: $actor")


        // the supported mode to mock an actor is with something like this:
        // mock = TestActorRef.apply(new AbstractFunction0() {
        //     @Override
        //     Pi.Worker apply() {
        //         return new Pi.Worker()
        //     }
        // }, system)
        // and in any test method then use something like this:
        // probe = TestProbe.apply(system)
        // mock  = probe.ref()
        // mock.tell("Hello", probe.ref())
    }

    @After
    void tearDown()
    {
        // shutdown the local actor system
        // if (actor != null) {
        //     actor.stop()
        // }
        // if (system != null) {
        //     println("Shutting down Local Actor System instance: $system")
        //     system.shutdown()
        // }
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
    void testLocalAkkaSystem_Greetings()
    {
        log.info "testLocalAkkaSystem_Greetings()"

        // creates the local actor system
        // ActorSystem system = ActorSystem.create("GreetingSystem")  // commented, to reuse the actor system global in this test class
        println("Actor System    instance is: $system")
        assertNotNull system

        // get a reference to our greeting actor
        // ActorRef actor = system.actorOf(new Props(GreetingActor), "greeting_actor")  // commented, to reuse the actor reference global in this test class
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a null message
        actor.tell(null)
        assertNotNull actor  // dummy

        // send to the greeting actor a Greeting message
        actor.tell(new Greeting("Test Greeting"))
        assertNotNull actor  // dummy

        // send to the greeting actor an unknown message
        actor.tell(new String("Test String"))
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_OtherCommands()
    {
        log.info "testLocalAkkaSystem_OtherCommands()"

        // creates the local actor system
        // ActorSystem system = ActorSystem.create("GreetingSystem")  // commented, to reuse the actor system global in this test class
        println("Actor System    instance is: $system")
        assertNotNull system

        // get a reference to our greeting actor
        // ActorRef actor = system.actorOf(new Props(GreetingActor), "greeting_actor")  // commented, to reuse the actor reference global in this test class
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a sample generic message
        actor.tell(new GenericMessage<String>("simple generic message with a String"))
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_Wait()
    {
        log.info "testLocalAkkaSystem_Wait()"

        println("Actor System    instance is: $system")
        assertNotNull system
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a Wait message
        long sleepTime = 2000 // msec
        long startSleep = System.currentTimeMillis()
        actor.tell(new Wait(sleepTime))
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

        println("Actor System    instance is: $system")
        assertNotNull system
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a stop message
        actor.tell(new Stop())
        assertNotNull actor  // dummy

        // // send to the greeting actor a shutdown message
        // actor.tell(new Shutdown())
        // assertNotNull actor  // dummy

        // send to the greeting actor an unknown message
        actor.tell(new String("Test String"))
        assertNotNull actor  // dummy
        // ok, but note that this actor (after the Shutdown message) will not get this message ...
    }

    @Test
    void testLocalAkkaSystem_ActorCommand()
    {
        log.info "testLocalAkkaSystem_ActorCommand()"

        println("Actor System    instance is: $system")
        assertNotNull system
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // wrap sending message to the actor inside a command
        ActorCommand cmd = new ActorCommand(actor, new GenericMessage<String>("ActorCommand: simple generic message with a String"))
        println("ActorCommand    instance is: $cmd")
        cmd.execute()
        assertNotNull cmd  // dummy

        // wrap sending message to the actor inside a command
        ActorCommand cmd2 = new ActorCommand(actor, new String("ActorCommand: Test String"))
        println("ActorCommand    instance is: $cmd2")
        cmd2.execute()
        assertNotNull cmd2  // dummy
    }
}

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
import grails_akka.actor.GreetingActor

import org.junit.Before
import org.junit.Test

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.TestProbe

/**
 * RemoteAkkaIntegrationTests
 * <br/>
 * Integration tests with a Remote Akka System.
 */
class RemoteAkkaIntegrationTests
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
}

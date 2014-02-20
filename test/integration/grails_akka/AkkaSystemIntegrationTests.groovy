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

		// show details of injected akkaService ...
		println("akkaService: $akkaService")

        println("setup: end.")
	}

    @Before
    void setUp()
    {
        assertNotNull akkaService

        // get the actor system, using the akkaService
		system = akkaService.akkaSystem()
        println("Actor System instance: $system")

		// TODO: add an actor to the system ...

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


}

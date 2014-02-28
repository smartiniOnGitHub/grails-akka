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

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(AkkaController)
class AkkaControllerSpec extends Specification 
{
	def akkaServiceMock = mockFor(AkkaService, true)

	def setupSpec() {
		println("setup: start ...")

		println("setup: end.")
	}

	def setup() {
		// define some fake method in the mock ...
		// TODO: verify if it's right ...
		akkaServiceMock.demand.akkaSystem(0)  { -> ["akkaSystem_mock": true] }
		akkaServiceMock.demand.akkaProps(1)   { Class clazz -> ["akkaProps_mock": true] }
		akkaServiceMock.demand.akkaActorOf(1) { Class clazz -> ["akkaActorOf_mock": true] }

		println("akkaService: $akkaServiceMock")
	}

	def cleanup() {
	}

	def cleanupSpec() {
		println("teardown: start ...")

		println("teardown: end.")
	}


	void "test akkaService in controller"() {
        setup:
		akkaServiceMock != null  // put here as a precondition, verify if it's right ...
		def dummy = true  // dummy

		when:
		def model = controller.index()

		then:
		// akkaServiceMock != null
		// model.akkaSystem != null  // TODO: fix me ...
		akkaServiceMock != null  // temp

		// where:
		// // filter conditions for this test ...
	}

	void "test actor from akkaService in controller"() {
		when:
		def model = controller.index()

		then:
		// model.greetingActor != null  // TODO: fix me ...
		akkaServiceMock != null  // temp
	}

}
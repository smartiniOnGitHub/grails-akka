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
	def akkaService  // verify if needed, or if it's already injected in the controller ...


	def setupSpec() {
        println("setup: start ...")

        println("setup: end.")
	}

	def setup() {
		// akkaService = new AkkaService  // verify if needed here (or at least if AkkaService here is a mock of the real class) ...
	}

	def cleanup() {
	}

	def cleanupSpec() {
        println("teardown: start ...")

        println("teardown: end.")
	}


	void "test akkaService in controller"() {
        setup:
		// assert akkaService != null  // check if applicable here ...
		def dummy = true

		when:
		def model = controller.index()

		then:
		akkaService != null
		model.akkaSystem != null

		// where:
		// // filter conditions for this test ...
	}

	void "test actor from akkaService in controller"() {
		when:
		def model = controller.index()

		then:
		akkaService != null
		model.akkaSystem != null
		model.greetingActor != null
	}

}
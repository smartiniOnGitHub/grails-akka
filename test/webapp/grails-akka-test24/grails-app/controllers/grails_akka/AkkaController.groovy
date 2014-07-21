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

import akka.actor.*

import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

/**
 * Sample Controller for displaying Akka System related info (using akkaService)
 */
class AkkaController {

	def akkaService

	def index() {
		log.info("index - params: $params")

		// publish some data (read from akkaService) to the page ...
        // assert akkaService != null  // dummy
		// render akkaService.akkaSystem()  // test

		ActorRef actor = akkaService?.akkaActorOf(GreetingActor)
		def message = params?.message

        // actor.tell(new Greeting(message ?: "Test Greeting"), null)
		if (message) {  // new, make the call only when the parameter is passed ...
			actor?.tell(new Greeting(message), null)
		}
        // assert actor != null  // dummy


		[
			akkaSystem: akkaService?.akkaSystem(),
			// props: akkaService?.akkaProps(GreetingActor),
			greetingActor: actor,
			message: message
		]
	}


}

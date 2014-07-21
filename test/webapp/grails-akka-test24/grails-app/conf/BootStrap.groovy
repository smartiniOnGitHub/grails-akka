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

import grails.util.Environment

import javax.servlet.ServletContext

import akka.actor.*

import grails_akka.*
import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

class BootStrap
{
    def grailsApplication
	def akkaService

    def init = { ServletContext sc ->
        println "Bootstrap: starting webapp in Environment " + Environment.current
        println """
Remember that to test the plugin (inline here),
this webapp (other than the usual run-app) could be run even with with run-war ...
"""

        println """
Bootstrap: manual call to akkaService (for showing it here),
akkaSystem is ${akkaService?.akkaSystem()}
"""
		createActors()

        println "Bootstrap: finished"
    }

	def destroy = { ServletContext sc ->
        println "Bootstrap: closing webapp"
	}

	// creation of some local actors, for sample
	// in future move to a BootstrapHelper class ...
	private def createActors()
	{
        println "Bootstrap: creating actors"
		
		assert akkaService != null
		assert akkaService.akkaSystem() != null

		// first version, all manual ...
		// Props    props = Props.create(GreetingActor)  // it's the same as passing GreetingActor.class
		// ActorRef actor = akkaService.akkaSystem().actorOf(props, "greeting_actor")
		// other version, using service simple methods ...
		// Props    props = akkaService.akkaProps(GreetingActor)
		// // ActorRef actor = akkaService.akkaSystem().actorOf(props, "greeting_actor")
		// // ActorRef actor = akkaService.akkaSystem().actorOf(props)
		// ActorRef actor = akkaService.akkaActorOf(props, "greeting_actor")
		// ActorRef actor = akkaService.akkaActorOf(props)
		// println("Props props: $props")
		// other version, using service all-in-one methods ...
		// ActorRef actor = akkaService.akkaActorOf(GreetingActor, "greeting_actor")
		ActorRef actor = akkaService.akkaActorOf(GreetingActor)

        println("Actor Reference to GreetingActor: $actor")
	}

}

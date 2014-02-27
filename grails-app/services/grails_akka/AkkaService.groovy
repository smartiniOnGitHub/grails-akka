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

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

import akka.actor.*


/**
 * Service for manage a default Akka System in the webapp
 * <br/>
 * It has default scope (singleton), but in a not transactional way.
 */
class AkkaService 
{
	static transactional = false  // transactional behaviour not needed here ...

	private static ActorSystem system
	private static final String ACTOR_SYSTEM_DEFAULT_NAME = "grails-akka"

	// useful reference to empty sender actor, use this instead of null ...
	private static final ActorRef ACTOR_NONE = ActorRef.noSender()

	def grailsApplication


	/**
	 * Initialization, automatically called after creation, via the PostConstruct annotation.
	 */
	@PostConstruct
	void init() {
		log.info "initializing Akka ActorSystem: start ..."

		// calculate a default name for the actor system, depending:
		// first on the (optional) config variable (grails_akka.akkaSystem.name),
		// otherwise on the webapp name, or last use a default one ...
		// def applicationContext = grailsApplication.mainContext
		def applicationName = grailsApplication.config.grails_akka.akkaSystem.name
		if (applicationName == null || applicationName.getClass() == groovy.util.ConfigObject)  // config key not defined
			applicationName = grailsApplication.metadata.getApplicationName() ?: ACTOR_SYSTEM_DEFAULT_NAME
		
		system = ActorSystem.create(applicationName);
        // println("ActorSystem: $system")  // enable only for tests ...
        log.info("ActorSystem: $system")

		log.info "initializing Akka ActorSystem: done."
	}

	/**
	 * Destroy, automatically called before destroy, via the PreDestroy annotation.
	 */
	@PreDestroy
	void destroy() {
		log.info "destroying Akka ActorSystem: start shutdown ..."

		system?.shutdown()
		system = null

		log.info "destroying Akka ActorSystem: done."
	}


	/**
	 * Returns the Akka ActorSystem instance used here.
	 *
	 * @return ActorSystem
	 */
	ActorSystem akkaSystem() {
		return system
	}

	/**
	 * Create and returns an Akka Props instance,
	 * needed later for searching actors.
	 *
	 * @param Class clazz the Class to use
	 * @return Props
	 */
	Props akkaProps(Class clazz) {
		assert clazz  != null

		Props props = Props.create(clazz)
		return props
	}

	/**
	 * Returns the Akka Actor (ActorRef instance),
	 * found in the ActorSystem by the given arguments.
	 *
	 * @param Props props the Props instance to use
	 * @param String name the name of the actor to retrieve
	 * @return ActorSystem
	 */
	ActorRef akkaActorOf(Props props, String name) {
		assert props  != null

		assert system != null

		ActorRef actor = (name) ? system.actorOf(props, name) : system.actorOf(props)
		return actor
	}

	/**
	 * Returns the Akka Actor (ActorRef instance),
	 * found in the ActorSystem by the given arguments.
	 *
	 * @param Class clazz the Class to use to build Props for the actor
	 * @param String name the name of the actor to retrieve
	 * @return ActorSystem
	 */
	ActorRef akkaActorOf(Class clazz, String name) {
		assert clazz  != null

		Props props = akkaProps(clazz)
		assert props  != null
		assert system != null

		ActorRef actor = (name) ? system.actorOf(props, name) : system.actorOf(props)
		return actor
	}


}

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

	static private ActorSystem system
	static final String ACTOR_SYSTEM_DEFAULT_NAME = "grails-akka"
	
	def grailsApplication


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

	@PreDestroy
	void destroy() {
		log.info "destroying Akka ActorSystem: start shutdown ..."

		system?.shutdown()
		system = null

		log.info "destroying Akka ActorSystem: done."
	}


	ActorSystem akkaSystem() {
		return system
	}

// TODO: add other methods ...


}

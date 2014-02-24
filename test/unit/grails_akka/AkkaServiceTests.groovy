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

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AkkaService)
class AkkaServiceTests 
{
	// def akkaService = mockFor(AkkaService)  // unnecessary here, TestFor will generate and populate a service variable here ...
	// note that of course a unit test like this is not so useful, so instead do an integration test for the service ...

	void testAkkaServiceNotNull() {
        // println("akkaService instance (mocked): $akkaService")
        // assertNotNull akkaService
        println("akkaService instance (mocked): $service")
        assertNotNull service
	}

	/*
	void testAkkaSystemNotNull() {
        // println("akkaService instance (mocked): $akkaService")
        // assertNotNull akkaService
        println("akkaService instance: $service.system")
        assertNotNull service.system
	}
	// to enable this test method, I need  something like (in a test method I think):
	service.metaClass.akkaSystem = { arg1 -> return true }
	 */

}

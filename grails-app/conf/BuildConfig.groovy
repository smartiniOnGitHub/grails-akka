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

grails.project.work.dir = "target"
// grails.project.docs.output.dir = 'docs/manual'
grails.project.source.level = 1.6
grails.project.target.level = 1.6

def akkaVersion = '2.2.5'
def scalaSuffixVersion = '_2.10'

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenCentral()
		mavenRepo "https://repo.grails.org/grails/plugins" 
    }

    dependencies {
        def excludes = {
            // disable excludes, or compile errors will happen ...
            // // exclude, to avoid version conflict
            // excludes 'scala-library'
            // excludes 'commons-codec'
            // excludes 'commons-io'
            // transitive = false
        }

        compile("com.typesafe.akka:akka-actor$scalaSuffixVersion:$akkaVersion", 
			"com.typesafe.akka:akka-remote$scalaSuffixVersion:$akkaVersion", 
			excludes
		)
        test("com.typesafe.akka:akka-testkit$scalaSuffixVersion:$akkaVersion", excludes)
    }

    plugins {
        build(":release:2.2.1", ":rest-client-builder:1.0.3") {
            export = false
        }
    }
}

grails.release.scm.enabled = false
grails.project.repos.default = "grailsCentral"

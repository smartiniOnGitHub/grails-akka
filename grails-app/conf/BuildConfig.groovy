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

// grails.servlet.version = "3.0"  // verify if makes sense to enable this even here ...
grails.project.class.dir = "target/classes"
// grails.project.docs.output.dir = 'docs/manual'
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.source.level = 1.6
grails.project.target.level = 1.6
// grails.project.war.file = "target/${appName}-${appVersion}.war"
// grails.project.work.dir = 'target'


def akkaVersion = '2.0.5'

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // excludes 'ehcache'  // uncomment to disable ehcache
    }

    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        grailsCentral()
        //mavenCentral()
        //mavenLocal()
        mavenRepo "http://repo.typesafe.com/typesafe/releases/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        def excludes = {
            // disable excludes, or compile errors will happen ...
            // // exclude, to avoid version conflict
            // excludes 'scala-library'
            // excludes 'commons-codec'
            // excludes 'commons-io'
            // transitive = false
        }

        compile("com.typesafe.akka:akka-actor:$akkaVersion", "com.typesafe.akka:akka-remote:$akkaVersion", excludes)
        test("com.typesafe.akka:akka-testkit:$akkaVersion", excludes)
    }

    plugins {
        build(":release:2.2.0") {
            export = false
        }
    }

}

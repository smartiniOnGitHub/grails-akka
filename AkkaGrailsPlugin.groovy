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

// import org.apache.log4j.Logger

class AkkaGrailsPlugin {
    def version = "0.5"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
        "grails-app/views/error.gsp"
        // , "src/docs/"
    ]

    def title = "Akka Integration"
    def author = "Sandro Martini"
    def authorEmail = "sandro.martini@gmail.com"
    def description = '''\
Let Grails webapps to call/use Akka actors from Groovy and Java.
Note that it requires a Servlet 3.x environment to run.
'''
    def documentation = "http://grails.org/plugin/akka"

    // Extra (optional) plugin metadata
    def license = "APACHE"
    // def organization = [ name: "MartinS", url: "https://github.com/smartiniOnGitHub/grails-akka/" ]
    // def developers = [ [ name: "other developer", email: "other.developer@my-company.com" ]]
    def issueManagement = [ system: "github", url: "https://github.com/smartiniOnGitHub/grails-akka/issues/" ]
    def scm = [ url: "https://github.com/smartiniOnGitHub/grails-akka/" ]


    // private Logger log = Logger.getLogger('grails.plugin.akka.AkkaGrailsPlugin')


    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }

}

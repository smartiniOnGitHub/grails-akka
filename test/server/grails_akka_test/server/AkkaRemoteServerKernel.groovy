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
package grails_akka_test.server

// a simple server console application for creating some Akka Actors and make them reachable from other (remote) processes
//
// to run this with right CLASSPATH and libraries, ensure to run inside a Grails Console started in Plugin main folder (and not simply from a Groovy Console) ...

import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

// import org.junit.*

import akka.actor.*
import akka.kernel.Bootable
import akka.testkit.*

import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration


// TODO: find a (simple and clean) way to resolve references to  akka.kernel.Bootable class (not inlcluded in the Plugin because not necessary) ...

// start an Akka Server for publishing remotable actors, for simplicity using Akka Kernel classes
class AkkaServerKernel implements Bootable {

    // inline Akka configuration script, to enable remoting of actors, and with some useful settings for a dev environment
    def akkaConfig = '''
    akka {
      loglevel = 'DEBUG'
      actor {
        provider = 'akka.remote.RemoteActorRefProvider'
      }
      remote {
        enabled-transports = ['akka.remote.netty.tcp']
        netty.tcp {
          hostname = '127.0.0.1'
          # Sever, listen on default Akka tcp port (2552)
          port = 2552
        }
        log-sent-messages = on
        log-received-messages = on
      }
    }
    '''
    final String remoteSystemName = "RemoteActorSystem"
    final ActorSystem system = // ActorSystem.create(remoteSystemName)
        ActorSystem.create(remoteSystemName, ConfigFactory.load(akkaConfig))

    def startup() {
        println("startup: start at ${new Date()}.")
        println("using Akka Config: $akkaConfig")
        println("system: $system")
        Props       props  = Props.create(GreetingActor.class)
        println("props: $props")
        waitAMoment()
        println("startup: end   at ${new Date()}.")
        println("Server ready ...")
    }

    def shutdown() {
        println("shutdown: start at ${new Date()}.")
        println("remoteSystemName: Shutting Down")
        waitAMoment()
        system.shutdown
        println("shutdown: end   at ${new Date()}.")
    }

    def waitAMoment() {
        sleep 500  // workaround, mainly for flushing console output ...
    }
}


// run the server
// AkkaServerKernel server = new AkkaServerKernel()
// server.startup()
// 
// note that to run this server, external script for Akka microkernel but be used, and pass to it this class name

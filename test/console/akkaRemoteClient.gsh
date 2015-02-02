// a simple client console application for looking (consuming) some Remote Akka Actors published by akkaRemoteServer script (it must be running)
//
// to run this with right CLASSPATH and libraries, ensure to run inside a Grails Console started in Plugin main folder (and not simply from a Groovy Console) ...

import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

// import org.junit.*

import akka.actor.*
import akka.testkit.*

import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration


println("Application: Start a simple client console application for for looking (consuming) some Remote Akka Actors published by akkaRemoteServer script (it must be running)\n")


// setup phase
println("setup: start at ${new Date()}.")

// TODO: print Akka version (and related Scala version) ...

// inline Akka configuration script, to enable looking for remote actors, and with some useful settings for a dev environment
def akkaConfig = '''
akka {
  loglevel = 'DEBUG'
  actor {
    provider = 'akka.remote.RemoteActorRefProvider'
  }
  remote {
    # enabled-transports = ['akka.remote.netty.tcp']
    netty.tcp {
      hostname = '127.0.0.1'
      # Client, use a different port than server (2552)
      # port = 2553
      port = 0
    }
    log-sent-messages = on
    log-received-messages = on
  }
}
'''
println("using Akka Config: $akkaConfig")

// global actor system to start here
final String localSystemName = "LookupActorSystem" // "RemoteActorSystem-Client"
final String remoteSystemName = "RemoteActorSystem"
final String remotePath = "akka.tcp://RemoteActorSystem@127.0.0.1:2552/user/"

final ActorSystem system = // ActorSystem.create(remoteSystemName, ConfigFactory.load(akkaConfig))
    // ActorSystem.create(localSystemName, ConfigFactory.load(akkaConfig))
    ActorSystem.create(localSystemName)
println("system: $system")
sleep 500  // workaround, mainly for flushing console output ...
println("setup: end at ${new Date()}.")

println("check: start")
println("Actor System instance: $system")
assert system != null

sleep 500  // workaround, mainly for flushing console output ...
println("check: end at ${new Date()}.")
println("Client ready ...")

// TODO: make some call ...
// get reference and then call some remote actors

// TODO: ... check settings, and then try to simplify url for actor selection (and keep the full version commented) ...

// TODO: create an actor in the  remote system (changes required even in config file) ...
// final ActorRef actor = system.actorOf(Props.create(GreetingActor.class, remotePath), "greeting_actor");


ActorSelection selection = // system.actorSelection(remotePath + "greeting_actor")
    system.actorSelection(remotePath + "greeting_actor")  // TODO: check if/how to do this but with context ...
println("Get Actor Selection to GreetingActor: $selection")
assert selection != null
selection.tell("Test String", null);
assert selection != null

// TODO: get ActorRef actor from selection, sending a (via identify or similar) message to the selection and use the getSender reference of the reply from the actor ...

/*
// send some test messages to the actor
actor.tell(new Greeting("Test Greeting"), null)
assert actor != null
actor.tell(new String("Test String"), null)
assert actor != null
actor.tell(new GenericMessage<String>("simple generic message with a String"), null)
assert actor != null
 */

system.shutdown()
sleep 500  // workaround, mainly for flushing console output ...
println("\nApplication: execution end at ${new Date()}.")

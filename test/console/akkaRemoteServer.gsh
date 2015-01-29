// a simple server console application for creating some Akka Actors and make them reachable from other (remote) processes
//
// to run this with right CLASSPATH and libraries, ensure to run inside a Grails Console started in Plugin main folder (and not simply from a Groovy Console) ...

import grails_akka_test.actor.*
import grails_akka_test.command.*
import grails_akka_test.message.*

import org.junit.*

import akka.actor.*
import akka.testkit.*

import com.typesafe.config.ConfigFactory

import scala.concurrent.duration.Duration


println("Application: Start a simple server console application for creating some Akka Actors and make them reachable from other (remote) processes\n")


// setup phase
println("setup: start at ${new Date()}.")

// TODO: print Akka version (and related Scala version) ...

// inline Akka configuration script, to enable remoting of actors, and with some useful settings for a dev environment
def akkaConfig = """
akka {
  loglevel = "DEBUG"
  actor {
    provider = "akka.remote.RemoteActorRefProvider"
  }
  remote {
    enabled-transports = ["akka.remote.netty.tcp"]
    netty.tcp {
      hostname = "127.0.0.1"
      port = 2552
    }
    log-sent-messages = on
    log-received-messages = on
  }
}
"""
println("using Akka Config: $akkaConfig")

// global actor system to start here
final ActorSystem system = // ActorSystem.create("RemoteActorSystem")
  ActorSystem.create("RemoteActorSystem", ConfigFactory.load(akkaConfig))
println("system: $system")
Props       props  = Props.create(GreetingActor.class)
println("props: $props")
sleep 500  // workaround, mainly for flushing console output ...

// create instance for some actors
ActorRef actor = system.actorOf(props, "greeting_actor")
println("Get Actor Reference to GreetingActor: $actor")
// TODO: start more actors here ...

sleep 500  // workaround, mainly for flushing console output ...
println("setup: end at ${new Date()}.")


println("check: start")
println("Actor System instance: $system")
assert system != null
// get a reference to our greeting actor
println("props: $props")
assert props != null
actor = system.actorOf(props);
println("Actor Reference instance is: $actor")
assert actor != null
// send some test messages to the actor
actor.tell(new Greeting("Test Greeting"), null)
assert actor != null
actor.tell(new String("Test String"), null)
assert actor != null
actor.tell(new GenericMessage<String>("simple generic message with a String"), null)
assert actor != null
sleep 500  // workaround, mainly for flushing console output ...
println("check: end at ${new Date()}.")


println("Server ready ...")


// TODO: make them remotable via config file, or with multiline string here ...

// TODO: make this application run, at least for one minute ...

// workaround, to keep this script running until user write a line of text
sleep 500  // workaround, mainly for flushing console output ...
println("\nHit ENTER to exit ...")
println("(note that when running in Groovy Console, the input is being read from the text console in the background)")
def quit = System.console().readLine()


sleep 500  // workaround, mainly for flushing console output ...
println("\nApplication: execution end at ${new Date()}.")

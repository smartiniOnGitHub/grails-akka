package grails_akka

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import org.junit.rules.*

import akka.actor.*
import akka.routing.*
import akka.util.Duration
import akka.testkit.*

// import java.util.concurrent.TimeUnit

import grails_akka.actor.*
import grails_akka.command.*
import grails_akka.message.*


/**
 * RemoteAkkaIntegrationTests
 * <br/>
 * Integration tests with a Remote Akka System.
 */
class RemoteAkkaIntegrationTests
{
    // global actor system for this test class, to reuse it in all test methods here
    ActorSystem system = null
    ActorRef    actor  = null

    // the supported mode to mock an actor is with something like this:
    TestProbe probe = null
    ActorRef  mock  = null


    @Before
    public void setUp()
    {
        // creates the local actor system
        // system = ActorSystem.apply()
        system = ActorSystem.create("LocalActorSystem")
        println("Created Local Actor System instance:  $system")

        // get a reference to the greetingActor
        // actor  = TestActorRef.apply(new Props(GreetingActor.class), system)  // this is the supported way
        actor = system.actorOf(new Props(GreetingActor.class), "greeting_actor")  // this is my way (not test-specific) ...
        println("Get Actor Reference to GreetingActor: $actor")


        // the supported mode to mock an actor is with something like this:
        // mock = TestActorRef.apply(new AbstractFunction0() {
        //     @Override
        //     public Pi.Worker apply() {
        //         return new Pi.Worker();
        //     }
        // }, system);
        // and in any test method then use something like this:
        // probe = TestProbe.apply(system)
        // mock  = probe.ref()
        // mock.tell("Hello", probe.ref())
    }

    @After
    public void tearDown()
    {
        // shutdown the local actor system
        // if (actor != null) {
        //     actor.stop()
        // }
        // if (system != null) {
        //     println("Shutting down Local Actor System instance: $system")
        //     system.shutdown()
        // }
    }

    @Test(expected = InstantiationException.class)
    void testClassAvailable_UntypedActor()
    {
        log.info "testClassAvailable_UntypedActor()"

        def className = null;
        def classInstance = null;

        className = "akka.actor.UntypedActor"  // abstract, so not instantiable ...
        classInstance = Class.forName(className).newInstance();
        println("$className instance is: $classInstance")
        // assertNotNull classInstance
        // here I expect an InstantiationException thrown
    }

    @Test(expected = InstantiationException.class)
    void testClassAvailable_ActorSystem()
    {
        log.info "testClassAvailable_ActorSystem()"

        def className = null;
        def classInstance = null;

        className = "akka.actor.ActorSystem"  // abstract, so not instantiable ...
        classInstance = Class.forName(className).newInstance();
        println("$className instance is: $classInstance")
        // assertNotNull classInstance
        // here I expect an InstantiationException thrown
    }

}

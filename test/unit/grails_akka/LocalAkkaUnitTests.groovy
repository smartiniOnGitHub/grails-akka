package grails_akka

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
// import org.junit.rules.*

import akka.actor.*
import akka.routing.*
import akka.util.Duration
import akka.testkit.*

// import java.util.concurrent.TimeUnit

import grails_akka.actor.*
import grails_akka.command.*
import grails_akka.message.*


/**
 * LocalAkkaUnitTests
 * <br/>
 * Execute some unit tests with a Local Akka System.
 *
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class LocalAkkaUnitTests
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

    @Test
    // @Test(expected = InstantiationException.class)
    void testClassAvailable()
    {
        log.info "testClassAvailable()"

        def className = null;
        def classInstance = null;

        className = "akka.actor.ActorSystem"  // abstract, so not instantiable ...
        shouldFail(java.lang.InstantiationException) {  // more flexible insted of ExpectedException
            classInstance = Class.forName(className).newInstance();
        }
        println("$className instance is: $classInstance")
        // assertNotNull classInstance
        // here I expect an InstantiationException thrown

        className = "akka.actor.UntypedActor"  // abstract, so not instantiable ...
        shouldFail(java.lang.InstantiationException) {  // more flexible insted of ExpectedException
            classInstance = Class.forName(className).newInstance();
        }
        println("$className instance is: $classInstance")
        // assertNotNull classInstance
        // here I expect an InstantiationException thrown
    }

    @Test
    void testLocalAkkaSystem_Greetings()
    {
        log.info "testLocalAkkaSystem_Greetings()"

        // creates the local actor system
        // ActorSystem system = ActorSystem.create("GreetingSystem")  // commented, to reuse the actor system global in this test class
        println("Actor System    instance is: $system")
        assertNotNull system

        // get a reference to our greeting actor
        // ActorRef actor = system.actorOf(new Props(GreetingActor.class), "greeting_actor")  // commented, to reuse the actor reference global in this test class
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a null message
        actor.tell(null);
        assertNotNull actor  // dummy

        // send to the greeting actor a Greeting message
        actor.tell(new Greeting("Test Greeting"));
        assertNotNull actor  // dummy

        // send to the greeting actor an unknown message
        actor.tell(new String("Test String"));
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_OtherCommands()
    {
        log.info "testLocalAkkaSystem_OtherCommands()"

        // creates the local actor system
        // ActorSystem system = ActorSystem.create("GreetingSystem")  // commented, to reuse the actor system global in this test class
        println("Actor System    instance is: $system")
        assertNotNull system

        // get a reference to our greeting actor
        // ActorRef actor = system.actorOf(new Props(GreetingActor.class), "greeting_actor")  // commented, to reuse the actor reference global in this test class
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a sample generic message
        actor.tell(new GenericMessage<String>("simple generic message with a String"));
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_Wait()
    {
        log.info "testLocalAkkaSystem_Wait()"

        println("Actor System    instance is: $system")
        assertNotNull system
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a Wait message
        long sleepTime = 2000 // msec
        long startSleep = System.currentTimeMillis();
        actor.tell(new Wait(sleepTime));
        long stopSleep = System.currentTimeMillis();
        long delta = stopSleep - startSleep
        // TODO: enable later and make it working ...
        // assertTrue delta >= sleepTime
        assertNotNull actor  // dummy
    }

    @Test
    void testLocalAkkaSystem_Stop()
    {
        log.info "testLocalAkkaSystem_Stop()"

        println("Actor System    instance is: $system")
        assertNotNull system
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // send to the greeting actor a stop message
        actor.tell(new Stop());
        assertNotNull actor  // dummy

        // // send to the greeting actor a shutdown message
        // actor.tell(new Shutdown());
        // assertNotNull actor  // dummy

        // send to the greeting actor an unknown message
        actor.tell(new String("Test String"));
        assertNotNull actor  // dummy
        // ok, but note that this actor (after the Shutdown message) will not get this message ...
    }

    @Test
    void testLocalAkkaSystem_ActorCommand()
    {
        log.info "testLocalAkkaSystem_ActorCommand()"

        println("Actor System    instance is: $system")
        assertNotNull system
        println("Actor Reference instance is: $actor")
        assertNotNull actor

        // wrap sending message to the actor inside a command
        ActorCommand cmd = new ActorCommand(actor, new GenericMessage<String>("ActorCommand: simple generic message with a String"))
        println("ActorCommand    instance is: $cmd")
        cmd.execute()
        assertNotNull cmd  // dummy

        // wrap sending message to the actor inside a command
        ActorCommand cmd2 = new ActorCommand(actor, new String("ActorCommand: Test String"))
        println("ActorCommand    instance is: $cmd2")
        cmd2.execute()
        assertNotNull cmd2  // dummy
    }

}

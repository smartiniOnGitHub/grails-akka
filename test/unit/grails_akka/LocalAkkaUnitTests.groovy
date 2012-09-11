package grails_akka

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
// import org.junit.rules.*

import akka.actor.*
import akka.routing.*
import akka.util.Duration

// import java.util.concurrent.TimeUnit


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

    @Before public void setUp() {
        // Setup logic here
    }

    @After public void tearDown() {
        // Tear down logic here
    }

    @Test
    // @Test(expected = InstantiationException.class)
	void testClassAvailable() 
	{
        log.info "testClassAvailable()"

		def className = null;
		def classInstance = null;

		className = "akka.actor.UntypedActor"  // abstract, so not instantiable ...
		shouldFail(java.lang.InstantiationException) {  // more flexible insted of ExpectedException
			classInstance = Class.forName(className).newInstance();
		}
		println("$className instance is: $classInstance")
		// assertNotNull classInstance
		// here I expect an InstantiationException thrown

		className = "akka.actor.ActorSystem"  // abstract, so not instantiable ...
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
		ActorSystem system = ActorSystem.create("GreetingSystem")
		println("Actor System instance is: $system")
		assertNotNull system

		// get a reference to our greeting actor
		ActorRef greetingActor = system.actorOf(new Props(GreetingActor.class), "greeting_actor")
		println("Actor Reference instance is: $greetingActor")
		assertNotNull greetingActor

		// send to the greeting actor a null message
		greetingActor.tell(null);
		assertNotNull greetingActor  // dummy

		// send to the greeting actor a Greeting message
		greetingActor.tell(new Greeting("Test Greeting"));
		assertNotNull greetingActor  // dummy

		// send to the greeting actor an unknown message
		greetingActor.tell(new String("Test String"));
		assertNotNull greetingActor  // dummy
	}

    @Test
	void testLocalAkkaSystem_OtherCommands() 
	{
        log.info "testLocalAkkaSystem_OtherCommands()"

		// creates the local actor system
		ActorSystem system = ActorSystem.create("GreetingSystem")
		println("Actor System instance is: $system")
		assertNotNull system

		// get a reference to our greeting actor
		ActorRef greetingActor = system.actorOf(new Props(GreetingActor.class), "greeting_actor")
		println("Actor Reference instance is: $greetingActor")
		assertNotNull greetingActor

		// send to the greeting actor a sample Command message
		greetingActor.tell(new Command("execute"));
		assertNotNull greetingActor  // dummy

		// send to the greeting actor a Wait message
		long sleepTime = 2000 // msec
		long startSleep = System.currentTimeMillis();
		greetingActor.tell(new Wait(sleepTime));
		long stopSleep = System.currentTimeMillis();
		long delta = stopSleep - startSleep
		// TODO: enable later and make it working ...
		// assertTrue delta >= sleepTime
		assertNotNull greetingActor  // dummy

		// send to the greeting actor a shutdown message
		greetingActor.tell(new Shutdown());
		assertNotNull greetingActor  // dummy

		// send to the greeting actor a sample Command message
		// ok, but note that this actor (after the Shutdown message) will not get this message ...
		greetingActor.tell(new Command("execute2"));
		assertNotNull greetingActor  // dummy
	}

}

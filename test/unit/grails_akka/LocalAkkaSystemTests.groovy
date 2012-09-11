package grails_akka

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
// import org.junit.rules.*


/**
 * LocalAkkaSystemTests
 * <br/>
 * Execute some unit tests with a Local Akka System.
 *
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class LocalAkkaSystemTests 
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

}

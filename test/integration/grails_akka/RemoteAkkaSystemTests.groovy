package grails_akka

import static org.junit.Assert.*
import org.junit.*
import org.junit.rules.*


/**
 * RemoteAkkaSystemTests
 * <br/>
 * Integration tests with a Remote Akka System.
 *
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
class RemoteAkkaSystemTests 
{

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
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

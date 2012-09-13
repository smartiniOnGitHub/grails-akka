grails-akka - TODO
==================

TODO
----

- try to exclude the transitivity of scala (at least put a commented line in the BuildConfig of the  plugin) ...

- try with some local actor:
    + then verify them from the test webapp ...

- in the test webapp, do a grails war and inspect the generated war file ...

- fix docs and write the 2 Changelog (or similar) ... with 0.5, and 1.0, then remove this line

- package the plugin and verify if all is good ...
	+ add by hand README.md, NOTICE, LICENSE, and RELEASE-NOTES and README if present ...


- for release 1.0:
	+ verify the info put in readme.md
		* and then, create another test webapp (but not inline here) where to install it, following only readme info, to ensure they are right ...
	+ add some long-running actor, for example giving it the delay ...
		* ok, but up to now it doesn't seem to block execution for the given delay ... verify better, to make it work
	+ make an akkaService, and test it
		* do it in the test webapp, because probably in the plugin it would be hard to have it so much generalized ...
	+ fix TODO in code ...


- for release 1.0 or later:
	+ use some remote actor ... 
		* grails-akka-test webapp integration tests: 
		  call some remote actors (before start its server, maybe with a microkernel application under integration tests, as this) ...
	+ make it load a config file from the classpath, 
	  maybe with a dedicated key under Config.grails (in the webapp or in the plugin ? or in both ?), or using grails.config.locations ...
		* use sample conf files for Java and put under integration tests root folder, then try to use them
	+ update package creation, to add even README.md, NOTICE, LICENSE files in root, and RELEASE-NOTES and README if present ...


- for later:
	+ enabling scala ...
		* re-enable the creation of scala source folders when installing the plugin in a test webapp ...
		* enable scala compilation and verify if some scala content there is compiled ...
	+ create a sample Java/Groovy project where to put common classes for messages, and use it from the test webapp ...
		* maybe build it with gradle ...


- etc ...

---------------


DONE
----

- add references to akka license and scala license in NOTICE ... ok

- add (as usual) the ant build_trim-whitespace.xml, to normalize sources ...
	+ use it with: ant -f build_trim-whitespace.xml
	  (the trim-whitespace target is the default)

- run grails tests with output to console enabled, like this:
cls && grails test-app unit: -echoOut
cls && grails test-app integration: -echoOut

- try with some local actor, as seen here: http://doc.akka.io/docs/akka/2.0.3/java/untyped-actors.html
	+ ok (but without a sepcific configuration given to it), 
	  both in unit and integration tests (but had to duplicate test classes, to not put in the plugin, to have it cleaner) ...

- make messages classes more Groovy-like ... ok
	+ add some Groovy-specific extensions (annotations, etc), to show how to simplify code ... ok

- add sample of (better) testing of actors and logic used inside them using akka TestKit, as seen here:
  http://doc.akka.io/docs/akka/2.1-M2/java/testing.html
  http://doc.akka.io/docs/akka/2.0.3/scala/testing.html

- move test classes in subpackages by type (actor, command, message) ... ok
- copy classes used by tests (actor, command, message) even under the test webapp, to test (later) real calls to them from there ... ok

- comment the creation of scala source folders and other scala-related stuff ...


---------------

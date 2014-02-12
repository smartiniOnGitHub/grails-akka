grails-akka - TODO
==================

TODO
----

- for release 1.0:
	- set release number ... ok
	- update to Grails-2.2.x the plugin ... ok
	- update to Grails-2.2.x the test webapp (and resources:1.2.1, etc) ... ok
	- verify if publish even other akka modules ... no, maybe later (but I don't think)
	- create an akkaService to simplify interaction with akka (akkaService), but verify it only in the test webapp ...
	- verify if create the akkaSystem in a Servlet (or other more Grails-related), but verify if only in the test webapp ...
	- use akkaSystem and akkaService directly from a sample Controller in the test webapp ...
	- add more tests
    - add some long-running actor, for example giving it the delay ...
        - ok, but up to now it doesn't seem to block execution for the given delay ... verify better, to make it work
    - verify the info put in readme.md
        - and then, create another test webapp (but not inline here) where to install it, following only readme info, to ensure they are right ...
    - test the right behaviour (to be sure that Thread suspend/resume in done the right way), with a stress test ...
    - fix TODO in code ...
	- generate a new (empty grails webapp), and check if the (new, lighter) version of the plugin is good, 
	  for example copying test classes by the usual plugin test webapp ...
    - sample usage of akka poison pill messages:
http://doc.akka.io/docs/akka/2.2.3/java/untyped-actors.html
    - verify if/how to exclude the transitivity of scala, to avoid dependency errors in the grails dependency-report ...
        - put exclusions for this in the BuildConfig of the plugin, until I get 0 evicted (in build, compile, and runtime)... ok
        - I commented all the exclusions for now otherwise a compile error will happen ...
          just for reference, but could be the wrong way to do this, so let's see later if/how to fix this
    - sample usage of Futures:
http://doc.akka.io/docs/akka/2.2.3/java/futures.html


- later:
	- update dependencies to Akka-2.3.x and Scala-2.10.x ...
    - use some remote actor ...
        * grails-akka-test webapp integration tests:
          call some remote actors (before start its server, maybe with a microkernel application under integration tests, as this) ...
    - make it load a config file from the classpath,
      maybe with a dedicated key under Config.grails (in the webapp or in the plugin ? or in both ?), or using grails.config.locations ...
        * use sample conf files for Java and put under integration tests root folder, then try to use them
    - update package creation, to add even README.md, NOTICE, LICENSE files in root, and RELEASE-NOTES and README if present ...
        * add by hand CHANGES.md with inside the full history of changes ... no, better: put this in plugin docs, in gdoc format
    - sample usage of akka typed actors:
http://doc.akka.io/docs/akka/2.2.3/java/typed-actors.html
	- (optional) migrate unit tests to use JavaTestKit more deeply ...


- others (later):
	- create a sample Java/Groovy project where to put common classes for messages, and use it from the test webapp ...
		- maybe build it with gradle ...


- etc ...

---------------


DONE
----

- run grails tests with output to console enabled, like this:
cls && grails test-app unit: -echoOut
cls && grails test-app integration: -echoOut

- try with some local actor, as seen here: http://doc.akka.io/docs/akka/2.0.5/java/untyped-actors.html
    + ok (but without a specific configuration given to it),
      both in unit and integration tests (but had to duplicate test classes, to not put in the plugin, to have it cleaner) ...

- make messages classes more Groovy-like ... ok
    + add some Groovy-specific extensions (annotations, etc), to show how to simplify code ... ok

- add sample of (better) testing of actors and logic used inside them using akka TestKit, as seen here:
  http://doc.akka.io/docs/akka/2.0.5/java/testing.html
  http://doc.akka.io/docs/akka/2.0.5/scala/testing.html

- move test classes in subpackages by type (actor, command, message) ... ok
- copy classes used by tests (actor, command, message) even under the test webapp, to test (later) real calls to them from there ... ok

- comment the creation of scala source folders and other scala-related stuff ... ok

- add (as usual) the ant build_trim-whitespace.xml, to normalize sources ...
    + use it with: ant -f build_trim-whitespace.xml
      (the trim-whitespace target is the default)

- add references to akka license (and maybe even to scala license) in NOTICE ... ok
- add Apache license header in any file, important ... ok

- try with some local actor:
    + then verify them from the test webapp ... ok

- in the test webapp, do a grails war and inspect the generated war file ... ok

- update dependencies to Java 7 ... ok, but only when required by Akka/Scala
- enabling scala ... maybe later
	- re-enable the creation of scala source folders when installing the plugin in a test webapp ...
	- enable scala compilation and verify if some scala content there is compiled ...
	- or maybe use the grails-scala plugin, but ONLY if updated to Scala version used by Akka here (now is Scala-2.9.2) ...


- for release 0.5 (first public release):
    + update gdocs under src/docs/guide , then publish on github-pages ... ok
    + tag sources in git ... ok
    + put in downloads a zipped version of the plugin
        * ensure LICENSE file is put in root of the generated plugin ... ok
        * add by hand README.md, NOTICE, and RELEASE-NOTES and README if present ... ok

- for release 0.6 (second public release):
    + update akka to 2.0.4 ... ok
    + publish the plugin ... ok
	+ update generated gdocs on github-pages ... ok

- for release 0.6.1 (maintenance public release):
    + update akka to 2.0.5 ... ok
    + publish the plugin, and then update generated gdocs on github-pages ... ok

- for release 0.6.2 (maintenance public release):
    + accept pull request (for cleanup) from Burt ... ok
    + rename package of test classes and exclude it from packaging ... ok
	+ run many tests on plugin, and the test webapp ... ok
    + publish the plugin, and then update generated gdocs on github-pages ... ok

- for release 0.6.5 (maintenance public release):
    + add Thumbs.db in pluginExcludes ... ok

- for release 0.12.5 (maintenance public release):
	- the strange number version of the plugin is made by Scala-2.10.2 (required by Akka-2.2.3):
		10.2 + 2.3 = 10.5 :-) ... but next version probably will be 1.0.0
	- update to Akka-2.2.3 (aligned with Scala-2.10.2), so this strange number in the plugin release ... ok
	- verify if add an akkaService ... but check how to configure it from outside ... ok but later
	- do other tests and small adjustments ... ok
	- removed the Typesafe Maven repository (no more needed) ... ok
	- updates for Akka-2.2.3 started (see LocalAkkaUnitTests under test webapp, unit tests):
		- make it work in the test webapp, then copy/update even in the plugin ... ok
		- to run only unit tests with output use (from the root folder of the test webapp: ... ok
		  grails test-app unit: -echoOut
	- fix tests (make them work) both for the plugin, and for the test webapp ... ok
    - update Groovy doc files ... ok


---------------

grails-akka - TODO
==================

TODO
----

- later:
	- update dependencies to Akka-2.3.4 or later (but stay on Scala-2.10.x for now) ...
	- check if set a new plugin release as 2.3.4 or 2.3.4_2.10 (like in the Scala world), and then make even a _2.11 release ...
	- create a (another) test webapp with Grails-2.4.2 and configure it for testing the plugin in inline mode (as in the other) ... ok
	- merge/update changes from the existing (older) test webapp into the new one (based on Grails-2.4.x)... ok
	- test webapp24: fix failing tests (only) in its integration tests, see not versioned file integration-tests.out ...
		cls && grails test-app integration:  -echoOut > integration-tests.out
	- test webapp: add a fieldset to wrap the form with input text field (and write in page which is the test run there),
	  and put in page result of that process, using reply to the sender ...
	- add other tests in the actor and integration tests (with slow process, etc), and call them from page to ensure all if good ...
	- test webapp: add the config key (grails_akka.akkaSystem.name) so the akkaService can use it as actorSystem name (when given) ...
	- test webapp: improve AkkaController to invoke/play more complex methods in actors ...
	- test webapp: improve AkkaController to handle ajax calls from its pages, and there send messages to actors ...
	- test webapp: (enabling and) fix failing tests in AkkaControllerSpec (for testing AkkaController using Spock) ...
	- akkaService: check if add an utility method for Identifying Actors via Actor Selection ...
    - fix TODO in plugin ...
	- add more tests ...
	- verify (deeply) if it's better to not expose directly the ActorSystem in the akkaService ...
    - verify the info put in readme.md
        - and then, create another test webapp (but not inline here) where to install it, following only readme info, to ensure they are right ...
    - add some long-running actor, for example giving it the delay ...
        - ok, but up to now it doesn't seem to block execution for the given delay ... verify better, to make it work
    - test the right behaviour (to be sure that Thread suspend/resume in done the right way), with a stress test ...
    - use some remote actor ...
http://doc.akka.io/docs/akka/current/java/remoting.html#remote-lookup-sample-java
		- under plugin test folder, create a remote-akka folder containing a standalone akka server project to use as remote akka server test instance,
		  but verify how to start/stop/test it in a simple way (Groovy shell scripts, or Gradle, or Sbt) ...
		- in the test webapp, add an akka config file to point to that remote akka instance, 
		  but maybe put it in a folder not under classpath, so if/when needed if must be copied by hand 
		  (or enable it but only in a new, dedicated test webapp) ...
        - grails-akka-test webapp integration tests:
          call some remote actors (before start its server, maybe with a microkernel application under integration tests, as this) ...
    - make it load a config file from the classpath,
      maybe with a dedicated key under Config.grails (in the webapp or in the plugin ? or in both ?), or using grails.config.locations ...
        - use sample conf files for Java and put under integration tests root folder, then try to use them
    - update package creation, to add even README.md, NOTICE, LICENSE files in root, and RELEASE-NOTES and README if present ...
        - add by hand CHANGES.md with inside the full history of changes ... no, better: put this in plugin docs, in gdoc format
    - sample usage of akka typed actors:
http://doc.akka.io/docs/akka/current/java/typed-actors.html
	- verify if/how to set akkaSystem name to use in the akkaService ...
	- (optional) create a Controller (and related views) for simple diagnostic purposes on the AkkaSystem ...
	    - enabled by default only in DEV environment, and with the ability to enable it (but protected) in other environments ...
	- (optional) Asynchronous Integration Testing using JavaTestKit ... maybe later
	- generate a new (empty grails webapp), and check if the (new, lighter) version of the plugin is good, 
	  for example copying test classes by the usual plugin test webapp ...
    - sample usage of akka poison pill messages:
http://doc.akka.io/docs/akka/current/java/untyped-actors.html
    - verify if/how to exclude the transitivity of scala, to avoid dependency errors in the grails dependency-report ...
        - put exclusions for this in the BuildConfig of the plugin, until I get 0 evicted (in build, compile, and runtime)... ok
        - I commented all the exclusions for now otherwise a compile error will happen ...
          just for reference, but could be the wrong way to do this, so let's see later if/how to fix this
    - sample usage of Futures:
http://doc.akka.io/docs/akka/current/java/futures.html
    - fix remaining TODO in code ...


- others (later, for Grails-2.3.x or higher):
	- create a sample Java/Groovy project where to put common classes for messages, and use it from the test webapp ...
		- maybe build it with gradle ...
	- update messages classes (at least Result, Failure) to use the Groovy @Immutable annotation, make class definition final, 
	  and keep only the property but not final and remove constructor with arguments, 
	  because (both block of code) will be no more needed ...
	- test webapp: do some cleanup inside Bootstrap, moving some things in one (or more) BootstrapHelper class ...
	- test webapp: align AkkaController (and maybe even AkkaService) to Servlet 3.0 Asynchronous Rendering ...
	- test webapp: cleanup/improve AkkaControllerSpec ...
	- test webapp: add an AkkaSystemSpec with Spock, but for integration tests now (using the real akkaService, etc) ...
	- test webapp: add an AkkaRestController, to expose methods via rest ...


- etc ...

---------------


DONE
----

- run grails tests with output to console enabled, like this:
cls && grails test-app unit: -echoOut
cls && grails test-app integration: -echoOut

- try with some local actor, as seen here: http://doc.akka.io/docs/akka/current/java/untyped-actors.html
    + ok (but without a specific configuration given to it),
      both in unit and integration tests (but had to duplicate test classes, to not put in the plugin, to have it cleaner) ...

- make messages classes more Groovy-like ... ok
    + add some Groovy-specific extensions (annotations, etc), to show how to simplify code ... ok

- add sample of (better) testing of actors and logic used inside them using akka TestKit, as seen here:
  http://doc.akka.io/docs/akka/current/java/testing.html
  http://doc.akka.io/docs/akka/current/scala/testing.html

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
	- the strange number version of the plugin is made by Scala-2.10.2 (required by Akka):
		10.2 + 2.3 = 10.5 :-) ... but next version will be a ga release (no more 0.x release)
	- update to Akka-2.2.3 (aligned with Scala-2.10.2), so this strange number in the plugin release ... ok
	- verify if add an akkaService ... but check how to configure it from outside ... ok but later
	- do other tests and small adjustments ... ok
	- removed the Typesafe Maven repository (no more needed) ... ok
	- updates required by Akka started (see LocalAkkaUnitTests under test webapp, unit tests):
		- make it work in the test webapp, then copy/update even in the plugin ... ok
		- to run only unit tests with output use (from the root folder of the test webapp: ... ok
		  grails test-app unit: -echoOut
	- fix tests (make them work) both for the plugin, and for the test webapp ... ok
    - update Groovy doc files ... ok

- for release 2.2.3:
	- set release number ... ok
	- update release number to the same of Akka ... ok
	- update to Grails-2.2.x the plugin ... ok
	- update to Grails-2.2.x the test webapp (and resources:1.2.1, etc) ... ok
	- verify if publish even other akka modules ... no, maybe later (but I don't think)
	- create an akkaService to simplify interaction with akka (akkaService), but verify it only in the test webapp ... ok
	- verify if create the akkaSystem in a Servlet (or other more Grails-related), but verify if only in the test webapp ... no
	- in akkaService (transactional false), make akkaSystem static final, and use lifecycle annotations like seen here: ...
https://jira.grails.org/browse/GRAILS-10137
	  then make the actorSystem dependent on the webapp context name,
	  and then test the service in the test webapp (and even in Bootstrap, to have more info even at application startup) ... ok
	- use akkaService directly from a sample Controller in the test webapp and ask for akkaSystem (maybe hiding it) ... ok
	- (optional) add base class for actor messages (from Akka best practices), and add other sample generic messages ... ok
	- in akkaService, check if akkaSystem read-only is good ... yes, it seems ok the same ... ok
	- in akkaService, add comments to service methods, to better see them in generated docs ... ok
	- test akkaService, with not-so-trivial unit and integration (this is important) tests ... ok
	- test webapp: in Bootstrap, add one or more Actors using akkaService ... ok
	- test webapp: create an AkkaController to show some akkaSystem info, and to start invoke/play with sample actor ... ok
	- test webapp: setup unit tests in AkkaControllerSpec (for testing AkkaController using Spock) ... ok
		- note that currently to run it it's not enough to run using the usual test-app command, instead use:
		  grails test-app AkkaControllerSpec -echoOut
		  or
		  grails test-app :spock -echoOut
	- generate and publish plugin doc for this release ... ok

- for 2.2.4 (maintenance and improvements):
	- update release number to the same of Akka ... ok
	- update all references (even in links, docs, etc) to this new release number ... ok
	- update resources plugin to latest stable ... ok
	- update to Grails-2.2.5 (maintenance over 2.2.x) ... ok
	- update all plugins in the test webapp ... ok
	- add in akkaService methods to handle actorSelection, as seen here: ... ok
http://doc.akka.io/docs/akka/current/java/untyped-actors.html#actorselection-java
	- generate and publish plugin doc for this release ... ok


---------------

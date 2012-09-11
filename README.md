grails-akka
===========

This is a Grails Plugin that enable the usage of Akka actors (both local and remote) in Grails Web projects.


Distribution
============

grails-akka-<version>.zip for the plugin source distribution
  -- this is the recommended version, because the binary version of this plugin doesn't contain required jars


Dependencies
============

* a Servlet 3.x container to run the Web application
* Akka 2.x (as specified in plugin descriptor) and related dependencies


Installation
============

In BuildConfig.groovy of the webapp, under the plugins section (at the end of the file) add:

	compile ":grails-akka:<version>"

and copy the plugin zip under the webapp lib folder, 
then run grails refresh-dependencies, grails clean , then grails compile , and the plugin should be installed.
In case of Errors during plugin installation, Set log level to 'warn' in BuildConfig.groovy to get more information, and retry.
Instead of the compile it's possible even to use runtime, and in that case simply running a grails console should be enough.

One time installed, you can safely move in a backup folder or delete the plugin zip just copied, 
but if the project folder (under the user home folder) is deleted, the plugin should be reinstalled.
Instead, running a grails war (with the plugin zip still under the lib folder) doesn't put it in the generated war, 
so should be safe even to keep it there.


Documentation
=============

See the Plugin documentation (sources under src/docs/).

Home Page for the project (and sources) on GitHUB:
[grails-akka](https://github.com/smartiniOnGitHub/grails-akka`)


import javax.servlet.ServletContext

import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.Environment


class BootStrap
{
    def grailsApplication

    def init = { ServletContext sc ->

        println "Bootstrap running in Environment " + Environment.current
        def now = new Date()

        // final Thread thread = Thread.currentThread();
        // final ClassLoader tccl = thread.getContextClassLoader();

		println """
Remember that to test in the right way the grails-akka plugin (inline here), 
this webapp must be run not with run-app, but with run-war ...
				"""

    }

    def destroy = {
    }


}

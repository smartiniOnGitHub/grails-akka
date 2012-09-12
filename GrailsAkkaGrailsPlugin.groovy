class GrailsAkkaGrailsPlugin {
    // the plugin version
    def version = "1.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Akka Plugin" // Headline display name of the plugin
    def author = "Sandro Martini"
    def authorEmail = "sandro.martini@gmail.com"
    def description = '''\
Let Grails webapps to call/use Akka actors from Groovy and Java.
Note that it requires a Servlet 3.x environment to run.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-akka"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "MartinS", url: "https://github.com/smartiniOnGitHub/grails-akka/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "other developer", email: "other.developer@my-company.com" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "JIRA", url: "https://github.com/smartiniOnGitHub/grails-akka/issues/" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/smartiniOnGitHub/grails-akka/" ]

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }

}

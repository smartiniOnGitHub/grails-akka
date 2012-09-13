// grails.servlet.version = "3.0"  // verify if makes sense to enable this even here ...
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.source.level = 1.6
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

def akkaVersion = '2.0.3'

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // excludes 'ehcache'  // uncomment to disable ehcache
    }

    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        grailsCentral()
        //mavenCentral()
        //mavenLocal()
        mavenRepo "http://repo.typesafe.com/typesafe/releases/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        compile("com.typesafe.akka:akka-actor:$akkaVersion", "com.typesafe.akka:akka-remote:$akkaVersion")
        test("com.typesafe.akka:akka-testkit:$akkaVersion")
    }

    plugins {
        build(":release:2.0.4", ":rest-client-builder:1.0.2") {
            export = false
        }
    }

}

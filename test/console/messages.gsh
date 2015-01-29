// some Groovy statements for testing available classes from the Grails Console
//
// to run this with right CLASSPATH and libraries, ensure to run inside a Grails Console started in Plugin main folder (and not simply from a Groovy Console) ...

import  grails_akka_test.message.*

def r1 = new Result()
println "r1 = $r1"
assert r1.value == null

def r2 = new Result("test")
println "r2 = $r2"
assert r2.value == "test"

// r1.value = "set value"  // forbidden because it's a read-only property
// r2.value = "set value"  // forbidden because it's a read-only property

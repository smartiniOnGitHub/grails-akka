/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails_akka_test.actor

import grails_akka_test.message.Greeting
import grails_akka_test.message.Shutdown
import grails_akka_test.message.Stop
import grails_akka_test.message.Wait
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.testkit.JavaTestKit;
import scala.concurrent.duration.Duration;
import akka.event.Logging
import akka.event.LoggingAdapter

/**
 * Greeting actor, as a sample.
 * <br/>
 * This code is derived from Akka Samples.
 */
class GreetingActor extends UntypedActor
{
    LoggingAdapter log = Logging.getLogger(getContext().system(), this)

	ActorRef target = null

	// referencing another actor, for sample
	private ActorRef otherActor = null  // getContext().actorOf(new Props(OtherActor.class), "other_actor")


	public GreetingActor() {
	}

	// new, add a new version of the constructor with a reference to the otherActor, so now I must define even the standard (no arg) constructor ...
	public GreetingActor(ActorRef otherActor) {
		this.otherActor = otherActor;
	}


    @Override
    void onReceive(Object message) throws Exception
    {
        String messageClassName = message?.getClass()?.getName()

        if (message == null)
        {
            log.warning("$messageClassName: null message")
            unhandled(message)
        }
        else if (message instanceof Greeting)
        {
            log.info("$messageClassName: Hello \"" + ((Greeting) message)?.who + "\"")
        }
        // else if (message instanceof BaseMessage)  // but it's abstract ...
        // {
        //     log.info("$messageClassName: Hello BaseMessage instance")
        // }
        else if (message instanceof Stop)
        {
            log.info(messageClassName + ": " + "Stop this actor now ...")
            // Stops this actor and all its supervised children
            getContext().stop(getSelf())
        }
        else if (message instanceof Shutdown)
        {
            log.info(messageClassName + ": " + "Shutdown this akka system now ...")
            // Shutdown the entire akka system
            getContext.getSystem().shutdown()
        }
        else if (message instanceof Wait)
        {
            long sleep = ((Wait) message).msec
            log.info(messageClassName + ": " + "Waiting for " + sleep + " milliseconds now ...")
            // Sleep this actor for the given time
            long startSleep = System.currentTimeMillis()
            // sleep this actor
            // note that this is not the right way, but should be ok in this small test ...
            // because Thread.sleep breaks actors management as it will monopolize all threads of the used executor
            Thread.sleep(sleep)
            // note that probably instead it's needed something like this
            // getContext.getSystem().getScheduler().scheduleOnce(sleep, sender, "Done")
            long stopSleep = System.currentTimeMillis()
            log.info("Wait: " + "End Waiting, after " + (stopSleep - startSleep) + " milliseconds.")
         } else if (message instanceof ActorRef) {
            log.info("$messageClassName: Message from an ActorRef, now reply to it ...")
			target = (ActorRef) message;
			getSender().tell("done", getSelf());
		}
        else
        {
            log.warning("Unknown message type $messageClassName, contents: \"" + message?.toString() + "\"")
            unhandled(message)
        }
    }
}

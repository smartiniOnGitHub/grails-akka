package grails_akka.actor

import akka.actor.UntypedActor
import akka.event.*

import grails_akka.actor.*
import grails_akka.command.*
import grails_akka.message.*


/**
 * Greeting actor, as a sample.
 * <br/>
 * This code is derived from Akka Samples.
 */
public class GreetingActor extends UntypedActor
{
    LoggingAdapter log = Logging.getLogger(getContext().system(), this)

    @Override
    public void onReceive(Object message) throws Exception
    {
        String messageClassName = message?.getClass()?.getName();

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
            // TODO: probably it's needed something like this
            // getContext.getSystem().getScheduler().scheduleOnce(sleep, sender, "Done")
            long stopSleep = System.currentTimeMillis()
            log.info("Wait: " + "End Waiting, after " + (stopSleep - startSleep) + " milliseconds.")
        }
        else
        {
            log.warning("Unknown message type $messageClassName, contents: \"" + message?.toString() + "\"")
            unhandled(message)
        }
    }

}

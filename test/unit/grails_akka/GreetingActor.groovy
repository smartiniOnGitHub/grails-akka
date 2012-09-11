package grails_akka

import akka.actor.UntypedActor
import akka.event.*


/**
 * Greeting actor, as a sample.
 * <br/>
 * This code is derived from Akka Samples.
 */
public class GreetingActor extends UntypedActor 
{
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public void onReceive(Object message) throws Exception 
	{
		if (message instanceof Greeting)
		{
			log.info("Greeting: " + "Hello \"" + ((Greeting) message)?.who + "\"");
		}
		else if (message instanceof Command)
		{
			String command = ((Command) message)?.command
			log.info("Command: " + "Processing command \"" + command + "\" ...");
		}
		else if (message instanceof Shutdown)
		{
			log.info("Shutdown: " + "Stop this actor now ...");
			// Stops this actor and all its supervised children
			getContext().stop(getSelf());
			// Shutdown the entire akka system
			// getContext.getSystem().shutdown();
		}
		else if (message instanceof Wait)
		{
			long sleep = ((Wait) message)?.msec
			log.info("Wait: " + "Waiting for " + sleep + " milliseconds now ...");
			// Sleep this actor for the given time
			long startSleep = System.currentTimeMillis();
			// sleep this actor
			// note that this is not the right way, but should be ok in this small test ...
			// because Thread.sleep breaks actors management as it will monopolize all threads of the used executor
			Thread.sleep(sleep)
			// TODO: probably it's needed something like this
			// getContext.getSystem().getScheduler().scheduleOnce(sleep, sender, "Done")
			long stopSleep = System.currentTimeMillis();
			log.info("Wait: " + "End Waiting, after " + (stopSleep - startSleep) + " milliseconds.");
		}
		else
		{
			log.warning(message?.getClass()?.getName() + ": " + "Received unknown message \"" + message?.toString() + "\"");
			unhandled(message);
		}
    }

}

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
			log.info("Hello \"" + ((Greeting) message)?.who + "\"");
		}
		else
		{
			log.warning("Received unknown message \"" + message?.toString() + "\"");
			unhandled(message);
		}
    }

}

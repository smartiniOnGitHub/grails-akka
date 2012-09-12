package grails_akka.actor

import akka.actor.UntypedActor
import akka.event.*


/**
 * Abstract base actor class, to use (when desired) as base class for all other messages here.
 */
public abstract class BaseActor extends UntypedActor
{
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception
    {
        // do nothing here ... but override in subclasses
        // String messageClassName = message?.getClass()?.getName();
    }

}

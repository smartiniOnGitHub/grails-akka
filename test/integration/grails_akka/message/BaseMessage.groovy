package grails_akka.message

/**
 * Abstract base message class, to use (when desired) as base class for all other messages here.
 * <br/>
 * Used a kind of message to send to actors.
 * <br/>
 * This code is derived from Akka Samples.
 */
public abstract class BaseMessage implements Serializable
{

    public BaseMessage()
    {
    }

}

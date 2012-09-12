package grails_akka.message

/**
 * Greeting message, as a sample.
 * <br/>
 * Used a kind of message to send to actors.
 * <br/>
 * This code is derived from Akka Samples.
 */
public class Greeting implements Serializable
{
    public final String who;

    public Greeting(String who)
    {
        this.who = who;
    }

}

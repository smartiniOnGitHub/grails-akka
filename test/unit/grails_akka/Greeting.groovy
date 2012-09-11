package grails_akka

/**
 * Greeting message, as a sample.
 * <br/>
 * Used by actors.
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

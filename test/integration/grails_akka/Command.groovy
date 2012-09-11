package grails_akka

/**
 * Command, as a sample.
 * <br/>
 * Used by actors.
 * <br/>
 * This code is derived from Akka Samples.
 */
public class Command implements Serializable 
{
	public final String command;

	public Command(String command) 
	{
		this.command = command;
	}

}

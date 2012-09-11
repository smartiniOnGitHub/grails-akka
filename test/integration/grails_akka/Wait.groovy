package grails_akka

/**
 * Waiting message, as a sample.
 * <br/>
 * Used by actors.
 * <br/>
 * This code is derived from Akka Samples.
 */
public class Wait implements Serializable 
{
	public final long msec;

	public Wait(long msec) 
	{
		// this.msec = (msec < 0) ? 0l : msec;
		if (msec < 0)
			throw new IllegalArgumentException("Waiting time must be 0 or positive.");
		
		this.msec = msec;
	}

}

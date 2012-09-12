package grails_akka.message

import groovy.transform.*


/**
 * Waiting message, as a sample.
 * <br/>
 * Used a kind of message to send to actors.
 */
@EqualsAndHashCode
// @Immutable
@ToString
public class Wait implements Serializable
{
    final long msec;

    public Wait(long msec)
    {
        // this.msec = (msec < 0) ? 0l : msec;
        if (msec < 0)
            throw new IllegalArgumentException("Waiting time must be 0 or positive.");

        this.msec = msec;
    }

}

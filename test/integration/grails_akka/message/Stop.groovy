package grails_akka.message

import groovy.transform.*


/**
 * Stop message, as a sample.
 * <br/>
 * Used a kind of message to send to actors.
 */
// @EqualsAndHashCode
@Immutable
// @ToString
public class Stop extends BaseMessage implements Serializable
{

}

package grails_akka.message

import groovy.transform.*


/**
 * Shutdown message, as a sample.
 * <br/>
 * Used a kind of message to send to actors.
 */
// @EqualsAndHashCode
@Immutable
// @ToString
public class Shutdown extends BaseMessage implements Serializable
{

}

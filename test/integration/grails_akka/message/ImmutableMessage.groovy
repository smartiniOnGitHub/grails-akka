package grails_akka.message

/**
 * ImmutableMessage, as a sample.
 * <br/>
 * Used a kind of message to send to actors.
 * <br/>
 * This code is derived from Akka Samples.
 */
public class ImmutableMessage
{
    private final int sequenceNumber;
    private final List<String> values;

    public ImmutableMessage(int sequenceNumber, List<String> values)
    {
        this.sequenceNumber = sequenceNumber;
        this.values         = Collections.unmodifiableList(new ArrayList<String>(values));
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public List<String> getValues() {
        return values;
    }

}

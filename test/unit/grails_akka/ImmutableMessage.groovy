package grails_akka

/**
 * ImmutableMessage, as a sample.
 * <br/>
 * Used by actors.
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

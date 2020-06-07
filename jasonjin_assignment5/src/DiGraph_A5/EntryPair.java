package DiGraph_A5;


public class EntryPair implements Comparable<EntryPair> {
    public Vertex value;
    public long priority;

    public EntryPair(Vertex aValue, long aPriority) {
        value = aValue;
        priority = aPriority;
      }

      public Vertex getValue() { return value; }
      public long getPriority() { return priority; }

	@Override
	public int compareTo(EntryPair o) {
		int thisComp = (int) this.priority;
		int thatComp = (int) o.priority;
		//return 0;
		return this.value.label.compareTo(o.value.label);
	}

}

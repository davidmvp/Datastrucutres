

	/** OrderedSet interface for the fourth assignment.  This represents a
    container of unique objects that are ordered, similar to the
    SortedSet.java interface in the API.  Note that this interface
    extends our generic Set interface. 
*/

public interface OrderedSet<T extends Comparable<? super T>> 
    extends Iterable<T>, Set<T>  {

    /** Return a subset of this set with elements in the range [start, end).
        This does not return a copy of the set, but rather a "view" into it
        @param start the inclusive low value of the range
        @param end the exclusive high value of the range
        @return the subset itself
    */
    public OrderedSet<T> subset(T start, T end);

	public void print();


    /*  Any class that implements this interface must present the Set
        elements ordered from any methods which return a Set or
        iterate over a Set somehow (including toString).
    */
}


/**
 * edge class.
 * @author davidmvp23
 *
 */
public class Edge {
    /**
     * start of the edge.
     */
    private Vertex start;
    /**
     * end of the edge.
     */
    private Vertex end;
    /**
     * weight of the edge.
     */
    private int weight;
    /**
     * constructor for edge.
     * @param u start
     * @param v end
     */
    public Edge(Vertex u, Vertex v) {
      
        this.start = u;
      
        this.end = v;
        u.addDegree();
     
        v.addDegree();
        u.addNeightbors(v);
        v.addNeightbors(u);               
    }
    /**
     * set weight.
     * @param i i
     */
    public void setWeight(int i) {
        this.weight = i;
    }
    /**
     * get weight.
     * @return weight.
     */
    public int getWeight() {
        return this.weight;
    }
    /**
     * get start.
     * @return start.
     */
    public Vertex getStart() {
        return this.start;
    }
    /**
     * get end.
     * @return end.
     */
    public Vertex getEnd() {
        return this.end;
    }
    /**
     * check if its incident.
     * @param v v
     * @return true if it is.
     */
    public boolean isIncident(Vertex v) {
        return v.equals(this.start) || v.equals(this.end);
    }
    /**
     * return string.
     * @return a string that is readable.
     */
    public String toString() {
        return this.start.toString() + " " + this.end.toString()
                + " " + this.weight + "       ";
    }
}
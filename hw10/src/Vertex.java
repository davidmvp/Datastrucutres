import java.util.ArrayList;
/**
 * vertex class.
 * @author davidmvp23
 *
 */
public class Vertex {
    /**
     * all the neighbors.
     */
    private ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
    /**
     * IP.
     */
    private String IP = "";
    /**
     * degree of a vertex.
     */
    private int degree = 0;
    /**
     * ID.
     */
    private int ID = 0;
    /**
     * vertex pa.
     */
    private Vertex pa;
    /**
     * constructor.
     */
    public Vertex() {
        
    }
    /**
     * helper constructor.
     * @param stuff the ip.
     */
    public Vertex(String stuff) {
        this.IP = stuff;
    }
    /**
     * set id.
     * @param i id.
     */
    public void setId(int i) {
        this.ID = i;
    }
    /**
     * get ID.
     * @return ID number
     */
    public int getId() {
        return this.ID;
    }
    /**
     * add edges.
     */
    public void addDegree() {
        this.degree++;
    }
    /**
     * return degree.
     * @return degree.
     */
    public int degree() {
        return this.degree;
    }
    /**
     * add neighbors.
     * @param v add V.
     */
    public void addNeightbors(Vertex v) {
        this.neighbors.add(v);
        return;
    }
    /**
     * set its parent.
     * @param v pa.
     */
    public void setParent(Vertex v) {
        this.pa = v;
    }
    /**
     * return parent.
     * @return the parent.
     */
    public Vertex returnParent() {
        return this.pa;
    }
    /**
     * get all the neighbors.
     * @return its neighbors.
     */
    public ArrayList<Vertex> getNeighbors() {
        return this.neighbors;
    }
    /**
     * check to see if they are adjacent.
     * @param v vertex
     * @return true or false.
     */
    public boolean isAdjacent(Vertex v) {
        if (this.neighbors.contains(v)) {
            return true;
        }
        return false;
    }
    /**
     * get IP address.
     * @return return ip.
     */
    public Object getIP() {
        return this.IP;
    }
    /**
     * print to string.
     * @return return the string.
     */
    public String toString() {
        return "ID: " + this.ID + "IP address " + this.IP;
    }


}
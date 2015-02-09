import java.util.ArrayList;
/**
 * graph interface.
 * @author davidmvp23
 *
 */
public interface Graph {
    /**
     * number of edges.
     * @return return edges.
     */
    int numEdges();
    /**
     * number of verts.
     * @return verts
     */
    int numVerts();

    /**
    * Add a vertex or edge if it doesn't exist yet.
    * @param v vertex.
    * @return true if exists. 
    */
    boolean addVertex(Vertex v);
    /**
     * add edges.
     * @param v v
     * @param u u
     * @param i i
     * @return true or false.
     */
    boolean addEdge(Vertex v, Vertex u, int i);  // implicitly adds vertices?

    
    /**
     * return all the neighbors.
     * @param v v
     * @return neighbors
     */
    ArrayList<Vertex> neighbors(Vertex v);
    /**
     * degree.
     * @param v v
     * @return degree.
     */
    int degree(Vertex v);

    

   

}
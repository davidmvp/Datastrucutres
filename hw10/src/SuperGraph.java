import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Super Graph.
 * @author davidmvp23
 *
 */
public class SuperGraph implements Graph {
    
    /**
     * max vertices, it can change for in3.txt.
     */
    public static final int MAXVERTS = 1000;
    /**
     * All the vertices.
     */
    private static ArrayList<Vertex> vertices;
    /**
     * to keep all the edges.
     */
    private static  Edge[][] graph;
    /**
     * number of edges.
     */
    private int numEdges;
   
    /**
     * keep track of ID.
     */
    private int iD;
    /**
     * bandwidth.
     */
    private int [] bandwidth;
    /**
     * number of hops.
     */
    private int[] numHops;
    /**
     * use to check if the node has been traversed.
     */
    private boolean[] s; 
    /**
     * used to find clusters.
     */
    private ArrayList<Vertex>[] clusters;
    /**
     * to check the highest level.
     */
    private int level = 0;
    /**
     * current level.
     */
    private int currLevel;
  
    /**
     * constructor for supergraph.
     */
    public SuperGraph() {
        this(MAXVERTS);
    }
    /**
     * create constructor with verts size.
     * @param verts size of the graph.
     */
    public SuperGraph(int verts) {
        graph = new Edge[verts][verts];
        this.numEdges = 0;
        vertices = new ArrayList<Vertex>(verts);
        this.iD = 0;
    }
    /**
     * return number of edges.
     * @return number of edges.
     */
    public int numEdges() {
        return this.numEdges;
    }
    /**
     * return number of verts.
     * @return number of verts.
     */
    public int numVerts() {
        return SuperGraph.vertices.size();
    }

    /**
     * check if the grapg contains vertex v.
     * @param v the vertex
     * @return true if its in the graph.
     */
    public boolean contains(Vertex v) {
        Iterator<Vertex> itr = vertices.iterator();
        while (itr.hasNext()) {
            Object tmp = itr.next().getIP();
          
            if (tmp .equals(v.getIP()))  {
                
                return true;
            }
        }
        return false;
        
    }
    /**
     * return vertex.
     * @param t the vertex we want to find.
     * @return return the vertex.
     */
    public Vertex returnVertex(String t) {
        Iterator<Vertex> itr = vertices.iterator();
        Vertex temp;
        while (itr.hasNext()) {
            temp = itr.next();
            if (temp.getIP().equals(t)) {
                
                return temp;
            }
        }
        return null;
        
    }
      
        
    /**
     * add a vertex in the graph.
     * @param v vertex.
     * @return true if its a success.
     */
    public boolean addVertex(Vertex v) {
        if (SuperGraph.vertices.size() == SuperGraph.graph.length) {
            return false;
        }

        if (!SuperGraph.vertices.contains(v)) {
            v.setId(this.iD);
            this.iD++;
            SuperGraph.vertices.add(v);
            return true;
        }
        return false;
    }
    
    
 

    /** Add an edge to the graph, adding the vertices if necessary.
        @param v the start of the edge
        @param u the end of the edge
        @param i the bandwidth.
        @return true if added, false if it already existed in the graph
    */
    public boolean addEdge(Vertex v, Vertex u, int i) {
       
        Edge e = new Edge(v, u);
        e.setWeight(i);
        graph[v.getId()][u.getId()] = e;
        graph[u.getId()][v.getId()] = e;
        return true;
    }
    /**
     * find neightbors.
     * @param v the vertex.
     * @return return all of its neighbors.
     */
    public ArrayList<Vertex> neighbors(Vertex v) {
        return v.getNeighbors();
        
    }
    /**
     * find out the adjacent vertex.
     * @param v vertex
     * @param e edge
     * @return the other vertex.
     */
    public Vertex adjacentVertex(Vertex v, Edge e) {
        Vertex start = e.getStart();
        Vertex end = e.getEnd();
        if (start.equals(v)) {
            return end;
        } else {
            return start;
        }
    }
    /**
     * Adjacent Edges.
     * @param v vertex
     * @return return all of its adjacent edges.
     */
    public ArrayList<Edge> adjacentEdges(Vertex v) {
        
        int index = 0;
        ArrayList<Vertex> n = this.neighbors(v);
        ArrayList<Edge> sa = new ArrayList<Edge>();
        Iterator<Vertex> itr = n.iterator();
        while (itr.hasNext()) {
            Vertex temp = itr.next();
            index = vertices.indexOf(temp);
            if (index == -1) {
                return null;
            }
            sa.add(graph[vertices.indexOf(v)][index]);
        }
        return sa;
    }
    /**
     * find the degree of a vertex.
     * @param v the vertex.
     * @return reutnr the degree.
     */
    public int degree(Vertex v) {
        return v.degree();
    }
    /**
     * lookup table can display all the nodes in the network.
     */
    public void lookupTable() {
        System.out.println("Node Look-up table");
        System.out.println("ID      " + "Ip");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i).getId()
                    + "    " + vertices.get(i).getIP());
        }
        System.out.println();
    }
    /**
     * display the adjacency lists.
     */
    public void adjacent() {
        System.out.println();
        System.out.println("Adjacency Lists based on " 
                + "vertex IDs (not showing bandwidth)");
        Iterator<Vertex> itr = vertices.iterator();
        Vertex temp;
        while (itr.hasNext()) {
            temp = itr.next();
            System.out.print(temp.getId() + ": ");
            Iterator<Vertex> it = temp.getNeighbors().iterator();
            while (it.hasNext()) {
                System.out.print(it.next().getId());
                if (it.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
     
    }
    /**
     * prim algorimthm for part 3.
     */
    public static void prim() { 
        System.out.println();
        System.out.println("Maximum Bandwith Spanning Tree");
        int num = 8;
        double[] lowcost = new double[num];    
          
        int[] closest = new int[num];   
          
        boolean[] s = new boolean[num];  
        s[0] = true;   
        double min = 10000;
        for (int i = 1; i < num; i++) {
            min = 100000;
            
            if (graph[0][i] == null) {
                lowcost[i] = 0;
            } else {
               
                lowcost[i] = graph[0][i].getWeight();  
            }
            closest[i] = 0;  
            s[i] = false;  
        }  
          
        for (int i = 0; i < num; i++) {  
            min = 0;
            
            int j = 0;  
            for (int k = 1; k < num; k++) {  
                if ((lowcost[k] > min) && (!s[k])) {
                    min = lowcost[k];  
                    j = k;  
                }  
            }  
            if (graph[j][closest[j]] != null) {
                System.out.print("(" + j + "," + closest[j]
                        + "," + graph[j][closest[j]].getWeight() + ")  ");
            }
              
            s[j] = true;
            
            for (int k = 1; k < num; k++) {
               
                
                if ((graph[j][k] != null) && (graph[j][k].getWeight() 
                        > lowcost[k]) && !s[k]) { 
                    lowcost[k] = graph[j][k].getWeight(); 
                    lowcost[k] = graph[k][j].getWeight();
                    closest[k] = j;  
                }  
            }  
        }
        
        System.out.println();
    }  
    /**
     * display the routing table.
     */
    public void routingTable() {
        System.out.println();
        System.out.println("Routing Table");
        this.s = new boolean[vertices.size()];
        this.numHops = new int[vertices.size()];
        this.bandwidth = new int[vertices.size()];
        
        
        for (int i = 0; i < vertices.size(); i++) {
            this.numHops[i] = 0;
            this.bandwidth[i] = 100000;
            this.s[i] = false;
        }
        System.out.print(" ");
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print("  " + i);
        }
        System.out.println();
        for (int j = 0; j < vertices.size(); j++) {
            this.routingTable(j, vertices.get(j));
            System.out.print(j + "  ");
            
            for (int i = 0; i < vertices.size(); i++) {
                Vertex temp = vertices.get(i);
             
                while (temp.returnParent() != null) {
                    if (temp.returnParent().equals(vertices.get(j))) {
                   
                        break;
                    } else {
                        temp = temp.returnParent();
                    }
                }
                if (temp.getId() == j) {
                    System.out.print("-" + "  ");
                } else {
                    System.out.print(temp.getId() + "  ");
                }
            }
            System.out.println();
            for (int i = 0; i < vertices.size(); i++) {
                this.numHops[i] = 0;
                this.bandwidth[i] = 100000;
                this.s[i] = false;
                vertices.get(i).setParent(null);
         
            }
        }
    }

   /**
    * routing table helper. 
    * @param i number i
    * @param v vertex
    */
    public void routingTable(int i, Vertex v) {
            
           
           
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(v);
        this.s[i] = true;
           
        while (q.isEmpty() == false) {
            Vertex temp = q.remove();
            ArrayList<Edge> n = this.adjacentEdges(temp);
              
            Iterator<Edge> itr = n.iterator();
            while (itr.hasNext()) {
                Edge e = itr.next();
                Vertex u = this.adjacentVertex(temp, e);
                int t = vertices.indexOf(u);
                if (this.s[t] == true
                        && this.numHops(u) == this.numHops(temp)+1) {
                    Edge ed = graph[u.returnParent().getId()][u.getId()];
                      
                    if (ed.getWeight() == this.bandwidth[t]
                            && e.getWeight() >= ed.getWeight()) {
                            
                        if (this.bandwidth[temp.getId()] < this.bandwidth[t]) {
                        } else {
                          
                            this.bandwidth[t] = e.getWeight();
                            u.setParent(temp);
                        }
                        
                    }
                }
                if (this.s[t] == false) {
                    this.s[t] = true;
                    u.setParent(temp);
                    this.bandwidth[t] = e.getWeight();
                    q.add(u);  
                         
                }
                   
            }
        }
                
    }
    /**
     * number of hops.
     * @param x vertex
     * @return number of hops
     */
    public int numHops(Vertex x) {
        int i = 0;
        while (x != null) {
            x = x.returnParent();
            i++;
        }
        return i;
    }
    /**
     * cluster (extra credit).
     */
    public void cluster() {
        this.clusters= new ArrayList[vertices.size()];
        this.s = new boolean[vertices.size()];
        int w = 0;
        int t = 0;
        Vertex v = null;
        for (int i = 0; i < vertices.size(); i++) {
            this.clusters[i] = new ArrayList<Vertex>();
            this.s[i] = false;
            if (vertices.get(i).degree() > w) {
                w = vertices.get(i).degree();
                t = i;
            }
        }
        this.clusters[0].add(vertices.get(t));
        c(t, vertices.get(t));
            
    }
           
    /**
     * cluster helper. 
     * @param i i
     * @param v vertex.
     */
    public void c(int i, Vertex v) {
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(v);
        this.s[i] = true;
               
        while (q.isEmpty() == false) {
            Vertex temp = q.remove();
              
            ArrayList<Edge> n = this.adjacentEdges(temp);
            Iterator<Edge> itr = n.iterator();
            while (itr.hasNext()) {
                Edge e = itr.next();
                Vertex u = this.adjacentVertex(temp, e);
                int t = vertices.indexOf(u);
                if (this.s[t] == false) {
                    this.s[t] = true;
                    int d = this.clusters(t);
                    if (d != -2) {
                        this.clusters[d + 1].add(u);
                        this.level++;
                    }
                    q.add(u); 
                }
            }
        }
                       
                
           
    }
    /**
     * cluster print.
     */
    public void clustersPrint() {
        int i = 0;
        System.out.println("Clusters of non-adjacent computers");
        while (this.clusters[i].isEmpty() == false) {
                
            System.out.println();
            System.out.print("cluster " + (i + 1) + ": ");
            Iterator<Vertex> itr = this.clusters[i].iterator();
            while (itr.hasNext()) {
                System.out.print(itr.next().getId());
                if (itr.hasNext()) {
                    System.out.print(", ");
                }
            }
            i++;
        }
            
    }
    /**
     * cluster help method.
     * @param i level.
     * @return reutnr level.
     */
    public int clusters(int i) {
               
        this.currLevel = 0;
        while (this.currLevel <= this.level) {
            Iterator<Vertex> itr = this.clusters[this.currLevel].iterator();
            while (itr.hasNext()) {
                Vertex v = itr.next(); 
                ArrayList<Vertex> neighbors = v.getNeighbors();
                if (neighbors.contains(vertices.get(i))) {
                    if (this.currLevel == this.level) {
                        return this.currLevel;
                           
                    }
                    this.currLevel++;
                    break;
                       
                }
                if (itr.hasNext() == false) {
                    this.clusters[this.currLevel].add(vertices.get(i));
                    return -2;
                }
            }
                   
                
                 
                    
        }
              
        return 0;
    }
}
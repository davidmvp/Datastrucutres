import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * driver.
 * @author davidmvp23
 *
 */
public class driver {

    /**
     * @param args args.
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        SuperGraph g = new SuperGraph();
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter input file name: "); 
        // get the file name fro user.
        String filename = kb.next();
        Scanner filescan = new Scanner(new FileReader(filename));
        while (filescan.hasNext()) {
            String str = filescan.nextLine();
            StringTokenizer token = new StringTokenizer(str);
            String s = token.nextToken();
            Vertex v = new Vertex(s);
            
          
            if (g.contains(v) == false) {
            
                g.addVertex(v);
                v = g.returnVertex(s);
            } else {
                
                v = g.returnVertex(s);
                
            }
            s = token.nextToken();
            
            Vertex q = new Vertex(s);
            
            if (g.contains(q) == false) {
          
                g.addVertex(q);
                q = g.returnVertex(s);
            } else {
               
                q  = g.returnVertex(s);
       
            }
            int i = Integer.parseInt(token.nextToken());
           
            g.addEdge(v, q, i);
          
        }
        g.lookupTable(); //lookup table
        g.adjacent(); // adjacency list.
        SuperGraph.prim(); // maximum bandwidth spanning tree.
        g.routingTable(); //routing table.
        g.cluster(); // extra credit.
        g.clustersPrint();
    }
}



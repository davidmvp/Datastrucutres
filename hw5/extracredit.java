import java.util.StringTokenizer;
public class extracredit {
    public static void main(String[] args) {
  
 
    String str="|| true false";
    StringTokenizer token = new StringTokenizer(str);
    str = token.nextToken();
    TreeRoot<String> tree = new TreeRoot<String>(str);
    while (token.hasMoreTokens()) {

        str = token.nextToken();

        tree.addChild(str);
           
        }
    System.out.println("aa");
   tree.drawGraph();
}
}
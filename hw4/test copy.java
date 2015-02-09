
public class test {
    public static void main(String[] args) {

        OrderedSet<Integer> ib = new OrderedSetList<Integer>();
        OrderedSet<Integer> ib2 = new OrderedSetList<Integer>();
        
        for (int i =0; i<100;i++)
            {ib.add(i);
            
            }
        ib2 = ib.subset(3,99);
        System.out.println(ib.size());
        System.out.println(ib2.size());
        System.out.println(ib);
        System.out.println(ib2);
        ib2.remove(4);
        ib2.remove(6);
        System.out.println(ib);
        System.out.println(ib2);
        System.out.println(ib.size());
        for (int i =0; i<97;i++){
            ib.remove(i);
        }
        System.out.println(ib);
        System.out.println(ib2);
        System.out.println(ib.size());
        System.out.println(ib2.size());
    }
}

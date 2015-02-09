
/** Main program to test your OrderedSetList implementation
 */
public class OrderedSetTest {
    
    public static void main(String[] args) {

        OrderedSet<Integer> ib = new OrderedSetList<Integer>();
        OrderedSet<Integer> ib2 = new OrderedSetList<Integer>();
        OrderedSet<Integer> ib3;

        System.out.println(ib.size() + " should be 0");
        System.out.println(ib.contains(4) + " should be false");
        System.out.println(ib.remove(5) + " should be false");
        System.out.println(ib.isEmpty() + " should be true");
        ib.add(14);  // Java auto-boxing converts int to Integer implicitly
        ib.add(20);
        ib.add(6);
        ib.add(11);
        System.out.println(ib + " should be {6, 11, 14, 20}");
        System.out.println(ib.remove(6) + " should be true");
        System.out.println(ib + " after remove should be {11, 14, 20}");
        System.out.println(ib.remove(14) + "should be true");
        System.out.println(ib.isEmpty() + " should be false");
        ib.add(5);
        System.out.println(ib.size() + " size should be 3");
        System.out.println(ib.contains(5) + " should be true");
        System.out.println(ib.contains(1) + " should be false");
        System.out.println(ib + " should be {5, 11, 20}");
        System.out.println(ib.remove(5) + " should be true");
        ib.add(20);
        ib.add(8);
        ib.add(100);
        ib.add(14);
        ib.add(33);
        ib.add(5);
        System.out.println(ib + " should be {5, 8, 11, 14, 20, 33, 100}");
        System.out.println(ib2.size() + " size should be 0");
        System.out.println(ib2.union(ib) + " should be {5, 8, 11, 14, 20, 33, 100}");
        System.out.println(ib2.intersect(ib) + " should be {}");
        ib2.add(14);
        ib2.add(25);
        ib2.add(5);
        ib2.add(6);
        System.out.println("ib is " + ib + " should be {5, 8, 11, 14, 20, 33, 100}");
        System.out.println("ib2 is " + ib2 + " should be {5, 6, 14, 25}");
        System.out.println(ib2.size() + " size should be 4");
        System.out.println("union is " + ib2.union(ib) + " should be {5, 6, 8, 11, 14, 20, 25, 33, 100}");
        System.out.println("union is " + ib.union(ib2) + " should be {5, 6, 8, 11, 14, 20, 25, 33, 100}");
        System.out.println(ib2.intersect(ib) + " should be {5, 14}");

        System.out.println(ib.subset(10, 100) + " should be {11, 14, 20, 33}");
        System.out.println(ib2.subset(0,3) + " should be {}");
        ib3 = ib.subset(8, 15);
        System.out.println("ib.subset(8,15) " + ib3 + " should be {8, 11, 14}");
        System.out.println(ib3.remove(11) + " should be true");
        System.out.println("ib is " + ib + " should be {5, 8, 14, 20, 33, 100}");
    }

}
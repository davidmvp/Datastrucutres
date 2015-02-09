public class test {
    
    public static void main(String[] args) {

        OrderedSet<Integer> ib = new OrderedSetList<Integer>();
       // OrderedSet<Integer> ib2 = (OrderedSet<Integer>) new OrderedSetList<Integer>();
        //OrderedSet<Integer> ib3;
       
ib.add(7);
ib.add(98);
ib.add(97);

for (int i = 0;i<60;i++){
ib.add(i);}

    System.out.println(ib.subset(4, 6));
    
    
}
}

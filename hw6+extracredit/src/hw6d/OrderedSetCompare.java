import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;




/**
 * This OrderedSet compare class provides mechanisms for comparing actual 
 * running times of different implementations of an OrderedSet interface. 
 * @author davidmvp23
 *
 * @param <T>
 */
public class OrderedSetCompare<T extends Comparable<? super T>>  {
    /** Shared random variable. */
    private static Random randy = new Random();
    
    /** Collection of OrderedSets. */
    private ArrayList<OrderedSet<T>> sets;

    /** How many implementations we have. */
    private int numSets = 0;

    /** Collection of Objects to test with. */
    private ArrayList<T> values; 

    /** array of indices to use for look-up in values. */
    private int[] indices;


    /** Create array of up to 5 OrderedSets & initialize collections.
     */
    public OrderedSetCompare() {
        final int k = 20;
        this.sets = new ArrayList<OrderedSet<T>>(k);
        this.numSets = 0;
        this.values = new ArrayList<T>();
    }
    /**
     * Sort the list using java API.
     */
    public void sortList() {
        Collections.sort(this.values);
    }
    /** Add an implementation to the ordered sets.
        @param os the OrderedSet to include
        @return true if added, false if sets is full
     */
    public boolean add(OrderedSet<T> os) {
        final int i = 20;
        if (this.sets.size() < i) {
            this.sets.add(os);
            this.numSets++;
            
            return true;
        }
        return false;
    }

    /** Read integers from a plain text file, one token at a time,
        store in values.
        @param filename the external filename
     * @throws IOException 
    */
  
    public void readIntegers(String filename) throws IOException {
        
        BufferedReader br = new BufferedReader(
                new FileReader("RandomIntegers.txt"));
        String str = br.readLine();
        
        while (str != null) {
        
            StringTokenizer token = new StringTokenizer(str);
            while (token.hasMoreTokens()) {
                String s = token.nextToken();
                this.values.add((T) s);
            }
            str = br.readLine();
        }
        br.close();
    }

    /** Run tests and record times with the current data set on all the
        implementations, then sort the data set and repeat all the
        tests.
    */
    public void runTests() {
        int i, size = this.values.size();
        final int s = 10;
        
        this.indices = new int[size / s];
        
        for (i = 0; i < this.indices.length; i++) {
            this.indices[i] = randy.nextInt(size);
        }

        for (i = 0; i < this.numSets; i++) {  // one implementation at a time
            System.out.println("This is test run for set No." + (i + 1));
            System.out.println("Run time for insert functoin: " 
                    + runInserts(this.sets.get(i)));  
            System.out.println("Run time for find functoin:   " 
                    + runFinds(this.sets.get(i), this.indices));  
            // all succeed
            System.out.println("Run time for delete functoin: " 
                    + runDeletes(this.sets.get(i), this.indices));  
            // some succeed, fail if duplicates
            System.out.println("Run time for find functoin:   " 
                    + runFinds(this.sets.get(i), this.indices));  
            // all fail because they were just deleted
        }
        this.sortList();
        System.out.println("Now we sort the list, and do the testing again.");
        for (i = 0; i < this.numSets; i++) {  // one implementation at a time
            System.out.println("This is test run for set No." + (i + 1));
            System.out.println("Run time for insert functoin: " 
                    + runInserts(this.sets.get(i)));  
            System.out.println("Run time for find functoin:   " 
                    + runFinds(this.sets.get(i), this.indices));  
            // all succeed
            System.out.println("Run time for delete functoin: " 
                    + runDeletes(this.sets.get(i), this.indices));  
            // some succeed, fail if duplicates
            System.out.println("Run time for find functoin:   " 
                    + runFinds(this.sets.get(i), this.indices));  
            // all fail because they were just deleted
        }

        
    }

    /** Insert values into an Ordered List, timing the process.
        @param os the OrderedSet instance to use
        @return the number of elapsed milliseconds
    */
    public long runInserts(OrderedSet<T> os) {
        
        long start, end;
        int i, size = this.values.size();
        start = System.currentTimeMillis();
        for (i = 0; i < size; i++) {
            os.add(this.values.get(i));
        }
        end = System.currentTimeMillis();
       
        return end - start;
    }

    /** Find values in an Ordered List, timing the process.
        @param os the OrderedSet instance to use
        @param indice the indices of the values to find
        @return the number of elapsed milliseconds
    */
    public long runFinds(OrderedSet<T> os, int[] indice) {
        long start, end;
        int i, size = this.values.size();
        start = System.currentTimeMillis();
        for (i = 0; i < size; i++) {
            os.contains(this.values.get(i));
        }
        end = System.currentTimeMillis();
        
        return end - start;
        
    }

    /** Delete values from an Ordered List, timing the process.
        @param os the OrderedSet instance to use
        @param indice the indices of the values to delete
        @return the number of elapsed milliseconds
    */
    public long runDeletes(OrderedSet<T> os, int[] indice) {
        long start, end;
        int i, size = this.values.size();
        start = System.currentTimeMillis();
        for (i = 0; i < size; i++) {
            os.remove(this.values.get(i));
        }
        end = System.currentTimeMillis();
        
        return end - start;
    }
    /**
     * Make 110000 random integers ranges from 1 to 10000. 
     * I have a smaller range so that the whole testing takes less time.
     * @throws IOException check if the file exists.
     */
    public void makeRandomVariables() throws IOException {
        final int s = 10000;
        final int q = 110000;
        File file = new File("RandomIntegers.txt");
        Writer writer =   new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < q; i++) {
            writer.write(randy.nextInt(s) + " ");
        }
        writer.close();
        
    }

    /** Set up OrderedSet implementations to test, get input filenames,
        run tests on everything, create timing report.
     ** @param args the driver.   
     * @throws IOException 
    */
    public static void main(String[] args) throws IOException {

        String filename;
        Scanner kb = new Scanner(System.in);

        OrderedSetCompare<Integer> osc = new OrderedSetCompare<Integer>();
        osc.add(new OrderedSetList<Integer>());
        osc.add(new Bst<Integer>());
        osc.add(new Avl<Integer>());
        OrderedSetCompare<String> osc1 = new OrderedSetCompare<String>();
        osc1.add(new OrderedSetList<String>());
        osc1.add(new Bst<String>());
        osc1.add(new Avl<String>());
        //osc.makeRandomVariables();
     
        System.out.println("Enter the name of integer text " 
                + "file you want to choose from");
        filename = kb.nextLine();
        osc.readIntegers(filename);
        osc.runTests();
        System.out.println("Enter the name of String text" 
                + "file you want to choose from");
        filename = kb.nextLine();
        osc1.readIntegers(filename);
        
        
        osc1.runTests();
      
       

    }

}
import java.util.Scanner;
import java.io.*;

public class AllocationDriver {

    public static void main(String[] args) throws IOException {
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        String filename = kb.next();
        Scanner filescan = new Scanner(new FileReader(filename));
        int size = filescan.nextInt();
        filescan.nextLine();
        
        PrintWriter logger = new PrintWriter(new FileWriter("translog.txt"));
        closeFit bf = new closeFit(size);//new BestFit(size);
        WorstFit wf = new WorstFit(size);
        closeFit cf = new closeFit(size);//new CloseFit(size);
        String line;
        Scanner linescan;
        int temp;
        
        System.out.println("Memory Transaction Log");
        System.out.println("Memory Size: " + size);
        System.out.println("Memory Address begins at location 0\n");
        System.out.println("Log Format: A/D | ID# | (DF?) | Success/Fail | Address | Size");
        for (int i = 0; i < 112; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%14s%-37s%-35s%s", " ", "Best Fit", "Worst Fit", "Close Fit\n");
        for (int i = 0; i < 112; i++) {
            System.out.print("-");
        }
        System.out.println();
      
        while (filescan.hasNext()) {
            line = filescan.nextLine();
            //System.out.println(line);
            linescan = new Scanner(line);
            linescan.next();
            if (line.charAt(0) == 'A' || line.charAt(0) == 'a') {
                temp = Integer.parseInt(linescan.next());
                System.out.print(logAllocate(bf, temp));
                System.out.print(logAllocate(wf, temp));
                System.out.print(logAllocate(cf, temp));
                System.out.println();
                //System.out.println(wf.toStringAll() + "\n");
            } else {
                temp = Integer.parseInt(linescan.next());
                System.out.print(logDeallocate(bf, temp));
                System.out.print(logDeallocate(wf, temp));
                System.out.print(logDeallocate(cf, temp));
                System.out.println();
                //System.out.println(wf.toStringAll() + "\n");
            }
        }
        filescan.close();       
               
        analysis(size, filename, bf, wf, cf);
    }
     
    public static String logAllocate(Allocator xf, int size) {
        int id = xf.getLastID();
        boolean success = xf.allocate(size);
        int addr = xf.getLastAddr();
        if (success && !xf.lastDefrag()) {
            return String.format("|%-3s%-4d%-3s%-10s%-7d%7d|", 
                "A", id, " ", "SUCCESS",
                addr, size);
        } else if (success && xf.lastDefrag()) {
            return String.format("|%-3s%-4d%-3s%-10s%-7d%7d|", 
                    "A", id, "DF", "SUCCESS",
                    addr, size);
        } else {
            return String.format("|%-3s%-4d%-3s%-10s%-7d%7d|", 
                    "A", id, "DF", "FAILED", 0, size);
        }
    }
    
    public static String logDeallocate(Allocator xf, int id) {
        boolean success = xf.deallocate(id);
        int addr = xf.getLastAddr(), size = xf.getLastSize();
        if (success) {
            return String.format("|%-3s%-4d%-3s%-10s%-7d%7d|", 
                "D", id, " ", "SUCCESS",
                addr, size);
        } else {
            return String.format("|%-3s%-4d%-3s%-10s%-7d%7d|", 
                    "D", id, " ", "FAILED", 0, 0);
        }
    }
        
    
    public static void analysis(int size, String filename, 
            Allocator bf, Allocator wf, Allocator cf) throws IOException {
        PrintWriter output = new PrintWriter(new FileWriter("analysis.txt"));
        System.out.println("Performance Analysis Chart");
        System.out.println("Memory Size: " + size);
        System.out.println("Input File Used: " + "in.txt\n");
        for (int i = 0; i < 71; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%-35s%12s%12s%12s\n", "Statistic:",
                "Best Fit", "Worst Fit", "Close Fit");
        for (int i = 0; i < 71; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%-35s%12d%12d%12d\n", "Number of Defragmentations:", 
                bf.getDefrags(), wf.getDefrags(), cf.getDefrags());
        System.out.printf("%-35s%12d%12d%12d\n", "# of failed allocation requests:",
                bf.getFailedAllocations(), 
                wf.getFailedAllocations(), cf.getFailedAllocations());
        System.out.printf("%-35s%12d%12d%12d\n", "Average size failed allocs:",
                bf.getAvgFailedSize(),
                wf.getAvgFailedSize(), cf.getAvgFailedSize());
        System.out.printf("%-35s%12.2f%12.2f%12.2f\n",
                "Average time to process alloc:", 
                bf.getAvgAllocationTime(), 
                wf.getAvgAllocationTime(), cf.getAvgAllocationTime());
        System.out.printf("%-35s%12.2f%12.2f%12.2f\n", 
                "Average time/size [quicksort]:",
                bf.getAvgQuickSortTime(), 
                wf.getAvgQuickSortTime(), cf.getAvgQuickSortTime());
        System.out.printf("%-35s%12.2f%12.2f%12.2f\n", 
                "Average time/size [bucketsort]:", 
                bf.getAvgBucketSortTime(),
                wf.getAvgBucketSortTime(), cf.getAvgBucketSortTime());
        
        output.close();
    }

}
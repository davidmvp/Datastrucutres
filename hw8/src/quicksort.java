public class QuickSort
13: {
14:    public QuickSort(int[] anArray)
15:    {
16:       a = anArray;
17:    }
18: 
19:    /**
20:       Sorts the array managed by this sorter
21:    */
22:    public void sort()
23:    {
24:       sort(0, a.length - 1);
25:    }
26: 
27:    public void sort(int low, int high)
28:    {
29:       if (low >= high) return;
30:       int p = partition(low, high);
31:       sort(low, p);
32:       sort(p + 1, high);
33:    }
34: 
35:    private int partition(int low, int high)
36:    {
37:       // First element
38:       int pivot = a[low];
39: 
40:       // Middle element
41:       //int middle = (low + high) / 2;
42:       //int pivot = a[middle];
43: 
44:       int i = low - 1;
45:       int j = high + 1;
46:       while (i < j)
47:       {
48:          i++; while (a[i] < pivot) i++;
49:          j--; while (a[j] > pivot) j--;
50:          if (i < j) swap(i, j);
51:       }
52:       return j;
53:    }
54: 
55:    /**
56:       Swaps two entries of the array.
57:       @param i the first position to swap
58:       @param j the second position to swap
59:    */
60:    private void swap(int i, int j)
61:    {
62:       int temp = a[i];
63:       a[i] = a[j];
64:       a[j] = temp;
65:    }
66: 
67:    private int[] a;
68: }

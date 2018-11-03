/** Sample starter code for SP9.
 *  @author
 */

package usg170030;
import java.util.Arrays;
import java.util.Random;

public class SP9 {
    public static Random random = new Random();
    public static int numTrials = 100;
    public static void main(String[] args) {
        int n = 10;  int choice = 2;// + random.nextInt(4);
        if(args.length > 0) { n = Integer.parseInt(args[0]); }
        if(args.length > 1) { choice = Integer.parseInt(args[1]); }
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = i;
        }
        Timer timer = new Timer();

        choice = 4;

        switch(choice) {
            case 1:
                Shuffle.shuffle(arr);
                numTrials = 1;
                insertionSort(arr);
                System.out.println(Arrays.toString(arr));
                break;
            case 2:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort1(arr);
                }
                System.out.println(Arrays.toString(arr));
                break;  // etc
            case 3:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort2(arr);
                }
                System.out.println(Arrays.toString(arr));
                break;  // etc
            case 4:
                for(int i=0; i<numTrials; i++) {
                    Shuffle.shuffle(arr);
                    mergeSort3(arr);
                }
                System.out.println(Arrays.toString(arr));
                break;  // etc

        }
        timer.end();
        timer.scale(numTrials);

        System.out.println("Choice: " + choice + "\n" + timer);
    }

    public static void insertionSort(int[] arr, int p, int r)
    {
        for (int i=p+1; i<=r; ++i) {
            int key = arr[i];
            int j = i-1;

            while (j>=p && arr[j] > key) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }
    public static void insertionSort(int[] arr) {
        for (int i=1; i<arr.length; ++i) {
            int key = arr[i];
            int j = i-1;

            while (j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }

    public static void mergeSort1(int[] arr) {
        mergeSort1(arr, 0, arr.length - 1);
    }

    public static void mergeSort1(int[] arr, int p, int r) {
        if (p < r) {
            int mid = (p+r)/2;

            // Sort first and second halves
            mergeSort1(arr, p, mid);
            mergeSort1(arr , mid+1, r);

            // Merge the sorted halves
            merge(arr, p, mid, r);
        }
    }

    public static void merge(int arr[], int p, int mid, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = mid - p + 1;
        int n2 = r - mid;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        System.arraycopy(arr,p,L,0  ,n1);
        System.arraycopy(arr,mid+1,R,0,n2);

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        for (int k = p; k <= r; k++) {
            if (j >= R.length || (i < L.length && L[i] <= R[j])) {
                arr[k] = L[i++];
            }
            else {
                arr[k] = R[j++];
            }
        }
    }


    public static void mergeSort2(int[] A) {
        mergeSort2(A, 0, A.length - 1);
    }

    public static void mergeSort2(int[] A, int p, int r) {
        int threshold = 4;
        if (r - p + 1 < threshold)
        {
            insertionSort(A);
        }
        else
        {
            int mid = (p+r)/2;

            // Sort first and second halves
            mergeSort1(A, p, mid);
            mergeSort1(A , mid+1, r);

            int[] B = new int[A.length];
            // Merge the sorted halves
            merge(A, B, p, mid, r);
        }
    }

    public static void merge(int A[], int[] B, int p, int mid, int r)
    {
        System.arraycopy(A,p,B,p,r-p+1);
        //System.out.println(Arrays.toString(B));

        int i = p;
        int j = mid + 1;

        // Initial index of merged subarry array
        int k = p;
        while (k <= r)
        {
            if (j >= A.length || (i <= mid && B[i] <= B[j]))
            {
                A[k] = B[i++];
            }
            else {
                A[k] = B[j++];
            }
            k++;
        }
    }

    public static void mergeSort3(int[] A) {
        int[] B = new int[A.length];
        System.arraycopy(A,0,B,0,A.length);
        mergeSort3(A,B,0,A.length);
    }

    public static void mergeSort3(int[] A, int[] B, int left, int n) {
        int threshold = 4;
        if (n < threshold)
        {
            insertionSort(A, left, left+n-1);
        }
        else
        {
            mergeSort3(B,A,left,n/2);
            mergeSort3(B,A,left+n/2,n-n/2);
            merge3(A,B,left,left+n/2-1,left+n-1);
        }
    }

    public static void merge3(int A[], int[] B, int p, int mid, int r)
    {
        int i = p, j = mid + 1, k = p ;
        while (i<=mid && j<=r)
        {
            if (B[i]<=B[j])
            {
                A[k++] = B[i++];
            }
            else
            {
                A[k++] = B[j++];
            }
        }

        while (i<=mid)
        {
            A[k++] = B[i++];
        }
        while(j<=r)
        {
            A[k++] = B[j++];
        }
    }


    /** Timer class for roughly calculating running time of programs
     *  @author rbk
     *  Usage:  Timer timer = new Timer();
     *          timer.start();
     *          timer.end();
     *          System.out.println(timer);  // output statistics
     */

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;
        boolean ready;

        public Timer() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public void start() {
            startTime = System.currentTimeMillis();
            ready = false;
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            ready = true;
            return this;
        }

        public long duration() { if(!ready) { end(); }  return elapsedTime; }

        public long memory()   { if(!ready) { end(); }  return memUsed; }

        public void scale(int num) { elapsedTime /= num; }

        public String toString() {
            if(!ready) { end(); }
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }

    /** @author rbk : based on algorithm described in a book
     */


    /* Shuffle the elements of an array arr[from..to] randomly */
    public static class Shuffle {

        public static void shuffle(int[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static<T> void shuffle(T[] arr) {
            shuffle(arr, 0, arr.length-1);
        }

        public static void shuffle(int[] arr, int from, int to) {
            int n = to - from  + 1;
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        public static<T> void shuffle(T[] arr, int from, int to) {
            int n = to - from  + 1;
            Random random = new Random();
            for(int i=1; i<n; i++) {
                int j = random.nextInt(i);
                swap(arr, i+from, j+from);
            }
        }

        static void swap(int[] arr, int x, int y) {
            int tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        static<T> void swap(T[] arr, int x, int y) {
            T tmp = arr[x];
            arr[x] = arr[y];
            arr[y] = tmp;
        }

        public static<T> void printArray(T[] arr, String message) {
            printArray(arr, 0, arr.length-1, message);
        }

        public static<T> void printArray(T[] arr, int from, int to, String message) {
            System.out.print(message);
            for(int i=from; i<=to; i++) {
                System.out.print(" " + arr[i]);
            }
            System.out.println();
        }
    }
}

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Task2 {

    Integer[] sortedList;
    Long sortingTime;
    Long basicSearchTime;
    Long binSearchTime;

    public Task2(Integer[] sortedList, Long sortingTime, Long basicSearchTime, Long binSearchTime) {
        this.sortedList = sortedList;
        this.sortingTime = sortingTime;
        this.basicSearchTime = basicSearchTime;
        this.binSearchTime = binSearchTime;
    }

    @Override
    public String toString() {
        return "Task2{" +
                "sortedList=" + sortedList +
                ", sortingTime=" + sortingTime +
                ", basicSearchTime=" + basicSearchTime +
                ", binSearchTime=" + binSearchTime +
                '}';
    }
}

public class Main {

    public static void quickSort(Integer[] arr, int start, int end) {

        int partition = partition(arr, start, end);

        if (partition - 1 > start) {
            quickSort(arr, start, partition - 1);
        }
        if (partition + 1 < end) {
            quickSort(arr, partition + 1, end);
        }
    }

    public static int partition(Integer[] arr, int start, int end) {
        int pivot = arr[end];

        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                int temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
                start++;
            }
        }
        int temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;

        return start;
    }

    public static Integer[] generateRandomArray(int size, int min, int max) throws Exception {
        if (max >= size) {
            Random rand = new Random();
            final ArrayList<Integer> nums = new ArrayList<>() {{
                for (int i = 1; i <= size; i++) {
                    var r = rand.nextInt(min, max);
                    while (this.contains(r)) {
                        r = rand.nextInt(min, max);
                    }
                    add(r);
                }
            }};
            return nums.toArray(new Integer[0]);
        } else {
            throw new Exception("WTF");
        }
    }

    public static Integer[] copyTableAndSort(Integer[] arr) {
        Integer[] copy = Arrays.copyOf(arr,arr.length);
        quickSort(copy,0,copy.length-1);
        return copy;
    }

    public static int basicSearch(Integer[] arr,int key){
        for(int i = 0; i<arr.length-1;i++){
            if(arr[i] == key) return i;
        }
        return -1;
    }

    public static long basicSearchTime(Integer[] A,Integer[] B){
        Instant start = Instant.now();
        for(int i =0; i<A.length-1;i++){
            basicSearch(A,B[i]);
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public static int binSearch(
            Integer[] sortedArray, int key, int low, int high) {
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low  + ((high - low) / 2);
            if (sortedArray[mid] < key) {
                low = mid + 1;
            } else if (sortedArray[mid] > key) {
                high = mid - 1;
            } else if (sortedArray[mid] == key) {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static long binarySearchTime(Integer[] A,Integer[] B){
        Instant start = Instant.now();
        for(int i = 0; i<A.length-1;i++){
            binSearch(B,A[i],0,A.length-1);
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public static Task2 getSecondTask(Integer[] array) {
        Instant start = Instant.now();
        var sorted = copyTableAndSort(array);
        Instant end = Instant.now();
        var time = Duration.between(start, end).toMillis();
        return new Task2(sorted,time,basicSearchTime(array,sorted),binarySearchTime(array,sorted));
    }

    public static void main(String[] args) throws Exception {
        var list = generateRandomArray(60000, 1, 10000000);
        var task2 = getSecondTask(list);
        System.out.println(task2.toString());

    }
}

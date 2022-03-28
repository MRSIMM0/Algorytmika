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

class Task3i4 {
    long time;
    int height;
    long searchTime;

    public Task3i4(long time, int height, long searchTime) {
        this.time = time;
        this.height = height;
        this.searchTime = searchTime;
    }

    @Override
    public String toString() {
        return "Task3{" +
                "time=" + time +
                ", height=" + height +
                ", searchTime=" + searchTime +
                '}';
    }
}

public class Main {
    static ArrayList<Integer> midTable = new ArrayList<>();

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
        Integer[] copy = Arrays.copyOf(arr, arr.length);
        quickSort(copy, 0, copy.length - 1);
        return copy;
    }

    public static int basicSearch(Integer[] arr, int key) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == key) return i;
        }
        return -1;
    }

    public static long basicSearchTime(Integer[] A, Integer[] B) {
        Instant start = Instant.now();
        for (int i = 0; i < A.length - 1; i++) {
            basicSearch(A, B[i]);
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public static int binSearch(Integer[] sortedArray, int key, int low, int high) {
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = low + ((high - low) / 2);
            midTable.add(mid);
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

    public static long binarySearchTime(Integer[] A, Integer[] B) {
        Instant start = Instant.now();
        for (int i = 0; i < A.length - 1; i++) {
            binSearch(B, A[i], 0, A.length - 1);
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public static Task2 getSecondTask(Integer[] array) {
        Instant start = Instant.now();
        var sorted = copyTableAndSort(array);
        Instant end = Instant.now();
        var time = Duration.between(start, end).toMillis();
        return new Task2(sorted, time, basicSearchTime(array, sorted), binarySearchTime(array, sorted));
    }


    static class Node {
        int data;
        Node left = null;
        Node right = null;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data > node.data) {
            node.right = insert(node.right, data);
        }
        if (data < node.data) {
            node.left = insert(node.left, data);
        }
        return node;
    }

    public static Node buildBST(Integer[] arr) {
        Node root = new Node(arr[0]);
        for (int i = 0; i < arr.length - 1; i++) {
            root = insert(root, arr[i]);
        }
        return root;
    }

    public static int bstHeight(Node bst) {
        if (bst == null) return 0;
        else return 1 + Math.max(bstHeight(bst.left), bstHeight(bst.right));
    }

    public static Node search(Node root, int key) {
        if (root == null || root.data == key)
            return root;
        if (root.data < key)
            return search(root.right, key);
        return search(root.left, key);
    }

    public static long searchTime(Node bst, Integer[] A) {
        Instant start = Instant.now();
        for (int i = 0; i < A.length - 1; i++) {
            search(bst, A[i]);
        }
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public static Task3i4 getThirdTask(Integer[] A) {
        Instant start = Instant.now();
        Node node = buildBST(A);
        Instant end = Instant.now();
        var timeC = Duration.between(start, end).toMillis();

        var height = bstHeight(node);

        var searchTime = searchTime(node, A);

        return new Task3i4(timeC, height, searchTime);
    }
    public static long getSearchTime(Integer[] A,Node B){
        Instant start = Instant.now();
        for(int i = 0; i < A.length; i++){
            search(B,A[i]);
        }
        Instant end = Instant.now();
        return Duration.between(start,end).toMillis();
    }

    //    TODO
    public static Task3i4 getForthTask(Integer[] A) {
        var B = midTable.toArray(new Integer[0]);
        System.out.println(B.length);
        Instant start = Instant.now();
        var optimased = buildBST(B);
        Instant end = Instant.now();
        var time = Duration.between(start,end).toMillis();
        var height = bstHeight(optimased);

        return new Task3i4(time,height,getSearchTime(A,optimased));
    }

    public static void main(String[] args) throws Exception {
        var list = generateRandomArray(20000, 1, 10000000);
        var task2 = getSecondTask(list);
        var sortedList = task2.sortedList;

        System.out.println(task2.toString());

        var task3 = getThirdTask(list);
        System.out.println(task3.toString());

        var task4 =  getForthTask(list);
        System.out.println(task4.toString());
    }
}

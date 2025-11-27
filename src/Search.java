import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Search {

    // Primera ocurrencia array
    public static int indexOfFirst(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key)
                return i;
        }
        return -1;
    }

    // Primera ocurrencia sll
    public static Node findFirst(Node head, int key) {
        Node cur = head;
        while (cur != null) {
            if (cur.value == key)
                return cur;
            cur = cur.next;
        }
        return null;
    }

    // Ultima ocurrencia array
    public static int indexOfLast(int[] a, int key) {
        int last = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) last = i;
        }
        return last;
    }

    // Ultima ocurrencia ssl
    public static Node findLast(Node head, int key) {
        Node cur = head;
        Node last = null;
        while (cur != null) {
            if (cur.value == key)
                last = cur;
            cur = cur.next;
        }
        return last;
    }

    // Find all array
    public static List<Integer> findAll(int[] a, IntPredicate p) {
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (p.test(a[i])) r.add(i);
        }
        return r;
    }

    // Find alll ssl
    public static List<Node> findAll(Node head, Predicate<Node> p) {
        List<Node> r = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            if (p.test(cur)) r.add(cur);
            cur = cur.next;
        }
        return r;
    }

    // Centinela
    public static int indexOfSentinel(int[] a, int key) {
        int n = a.length;
        int last = a[n - 1];
        a[n - 1] = key;

        int i = 0;
        while (a[i] != key) i++;

        a[n - 1] = last;

        if (i < n - 1 || last == key)
            return i;
        return -1;
    }

    // Busqueda binaria
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
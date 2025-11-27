package ed.u2.search;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class SearchAlgorithms {

    // =============================
    // 1. Primera ocurrencia (ARRAY)
    // =============================
    public static int indexOfFirst(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) return i;
        }
        return -1;
    }

    // =============================
    // 1. Primera ocurrencia (SLL)
    // =============================
    public static Node findFirst(Node head, int key) {
        Node current = head;
        while (current != null) {
            if (current.value == key) return current;
            current = current.next;
        }
        return null;
    }

    // =============================
    // 2. Última ocurrencia (ARRAY)
    // =============================
    public static int indexOfLast(int[] a, int key) {
        int last = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) last = i;
        }
        return last;
    }

    // =============================
    // 2. Última ocurrencia (SLL)
    // =============================
    public static Node findLast(Node head, int key) {
        Node current = head;
        Node last = null;
        while (current != null) {
            if (current.value == key) last = current;
            current = current.next;
        }
        return last;
    }

    // =============================
    // 3. findAll (ARRAY)
    // =============================
    public static List<Integer> findAll(int[] a, IntPredicate pred) {
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (pred.test(a[i])) out.add(i);
        }
        return out;
    }

    // =============================
    // 3. findAll (SLL)
    // =============================
    public static List<Node> findAll(Node head, Predicate<Node> pred) {
        List<Node> out = new ArrayList<>();
        Node current = head;
        while (current != null) {
            if (pred.test(current)) out.add(current);
            current = current.next;
        }
        return out;
    }

    // =============================
    // 4. Secuencial con centinela (ARRAY)
    // =============================
    public static int sentinelSearch(int[] a, int key) {
        int last = a[a.length - 1];
        a[a.length - 1] = key;

        int i = 0;
        while (a[i] != key) i++;

        a[a.length - 1] = last;

        if (i < a.length - 1 || last == key)
            return i;

        return -1;
    }

    // =============================
    // 5. Búsqueda binaria (ARRAY)
    // =============================
    public static int binarySearch(int[] a, int key) {
        int low = 0, high = a.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (a[mid] == key) return mid;
            if (a[mid] < key) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }
}

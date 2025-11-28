package ed.u2.search;

import java.util.*;
import java.util.function.Predicate;

/**
 * Clase SearchAlgorithms:
 * Contiene implementaciones de diversos algoritmos de búsqueda,
 * tanto para arreglos como para listas enlazadas simples (SLL).
 *
 * Este archivo centraliza TODA la lógica de búsqueda del taller.
 */
public class SearchAlgorithms {

    // ------------------------------------------------------------
    //  BÚSQUEDAS EN ARREGLOS (ARRAYS)
    // ------------------------------------------------------------

    /**
     * Busca la primera posición donde aparece el valor "key" en el arreglo.
     *
     * @param arr Arreglo donde buscar
     * @param key Valor a buscar
     * @return índice de la primera ocurrencia, o -1 si no existe
     */
    public static int indexOfFirst(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca la última posición donde aparece el valor "key" en el arreglo.
     *
     * @param arr Arreglo donde buscar
     * @param key Valor a buscar
     * @return índice de la última ocurrencia, o -1 si no existe
     */
    public static int indexOfLast(int[] arr, int key) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * findAll:
     * Retorna TODOS los índices cuyo valor cumple un predicado.
     *
     * Ejemplos:
     *  - buscar pares     → x -> x % 2 == 0
     *  - buscar valores >3 → x -> x > 3
     *
     * @param arr Arreglo donde buscar
     * @param pred Predicado lógico aplicado a cada elemento
     * @return Lista con todos los índices encontrados
     */
    public static List<Integer> findAll(int[] arr, Predicate<Integer> pred) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (pred.test(arr[i])) {
                result.add(i);
            }
        }

        return result;
    }

    /**
     * Búsqueda lineal con centinela.
     * Se añade temporalmente el valor buscado al final del arreglo
     * para evitar comprobaciones internas.
     *
     * @param arr Arreglo donde buscar
     * @param key Valor a buscar
     * @return índice donde aparece key o -1 si no existe
     */
    public static int sentinelSearch(int[] arr, int key) {
        int n = arr.length;
        int last = arr[n - 1];

        arr[n - 1] = key;

        int i = 0;
        while (arr[i] != key) {
            i++;
        }

        arr[n - 1] = last;

        if (i < n - 1 || last == key) {
            return i;
        }

        return -1;
    }

    /**
     * Implementación clásica de Binary Search.
     * El arreglo debe estar ORDENADO.
     *
     * @param arr Arreglo ordenado
     * @param key Valor a buscar
     * @return índice donde aparece key, o -1 si no existe
     */
    public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // ------------------------------------------------------------
    //  BÚSQUEDAS EN SLL (LISTA ENLAZADA SIMPLE)
    // ------------------------------------------------------------

    /**
     * Retorna el primer nodo que contiene el valor buscado.
     *
     * @param head inicio de la SLL
     * @param key valor buscado
     * @return nodo donde aparece key, o null si no existe
     */
    public static Node findFirst(Node head, int key) {
        Node current = head;
        while (current != null) {
            if (current.value == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Retorna el último nodo que contiene el valor buscado.
     *
     * @param head inicio de la SLL
     * @param key valor buscado
     * @return nodo donde aparece key por última vez, o null si no existe
     */
    public static Node findLast(Node head, int key) {
        Node current = head;
        Node lastFound = null;

        while (current != null) {
            if (current.value == key) {
                lastFound = current;
            }
            current = current.next;
        }

        return lastFound;
    }

    /**
     * Retorna TODOS los nodos que cumplen un predicado (valor <3, pares, etc.).
     *
     * @param head inicio de la SLL
     * @param pred condición lógica
     * @return lista de nodos encontrados
     */
    public static List<Node> findAll(Node head, Predicate<Node> pred) {
        List<Node> result = new ArrayList<>();

        Node current = head;
        while (current != null) {
            if (pred.test(current)) {
                result.add(current);
            }
            current = current.next;
        }

        return result;
    }
}

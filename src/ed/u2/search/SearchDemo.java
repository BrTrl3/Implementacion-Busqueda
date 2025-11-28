package ed.u2.search;

import java.util.*;

public class SearchDemo {

    public static void main(String[] args) {

        // -----------------------
        // DATASETS DEL TALLER
        // -----------------------
        int[] A = {8, 3, 6, 3, 9};
        int[] B = {5, 4, 3, 2, 1};
        int[] C = {1, 2, 3, 4, 5};
        int[] D = {2, 2, 2, 2};
        int[] E = {9, 1, 8, 2};

        // -------------------------------
        // PRIMERA Y ÚLTIMA OCURRENCIA EN A
        // -------------------------------
        System.out.println("Arreglo A: " + Arrays.toString(A));
        System.out.println("Primera ocurrencia en A de 3 → " +
                SearchAlgorithms.indexOfFirst(A, 3));
        System.out.println("Última ocurrencia en A de 3 → " +
                SearchAlgorithms.indexOfLast(A, 3));
        System.out.println();

        // -------------------------------
        // FINDALL EN A (pares)
        // -------------------------------
        System.out.println("findAll pares en A");
        System.out.println("Arreglo usado: " + Arrays.toString(A));
        System.out.println("Índices encontrados → " +
                SearchAlgorithms.findAll(A, x -> x % 2 == 0));
        System.out.println();

        // -------------------------------
        // CENTINELA EN B
        // -------------------------------
        System.out.println("Arreglo B (inverso): " + Arrays.toString(B));
        System.out.println("Centinela búsqueda 3 en B → " +
                SearchAlgorithms.sentinelSearch(B, 3));
        System.out.println();

        // -------------------------------
        // BINARY SEARCH EN C (YA ORDENADO)
        // -------------------------------
        System.out.println("BinarySearch 4 en C");
        System.out.println("Arreglo ordenado C: " + Arrays.toString(C));
        System.out.println("Resultado binarySearch(4) → " +
                SearchAlgorithms.binarySearch(C, 4));
        System.out.println();

        // -------------------------------
        // BINARY SEARCH EN E (ORIGINAL + ORDENADO)
        // -------------------------------
        System.out.println("BinarySearch 8 en E");
        System.out.println("Arreglo original: " + Arrays.toString(E));

        int[] E_copy = Arrays.copyOf(E, E.length);
        Arrays.sort(E_copy);
        System.out.println("Arreglo ordenado: " + Arrays.toString(E_copy));

        System.out.println("Resultado binarySearch(8) → " +
                SearchAlgorithms.binarySearch(E_copy, 8));
        System.out.println();

        // -------------------------------
        // FINDALL EN D (duplicados)
        // -------------------------------
        System.out.println("Arreglo D (duplicados): " + Arrays.toString(D));
        System.out.println("findAll de valores == 2 → " +
                SearchAlgorithms.findAll(D, x -> x == 2));
        System.out.println();

        // -------------------------------
        // DEMO DE SLL
        // -------------------------------
        Node head = new Node(3);
        head.next = new Node(1);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);

        System.out.println("Lista enlazada utilizada: 3 → 1 → 3 → 2");

        // Primera ocurrencia SLL
        Node firstNode = SearchAlgorithms.findFirst(head, 3);
        int firstPosition = getNodePosition(head, firstNode);
        System.out.println("SLL primera ocurrencia de 3 → nodo " + firstPosition +
                " | valor dentro del nodo: " + firstNode.value);

        // Última ocurrencia SLL
        Node lastNode = SearchAlgorithms.findLast(head, 3);
        int lastPosition = getNodePosition(head, lastNode);
        System.out.println("SLL última ocurrencia de 3 → nodo " + lastPosition +
                " | valor dentro del nodo: " + lastNode.value);

        // findAll SLL
        List<Node> nodesFound = SearchAlgorithms.findAll(head, n -> n.value < 3);
        System.out.println("SLL findAll <3 → " + nodesFound);
    }

    // ------------------------------------------
    // OBTENER NÚMERO DE NODO DENTRO DE LA LISTA
    // ------------------------------------------
    private static int getNodePosition(Node head, Node target) {
        int pos = 1;
        Node current = head;
        while (current != null) {
            if (current == target) return pos;
            current = current.next;
            pos++;
        }
        return -1;
    }
}

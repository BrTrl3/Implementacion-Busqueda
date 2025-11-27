package ed.u2.search;

import java.util.*;

public class SearchDemo {

    public static void main(String[] args) {

        int[] A = {3, 2, 5, 7, 3};
        int[] B = {7, 7, 7};
        int[] C = {2};
        int[] D = {};

        // -------------------------------
        // PRIMERA Y ÚLTIMA OCURRENCIA A
        // -------------------------------
        System.out.println("Arreglo A: " + Arrays.toString(A));
        System.out.println("Primera ocurrencia en A de 3 → " + SearchAlgorithms.indexOfFirst(A, 3));
        System.out.println("Última ocurrencia en A de 3 → " + SearchAlgorithms.indexOfLast(A, 3));
        System.out.println();

        // -------------------------------
        // FINDALL EN A (PARES)
        // -------------------------------
        System.out.println("findAll pares en A");
        System.out.println("Arreglo usado: " + Arrays.toString(A));
        System.out.println("Índices encontrados → " +
                SearchAlgorithms.findAll(A, x -> x % 2 == 0));
        System.out.println();

        // -------------------------------
        // CENTINELA EN B
        // -------------------------------
        System.out.println("Arreglo B: " + Arrays.toString(B));
        System.out.println("Centinela búsqueda 7 en B → " +
                SearchAlgorithms.sentinelSearch(B, 7));
        System.out.println();

        // -------------------------------
        // BINARY SEARCH EN A (ORIGINAL + ORDENADO)
        // -------------------------------
        int[] A_copy = Arrays.copyOf(A, A.length);
        System.out.println("BinarySearch 5 en A");
        System.out.println("Arreglo original: " + Arrays.toString(A_copy));

        Arrays.sort(A_copy);
        System.out.println("Arreglo ordenado: " + Arrays.toString(A_copy));

        System.out.println("Resultado binarySearch(5) → " +
                SearchAlgorithms.binarySearch(A_copy, 5));
        System.out.println();

        // -------------------------------
        // USAR C Y D TAMBIÉN EN BÚSQUEDAS
        // -------------------------------
        System.out.println("Arreglo C: " + Arrays.toString(C));
        System.out.println("binarySearch(2) en C (ordenado) → " +
                SearchAlgorithms.binarySearch(C, 2));
        System.out.println();

        System.out.println("Arreglo D: " + Arrays.toString(D));
        System.out.println("findAll en D (pares) → " +
                SearchAlgorithms.findAll(D, x -> x % 2 == 0));
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
        return -1; // no debería pasar
    }

}

package ed.u2.search;

/**
 * Clase Node:
 * Representa un nodo de una Lista Enlazada Simple (SLL).
 *
 * Cada nodo contiene:
 *  - un valor entero (value)
 *  - una referencia al siguiente nodo (next)
 *
 * Se usa en este taller para probar los métodos de búsqueda
 * sobre estructuras dinámicas.
 */
public class Node {

    public int value;   // Dato almacenado en el nodo
    public Node next;   // Enlace al siguiente nodo

    /**
     * Constructor: crea un nodo con un valor específico.
     * @param value valor que contendrá el nodo
     */
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

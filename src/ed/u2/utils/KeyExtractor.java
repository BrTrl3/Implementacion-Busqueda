package ed.u2.utils;

/**
 * Funcional interface que define cómo extraer la clave de ordenación
 * de un objeto T. La clave debe implementar Comparable.
 */
public interface KeyExtractor<T, K> {
    K extract(T t);
}

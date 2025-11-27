package ed.u2.sorting;

import ed.u2.utils.KeyExtractor;
import java.util.Arrays;

public class SortingUtils {

    private SortingUtils() {}

    // ME   TODO swap(), los algoritmos BubbleSort, SelectionSort e InsertionSort lo usan
    public static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Verifica si está ordenado
    public static <T extends Comparable<T>> boolean isSorted(T[] a) {
        if (a == null || a.length < 2) return true;

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i].compareTo(a[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    // Copia superficial (necesaria para benchmarking)
    @SuppressWarnings("unchecked")
    public static <T> T[] copy(T[] original) {
        return (original == null) ? null : (T[]) original.clone();
    }

    // Benchmark generalizado
    public static <T> BenchmarkResult runBenchmark(String algorithmName, String datasetName, String datasetType, T[] original,
            KeyExtractor<T, ? extends Comparable<?>> keyExtractor, SortingAlgorithm<T> algorithm) {

        final int R = 10;
        long[] times = new long[R];
        long totalComparisons = 0;
        long totalSwaps = 0;

        for (int i = 0; i < R; i++) {
            T[] copy = copy(original);

            long start = System.nanoTime();
            SortMetrics metrics = algorithm.sort(copy, keyExtractor);
            long end = System.nanoTime();

            times[i] = end - start;

            totalComparisons += metrics.comparisons();
            totalSwaps += metrics.swaps();
        }

        long[] valid = Arrays.copyOfRange(times, 3, R);
        Arrays.sort(valid);
        long median = valid[valid.length / 2];

        return new BenchmarkResult(algorithmName, datasetName, datasetType, original.length, totalComparisons / R,
                totalSwaps / R, median);
    }
}

package ed.u2.sorting;

import ed.u2.utils.KeyExtractor;

public class SelectionSort {

    private SelectionSort() {}

    public static <T> SortMetrics sort(T[] a, KeyExtractor<T, ? extends Comparable<?>> keyExtractor) {

        if (a == null || a.length < 2) {
            return new SortMetrics(0, 0);
        }

        long comparisons = 0;
        long swaps = 0;

        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            Comparable<Object> minValue = (Comparable<Object>) keyExtractor.extract(a[minIndex]);

            for (int j = i + 1; j < n; j++) {
                Comparable<Object> current = (Comparable<Object>) keyExtractor.extract(a[j]);
                comparisons++;

                if (current.compareTo(minValue) < 0) {
                    minIndex = j;
                    minValue = current;
                }
            }

            if (minIndex != i) {
                T tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
                swaps++;
            }
        }

        return new SortMetrics(comparisons, swaps);
    }
}

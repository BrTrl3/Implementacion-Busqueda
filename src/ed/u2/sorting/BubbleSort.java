package ed.u2.sorting;

import ed.u2.utils.KeyExtractor;

public class BubbleSort {

    private BubbleSort() {}

    public static <T> SortMetrics sort(T[] a, KeyExtractor<T, ? extends Comparable<?>> keyExtractor) {
        if (a == null || a.length < 2) {
            return new SortMetrics(0, 0);
        }

        long comparisons = 0;
        long swaps = 0;

        int n = a.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                Comparable left = keyExtractor.extract(a[j]);
                Comparable right = keyExtractor.extract(a[j + 1]);
                comparisons++;

                if (left.compareTo(right) > 0) {
                    T tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return new SortMetrics(comparisons, swaps);
    }
}

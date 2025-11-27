package ed.u2.sorting;

import ed.u2.utils.KeyExtractor;

public class InsertionSort {

    private InsertionSort() {}

    public static <T> SortMetrics sort(T[] a, KeyExtractor<T, ? extends Comparable<?>> keyExtractor) {

        if (a == null || a.length < 2) {
            return new SortMetrics(0, 0);
        }

        long comparisons = 0;
        long swaps = 0;

        int n = a.length;

        for (int i = 1; i < n; i++) {
            T keyElem = a[i];
            Comparable<Object> keyVal = (Comparable<Object>) keyExtractor.extract(keyElem);

            int j = i - 1;

            while (j >= 0) {
                comparisons++;

                Comparable<Object> current =
                        (Comparable<Object>) keyExtractor.extract(a[j]);

                if (current.compareTo(keyVal) > 0) {
                    a[j + 1] = a[j];
                    swaps++;
                    j--;
                } else {
                    break;
                }
            }

            a[j + 1] = keyElem;
        }

        return new SortMetrics(comparisons, swaps);
    }
}

package BubbleNBinary;

public class BubbleSort {
    /* Swap marker; whether there was a swap in the inner loop */
    static boolean swapped;

    /* Perform the bubble sort algorithm */
    @SuppressWarnings({"rawtypes","unchecked"})
    public static void sort(Comparable[] elements) {
        for (int i = 0; i < elements.length; i++) {

            for (int j = 1; j < elements.length - i; j++) {
                if (elements[j - 1].compareTo(elements[j])>0) {
                    swap(elements, j - 1, j);
                }
            }

            /* If no two elements were swapped by inner loop, then break  */
            if (swapped == false) {
                break;
            }
        }
    }

    /* Swap two array elements: elements[a] with elements[b] */
    @SuppressWarnings({"rawtypes","unchecked"})
    public static void swap(Comparable[] elements, int a, int b) {
        Comparable tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
        swapped = true; // a swap has occurred
    }

}


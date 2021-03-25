package BubbleNBinary;

public class BinarySearch {
    @SuppressWarnings({"rawtypes","unchecked"})

    public static int search( IPAddress[] elements, long key) {

        int low = 0;									// 1
        int high = elements.length - 1;					// 1


        while (low <= high) {							// 2
            int mid = low + (high - low) / 2;			// 3

            if (key>=elements[mid].ipFrom && key<=elements[mid].ipTo ) {					// 4
                return mid;							// 4
            } else if (key < elements[mid].ipFrom ) {			// 5
                high= mid - 1;							// 5
            } else {									// 6
                low=mid+1;								// 6
            }
        }
        return -1;										// 7
    }
}

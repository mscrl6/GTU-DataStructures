public class BubbleSort extends SortAlgorithm {

    public BubbleSort(int input_array[]) {
        super(input_array);
    }

    // Implements the bubble sort algorithm to sort the array.
    public void sort() {
        boolean swapped;
        // Outer loop to control the number of passes.
        for (int i = 0; i < arr.length - 1; ++i) {
            swapped = false;
            // Inner loop for comparing elements.
            for (int j = 0; j < arr.length - i - 1; j++) {
                comparison_counter++;
                // Swap if the elements are in the wrong order.
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }
            // If no elements were swapped the array is sorted.
            if (!swapped) {
                break;
            }
        }
    }

    @Override
    public void print() {
        System.out.print("Bubble Sort\t=>\t");
        super.print();
    }
}

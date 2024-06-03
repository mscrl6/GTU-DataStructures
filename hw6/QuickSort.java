public class QuickSort extends SortAlgorithm {

    public QuickSort(int input_array[]) {
        super(input_array);
    }

    // Partition the array elements based on a pivot element
    private int partition(int start, int end) {
        int pivot = arr[end];// Choose the last element as the pivot
        int i = start - 1;// Index of smaller element
        // Rearrange the elements according to the pivot
        for (int j = start; j <= end - 1; j++) {
            comparison_counter++;
            if (arr[j] < pivot) {
                i++;
                swap(j, i); // Swap current element with the element at i
            }
        }
        i++; // Move the pivot element to the correct position
        swap(i, end); // Swap the pivot element with the element at i
        return i; // Return the pivot index
    }

    // Recursive function to perform quicksort
    private void sort(int start, int end) {
        if (end <= start) // if the range has less than two elements return
            return;
        int pivot = partition(start, end);// Partition the range and get the pivot index
        sort(start, pivot - 1); // Recursively sort elements before partition
        sort(pivot + 1, end); // Recursively sort elements after partition
    }

    // Sort method that starts the recursive sorting process
    @Override
    public void sort() {
        sort(0, arr.length - 1);
    }

    @Override
    public void print() {
        System.out.print("Quick Sort\t=>\t");
        super.print();
    }
}

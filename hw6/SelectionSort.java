public class SelectionSort extends SortAlgorithm {

    public SelectionSort(int input_array[]) {
        super(input_array);
    }

    // Sort method that starts the recursive sorting process
    @Override
    public void sort() {
        sort(0);
    }

    // Recursive method to perform the selection sort
    private void sort(int lastIndex) {
        // If the last index is the last element stop the recursion
        if (lastIndex == arr.length - 1) {
            return;
        }
        int minIndex = lastIndex;
        // Loop to find the minimum element in the unsorted part of the array
        for (int i = lastIndex + 1; i < arr.length; ++i) {
            comparison_counter++;
            if (arr[minIndex] > arr[i]) {
                minIndex = i;
            }
        }
        // Swap the found minimum element with the first element of the unsorted part
        swap(lastIndex, minIndex);
        // Recursive call to sort the remaining part of the array
        sort(lastIndex + 1);
    }

    @Override
    public void print() {
        System.out.print("Selection Sort\t=>\t");
        super.print();
    }
}

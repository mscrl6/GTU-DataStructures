public class MergeSort extends SortAlgorithm {

    public MergeSort(int input_array[]) {
        super(input_array);
    }

    // Combines two halves into a sorted final array
    private void merge(int left, int middle, int right) {
        int n1 = middle - left + 1; // Number of elements in the first half
        int n2 = right - middle; // Number of elements in the secoond half

        int L[] = new int[n1]; // Temp array for first half
        int R[] = new int[n2]; // Temp array for first half

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[middle + 1 + j];

        int i = 0, j = 0;

        int k = left; // Initial index of subarray

        // Merge the temporary arrays back into the original array
        while (i < n1 && j < n2) {
            comparison_counter++;
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // Copy remaining elements of L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // Copy remaining elements of R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Sorts the array using merge sort algorithm
    private void sort(int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            sort(left, middle);
            sort(middle + 1, right);

            merge(left, middle, right);
        }
    }

    // Sort method that starts the recursive sorting process
    @Override
    public void sort() {
        sort(0, arr.length - 1);
    }

    @Override
    public void print() {
        System.out.print("Merge Sort\t=>\t");
        super.print();
    }
}

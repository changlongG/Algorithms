package algorithm;

public class QuickSort {
	public static int partition(int[] array, int low, int high) {
		int i = low;
		int j = high;
		while (i < j) {
			while (i < high && array[low] >= array[i]) {
				i++;
				// System.out.print(i + " ");
			}
			while (j >= low && array[low] < array[j]) {
				j--;
				// System.out.println(j + " ");
			}
			swap(array, i, j);
		}

		swap(array, i, j);
		swap(array, low, j);
		return j;
	}

	public static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int pivit = partition(array, low, high);
			quickSort(array, low, pivit - 1);
			quickSort(array, pivit + 1, high);

		}

	}

	public static void main(String[] args) {
		int[] array = new int[] { 3, 59, 7, 2, 4, 9, 0, 5, 4, 4, 2, 56, 33, 21 };
		quickSort(array, 0, array.length - 1);
		for (int i : array) {
			System.out.print(i + " ");
		}
	}
}

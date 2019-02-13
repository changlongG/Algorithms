package algorithm;

public class InsertionSort {
	public static int[] insertionsort(int[] array) {
		int index = 1;
		while (index < array.length) {
			for (int i = index; i > 0; i--) {
				if (array[i] < array[i - 1]) {
					int temp = array[i];
					array[i] = array[i - 1];
					array[i - 1] = temp;
				}
			}
			index++;
		}
		return array;
	}

	public static void main(String[] args) {
		int[] array = { 4, 5, 3, 7, 9, 5, 8, 11, -1 };
		array = insertionsort(array);
		for (int i : array) {
			System.out.print(i + ", ");
		}
	}
}

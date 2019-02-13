package algorithm;

public class ShellSort {
	public static int[] shellSort(int[] array) {
		int increment = array.length / 3;
		while (true) {
			// System.out.println(increment);
			int index = 0;
			while (index < array.length - increment) {
				for (int i = index; i >= 0 && i + increment < array.length; i--) {
					if (array[i] > array[i + increment]) {
						int temp = array[i];
						array[i] = array[i + increment];
						array[i + increment] = temp;
					}
				}
				index++;
			}
			if (increment == 1) {
				break;
			}
			increment = increment / 3;
		}
		return array;
	}

	public static void main(String[] args) {
		int[] array = { 4, 5, 3, 7, 9, 5, 8, 11, -1, 9, 2, 5, 11, 29, 4 };
		array = shellSort(array);
		for (int i : array) {
			System.out.print(i + ", ");
		}
	}
}

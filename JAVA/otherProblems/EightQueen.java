package algorithm;

public class EightQueen {
	public static int abs(int a, int b) {
		return a > b ? (a - b) : (b - a);
	}

	public static boolean oK(int[] position, int len) {
		for (int i = 1; i < len; i++) {
			if (position[i] == position[len] || abs(len, i) == abs(position[len], position[i])) {
				return false;
			}
		}
		return true;
	}

	public static void print(int[] a) {
		for (int i : a) {
			System.out.print(i);

		}
		System.out.println();
	}

	public static int eightQueen_iterative(int n) {
		int k = 1;
		int count = 0;
		int[] position = new int[n + 1];
		while (k > 0) {
			position[k] = position[k] + 1;
			while (position[k] <= n && !oK(position, k)) {
				position[k] = position[k] + 1;
			}
			if (position[k] <= n) {
				if (k == n) {
					count++;
				} else {
					k++;
					position[k] = 0;
				}
			} else {
				k--;
			}
		}

		return count;
	}

	public static int eightQueen_recursive(int n) {
		int[] position = new int[n + 1];
		int count = 0;
		int k = 1;
		return recursive(n, position, k, count);
	}

	public static int recursive(int n, int[] position, int k, int count) {
		if (k > n) {
			count++;
		} else {
			for (int i = 1; i <= n; i++) {
				position[k] = i;
				if (oK(position, k)) {
					count = recursive(n, position, k + 1, count);
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(eightQueen_iterative(8));
		System.out.println(eightQueen_recursive(8));
	}
}

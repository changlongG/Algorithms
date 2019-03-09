//zoj1406 Jungle Roads  kruskal
package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JungleRoads {
	public static int villages = 0;
	public static JungleRoadsEdge[] edges_array = new JungleRoadsEdge[80];
	public static int edgesIndex = 0;
	public static int[] vetx = new int[28];

	public static int partition(JungleRoadsEdge[] array, int low, int high) {
		int i = low;
		int j = high;
		while (i < j) {
			while (i < high && array[i].edge <= array[low].edge) {
				i++;
			}
			while (j >= low && array[j].edge > array[low].edge) {
				j--;
			}
			swap(array, i, j);
		}
		swap(array, i, j);
		swap(array, low, j);
		return j;
	}

	public static void swap(JungleRoadsEdge[] edges, int i, int j) {
		JungleRoadsEdge temp = edges[i];
		edges[i] = edges[j];
		edges[j] = temp;
	}

	public static void quickSort(JungleRoadsEdge[] array, int low, int high) {
		if (low < high) {
			int pivot = partition(array, low, high);
			quickSort(array, low, pivot - 1);
			quickSort(array, pivot + 1, high);
		}
	}

	public static int getRoot(int a) {

		while (vetx[a] != a) {
			a = vetx[a];
		}
		return a;
	}

	public static int kruskal() {
		int res = 0;
		int count = 0;
		quickSort(edges_array, 0, edgesIndex - 1);
		for (int i = 0; i < edgesIndex; i++) {
			int a = edges_array[i].a.charAt(0) - 'A';
			int b = edges_array[i].b.charAt(0) - 'A';
			if (getRoot(a) != getRoot(b)) {
				vetx[getRoot(a)] = b;
				count++;
				res += edges_array[i].edge;
			}
		}
		return res;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("JungleRoads.txt"));
		while (true) {
			villages = sc.nextInt();
			if (villages == 0) {
				break;
			}
			for (int i = 0; i < 80; i++) {
				edges_array[i] = null;
			}
			for (int i = 0; i < villages; i++) {
				vetx[i] = i;
			}
			sc.nextLine();
			edgesIndex = 0;
			for (int i = 0; i < villages - 1; i++) {
				String[] temprow = sc.nextLine().split(" ");
				for (int j = 0; j < Integer.valueOf(temprow[1]); j++) {
					edges_array[edgesIndex] = new JungleRoadsEdge(temprow[0], temprow[2 * (j + 1)],
							Integer.valueOf(temprow[2 * (j + 1) + 1]));
					edgesIndex++;
				}
			}
			System.out.println(kruskal());
		}
		sc.close();
	}
}

class JungleRoadsEdge {
	public String a, b;
	public int edge;

	public JungleRoadsEdge(String a, String b, int edge) {
		this.a = a;
		this.b = b;
		this.edge = edge;
	}

}
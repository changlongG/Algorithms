package algorithm;

import java.util.Arrays;

public class MinHeap {
	private int maxsize = 10;
	private int size;
	private int[] minheap;

	public MinHeap() {
		minheap = new int[maxsize + 1];
		size = 1;
		minheap[0] = Integer.MIN_VALUE;
	}

	public void swap(int a, int b) {
		int temp = minheap[a];
		minheap[a] = minheap[b];
		minheap[b] = temp;
	}

	public void insert(int insertvalue) {
		if (size <= maxsize) {
			minheap[size] = insertvalue;
			int current = size;
			while (minheap[current] < minheap[current / 2]) {
				swap(current, current / 2);
				current = current / 2;
			}
			size++;
		} else {
			if (insertvalue > minheap[1]) {
				remove();
				insert(insertvalue);
			}
		}
	}

	public void shiftdown(int pos) {
		int small = pos;
		while (pos <= size / 2) {
			int left = pos * 2;
			int right = pos * 2 + 1;
			if (left < size && minheap[left] < minheap[pos]) {
				small = left;
			}
			if (right < size && minheap[right] < minheap[left]) {
				small = right;
			}
			if (small == pos) {
				break;
			} else {
				swap(pos, small);
				pos = small;
			}

		}
	}

	public void remove() {
		swap(1, size - 1);
		minheap[size - 1] = 0;
		size--;
		shiftdown(1);

	}

	public static void main(String[] args) {
		MinHeap mh = new MinHeap();
		mh.insert(-11);
		mh.insert(10);
		mh.insert(11);
		mh.insert(14);
		mh.insert(9);
		mh.insert(8);
		mh.insert(18);
		mh.insert(1);
		mh.insert(111);
		mh.insert(-1111);

		for (int i : mh.minheap) {
			System.out.print(i + " ");
		}

	}
}

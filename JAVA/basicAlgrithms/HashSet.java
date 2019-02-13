package algorithm;

class HashSet {
	private int[][] bruckets;
	private int[] brucketsize;
	private final int size = 100;

	/** Initialize your data structure here. */
	public HashSet() {
		bruckets = new int[size][size];
		brucketsize = new int[size];
	}

	public int hashcode(int key) {
		return key % size;
	}

	public void swap(int[] array, int a, int length) {
		int temp = array[length - 1];
		array[length - 1] = array[a];
		array[a] = temp;
	}

	public void add(int key) {
		int brucket = hashcode(key);
		if (brucketsize[brucket] == 0) {
			bruckets[brucket][brucketsize[brucket]] = key;
			brucketsize[brucket] += 1;
		} else {
			int signal = 0;
			for (int i = 0; i < brucketsize[brucket]; i++) {
				if (bruckets[brucket][i] == key) {
					signal = 1;
					continue;
				}
			}
			if (signal == 0) {
				bruckets[brucket][brucketsize[brucket]] = key;
				brucketsize[brucket] += 1;
			}
		}

	}

	public void remove(int key) {
		int brucket = hashcode(key);
		if (brucketsize[brucket] > 0) {
			for (int i = 0; i < brucketsize[brucket]; i++) {
				if (bruckets[brucket][i] == key) {
					swap(bruckets[brucket], i, brucketsize[brucket]);
					bruckets[brucket][brucketsize[brucket] - 1] = 0;
					brucketsize[brucket] -= 1;
					break;
				}
			}
		}
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key) {
		int brucket = hashcode(key);
		if (brucketsize[brucket] > 0) {
			for (int i = 0; i < brucketsize[brucket]; i++) {
				if (bruckets[brucket][i] == key) {
					return true;
				}
			}
		}
		return false;
	}
}

/**
 * Your MyHashSet object will be instantiated and called as such: MyHashSet obj
 * = new MyHashSet(); obj.add(key); obj.remove(key); boolean param_3 =
 * obj.contains(key);
 */
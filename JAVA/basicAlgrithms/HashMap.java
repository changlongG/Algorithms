package algorithm;

class HashMap {
	private int[][][] bruckets;
	private int[] brucketsize;

	/** Initialize your data structure here. */
	public HashMap() {
		bruckets = new int[100][100][2];
		brucketsize = new int[100];
	}

	public int hashcode(int key) {
		return key % 100;
	}

	public void swap(int[][] array, int a, int length) {
		int[] temp = array[length - 1];
		array[length - 1] = array[a];
		array[a] = temp;
	}

	/** value will always be non-negative. */
	public void put(int key, int value) {
		int brucket = hashcode(key);
		if (brucketsize[brucket] == 0) {
			bruckets[brucket][0][0] = key;
			bruckets[brucket][0][1] = value;
			brucketsize[brucket] += 1;
		} else {
			int signal = 0;
			for (int i = 0; i < brucketsize[brucket]; i++) {
				if (bruckets[brucket][i][0] == key) {
					bruckets[brucket][i][1] = value;
					signal = 1;
					break;
				}
			}
			if (signal == 0) {
				bruckets[brucket][brucketsize[brucket]][0] = key;
				bruckets[brucket][brucketsize[brucket]][1] = value;
				brucketsize[brucket] += 1;
			}
		}

	}

	/**
	 * Returns the value to which the specified key is mapped, or -1 if this map
	 * contains no mapping for the key
	 */
	public int get(int key) {
		int brucket = hashcode(key);
		if (brucketsize[brucket] > 0) {
			for (int i = 0; i < brucketsize[brucket]; i++) {
				if (bruckets[brucket][i][0] == key) {
					return bruckets[brucket][i][1];
				}
			}
		}
		return -1;
	}

	/**
	 * Removes the mapping of the specified value key if this map contains a
	 * mapping for the key
	 */
	public void remove(int key) {
		int brucket = hashcode(key);
		if (brucketsize[brucket] > 0) {
			for (int i = 0; i < brucketsize[brucket]; i++) {
				if (bruckets[brucket][i][0] == key) {
					swap(bruckets[brucket], i, brucketsize[brucket]);
					brucketsize[brucket] -= 1;
					break;
				}
			}
		}
	}
}

/**
 * Your MyHashMap object will be instantiated and called as such: MyHashMap obj
 * = new MyHashMap(); obj.put(key,value); int param_2 = obj.get(key);
 * obj.remove(key);
 */

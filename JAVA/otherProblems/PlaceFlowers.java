package algorithm;

class PlaceFlowers {
	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
		int count = 0;
		int plots = 0;
		int signal = 0;
		if (flowerbed.length == 1 && flowerbed[0] == 0) {
			plots += 1;
		}
		if (flowerbed[0] == 0) {
			signal = 1;
		}
		for (int i = 0; i < flowerbed.length; i++) {
			if (flowerbed[i] == 1) {
				if (count > 2) {
					if (signal == 0) {
						if (count % 2 == 0) {
							plots += (count - 1) / 2;

						} else {
							plots += count / 2;
						}
					} else {
						plots += count / 2;

					}
				}
				if (count == 2 && i - 1 == 1) {
					plots += 1;
				}
				count = 0;
				signal = 0;
			} else {
				count++;
				if (i == flowerbed.length - 1) {
					if (signal == 1) {
						plots += (count + 1) / 2;

					} else {
						plots += count / 2;
					}
				}
			}

		}

		return n <= plots;
	}

	public static void main(String[] args) {
		int[] a = { 0, 1, 0 };
		int n = 1;
		System.out.println(canPlaceFlowers(a, n));
	}
}
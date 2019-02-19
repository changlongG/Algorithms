//Tempter of the bone. Use dfs search to find a way to the bone within a time limit.

package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TempteroftheBone {
	public static int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	public static int temprow = 0;
	public static int tempcolumn = 0;

	public static int distance(int[] dog, int[] door) {
		int dx = dog[0] > door[0] ? (dog[0] - door[0]) : (door[0] - dog[0]);
		int dy = dog[1] > door[1] ? (dog[1] - door[1]) : (door[1] - dog[1]);
		return dx + dy;
	}

	public static int abs(int a, int b) {

		return a > b ? (a - b) : (b - a);
	}

	public static boolean isMazePoint(int x, int y) {
		return x >= 0 && x < temprow && y >= 0 && y < tempcolumn;
	}

	public static boolean dfs(int[][] map, int[] dog, int[] door, int time) {
		// System.out.println(dog[0] + " " + dog[1]);
		if (dog[0] == door[0] && dog[1] == door[1] && time == 0) {
			return true;
		}
		if (dog[0] == door[0] && dog[1] == door[1] && time != 0) {
			return false;
		}
		if (time <= 0) {
			return false;
		}

		if (abs(distance(dog, door), time) % 2 == 1 || distance(dog, door) < time) {
			return false;
		}

		// time--;
		for (int i = 0; i < 4; i++) {
			int nextX = dog[0] + direction[i][0];
			int nextY = dog[1] + direction[i][1];
			if (isMazePoint(nextX, nextY) && map[nextX][nextY] < 2) {
				int[] nextdog = { nextX, nextY };
				map[nextX][nextY] = 2;

				if (dfs(map, nextdog, door, time - 1)) {
					return true;
				}

				map[nextX][nextY] = 0;
			}
		}
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		long startTime = System.currentTimeMillis();

		Scanner sc = new Scanner(new FileInputStream("TempteroftheBoneTestcase.txt"));
		while (true) {
			String[] info = sc.nextLine().split("\\s+");
			int row = Integer.valueOf(info[0]);
			int column = Integer.valueOf(info[1]);
			int time = Integer.valueOf(info[2]);
			int[] dog = new int[2];
			int[] door = new int[2];
			if (row + column + time == 0) {
				break;
			}
			int[][] map = new int[row][column];
			for (int i = 0; i < row; i++) {
				char[] temprow = sc.nextLine().toCharArray();
				for (int j = 0; j < column; j++) {
					switch (temprow[j]) {
					case '.':
						map[i][j] = 0;
						break;
					case 'S':
						map[i][j] = 2;
						dog[0] = i;
						dog[1] = j;
						break;
					case 'D':
						map[i][j] = 1;
						door[0] = i;
						door[1] = j;
						break;
					case 'X':
						map[i][j] = 3;
						break;
					}
				}
			}
			temprow = row;
			tempcolumn = column;
			boolean a = dfs(map, dog, door, time);
			System.out.println("# " + a);

		}

		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}

}

// maze with fire problem, use one bfs search, put firepoint into queue first then human.
package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazewithFire_v2 {
	static MyQueue<MazePoint_v2> queue = new MyQueue();
	static int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static boolean isMazePoint(int x, int y, int row, int column) {
		return x >= 0 && x < row && y >= 0 && y < column;
	}

	public static boolean isEdge(int x, int y, int row, int column) {
		return x == 0 || x == row - 1 || y == 0 || y == column - 1;
	}

	public static int bfs(int[][] map) {
		while (!queue.isEmpty()) {
			MazePoint_v2 mp = queue.poll();
			if (mp.getType() == 1) {
				if (isEdge(mp.getX(), mp.getY(), map.length, map[0].length)) {
					return mp.getStep() + 1;
				}
				for (int i = 0; i < 4; i++) {
					int nextX = mp.getX() + direction[i][0];
					int nextY = mp.getY() + direction[i][1];
					if (isMazePoint(nextX, nextY, map.length, map[0].length) && map[nextX][nextY] == 0) {
						map[nextX][nextY] = 1;
						MazePoint_v2 temp = new MazePoint_v2(nextX, nextY, 1);
						temp.setStep(mp.getStep() + 1);
						queue.add(temp);
					}
				}
			} else {
				for (int i = 0; i < 4; i++) {
					int nextX = mp.getX() + direction[i][0];
					int nextY = mp.getY() + direction[i][1];
					if (isMazePoint(nextX, nextY, map.length, map[0].length) && map[nextX][nextY] < 2) {
						map[nextX][nextY] = 2;
						MazePoint_v2 temp = new MazePoint_v2(nextX, nextY, 2);
						queue.add(temp);
					}
				}
			}
		}
		return -1;
	}

	public static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		long startTime = System.currentTimeMillis();

		Scanner sc = new Scanner(new FileInputStream("MazeWithFireTestcase.txt"));
		int casenumber = sc.nextInt();
		for (int i = 0; i < casenumber; i++) {
			while (!queue.isEmpty()) {
				queue.poll();
			}
			int row = sc.nextInt();
			int column = sc.nextInt();
			int[][] maps = new int[row][column];
			sc.nextLine();
			for (int j = 0; j < row; j++) {
				char[] temprow = sc.nextLine().toCharArray();
				for (int m = 0; m < column; m++) {
					switch (temprow[m]) {
					case '.':
						maps[j][m] = 0;
						break;
					case 'J':
						maps[j][m] = 1;
						break;
					case 'F':
						maps[j][m] = 2;
						break;
					case '#':
						maps[j][m] = 3;
						break;
					}
				}
			}
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < column; c++) {
					if (maps[r][c] == 2) {
						queue.add(new MazePoint_v2(r, c, 2));
					}
				}
			}
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < column; c++) {
					if (maps[r][c] == 1) {
						queue.add(new MazePoint_v2(r, c, 1));
						break;
					}
				}
			}
			int a = bfs(maps);
			System.out.println("#" + a);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}
}

class MazePoint_v2 {
	private int x;
	private int y;
	private int step;
	private int type;

	public MazePoint_v2(int x, int y, int type) {
		this.x = x;
		this.y = y;
		// 0 能走 1 人 2 火 3 墙
		this.step = 0;
		this.type = type;
	}

	public void setStep(int v) {
		this.step = v;
	}

	public int getStep() {
		return this.step;
	}

	public int getType() {
		return this.type;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
// maze with fire problem, use bfs search twice, once for all firepoints ,record the fire reach time for each position. And the other for human, if steps less than fire time, then it can be visited. Firepoints first

package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazewithFire {
	private static MazePoint[][] map;
	MyQueue<MazePoint> queue = new MyQueue();
	static int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public MazewithFire(int[][] map) {
		this.map = new MazePoint[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				this.map[i][j] = new MazePoint(i, j, map[i][j]);
				if (map[i][j] == 2) {
					this.map[i][j].setFiretime(0);
				}
			}
		}
	}

	public boolean isMazePoint(int x, int y) {
		return x >= 0 && x < this.map.length && y >= 0 && y < this.map[0].length;
	}

	public boolean isEdge(int x, int y) {
		return x == 0 || x == this.map.length - 1 || y == 0 || y == this.map[0].length - 1;
	}

	public void firebfs() {
		while (!queue.isEmpty()) {
			MazePoint mp = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = mp.getX() + direction[i][0];
				int nextY = mp.getY() + direction[i][1];
				if (isMazePoint(nextX, nextY) && map[nextX][nextY].getType() <= 1
						&& map[nextX][nextY].getVistit() == -1) {
					map[nextX][nextY].setVisit(2);
					queue.add(map[nextX][nextY]);
					map[nextX][nextY].setFiretime(mp.getFiretime() + 1);
				}
			}

		}
		// print(map);
	}

	public int humanbfs() {

		while (!queue.isEmpty()) {
			MazePoint mp = queue.poll();

			if (mp.getStep() < mp.getFiretime()) {

				if (isEdge(mp.getX(), mp.getY())) {
					return mp.getStep() + 1;
				}
				for (int i = 0; i < 4; i++) {
					int nextX = mp.getX() + direction[i][0];
					int nextY = mp.getY() + direction[i][1];
					if (isMazePoint(nextX, nextY) && map[nextX][nextY].getType() == 0
							&& map[nextX][nextY].getVistit() != 1) {
						map[nextX][nextY].setVisit(1);
						queue.add(map[nextX][nextY]);
						map[nextX][nextY].setStep(mp.getStep() + 1);

					}
				}

			}
		}
		return -1;
	}

	// print for debug
	public static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print(MazePoint[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j].getFiretime() + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		long startTime = System.currentTimeMillis();

		Scanner sc = new Scanner(new FileInputStream("MazeWithFireTestcase.txt"));
		int casenumber = sc.nextInt();
		for (int i = 0; i < casenumber; i++) {
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
			MazewithFire mf = new MazewithFire(maps);
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < column; c++) {
					if (maps[r][c] == 2) {
						mf.queue.add(map[r][c]);
					}
				}
			}

			mf.firebfs();
			for (int r = 0; r < row; r++) {
				for (int c = 0; c < column; c++) {
					if (maps[r][c] == 1) {
						MazePoint mphuman = new MazePoint(r, c, 1);
						mf.queue.add(mphuman);
						break;
					}
				}
			}

			int a = mf.humanbfs();
			System.out.println("#" + a);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}
}

class MazePoint {
	private int type;
	private int visited;
	private int firetime;
	private int x;
	private int y;
	private int step;

	public MazePoint(int x, int y, int type) {
		this.x = x;
		this.y = y;
		// 0 能走 1 人 2 火 3 墙
		this.type = type;
		this.visited = -1;
		this.firetime = Integer.MAX_VALUE;
		this.step = 0;
	}

	public void setVisit(int v) {
		this.visited = v;
	}

	public int getVistit() {
		return this.visited;
	}

	public void setStep(int v) {
		this.step = v;
	}

	public int getStep() {
		return this.step;
	}

	public void setFiretime(int time) {
		this.firetime = time;
	}

	public int getFiretime() {
		return this.firetime;
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
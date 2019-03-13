package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DungeonMaster {
	public static int levels, row, column = 0;
	public static int[][][] map = new int[31][31][31];
	public static int goall, goalx, goaly = 0;
	public static int[][] directions = { { 0, 0, 1 }, { 0, 0, -1 }, { 0, 1, 0 }, { 0, -1, 0 }, { 1, 0, 0 },
			{ -1, 0, 0 } };
	public static DungeonMasterQueue<DungeonMasterNode> queue = new DungeonMasterQueue();

	public static boolean isMapPoint(int l, int x, int y) {
		return l >= 0 && l < levels && x >= 0 && x < row && y >= 0 && y < column;
	}

	public static int bfs() {
		while (!queue.isEmpty()) {
			DungeonMasterNode node = queue.poll();
			if (node.l == goall && node.x == goalx && node.y == goaly) {
				return node.step;
			}
			for (int i = 0; i < 6; i++) {
				int nextL = node.l + directions[i][0];
				int nextX = node.x + directions[i][1];
				int nextY = node.y + directions[i][2];
				if (isMapPoint(nextL, nextX, nextY) && map[nextL][nextX][nextY] < 3) {
					map[nextL][nextX][nextY] = 3;
					queue.add(new DungeonMasterNode(nextL, nextX, nextY, node.step + 1));
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream("DungeonMaster.txt"));
		while (sc.hasNext()) {
			levels = sc.nextInt();
			row = sc.nextInt();
			column = sc.nextInt();
			if (levels == 0 || row == 0 || column == 0) {
				break;
			}
			sc.nextLine();
			while (!queue.isEmpty()) {
				queue.poll();
			}
			for (int i = 0; i < levels; i++) {
				for (int j = 0; j < row; j++) {
					char[] temprow = sc.nextLine().toCharArray();
					for (int k = 0; k < column; k++) {
						switch (temprow[k]) {
						case '.':
							map[i][j][k] = 0;
							break;
						case 'E':
							map[i][j][k] = 1;
							goall = i;
							goalx = j;
							goaly = k;
							break;
						case 'S':
							map[i][j][k] = 2;
							queue.add(new DungeonMasterNode(i, j, k, 0));
							map[i][j][k] = 3;
							break;
						case '#':
							map[i][j][k] = 3;
							break;
						}

					}
				}
				sc.nextLine();
			}
			int res = bfs();
			// System.out.println(res);
			if (res >= 0) {
				System.out.printf("Escaped in %d minute(s).\n", res);
			} else {
				System.out.println("Trapped!");
			}
		}
	}
}

class DungeonMasterNode {
	public int l, x, y, step;

	public DungeonMasterNode(int l, int x, int y, int step) {
		this.l = l;
		this.x = x;
		this.y = y;
		this.step = step;
	}
}

class DungeonMasterQueue<T> {
	public int size, front, rear;
	public int defaultsize = 10000;
	public T[] queue;

	public DungeonMasterQueue() {
		queue = (T[]) new Object[defaultsize];
		size = 0;
		front = 0;
		rear = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return front == (rear + 1) % defaultsize;
	}

	public void add(T ob) {
		if (!isFull()) {
			size++;
			queue[rear] = ob;
			rear = (rear + 1) % defaultsize;
		}
	}

	public T poll() {
		if (!isEmpty()) {
			size--;
			T temp = queue[front];
			front = (front + 1) % defaultsize;
			return temp;
		} else {
			return null;
		}
	}
}
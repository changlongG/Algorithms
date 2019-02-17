package algorithm;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Interstaller {
	public static int distance(int sx, int sy, int dx, int dy) {
		int tempx = sx > dx ? (sx - dx) : (dx - sx);
		int tempy = sy > dy ? (sy - dy) : (dy - sy);
		return tempx + tempy;
	}

	public static int floyd(int[][] position, int length) {
		for (int k = 0; k < length; k++) {
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					if (position[i][j] > position[i][k] + position[k][j]) {
						position[i][j] = position[i][k] + position[k][j];
					}
				}
			}
		}

		return position[0][1];
	}

	public static int dijkstra(int[][] map, int startpoint, int endpoint, int mapsize) {
		int[] weights = new int[mapsize];
		boolean[] visit = new boolean[mapsize];
		for (int i = 0; i < mapsize; i++) {
			weights[i] = map[startpoint][i];
		}
		visit[startpoint] = true;

		for (int j = 1; j < mapsize; j++) {
			int dis = Integer.MAX_VALUE;
			int k = 0;
			for (int i = 0; i < mapsize; i++) {
				if (!visit[i] && weights[i] < dis) {
					dis = weights[i];
					k = i;
				}
			}
			visit[k] = true;

			// dis为从起点到k的最小值 不等于 map[startpoint][k]
			for (int i = 0; i < mapsize; i++) {
				if (!visit[i] && (weights[i] > dis + map[k][i])) {
					weights[i] = dis + map[k][i];
				}
			}
			// print(weights);
		}

		return weights[endpoint];

	}

	public static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void print(int[] map) {
		System.out.println();
		for (int i = 0; i < map.length; i++) {

			System.out.print(map[i] + " ");

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		long startTime = System.currentTimeMillis();
		Scanner sc = new Scanner(new FileInputStream("test.txt"));
		int testcase = sc.nextInt();
		for (int i = 1; i <= testcase; i++) {
			int wormholes = sc.nextInt();
			int[][] map = new int[wormholes * 2 + 2][wormholes * 2 + 2];
			sc.nextLine();
			String[] sandd = sc.nextLine().split(" ");
			int distance = distance(Integer.valueOf(sandd[0]), Integer.valueOf(sandd[1]), Integer.valueOf(sandd[2]),
					Integer.valueOf(sandd[3]));
			map[0][1] = distance;
			map[1][0] = distance;
			String wormhole_string = "";
			for (int j = 0; j < wormholes; j++) {
				wormhole_string += sc.nextLine() + " ";
			}
			String[] wormhole = wormhole_string.split("\\s+");
			for (int j = 0; j < wormholes; j++) {
				distance = distance(Integer.valueOf(sandd[0]), Integer.valueOf(sandd[1]),
						Integer.valueOf(wormhole[j * 5 + 0]), Integer.valueOf(wormhole[j * 5 + 1]));
				map[0][j * 2 + 2] = distance;
				map[j * 2 + 2][0] = distance;
				distance = distance(Integer.valueOf(sandd[0]), Integer.valueOf(sandd[1]),
						Integer.valueOf(wormhole[j * 5 + 2]), Integer.valueOf(wormhole[j * 5 + 3]));
				map[0][j * 2 + 3] = distance;
				map[j * 2 + 3][0] = distance;
				distance = distance(Integer.valueOf(sandd[2]), Integer.valueOf(sandd[3]),
						Integer.valueOf(wormhole[j * 5 + 0]), Integer.valueOf(wormhole[j * 5 + 1]));
				map[1][j * 2 + 2] = distance;
				map[j * 2 + 2][1] = distance;
				distance = distance(Integer.valueOf(sandd[2]), Integer.valueOf(sandd[3]),
						Integer.valueOf(wormhole[j * 5 + 2]), Integer.valueOf(wormhole[j * 5 + 3]));
				map[1][j * 2 + 3] = distance;
				map[j * 2 + 3][1] = distance;
				distance = Integer.valueOf(wormhole[j * 5 + 4]);
				map[j * 2 + 2][j * 2 + 3] = distance;
				map[j * 2 + 3][j * 2 + 2] = distance;
				for (int a = j + 1; a < wormholes; a++) {
					distance = distance(Integer.valueOf(wormhole[j * 5 + 0]), Integer.valueOf(wormhole[j * 5 + 1]),
							Integer.valueOf(wormhole[a * 5 + 0]), Integer.valueOf(wormhole[a * 5 + 1]));
					map[j * 2 + 2][a * 2 + 2] = distance;
					map[a * 2 + 2][j * 2 + 2] = distance;
					distance = distance(Integer.valueOf(wormhole[j * 5 + 0]), Integer.valueOf(wormhole[j * 5 + 1]),
							Integer.valueOf(wormhole[a * 5 + 2]), Integer.valueOf(wormhole[a * 5 + 3]));
					map[j * 2 + 2][a * 2 + 3] = distance;
					map[a * 2 + 3][j * 2 + 2] = distance;
					distance = distance(Integer.valueOf(wormhole[j * 5 + 2]), Integer.valueOf(wormhole[j * 5 + 3]),
							Integer.valueOf(wormhole[a * 5 + 0]), Integer.valueOf(wormhole[a * 5 + 1]));
					map[j * 2 + 3][a * 2 + 2] = distance;
					map[a * 2 + 2][j * 2 + 3] = distance;
					distance = distance(Integer.valueOf(wormhole[j * 5 + 2]), Integer.valueOf(wormhole[j * 5 + 3]),
							Integer.valueOf(wormhole[a * 5 + 2]), Integer.valueOf(wormhole[a * 5 + 3]));
					map[j * 2 + 3][a * 2 + 3] = distance;
					map[a * 2 + 3][j * 2 + 3] = distance;
				}
			}
			// System.out.println(wormhole_string);
			// print(map);
			// int answer = floyd(map, wormholes * 2 + 2);
			int answer = dijkstra(map, 0, 1, wormholes * 2 + 2);
			System.out.println("#" + answer);
			long endTime = System.currentTimeMillis();
			System.out.println("程序运行时间：" + (endTime - startTime) + "ms");

		}
	}

}

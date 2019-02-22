// Minesweepers BFS solution

package algorithm;

public class Minesweepers {

	public static MyQueue<MapNode> queue = new MyQueue();
	public static int[][] direction = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	public static int[][] searchMine = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
			{ -1, -1 } };

	public static boolean isMapPoint(int x, int y, int row, int column) {
		return (x >= 0 && x < row && y >= 0 && y < column);
	}

	public static int countMine(int x, int y, int row, int column, char[][] board) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			int nextX = x + searchMine[i][0];
			int nextY = y + searchMine[i][1];
			if (isMapPoint(nextX, nextY, row, column)) {
				if (board[nextX][nextY] == 'M') {
					count++;
				}
			}
		}
		return count;
	}

	public static char[][] bfs(char[][] board) {
		while (!queue.isEmpty()) {
			MapNode node = queue.poll();
			for (int i = 0; i < 8; i++) {
				int nextX = node.getX() + searchMine[i][0];
				int nextY = node.getY() + searchMine[i][1];
				if (isMapPoint(nextX, nextY, board.length, board[0].length) && board[nextX][nextY] == 'E') {
					int count = countMine(nextX, nextY, board.length, board[0].length, board);
					if (count > 0) {
						board[nextX][nextY] = (char) (count + '0');

					} else {
						board[nextX][nextY] = 'B';
						MapNode newnode = new MapNode('B', nextX, nextY);
						queue.add(newnode);
					}
				}
			}
		}
		return board;
	}

	public char[][] updateBoard(char[][] board, int[] click) {
		if (isMapPoint(click[0], click[1], board.length, board[0].length)) {
			if (board[click[0]][click[1]] == 'M') {
				board[click[0]][click[1]] = 'X';
			} else if (board[click[0]][click[1]] == 'E') {
				int count = countMine(click[0], click[1], board.length, board[0].length, board);
				if (count > 0) {
					board[click[0]][click[1]] = (char) (count + '0');

				} else {
					board[click[0]][click[1]] = 'B';
					MapNode node = new MapNode('B', click[0], click[1]);
					queue.add(node);
				}
				// queue.add(node);
				bfs(board);
			}
		}

		return board;
	}
}

class MapNode {
	public char type;
	public int x;
	public int y;

	public MapNode(char type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

	public char getType() {
		return this.type;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}

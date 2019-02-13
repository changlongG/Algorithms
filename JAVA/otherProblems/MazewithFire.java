package algorithm;

public class MazewithFire {
	private MazePoint[][] map;
	MyQueue<MazePoint> queue = new MyQueue();

	public MazewithFire(int[][] map) {
		this.map = new MazePoint[map.length][map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				this.map[i][j] = new MazePoint(i, j, map[i][j]);
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
			if (mp.getVistit() == -1) {

				mp.setVisit(2);

				if (isMazePoint(mp.getX() - 1, mp.getY()) && (map[mp.getX() - 1][mp.getY()].getType() <= 1)
						&& map[mp.getX() - 1][mp.getY()].getVistit() == -1) {
					queue.add(map[mp.getX() - 1][mp.getY()]);
					map[mp.getX() - 1][mp.getY()].setFiretime(map[mp.getX()][mp.getY()].getFiretime() + 1);
				}
				if (isMazePoint(mp.getX(), mp.getY() - 1) && (map[mp.getX()][mp.getY() - 1].getType() <= 1)
						&& map[mp.getX()][mp.getY() - 1].getVistit() == -1) {
					queue.add(map[mp.getX()][mp.getY() - 1]);
					map[mp.getX()][mp.getY() - 1].setFiretime(map[mp.getX()][mp.getY()].getFiretime() + 1);
				}
				if (isMazePoint(mp.getX() + 1, mp.getY()) && (map[mp.getX() + 1][mp.getY()].getType() <= 1)
						&& map[mp.getX() + 1][mp.getY()].getVistit() == -1) {
					queue.add(map[mp.getX() + 1][mp.getY()]);
					map[mp.getX() + 1][mp.getY()].setFiretime(map[mp.getX()][mp.getY()].getFiretime() + 1);
				}
				if (isMazePoint(mp.getX(), mp.getY() + 1) && (map[mp.getX()][mp.getY() + 1].getType() <= 1)
						&& map[mp.getX()][mp.getY() + 1].getVistit() == -1) {
					queue.add(map[mp.getX()][mp.getY() + 1]);
					map[mp.getX()][mp.getY() + 1].setFiretime(map[mp.getX()][mp.getY()].getFiretime() + 1);

				}
				// System.out.println(mp.getX() + " " + mp.getY() + " : " +
				// map[mp.getX()][mp.getY()].getFiretime());
			}
			// System.out.println(mp.getX() + " " + mp.getY() + " : " +
			// map[mp.getX()][mp.getY()].getFiretime());
		}

	}

	public int humanbfs() {

		while (!queue.isEmpty()) {

			MazePoint mp = queue.poll();

			if (mp.getVistit() != 1 && mp.getStep() < map[mp.getX()][mp.getY()].getFiretime()) {

				/*
				 * System.out.println("step:" + mp.getX() + " " + mp.getY() +
				 * " : " + map[mp.getX()][mp.getY()].getStep());
				 * System.out.println( "fire:" + mp.getX() + " " + mp.getY() +
				 * " : " + map[mp.getX()][mp.getY()].getFiretime());
				 */

				mp.setVisit(1);
				if (isEdge(mp.getX(), mp.getY())) {
					return mp.getStep() + 1;
				}
				if (isMazePoint(mp.getX() - 1, mp.getY()) && (map[mp.getX() - 1][mp.getY()].getType() == 0)) {
					queue.add(map[mp.getX() - 1][mp.getY()]);
					map[mp.getX() - 1][mp.getY()].setStep(map[mp.getX() - 1][mp.getY()].getStep() + 1);

				}
				if (isMazePoint(mp.getX(), mp.getY() - 1) && (map[mp.getX()][mp.getY() - 1].getType() == 0)) {
					queue.add(map[mp.getX()][mp.getY() - 1]);
					map[mp.getX()][mp.getY() - 1].setStep(map[mp.getX()][mp.getY()].getStep() + 1);

				}
				if (isMazePoint(mp.getX() + 1, mp.getY()) && (map[mp.getX() + 1][mp.getY()].getType() == 0)) {
					queue.add(map[mp.getX() + 1][mp.getY()]);
					map[mp.getX() + 1][mp.getY()].setStep(map[mp.getX()][mp.getY()].getStep() + 1);

				}
				if (isMazePoint(mp.getX(), mp.getY() + 1) && (map[mp.getX()][mp.getY() + 1].getType() == 0)) {
					queue.add(map[mp.getX()][mp.getY() + 1]);
					map[mp.getX()][mp.getY() + 1].setStep(map[mp.getX()][mp.getY()].getStep() + 1);
				}

			}
		}
		return -1;
	}

	public static void main(String[] args) {

		int[][] n = { { 3, 3, 3, 3 }, { 3, 1, 2, 3 }, { 3, 0, 0, 3 }, { 3, 0, 0, 3 } };

		MazewithFire mf = new MazewithFire(n);

		MazePoint mpfire = new MazePoint(1, 2, 2);
		mf.queue.add(mpfire);
		mf.firebfs();
		MazePoint mphuman = new MazePoint(1, 1, 1);
		mf.queue.add(mphuman);
		int a = mf.humanbfs();
		System.out.print(a);
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
		this.firetime = 0;
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
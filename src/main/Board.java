package main;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private Color[][][] board = new Color[4][4][4];//x,y,z
	private Point lastPutPoint = null;

	Color get(int x, int y, int z) {
		return board[x][y][z];
	}

	int getZ(int x, int y) {
		int z;
		for (z = 0; z < 4; z++) {
			if (board[x][y][z] == null) break;
		}
		return z;
	}

	boolean put(int x, int y, Color col) {
		int z = getZ(x, y);
		if (z > 3) return false;
		board[x][y][z] = col;
		lastPutPoint = new Point(x, y);
		return true;
	}

	boolean put(Point point, Color col) {
		return put(point.X, point.Y, col);
	}

	List<Point> canPutList() {
		List<Point> res = new ArrayList<>();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (board[x][y][3] == null) res.add(new Point(x, y));
			}
		}
		return res;
	}

	boolean isWin() {
		int X = lastPutPoint.X;
		int Y = lastPutPoint.Y;
		int Z = getZ(X, Y)-1;
		Color col = get(X, Y, Z);

		if (get(0, Y, Z) == col &&
		    get(1, Y, Z) == col &&
		    get(2, Y, Z) == col &&
		    get(3, Y, Z) == col   ) return true;

		if (get(X, 0, Z) == col &&
			get(X, 1, Z) == col &&
			get(X, 2, Z) == col &&
			get(X, 3, Z) == col   ) return true;

		if (get(X, Y, 0) == col &&
			get(X, Y, 1) == col &&
			get(X, Y, 2) == col &&
			get(X, Y, 3) == col   ) return true;

		if(X==Y && Y==Z) {
			if (get(0, 0, 0) == col &&
				get(1, 1, 1) == col &&
				get(2, 2, 2) == col &&
				get(3, 3, 3) == col   ) return true;
		}
		if(3-X==Y && Y==Z) {
			if (get(3, 0, 0) == col &&
				get(2, 1, 1) == col &&
				get(1, 2, 2) == col &&
				get(0, 3, 3) == col   ) return true;
		}
		if(X==3-Y && 3-Y==Z) {
			if (get(0, 3, 0) == col &&
				get(1, 2, 1) == col &&
				get(2, 1, 2) == col &&
				get(3, 0, 3) == col   ) return true;
		}
		if(X==Y && Y==3-Z) {
			if (get(0, 0, 3) == col &&
				get(1, 1, 2) == col &&
				get(2, 2, 1) == col &&
				get(3, 3, 0) == col   ) return true;
		}
		return false;
	}

	void print() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int z = 0; z < 4; z++) {
					Color c = get(x, y, z);
					String s = "-";
					if (c == Color.BLACK) s = "0";
					if (c == Color.WHITE) s = "1";
					System.out.print(s);
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
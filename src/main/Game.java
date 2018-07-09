package main;

public class Game {
	Player[] players = new Player[2];
	Board board;

	void setPlayers(Player p0, Player p1) {
		players[0] = p0;
		players[1] = p1;
	}

	void setBoard(Board b) {
		board = b;
	}

	Player play() {
		int t = 0;
		while (true) {
			if (turn(players[t])) return players[t];
			t %= 2;
		}
	}

	boolean turn(Player p) {
		p.put(board);
		board.print();
		System.out.println();
		if (board.isWin()) {
			System.out.println("win");
			return true;
		}
		return false;
	}
}

package main;

public class Main {

	public static void main(String args[]) {
//		GAPlayer p0 = new GAPlayer();
//		List<Integer> list = new ArrayList<Integer>(64);
//		for (int i = 0; i < 64; i++) list.add(i);
//		Collections.shuffle(list);
//		p0.setGene(list);
//		p0.printGene();

		NNPlayer p0 = new NNPlayer();

		Player p1 = new Player();

		p0.setColor(Color.BLACK);
		p1.setColor(Color.WHITE);

		Game game = new Game();
		game.setBoard(new Board());
		game.setPlayers(p0, p1);
		game.play();
	}
}

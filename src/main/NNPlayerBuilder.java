package main;

public class NNPlayerBuilder {

	public NNPlayer mkRand() {
		return new NNPlayer();
	}

	public NNPlayer mkChild(NNPlayer p0, NNPlayer p1) {
		double[][] w129 = new double[129][128];
		for (int i = 0; i < 129; i++) {
			for (int j = 0; j < 128; j++) {
				double r = Math.random();
				if (r < 0.02) {
					w129[i][j] = 2*Math.random() - 1;
				}else if (r < 0.51) {
					w129[i][j] = p0.weight129[i][j];
				}else {
					w129[i][j] = p1.weight129[i][j];
				}
			}
		}

		double[][] w16 = new double[16][128];
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 128; j++) {
				double r = Math.random();
				if (r < 0.02) {
					w16[i][j] = 2*Math.random() - 1;
				}else if (r < 0.51) {
					w16[i][j] = p0.weight16[i][j];
				}else {
					w16[i][j] = p1.weight16[i][j];
				}
			}
		}


	}
}

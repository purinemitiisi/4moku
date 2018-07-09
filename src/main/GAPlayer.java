package main;

import java.util.List;

public class GAPlayer extends Player {

	int[] gene = new int[64];

	@Override
	void put(Board b) {
		List<Point> list = b.canPutList();
		Point res = null;
		int max = -1;
		for (Point p : list) {
			int n = getGene(p.X, p.Y, b.getZ(p.X, p.Y));
			if (max < n) {
				max = n;
				res = p;
			}
		}
		b.put(res, color);
	}

	void setGene(List<Integer> list) {
		for (int i = 0; i < 64; i++)
			gene[i] = list.get(i);
	}

	int getGene(int x, int y, int z) {
		return gene[x*16+y*4+z];
	}

	void printGene() {
		for (int i = 0; i < 64; i++) {
			System.out.print(gene[i]+" ");
			if (i%4 == 3) System.out.print("|");
			if (i%16 == 15) System.out.println();
		}

	}


}

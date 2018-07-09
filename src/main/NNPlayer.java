package main;

import java.util.Arrays;

public class NNPlayer extends Player {
	int geneSize = 129; //is black:64 is white:64
	int nodeSize = 128;
	int outSize = 16;
	int[] gene = new int[129];
	double[]  node = new double[129];
	double[]  out = new double[16];
	double[][] weight129 = new double[129][128];
	double[][] weight16 = new double[16][128];


	NNPlayer() {
		for (int i = 0; i < 129; i++) {
			for (int j = 0; j < 128; j++) {
				weight129[i][j] = 2*Math.random()-1.0;
			}
		}

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 128; j++) {
				weight16[i][j] = 2*Math.random()-1.0;
			}
		}
	}

	NNPlayer(double[][] w129, double[][] w16) {
		weight129 = Arrays.copyOf(w129, 129);
		weight16  = Arrays.copyOf(w16 , 16 );
	}

	void setGene(Board b) {
		gene = new int[129];
		gene[0] = 1;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				for (int z = 0; z < 4; z++) {
					Color col = b.get(x, y, z);
					if (col == Color.BLACK) gene[ 1 + x*16+y*4+z] = 1;
					if (col == Color.WHITE) gene[65 + x*16+y*4+z] = 1;
				}
			}
		}
	}


	@Override
	void put(Board b) {
		setGene(b);
		double[] score = new double[16];
		for (int i = 0; i < 129; i++) {
			node[i] = ReLU(sum(gene, weight129[i]));
		}
		for (int i = 0; i < 16; i++) {
			out[i] = ReLU(sum(node, weight16[i]));
		}
		double max = 0;
		int num = 0;
		for (int i = 0; i < 16; i++) {
			double d = softmax(out,i);
			System.out.print(String.format("%.2f", d)+" ");
			if (i%4 == 3) System.out.println();
			if (d > max) {
				max = d;
				num = i;
			}
		}
		b.put(num%4, num/4, color);
	}

	double softmax(double[] node, int n) {
		double sum = 0;
		for (double d : node) {
			sum += Math.exp(d);
		}
		return Math.exp(node[n])/sum;
	}

	double ReLU(double d) {
		return Math.max(0, d);
	}

	double sum(double[] d, double[] w) {
		double res = 0;
		for (int n = 0; n < d.length; n++)
			res += d[n]*w[n];
		return res;
	}

	double sum(int[] d, double[] w) {
		double res = 0;
		for (int n = 0; n < d.length; n++)
			res += d[n]*w[n];
		return res;
	}

}

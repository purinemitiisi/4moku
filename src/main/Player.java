package main;

import java.util.List;

public class Player {
	Color color;

	void put(Board b) {
		List<Point> list = b.canPutList();
		Point point = list.get((int) (Math.random()*list.size()));
		b.put(point, color);
	}

	void setColor(Color c) {
		color = c;
	}
}

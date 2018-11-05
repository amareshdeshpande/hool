package com.hool.app.misc;

public class Direction {
	private static final String[] directions = new String[5];

	public static final int north = 0;
	public static final int east = 1;
	public static final int south = 2;
	public static final int west = 3;
	public static final int full_hand = 4;

	public static String toString(int direction) {
		directions[0] = "north";
		directions[1] = "east";
		directions[2] = "south";
		directions[3] = "west";
		directions[4] = "Full Hand";

		return directions[direction];

	}
}

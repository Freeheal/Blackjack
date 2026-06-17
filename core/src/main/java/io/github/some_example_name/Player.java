package io.github.some_example_name;

public class Player {
	private int geld;
	private String name;
	private int streak;

	public Player(String name) {
		this.name = name;
		geld = 1000;
	}

	public void setGeld(int einsatz) {
		geld += einsatz;
	}

	public int getGeld() {
	return geld;
	}

}

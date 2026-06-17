package io.github.some_example_name;

public class Player {
	private int geld;
	private String name;
	private int streak;

	public Player(String name) {
		this.name = name;
		geld = 1000;
	}

	public void addGeld(int einsatz) { geld += einsatz; }
	public int getGeld() { return geld; }
    public int getStreak (){return this.streak;}
    public void setStreak (int i){this.streak = i;}
    public String getName (){return this.name;}
    public void setName (String i){this.name = i;}

}

package io.github.some_example_name;

import java.util.*;

import io.github.some_example_name.Karte.bild;
import io.github.some_example_name.Karte.typ;

public class Blackjack {
	private int einsatz;
	private int playerWert;
	private int dealerWert;
	Stack<Karte> stapel = new Stack<>();
	ArrayList<Karte> playerHand = new ArrayList<>();
	ArrayList<Karte> dealerHand = new ArrayList<>();

	public Blackjack() {

		// Herz
		stapel.push(new Karte(2, typ.Herz, bild.Zahl));
		stapel.push(new Karte(3, typ.Herz, bild.Zahl));
		stapel.push(new Karte(4, typ.Herz, bild.Zahl));
		stapel.push(new Karte(5, typ.Herz, bild.Zahl));
		stapel.push(new Karte(6, typ.Herz, bild.Zahl));
		stapel.push(new Karte(7, typ.Herz, bild.Zahl));
		stapel.push(new Karte(8, typ.Herz, bild.Zahl));
		stapel.push(new Karte(9, typ.Herz, bild.Zahl));
		stapel.push(new Karte(10, typ.Herz, bild.Zahl));
		stapel.push(new Karte(10, typ.Herz, bild.Bube));
		stapel.push(new Karte(10, typ.Herz, bild.Dame));
		stapel.push(new Karte(10, typ.Herz, bild.Koenig));
		stapel.push(new Karte(11, typ.Herz, bild.Ass));

		// Karo
		stapel.push(new Karte(2, typ.Karo, bild.Zahl));
		stapel.push(new Karte(3, typ.Karo, bild.Zahl));
		stapel.push(new Karte(4, typ.Karo, bild.Zahl));
		stapel.push(new Karte(5, typ.Karo, bild.Zahl));
		stapel.push(new Karte(6, typ.Karo, bild.Zahl));
		stapel.push(new Karte(7, typ.Karo, bild.Zahl));
		stapel.push(new Karte(8, typ.Karo, bild.Zahl));
		stapel.push(new Karte(9, typ.Karo, bild.Zahl));
		stapel.push(new Karte(10, typ.Karo, bild.Zahl));
		stapel.push(new Karte(10, typ.Karo, bild.Bube));
		stapel.push(new Karte(10, typ.Karo, bild.Dame));
		stapel.push(new Karte(10, typ.Karo, bild.Koenig));
		stapel.push(new Karte(11, typ.Karo, bild.Ass));

		// Kreuz
		stapel.push(new Karte(2, typ.Karo, bild.Zahl));
		stapel.push(new Karte(3, typ.Karo, bild.Zahl));
		stapel.push(new Karte(4, typ.Karo, bild.Zahl));
		stapel.push(new Karte(5, typ.Karo, bild.Zahl));
		stapel.push(new Karte(6, typ.Karo, bild.Zahl));
		stapel.push(new Karte(7, typ.Karo, bild.Zahl));
		stapel.push(new Karte(8, typ.Karo, bild.Zahl));
		stapel.push(new Karte(9, typ.Karo, bild.Zahl));
		stapel.push(new Karte(10, typ.Karo, bild.Zahl));
		stapel.push(new Karte(10, typ.Karo, bild.Bube));
		stapel.push(new Karte(10, typ.Karo, bild.Dame));
		stapel.push(new Karte(10, typ.Karo, bild.Koenig));
		stapel.push(new Karte(11, typ.Karo, bild.Ass));

		// Pik
		stapel.push(new Karte(2, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(3, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(4, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(5, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(6, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(7, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(8, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(9, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(10, typ.Kreuz, bild.Zahl));
		stapel.push(new Karte(10, typ.Kreuz, bild.Bube));
		stapel.push(new Karte(10, typ.Kreuz, bild.Dame));
		stapel.push(new Karte(10, typ.Kreuz, bild.Koenig));
		stapel.push(new Karte(11, typ.Kreuz, bild.Ass));

		Collections.shuffle(stapel);
		ziehenPlayer(playerHand);
		ziehenDealer(dealerHand);
	}

	public void ziehenPlayer(ArrayList<Karte> hand) {
		hand.add(stapel.pop());
		hand.add(stapel.pop());
	}

	public void ziehenDealer(ArrayList<Karte> hand) {
		hand.add(stapel.pop());
		hand.add(stapel.pop()); // Muss vorerst verdeckt vorliegen
	}

	public void hit(ArrayList<Karte> hand) {
		hand.add(stapel.pop());
		update();
	}

	public int verdoppeln(ArrayList<Karte> hand, int einsatz) {
		hand.add(stapel.pop());
		return einsatz * 2;
	}

	public void setEinsatz(int wert) {
		einsatz = wert;
	}

	public void stand() {
		dealer();
	}

	public void dealer() {
		if (dealerWert >= 17)
			return;
		hit(dealerHand);
		update();
		dealer();
	}

	public int getWert(ArrayList<Karte> hand) {
		int res = 0;
		int count = 0;
		for (var i : hand) {
			if (i.getFoto() == bild.Ass)
				count++;
			res += i.getWert();
		}
		while (count > 0 && res > 21) {
			count--;
			res -= 10;
		}
		return res;
	}

	public void update() {
		playerWert = getWert(playerHand);
		dealerWert = getWert(dealerHand);
		if (playerWert == 21)
			return; // methode !
		if (dealerWert == 21)
			return;

		if (playerWert > 21)
			return;
		if (dealerWert > 21)
			return;
	}
	public void resumeee(ArrayList<Karte> hand) {
		
		
	}

}

package io.github.some_example_name;

import java.util.*;

public class Blackjack {
	enum WinStates {
		Won, Lost, Equal, Playing;
    @Override
    public String toString() {
        switch (this) {
            case Won: return "Won";
            case Lost: return "Lost";
            case Equal:  return "Equal";
            case Playing:  return "Playing";
            default:    return name();
        }
    }
}

	private int einsatz;
	//private int playerWert;
	//private int dealerWert;
	private boolean playerturn = true;
	private WinStates winstate = WinStates.Playing;
	Stack<Karte> stapel = new Stack<>();
	ArrayList<Karte> playerHand = new ArrayList<>();
	ArrayList<Karte> dealerHand = new ArrayList<>();
	Player player = new Player("Phillip");

	public Blackjack() {

		// Herz
        stapelInit();

		ziehenPlayer(playerHand);
		ziehenDealer(dealerHand);
	}

    private void stapelInit(){
		for (int i = 2; i < 10; i++) {
			stapel.push(new Karte(i, Karte.typ.Herz, Karte.bild.Zahl));
		}
		stapel.push(new Karte(10, Karte.typ.Herz, Karte.bild.Zahl));
		stapel.push(new Karte(10, Karte.typ.Herz, Karte.bild.Bube));
		stapel.push(new Karte(10, Karte.typ.Herz, Karte.bild.Dame));
		stapel.push(new Karte(10, Karte.typ.Herz, Karte.bild.Koenig));
		stapel.push(new Karte(11, Karte.typ.Herz, Karte.bild.Ass));

		// Karo
		for (int i = 2; i < 10; i++) {
			stapel.push(new Karte(i, Karte.typ.Karo, Karte.bild.Zahl));
		}
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Zahl));
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Bube));
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Dame));
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Koenig));
		stapel.push(new Karte(11, Karte.typ.Karo, Karte.bild.Ass));

		// Kreuz
		for (int i = 2; i < 10; i++) {
			stapel.push(new Karte(i, Karte.typ.Karo, Karte.bild.Zahl));
		}
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Zahl));
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Bube));
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Dame));
		stapel.push(new Karte(10, Karte.typ.Karo, Karte.bild.Koenig));
		stapel.push(new Karte(11, Karte.typ.Karo, Karte.bild.Ass));

		// Pik
		for (int i = 2; i < 10; i++) {
			stapel.push(new Karte(i, Karte.typ.Kreuz, Karte.bild.Zahl));
		}
		stapel.push(new Karte(10, Karte.typ.Kreuz, Karte.bild.Zahl));
		stapel.push(new Karte(10, Karte.typ.Kreuz, Karte.bild.Bube));
		stapel.push(new Karte(10, Karte.typ.Kreuz, Karte.bild.Dame));
		stapel.push(new Karte(10, Karte.typ.Kreuz, Karte.bild.Koenig));
		stapel.push(new Karte(11, Karte.typ.Kreuz, Karte.bild.Ass));

		Collections.shuffle(stapel);

    }
	public void ziehenPlayer(ArrayList<Karte> hand) {
		hand.add(stapel.pop());
		hand.add(stapel.pop());
	}

	public void ziehenDealer(ArrayList<Karte> hand) {
		hand.add(stapel.pop());
		hand.add(stapel.pop()); // Muss vorerst verdeckt vorliegen
		hand.getFirst().setSichtbar(false);
	}

	public void hit(ArrayList<Karte> hand) {
		hand.add(stapel.pop());
		checkLoss();
	}

	public void verdoppeln(ArrayList<Karte> hand) {
		playerturn = false;
		hand.add(stapel.pop());
		player.addGeld(-einsatz); // Spieler setzt nochmal den gleichen Einsatz
		this.einsatz *= 2; // theoretischen Gewinn verdoppeln
		endRound();

	}
    public Player getPlayer (){return this.player;}
    public void setPlayer (Player i ){this.player = i;}

	public boolean setEinsatz(int wert) {
		if (wert > player.getGeld())
			return false;
		einsatz = wert;
		player.addGeld(-wert); // Wert wird vom aktuellen Geld des Spielers abgezogen
		return true;
	}

	public int getEinsatz() {
		return this.einsatz;
	}

	public void stand() {
		playerturn = false;
		dealerHand.getFirst().setSichtbar(true);
		endRound();
	}

	public void dealer() {
		if (getWert(dealerHand) >= 17)
			return;
		hit(dealerHand);
		// update();
		dealer();
	}

	public int getHiddenWert(ArrayList<Karte> hand) {
		int res = 0;
		int count = 0;
		for (var i : hand) {
			if (i.getFoto() == Karte.bild.Ass)
				count++;
			res += i.getWert();
		}
		while (count > 0 && res > 21) {
			count--;
			res -= 10;
		}
		return res;
	}

	public int getWert(ArrayList<Karte> hand) {
		int res = 0;
		int count = 0;
		for (var i : hand) {
			if (!i.getSichtbar())
				continue;
			if (i.getFoto() == Karte.bild.Ass)
				count++;
			res += i.getWert();
		}
		while (count > 0 && res > 21) {
			count--;
			res -= 10;
		}
		return res;
	}

	private void checkLoss() {
		if (getWert(playerHand) > 21) {
			winstate = WinStates.Lost;
			playerturn = false;
			dealerHand.getFirst().setSichtbar(true);
			;
		}
	}
    public WinStates getWinState()  {
        return winstate;
    }

	public WinStates checkWin() {
		var d = getHiddenWert(dealerHand);
		var p = getHiddenWert(playerHand);

		if (p > 21)
			return WinStates.Lost;
		if (d > 21)
			return WinStates.Won;

		if (p > d)
			return WinStates.Won;
		else if (p < d)
			return WinStates.Lost;
		else
			return WinStates.Equal;
	}

	public WinStates endRound() {
		dealer();
		winstate = checkWin();
		if (winstate == WinStates.Equal) {
			player.addGeld(einsatz); // Spieler bekommt Einsatz zurück
            player.setStreak(0); // TODO evtl anders
            return WinStates.Equal;
		}
        else if (winstate == WinStates.Won) {
			player.addGeld(einsatz); // Spieler erhält das Doppelte
            player.setStreak(player.getStreak() + 1);
            return WinStates.Won;
		}
        else {
			player.addGeld(-1 *  einsatz); // Spieler verliert Einsatz
            player.setStreak(0);
            return WinStates.Lost;
        }
	}

	public void newRound() {
		//einsatz = 100;
        setEinsatz(100);
        //player.addGeld(-einsatz);
		//for (Karte i : playerHand) { // Reset des ursprünglichen Kartendecks
		//	stapel.push(i);
		//}
		//for (Karte i : dealerHand) {
		//	stapel.push(i);
		//}
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        stapelInit();
        ziehenPlayer(playerHand);
        ziehenDealer(dealerHand);

		Collections.shuffle(stapel); // neues Deck ist bereit
		winstate = WinStates.Playing;
		playerturn = true;
	}
}

import java.util.Scanner;
import java.util.ArrayList;

public class Haupt {

	static boolean playerturn = true;

	public static void main (String []args) { 
		Scanner scanner = new Scanner(System.in);
		while (true) {
			Blackjack b1 = new Blackjack();
			System.out.print("\u001B[2J\u001B[1;1H");
			System.out.println("INPUT START BET : ");
			var s = scanner.nextInt();
			assert(s >= 0);
			b1.setEinsatz(s);
			System.out.println("EINSATZ : " + b1.getEinsatz());
			loop(scanner,b1);
			scanner.next();
			playerturn = true;
		}
	}
	public static void draw_output(Blackjack b1) {
		System.out.print("\u001B[2J\u001B[1;1H");

		System.out.println("EINSATZ: "+b1.getEinsatz());
		var playerHand = "";
		for (int i = 0 ; i < b1.playerHand.size() ; i++) { 
			playerHand+= b1.playerHand.toArray()[i] + " | ";
		}
		var dealerHand = "";
		for (int i = 0 ; i <b1.dealerHand.size() ; i++) { 
			dealerHand+= b1.dealerHand.toArray()[i] + " | ";
		}
		var wert = b1.getWert(b1.playerHand);
		System.out.println("PLAYER HAND : "+playerHand  + " WERT :" + wert);
		wert = b1.getWert(b1.dealerHand);
		System.out.println("DEALER HAND : "+dealerHand  + " WERT :" + wert);

	}
	public static void loop(Scanner scanner,Blackjack b1){
		while (true) {
			if (playerturn) { 
				draw_output(b1);
				var s = scanner.next();

				switch (s) {
				case "hit" : 
					b1.hit(b1.playerHand);
					if (b1.getWert(b1.playerHand) > 21){
						playerturn = false;
						b1.dealerHand.getFirst().setSichtbar(true);
					}
					break;
				case "double":
					b1.verdoppeln(b1.playerHand,b1.getEinsatz());
					if (b1.getWert(b1.playerHand) > 21){
						playerturn = false;
						b1.dealerHand.getFirst().setSichtbar(true);
					}
					break;
				case "stand":
					b1.stand();
					playerturn = false;
					b1.dealerHand.getFirst().setSichtbar(true);
					break;
				default:
					break;
				}
			}else { 
				draw_output(b1);
				Blackjack.WinStates c = b1.checkWin();
				if (c == Blackjack.WinStates.Won)
					System.out.println("WON");
				else if(c == Blackjack.WinStates.Lost) 
					System.out.println("LOST");
				else 
					System.out.println("EQUAL");
				return;
			}
		}

	}

}


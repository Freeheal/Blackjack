package io.github.some_example_name;

public class Karte {

	enum typ {
		Karo, Pik, Herz, Kreuz
	}
	
	enum bild {
		Ass, Koenig, Dame, Bube, Zahl
	}


	private int wert;
    typ symbol;
    bild foto;

	public Karte(int wert, typ symbol, bild foto) {
		this.wert = wert;
		this.symbol = symbol;
		this.foto = foto;
	}

}

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
    
    public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public typ getSymbol() {
		return symbol;
	}

	public void setSymbol(typ symbol) {
		this.symbol = symbol;
	}

	public bild getFoto() {
		return foto;
	}

	public void setFoto(bild foto) {
		this.foto = foto;
	}


	public Karte(int wert, typ symbol, bild foto) {
		this.wert = wert;
		this.symbol = symbol;
		this.foto = foto;
	}

}

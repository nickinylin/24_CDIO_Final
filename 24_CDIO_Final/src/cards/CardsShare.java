package cards;

public class CardsShare extends Cards {
	private int value;

	// public CardsTransaction(String titel, String cardtext, int value) - Dette
	// var hvad der stod før.
	public CardsShare(String cardtext, int value) {

		// this.titel=titel;
		this.cardtext = cardtext;
		this.value = value;
	}

	public int getvalue() {
		return value;

	}

}

package cards;

public class CardsTransaction extends Cards {
	private int value;

	// public CardsTransaction(String titel, String cardtext, int value) - Dette
	// var hvad der stod før.
	public CardsTransaction(String cardtext, int value) {

		// this.titel=titel;
		this.cardtext = cardtext;
		this.value = value;
	}

	public int getvalue() {
		return value;

	}

}

package game;

public class CardsMoveto  extends Cards{
private int destination;
private String destinationName;


// public CardsMoveto(String titel, String cardtext, int destination) - Dette var hvad der stod før.
public CardsMoveto(String cardtext, int destination){

//	this.titel=titel;
	this.cardtext=cardtext;
	this.destination=destination;
}

public CardsMoveto(String cardtext, String destination){
	this.cardtext=cardtext;
	this.destinationName=destination;
}

public void play(){
	
	
}



}

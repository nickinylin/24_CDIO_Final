package game;

public class CardsMoveto  extends Cards{
private int moves;
private String destination;


// public CardsMoveto(String titel, String cardtext, int destination) - Dette var hvad der stod før.
public CardsMoveto(String cardtext, int destination){

//	this.titel=titel;
	this.cardtext=cardtext;
	moves=destination;
}

public CardsMoveto(String cardtext, String destination){
	this.cardtext=cardtext;
	this.destination=destination;
}
public int getExtraMoves(){
	return moves;
}
public String getDestination(){
	return destination;
}
//public void play(Player player){
//	if (destinationName != null){
//		//move player to field containing destinationName
//	}
//	else{
//		player.movePlayer(player, destination);
//	}
//	
//}



}

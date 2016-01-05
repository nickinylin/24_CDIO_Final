package game;

import java.util.Arrays;
import java.util.Collections;

import kortdeck.GetOutofJail;

public class CardsDeck {
private Cards[] deck;
private Cards[] discard;
private int currentdecksize;
private int discardsize;
public CardsDeck(){
	deck = new Cards[]{
		new CardsTransaction(),
		new GetOutofJail(),
		new CardsMoveto()
	};
	discard = new Cards[deck.length];
	currentdecksize=deck.length;
	discardsize=0;
}

	//Discard kan fjernes og shuffledeck kan simplificeres hvis Get out of jail kortene fjernes.
	
	
	public Cards drawcard(){
	Cards card=deck[currentdecksize];
	deck[currentdecksize]=null;
currentdecksize=currentdecksize-1;
	return card;
	}
	public void shuffledeck(){
		while (discardsize>0){
			deck[deck.length-discardsize]=discard[deck.length-discardsize];
			discard[deck.length-discardsize]=null;
			discardsize--;
		}
		java.util.List<?> lists = (java.util.List<?>) Arrays.asList(deck);
        Collections.shuffle(lists);
		
		
	}
	
	public void discardcard(Cards card){
		discard[deck.length-discardsize]=card;
		discardsize++;	
	}
}


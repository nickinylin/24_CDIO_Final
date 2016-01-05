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
			new Transaction("aktier", "Modtag udbytte af Deres aktier, modtag 1000kr", 1000),
			new Transaction("aktier", "Modtag udbytte af Deres aktier, modtag 1000kr.", 1000),
			new Transaction("Klasselotteri", "De har vundet i Klasselotteriet, modtag 500kr.", 500),
			new Transaction("Klasselotteri", "De har vundet i Klasselotteriet, modtag 500kr.", 500),
			new Transaction("Præmieobligation.", "Deres præmieobligation er udtrukket. De modtager 1000kr.", 1000),
			new Transaction("Præmieobligation.", "Deres præmieobligation er udtrukket. De modtager 1000kr.", 1000),
			new Transaction("Avl.", "Værdien af egen avl fra nyttehaven udgør 200kr, som De modtager af banken.", 200),
			new Transaction("Kvartal skat.", "Kommunen har eftergivet et kvartals skat. Hæv i banken 3000kr.", 3000),
			new Transaction("Tipning.", "De havde en række med elleve rigtige i tipning. Modtag 1000kr.", 1000),
			new Transaction("Dyrtiden.", "Grundet dyrtiden har de fået gageforhøjelse. Modtag 1000kr.", 1000),
			new Transaction("Møbler.", "De har solgt nogle gamle møbler på auktion, og modtager 1000kr.", 1000),
			new Transaction("Aktieudbytte.", "De modtager deres aktieudbytte. Modtag 1000kr af banken", 1000),
			
			new Transaction("Bilforsikring.", "Betal deres bilforsikring.", -1000),
			new Transaction("Told.", "De har været en tur i udlandet og har haft for mange cigaretter med hjem. Betal told 200kr.", -200),
			new Transaction("Parkeringsbæde.", "De har fået en parkeringsbøde. Betal 200kr.", -200),
			new Transaction("Øl levering.", "Betal 200kr for levering af 2 kasser øl.", -200),
			new Transaction("Fuldt stop.", "De har kørt frem for fuldt stop, betal 1000kr i bøde.", -1000),
			new Transaction("Tandlægeregning.", "De har modtaget Deres tandlægeregning, betal 2000kr.", -2000),
			new Transaction("Dæk.", "De har købt 4 nye dæk til Deres vogn, betal 1000kr.", -1000),
			new Transaction("Reparation.", "Betal 3000kr for reparation af Deres vogn.", -3000),
			new Transaction("Reparation.", "Betal 3000kr for reparation af Deres vogn.", -3000),
			new Transaction("Vognvask & smøring.", "Betal 300kr for vognvask og smøring.", -300),
			new Transaction("Ejendomsskat.", "Ejendomsskatterne er steget. Ekstraudgifterne er: 800kr pr. hus. 2300kr pr. hotel.", -800, 2300),
			new Transaction("Oliepris.", "Oliepriserne er steget og De skal betale: 500kr pr. hus. 2000kr pr. hotel.", -500, 2000),
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


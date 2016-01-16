package cards;

import java.util.Arrays;
import java.util.Collections;

import controllers.Language;

public class CardsDeck {
	private Cards[] deck;
	private Cards[] discard;
	private int currentdecksize;
	private int discardsize;

	public CardsDeck() {
		deck = new Cards[] {

				// Nedenfor er alle kort der medfører at man modtager penge.
				new CardsTransaction(Language.cards_transaction1, 1000),
				new CardsTransaction(Language.cards_transaction1, 1000),
				new CardsTransaction(Language.cards_transaction2, 500),
				new CardsTransaction(Language.cards_transaction2, 500),
				new CardsTransaction(Language.cards_transaction3, 1000),
				new CardsTransaction(Language.cards_transaction3, 1000),
				new CardsTransaction(Language.cards_transaction4, 200),
				new CardsTransaction(Language.cards_transaction5, 3000),
				new CardsTransaction(Language.cards_transaction6, 1000),
				new CardsTransaction(Language.cards_transaction7, 1000),
				new CardsTransaction(Language.cards_transaction8, 1000),
				new CardsTransaction(Language.cards_transaction9, 1000),

				// Nedenfor er alle kort der medfører at man betaler penge.
				new CardsTransaction(Language.cards_transaction10, -1000),
				new CardsTransaction(Language.cards_transaction11, -200),
				new CardsTransaction(Language.cards_transaction12, -200),
				new CardsTransaction(Language.cards_transaction13, -200),
				new CardsTransaction(Language.cards_transaction14, -1000),
				new CardsTransaction(Language.cards_transaction15, -2000),
				new CardsTransaction(Language.cards_transaction16, -1000),
				new CardsTransaction(Language.cards_transaction17, -3000),
				new CardsTransaction(Language.cards_transaction17, -3000),
				new CardsTransaction(Language.cards_transaction18, -300),

				new CardsShare(Language.cards_share1, 500),
				new CardsShare(Language.cards_share2, 200),
				new CardsShare(Language.cards_share3, 500),
				new CardsLegat(Language.cards_legat, 40000),

				// new GetOutofJail(),
				new CardsMoveto(Language.cards_moveTo1, 3),
				new CardsMoveto(Language.cards_moveTo1, 3),
				new CardsMoveto(Language.cards_moveTo2, -3),
				new CardsMoveto(Language.cards_moveTo2, -3),

				new CardsMoveto(Language.cards_moveTo3, Language.field_Start), 
				new CardsMoveto(Language.cards_moveTo3, Language.field_Start),

				new CardsMoveto(Language.cards_moveTo4, Language.field_VisitJail),
				new CardsMoveto(Language.cards_moveTo4, Language.field_VisitJail),

				new CardsMoveto(Language.cards_moveTo5, Language.field_Strandvejen),
				new CardsMoveto(Language.cards_moveTo6, Language.field_Groenningen),
				new CardsMoveto(Language.cards_moveTo7, Language.field_Vimmelskaftet),
				new CardsMoveto(Language.cards_moveTo8, Language.field_FrederiksbergAlle),
				new CardsMoveto(Language.cards_moveTo9, Language.field_Raadhuspladsen),

				new CardsMoveto(Language.cards_moveTo10,	Language.field_Fleet2),

				new CardsMoveto(Language.cards_moveTo11, "fleet"),
				new CardsMoveto(Language.cards_moveTo11, "fleet"),


		};

		shuffledeck();
		discard = new Cards[deck.length];
		currentdecksize = deck.length-1;
		discardsize = 0;
	}

	// Discard kan fjernes og shuffledeck kan simplificeres hvis Get out of jail
	// kortene fjernes.

	public Cards drawcard() {
		if(currentdecksize==0)
			shuffledeck();

		Cards card = deck[currentdecksize];
		currentdecksize = currentdecksize - 1;
		return card;
	}

	public void shuffledeck() {
			currentdecksize=deck.length-1;
		java.util.List<?> lists = (java.util.List<?>) Arrays.asList(deck);
		Collections.shuffle(lists);

	}

//	public void discardcard(Cards card) {
//		discard[deck.length - discardsize] = card;
//		discardsize++;
//	}
	public Cards drawspecifik(int type){
		Cards card = drawcard();
		while(true)
switch(type){
// transaction (type 1)
case 1:
	if (card instanceof CardsTransaction){
				return card;
	}
		card=drawcard();
	break;	

// Share (type 2)
case 2:
	if (card instanceof CardsShare){
				return card;
	}
		card=drawcard();
		break;
		
// Legat (type 3)
case 3:
	if (card instanceof CardsLegat){
		return card;
}
card=drawcard();
break;

// move to destination (type 4)
case 4:
	if (card instanceof CardsMoveto){
		CardsMoveto tempcard=(CardsMoveto)card;
		if(tempcard.getExtraMoves()==0 && !tempcard.getDestination().equals("fleet")&& !tempcard.getDestination().equals(Language.field_VisitJail))
		return card;
}
card=drawcard();
break;

// move extra (type 5)
case 5:
if (card instanceof CardsMoveto){
	CardsMoveto tempcard=(CardsMoveto)card;
	if(tempcard.getExtraMoves()!=0)
	return card;
}
card=drawcard();
break;

// move to nearest fleet (type 6)
case 6:
if (card instanceof CardsMoveto){
	CardsMoveto tempcard=(CardsMoveto)card;
	if(tempcard.getExtraMoves()==0 && tempcard.getDestination().equals("fleet")&& !tempcard.getDestination().equals(Language.field_VisitJail)){
	return card;
	}
}
card=drawcard();
break;

//move to jail (type 7)
case 7:
if (card instanceof CardsMoveto){
	CardsMoveto tempcard=(CardsMoveto)card;
	if(tempcard.getExtraMoves()==0 && !tempcard.getDestination().equals("fleet")&& tempcard.getDestination().equals(Language.field_VisitJail))
	return card;
}
card=drawcard();
break;
}
	}
}

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

				new CardsMoveto(Language.cards_moveTo4, "De fængsles"),
				new CardsMoveto(Language.cards_moveTo4, "De fængsles"),

				new CardsMoveto(Language.cards_moveTo5, Language.field_Strandvejen),
				new CardsMoveto(Language.cards_moveTo6, Language.field_Groenningen),
				new CardsMoveto(Language.cards_moveTo7, Language.field_Vimmelskaftet),
				new CardsMoveto(Language.cards_moveTo8, Language.field_FrederiksbergAlle),
				new CardsMoveto(Language.cards_moveTo9, Language.field_Raadhuspladsen),

				new CardsMoveto(Language.cards_moveTo10,	Language.field_Fleet2),

				new CardsMoveto(Language.cards_moveTo11, "fleet"),
				new CardsMoveto(Language.cards_moveTo11, "fleet"),


		};

//		shuffledeck();
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
		deck[currentdecksize] = null;
		currentdecksize = currentdecksize - 1;
		return card;
	}

	public void shuffledeck() {
		while (discardsize > 0) {
			deck[deck.length - discardsize] = discard[deck.length - discardsize];
			discard[deck.length - discardsize] = null;
			discardsize--;
		}
		java.util.List<?> lists = (java.util.List<?>) Arrays.asList(deck);
		Collections.shuffle(lists);

	}

	public void discardcard(Cards card) {
		discard[deck.length - discardsize] = card;
		discardsize++;
	}
}

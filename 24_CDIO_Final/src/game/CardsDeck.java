package game;

import java.util.Arrays;
import java.util.Collections;


public class CardsDeck {
private Cards[] deck;
private Cards[] discard;
private int currentdecksize;
private int discardsize;

public CardsDeck(){
	deck = new Cards[]{

			new CardsTransaction("Modtag udbytte af Deres aktier, modtag 1000kr", 1000),
			new CardsTransaction("Modtag udbytte af Deres aktier, modtag 1000kr", 1000),
			new CardsTransaction("De har vundet i Klasselotteriet, modtag 500kr", 500),
			new CardsTransaction("De har vundet i Klasselotteriet, modtag 500kr", 500),
			new CardsTransaction("Deres præmieobligation er udtrukket. De modtager 1000kr", 1000),
			new CardsTransaction("Deres præmieobligation er udtrukket. De modtager 1000kr", 1000),
			new CardsTransaction("Værdien af egen avl fra nyttehaven udgør 200kr, som De modtager af banken", 200),
			new CardsTransaction("Kommunen har eftergivet et kvartals skat. Hæv i banken 3000kr", 3000),
			new CardsTransaction("De havde en række med elleve rigtige i tipning. Modtag 1000kr", 1000),
			new CardsTransaction("Grundet dyrtiden har de fået gageforhøjelse. Modtag 1000kr", 1000),
			new CardsTransaction("De har solgt nogle gamle møbler på auktion, og modtager 1000kr", 1000),
			new CardsTransaction("De modtager deres aktieudbytte. Modtag 1000kr af banken", 1000),
			
			new CardsTransaction("Betal deres bilforsikring", -1000),
			new CardsTransaction("De har været en tur i udlandet og har haft for mange cigaretter med hjem. Betal told 200kr", -200),
			new CardsTransaction("De har fået en parkeringsbøde. Betal 200kr", -200),
			new CardsTransaction("Betal 200kr for levering af 2 kasser øl", -200),
			new CardsTransaction("De har kørt frem for fuldt stop, betal 1000kr i bøde", -1000),
			new CardsTransaction("De har modtaget Deres tandlægeregning, betal 2000kr", -2000),
			new CardsTransaction("De har købt 4 nye dæk til Deres vogn, betal 1000kr", -1000),
			new CardsTransaction("Betal 3000kr for reparation af Deres vogn", -3000),
			new CardsTransaction("Betal 3000kr for reparation af Deres vogn", -3000),
			new CardsTransaction("Betal 300kr for vognvask og smøring", -300),
			
			// Er slashed ud indtil vi har fundet en løsning
//			new CardsTransaction("Ejendomsskatterne er steget. Ekstraudgifterne er: 800kr pr. hus. 2300kr pr. hotel.", -800*amountOfHouses, 2300*amountOfHotels),
//			new CardsTransaction("Oliepriserne er steget og De skal betale: 500kr pr. hus. 2000kr pr. hotel.", -500, 2000),,
//			new CardsTransaction("De skal holde familiefest og får et tilskud fra hver medspiller på kr. 500", 500*amountofplayers);
//			new CardsTransaction("Det er Deres fødselsdag. Modtag af hver medspiller kr. 200", 200*amountofplayers);
//			new CardsTransaction("De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver spiller kr. 500", 500*amountofplayers);
//			new CardsTransaction("De modtager Matador-legatet for værdigt trængende på kr. 40.000. Ved værdigt trængende forstås, at Deres formue, dvs. Deres kantante penge + skøder + bygninger, ikke overstiger kr. 15.000", if(formue<15000){
//				formue = formue+40000)}
//			new GetOutofJail(),
			new CardsMoveto("Ryk 3 felter frem", 3),
			new CardsMoveto("Ryk 3 felter frem", 3),
			new CardsMoveto("Ryk 3 felter tilbage", -3),
			new CardsMoveto("Ryk 3 felter tilbage", -3),
			
			new CardsMoveto("Ryk frem til START", "Start"),
			new CardsMoveto("Ryk frem til START", "Start"),
			
			new CardsMoveto("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4000", "På besøg i fængsel"),
			new CardsMoveto("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4000", "På besøg i fængsel"),
			
			new CardsMoveto("Ryk frem til Strandvejen. Hvis de passerer START indkassér da kr. 4000", "Strandvejen"),
			new CardsMoveto("Ryk frem til Grønningen. Hvis De passerer START, indkassér da kr. 4000", "Grønningen"),
			new CardsMoveto("Ryk frem til Vimmelskaftet. Hvis De passerer START, indkassér da kr. 4000", "Vimmelskaftet"),
			new CardsMoveto("Ryk frem til Frederiksberg Allé. Hvis De passerer START, indkassér da kr. 4000", "Frederiksberg Allé"),
			new CardsMoveto("Tag ind på rådhuspladsen", "Rådhuspladsen"),
			
			new CardsMoveto("Tag med Mols-linien. Flyt brikken frem, og hvis de passerer START, indkassér da kr. 4000", "Mols-linien")
			
//			new CardsMoveto("Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berretiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken", nærmeste rederi),
//			new CardsMoveto("Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berretiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken", nærmeste rederi),
//			new CardsMoveto("Tag med den nærmeste færge. Flyt brikken frem, og hvis De passerer START indkassér da kr. 4000", nærmeste færge),
//			new GetOutofJail()
	};
	
	discard = new Cards[deck.length];
	currentdecksize=deck.length;
	discardsize=0;
}

	//Discard kan fjernes og shuffledeck kan simplificeres hvis Get out of jail kortene fjernes.
	
	
	public Cards drawcard(Player player){
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


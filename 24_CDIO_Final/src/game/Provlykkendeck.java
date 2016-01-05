package game;

import java.util.Arrays;
import java.util.Collections;

import kortdeck.GetOutofJail;

public class Provlykkendeck {
private Kort[] deck;
private Kort[] discard;
private int currentdecksize;
private int discardsize;
public Provlykkendeck(){
	deck = new Kort[]{
		new Transaction("De skal holde familiefest og får et tilskud fra hver medspiller på kr. 500", 500*amountofplayers);
		new Transaction("Det er Deres fødselsdag. Modtag af hver medspiller kr. 200", 200*amountofplayers);
		new Transaction("De har lagt penge ud til et sammenskudsgilde. Mærkværdigvis betaler alle straks. Modtag fra hver spiller kr. 500", 500*amountofplayers);
		new Transaction("De modtager Matador-legatet for værdigt trængende på kr. 40.000. Ved værdigt trængende forstås, at Deres formue, dvs. Deres kantante penge + skøder + bygninger, ikke overstiger kr. 15.000", if(formue<15000){
			formue = formue+40000)}
//		new GetOutofJail(),
		new Moveto("Ryk 3 felter frem", 3);
		new Moveto("Ryk 3 felter tilbage", -3);
		new Moveto("Ryk 3 felter tilbage", -3);
		
		new Moveto("Ryk frem til START", Start);
		new Moveto("Ryk frem til START", Start);
		
		new Moveto("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4000", Jail);
		new Moveto("Gå i fængsel. Ryk direkte til fængslet. Selv om De passerer START, indkasserer De ikke kr. 4000", Jail);
		
		new Moveto("Ryk frem til Strandvejen. Hvis de passerer START indkassér da kr. 4000", Strandvejen);
		new Moveto("Ryk frem til Grønningen. Hvis De passerer START, indkassér da kr. 4000", Grønningen);
		new Moveto("Ryk frem til Vimmelskaftet. Hvis De passerer START, indkassér da kr. 4000", Vimmelskaftet);
		new Moveto("Ryk frem til Frederiksberg Allé. Hvis De passerer START, indkassér da kr. 4000", Frederiksberg Allé);
		new Moveto("Tag ind på rådhuspladsen", Rådhuspladsen);
		
		new Moveto("Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berretiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken", nærmeste rederi);
		new Moveto("Ryk brikken frem til det nærmeste rederi og betal ejeren to gange den leje, han ellers er berretiget til. Hvis selskabet ikke ejes af nogen, kan De købe det af banken", nærmeste rederi);
		new Moveto("Tag med den nærmeste færge. Flyt brikken frem, og hvis De passerer START indkassér da kr. 4000", nærmeste færge);
		new Moveto("Tag med Mols-linien. Flyt brikken frem, og hvis de passerer START, indkassér da kr. 4000", Mols-linien);
		
		
		
	};
	discard = new Kort[deck.length];
	currentdecksize=deck.length;
	discardsize=0;
}

	//Discard kan fjernes og shuffledeck kan simplificeres hvis Get out of jail kortene fjernes.
	
	
	public Kort drawcard(){
	Kort card=deck[currentdecksize];
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
	
	public void discardcard(Kort card){
		discard[deck.length-discardsize]=card;
		discardsize++;	
	}
}


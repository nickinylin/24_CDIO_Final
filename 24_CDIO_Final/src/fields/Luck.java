package fields;

import cards.Cards;
import cards.CardsDeck;
import cards.CardsMoveto;
import game.Player;

public class Luck extends Field {
	
	private CardsDeck deck;
	
    public Luck(String name) {
        super(name);
    }

	@Override
	public void landOnField(Player player) {
		Cards card= deck.drawcard();
		if (card instanceof CardsMoveto) {
            CardsMoveto move=(CardsMoveto) card;
            if (move.getExtraMoves()==0){
            	
            }
            	
            
            }
            //		switch(player.getPlayerPosition())
//		case 1:
//		case 2:
//		case 3:
//		case 4:
		if(player.getPlayerPosition()>0 && player.getPlayerPosition()<10)
		{
		player.setPlayerPosistion(player, 6);
		Fleet ships = (Fleet)fields[6];
			if(ships.fieldowned)
			{
				player.setPlayerPosistion(player, 6);
				ships.landOnField(player);
			}
			else
				player.setPlayerPosistion(player, 6);
				ships.landOnField(player);
		}
		else if(player.getPlayerPosition()>=11 && player.getPlayerPosition()<=20)
		{
		player.setPlayerPosistion(player, 16);
		Fleet ships = (Fleet)fields[16];
			if(ships.fieldowned)
			{
			player.setPlayerPosistion(player, 16);
			ships.landOnField(player);
			}
		}
		else if(player.getPlayerPosition()>=21 && player.getPlayerPosition()<=30)
		{
		player.setPlayerPosistion(player, 26);
		Fleet ships = (Fleet)fields[26];
			if(ships.fieldowned)
			{
			player.setPlayerPosistion(player, 26);
			ships.landOnField(player);
			}
		}
		else if(player.getPlayerPosition()>=31 && player.getPlayerPosition()<=40)
		{
		player.setPlayerPosistion(player, 36);
		Fleet ships = (Fleet)fields[36];
			if(ships.fieldowned)
			{
			player.setPlayerPosistion(player, 36);
			ships.landOnField(player);
			}
		}
		
            
	}
    
}

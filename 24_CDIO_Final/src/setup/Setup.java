package setup;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_resources.GUI;
import game.Player;

public class Setup {
	private static Player[] id;
	

	public Setup() {
		
	}
	
	public Player[] createPlayers() {
		// How many Players?
		Player[] players = null;
				String NumberofPlayers = GUI.getUserSelection("", "2 Players", "3 Players", "4 Players", "5 Players", "6 Players");

				// Create Player 1,2,3,4,5,6
				switch (NumberofPlayers) {
				case "6 Players": players = addPlayer(6); break;
				case "5 Players": players = addPlayer(5); break;
				case "4 Players": players = addPlayer(4); break;
				case "3 Players": players = addPlayer(3); break;
				case "2 Players": players = addPlayer(2); break;
				default:

				}
				return players;
		
	}

	
	
	
	public static Player[] addPlayer(int antal) {

		id = new Player[antal];

		for (int i = 0; i < antal; i++) {


			GUI.displayChanceCard("Type in your name");
			String name = GUI.getUserString("");

			if (name.equals("")) {
				name = "Player "+(i+1);
			}

			GUI.displayChanceCard(name + " choose your type of car");
			String cartype = GUI.getUserSelection("", "Car", "RaceCar", "Tractor", "Ufo");

			Car.Builder builder = new Car.Builder();

			switch (cartype) {
			default:
			case "Car": builder.typeCar(); break;
			case "RaceCar": builder.typeRacecar(); break;
			case "Tractor": builder.typeTractor(); break;
			case "Ufo": builder.typeUfo(); break;
			}

			GUI.displayChanceCard(name + " choose your "+cartype+" color");
			String color = GUI.getUserSelection("", "Red", "Blue", "Green", "Yellow", "White", "Black", "Pink", "Magenta", "Grey", "Orange", "Cyan");

			switch (color) {
			default:
			case "Red": builder.primaryColor(Color.RED); break;
			case "Blue": builder.primaryColor(Color.BLUE); break;
			case "Green": builder.primaryColor(Color.GREEN); break;
			case "Yellow": builder.primaryColor(Color.YELLOW); break;
			case "White": builder.primaryColor(Color.WHITE); break;
			case "Black": builder.primaryColor(Color.BLACK); break;
			case "Pink": builder.primaryColor(Color.PINK); break;
			case "Magenta": builder.primaryColor(Color.MAGENTA); break;
			case "Grey": builder.primaryColor(Color.LIGHT_GRAY); break;
			case "Orange": builder.primaryColor(Color.ORANGE); break;
			case "Cyan": builder.primaryColor(Color.CYAN); break;
			}

			Car car = builder.build();

			Player player = new Player(name);
			id[i] = player;

			GUI.addPlayer(name, player.getMoney(), car);
		}

		return id;
	}

}

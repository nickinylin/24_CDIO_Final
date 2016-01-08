package setup;

import java.awt.Color;

import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Shipping;
import desktop_fields.Street;
import desktop_resources.GUI;
import game.Player;
import fields.Empty;
import fields.Field;
import fields.Fleet;
import fields.Jail;
import fields.Labor;
import fields.Luck;

import fields.Refuge;
import fields.Tax;
import fields.Territory;

public class Setup {

	private static Player[] id;
//	private Field[] fields;


	/**
	 * creates the fields for both the GUI and gamelogic to interact with.
	 * @return Field[]
	 */

	public Field[] createFields() {

		Field[] fields = new Field[] { 
				new Refuge ("Start", "Start", 4000),
				new Territory ("Rødovrevej",1,1200,1000,50,250,750,2250,4000,6000),
				new Luck ("Prøv lykken"),
				new Territory ("Hvidovrevej",1,1200,1000,50,250,750,2250,4000,6000),
				new Tax ("Betal indkomst skat",4000, "special"),
				new Fleet ("Scanlines!<br> Helsingør - Helsingborg"),
				new Territory ("Roskildevej",2, 2000, 1000,100,600,1800,5400,8000,11000),
				new Luck ("Prøv lykken"),
				new Territory ("Valby Langgade", 2, 2000, 1000,100,600,1800,5400,8000,11000),
				new Territory ("Allegade", 2, 2400, 1000,150,800,2000,6000,9000,12000),
				new Refuge ("På besøg i fængsel", "Visit", 0),
				new Territory ("Frederiksberg Alle", 3, 2800,2000,200,1000,3000,9000,12500,15000),
				new Labor ("Tuborg"),
				new Territory ("Bulowsvej", 3, 2800,2000,200,1000,3000,9000,12500,15000),
				new Territory ("Gl. Kongevej", 3, 3200,2000,250,1250,3750,10000,14000,18000),
				new Fleet ("Mols-linien!<br> En genvej i Danmark"),
				new Territory ("Bernstorffsvej", 4, 3600,2000,300,1400,4000,11000,15000,19000),
				new Luck ("Prøv lykken"),
				new Territory ("Hellerupvej", 4, 3600,2000,300,1400,4000,11000,15000,19000),
				new Territory ("Stranvejen",4, 4000,2000,350,1600,4400,12000,16000,20000),
				new Refuge ("Parkering", "Parking", 0),
				new Territory ("Trianglen",5,4400,3000,350,1800,5000,14000,17500,21000),
				new Luck ("Prøv lykken"),
				new Territory ("Østerbrogade",5,4400,3000,350,1800,5000,14000,17500,21000),
				new Territory ("Grønningen",5,4800,3000,400,2000,6000,15000,18500,22000),
				new Fleet ("Scanlines!<br> Gedser - Rostock"),
				new Territory ("Bredgade",6,5200,3000,450,2200,6600,16000,19500,23000),
				new Territory ("Kgs. Nytorv",6,5200,3000,450,2200,6600,16000,19500,23000),
				new Labor ("Carlsberg"),
				new Territory ("Østergade",6,5600,3000,500,2400,7200,17000,20500,24000),
				new Jail ("De fængsles"),
				new Territory ("Amagertorv",7,6000,4000,550,2600,7800,18000,22000,25000),
				new Territory ("Vimmelskaftet",7,6000,4000,550,2600,7800,18000,22000,25000),
				new Luck ("Prøv lykken"),
				new Territory ("Nygade",7,6400,4000,600,3000,9000,20000,24000,28000),
				new Fleet ("Scanlines!<br> Rødby - Puttgarden"),
				new Luck ("Prøv lykken"),
				new Territory ("Frederiksberggade",8,7000,4000,700,3500,10000,22000,26000,30000),
				new Tax ("Ekstraordinær statsskat - betal",2000,"false"),
				new Territory ("Rådhuspladsen",8,8000,4000,1000,4000,12000,28000,34000,40000),
		};


		// We shuffle the array list field, so every game has a random board.
		//		java.util.List<?> lists = (java.util.List<?>) Arrays.asList(fields);
		//		Collections.shuffle(lists);

		createGUIFields(fields);
		return fields;
	}


	private static void createGUIFields(Field[] fields) {
		desktop_fields.Field list[] = new desktop_fields.Field[fields.length];
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field instanceof Territory) {
				Territory territory = (Territory) field;

				if (territory.getFieldGroup() == 1) {
					list[i] = new Street.Builder()
							.setTitle(field.getName())
							.setSubText("Costs: "+territory.getPrice()+"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(56, 132, 218))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 2) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName() )
							.setSubText("Costs: "+territory.getPrice() +"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(224, 71, 52))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 3) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName() )
							.setSubText("Costs: "+territory.getPrice() +"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(225, 231, 0))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 4) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText("Costs: "+territory.getPrice()+"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(140, 121, 121))
							.setFgColor(Color.WHITE)
							.build();
				}else if (territory.getFieldGroup() == 5) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText("Costs: "+territory.getPrice()+"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(255, 18, 31))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 6) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText("Costs: "+territory.getPrice()+"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(250, 250, 250))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 7) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText("Costs: "+territory.getName()+"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(255, 202, 70))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 8) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText("Costs: "+territory.getPrice()+"")
							.setDescription("Rent: "+territory.getRent(fields)+"")
							.setBgColor(new Color(112, 21, 97))
							.setFgColor(Color.WHITE)
							.build();
				}

			} else if (field instanceof Refuge) {
				Refuge refuge = (Refuge) field;
				if (refuge.type() == "Start") {
					list[i] = new desktop_fields.Refuge.Builder()
							.setTitle(field.getName())
							.setSubText("START")
							.setPicture("images/money.png")
							.setBgColor(Color.RED)
							.setFgColor(Color.BLACK)
							.build();
				} else if (refuge.type() == "Visit") {
					list[i] = new desktop_fields.Refuge.Builder()
							.setTitle(fields[i].getName())
							.setSubText("På besøg")
							.setPicture("images/Jail.jpg")
							.setBgColor(new Color(127, 127, 127))
							.setFgColor(Color.BLACK)
							.build();
				} else if (refuge.type() == "Parking") {
					list[i] = new desktop_fields.Refuge.Builder()
							.setTitle(fields[i].getName())
							.setSubText("Parkering")
							.setPicture("images/Cones.jpg")
							.setBgColor(Color.WHITE)
							.setFgColor(Color.BLACK)
							.build();
				}

			} else if (field instanceof Labor) {
				list[i] = new Brewery.Builder()
						.setTitle(fields[i].getName())
						.setDescription("Rent: 100xDices")
						.setSubText("")
						.setBgColor(new Color(1, 76, 28))
						.setFgColor(Color.WHITE)
						.setRent("100xDices")
						.setPicture("images/"+fields[i].getName()+".jpg")
						.build(); 

			} else if (field instanceof Tax) {
				Tax tax = (Tax) field;
				list[i] = new desktop_fields.Tax.Builder()
						.setTitle(fields[i].getName())
						.setDescription("TAX: "+tax.getRent()+"")
						.setSubText("Pay "+tax.getRent()+" in TAXES")
						.setBgColor(Color.GRAY)
						.build(); 

			} else if (field instanceof Fleet) {
				list[i] = new Shipping.Builder()
						.setTitle(fields[i].getName())
						.setDescription("Rent: 500-4000<br>"+fields[i].getName())
						.setSubText("")
						.setBgColor(Color.WHITE)
						.setFgColor(Color.BLACK)
						.setRent("500 per owned Fleet")
						.build();
			} else if (field instanceof Luck) {
				list[i] = new Chance.Builder()
						.setBgColor(Color.BLACK)
						.setFgColor(Color.WHITE)
						.build();

			} else if (field instanceof Jail) {
				list[i] = new desktop_fields.Jail.Builder()
						.setPicture("images/GoToJail.jpg")
						.setSubText("Gå i fængsel")
						.setBgColor(new Color(127, 127, 125))
						.setFgColor(Color.WHITE)
						.build();
			} else if (field instanceof Empty) {
				list[i] = new desktop_fields.Empty.Builder()
						.setBgColor(new Color(85, 81, 139))
						.setFgColor(Color.WHITE)
						.build();
			}
		}

		GUI.create(list);
	}



//	public Player[] createPlayers() {
//		// How many Players?
//		Player[] players = null;
//		String NumberofPlayers = GUI.getUserSelection("", "2 Players", "3 Players", "4 Players", "5 Players", "6 Players");
//
//		// Create Player 1,2,3,4,5,6
//		switch (NumberofPlayers) {
//		case "6 Players": players = addPlayer(6); break;
//		case "5 Players": players = addPlayer(5); break;
//		case "4 Players": players = addPlayer(4); break;
//		case "3 Players": players = addPlayer(3); break;
//		case "2 Players": players = addPlayer(2); break;
//		default:
//
//		}
//		return players;
//
//	}
//
//
//
//
//	public static Player[] addPlayer(int antal) {
//
//		id = new Player[antal];
//
//		for (int i = 0; i < antal; i++) {
//
//
//			GUI.displayChanceCard("Type in your name");
//			String name = GUI.getUserString("");
//
//			if (name.equals("")) {
//				name = "Player "+(i+1);
//			}
//
//			GUI.displayChanceCard(name + " choose your type of car");
//			String cartype = GUI.getUserSelection("", "Car", "RaceCar", "Tractor", "Ufo");
//
//			Car.Builder builder = new Car.Builder();
//
//			switch (cartype) {
//			default:
//			case "Car": builder.typeCar(); break;
//			case "RaceCar": builder.typeRacecar(); break;
//			case "Tractor": builder.typeTractor(); break;
//			case "Ufo": builder.typeUfo(); break;
//			}
//
//			GUI.displayChanceCard(name + " choose your "+cartype+" color");
//			String color = GUI.getUserSelection("", "Red", "Blue", "Green", "Yellow", "White", "Black", "Pink", "Magenta", "Grey", "Orange", "Cyan");
//
//			switch (color) {
//			default:
//			case "Red": builder.primaryColor(Color.RED); break;
//			case "Blue": builder.primaryColor(Color.BLUE); break;
//			case "Green": builder.primaryColor(Color.GREEN); break;
//			case "Yellow": builder.primaryColor(Color.YELLOW); break;
//			case "White": builder.primaryColor(Color.WHITE); break;
//			case "Black": builder.primaryColor(Color.BLACK); break;
//			case "Pink": builder.primaryColor(Color.PINK); break;
//			case "Magenta": builder.primaryColor(Color.MAGENTA); break;
//			case "Grey": builder.primaryColor(Color.LIGHT_GRAY); break;
//			case "Orange": builder.primaryColor(Color.ORANGE); break;
//			case "Cyan": builder.primaryColor(Color.CYAN); break;
//			}
//
//			Car car = builder.build();
//
//			Player player = new Player(name);
//			id[i] = player;
//
//			GUI.addPlayer(name, player.getMoney(), car);
//			GUI.setCar(1, name);
//		}		
//
//
//		return id;
//	}

	public Player[] createPlayers() {
		// How many Players?
		Player[] players = null;
		
		players = addPlayer(2);
		
		return players;
}




	public static Player[] addPlayer(int antal) {

		id = new Player[antal];

		for (int i = 0; i < antal; i++) {


			
			String name = "";

			if (name.equals("")) {
				name = "Player "+(i+1);
			}

			Car.Builder builder = new Car.Builder();

			builder.typeCar();
			
			if (i==1) {
				builder.primaryColor(Color.RED);
			} else {
				builder.primaryColor(Color.BLUE);
			}
			

			Car car = builder.build();

			Player player = new Player(name);
			id[i] = player;

			GUI.addPlayer(name, player.getMoney(), car);
			GUI.setCar(1, name);
			
		}		


		return id;
	}
	
	
}

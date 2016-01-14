package setup;

import java.awt.Color;
import controllers.Language;
import desktop_codebehind.Car;
import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Shipping;
import desktop_fields.Street;
import desktop_resources.GUI;
import game.Player;
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


	/**
	 * creates the fields for both the GUI and gamelogic to interact with.
	 * @return Field[]
	 */

	public Field[] createFields() {

		Field[] fields = new Field[] { 
				new Refuge (Language.field_Start, "Start", 4000),
				new Territory (Language.field_Roedovrevej,1,1200,1000,50,250,750,2250,4000,6000),
				new Luck (Language.luck),
				new Territory (Language.field_Hvidovrevej,1,1200,1000,50,250,750,2250,4000,6000),
				new Tax (Language.field_TaxSpecial,4000, "special"),
				new Fleet (Language.field_Fleet1),
				new Territory (Language.field_Roskildevej,2, 2000, 1000,100,600,1800,5400,8000,11000),
				new Luck (Language.luck),
				new Territory (Language.field_ValbyLanggade, 2, 2000, 1000,100,600,1800,5400,8000,11000),
				new Territory (Language.field_Allegade, 2, 2400, 1000,150,800,2000,6000,9000,12000),
				new Refuge (Language.field_VisitJail, "Visit", 0),
				new Territory (Language.field_FrederiksbergAlle, 3, 2800,2000,200,1000,3000,9000,12500,15000),
				new Labor (Language.field_Labor1),
				new Territory (Language.field_Bulowsvej, 3, 2800,2000,200,1000,3000,9000,12500,15000),
				new Territory (Language.field_GlKongevej, 3, 3200,2000,250,1250,3750,10000,14000,18000),
				new Fleet (Language.field_Fleet2),
				new Territory (Language.field_Bernstorffsvej, 4, 3600,2000,300,1400,4000,11000,15000,19000),
				new Luck (Language.luck),
				new Territory (Language.field_Hellerupvej, 4, 3600,2000,300,1400,4000,11000,15000,19000),
				new Territory (Language.field_Strandvejen,4, 4000,2000,350,1600,4400,12000,16000,20000),
				new Refuge (Language.field_Parking, "Parking", 0),
				new Territory (Language.field_Trianglen,5,4400,3000,350,1800,5000,14000,17500,21000),
				new Luck (Language.luck),
				new Territory (Language.field_Oesterbrogade,5,4400,3000,350,1800,5000,14000,17500,21000),
				new Territory (Language.field_Groenningen,5,4800,3000,400,2000,6000,15000,18500,22000),
				new Fleet (Language.field_Fleet3),
				new Territory (Language.field_Bredgade,6,5200,3000,450,2200,6600,16000,19500,23000),
				new Territory (Language.field_KgsNytorv,6,5200,3000,450,2200,6600,16000,19500,23000),
				new Labor (Language.field_Labor2),
				new Territory (Language.field_Oestergade,6,5600,3000,500,2400,7200,17000,20500,24000),
				new Jail (Language.field_Jail),
				new Territory (Language.field_Amagertorv,7,6000,4000,550,2600,7800,18000,22000,25000),
				new Territory (Language.field_Vimmelskaftet,7,6000,4000,550,2600,7800,18000,22000,25000),
				new Luck (Language.luck),
				new Territory (Language.field_Nygade,7,6400,4000,600,3000,9000,20000,24000,28000),
				new Fleet (Language.field_Fleet4),
				new Luck (Language.luck),
				new Territory (Language.field_Frederiksberggade,8,7000,4000,700,3500,10000,22000,26000,30000),
				new Tax (Language.field_Tax,2000,"false"),
				new Territory (Language.field_Raadhuspladsen,8,8000,4000,1000,4000,12000,28000,34000,40000),
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
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(56, 132, 218))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 2) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName() )
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(224, 71, 52))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 3) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName() )
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(225, 231, 0))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 4) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(140, 121, 121))
							.setFgColor(Color.WHITE)
							.build();
				}else if (territory.getFieldGroup() == 5) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(255, 18, 31))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 6) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(250, 250, 250))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 7) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(255, 202, 70))
							.setFgColor(Color.BLACK)
							.build();
				} else if (territory.getFieldGroup() == 8) {
					list[i] = new Street.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.price +": "+ territory.getPrice())
							.setDescription(Language.basicrent + territory.getBaseRent() +"<br>"+ Language.houseprice +": "+ territory.getBuildingPrice() +"<br>"+ Language.pawnprice +": "+territory.getPawnPrice()+"<br>"+ Language.rent1house +": "+territory.getFieldrenthouse1()+"<br>"+ Language.rent2house +": "+territory.getFieldrenthouse2()+"<br>"+ Language.rent3house +": "+territory.getFieldrenthouse3()+"<br>"+ Language.rent4house +": "+territory.getFieldrenthouse4()+"<br>"+ Language.renthotel +": "+territory.getFieldrenthotel() +"<br>")
							.setBgColor(new Color(112, 21, 97))
							.setFgColor(Color.WHITE)
							.build();
				}

			} else if (field instanceof Refuge) {
				Refuge refuge = (Refuge) field;
				if (refuge.type() == "Start") {
					list[i] = new desktop_fields.Refuge.Builder()
							.setTitle(field.getName())
							.setSubText(Language.field_Start)
							.setDescription(Language.field_StartDescription)
							.setPicture("images/money.png")
							.setBgColor(Color.RED)
							.setFgColor(Color.BLACK)
							.build();
				} else if (refuge.type() == "Visit") {
					list[i] = new desktop_fields.Refuge.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.field_VisitJail)
							.setPicture("images/Jail.jpg")
							.setDescription("")
							.setBgColor(new Color(127, 127, 127))
							.setFgColor(Color.BLACK)
							.build();
				} else if (refuge.type() == "Parking") {
					list[i] = new desktop_fields.Refuge.Builder()
							.setTitle(fields[i].getName())
							.setSubText(Language.field_Parking)
							.setPicture("images/Cones.jpg")
							.setDescription(Language.field_ParkingDescription)
							.setBgColor(Color.WHITE)
							.setFgColor(Color.BLACK)
							.build();
				}

			} else if (field instanceof Labor) {
				list[i] = new Brewery.Builder()
						.setTitle(fields[i].getName())
						.setDescription(fields[i].getName()+"<br><br>"+ Language.rent +"<br>"+ Language.field_Labor1Rent +"<br>"+ Language.field_Labor2Rent +"<br><br><br><br>")
						.setSubText(Language.price+": 3000")
						.setBgColor(new Color(1, 76, 28))
						.setFgColor(Color.WHITE)
						.setPicture("images/"+fields[i].getName()+".jpg")
						.build(); 

			} else if (field instanceof Tax) {
				Tax tax = (Tax) field;
				list[i] = new desktop_fields.Tax.Builder()
						.setTitle("")
						.setDescription(fields[i].getName())
						.setSubText(Language.pay + tax.getRent() + Language.paytax)
						.setBgColor(Color.GRAY)
						.build(); 

			} else if (field instanceof Fleet) {
				list[i] = new Shipping.Builder()
						.setTitle(fields[i].getName())
						.setDescription(fields[i].getName()+"<br><br>"+ Language.field_Fleet1Rent + "<br>"+ Language.field_Fleet2Rent +"<br>" +Language.field_Fleet3Rent +"<br>"+ Language.field_Fleet4Rent +"<br><br>")
						.setSubText(Language.price +": 4000")
						.setBgColor(Color.WHITE)
						.setFgColor(Color.BLACK)
						.build();
			} else if (field instanceof Luck) {
				list[i] = new Chance.Builder()
						.setBgColor(Color.BLACK)
						.setFgColor(Color.WHITE)
						.build();

			} else if (field instanceof Jail) {
				list[i] = new desktop_fields.Jail.Builder()
						.setPicture("images/GoToJail.jpg")
						.setSubText(Language.field_JailMove)
						.setBgColor(new Color(127, 127, 125))
						.setFgColor(Color.WHITE)
						.setDescription(Language.field_JailDescription)
						.build();
			}
		}

		GUI.create(list);
	}


	public Player[] createPlayers() {
		// How many Players?
		Player[] players = null;
		String NumberofPlayers = GUI.getUserSelection("", Language.players_2players, Language.players_3players, Language.players_4players, Language.players_5players, Language.players_6players);

		// Create Player 1,2,3,4,5,6
		switch (NumberofPlayers) {
		case Language.players_2players: players = addPlayer(2); break;
		case Language.players_3players: players = addPlayer(3); break;
		case Language.players_4players: players = addPlayer(4); break;
		case Language.players_5players: players = addPlayer(5); break;
		case Language.players_6players: players = addPlayer(6); break;
		default:

		}
		return players;

	}




	public static Player[] addPlayer(int antal) {

		id = new Player[antal];

		for (int i = 0; i < antal; i++) {

			GUI.displayChanceCard(Language.players_name);
			String name = GUI.getUserString("");

			if (name.equals("")) {
				name = Language.players_empty + (i+1);
			}

			GUI.displayChanceCard(name +"<br><br>"+ Language.players_choosecar);
			String cartype = GUI.getUserSelection("", Language.players_car, Language.players_racecar, Language.players_tractor, Language.players_ufo);

			Car.Builder builder = new Car.Builder();

			switch (cartype) {
			default:
			case Language.players_car: builder.typeCar(); break;
			case Language.players_racecar: builder.typeRacecar(); break;
			case Language.players_tractor: builder.typeTractor(); break;
			case Language.players_ufo: builder.typeUfo(); break;
			}

			GUI.displayChanceCard(name +"<br><br>"+ Language.players_choose +cartype+ Language.players_color);
			String color = GUI.getUserSelection("", Language.color_red, Language.color_blue, Language.color_green, Language.color_yellow, Language.color_white, Language.color_black, Language.color_pink, Language.color_magenta, Language.color_gray, Language.color_orange, Language.color_turquoise);

			switch (color) {
			default:
			case Language.color_red: builder.primaryColor(Color.RED); break;
			case Language.color_blue: builder.primaryColor(Color.BLUE); break;
			case Language.color_green: builder.primaryColor(Color.GREEN); break;
			case Language.color_yellow: builder.primaryColor(Color.YELLOW); break;
			case Language.color_white: builder.primaryColor(Color.WHITE); break;
			case Language.color_black: builder.primaryColor(Color.BLACK); break;
			case Language.color_pink: builder.primaryColor(Color.PINK); break;
			case Language.color_magenta: builder.primaryColor(Color.MAGENTA); break;
			case Language.color_gray: builder.primaryColor(Color.LIGHT_GRAY); break;
			case Language.color_orange: builder.primaryColor(Color.ORANGE); break;
			case Language.color_turquoise: builder.primaryColor(Color.CYAN); break;
			}

			Car car = builder.build();

			Player player = new Player(name);
			id[i] = player;

			GUI.addPlayer(name, player.getMoney(), car);
			GUI.setCar(1, name);
		}		


		return id;
	}

	//	public Player[] createPlayers() {
	//		// How many Players?
	//		Player[] players = null;
	//		
	//		players = addPlayer(2);
	//		
	//		return players;
	//	}


	//	public static Player[] addPlayer(int antal) {
	//
	//		id = new Player[antal];
	//
	//		for (int i = 0; i < antal; i++) {
	//
	//
	//
	//			String name = "";
	//
	//			if (name.equals("")) {
	//				name = "Player "+(i+1);
	//			}
	//
	//			Car.Builder builder = new Car.Builder();
	//
	//			builder.typeCar();
	//
	//			if (i==1) {
	//				builder.primaryColor(Color.RED);
	//			} else {
	//				builder.primaryColor(Color.BLUE);
	//			}
	//
	//
	//			Car car = builder.build();
	//
	//			Player player = new Player(name);
	//			id[i] = player;
	//
	//			GUI.addPlayer(name, player.getMoney(), car);
	//			GUI.setCar(1, name);
	//
	//		}		
	//
	//
	//		return id;
	//	}


}

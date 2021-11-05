import java.util.Scanner; 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.PatternSyntaxException;

public class Restaurant {
	private static final String dateFormatString = "dd/MM/yyyy"; 
	private static final String dateTimeFormatString = "dd/MM/yyyy HH:mm"; 
	private Hashtable<String, Staff> staffList; 
	private Menu menu;
	private TableManager tableMgr;
	private OrderManager orderMgr;
	private ReservationManager resMgr;	
	public Restaurant() 
	{
		// initialise staffList
		staffList = new Hashtable<String, Staff>();
		// initialise menu
		//// how to create promotional package ??
		menu = new Menu();
		// initialise tables
		tableMgr = new TableManager();
		// initialise order manager
		orderMgr = new OrderManager();
		// initialise reservation manager
		resMgr = new ReservationManager();
	}
	public Restaurant(String staffFileName, String menuFileName, String tableFileName) 
	{
		String path = new File("").getAbsolutePath();
		String fileName;
		File file;
		BufferedReader br;
        String line, data[];
		// initialise staffList
		staffList = new Hashtable<String, Staff>();
		fileName = path + staffFileName;
		System.out.printf("Reading staff data from %s ...\n", fileName);
	    file = new File(fileName);
		Staff staff;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null)
			{
				data = line.split("\\|");
				// ID|name|gender|job
				staff = new Staff(data[1], data[2], data[3]);
				staffList.put(data[0], staff);
			}
		} catch (FileNotFoundException e) {
			System.out.printf("ERROR: FileNotFoundException for %s\n", fileName);
		} catch (IOException e) {
			System.out.printf("ERROR: IOException while reading %s\n", fileName);
		}
		// initialise menu
		//// how to create promotional package ??
		menu = new Menu();
		fileName = path + menuFileName;
		System.out.printf("Reading menu data from %s ...\n", fileName);
	    file = new File(fileName);
		AlaCarte item;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null)
			{
				data = line.split("\\|");
				// name|desc|price|type
				item = new AlaCarte(data[0], data[1], data[2], data[3]);
				menu.addMenuItem(item);
			}
		} catch (FileNotFoundException e) {
			System.out.printf("ERROR: FileNotFoundException for %s\n", fileName);
		} catch (IOException e) {
			System.out.printf("ERROR: IOException while reading %s\n", fileName);
		}
		// initialise tables
		tableMgr = new TableManager();
		fileName = path + tableFileName;
		System.out.printf("Reading table data from %s ...\n", fileName);
	    file = new File(fileName);
		Table table;
		int tableNum = 1;
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null)
			{
				// capacity
				table = new Table(tableNum++, line, true);
				tableMgr.addTable(table); //// how to pass tables to tableMgr?
			}
		} catch (FileNotFoundException e) {
			System.out.printf("ERROR: FileNotFoundException for %s\n", fileName);
		} catch (IOException e) {
			System.out.printf("ERROR: IOException while reading %s\n", fileName);
		}
		// initialise order manager
		orderMgr = new OrderManager();
		// initialise reservation manager
		resMgr = new ReservationManager();
	}
	private Customer inputCustomer(Scanner sc)
	{
		Customer customer;
		System.out.print("Enter customer name: ");
		String custName = sc.nextLine();
		System.out.print("Enter customer contact: ");
		String custContact = sc.nextLine();
		char membership;
		do 
		{
			System.out.print("Is customer a member? (Y/N) ");
			membership = sc.next().toUpperCase().charAt(0);
			if (membership == 'Y' || membership == 'N')
				break;
			else
				System.out.print("Invalid input. Please enter Y or N.\n");
		} while (true); 
		if (membership == 'Y')
			customer = new Customer(custName, custContact, true);
		else
			customer = new Customer(custName, custContact, false);
		return customer;
	}
	private LocalDate inputDate(Scanner sc)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatString);
		LocalDate date = null;
		String dateString;
		boolean valid = true;
		do 
		{
			System.out.printf("Enter date (%s): ", dateFormatString);
			dateString = sc.next();
			try {
				date = LocalDate.parse(dateString, formatter);
			}
			catch (DateTimeParseException exc) {
				System.out.printf("Invalid date input: %s is not parsable!", dateString);
				valid = false;
			}
		} while (!valid);
		return date;
	}
	private LocalDateTime inputDateTime(Scanner sc)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatString);
		LocalDateTime dateTime = null;
		String dateString, dateTimeString;
		int hour;
		boolean valid = true;
		do 
		{
			System.out.printf("Enter date (%s): ", dateFormatString);
			dateString = sc.next();
			System.out.print("Enter hour (24H, from 11 to 21): ");
			hour = sc.nextInt();
			dateTimeString = String.format("%s %02d:00",dateString, hour);
			try {
				dateTime = LocalDateTime.parse(dateTimeString, formatter);
			}
			catch (DateTimeParseException exc) {
				System.out.printf("Invalid datetime input: %s is not parsable!\n", dateTimeString);
				valid = false;
			}
			if (valid)
				valid = resMgr.isValidDate(dateTimeString);
		} while (!valid);
		return dateTime;
	}
	private MenuItem inputMenuItem(Scanner sc, boolean isAlaCarte, boolean isPromo)
	{
		String menuItemID;
		MenuItem item;
		do
		{
			menu.displayMenu(); //// check inputs 
			System.out.print("Enter menu item ID: ");
			menuItemID = sc.next(); 
			if (menu.isValidID(menuItemID))
			{
				item = menu.getMenuItem(menuItemID);
				if (isAlaCarte && !isPromo) // only accept ala carte 
				{
					if (item.checkAlaCarte()) 
						break;
					else
						System.out.print("Invalid input. Please enter ID of ala carte item.\n");
				} else if (!isAlaCarte && isPromo) // only accept promo
				{
					if (!item.checkAlaCarte()) 
						break;
					else
						System.out.print("Invalid input. Please enter ID of promotion item.\n");
				}
				else // accept any menu item
					break;
			}
			else
				System.out.print("Invalid input. Please enter valid ID.\n");
		} while (true);
		return item;
	}
	private int inputPax(Scanner sc)
	{
		int pax; 
		do 
		{
			System.out.print("Enter number of people: "); 
			pax = sc.nextInt();
			if (pax >= 0 && pax <= 10)
				break;
			else
				System.out.print("Invalid input. Please enter positive integer.\n");
		} while (true); 
		return pax;
	}
	private float inputPrice(Scanner sc)
	{
		float price;
		do 
		{
			System.out.print("Enter price: ");
			price = sc.nextFloat();
			if (price <= 0)
				System.out.print("Invalid input. Please enter positive value.\n");
		} while	(price <= 0); 
		return price;
	}
	private int inputQuantity(Scanner sc)
	{
		int quantity;
		do 
		{
			System.out.print("Enter quantity: ");
			quantity = sc.nextInt();
			if (quantity > 0)
				break;
			else
				System.out.print("Invalid input. Please enter positive integer.\n");
		} while (true);
		return quantity;
	}
	private Staff inputStaff(Scanner sc)
	{
		String employeeID; 
		Staff staff;
		do 
		{
			System.out.printf("Enter employee ID: ");
			employeeID = sc.next();
			staff = staffList.get(employeeID);
			if (staff != null)
				break;
			else // id not found in dictionary
				System.out.print("Invalid input. Please enter valid employee ID.\n");
		} while (true);
		return staff;
	}
	private int inputTableNum(Scanner sc)
	{
		int tableNum;
		do 
		{
			System.out.print("Enter table number: ");
			tableNum = sc.nextInt();
			if (tableMgr.isValidTableNumber(tableNum))
				break;
			else
				System.out.print("Invalid input. Please enter valid table number.\n");
		} while (true);
		return tableNum;
	}
	private String inputType(Scanner sc)
	{
		String type;
		do
		{
			System.out.printf("Enter ala carte item type: ");
			type = sc.next();
			if (AlaCarte.isValidType(type))
				break;
			else
				System.out.print("Invalid input. Please enter valid type.\n");
		} while (true);
		return type;
	}
	private void alaCarteHelper(Scanner sc) 
	{
		AlaCarte alaCarteItem;
		int option = 0;		
		do {
			System.out.print("====== RRPSS manage ala carte items ======\n");
			System.out.print("1) Create ala carte item\n" + 
							"2) Update ala carte item\n" + 
							"3) Remove ala carte item\n" + 
							"4) Return to RRPSS application main menu\n");
			System.out.print("Enter option number: ");
			option = sc.nextInt();
			switch (option)
			{
			case 1:
				// create
				System.out.print("Enter name of new ala carte item: ");
				String name = sc.nextLine(); 
				System.out.print("Enter description: ");
				String desc = sc.nextLine();
				float price = inputPrice(sc);
				String type = inputType(sc);				
				alaCarteItem = new AlaCarte(name, desc, price, type);
				menu.addMenuItem(alaCarteItem);
				break;
			case 2:
				// update
				alaCarteItem = (AlaCarte) inputMenuItem(sc, true, false);
				System.out.printf("====== RRPSS update ala carte item %s =====\n", alaCarteItem.getName());
				System.out.printf("1) Update name of ala carte item\n" +
								"2) Update description of ala carte item\n" +
								"3) Update price of ala carte item\n", 
								"4) Update type of ala carte item\n");
				System.out.print("Enter option number (1-4): ");
				int subOption = sc.nextInt();
				switch (subOption)
				{
				case 1:
					// name
					System.out.printf("Current name: %s\n", alaCarteItem.getName());
					System.out.print("Enter new name: ");
					String itemName = sc.nextLine(); 
					alaCarteItem.setName(itemName);
					break;
				case 2:
					// desc
					System.out.printf("Current description: %s\n", alaCarteItem.getDescription());
					System.out.print("Enter new description: ");
					String itemDesc = sc.nextLine();	
					alaCarteItem.setDescription(itemDesc);
					break;
				case 3:
					// price
					System.out.printf("Current price: %d\n", alaCarteItem.getPrice());
					float itemPrice = inputPrice(sc);
					alaCarteItem.setPrice(itemPrice);
					break;
				case 4:
					// type
					System.out.printf("Current type: %d\n", alaCarteItem.getType());
					String itemType = inputType(sc);
					alaCarteItem.setType(itemType);
					break;
				default:
					System.out.print("Invalid input. Returning to RRPSS menu for managing ala carte items...\n");
				}
				break;
			case 3: 
				// remove
				alaCarteItem = (AlaCarte) inputMenuItem(sc, true, false);
				menu.removeMenuItem(alaCarteItem);
				break;
			case 4:
				// back to main
				System.out.printf("Returning to RRPSS main menu...\n");
				break;
			default:
				// invalid input
				System.out.print("Invalid option. Please enter option 1-4.\n");
			}
		} while (option != 4);
	}
	private void promotionHelper(Scanner sc) 
	{
		Promotion promo;
		int option = 0;
		do 
		{
			System.out.print("====== RRPSS manage promotions ======\n");
			System.out.print("1) Create promotion\n" + 
							"2) Update promotion\n" + 
							"3) Remove promotion\n" +
							"4) Return to RRPSS application main menu\n");
			System.out.print("Enter option number: ");
			option = sc.nextInt();
			switch (option) 
			{
			case 1: 
				// create
				System.out.print("Enter name of new promotional package: ");
				String name = sc.nextLine(); 
				System.out.print("Enter description: ");
				String desc = sc.nextLine();
				float price = inputPrice(sc);
				promo = new Promotion(name, desc, price);
				System.out.printf("Enter number of ala carte items in promotional package: ");
				int numItems = sc.nextInt(); 
				AlaCarte alaCarteItem;
				for (int i=0; i<numItems; i++)
				{
					alaCarteItem = (AlaCarte) inputMenuItem(sc, true, false); 
					promo.addItem(alaCarteItem);
				}
				menu.addMenuItem(promo);
				break;
			case 2:
				// update
				promo = (Promotion) inputMenuItem(sc, false, true);
				System.out.printf("====== RRPSS update promotion %s =====\n", promo.getName());
				System.out.printf("1) Update name of promotional package\n" +
								"2) Update description of promotional package\n" +
								"3) Update price of promotional package\n", 
								"4) Add item to promotional package\n" +
								"5) Remove item from %<s\n");
				System.out.print("Enter option number (1-5): ");
				int subOption = sc.nextInt();
				switch (subOption)
				{
				case 1:
					// name
					System.out.printf("Current name: %s\n", promo.getName());
					System.out.print("Enter new name: ");
					String promoName = sc.nextLine(); 
					promo.setName(promoName);
					break;
				case 2:
					// desc
					System.out.printf("Current description: %s\n", promo.getDescription());
					System.out.print("Enter new description: ");
					String promoDesc = sc.nextLine();	
					promo.setDescription(promoDesc);
					break;
				case 3:
					// price
					System.out.printf("Current price: %d\n", promo.getPrice());
					float promoPrice = inputPrice(sc);
					promo.setPrice(promoPrice);
					break;
				case 4:
					alaCarteItem = (AlaCarte) inputMenuItem(sc, true, false);
					promo.addItem(alaCarteItem);
					break;
				case 5:
					alaCarteItem = (AlaCarte) inputMenuItem(sc, true, false);
					promo.removeItem(alaCarteItem);
					break;
				default:
					System.out.print("Invalid input. Returning to RRPSS menu for managing promotions...\n");
				}
				break;
			case 3: 
				// remove
				promo = (Promotion) inputMenuItem(sc, false, true);
				menu.removeMenuItem(promo);
				break;
			case 4:
				// back to main
				System.out.printf("Returning to RRPSS main menu...\n");
				break;
			default:
				// invalid input
				System.out.print("Invalid option. Please enter option 1-4.\n");
			}
		} while (option != 4);
	}
	private void orderHelper(Scanner sc) 
	{
		int tableNum, quantity;
		Order order;
		MenuItem item;
		int option = 0;
		do {
			System.out.print("====== RRPSS manage orders ======\n");
			System.out.print("1) Create order\n" + 
								"2) View order\n" + 
								"3) Add order item\n" + 
								"4) Remove order item\n" +
								"5) Return to RRPSS application main menu\n");
			System.out.print("Enter option number: ");
			option = sc.nextInt();
			switch (option)
			{
			case 1:
				// create
				Staff staff = inputStaff(sc);
				LocalDateTime now = LocalDateTime.now(); 
				Customer customer = inputCustomer(sc);
				int pax = inputPax(sc);
				tableNum = tableMgr.checkCurrentAvailability(pax);
				tableMgr.setTableAvailability(tableNum, false);
				orderMgr.createOrder(staff, now, tableNum, customer);
				break;
			case 2: 
				// view
				tableNum = inputTableNum(sc);
				order = orderMgr.getOrder(tableNum); 
				order.viewOrder();
				break;
			case 3:
				// add
				tableNum = inputTableNum(sc);
				order = orderMgr.getOrder(tableNum);
				item = inputMenuItem(sc, true, true);
				quantity = inputQuantity(sc);	 
				order.addItem(item, quantity);
				break;
			case 4:
				// remove
				tableNum = inputTableNum(sc);
				order = orderMgr.getOrder(tableNum);
				item = inputMenuItem(sc, true, true);
				quantity = inputQuantity(sc);	 
				order.removeItem(item, quantity);
				break;
			case 5:
				// back to main
				System.out.printf("Returning to RRPSS main menu...\n");
				break;
			default:
				// invalid input
				System.out.print("Invalid option. Please enter option 1-5.\n");
			}
		} while (option != 5);
	}
	private void reservationHelper(Scanner sc) 
	{
		LocalDateTime dateTime;
		String custName;
		int option = 0;		
		do 
		{
			System.out.print("====== RRPSS manage reservations ======\n");
			System.out.print("1) Create reservation booking\n" + 
							"2) Check reservation booking\n" + 
							"3) Remove reservation booking\n" +
							"4) Return to RRPSS application main menu\n");
			System.out.print("Enter option number: ");
			option = sc.nextInt();
			switch (option) 
			{
			case 1: 
				// create
				dateTime = inputDateTime(sc);
				int pax = inputPax(sc); 
				Customer customer = inputCustomer(sc);
				resMgr.createReservation(dateTime, pax, customer);
				break;
			case 2:
				// check
				System.out.print("Enter customer name: ");
				custName = sc.nextLine();
				dateTime = inputDateTime(sc);
				resMgr.checkReservation(custName, dateTime);
				break;
			case 3: 
				// remove
				System.out.print("Enter customer name: ");
				custName = sc.nextLine();
				dateTime = inputDateTime(sc);
				if (resMgr.removeReservation(custName, dateTime))
					System.out.println("SUCCESS: Reservation has been removed.");
				else
				System.out.println("FAILURE: No such reservation found.");
				break;
			case 4:
				// back to main
				System.out.printf("Returning to RRPSS main menu...\n");
				break;
			default:
				// invalid input
				System.out.print("Invalid option. Please enter option 1-4.\n");
			};
		} while (option != 4);
	}
	public void runRRPSS() 
	{
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		do {
			// update reservations
			resMgr.deleteInvalidReservations(tableMgr);
			resMgr.setReservedTablesOccupied(tableMgr);

			// main menu and options
			System.out.print("====== RRPSS main menu ======\n");
			System.out.print("1) Manage ala carte items\n" + 
							"2) Manage promotions\n" + 
							"3) Manage orders\n" +
							"4) Manage reservation bookings\n" +
							"5) Check table availability\n" +
							"6) Print order invoice\n" +
							"7) Print sales revenue report by period\n" +
							"8) Quit RRPSS application\n");
			System.out.print("Enter option number: ");
			option = sc.nextInt();
			switch (option) 
			{
			case 1:
				// ala carte items
				alaCarteHelper(sc);
				break;
			case 2:
				// promotions
				promotionHelper(sc);
				break;
			case 3: 
				// orders
				orderHelper(sc);
				break;
			case 4:
				// reservation bookings
				reservationHelper(sc);
				break;
			case 5: 
				// table availability
				int subOption = 0;
				do 
				{
					System.out.print("====== RRPSS table availability ======\n");
					System.out.print("1) Check current availability \n" + 
									"2) Check future availability");
					subOption = sc.nextInt();
					if (subOption == 1 || subOption == 2)
						break;
					else
						System.out.println("Invalid input. Please enter 1 or 2.");
				} while (true);
				int pax = inputPax(sc);
				int table;
				switch (subOption)
				{
				case 1:
					table = tableMgr.checkCurrentAvailability(pax);
					break;
				case 2:
					LocalDateTime dateTime = inputDateTime(sc);
					table = tableMgr.checkFutureAvailability(pax, dateTime);
				}
				if (table != -1)
					System.out.printf("Table for %d AVAILABLE\n", pax);
				else
					System.out.printf("Table for %d NOT AVAILABLE\n", pax);
				break;
			case 6:
				// order invoice
				int tableNum;
				Order order; 				
				do 
				{
					tableNum = inputTableNum(sc);
					order = orderMgr.getOrder(tableNum);
					if (order != null)
						break;
					else
						System.out.printf("Invalid input. No existing order for table %d. \n", tableNum);
				} while (true);
				order.printOrderInvoice();
				tableMgr.setTableAvailability(tableNum, true);
				break;
			case 7: 
				// sale revenue report
				System.out.print("REPORT PERIOD START: ");
				LocalDate startDate = inputDate(sc);
				System.out.print("REPORT PERIOD END: ");
				LocalDate endDate = inputDate(sc);
				orderMgr.generateSalesRevenueReport(startDate, endDate); 
				break;
			case 8:
				// quit
				System.out.printf("Closing RRPSS...\n");
				break;
			default:
				// invalid input
				System.out.print("Invalid option. Please enter option 1-8.\n");
			};
		} while (option != 8);
		sc.close();
	}
}
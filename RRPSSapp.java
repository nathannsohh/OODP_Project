import java.util.Scanner; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;

public class RRPSSapp {
	private static String dateFormatString = "dd/MM/yyyy"; // 10/07/1996
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString);
	private static String dateTimeFormatString = "dd/MM/yyyy-HH:mm:ss"; // 10/07/1996-23:30:55
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat(dateTimeFormatString);
	private static String[] optionList_main = {"Manage menu items", 
											"Manage promotions", 
											"Manage orders", 
											"Manage reservation bookings", 
											"Check table availability", 
											"Print order invoice", 
											"Print sales revenue report by period", 
											"Quit RRPSS application"
											};
	private static String[] optionList_menuItem = {"Create menu item", 
													"Update menu item", 
													"Remove menu item", 
													"Return to RRPSS application main menu"
													};
	private static String[] optionList_promotion = {"Create promotion", 
													"Update promotion", 
													"Remove promotion",
													"Return to RRPSS application main menu"
													};
	private static String[] optionList_order = {"Create order", 
												"View order", 
												"Add order item", 
												"Remove order item",
												"Return to RRPSS application main menu"
												};
	private static String[] optionList_reservation = {"Create reservation booking", 
														"Check reservation booking", 
														"Remove reservation booking",
														"Return to RRPSS application main menu"
														};
	private static void displayOptions(String[] optionList) 
	{
		for (int i = 0; i < optionList.length; i++)
			System.out.printf("%d) %s", i+1, optionList[i]); 
	}
	public static void menuHelper(Menu menu, boolean isPromo) 
	{
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		Package promo = null; AlaCarte alaCarteItem = null;
		String itemType, menuItemID;
		if (isPromo)
		{
			itemType = "promotional set package";
		}
		else
			itemType = "ala carte menu item";			
		do {
			if (isPromo)
			{
				System.out.print("====== RRPSS manage promotions ======\n");
				displayOptions(optionList_promotion);
			}
			else
			{
				System.out.print("====== RRPSS manage menu items ======\n");
				displayOptions(optionList_menuItem);
			}
			System.out.printf("Enter option number: ");
			option = sc.nextInt();
			if (option == 1)
			{
				// create
				System.out.printf("Enter name of %s: ", itemType);
				String name = sc.nextLine(); 
				System.out.printf("Enter description for %s: ", itemType);
				String desc = sc.nextLine();
				float price;
				do 
				{
					System.out.printf("Enter price of %s: ", itemType);
					price = sc.nextFloat();
					if (price <= 0)
						System.out.print("Invalid input. Please enter positive value.\n");
				} while	(price <= 0); 
				if (isPromo)
				{
					promo = new Package(name, desc, price);
					System.out.printf("Enter number of items in %s: ", itemType);
					int numItems = sc.nextInt(); 
					for (int i=0; i<numItems; i++)
					{
						MenuItem item;
						do
						{
							System.out.print("Enter menu item ID: ");
							menuItemID = sc.next(); 
							if (menu.isValidID(menuItemID))
							{
								item = menu.getMenuItem(menuItemID);
								if (item instanceof AlaCarte) //// verify this works
									break;
								else
									System.out.print("Invalid input. Please enter ID of ala carte item.\n");
							}
							else
								System.out.print("Invalid input. Please enter valid ID.\n");
						} while (true);
						promo.addItem((AlaCarte) item);
					}
					menu.addMenuItem(promo);
				}
				else
				{
					String type;
					do
					{
						System.out.printf("Enter %s type: ", itemType);
						type = sc.next();
						if (AlaCarte.isValidType(type))
							break;
						else
							System.out.print("Invalid input. Please enter valid type.\n");
					} while (true);
					alaCarteItem = new AlaCarte(name, desc, price, type);
					menu.addMenuItem(alaCarteItem);
				}
			}
			else
			{
				//// reconsider: what if want to edit diff promo than just created 
				if (option == 2 || option == 3) 
				if (isPromo && promo == null)
				{
					MenuItem item;
					do
					{
						System.out.print("Enter menu item ID: ");
						menuItemID = sc.next(); 
						if (menu.isValidID(menuItemID))
						{
							item = menu.getMenuItem(menuItemID);
							if (item instanceof Package) //// verify this works
								break;
							else
								System.out.print("Invalid input. Please enter ID of ala carte item.\n");
						}
						else
							System.out.print("Invalid input. Please enter valid ID.\n");
					} while (true);
					promo = (Package) item;
				}
				else if (!isPromo && alaCarteItem == null)
				{
					MenuItem item;
					do
					{
						System.out.print("Enter menu item ID: ");
						menuItemID = sc.next(); 
						if (menu.isValidID(menuItemID))
						{
							item = menu.getMenuItem(menuItemID);
							if (item instanceof AlaCarte) //// verify this works
								break;
							else
								System.out.print("Invalid input. Please enter ID of ala carte item.\n");
						}
						else
							System.out.print("Invalid input. Please enter valid ID.\n");
					} while (true);
					alaCarteItem = (AlaCarte) item;
				}
				switch (option) 
				{
				case 2:
					// update
					System.out.printf("====== RRPSS update %s %s =====\n", itemType, menuItemID);
					System.out.printf("1) Update name of %s\n" +
									"2) Update description of %<s\n" +
									"3) Update price of %<s\n", 
									itemType);
					if (isPromo)
						System.out.printf("4) Add item to %s\n" +
										"5) Remove item from %<s\n", 
										itemType);
					else 
						System.out.printf("4) Update type of %s\n", itemType);
					System.out.print("Enter option number (1-5): ");
					int subOption = sc.nextInt();
					//// should we rename package to promotion ??
					if (1 <= subOption && subOption <= 3)
					{
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
							float promoPrice;
							do 
							{
								System.out.print("Enter new price for promotional set package: ");
								promoPrice = sc.nextFloat();
								if (promoPrice <= 0)
									System.out.print("Invalid input. Please enter positive value.\n");
							} while	(promoPrice <= 0); 
							promo.setPrice(promoPrice);
							break;
						}
					}
					else if (isPromo && (subOption == 4 || subOption == 5))
					{
						// edit items
						MenuItem item;
						do
						{
							System.out.print("Enter menu item ID: ");
							menuItemID = sc.next(); 
							if (menu.isValidID(menuItemID))
							{
								item = menu.getMenuItem(menuItemID);
								if (item instanceof AlaCarte) //// verify this works
									break;
								else
									System.out.print("Invalid input. Please enter ID of ala carte item.\n");
							}
							else
								System.out.print("Invalid input. Please enter valid ID.\n");
						} while (true);
						//// get feedback ??
						if (option == 4)
							// add item
							promo.addItem((AlaCarte) item);
						else if (option == 5)
							// remove item
							promo.removeItem((AlaCarte) item);
					}
					else if (!isPromo && subOption == 4)
					{
						// type
						String type;
						do
						{
							System.out.printf("Enter %s type: ", itemType);
							type = sc.next();
							if (AlaCarte.isValidType(type))
								break;
							else
								System.out.print("Invalid input. Please enter valid type.\n");
						} while (true);
						alaCarteItem.setType(type);
					}
					else
						System.out.print("Invalid input. Returning to RRPSS menu for managing promotions...\n");
					break;
				case 3: 
					// remove
					menu.removeMenuItem(promo);
					break;
				case 4:
					// back to main
					System.out.printf("Returning to RRPSS main menu...\n");
					break;
				default:
					// invalid input
					System.out.printf("Invalid option. Please enter option 1-%d.\n", optionList_promotion.length);
				};
			}
		} while (option != 4);
	}
	/*
	public static void menuItemHelper(Menu menu) 
	{
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		do {
			System.out.print("====== RRPSS manage menu items ======\n");
			displayOptions(optionList_menuItem);
			System.out.printf("Enter option number: ");
			option = sc.nextInt();
			switch (option) 
			{
			case 1: 
				// create
			case 2:
				// update
			case 3: 
				// remove
			case 4:
				// back to main
				System.out.printf("Returning to RRPSS main menu...\n");
				break;
			default:
				// invalid input
				System.out.printf("Invalid option. Please enter option 1-%d.\n", optionList_menuItem.length);
			};
		} while (option != 4);
	}
	public static void promotionHelper(Menu menu) 
	{
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		String menuItemID;
		Package promo = null;
		do {
			System.out.print("====== RRPSS manage promotions ======\n");
			displayOptions(optionList_promotion);
			System.out.printf("Enter option number: ");
			option = sc.nextInt();
			if (option == 1)
			{
				// create
				System.out.print("Enter name of promotional set package: ");
				String promoName = sc.nextLine(); 
				System.out.print("Enter description for promotional set package: ");
				String promoDesc = sc.nextLine();
				float promoPrice;
				do 
				{
					System.out.print("Enter price of promotional set package: ");
					promoPrice = sc.nextFloat();
					if (promoPrice <= 0)
						System.out.print("Invalid input. Please enter positive value.\n");
				} while	(promoPrice <= 0); 			
				//// update constructor 
				promo = new Package(promoName, promoDesc, promoPrice);
				System.out.print("Enter number of items in promotional set package: ");
				int numItems = sc.nextInt(); 
				for (int i=0; i<numItems; i++)
				{
					//// error checking valid menu item id, is a alacarte item
					System.out.print("Enter menu item ID: ");
					menuItemID = sc.next(); 
					//// add getMenuItem method to menu 
					AlaCarte item = menu.getMenuItem(menuItemID);
					promo.addItem(item);
				}
				//// rename createMenuItem to addMenuItem
				menu.addMenuItem(promo);
			}
			else
			{
				if ((option == 2 || option == 3) && promo == null)
				{
					//// error checking valid menu item id, is a promo
					System.out.print("Enter promotional set package ID: ");
					menuItemID = sc.next(); 
					//// add getMenuItem method to menu 
					promo = menu.getMenuItem(menuItemID);
				}
				switch (option) 
				{
				case 2:
					// update
					System.out.print("====== RRPSS update promotion %s =====\n", menuItemID);
					System.out.print("1) Update name of promotional set package\n" +
									"2) Update description of promotional set package\n" +
									"3) Update price of promotional set package\n" +
									"4) Add item to promotional set package\n" +
									"5) Remove item from promotional set package\n" 
									);
					System.out.print("Enter option number (1-5): ");
					int opt = sc.nextInt();
					switch (opt)
					{
					//// add get/set methods for promotion (should we rename this ??)
					case 1:
						// name
						System.out.printf("Current name: %s\n", promo.getName());
						System.out.print("Enter new name: ");
						String promoName = sc.nextLine(); 
						promo.setName(promoName);\
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
						float promoPrice;
						do 
						{
							System.out.print("Enter new price for promotional set package: ");
							promoPrice = sc.nextFloat();
							if (promoPrice <= 0)
								System.out.print("Invalid input. Please enter positive value.\n");
						} while	(promoPrice <= 0); 
						promo.setPrice(promoPrice);
						break;
					case 4: case 5: 
						// edit items
						//// error checking valid menu item id, is a alacarte item
						System.out.print("Enter menu item ID: ");
						menuItemID = sc.next(); 
						//// add getMenuItem method to menu 
						AlaCarte item = menu.getMenuItem(menuItemID);
						if (option == 4)
							// add item
							promo.addItem(item);
						else if (option == 5)
							// remove item
							promo.removeItem(item);
						break;
					default: 
						System.out.print("Invalid input. Returning to RRPSS menu for managing promotions...\n");
					}
					break;
				case 3: 
					// remove
					menu.removeMenuItem(promo);
					break;
				case 4:
					// back to main
					System.out.printf("Returning to RRPSS main menu...\n");
					break;
				default:
					// invalid input
					System.out.printf("Invalid option. Please enter option 1-%d.\n", optionList_promotion.length);
				};
			}
		} while (option != 4);
	}
	*/
	public static void orderHelper(OrderManager orderMgr, Staff staff, int tableNum, Menu menu) 
	{
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		Order order = null;
		do {
			System.out.print("====== RRPSS manage orders ======\n");
			displayOptions(optionList_order);
			System.out.printf("Enter option number: ");
			option = sc.nextInt();
			if (option == 1)
			{
				// create
				//// orderMgr.createOrder store the order (composition)
				Date now = new Date(); // initialized as time of allocation, aka current datetime
				System.out.print("Enter customer name: ");
				String custName = sc.nextLine();
				System.out.print("Enter customer contact: ");
				String custContact = sc.nextLine();
				//// is membership an input or is there a running list of members??
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
				Customer customer;
				if (membership == 'Y')
					customer = new Customer(custName, custContact, true);
				else
					customer = new Customer(custName, custContact, false);
				orderMgr.createOrder(staff, now, tableNum, customer);
				order = orderMgr.getOrder(tableNum); 
			}
			else 
			{
				MenuItem item;
				String menuItemID;
				int quantity;
				if (order == null) 
					orderMgr.getOrder(tableNum); 
				if (option == 3 || option == 4)
				{
					do
					{
						System.out.print("Enter menu item ID: ");
						menuItemID = sc.next(); 
						if (menu.isValidID(menuItemID))
						{
							item = menu.getMenuItem(menuItemID);
							if (item instanceof AlaCarte) //// verify this works
								break;
							else
								System.out.print("Invalid input. Please enter ID of ala carte item.\n");
						}
						else
							System.out.print("Invalid input. Please enter valid ID.\n");
					} while (true);
					quantity = 0;
					do 
					{
						System.out.print("Enter quantity: ");
						quantity = sc.nextInt();
						if (quantity <= 0)
							System.out.print("Invalid input. Please enter positive integer.\n");
					} while (quantity <= 0);				
				}
				switch (option) 
				{
				case 2:
					// view
					order.viewOrder();
				case 3: 
					// add item
					order.addItem(item, quantity);
				case 4: 
					// remove item 
					order.removeItem(item, quantity);
				case 5:
					// back to main
					System.out.printf("Returning to RRPSS main menu...\n");
					break;
				default:
					// invalid input
					System.out.printf("Invalid option. Please enter option 1-%d.\n", optionList_order.length);
				};
			}
		} while (option != 5);
	}
	public static void reservationHelper(ReservationManager resMgr) 
	{
		//// standardize Date format
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		String custName, dateTimeString;
		Date dateTime;
		do {
			System.out.print("====== RRPSS manage reservations ======\n");
			displayOptions(optionList_reservation);
			System.out.printf("Enter option number: ");
			option = sc.nextInt();
			switch (option) 
			{
			case 1: 
				// create
				//// datetime error check (future, valid format) ??
				System.out.printf("Enter date and time (%s): ", dateTimeFormatString);
				dateTimeString = sc.next();
				
				dateTime = dateTimeFormat.parse(dateTimeString);
				int pax = 0; 
				do 
				{
					System.out.print("Enter number of people: "); 
					pax = sc.nextInt();
					if (pax <= 0)
						System.out.print("Invalid input. Please enter positive integer.\n");
				} while (pax <= 0); 
				//// what if multiple reservations for same customer? multiple copies ??
				System.out.print("Enter customer name: ");
				custName = sc.nextLine();
				System.out.print("Enter customer contact: ");
				String custContact = sc.nextLine();
				char membership;
				do 
				{
					System.out.print("Is customer a member? (Y/N) ");
					membership = sc.next().toUpperCase().charAt(0);
					if (membership != 'Y' && membership != 'N')
						System.out.print("Invalid input. Please enter Y or N.\n");
				} while (pax <= 0); 
				Customer customer;
				if (membership == 'Y')
					customer = new Customer(custName, custContact, true);
				else
					customer = new Customer(custName, custContact, false);
				resMgr.createReservation(dateTime, pax, customer);
			case 2:
				// check
				System.out.print("Enter customer name: ");
				custName = sc.nextLine();
				resMgr.checkReservation(custName);
			case 3: 
				// remove
				System.out.print("Enter customer name: ");
				custName = sc.nextLine();
				System.out.printf("Enter date and time (%s): ", dateTimeFormatString);
				dateTimeString = sc.next();
				//// datetime error check (future, valid format) ??
				dateTime = dateTimeFormat.parse(dateTimeString);
				resMgr.removeReservation(custName, dateTime);
			case 4:
				// back to main
				System.out.printf("Returning to RRPSS main menu...\n");
				break;
			default:
				// invalid input
				System.out.printf("Invalid option. Please enter option 1-%d.\n", optionList_reservation.length);
			};
		} while (option != 4);
	}
	public static void main(String[] args) {
		// initialize classes
		//// TO UPDATE staffList, menu, tableMgr with inputs from text files!!!
		Dictionary<String, Staff> staffList; // key = employeeID
		Menu menu = new Menu();
		TableManager tableMgr = new TableManager();
		OrderManager orderMgr = new OrderManager();
		ReservationManager resMgr = new ReservationManager();
		
		// run application
		Scanner sc = new Scanner(System.in); 
		int option = 0;
		do {
			System.out.print("====== RRPSS main menu ======\n");
			displayOptions(optionList_main);
			System.out.printf("Enter option number: ");
			option = sc.nextInt();
			int tableNum;
			switch (option) 
			{
			case 1:
				// menu items
				menuHelper(menu, false);
				break;
			case 2:
				// promotions
				menuHelper(menu, true);
				break;
			case 3: 
				// orders
				String employeeID; Staff staff;
				do 
				{
					System.out.printf("Enter employee ID: ");
					employeeID = sc.next();
					staff = staffList.get(employeeID);
				} while (staff != null);
				//// error checking: valid table number, table occupied etc.
				System.out.printf("Enter table number: ");
				tableNum = sc.nextInt();
				orderHelper(orderMgr, staff, tableNum, menu);
				break;
			case 4:
				// reservation bookings
				reservationHelper(resMgr);
				break;
			case 5: 
				// table availability
				int pax = 0; 
				//// error check: maximum pax ??
				do 
				{
					System.out.print("Enter number of people: "); 
					pax = sc.nextInt();
					if (pax <= 0)
						System.out.print("Invalid input. Please enter positive integer.\n");
				} while (pax <= 0);
				System.out.printf("Enter date and time (%s), or NOW: ", dateTimeFormatString);
				String dateTimeString = sc.next();
				
				boolean available = false;
				if (dateTimeString.toUpperCase() == "NOW")
					available = tableMgr.checkAvailability(pax);
				else 
				{
					//// datetime error check (now/future, valid format) ??
					Date dateTime = dateTimeFormat.parse(dateTimeString);
					//// need some way of checking availability for future date
					available = tableMgr.checkAvailability(pax, dateTime);
				}
				if (available)
					System.out.printf("Table for %d AVAILABLE for %s\n", pax, dateTimeString);
				else
					System.out.printf("Table for %d NOT AVAILABLE for %s\n", pax, dateTimeString);
				break;
			case 6:
				// order invoice
				Order order;
				do 
				{
					//// error checking: valid table number ??
					System.out.print("Enter table number: ");
					tableNum = sc.nextInt();
					order = orderMgr.getOrder(tableNum);
					if (order != null)
						break;
					else
						System.out.printf("Invalid input. No existing order for table %d. \n", tableNum);
				} while (true);
				order.printOrderInvoice();
				break;
			case 7: 
				// sale revenue report
				//// error checking: datetime ??
				System.out.printf("Enter start date (%s): ", dateFormatString);
				String start = sc.next();
				Date startDate = dateFormat.parse(start);
				System.out.printf("Enter end date (%s): ", dateFormatString);
				String end = sc.next();
				Date endDate = dateFormat.parse(end);
				orderMgr.generateSalesRevenueReport(startDate, endDate); 
				break;
			case 8:
				// quit
				System.out.printf("Closing RRPSS...\n");
				break;
			default:
				// invalid input
				System.out.printf("Invalid option. Please enter option 1-%d.\n", optionList_main.length);
			};
			
		} while (option != 7);
		sc.close();
	}
}
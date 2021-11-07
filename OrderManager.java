import java.util.*;
import java.util.function.Supplier;

import javax.swing.plaf.synth.SynthSplitPaneUI;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Manages the lists of orders in the restaurant. List of orders include: current order list and completed order list.
* @author Gregory Ong
 * @version 1.0
 * @since 2021-11-06
 */
public class OrderManager {
	/**
	 * The current order list that is the restaurant is serving.
	 */
	private ArrayList<Order> currentOrders;
	/**
	 * The completed order list which represent the orders closed after billing.
	 */
	private ArrayList<Order> completedOrders;

	/**
	 * Class constructor that creates the 2 lists, current orders list and the completed order list.
	 */
	public OrderManager(){
		currentOrders=new ArrayList<Order>();
		completedOrders=new ArrayList<Order>();
	}
	/**
	 * Displays the Sales Revenue Report of the restaurant from a specific start date to the end date.
	 * @param startDate The start date that the report covers.
	 * @param endDate The end date that the report covers.
	 */
	public void generateSalesRevenueReport(LocalDate startDate, LocalDate endDate) {
		//total revenue of the restaurant from the start date to end date.
		float total=0;
		//conversion to date and time for comparison.
		LocalDateTime start= startDate.atStartOfDay();
		LocalDateTime end= endDate.atTime(23,59,59);
		System.out.println("------------------------Sales Revenue Report------------------------");
		System.out.println("From: "+ startDate);
		System.out.println("To: "+ endDate);
		System.out.println();
		//Go through the completed orders list to find orders between the start and end date.
		for(int i=0;i<completedOrders.size();i++){
			if(completedOrders.get(i).getDatetime().compareTo(start)>=0 && completedOrders.get(i).getDatetime().compareTo(end)<=0){
				System.out.println("Order "+ i+1 );
				//In each order, go through the list of order items and display their name 
				//and whether they are Alacarte or promotional items
				for(int j=0;j<completedOrders.get(i).getOrderItems().size();j++){
					System.out.printf("Item "+ j + ": " + completedOrders.get(i).getOrderItems().get(j).getMenuItem().getName()+ " ");
					completedOrders.get(i).getOrderItems().get(j).getMenuItem().checkAlacarte();
					System.out.println();
				}
				//Display the revenue earned by the restaurant for the order which corresponds to price before GST.
				System.out.printf("Total: %.2f\n",completedOrders.get(i).getPriceBefGST());
				System.out.println();
				total+=completedOrders.get(i).getPriceBefGST();
				System.out.println("---------------------------------------------------------------------------");
			}
		}
		System.out.println();
		//Display the total revenue of the restaurant from the start date to the end date.
		System.out.printf("Total Revenue: %.2f\n", total);
		System.out.println("____________________________________________________________________");
	}
	/**
	 * Gets the order in the current order list by tablenumber.
	 * @param tableNumber The tablenumber of the order.
	 * @return
	 */
	public Order getOrder(int tableNumber){
		//Go through the current order list
		for(int i=0;i<currentOrders.size();i++){
			if(currentOrders.get(i).getTableNumber()==tableNumber){
				return currentOrders.get(i);
			}
		}
		//if not found or current order list is empty
		return null;
	}
	/**
	 * Creates order and add it to the current order list. 
	 * @param staff The staff that created this order.
	 * @param datetime The date and time that this order is created.
	 * @param tableNumber The table number of this order.
	 * @param customer The customer that placed this order.
	 */
	public void createOrder(Staff staff,LocalDateTime datetime,int tableNumber,Customer customer) {
		Order order= new Order(staff, datetime, tableNumber ,customer);
		currentOrders.add(order);
	}
	/**
	 * Prints the order invoice(receipt) of the order based on table number.
	 * @param TableNumber The table number of this order.
	 */
	public void printOrderInvoice(int TableNumber) {
		Order curOrder=getOrder(TableNumber);
		//If order is not found
		if(curOrder==null){
			System.out.println("This table currently does not have an order.");
		}
		else{//print order invoice(receipt) and remove this order from the current order list and add it to the completed order list.
			curOrder.printOrderInvoice();
			currentOrders.remove(curOrder);
			completedOrders.add(curOrder);
		}
	}	
}
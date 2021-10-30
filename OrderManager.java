import java.util.*;
import java.util.function.Supplier;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class OrderManager {

	private ArrayList<Order> currentOrders;
	private ArrayList<Order> completedOrders;

	public OrderManager(){
		currentOrders=new ArrayList<Order>();
		completedOrders=new ArrayList<Order>();
	}

	public void generateSalesRevenueReport(String startDate, String endDate) {
		float total=0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		LocalDateTime start=LocalDateTime.parse(startDate, formatter);
		LocalDateTime end=LocalDateTime.parse(endDate, formatter);
		for(int i=0;i<completedOrders.size();i++){
			if(completedOrders.get(i).getDatetime().compareTo(start)>=0 && completedOrders.get(i).getDatetime().compareTo(end)<=0){
				System.out.println("Order "+ i + ": List of items " );
				for(int j=0;j<completedOrders.get(i).getOrder().size();j++){
					System.out.println("Item "+ j + ": " +completedOrders.get(i).getOrder().get(j).getMenuItem() + completedOrders.get(i).getOrder().get(j).checkAlaCarte(););
				}
				System.out.printf("Total: %.2f\n"+completedOrders.get(i).getPriceBefGST());
				System.out.println();
				total+=completedOrders.get(i).getPriceBefGST();
			}
			System.out.println();
			System.out.println("Total Revenue: "+ total);
		}
	}

	public void createOrder(Staff staff,String datetime,int OrderID) {
		Order order= new Order(staff,datetime, OrderID);
		currentOrders.add(order);
	}

	public void viewOrder(int orderID) {
		for(int i=0;i<currentOrders.size();i++){
			if(currentOrders.get(i).getOrderID()==orderID){
				System.out.println("Quantity         Items");
				for(int j=0;j<currentOrders.get(i).getOrder().size();j++){
					System.out.println(currentOrders.get(i).getOrder().get(j).getQuantity() + "     " + currentOrders.get(i).getOrder().get(j).getMenuItem().getName() + "\n");
				}
			}
		}
	}
}
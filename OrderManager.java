
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

	public void generateSalesRevenueReport(LocalDate startDate, LocalDate endDate) {
		float total=0;
		LocalDateTime start= startDate.atStartOfDay();
		LocalDateTime end= endDate.atTime(23,59,59);
		System.out.println("====================SALES REVENUE REPORT========================");
		for(int i=0;i<completedOrders.size();i++){
			if(completedOrders.get(i).getDatetime().compareTo(start)>=0 && completedOrders.get(i).getDatetime().compareTo(end)<=0){
				System.out.println("Order "+ i + ": List of items " );
				for(int j=0;j<completedOrders.get(i).getOrder().size();j++){
					System.out.printf("Item "+ j + ": " + completedOrders.get(i).getOrder().get(j).getMenuItem()+ " ");
					if(completedOrders.get(i).getOrder().get(j).getMenuItem().checkAlacarte()) System.out.println("Ala-carte");
					else System.out.println("Promotion");
				}
				System.out.printf("Total: %.2f\n"+completedOrders.get(i).getPriceBefGST());
				System.out.println();
				total+=completedOrders.get(i).getPriceBefGST();
			}
			System.out.println();
		}
		System.out.println("Total Revenue: "+ total);
		System.out.println("____________________________________________________________");
	}

	public Order getOrder(int tableNumber){
		for(int i=0;i<currentOrders.size();i++){
			if(currentOrders.get(i).getTableNumber()==tableNumber){
				return currentOrders.get(i);
			}
		}
		return null;
	}


	public void createOrder(Staff staff,LocalDateTime datetime,int tableNumber,Customer customer) {
		Order order= new Order(staff, datetime, tableNumber ,customer);
		currentOrders.add(order);
	}


}
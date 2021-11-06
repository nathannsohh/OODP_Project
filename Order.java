import java.util.*;
import java.util.function.Supplier;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 * 
 */
public class Order {

	private Staff staff;
	private ArrayList<OrderItem> orderItems;
	private LocalDateTime datetime;
	private float subtotal;
	private float priceBefGST;
	private int tableNumber;
	private Customer customer;


	public Order(Staff staff,LocalDateTime datetime,int tableNumber, Customer customer){
		orderItems=new ArrayList<OrderItem>();
		this.staff=staff;
		this.datetime = datetime;
		this.tableNumber=tableNumber;
		this.customer=customer;
	}
	
	
	/** 
	 * @return LocalDateTime
	 */
	public LocalDateTime getDatetime(){
		return this.datetime;
	}
	
	
	/** 
	 * @return float
	 */
	public float getPriceBefGST(){
		return priceBefGST;
	}

	
	/** 
	 * @return int
	 */
	public int getTableNumber(){
		return tableNumber;
	}

	
	/** 
	 * @return ArrayList<OrderItem>
	 */
	public ArrayList<OrderItem> getOrder(){
		return this.orderItems;
	}
	
	
	/** 
	 * @param item
	 * @param quantity
	 */
	public void addItem(MenuItem item,int quantity) {
		OrderItem orderitem=new OrderItem(item,quantity);
		orderItems.add(orderitem);
	}

	
	/** 
	 * @param item
	 * @param quantity
	 */
	public void removeItem(MenuItem item,int quantity) { 
		for(int i = 0; i < orderItems.size(); i++){
			if(orderItems.get(i).getMenuItem()==item){
				if( (orderItems.get(i).getQuantity()-quantity) < 0 ){
					System.out.println("Insufficient quantity to remove!");
				}
				else{
					if(orderItems.get(i).getQuantity()-quantity==0){
						orderItems.remove(i);
						System.out.println(quantity + " " + item.getName() +" removed!");
					}
					else{
						orderItems.get(i).setQuantity(orderItems.get(i).getQuantity()-quantity);
						System.out.println(quantity + " " + item.getName() +" removed!");
					}
				}
			}
			else{
				System.out.println("No such item in the order!");
			}
		}
	}
	
	
	public void viewOrder() {
		System.out.println("Quantity         Items");
		for(int j=0;j<orderItems.size();j++){
				System.out.println(orderItems.get(j).getQuantity() + "     " + orderItems.get(j).getMenuItem().getName() + "\n");
		}
	}


	public void printOrderInvoice() {
		float discount=0;
		float price=0;
		System.out.println("                     MacDonalds                        ");
		System.out.println("*******************************************************");
		System.out.println("Table Number: " + tableNumber);
		System.out.println(datetime);
		System.out.println("Served by: "+ staff.getName());
		System.out.println("_______________________________________________________");
		System.out.println("Quantity      Item                            Price");
		for(int i=0;i<orderItems.size();i++){
			System.out.println(orderItems.get(i).getQuantity()+ orderItems.get(i).getMenuItem().getName() +"     "+ orderItems.get(i).getMenuItem().getPrice()*orderItems.get(i).getQuantity() + "\n");
			subtotal+= orderItems.get(i).getMenuItem().getPrice()*orderItems.get(i).getQuantity();
		}
		System.out.println("_______________________________________________________");
		System.out.printf("                                      Subtotal: %.2f\n" , subtotal);
		if(customer.getMember()==true){
			discount=(float)(subtotal*0.10);
			System.out.printf("			             Discount: %.2f\n", discount);
		}
		priceBefGST=(float)((subtotal-discount)*1.10);
		System.out.printf("                               10%% Service Charge: %.2f\n" , (float)(subtotal-discount)*0.10);
		System.out.printf("                                7%% Service Charge: %.2f\n" , priceBefGST*0.07);
		price=(float)(priceBefGST*1.07);
		System.out.printf("                                Total: %.2f\n", price);
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("             Thank You For Dining With Us!             ");
		System.out.println("*******************************************************");
		
	}

}
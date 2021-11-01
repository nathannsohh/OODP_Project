import java.util.*;
import java.util.function.Supplier;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Order {

	private Staff staff;
	private ArrayList<OrderItem> order;
	private Date datetime;
	private float subtotal;
	private float priceBefGST;
	private int tableNumber;
	private Customer customer;


	public Order(Staff staff,Date datetime,int tableNumber, Customer customer){
		order=new ArrayList<OrderItem>();
		this.staff=staff;
		this.datetime = datetime;
		this.tableNumber=tableNumber;
		this.customer=customer;
	}
	
	public Date getDatetime(){
		return this.datetime;
	}
	
	public float getPriceBefGST(){
		return priceBefGST;
	}

	public int getTableNumber(){
		return tableNumber;
	}

	public ArrayList<OrderItem> getOrder(){
		return this.order;
	}
	
	public void addItem(MenuItem itemname,int quantity) {
		OrderItem orderitem=new OrderItem(itemname,quantity);
		order.add(orderitem);
	}

	public void removeItem(MenuItem itemname,int quantity) { 
		for(int i = 0; i < order.size(); i++){
			if(order.get(i).getMenuItem()==itemname){
				if( (order.get(i).getQuantity()-quantity) < 0 ){
					System.out.println("Insufficient quantity to remove!");
				}
				else{
					if(order.get(i).getQuantity()-quantity==0){
						order.remove(i);
						System.out.println(quantity + " " + itemname +" removed!");
					}
					else{
						order.get(i).setQuantity(order.get(i).getQuantity()-quantity);
						System.out.println(quantity + " " + itemname +" removed!");
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
		for(int j=0;j<order.size();j++){
				System.out.println(order.get(j).getQuantity() + "     " + order.get(j).getMenuItem().getName() + "\n");
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
		for(int i=0;i<order.size();i++){
			System.out.println(order.get(i).getQuantity()+ order.get(i).getMenuItem().getName() +"     "+ order.get(i).getMenuItem().getPrice()*order.get(i).getQuantity() + "\n");
			subtotal+= order.get(i).getMenuItem().getPrice()*order.get(i).getQuantity();
		}
		System.out.println("_______________________________________________________");
		System.out.printf("                                      Subtotal: %.2f\n" + subtotal);
		if(customer.getMember()==true){
			discount=(float)(subtotal*0.10);
			System.out.println("Discount:                              Discount: %.2f\n"+ discount);
		}
		priceBefGST=(float)((subtotal-discount)*1.10);
		System.out.printf("                               10% Service Charge: %.2f\n" + (subtotal-discount)*0.10);
		System.out.printf("                                7% Service Charge: %.2f\n" + priceBefGST*0.07);
		price=(float)(priceBefGST*1.07);
		System.out.printf("                                Total= %.2f\n"+ price);
		System.out.println();
		System.out.println("*******************************************************");
		System.out.println("             Thank You For Dining With Us!             ");
		System.out.println("*******************************************************");
		
	}

}
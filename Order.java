import java.util.Date;
import java.util.List;

public class Order {

	Staff Waiter;
	private Staff staff;
	private List<OrderItem> orderItems;
	private Date datetime;
	private float price;
	private float subtotal;
	private float priceBefGST;

	public void addItem(MenuItem item, int quantity) {
		// TODO - implement Order.addItem
		throw new UnsupportedOperationException();
	}

	public void removeItem(MenuItem item, int quantity) {
		// TODO - implement Order.removeItem
		throw new UnsupportedOperationException();
	}

	public void viewOrder() {
		// TODO - implement OrderManager.viewOrder
		throw new UnsupportedOperationException();
	}

	public void printOrderInvoice() {
		// TODO - implement Order.printOrderInvoice
		throw new UnsupportedOperationException();
	}

}
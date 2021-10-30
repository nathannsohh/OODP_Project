public class OrderItem {

	private MenuItem item;
	private int quantity;

	public OrderItem(MenuItem item,int quantity){
		this.item=item;
		this.quantity=quantity;
	}

	public int getQuantity(){
		return quantity;
	}

	public MenuItem getMenuItem(){
		return this.item;
	}
	
	public void setQuantity(int newQuantity){
		quantity=newQuantity;
	}
	
}
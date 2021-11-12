package cz2002.rrpss.order;

import cz2002.rrpss.menu.MenuItem;

/**
 * Represents an order item in an order.
 * 
 * @author Gregory Ong
 * @version 1.0
 * @since 2021-11-06
 */
public class OrderItem {
	/**
	 * The corresponding menu item that make this order item.
	 */
	private MenuItem item;
	/**
	 * The quantity of this order item.
	 */
	private int quantity;

	/**
	 * Class constructor that creates an order item with attributes menu item and
	 * quantity.
	 * 
	 * @param item     This order item's corresponding menu item.
	 * @param quantity The quantity of this order item.
	 */
	public OrderItem(MenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	/**
	 * Gets the quantity of this order item.
	 * 
	 * @return The quantity of this order item.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity of this order item.
	 * 
	 * @param newQuantity The quantity of this order item.
	 */
	public void setQuantity(int newQuantity) {
		quantity = newQuantity;
	}

	/**
	 * Gets the menu item of this order item.
	 * 
	 * @return This order item's corresponding menu item.
	 */
	public MenuItem getMenuItem() {
		return this.item;
	}

}
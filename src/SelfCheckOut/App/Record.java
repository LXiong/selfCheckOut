/**
 * 
 */
package SelfCheckOut.App;

import java.util.Enumeration;
import java.util.Calendar;
import java.util.Vector;

/**
 * @author g1sawaf
 * A class that stores relevant information to a single transaction that has been made. 
 * Each transaction is stored in a separate Record object.
 */

public class Record {
	
	/*
	 * The record ID
	 */
	private int rID;
	
	/*
	 * The time of the transaction.
	 */
	private Calendar time;
	
	/*
	 * The total price of the transaction before tax.
	 */
	private double totPrice;
	
	/*
	 * The tax owed to the government from that transaction
	 */
	private double tax;
	
	/*
	 * Status of the transaction.
	 * False correlates to a failed transaction, True correlates to a successful transaction.
	 */
	private boolean status;
	
	/*
	 * Payment method, by default it is cash. Allows architectural support for future implementation for alternatives like credit cards etc.
	 */
	private String paymentMethod;
	
	/*
	 * A vector that stores all the items of that transaction.
	 */
	private Vector<GroceryItem> items;
	
	/*
	 * Class constructor that initializes variables given a CheckOutCart. 
	 */
	public Record(CheckOutCart cart, int recordID) {
		
		totPrice = cart.getTotalPreTaxCost();
		tax = cart.getTotalCost() - totPrice;
		paymentMethod = "cash";
		Enumeration<GroceryItem> cartItems = cart.listItems();
		items = new Vector<GroceryItem>();
		while (cartItems.hasMoreElements()) {
			items.add(cartItems.nextElement());
		}
		status = true;
		rID = recordID;
	}
	
	/*
	 * Return the total price after tax. 
	 */
	public double getPostTaxPrice(){
		return totPrice + tax;
	}
	
	/*
	 * Return price before tax.
	 */
	public double getPreTaxPrice(){
		return totPrice;
	}
	
	/*
	 * Return tax.
	 */
	public Calendar getTime(){
		return time;
	}
	
	/*
	 * Return tax.
	 */
	public void setTime(Calendar now){
		time = now;
	}
	
	/*
	 * Modify time
	 */
	public void modifyTime(int year, int month, int date) {
		time.set(year, month, date);
	}
	
	
	/*
	 * Return tax.
	 */
	public double getTax(){
		return tax;
	}
	
	/*
	 * Return the status of the transaction.
	 */
	public boolean getStatus(){
		return status;
	}
	
	/*
	 * Return the method used to pay for the transaction.
	 */
	public String getPaymentMethod(){
		return paymentMethod;
	}
	
	/*
	 * Return the Record ID
	 */
	public int getRID(){
		return rID;
	}
	
	/*
	 * List all the items in the checkout cart.
	 */
	public Enumeration<GroceryItem> getItems(){
		return items.elements();
	}
	
	/*
	 * Return a vector with all GroceryItems on promotion.
	 */
	public Vector<GroceryItem> getItemsOnPromotion(){
		Vector<GroceryItem> promoItems = new Vector<GroceryItem>();
		Enumeration<GroceryItem> itemsList = getItems();
		while ( getItems().hasMoreElements() ) {
			GroceryItem grocery = itemsList.nextElement();
			if ( grocery.getPromotion() ) {
				promoItems.add(grocery);
			}
		}
		return promoItems;
		
	}
}

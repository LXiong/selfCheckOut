/*
 * Creator: Susan Elliott Sim
 * 
 * Created on May 10, 2006
 * Updated on January 17, 2008, September 12, 2012
 * 
 * The CheckOutCart class maintains the items added as well as the total weight (tracked by the system, NOT 
 * the bagging area) and cost of the items.
 */

package SelfCheckOut.App;

import java.util.Vector;
import java.util.*;

/**
 * The CheckOutCart class stores a Vector of GroceryItems which
 * the customer has scanned.  It represents the products which the 
 * customer has scanned and bagged so far in the transaction.
 *
 */
public class CheckOutCart {
	/**
	 * A Vector of GroceryItems.
	 */
	private Vector<GroceryItem> myItems; // stores the items added

	/**
	 * The cost of the items in the cart
	 */
	private double totalCost;

	/**
	 * The predicted weight of the items in the cart.
	 */
	private double totalWeight; // total weight of the items, tracked by the
								// system. Can be used to compare with the
								// weight in the bagging area.

	private double totalPreTaxCost;
	private String prize;

	/**
	 * Creates a new CheckOutCart with an empty item list, 0 cost and 0 weight.
	 */
	public CheckOutCart() {
		myItems = new Vector<GroceryItem>();
		totalCost = 0;
		totalWeight = 0;
	}

	/**
	 * Accessor method which returns the cost of the items in the cart after tax.
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * Accessor method which returns the cost of the items in the cart before tax;
	 */
	public double getTotalPreTaxCost() {
		return totalPreTaxCost; // to trick the customer into buying more!
	}

	/**
	 * Accessor method which returns the predicted weight of the items in the cart.
	 */
	public double getTotalWeight() {
		return totalWeight;
	}

	/**
	 * Add a single item to the cart, and add its weight and cost to the running totals.
	 * @param newItem
	 */
	public void addItemToCart(GroceryItem newItem) {
		myItems.add(newItem);
		totalPreTaxCost = totalPreTaxCost + newItem.getPrice();
		totalCost = totalCost + newItem.getFinalPrice(); // to account for tax
		totalWeight = totalWeight + newItem.getWeight();
	}

	/**
	 * This method returns an enumeration of the GroceryItems in the cart.  We don't
	 * return the Vector since we don't want external code to alter our cart.
	 */
	public Enumeration<GroceryItem> listItems() {
		Enumeration<GroceryItem> enumItems = myItems.elements();
		return enumItems;
	}

	/**
	 * Accessor method which returns a string corresponding to the randomly awarded transaction prize
	 * Or null otherwise
	 */
	public String getPrize() {
		return prize;
	}


	/**
	 * Accessor method to set the feild coresponding to the randomly awarded transaction prize
	 */
	public void setPrize(String prize) {
		this.prize = prize;
	}
}

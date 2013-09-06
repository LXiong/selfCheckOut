package SelfCheckOut.App;

/**
 * The Day class represent's a day when at least one transaction happened. All further
 * transactions until 11:59:59pm of that day will be recorded in the records vector.
 * In addition to storing records, the Day class supports getting the most popular
 * promotions, with 3 different methods of sorting implemented.
 */

import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Map.Entry;

public class Day {
	/**
	 * the day's date
	 */
	private Calendar date;

	/**
	 * all transaction records for this particular day
	 */
	private Vector<Record> records;

	/*
	 * Constructor method to create the current day
	 */
	public Day() {
		date = Calendar.getInstance();
		records = new Vector<Record>();
	}

	/**
	 * Accessor method returning the day's date
	 */
	public Calendar getDate() {
		return date;
	}

	/**
	 * Accessor method returning the day's date
	 */
	public Vector<Record> getRecords() {
		return records;
	}
	
	public void addRecord(Record record) {
		record.setTime(Calendar.getInstance());
		records.add(record);
	}

	/**
	 * Return the top promotions according to the parameter specified, with the number
	 * of promotions limited by count
	 */
	public Vector<GroceryItem> getPopularPromotions(String parameter, int count) {
		
		Vector<GroceryItem> items = new Vector<GroceryItem>();
		for (Record record : records) {
			for (GroceryItem item : record.getItemsOnPromotion()) {
				items.add(item);
			}
		}
		
		Vector<GroceryItem> result = new Vector<GroceryItem>();

		// better with a switch statement
		
		if (parameter.equals("quantity")) {
			result = sortPromotionsQuantity(items, count);
		}
		else if (parameter.equals("price")) {
			result = sortPromotionsPrice(items, count);
		}

		return result;
	}
	
	/**
	 * Store cart in a hashtable aggregating the quantity of each item. 
	 * The key is the GroceryItem and the value is its quantity.
	 * 
	 * @param A vector of GroceryItems.
	 */
	private Hashtable<GroceryItem, Integer> getCartQuantity(Vector<GroceryItem> cart) {
		
		Hashtable<GroceryItem, Integer> itemTable = new Hashtable<GroceryItem, Integer>();
		int i;
		for (i=0; i<cart.size(); i++) {
			if ( itemTable.containsKey(cart.get(i))){
				itemTable.put(cart.get(i), itemTable.get(cart.get(i)) + 1);
			}
			else {
				itemTable.put(cart.get(i), 1);
			}
		}
		return itemTable;
	}
	
	/**
	 * Store cart in a hashtable aggregating the quantity of each item. 
	 * The key is the GroceryItem and the value is its quantity.
	 * 
	 * @param A vector of GroceryItems.
	 */
	private Hashtable<GroceryItem, Double> getCartAggregatePrice(Vector<GroceryItem> cart) {
		
		Hashtable<GroceryItem, Double> itemTable = new Hashtable<GroceryItem, Double>();
		int i;
		for (i=0; i<cart.size(); i++) {
			if ( itemTable.containsKey(cart.get(i))){
				//increment the price
				itemTable.put(cart.get(i), itemTable.get(cart.get(i)) + cart.get(i).getPrice());
			}
			else {
				itemTable.put(cart.get(i), cart.get(i).getPrice());
			}
		}
		return itemTable;
	}
	
	/**
	 * Sort the items in the vector by quantity.
	 * Sort is done by descending order.
	 */
	private Vector<GroceryItem> sortPromotionsQuantity(Vector<GroceryItem> cart, int count) {
		
		Hashtable<GroceryItem, Integer> itemTable = getCartQuantity(cart);
		//new sorted vector to be returned.
		Vector<GroceryItem> sortedCart = new Vector<GroceryItem>();
		
		// to store the highest value
		int max;
		while (count > 0) {
			max=(Collections.max(itemTable.values()));  // This will return max value in the Hashmap
	        for (Entry<GroceryItem, Integer> entry : itemTable.entrySet()) {  // Iterate through hashmap
	            if (entry.getValue()==max) {
	                sortedCart.add(entry.getKey()); //add the value to the list, then remove it from the hashtable.
	                itemTable.remove(entry.getKey());
	                break;
	            }
	        }
	        count --;
		}
		return sortedCart;
	}
	
	
	/**
	 * Sort the items in the vector by price.
	 * Sort is done by descending order.
	 */
	private Vector<GroceryItem> sortPromotionsPrice(Vector<GroceryItem> cart, int count) {
		
		Hashtable<GroceryItem, Double> itemTable = getCartAggregatePrice(cart);
		//new sorted vector to be returned.
		Vector<GroceryItem> sortedCart = new Vector<GroceryItem>();
		
		// to store the highest value
		Double max;
		while (count > 0) {
			max=(Collections.max(itemTable.values()));  // This will return max value in the Hashmap
	        for (Entry<GroceryItem, Double> entry : itemTable.entrySet()) {  // Iterate through hashmap
	            if (entry.getValue()==max) {
	                sortedCart.add(entry.getKey()); //add the value to the list, then remove it from the hashtable.
	                itemTable.remove(entry.getKey());
	                break;
	            }
	        }
	        count --;
		}
		return sortedCart;
	}
}

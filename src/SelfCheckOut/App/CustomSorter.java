/**
 * 
 */
package SelfCheckOut.App;

import java.util.Vector;

/**
 * @author Zafer Sawaf
 * This class is responsible for sorting according to user request.
 * A user can choose to sort by Product name, Category, Number of Items
 * and Price.
 * 
 * I can't use comparators for this class due to the odd types, therefore
 * I'm going to do less efficient sorting.
 */
public class CustomSorter {
	
	/**
	 * Sort items by Product name. 
	 * Input is a vector of all the grocerytableelement,
	 */
	public Vector<GroceryTableElement> sortByProducts(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		GroceryTableElement highest;
		while (!groc.isEmpty()){
			if (option == 0) {
				lowest = getLowestString(groc, 0);
				sorted.add(lowest);
				groc.remove(lowest);
			}
			else {
				highest = getHighestString(groc, 0);
				sorted.add(highest);
				groc.remove(highest);
			}
		}
		return sorted;
	}
	
	/**
	 * Sort items by Category
	 * @param A vector of all GroceryTableElements
	 * @return A sorted Vector
	 */
	
	public Vector<GroceryTableElement> sortByCategory(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		GroceryTableElement highest;
		while (!groc.isEmpty()){
			if (option == 0) {
				lowest = getLowestString(groc, 1);
				sorted.add(lowest);
				groc.remove(lowest);
			}
			else {
				highest = getHighestString(groc, 1);
				sorted.add(highest);
				groc.remove(highest);
			}
		}
		return sorted;
	}
	
	/**
	 * Sort items by Category
	 * @param A vector of all GroceryTableElements
	 * @return A sorted Vector
	 */
	
	public Vector<GroceryTableElement> sortByPromotion(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		GroceryTableElement highest;
		while (!groc.isEmpty()){
			if (option == 0) {
				lowest = getLowestString(groc, 2);
				sorted.add(lowest);
				groc.remove(lowest);
			}
			else {
				highest = getHighestString(groc, 2);
				sorted.add(highest);
				groc.remove(highest);
			}
		}
		return sorted;
	}
	
	/**
	 * Sort items by number of purchases.
	 * @param A vector of all GroceryTableElements
	 * @return A sorted Vector
	 */
	public Vector<GroceryTableElement> sortByPurchases(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		while (!groc.isEmpty()){
			lowest = getLowestInt(groc, option);
			sorted.add(lowest);
			groc.remove(lowest);
		}
		return sorted;
	}
	
	/**
	 * Sort items by price.
	 * @param A vector of all GroceryTableElements
	 * @return A sorted Vector
	 */
	public Vector<GroceryTableElement> sortByPrice(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		while (!groc.isEmpty()){
			lowest = getLowestPrice(groc, option);
			sorted.add(lowest);
			groc.remove(lowest);
		}
		return sorted;
	}
	
	/**
	 * Sort items by price.
	 * @param A vector of all GroceryTableElements
	 * @return A sorted Vector
	 */
	public Vector<GroceryTableElement> sortByTax(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		while (!groc.isEmpty()){
			lowest = getLowestTax(groc, option);
			sorted.add(lowest);
			groc.remove(lowest);
		}
		return sorted;
	}
	
	/**
	 * Sort items by price.
	 * @param A vector of all GroceryTableElements
	 * @return A sorted Vector
	 */
	public Vector<GroceryTableElement> sortByWeight(Vector<GroceryTableElement> groc, int option) {
		Vector<GroceryTableElement> sorted = new Vector<GroceryTableElement>();
		GroceryTableElement lowest;
		while (!groc.isEmpty()){
			lowest = getLowestWeight(groc, option);
			sorted.add(lowest);
			groc.remove(lowest);
		}
		return sorted;
	}
	
	/**
	 * Helper function for sorting strings in ascending order.
	 */
	
	private GroceryTableElement getLowestString(Vector<GroceryTableElement> groc, int option){
		
		if (option == 0){
			String lowest = groc.elementAt(0).getProductName();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest.compareTo(g.getProductName()) >= 1) {
					lowest = g.getProductName();
					index = g;
				}
			}
			
			return index;
		}
		else if (option == 1){
			String lowest = groc.elementAt(0).getProductCategory();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest.compareToIgnoreCase(g.getProductCategory()) >= 1) {
					lowest = g.getProductCategory();
					index = g;
				}
			}
			
			return index;
		}
		else {
			Boolean lowest = groc.elementAt(0).getPromotion();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest.compareTo(g.getPromotion()) >= 1) {
					lowest = g.getPromotion();
					index = g;
				}
			}
			
			return index;
		}
	}
	
	
	/**
	 * Helper function for sorting strings in descending order.
	 */
	
	private GroceryTableElement getHighestString(Vector<GroceryTableElement> groc, int option){
		
		if (option == 0){
			String highest = groc.elementAt(0).getProductName();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (highest.compareTo(g.getProductName()) < 1) {
					highest = g.getProductName();
					index = g;
				}
			}
			
			return index;
		}
		else if (option == 1){
			String highest = groc.elementAt(0).getProductCategory();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (highest.compareToIgnoreCase(g.getProductCategory()) < 1) {
					highest = g.getProductCategory();
					index = g;
				}
			}
			
			return index;
		}
		else {
			Boolean highestBool = groc.elementAt(0).getPromotion();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (highestBool.compareTo(g.getPromotion()) < 1) {
					highestBool = g.getPromotion();
					index = g;
				}
			}
			
			return index;
		}
	}
	/*
	 * Helper method for sorting number of purchases
	 */
	private GroceryTableElement getLowestInt(Vector<GroceryTableElement> groc, int option) {
		if (option == 0) {
			int lowest = groc.elementAt(0).getNumPurchases();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest < g.getNumPurchases()) {
					lowest = g.getNumPurchases();
					index = g;
				}
			}
			return index;
		}
		else {
			int lowest = groc.elementAt(0).getNumPurchases();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest > g.getNumPurchases()) {
					lowest = g.getNumPurchases();
					index = g;
				}
			}
			return index;
		}
	}
	
	/*
	 * Helper method for sorting prices
	 */
	private GroceryTableElement getLowestPrice(Vector<GroceryTableElement> groc, int option) {
		if (option == 0) {
			double lowest = groc.elementAt(0).getTotalPrice();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest < g.getTotalPrice()) {
					lowest = g.getTotalPrice();
					index = g;
				}
			}
			return index;
		}
		else {
			double lowest = groc.elementAt(0).getTotalPrice();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest > g.getTotalPrice()) {
					lowest = g.getTotalPrice();
					index = g;
				}
			}
			return index;
		}
	}
	
	/*
	 * Helper method for sorting taxes
	 */
	private GroceryTableElement getLowestTax(Vector<GroceryTableElement> groc, int option) {
		if (option == 0) {
			double lowest = groc.elementAt(0).getTotalTax();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest < g.getTotalTax()) {
					lowest = g.getTotalTax();
					index = g;
				}
			}
			return index;
		}
		else {
			double lowest = groc.elementAt(0).getTotalTax();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest > g.getTotalTax()) {
					lowest = g.getTotalTax();
					index = g;
				}
			}
			return index;
		}
	}
	
	/*
	 * Helper method for sorting weight
	 */
	private GroceryTableElement getLowestWeight(Vector<GroceryTableElement> groc, int option) {
		if (option == 0) {
			double lowest = groc.elementAt(0).getWeight();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest < g.getWeight()) {
					lowest = g.getWeight();
					index = g;
				}
			}
			return index;
		}
		else {
			double lowest = groc.elementAt(0).getWeight();
			GroceryTableElement index = groc.elementAt(0);
			for (GroceryTableElement g : groc){
				if (lowest > g.getWeight()) {
					lowest = g.getWeight();
					index = g;
				}
			}
			return index;
		}
	}
}



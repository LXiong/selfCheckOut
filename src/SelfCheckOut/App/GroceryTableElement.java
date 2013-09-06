package SelfCheckOut.App;

import java.text.DecimalFormat;


/**
 * 
 * @author g0budnin (sean)
 * 
 * This class holds information for a single table entry in the GUI table which displays information about records.
 * These entries are sorted (by the class RecordSorter) into an arraylist which is displayed by the GUI.
 *
 */
public class GroceryTableElement {
	
	private String productName;
	private String productCategory;
	private int numPurchases;
	private double totalPrice;
	private double totalTax;
	private double weight;
	private boolean promotion;   // true => this product is on promotion

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setNumPurchases(int numPurchases) {
		this.numPurchases = numPurchases;
	}

	public int getNumPurchases() {
		return numPurchases;
	}

	public String printNumPurchases() {
		return ("" + numPurchases);
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public String printTotalPrice() {
		return toTwoDecimal(totalPrice);
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public String printTotalTax() {
		return toTwoDecimal(totalTax);
	}

	public void setPromotion(boolean promotion) {
		this.promotion = promotion;
	}

	public boolean getPromotion() {
		return promotion;
	}

	public String printPromotion() {
		String returnValue = "no";
		if (promotion) {
			returnValue = "yes";
		}
		return returnValue;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public String printWeight() {
		return toTwoDecimal(weight);
	}
	
	/**
	 * Clear the GroceryTableElement
	 */
	public void clear() {
		setProductName("");
		setProductCategory("");
		setNumPurchases(0);
		setTotalPrice(0.0);
		setTotalTax(0.0);
		setPromotion(false);
		setWeight(0.0);
	}
	
	/**
	 * Given a string of random decimal places, return a string of 2 decimal places. 
	 */
	public String toTwoDecimal(double num){
		String ret;
		DecimalFormat myFormatter = new DecimalFormat("0.00");
		ret = myFormatter.format(num);
		return ret;
	}
}

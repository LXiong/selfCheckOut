/*
 * Creator: Susan Elliott Sim
 * 
 * Created on May 10, 2006
 * Updated on January 17, 2008, September 12, 2012
 * 
 * The BulkProduct class is for products with a BIC code. It implements the ProductInfo interface.
 */

package SelfCheckOut.App;

/**
 * The BulkProduct class represents a grocery product which is sold by
 * weight, such as produce, deli, meat, etc.  The details of the product
 * are stored at the time of construction, and can be retrieved using
 * accessor functions.
 *
 */
public class BulkProduct implements ProductInfo {
	/**
	 * The BIC representing the product's 5-digit bulk item code.
	 */
	private BIC myBIC;

	/**
	 * The price per unit weight of the product
	 */
	private double myUnitPrice;

	/**
	 * The String description of the product.
	 */
	private String myDescription;

	/**
	* The category of the product.
	*/
	private String myCategory;

	/**
	 * @param description 	The text description of the product.
	 * @param BICcode 		A BIC representing the product's 5-digit bulk item code.
	 * @param price 		The unit price of the product.
	 * @param category		The category the product.
	 */
	public BulkProduct(String description, BIC BICcode, double price, String category) {
		myDescription = description;
		myBIC = BICcode;
		myUnitPrice = price;
		// Throw exception if category does not exist.
		if (CategoryDB.getInstance().existCategory(category)) {
			myCategory = category;
		} else {
			// throw exception for invalid category
		}
	}

	/**
	 * Accessor function returning the product's BIC
	 */
	public BIC getBIC() {
		return myBIC;
	}
	
	/* (non-Javadoc)
	 * @see edu.uci.ics121.SelfCheckOut.App.ProductInfo#getCode()
	 */
	@Override
	public Code getCode() {
		return getBIC();
	}

	/* (non-Javadoc)
	 * @see edu.uci.ics121.SelfCheckOut.App.ProductInfo#getPrice()
	 */
	@Override
	public double getPrice() {
		return myUnitPrice;
	}

	/* (non-Javadoc)
	 * @see edu.uci.ics121.SelfCheckOut.App.ProductInfo#getDescription()
	 */
	@Override
	public String getDescription() {
		return myDescription;
	}

	/**
	 * A setter method setting the category of the item.
	 */
	@Override
	public void setCategory(String itemCategory) {
		myCategory = itemCategory;
	}

	/**
	 * An accessor method returning the category of the item.
	 */
	@Override
	public String getCategory() {
		return myCategory;
	}

	/**
	 * Accessor method returning the actual tax rate for the item per kilo.
	 */
	@Override
	public double getTaxRate() {
		return  CategoryDB.getInstance().getTaxRate(myCategory);
	}

	/**
	 * Accessor method returning the post-tax unit price.
	 */
	@Override
	public double getPostTaxPrice() {
		return (getPrice() * (1 + getTaxRate()/100));
	}

}

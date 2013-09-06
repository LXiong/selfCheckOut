/*
 * Creator: Susan Elliott Sim
 * 
 * Created on May 10, 2006
 * Updated on January 17, 2008, September 12, 2012
 * 
 * The ProductInfo interface is implemented by BulkProduct and PackagedProduct.
 */

package SelfCheckOut.App;

/**
 * The ProductInfo interface is implemented by any class which represents a saleable product
 * in our store.  In out example, these will be the BulkProduct and PackagedProduct.  These 
 * products differ in the manner in which their prices are calculated, but have in common a
 * descriptor, price, identifying code, and category.  The interface provides common accessor 
 * methods for these fields.
 *
 */
public interface ProductInfo {
	/**
	 * Accessor method for product description
	 */
	public String getDescription();

	/**
	 * Accessor method for unit price
	 */
	public double getPrice();
	
	/**
	 * Accessor method for identifying code, either BIC or UPC.  (Both are of type Code)
	 */
	public Code getCode();

	/**
	 * A setter method setting the actual category of the item.
	 */
	public void setCategory(String itemCategory);

	/**
	 * Accessor method returning the actual category of the item.
	 */
	public String getCategory();

	/**
	 * Accessor method returning the actual tax rate for the item.
	 */
	public double getTaxRate();

	/**
	 * Accessor method returning the post-tax unit price.
	 */
	public double getPostTaxPrice();
}

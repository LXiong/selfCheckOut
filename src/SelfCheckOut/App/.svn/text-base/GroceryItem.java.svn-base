/*
 * 
 * Creator: Susan Elliott Sim
 * 
 * Created on May 10, 2006
 * Updated on January 17, 2008, September 12, 2012
 * 
 * The GroceryItem class.
 * Each item in the user's CheckOutCart is a GroceryItem object which contains product information,
 * the price (total for this item), and its weight.
 */

package SelfCheckOut.App;

/**
 * A GroceryItem object represents a specific item which has been scanned by
 * the customer.  A separate weight and cost are recorded when the object is
 * created, since BulkProducts only include the <i>unit</i> price, and we
 * require the item's actual weight and cost in order to compute the bill.
 *
 */
public class GroceryItem {
	/**
	 * The actual price of the item.
	 */
	private double price;

	/**
	 * The actual weight of the item.
	 */
	private double weight;

	/**
	 * The ProductInfo for the item, either a PackagedProduct or BulkProduct.
	 */
	private ProductInfo info;
	

	/**
	 * The promotion is a boolean value, depicting whether GroceryItem is an item
	 * (or collection of items) is currently a promotion
	 */

	private boolean promotion;


	/**
	 * This constructor records the information about this item, which can be retrieved using accessor methods.
	 * @param productInfo	The information (description, code) of the item being scanned.
	 * @param productCost	The cost of the item.  For PackagedProducts, this is simply the cost, but for BulkProducts this is computed based on the item's actual weight.
	 * @param productWeight	The weight of the item.  For PackagedProducts, this is simply the listed weight, but for BulkProducts this represents the amount of product purchased.
	 */
	public GroceryItem(ProductInfo productInfo, double productCost,
			double productWeight) {
		info = productInfo;
		price = productCost;
		weight = productWeight;
		promotion = false;
	}

	/**
	 * An accessor method returning the actual price of the item.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * An accessor method returning the actual weight of the item.	 
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * An accessor method returning the ProductInfo of the item.
	 */
	public ProductInfo getInfo() {
		return info;
	}

	/**
	 * A method calculating the post-tax price and returning it.
	 */
	public double getFinalPrice() {
		return (price * (1 + info.getTaxRate()/100));
	}
	
	/**
	 * An accessor method returning whether the item is on promotion.
	 */
	public boolean getPromotion() {
		return promotion;
	}
	
	/**
	 * An accessor method setting the item on promotion.
	 */
	public void setPromotion() {
		promotion = true;
	}
}

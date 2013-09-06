/*
 * Creator: Susan Elliott Sim
 * 
 * Created on May 10, 2006
 * Updated on January 17, 2008, September 12, 2012
 * 
 * The PackagedProduct class is for products with a UPC code. It implements the ProductInfo interface.
 */

package SelfCheckOut.App;

/**
 * A PackedProduct represents a single UPC-code-bearing product in the store.
 * Packaged products are sold as discrete single units, and never by weight.
 * Note the difference between 'items' and 'products':  A 'product' is a type
 * of good sold at the store, whereas an 'item' is a particular box of that
 * product.  
 *
 */
public class PackagedProduct implements ProductInfo {
	/**
	 * The UPC for this product. 
	 */
	private UPC myUPC;

	/**
	 * The price for a box of the product.
	 */
	private double myPrice;

	/**
	 * The estimated weight for a box of the product.
	 */
	private double myWeight;

	/**
	 * A text description of the product.
	 */
	private String myDescription;

	/**
	 * The category of the product.
	 */
	private String myCategory;

	/**
	 * This constructor stores all relevant details of the product, which can
	 * be retrieved using accessor methods.
	 * @param descrip		A text description of the product.
	 * @param UPCcode		A unique 12-digit UPC code for the product.
	 * @param productCost	The cost of the product.
	 * @param productWeight	The estimated weight of the product.
	 * @param category	The category the product.
	 */
	public PackagedProduct(String descrip, UPC UPCcode, double productCost,
			double productWeight, String category) {
		myDescription = descrip;
		myUPC = UPCcode;
		myPrice = productCost;
		myWeight = productWeight;
		// Throw exception if category does not exist.
		if (CategoryDB.getInstance().existCategory(category)) {
			myCategory = category;
		} else {
			// throw exception for invalid category
		}
	}

	/**
	 * An accessor method which returns the unique UPC of the product.
	 */
	public UPC getUPC() {
		return myUPC;
	}
	
	/**
	 * An accessor method which returns the unique Code (UPC) of the product.
	 */
	@Override
	public Code getCode() {
		return getUPC();
	}

	/**
	 * An accessor method which returns the price of the product.
	 */
	@Override
	public double getPrice() {
		return myPrice;
	}

	/**
	 * An accessor method which returns the weight of the product.
	 */
	public double getWeight() {
		return myWeight;
	}

	/**
	 * An accessor method which returns the text description of the product.
	 */
	@Override
	public String getDescription() {
		return myDescription;
	}

	/**
	 * A setter method setting the actual category of the item.
	 */
	@Override
	public void setCategory(String itemCategory) {
		myCategory = itemCategory;
	}

	/**
	 * An accessor method returning the actual category of the item.
	 */
	@Override
	public String getCategory() {
		return myCategory;
	}

	/**
	 * Accessor method returning the actual tax rate for the item.
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

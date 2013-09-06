/*
 * Creator: Susan Elliott Sim
 * Course: Inf111, Winter 2008
 * 
 * Created on May 10, 2006
 * Updated on January 17, 2008
 * 
 * Copyright, 2006, 2008 University of California. 
 * 
 * The ProductDB class maintains the items in the database. The items are stored in a hash table.
 */

package SelfCheckOut.App;

import java.util.Hashtable;
import java.util.Vector;

/**
 * The ProductDB class encapsulates the list of all products sold in the store. 
 * In a real system, this would likely be a wrapper around a database of products
 * which would be managed elsewhere.  In our sample system, we have a method which 
 * can provide a sample DB, and the capability to add items to the DB using an
 * addItem() method.
 *
 */
public class ProductDB {
	/**
	 * This Hashtable is the core of our sample DB.  In a real implementation, the
	 * actual data would likely be in a separate database, which we would access
	 * using database queries.
	 */
	private Hashtable<String, ProductInfo> productsHT;

	/**
	 * Constructs an empty database.
	 */
	public ProductDB() {
		productsHT = new Hashtable<String, ProductInfo>();
	}

	/**
	 * This test method constructs a sample database which is useful for
	 * testing purposes.  Products may also be added individually using the
	 * addItem() method.
	 * @throws Exception
	 */
	public void initializeTestDB() throws Exception {

        try {
        	// create categories
        	CategoryDB catDB = CategoryDB.getInstance();
			catDB.setTaxRateForCategory("Cereal", 1);
			catDB.setTaxRateForCategory("Drink", 2);
			catDB.setTaxRateForCategory("Dairy", 3);
			catDB.setTaxRateForCategory("Cookie", 10);
			catDB.setTaxRateForCategory("Candy", 13);
			catDB.setTaxRateForCategory("Pizza", 18);
			catDB.setTaxRateForCategory("Vegetable", 5);
			catDB.setTaxRateForCategory("Fruit", 13);
			catDB.setTaxRateForCategory("Soup", 20);

			// create UPC's
			UPC upc1 = new UPC("786936224306");
			UPC upc2 = new UPC("717951000842");
			UPC upc3 = new UPC("024543213710");
			UPC upc4 = new UPC("085392132225");

			// create BIC's
			BIC bic1 = new BIC("11111");
			BIC bic2 = new BIC("22222");
			BIC bic3 = new BIC("33333");
			BIC bic4 = new BIC("44444");
			BIC bic5 = new BIC("55555");
			BIC bic6 = new BIC("66666");
			BIC bic7 = new BIC("77777");
			BIC bic8 = new BIC("88888");
			BIC bic9 = new BIC("99999");
			BIC bic10 = new BIC("11112");
			BIC bic11 = new BIC("11113");
			BIC bic12 = new BIC("11114");
	
			// Packaged Products consist of a description, UPC, price, weight and category.
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
					3.52, 1.35, "Cereal");
			PackagedProduct pp2 = new PackagedProduct("Coca Cola (12 pack)",
					upc2, 3.20, 4, "Drink");
			PackagedProduct pp3 = new PackagedProduct("Ice Cream", upc3, 4.00,
					2.2, "Dairy");
			PackagedProduct pp4 = new PackagedProduct("Oreo Cookies", upc4,
					3.50, 0.8, "Cookie");

			BulkProduct bp1 = new BulkProduct("Mushroom Soup", bic1, 1, "Soup");
			BulkProduct bp2 = new BulkProduct("Chocolate", bic2, 2.8, "Candy");
			BulkProduct bp3 = new BulkProduct("Cheese pizza", bic3, 3, "Pizza");
			BulkProduct bp4 = new BulkProduct("Cabbage", bic4, 4.1, "Vegetable");
			BulkProduct bp5 = new BulkProduct("Eggplant", bic5, 8.18, "Vegetable");
			BulkProduct bp6 = new BulkProduct("Lemon", bic6, 0.78, "Fruit");
			BulkProduct bp7 = new BulkProduct("Grapefruit", bic7, 1.33, "Fruit");
			BulkProduct bp8 = new BulkProduct("Milk", bic8, 1.99, "Dairy");
			BulkProduct bp9 = new BulkProduct("Fruity Loops", bic9, 2.99, "Cereal");
			BulkProduct bp10 = new BulkProduct("Orange Juice", bic10, 16.00, "Drink");
			BulkProduct bp11 = new BulkProduct("Potato", bic11, 14.30, "Vegetable");
			BulkProduct bp12 = new BulkProduct("Skittles", bic12, 6.99, "Candy");
			
			// enable for products to be added from SelfCheckOutGui
			productsHT.put("11111", bp1);
			productsHT.put("22222", bp2);
			productsHT.put("33333", bp3);
			productsHT.put("44444", bp4);
			productsHT.put("55555", bp5);
			productsHT.put("66666", bp6);
			productsHT.put("77777", bp7);
			productsHT.put("88888", bp8);
			productsHT.put("99999", bp9);
			productsHT.put("11112", bp10);
			productsHT.put("11113", bp11);
			productsHT.put("11114", bp12);
			productsHT.put("786936224306", pp1);
			productsHT.put("717951000842", pp2);
			productsHT.put("024543213710", pp3);
			productsHT.put("085392132225", pp4);
			
			// Build carts.
			CheckOutCart c1 = new CheckOutCart();
			CheckOutCart c2 = new CheckOutCart();
			CheckOutCart c3 = new CheckOutCart();
			CheckOutCart c4 = new CheckOutCart();
			CheckOutCart c5 = new CheckOutCart();
			CheckOutCart c6 = new CheckOutCart();
			CheckOutCart c7 = new CheckOutCart();
			CheckOutCart c8 = new CheckOutCart();
			CheckOutCart c9 = new CheckOutCart();
			CheckOutCart c10 = new CheckOutCart();
			
			// Build Grocery Items
	        GroceryItem gi1 = new GroceryItem(pp1, bp1.getPrice(), 1);
	        GroceryItem gi2 = new GroceryItem(pp2, bp2.getPrice(), 1.5);
	        GroceryItem gi3 = new GroceryItem(pp3, bp3.getPrice(), 1.02);
	        GroceryItem gi4 = new GroceryItem(pp4, bp4.getPrice(), 4);

	       	GroceryItem gi5 = new GroceryItem(bp1, bp1.getPrice(), 1);
	        GroceryItem gi6 = new GroceryItem(bp2, bp2.getPrice(), 2.5);
	        GroceryItem gi7 = new GroceryItem(bp3, bp3.getPrice(), 0.87);
	        GroceryItem gi8 = new GroceryItem(bp4, bp4.getPrice(), 1.42);
	        GroceryItem gi9 = new GroceryItem(bp5, bp5.getPrice(), 3.5);
	        GroceryItem gi10 = new GroceryItem(bp6, bp6.getPrice(), 8);
	        GroceryItem gi11 = new GroceryItem(bp7, bp7.getPrice(), 1.05);
	        GroceryItem gi12 = new GroceryItem(bp8, bp8.getPrice(), 2.2);
	        GroceryItem gi13 = new GroceryItem(bp9, bp9.getPrice(), 4);
	        GroceryItem gi14 = new GroceryItem(bp10, bp10.getPrice(), 2.9);
	        GroceryItem gi15 = new GroceryItem(bp11, bp11.getPrice(), 3.8);
	        GroceryItem gi16 = new GroceryItem(bp12, bp12.getPrice(), 5.2);

	        gi3.setPromotion();
	        gi6.setPromotion();
	        gi9.setPromotion();
	        gi12.setPromotion();
	        
	        // Populate carts with grocery items
	        c1.addItemToCart(gi1);
	        c1.addItemToCart(gi2);
	        c1.addItemToCart(gi3);
	        
	        c2.addItemToCart(gi2);
	        c2.addItemToCart(gi4);
	        c2.addItemToCart(gi1);
	        
	        c3.addItemToCart(gi5);
	        c3.addItemToCart(gi6);
	        c3.addItemToCart(gi7);
	        
	        c4.addItemToCart(gi8);
	        c4.addItemToCart(gi9);

	        c5.addItemToCart(gi10);
	        c5.addItemToCart(gi11);
	        c5.addItemToCart(gi12);
	        
	        c6.addItemToCart(gi10);
	        c6.addItemToCart(gi11);
	        c6.addItemToCart(gi7);
	        
	        c7.addItemToCart(gi5);
	        c7.addItemToCart(gi2);
	        c7.addItemToCart(gi8);
	        
	        c8.addItemToCart(gi5);
	        c8.addItemToCart(gi6);
	        
	       	c9.addItemToCart(gi7);
	        c9.addItemToCart(gi8);
	        c9.addItemToCart(gi16);

	       	c10.addItemToCart(gi13);
	        c10.addItemToCart(gi14);
	        c10.addItemToCart(gi15);


	        //make tm
	        TransactionManager manager = TransactionManager.getInstance();
	        
	        manager.addRecord(c1);
	        manager.addRecord(c2);
	        manager.addRecord(c3);
	        manager.addRecord(c4);
	       	manager.addRecord(c5);
	        manager.addRecord(c6);
	        manager.addRecord(c7);
	        manager.addRecord(c8);
	        manager.addRecord(c9);
	        manager.addRecord(c10);
	        
	        Vector<Record> reports = manager.getAllReports();
	        
	        reports.get(0).modifyTime(2012, 10, 10);
	        reports.get(1).modifyTime(2012, 10, 10);
	        reports.get(2).modifyTime(2012, 10, 13);
	        reports.get(3).modifyTime(2012, 10, 18);
	        reports.get(4).modifyTime(2012, 11, 1);
	        reports.get(5).modifyTime(2012, 11, 2);
	        reports.get(6).modifyTime(2012, 11, 1);
	        reports.get(7).modifyTime(2012, 11, 1);
	        reports.get(8).modifyTime(2012, 11, 2);
	        reports.get(9).modifyTime(2012, 11, 1);
	        reports.get(10).modifyTime(2012, 10, 25);

        }
        catch (Exception e) { 	
        }
	}

	/**
	 * This method returns a copy of the ProductDB Hashtable.  If we
	 * provided the original, external code could modify the DB directly.
	 */
	public Hashtable<String, ProductInfo> listAll() {
		// make a copy of productsHT before returning
		Hashtable<String, ProductInfo> copyHT = new Hashtable<String, ProductInfo>(
				productsHT);
		return copyHT;
	}

	/**
	 * This method looks up a product in the database.  
	 * @param code	The UPC or BIC of the product.
	 * @return	The ProductInfo of the corresponding product, or null if no such product.
	 */
	public ProductInfo lookUpItem(Code code) {
		return productsHT.get(code.getCode());
	}

	/**
	 * This method is called to add items directly to the database in our example.  In a 
	 * real implementation, this would likely be done directly to the product database
	 * using a separate piece of software.
	 * @param item	The product to be added.
	 */
	public void addItem(ProductInfo item) {
		productsHT.put(item.getCode().getCode(), item);
	}
}

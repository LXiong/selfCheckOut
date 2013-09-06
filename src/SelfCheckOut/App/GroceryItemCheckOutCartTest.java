package SelfCheckOut.App;


import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SelfCheckOut.App.BIC;
import SelfCheckOut.App.BulkProduct;
import SelfCheckOut.App.CategoryDB;
import SelfCheckOut.App.CheckOutCart;
import SelfCheckOut.App.GroceryItem;
import SelfCheckOut.App.PackagedProduct;
import SelfCheckOut.App.UPC;

/**
 * @author c0ojhaph
 * 
 * In selfcheckout: payforgroceries, test by looking at tax collector. 
 * GroceryItem: getFinalPrice() for packaged and bulk.
 * PackagedProducts: getTaxRate();
 * CategoryDB: existCategory, getTaxRate, setTaxRateForCategory (check if it changes) DONE
 * CheckOutCart: addItemToCart
 */
public class GroceryItemCheckOutCartTest {
	
	static CategoryDB catDB = CategoryDB.getInstance();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		catDB.clear();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/******************************************************
	 *         Testing GroceryItem and CheckOutCart
	 *****************************************************/

	/**
	 * Check whether getFinalPrice works on GroceryItem
	 */
	@Test
	public void testGetFinalPriceBulkGroceryItemValidCategory() {
		try {
			BIC bic1 = new BIC("11111");
			catDB.setTaxRateForCategory("Fruit", 100);
			BulkProduct bp1 = new BulkProduct("Banana", bic1, 0.69, "Fruit");
			GroceryItem  g1 = new GroceryItem(bp1, 1.38, 2);
			double result = g1.getFinalPrice();
			assertEquals(result, 1.38, 0.01);
		}
		catch (Exception e) {
			fail("Should not reach here.");
		}
	}

	/**
	 * Check whether getFinalPrice returns an exception on inexistent category
	 */
	@Test
	public void testGetFinalPricePackagedGroceryItemInvalidCategory() {
		try {
			UPC upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 100);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Fish");
			GroceryItem  g1 = new GroceryItem(pp1, 3.52, 1.35);
			try {
				g1.getFinalPrice();
			}
			catch (Exception e) {
				assertTrue(true);
			}
		}
		catch (Exception e) {
			fail("Should not reach here.");
		}
	}

	/**
	 * Check that getTotalCost is equal to totalPreTaxCost in an empty card
	 */
	@Test
	public void testCheckOutCartNoItems() {
		CheckOutCart cart = new CheckOutCart();
		double result = cart.getTotalCost();
		double zeroResult = cart.getTotalPreTaxCost();
		assertEquals(result, zeroResult, 0);
	}

	/**
	 * Check that getTotalCost is totalPreTaxCost + tax with one item
	 */
	@Test
	public void testCheckOutCartOneItem() {
		try {
			UPC upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 100);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Cereal");
			GroceryItem  g1 = new GroceryItem(pp1, 3.52, 1.35);
			CheckOutCart cart = new CheckOutCart();
			cart.addItemToCart(g1);
			double result = cart.getTotalCost();
			double preTaxCost = cart.getTotalPreTaxCost();
			assertEquals(result, preTaxCost + 3.52, 0.01);
		}
		catch (Exception e) {
			fail("Should not reach here.");
		}
	}

	/**
	 * Check that getTotalCost is totalPreTaxCost + tax with many items
	 */
	@Test
	public void testCheckOutCartManyItems() {
		
		try {
			CheckOutCart cart = new CheckOutCart();
			catDB.setTaxRateForCategory("Cereal", 100);
			catDB.setTaxRateForCategory("Drink", 50);
	
			UPC upc1 = new UPC("786936224306");
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Cereal");
			GroceryItem  g1 = new GroceryItem(pp1, 3.52, 1.35);
	
			BIC bic1 = new BIC("11111");
			BulkProduct bp1 = new BulkProduct("Orange Juice", bic1, 2, "Drink");
			GroceryItem  g2 = new GroceryItem(bp1, 2, 1);
	
			cart.addItemToCart(g2);
			cart.addItemToCart(g1);
			double result = cart.getTotalCost();
			double preTaxCost = cart.getTotalPreTaxCost();
			assertEquals(result, preTaxCost + .52, 0.01);
		}
		catch (Exception e) {
			fail("Should not reach here.");
		}
	}
}

/**
 * 
 */
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
import SelfCheckOut.App.PackagedProduct;
import SelfCheckOut.App.UPC;
import SelfCheckOut.Exceptions.InvalidTaxException;

/**
 * @author g1sawaf
 * 
 * In selfcheckout: payforgroceries, test by looking at tax collector. 
 * GroceryItem: getFinalPrice() for packaged and bulk.
 * PackagedProducts: getTaxRate();
 * CategoryDB: existCategory, getTaxRate, setTaxRateForCategory (check if it changes) DONE
 * CheckOutCart: addItemToCart
 */
public class CategoryTaxTest {
	
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
	 *         Testing CategoryDB
	 *****************************************************/
	
	/**
	 * In an empty hashtable, test if category exists.
	 */
	
	@Test
	public void testEmptyExistCategory() {
		boolean result = catDB.existCategory("Fruit");
		assertFalse(result);
	}
	
	/**
	 * In a hashtable with one item, test if category exists.
	 */
	
	@Test
	public void testExistCategory() {
		try {
			catDB.setTaxRateForCategory("Fruit", 0.14);
			boolean result = catDB.existCategory("Fruit");
			assertTrue(result);
		} catch (InvalidTaxException e) {
			// TODO Auto-generated catch block
			fail("Not testing this here.");
		}
	}
	
	/**
	 * In a hashtable with item, test if category exists.
	 */
	@Test
	public void testNotExistCategory(){
		try {
			catDB.setTaxRateForCategory("Fruit", 0.14);
			boolean result = catDB.existCategory("Vegetables");
			assertFalse(result);
		} catch (InvalidTaxException e) {
			// TODO Auto-generated catch block
			fail("Not testing this here.");
		}
	}
	
	/**
	 * Test if program handles changing an existing tax rate on a category.
	 */
	@Test
	public void testChangeTaxRate(){
		try {
			catDB.setTaxRateForCategory("Fruit", 0.14);
			catDB.setTaxRateForCategory("Fruit", 0.20);
			double result = catDB.getTaxRate("Fruit");
			assertEquals(result, 0.20, 0.0);
			
		} catch (InvalidTaxException e) {
			// TODO Auto-generated catch block
			fail("Not testing this here.");
		}
	}
	
	/**
	 * Test if program handles negative tax rate with an exception.
	 */
	@Test
	public void testNegativeTaxRate(){
		try {
			catDB.setTaxRateForCategory("Fruit", -0.14);
			catDB.getTaxRate("Fruit");
			fail("MUst have thrown exception");
		} catch (InvalidTaxException e) {
			assertTrue(true);
		}
	}

	/**
	 * Check get category on inexistent category.
	 */
	@Test
	public void testNonExistentCategory() {
		boolean result = catDB.existCategory("meat");
		assertEquals(false, result);
	}


	/******************************************************
	 *         Testing Packaged and Bulk Products
	 *****************************************************/

	/**
	 * Check whether getTaxRate on a PackagedProduct with a valid category
	 */
	@Test
	public void testGetTaxRatePackagedValid() {
		UPC upc1;
		try {
			upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 1);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Cereal");
			double result = pp1.getTaxRate();
			assertEquals(result, 1, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("Nothing should fail.");
		}
	}

	/**
	 * Check whether getTaxRate on a PackagedProduct with an invalid category
	 */
	@Test
	public void testGetTaxRatePackagedInvalid() {
		try {
			UPC upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 1);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Kellogg");
			double result = pp1.getTaxRate();
			assertEquals(result, 0, 0);
		}
		catch(Exception e){
			assertTrue(true);
		}
	}

	/**
	 * Check whether getPostTaxPrice on a PackagedProduct with a valid category
	 */
	@Test
	public void testGetPostTaxPricePackagedValid() {
		try {
			UPC upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 1);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Cereal");
			double result = pp1.getPostTaxPrice();
			assertEquals(result, 3.56, 0.01);
		}
		catch (Exception e){
			fail("Should not get here.");
		}
	}

	/**
	 * Check whether getPostTaxPrice on a BulkProduct with a valid category
	 */
	@Test
	public void testGetPostTaxPriceBulkValid() {
		try {
			BIC bic1 = new BIC("11111");
			catDB.setTaxRateForCategory("Fruit", 25);
			BulkProduct bp1 = new BulkProduct("Banana", bic1, 0.69, "Fruit");
			double result = bp1.getPostTaxPrice();
			assertEquals(result, 0.86, 0.01);
		}
		catch (Exception e){
			fail("Should not get here.");
		}
	}

	/**
	 * Check whether getPostTaxPrice on a BulkProduct with an invalid category
	 */
	@Test
	public void testGetPostTaxPriceBulkInvalid() {
		try {
			BIC bic1 = new BIC("11111");
			catDB.setTaxRateForCategory("Fruit", 25);
			BulkProduct bp1 = new BulkProduct("Banana", bic1, 0.69, "Dairy");
			bp1.getPostTaxPrice();
		}
		catch (Exception e){
			assertTrue(true);
		}
	}
}
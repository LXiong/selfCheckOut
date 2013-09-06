/*
 * Creator: Susan Elliott Sim
 * 
 * Created on January 29, 2008, September 12, 2012
 * 
 * This class contains JUnit test cases for BulkProduct.java.
 * 
 */
package SelfCheckOut.App;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BulkProductTest {

	BulkProduct firstBulkProduct, differentBulkProduct;
	BIC firstBIC, differentBIC;
	String firstCode, firstDescription, differentCode, differentDescription, firstCategory, differentCategory;
	double firstPrice, differentPrice;
	
	@Before
	public void setUp() throws Exception {
		
		firstCode = "11111";
		firstBIC = new BIC(firstCode);
		firstDescription = "Banana";
		firstPrice = 0.69;
		firstCategory = "Fruit";
		firstBulkProduct = new BulkProduct(firstDescription, firstBIC, firstPrice, firstCategory);
		
		differentCode = "22222";
		differentBIC = new BIC(differentCode);
		differentDescription = "Orange";
		differentPrice = 0.99;
		differentCategory = "Fruit";
		differentBulkProduct = new BulkProduct(differentDescription, differentBIC, differentPrice, differentCategory);
		
	}

	@After
	public void tearDown() throws Exception {
		
		firstBulkProduct = null;
		firstBIC = null;
		firstDescription = null;
		firstPrice = 0.0;
	}

/*	We're not testing this because the constructor doesn't contain any logic
 * Calling the constructor in setUp is sufficient.
 * 
 * public void testBulkProduct() {
		fail("Not yet implemented");
	}
*/
	@Test
	public void testGetBIC() {		
		// first a test with a correct BIC
		assertEquals(firstBIC, firstBulkProduct.getBIC());
		// now test with a different BIC
		assertFalse(differentBIC == firstBulkProduct.getBIC());		
	}

/*	
 * The test for GetCode is pretty much the same as GetBIC, because
 * it's a facade for the method.
*/	
	
	@Test
	public void testGetCode() {

		// first a test with a correct BIC
		assertEquals(firstBIC, firstBulkProduct.getBIC());
		// now test with a different BIC
		assertFalse(differentBIC == firstBulkProduct.getBIC());		

	}

	@Test
	public void testGetPrice() {
		double EPSILON = 1e-15; // a really small number
		// first a test with a correct Price
		assertEquals(firstPrice, firstBulkProduct.getPrice(), EPSILON);
		// now test with a different Price 
		assertFalse(differentPrice == firstBulkProduct.getPrice());		
	}

	@Test
	public void testGetDescription() {
		// first a test with a correct Description
		assertEquals(firstDescription, firstBulkProduct.getDescription());
		// now test with a different Price 
		assertFalse(differentDescription == firstBulkProduct.getDescription());		
	}

}

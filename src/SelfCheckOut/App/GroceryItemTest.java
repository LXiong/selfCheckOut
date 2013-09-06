package SelfCheckOut.App;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GroceryItemTest {
	
	BulkProduct bp;
	double bulkItemWeight = 0.88; //amount purchased by customer
	PackagedProduct pp;
	GroceryItem bulkItem, packagedItem;
	double epsilon = 0.0000001;

	@Before
	public void setUp() throws Exception {
		UPC u = new UPC("786936224306");
		CategoryDB catDB = CategoryDB.getInstance();
		catDB.setTaxRateForCategory("Cereals", 1);
		pp = new PackagedProduct("Oatmeal Crisp", u, 4.75, 1.50, "Cereals");
		
		BIC b = new BIC("11111");
		catDB.setTaxRateForCategory("Vegetables", 0.03);
		bp = new BulkProduct("Onion", b, 0.79, "Vegatables");
		
		packagedItem = new GroceryItem(pp, pp.getPrice(), pp.getWeight());
		bulkItem = new GroceryItem(bp, bp.getPrice() * bulkItemWeight, bulkItemWeight);
	}

	@After
	public void tearDown() throws Exception {
		bulkItem = null;
		packagedItem = null;
	}

	@Test
	public void testGetPrice() {
		assertEquals(bulkItem.getPrice(), bp.getPrice() * bulkItemWeight, epsilon);
		assertEquals(packagedItem.getPrice(), pp.getPrice(), epsilon);
		assertFalse(bulkItem.getPrice() == packagedItem.getPrice());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(bulkItem.getWeight(), bulkItemWeight, epsilon);
		assertEquals(packagedItem.getWeight(), pp.getWeight(), epsilon);
		assertFalse(bulkItem.getWeight() == packagedItem.getWeight());
	}
	
	@Test
	public void getPackagedProductInfo() {		
		assertTrue(packagedItem.getInfo().equals(pp));
		assertFalse(packagedItem.getInfo().equals(bp));
	}
	
	@Test
	public void getBulkProductInfo() {		
		assertTrue(bulkItem.getInfo().equals(bp));
		assertFalse(bulkItem.getInfo().equals(pp));
	}
	
	@Test
	public void getPackagedProductFinalPrice() {		
		assertEquals(packagedItem.getFinalPrice(), pp.getPostTaxPrice(), epsilon);
	}
	
	@Test
	public void getBulkProductFinalPrice() {			
		double total = bp.getPrice() * bulkItemWeight * (1 + bp.getTaxRate() / 100);
		assertEquals(bulkItem.getFinalPrice(), total, epsilon);
	}

}

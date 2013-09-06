package SelfCheckOut.App;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PackagedProductTest {
	
	PackagedProduct p1, p2;
	UPC u1, u2;
	double weight1, weight2, price1, price2;
	String desc1, desc2, cat1, cat2;
	double epsilon = 1e-15;

	@Before
	public void setUp() throws Exception {
		CategoryDB catDB = CategoryDB.getInstance();
		catDB.setTaxRateForCategory("Snacks", 0.30);
		catDB.setTaxRateForCategory("Dairy", 0.04);
		
		desc1 = "Pop-tarts";
		u1 = new UPC("123456789012");
		price1 = 3.89;
		weight1 = 0.70;
		cat1 = "Snacks";
		p1 = new PackagedProduct(desc1, u1, price1, weight1, cat1);
		
		desc2 = "Greek Yogurt";
		u2 = new UPC("987654321012");
		price2 = 5.49;
		weight2 = 0.60;
		cat2 = "Dairy";
		p2 = new PackagedProduct(desc2, u2, price2, weight2, cat2);
	}

	@After
	public void tearDown() throws Exception {
		p1 = null;
		u1 = null;
		desc1 = null;
		cat1 = null;
		p2 = null;
		u2 = null;
		desc2 = null;
		cat2 = null;
	}

	@Test
	public void testGetUPC() {
		assertEquals(p1.getUPC(), u1);
		assertEquals(p2.getUPC(), u2);
		assertFalse(p1.getUPC().equals(p2.getUPC()));
	}
	
	@Test
	public void testGetCode() {
		assertEquals(p1.getCode(), u1);
		assertEquals(p2.getCode(), u2);
		assertFalse(p1.getCode().equals(p2.getCode()));
	}
	
	@Test
	public void testGetPrice() {
		assertEquals(p1.getPrice(), price1, epsilon);
		assertEquals(p2.getPrice(), price2, epsilon);
		assertFalse(p1.getPrice() == p2.getPrice());
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(p1.getWeight(), weight1, epsilon);
		assertEquals(p2.getWeight(), weight2, epsilon);
		assertFalse(p1.getWeight() == p2.getWeight());
	}
	
	@Test
	public void testGetDescription() {
		assertEquals(p1.getDescription(), desc1);
		assertEquals(p2.getDescription(), desc2);
		assertFalse(p1.getDescription() == p2.getDescription());
	}
	
	@Test
	public void testSetAndGetCategories() {
		//Get categories of p1, p2
		assertEquals(p1.getCategory(), cat1);
		assertEquals(p2.getCategory(), cat2);
		assertFalse(p1.getCategory() == p2.getCategory());
		
		//Set category of p1 to cat2
		p1.setCategory(cat2);
		assertEquals(p1.getCategory(), cat2);
		assertTrue(p1.getCategory() == p2.getCategory());
		
		//Set category of p1 back to cat1
		p1.setCategory(cat1);
	}

	@Test
	public void testGetPostTaxPrice() {
		
		CategoryDB catDB = CategoryDB.getInstance();
		double tax1 = catDB.getTaxRate(cat1);
		double tax2 = catDB.getTaxRate(cat2);
		
		assertEquals(p1.getPostTaxPrice(), price1 * (1 + tax1/100), epsilon);
		assertEquals(p2.getPostTaxPrice(), price2 * (1 + tax2/100), epsilon);
		assertFalse(p1.getPostTaxPrice() == p2.getPostTaxPrice());
	}
	
}

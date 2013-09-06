package SelfCheckOut.App;

import static org.junit.Assert.*;

import java.util.Hashtable;

import SelfCheckOut.Exceptions.InvalidTaxException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryDBTest {
	
	CategoryDB catDB;
	double epsilon = 0.0000001;

	@Before
	public void setUp() throws Exception {
		catDB = new CategoryDB();
	}

	@After
	public void tearDown() throws Exception {
		catDB = null;
	}

	@Test
	public void validTaxRate() throws InvalidTaxException {
		catDB.setTaxRateForCategory("Bakery", 3.14);
		assertEquals(catDB.getTaxRate("Bakery"), 3.14, epsilon);
	}
	
	@Test (expected = InvalidTaxException.class)
	public void negativeTaxRate() throws InvalidTaxException {
		catDB.setTaxRateForCategory("Bakery", -0.63);
	}
	
	@Test
	public void updateTaxRate() throws InvalidTaxException {
		catDB.setTaxRateForCategory("Bakery", 3.14);
		catDB.setTaxRateForCategory("Meat", 1.40);
		catDB.setTaxRateForCategory("Bakery", 2.00);
		assertEquals(catDB.getTaxRate("Bakery"), 2.00, epsilon);
		assertFalse(catDB.getTaxRate("Bakery") == catDB.getTaxRate("Meat"));
	}
	
	@Test
	public void testExistCategory() throws InvalidTaxException {
		catDB.setTaxRateForCategory("Vegetables", 3.14);
		assertTrue(catDB.existCategory("Vegetables"));
		assertFalse(catDB.existCategory("Fruits"));
	}
	
	@Test
	public void testgetCategoriesEmpty() throws InvalidTaxException {
		String[] expected = {"All Categories"};
		String[] result = catDB.getCategories();
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void testgetCategoriesMany() throws InvalidTaxException {
		catDB.setTaxRateForCategory("Fruit", 3.14);
		catDB.setTaxRateForCategory("Meat", 1.40);
		catDB.setTaxRateForCategory("Bakery", 2.00);
		catDB.setTaxRateForCategory("Vegetable", 2.00);
		catDB.setTaxRateForCategory("Fish", 2.00);
		String[] expected = {"All Categories","Bakery", "Fish",
				"Fruit", "Meat", "Vegetable"};
		String[] result = catDB.getCategories();
		assertArrayEquals(expected, result);
	}
	
	@Test
	public void clearAndCheck() throws InvalidTaxException {
		Hashtable<String, Double> emptyHT = new Hashtable<String, Double>(); //empty hashtable
		catDB.clear();
		assertTrue(catDB.listAll().equals(emptyHT));
	}

}

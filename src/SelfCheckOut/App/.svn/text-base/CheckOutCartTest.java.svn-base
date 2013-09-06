package SelfCheckOut.App;

import static org.junit.Assert.*;

import java.util.Enumeration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckOutCartTest {
	
	GroceryItem bulkItem, packagedItem;
	CheckOutCart cart;
	double epsilon = 0.0000001;

	@Before
	public void setUp() throws Exception {
		UPC u = new UPC("786936224306");
		CategoryDB catDB = CategoryDB.getInstance();
		catDB.setTaxRateForCategory("Cereals", 1);
		PackagedProduct pp = new PackagedProduct("Oatmeal Crisp", u, 4.75, 1.50, "Cereals");
		
		BIC b = new BIC("11111");
		catDB.setTaxRateForCategory("Vegetables", 0.03);
		BulkProduct bp = new BulkProduct("Onion", b, 0.79, "Vegatables");
		double bulkItemWeight = 0.88; //amount purchased by customer
		
		packagedItem = new GroceryItem(pp, pp.getPrice(), pp.getWeight());
		bulkItem = new GroceryItem(bp, bp.getPrice() * bulkItemWeight, bulkItemWeight);
		
		cart = new CheckOutCart();
	}

	@After
	public void tearDown() throws Exception {
		cart = null;
	}

	@Test
	public void testAddSingleItemToCart() {
		cart.addItemToCart(packagedItem);
		assertTrue(cart.listItems().nextElement().equals(packagedItem));
	}
	
	@Test
	public void testAddMultipleItemsToCart() {
		cart.addItemToCart(bulkItem);
		cart.addItemToCart(packagedItem);
		Enumeration<GroceryItem> items = cart.listItems();
		
		assertTrue(items.nextElement().equals(bulkItem));
		assertTrue(items.nextElement().equals(packagedItem));
	}
	
	@Test
	public void testGetTotalWeight() {
		cart.addItemToCart(bulkItem);
		cart.addItemToCart(packagedItem);
		assertEquals(cart.getTotalWeight(), bulkItem.getWeight() + packagedItem.getWeight(), epsilon);
	}
	
	@Test
	public void testGetTotalPreTaxCost() {
		cart.addItemToCart(bulkItem);
		cart.addItemToCart(packagedItem);
		assertEquals(cart.getTotalPreTaxCost(), bulkItem.getPrice() + packagedItem.getPrice(), epsilon);
	}
	
	@Test
	public void testGetTotalCost() {
		cart.addItemToCart(bulkItem);
		cart.addItemToCart(packagedItem);
		assertEquals(cart.getTotalCost(), bulkItem.getFinalPrice() + packagedItem.getFinalPrice(), epsilon);
	}

}

package SelfCheckOut.IntegrationTests;

import static org.junit.Assert.*;

import java.util.Enumeration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SelfCheckOut.App.BIC;
import SelfCheckOut.App.CheckOutCart;
import SelfCheckOut.App.GroceryItem;
import SelfCheckOut.App.ProductDB;
import SelfCheckOut.App.SelfCheckOut;
import SelfCheckOut.Devices.BaggingArea;
import SelfCheckOut.Devices.PaymentCollector;
import SelfCheckOut.Exceptions.InvalidBICException;

public class CartWithOneItem {

	static SelfCheckOut firstSCO;
	static BaggingArea firstBA;
	static ProductDB firstPDB;
	static PaymentCollector firstPC;

	static BIC firstBIC;
	static double firstWeight;
	static GroceryItem gi;

	@BeforeClass
	public static void classSetUp() throws Exception {
		//create a SelfCheckOut
		firstBA = new BaggingArea();
		firstPC = new PaymentCollector();
		firstPDB = new ProductDB();
		try {
			firstPDB.initializeTestDB();
		} catch (Exception e) {
			throw (e);
		}

		firstSCO = new SelfCheckOut(firstBA, firstPC, firstPDB);				

		// create a bulk item to play with
		try {
			firstBIC = new BIC("11111");
		} catch (InvalidBICException e) {
			fail("Invalid BIC");
		}

		firstWeight = 2.61;
	}

	@AfterClass
	public static void classTearDown(){
		firstSCO = null;
		firstBA = null;
		firstPDB = null;
		firstPC = null;
	}
	
	@Before
	public void setUp() throws Exception {
		gi = firstSCO.addItem(firstBIC, firstWeight); 
	}

	@After
	public void tearDown(){
		firstSCO.resetAll();
	}
	
	@Test
	public void listingItemsInCart() {
		Enumeration<GroceryItem> egi;

		egi = firstSCO.listItemsInCart();
		assertEquals(gi, egi.nextElement());
		assertFalse(egi.hasMoreElements());
	}

	@Test
	public void calculatingTotalCost() {

		double total, unitPrice;
		
		total = firstSCO.getTotalCost();
		unitPrice = firstPDB.lookUpItem(firstBIC).getPrice();
		assertEquals(firstWeight*unitPrice, total, 0.0);
	}

	@Test
	public void payingForGroceries() {
		CheckOutCart coc;
		Enumeration<GroceryItem> egi;
		double unitPrice;
		
		// first check that coc contains some groceries
		coc = firstSCO.payForGroceries();
		assertTrue(coc.listItems().hasMoreElements());

		// now check that the one GroceryItem in coc is the one that we put
		// in by checking the code, weight, and price
		gi = coc.listItems().nextElement();
		assertEquals(firstBIC.toString(), gi.getInfo().getCode().toString());
		
		double EPSILON = 1e-15; // a really small number
		assertEquals(firstWeight, gi.getWeight(), EPSILON);

		unitPrice = firstPDB.lookUpItem(firstBIC).getPrice();
		
		assertEquals(firstWeight*unitPrice, gi.getPrice(), 0.0);
		
		// we would check the actual payment here
		// but since it isn't implemented, we won't
		
		// current cart should be empty again
		
		egi = firstSCO.listItemsInCart();
		assertFalse(egi.hasMoreElements());
		
	}
	
}

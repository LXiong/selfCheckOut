package SelfCheckOut.IntegrationTests;

import static org.junit.Assert.*;

import java.util.Enumeration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SelfCheckOut.App.CheckOutCart;
import SelfCheckOut.App.GroceryItem;
import SelfCheckOut.App.ProductDB;
import SelfCheckOut.App.SelfCheckOut;
import SelfCheckOut.App.UPC;
import SelfCheckOut.Devices.BaggingArea;
import SelfCheckOut.Devices.PaymentCollector;
import SelfCheckOut.Exceptions.InvalidUPCException;

public class CartWithOnePackagedItem {

	static SelfCheckOut firstSCO;
	static BaggingArea firstBA;
	static ProductDB firstPDB;
	static PaymentCollector firstPC;

	static UPC firstUPC;
	static GroceryItem gi;
	static double firstWeight;

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

		// create a packaged item to play with
		try {
			firstUPC = new UPC("111111111111");
		} catch (InvalidUPCException e) {
			fail("Invalid UPC");
		}
		firstWeight = 1.21;
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
		gi = firstSCO.addItem(firstUPC); 
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

		double total;
		
		total = firstSCO.getTotalCost();
		assertEquals(firstPDB.lookUpItem(firstUPC).getPrice(), total, 0.0);
	}

	@Test
	public void payingForGroceries() {
		CheckOutCart coc;
		Enumeration<GroceryItem> egi;
		
		// first check that coc contains some groceries
		coc = firstSCO.payForGroceries();
		assertTrue(coc.listItems().hasMoreElements());

		// now check that the one GroceryItem in coc is the one that we put
		// in by checking the code, weight, and price
		gi = coc.listItems().nextElement();
		assertEquals(firstUPC.toString(), gi.getInfo().getCode().toString());
		
		double EPSILON = 1e-15; // a really small number
		assertEquals(firstWeight, gi.getWeight(), EPSILON);
		
		assertEquals(firstPDB.lookUpItem(firstUPC).getPrice(), gi.getPrice(), 0.0);
		
		// we would check the actual payment here
		// but since it isn't implemented, we won't
		
		// current cart should be empty again
		
		egi = firstSCO.listItemsInCart();
		assertFalse(egi.hasMoreElements());
		
	}
	
}

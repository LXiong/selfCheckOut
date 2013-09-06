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

public class CartWithTwoItems {

	static SelfCheckOut firstSCO;
	static BaggingArea firstBA;
	static ProductDB firstPDB;
	static PaymentCollector firstPC;

	static BIC firstBIC;
	static BIC secondBIC;
	static double firstWeight;
	static double secondWeight;
	
	static GroceryItem gi1;
	static GroceryItem gi2;
		
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
		
		// create another bulk item
		try {
			secondBIC = new BIC("22222");
		} catch (InvalidBICException e) {
			fail("Invalid BIC");
		}
		
		secondWeight = 1.21;
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
		gi1 = firstSCO.addItem(firstBIC, firstWeight); 
		gi2 = firstSCO.addItem(secondBIC, secondWeight);
	}

	@After
	public void tearDown(){
		firstSCO.resetAll();
	}
	
	@Test
	public void listingItemsInCart() {
		Enumeration<GroceryItem> egi;

		egi = firstSCO.listItemsInCart();
		assertEquals(gi1, egi.nextElement());
		assertEquals(gi2, egi.nextElement());
		assertFalse(egi.hasMoreElements());
	}

	@Test
	public void calculatingTotalCost() {

		double total, firstUnitPrice, secondUnitPrice;
		
		total = firstSCO.getTotalCost();
		firstUnitPrice = firstPDB.lookUpItem(firstBIC).getPrice();
		secondUnitPrice = firstPDB.lookUpItem(secondBIC).getPrice();
		assertEquals(firstWeight*firstUnitPrice + secondWeight*secondUnitPrice, total, 0.0);
	}

	@Test
	public void payingForGroceries() {
		CheckOutCart coc;
		Enumeration<GroceryItem> egi;
		double unitPrice;
		GroceryItem gi;
		
		// first check that coc contains some groceries
		coc = firstSCO.payForGroceries();
		assertTrue(coc.listItems().hasMoreElements());

		// now check that the two GroceryItems in coc are the ones that we put
		// in by checking the code, weight, and price
		gi = coc.listItems().nextElement();
		assertEquals(firstBIC.toString(), gi.getInfo().getCode().toString());
		
		double EPSILON = 1e-15; // a really small number
		assertEquals(firstWeight, gi.getWeight(), EPSILON);

		unitPrice = firstPDB.lookUpItem(firstBIC).getPrice();
		
		assertEquals(firstWeight*unitPrice, gi.getPrice(), 0.0);
		
		gi = coc.listItems().nextElement();
		assertEquals(secondBIC.toString(), gi.getInfo().getCode().toString());
		
		assertEquals(secondWeight, gi.getWeight(), EPSILON);
		
		unitPrice = firstPDB.lookUpItem(secondBIC).getPrice();
		
		assertEquals(secondWeight*unitPrice, gi.getPrice(), 0.0);
		
		// we would check the actual payment here
		// but since it isn't implemented, we won't
		
		// current cart should be empty again
		
		egi = firstSCO.listItemsInCart();
		assertFalse(egi.hasMoreElements());
		
	}
	
}

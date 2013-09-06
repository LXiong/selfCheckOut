package SelfCheckOut.IntegrationTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Enumeration;

import SelfCheckOut.App.BIC;
import SelfCheckOut.App.CheckOutCart;
import SelfCheckOut.App.GroceryItem;
import SelfCheckOut.App.ProductDB;
import SelfCheckOut.App.SelfCheckOut;
import SelfCheckOut.Devices.BaggingArea;
import SelfCheckOut.Devices.PaymentCollector;
import SelfCheckOut.Exceptions.AddWhileBaggingException;
import SelfCheckOut.Exceptions.AddWhilePayingException;
import SelfCheckOut.Exceptions.IncorrectStateException;
import SelfCheckOut.Exceptions.InvalidBICException;
import SelfCheckOut.Exceptions.InvalidProductException;

public class CartWithNoItems {

	static SelfCheckOut firstSCO;
	static BaggingArea firstBA;
	static ProductDB firstPDB;
	static PaymentCollector firstPC;

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
	}

	@AfterClass
	public static void classTearDown(){
		firstSCO = null;
		firstBA = null;
		firstPDB = null;
		firstPC = null;
	}

	@After
	public void tearDown(){
		firstSCO.resetAll();
	}
	
	
	@Test
	public void listItemsInEmptyCart(){
		Enumeration<GroceryItem> egi;

		// there's nothing in the cart, so this should be null
		egi = firstSCO.listItemsInCart();
		assertFalse(egi.hasMoreElements());
		
	}

	@Test
	public void calculatingTotalCostOfEmptyCart() {
		assertEquals(0.0, firstSCO.getTotalCost(), 0.0);
	}
	
	@Test
	public void payingForNoGroceries() {	
		
		CheckOutCart coc;
		// test paying for empty grocery cart
		coc = firstSCO.payForGroceries();
		assertFalse(coc.listItems().hasMoreElements());
	}

	@Test
	public void addOneGroceryItem() {
		GroceryItem gi;
		BIC firstBIC;
		double firstWeight;

		try {
			firstBIC = new BIC("11111");
			firstWeight = 2.61;
			gi = firstSCO.addItem(firstBIC, firstWeight); 
			
			assertNotNull(gi);
			assertEquals("Banana", gi.getInfo().getDescription());
			assertEquals(0.69, gi.getInfo().getPrice(), 0.0);
			
		} catch (AddWhileBaggingException awbe) {
			fail("Item scanned before previous item is bagged.");
		} catch (AddWhilePayingException awpe) {
			fail("Item scanned while payment is being processed");
		} catch (InvalidProductException ipe) {
			fail("Item not recognized.");
		} catch (IncorrectStateException ise) {
			fail("Invalid action for current state of Self Check Out.");
		} catch (InvalidBICException e) {
			fail("Invalid code for BIC.");
		}
	}

}

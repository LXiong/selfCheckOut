package SelfCheckOut.App;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import SelfCheckOut.Devices.BaggingArea;
import SelfCheckOut.Devices.PaymentCollector;
import SelfCheckOut.Exceptions.AddWhileBaggingException;
import SelfCheckOut.Exceptions.IncorrectStateException;
import SelfCheckOut.Exceptions.InvalidBICException;
import SelfCheckOut.Exceptions.InvalidEmployeeNumberException;
import SelfCheckOut.Exceptions.InvalidProductException;
import SelfCheckOut.Exceptions.InvalidUPCException;

public class SelfCheckOutTest {
	
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
	public static void classTearDown() throws Exception {
		firstSCO = null;
		firstBA = null;
		firstPDB = null;
		firstPC = null;
	}
	
	@Before
	public void setUp() throws IncorrectStateException, InvalidProductException {		
		gi = firstSCO.addItem(firstBIC, firstWeight); 
	}
	
	@After
	public void tearDown(){
		firstSCO.resetAll();
	}
	
	@Test (expected = AddWhileBaggingException.class)
	public void testCheckoutAlertBulkItem() throws IncorrectStateException, InvalidProductException {

		
		gi = firstSCO.addItem(firstBIC, firstWeight); //add item
		gi = firstSCO.addItem(firstBIC, firstWeight); //attempt to add another item
	}
	
	@Test (expected = AddWhileBaggingException.class)
	public void testCheckoutAlertPackagedItem() throws IncorrectStateException, InvalidProductException, InvalidUPCException {
		UPC firstUPC = new UPC("123456789012");
		
		gi = firstSCO.addItem(firstUPC); //add item
		gi = firstSCO.addItem(firstUPC); //attempt to add another item
	}
	
	@Test
	public void testResolveCheckoutAlertBulkItem() throws IncorrectStateException, InvalidProductException, InvalidEmployeeNumberException {
		
		try {
			gi = firstSCO.addItem(firstBIC, firstWeight); //add item
			gi = firstSCO.addItem(firstBIC, firstWeight); //attempt to add another item
		} catch (IncorrectStateException e) {
			firstSCO.resolveProblem(12345); //resolve alert
		} catch (InvalidProductException e) {
			firstSCO.resolveProblem(12345); //resolve alert
		}
		
		assertFalse(firstSCO.isAlertState());
	}
	
	@Test
	public void testResolveCheckoutAlertPackagedItem() throws InvalidUPCException, InvalidEmployeeNumberException {

		UPC firstUPC = new UPC("123456789012");
		
		try {
			gi = firstSCO.addItem(firstUPC); //add item
			gi = firstSCO.addItem(firstUPC); //attempt to add another item
		} catch (IncorrectStateException e) {
			firstSCO.resolveProblem(12345); //resolve alert
		} catch (InvalidProductException e) {
			firstSCO.resolveProblem(12345); //resolve alert
		} 
		
		assertFalse(firstSCO.isAlertState());
	}

}

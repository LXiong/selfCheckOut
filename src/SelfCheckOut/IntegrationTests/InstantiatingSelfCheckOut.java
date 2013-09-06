package SelfCheckOut.IntegrationTests;

import SelfCheckOut.App.*;

import SelfCheckOut.Devices.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InstantiatingSelfCheckOut {

	SelfCheckOut firstSCO;
	BaggingArea firstBA;
	ProductDB firstPDB;
	PaymentCollector firstPC;
		
	@Before
	public void setUp() throws Exception {
		
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

	@After
	public void tearDown() throws Exception {
		
		firstSCO = null;
		firstBA = null;
		firstPDB = null;
		firstPC = null;
	}

	@Test
	public void instantiateSCOWithNoArgs() throws Exception {
		SelfCheckOut noArgSelfCheckOut;
		
		noArgSelfCheckOut = new SelfCheckOut();
		assertNotNull(noArgSelfCheckOut.getBaggingArea());
		assertNotNull(noArgSelfCheckOut.getProductDB());
		assertNotNull(noArgSelfCheckOut.getPaymentCollector());
			
	}

/*
 * Don't need this, because it's been called in the setUp
 * 
 * 	public void testSelfCheckOutBaggingAreaPaymentCollectorProductDB() {
		fail("Not yet implemented");
	}
*/


/*	
 * This is not implemented, because the notifyBaggingAreaEvent only changes
 * a private variable.
 * 
 * public void testNotifyBaggingAreaEvent() {
		fail("Not yet implemented");
	}
*/
	

	@Test
	public void gettingBaggingArea() {
		assertSame(firstBA, firstSCO.getBaggingArea());
	}

	@Test
	public void gettingGetProductDB() {
		assertSame(firstPDB, firstSCO.getProductDB());
	}

	@Test
	public void gettingGetPaymentCollector() {
		assertSame(firstPC, firstSCO.getPaymentCollector());
	}

}

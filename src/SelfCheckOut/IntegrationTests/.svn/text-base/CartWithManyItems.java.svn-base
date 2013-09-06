package SelfCheckOut.IntegrationTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
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
import SelfCheckOut.App.UPC;
import SelfCheckOut.Devices.BaggingArea;
import SelfCheckOut.Devices.PaymentCollector;
import SelfCheckOut.Exceptions.InvalidBICException;
import SelfCheckOut.Exceptions.InvalidUPCException;

public class CartWithManyItems {

	static SelfCheckOut firstSCO;
	static BaggingArea firstBA;
	static ProductDB firstPDB;
	static PaymentCollector firstPC;
	static ArrayList<BIC> bics;
	static ArrayList<UPC> upcs;
	static ArrayList<Double> bulkWeights;
	static ArrayList<Double> packagedWeights;
	static ArrayList<GroceryItem> gis;
	
	static int numberOfItems = 50;

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

		for (int i = 0; i < numberOfItems; i++) {
			try {
				bics.add(new BIC("11111"));
			} catch (InvalidBICException e) {
				fail("Invalid BIC");
			}
			bulkWeights.add(i+1.5);
			try {
				upcs.add(new UPC("111111111111"));
			} catch (InvalidUPCException e) {
				fail("Invalid UPC");
			}
			packagedWeights.add(0.72);
		}
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
		for (int i = 0; i < numberOfItems; i++) {
			gis.add(firstSCO.addItem(bics.get(i), bulkWeights.get(i)));
			gis.add(firstSCO.addItem(upcs.get(i)));
		}
	}

	@After
	public void tearDown(){
		firstSCO.resetAll();
	}
	
	@Test
	public void addingAndBagging() throws Exception {
		double weight = firstBA.getTotalWeight();
		SelfCheckOut sco = new SelfCheckOut (firstBA, firstPC, firstPDB);
		for (int i = 0; i < numberOfItems; i++) {
			sco.addItem(bics.get(i), bulkWeights.get(i));
			firstBA.changeWeight(bulkWeights.get(i));
			assertEquals(weight + bulkWeights.get(i), firstBA.getTotalWeight(), 0.0);
			weight += bulkWeights.get(i);
			
			sco.addItem(upcs.get(i));
			firstBA.changeWeight(packagedWeights.get(i));
			assertEquals(weight + packagedWeights.get(i), firstBA.getTotalWeight(), 0.0);
			weight += packagedWeights.get(i);
		}
	}
	
	@Test
	public void listingItemsInCart() {
		Enumeration<GroceryItem> egi;

		egi = firstSCO.listItemsInCart();
		for (int i = 0; i < numberOfItems; i++) {
			assertEquals(gis.get(i), egi.nextElement());
			assertEquals(gis.get(i), egi.nextElement());
		}
		assertFalse(egi.hasMoreElements());
	}

	@Test
	public void calculatingTotalCost() {

		double total, unitPrice;
		double calculatedTotal = 0;
		
		total = firstSCO.getTotalCost();
		for (int i = 0; i < numberOfItems; i++) {
			unitPrice = firstPDB.lookUpItem(bics.get(i)).getPrice();
			calculatedTotal += bulkWeights.get(i) * unitPrice;
			calculatedTotal += firstPDB.lookUpItem(upcs.get(i)).getPrice();
		}
		assertEquals(calculatedTotal, total, 0.0);
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

		for (int i = 0; i < numberOfItems; i++) {
			//bulk
			gi = coc.listItems().nextElement();
			assertEquals(bics.get(i).toString(), gi.getInfo().getCode().toString());
			double EPSILON = 1e-15; // a really small number
			assertEquals(bulkWeights.get(i), gi.getWeight(), EPSILON);
			unitPrice = firstPDB.lookUpItem(bics.get(i)).getPrice();
			assertEquals(bulkWeights.get(i)*unitPrice, gi.getPrice(), 0.0);
			
			

			//packaged
			gi = coc.listItems().nextElement();
			assertEquals(packagedWeights.get(i), gi.getWeight(), EPSILON);			
			assertEquals(upcs.get(i).toString(), gi.getInfo().getCode().toString());
			assertEquals(firstPDB.lookUpItem(upcs.get(i)).getPrice(), gi.getPrice(), 0.0);
		}
		
		// we would check the actual payment here
		// but since it isn't implemented, we won't
		
		// current cart should be empty again
		
		egi = firstSCO.listItemsInCart();
		assertFalse(egi.hasMoreElements());
		
	}
	
}

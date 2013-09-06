package SelfCheckOut.App;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import SelfCheckOut.Exceptions.InvalidBICException;
import SelfCheckOut.Exceptions.InvalidTaxException;
import SelfCheckOut.Exceptions.InvalidUPCException;

public class ProductDBTest {
	
	ProductDB prodDB;

	@Before
	public void setUp() throws Exception {
		prodDB = new ProductDB();
	}

	@After
	public void tearDown() throws Exception {
		prodDB = null;
	}

	@Test
	public void testAddPackagedAndLookup() throws InvalidUPCException, InvalidBICException, InvalidTaxException {
		UPC u1 = new UPC("786936224306");
		UPC u2 = new UPC("717951000842");
		CategoryDB catDB = CategoryDB.getInstance();
		catDB.setTaxRateForCategory("Cereals", 1);
		PackagedProduct pp1 = new PackagedProduct("Cheerios", u1, 3.52, 1.35, "Cereals");
		PackagedProduct pp2 = new PackagedProduct("Frosted Flakes", u2, 4.75, 1.50, "Cereals");
		
		prodDB.addItem(pp1);
		prodDB.addItem(pp2);
		
		assertTrue(prodDB.lookUpItem(u1).equals(pp1));
		assertFalse(prodDB.lookUpItem(u2).equals(pp1));
	}
	
	@Test
	public void lookupPackagedNotExists() throws InvalidUPCException {
		UPC u = new UPC("024543213710");
		
		assertTrue(prodDB.lookUpItem(u) == null);
	}
	
	@Test
	public void testAddBulkAndLookup() throws InvalidUPCException, InvalidBICException, InvalidTaxException {
		BIC b1 = new BIC("11111");
		BIC b2 = new BIC("22222");
		CategoryDB catDB = CategoryDB.getInstance();
		catDB.setTaxRateForCategory("Vegetables", 1);
		BulkProduct bp1 = new BulkProduct("Spinach", b1, 0.50, "Vegatables");
		BulkProduct bp2 = new BulkProduct("Broccoli", b2, 0.99, "Vegatables");
		
		prodDB.addItem(bp1);
		prodDB.addItem(bp2);
		
		assertTrue(prodDB.lookUpItem(b1).equals(bp1));
		assertFalse(prodDB.lookUpItem(b2).equals(bp1));
	}
	
	@Test
	public void lookupBulkNotExists() throws InvalidBICException {
		BIC b = new BIC("33333");
		
		assertTrue(prodDB.lookUpItem(b) == null);
	}

}

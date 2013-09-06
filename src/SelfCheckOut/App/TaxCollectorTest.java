/**
 * 
 */
package SelfCheckOut.App;

import static org.junit.Assert.*;

import org.junit.Test;

import SelfCheckOut.App.BIC;
import SelfCheckOut.App.BulkProduct;
import SelfCheckOut.App.CategoryDB;
import SelfCheckOut.App.PackagedProduct;
import SelfCheckOut.App.TaxCollector;
import SelfCheckOut.App.UPC;


/**
 * This test suite deals with the Tax Collector public variable.
 * Methods with access to the TaxCollector tax are being tested. 
 * @author g1sawaf
 *
 */
public class TaxCollectorTest {
	
	static TaxCollector tax = TaxCollector.getInstance();
	static CategoryDB catDB = CategoryDB.getInstance();
	
	/**
	 * Test if the tax collected is the correct amount for one bulk item. 
	 */
	@Test
	public void testTaxCollectedSingleBulk(){
		try {
			BIC bic1 = new BIC("11111");
			catDB.setTaxRateForCategory("Fruit", 0.25);
			BulkProduct bp1 = new BulkProduct("Banana", bic1, 0.69, "Fruit");
			double result = bp1.getTaxRate();
			tax.addTax(bp1.getTaxRate());
			double expected = tax.getTax();
			assertEquals(expected, result, 0.1);
			tax.clearTax();
			
		}
		catch (Exception e){
			fail("Should not reach here.");
		}
	}
	
	/**
	 * Test if the tax collected is the correct amount for one packaged product. 
	 */
	@Test
	public void testTaxCollectedSinglePackaged(){
		try {
			UPC upc1;
			upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 1);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Cereal");
			double result = pp1.getTaxRate();
			tax.addTax(pp1.getTaxRate());
			double expected = tax.getTax();
			assertEquals(expected, result, 0.1);
			tax.clearTax();
			
		}
		catch (Exception e){
			fail("Should not reach here.");
		}
	}
	
	/**
	 * Test if the tax collected is correct for mixed items. 
	 */
	@Test
	public void testTaxCollectedMixedProducts(){
		try {
			UPC upc1;
			BIC bic1 = new BIC("11111");
			catDB.setTaxRateForCategory("Fruit", 0.25);
			BulkProduct bp1 = new BulkProduct("Banana", bic1, 0.69, "Fruit");
			tax.addTax(bp1.getTaxRate());
			upc1 = new UPC("786936224306");
			catDB.setTaxRateForCategory("Cereal", 1);
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
						3.52, 1.35, "Cereal");
			double result = pp1.getTaxRate() + bp1.getTaxRate();
			tax.addTax(pp1.getTaxRate());
			double expected = tax.getTax();
			assertEquals(expected, result, 0.1);
			tax.clearTax();
			
		}
		catch (Exception e){
			fail("Should not reach here.");
		}
	}
		
}

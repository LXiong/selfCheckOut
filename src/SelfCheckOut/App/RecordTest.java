/**
 * 
 */
package SelfCheckOut.App;

import java.util.Vector;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author g1sawaf
 * Testcases for the Record class.
 */
public class RecordTest extends TestCase {
	
	/**
	 * Test the tax rate of an empty record. 
	 */
	@Test
	public void testEmptyRecordTax(){
		CheckOutCart cart = new CheckOutCart();
		Record r1 = new Record(cart, 0);
		double tax = r1.getTax();
		assertEquals(tax, 0.0);
	}
	
	/**
	 * Test the promotion list of an empty record. 
	 */
	@Test
	public void testEmptyRecordPromotionList(){
		CheckOutCart cart = new CheckOutCart();
		Vector<GroceryItem> v1 = new Vector<GroceryItem>();
		Record r1 = new Record(cart, 0);
		assertEquals(v1, r1.getItemsOnPromotion());
	}
	
	/**
	 * Test the tax rate of a record with one item. 
	 */
	@Test
	public void testSingleRecordTax(){
		
		try {
			CheckOutCart cart = new CheckOutCart();
			
			UPC upc1 = new UPC("786936224306");
			
			CategoryDB categoryHT = CategoryDB.getInstance();
			categoryHT.setTaxRateForCategory("Cereal", 1);
			
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
					3.52, 1.35, "Cereal");
			
			
			GroceryItem gi1 = new GroceryItem(pp1, pp1.getPrice(), pp1.getWeight());
			
			cart.addItemToCart(gi1);
			
			double tax = cart.getTotalCost() - cart.getTotalPreTaxCost();
			
			Record r1 = new Record(cart, 0);
			
			assertEquals(r1.getTax(), tax);	
		}
		catch (Exception e) {
			//Should not reach here.
			assertFalse(true);
		}
	}	
}

/**
 * 
 */
package SelfCheckOut.App;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;


/**
 * @author g1sawaf
 *
 */
public class DayTest {
	
	@Test
	/**
	 * Test an empty Day
	 */
	public void testEmptyDay(){
		
		try {
			
			CheckOutCart cart = new CheckOutCart();
			Vector<GroceryItem> expected = new Vector<GroceryItem>();
			Record r1 = new Record(cart, 0);
			Day d1 = new Day();
			d1.addRecord(r1);
			assertEquals(d1.getPopularPromotions("quantity", 0), expected);
		}
		catch (Exception e) {
			assertFalse(true);
		}
	}

}

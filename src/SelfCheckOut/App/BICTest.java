/* Creator: Susan Elliott Sim
 * 
 * Created on January 29, 2008
 * Updated on September 12, 2012
 * 
 * This class contains JUnit test cases for BIC.java.
 * 
 */

package SelfCheckOut.App;

import SelfCheckOut.Exceptions.InvalidBICException;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BICTest {

	BIC firstBIC;
	String firstCode;
	
	@Before
	public void setUp() throws Exception {

		firstCode = "55555";
		try {
			firstBIC = new BIC(firstCode);
		} catch (InvalidBICException e) {
			fail("Invalid BIC");
		}
	}

	@After
	public void tearDown() throws Exception {

		firstBIC = null;
		firstCode = null;
	}

	@Test
	public void testGetCode() {
		Integer i = new Integer(firstCode) - 1;
		
		assertEquals(firstCode, firstBIC.getCode());		
		assertFalse(i.equals(firstBIC.getCode()));
		
	}

	@Test
	public void sameBICs() throws InvalidBICException{
		BIC secondBIC = new BIC(firstCode);
		assertTrue(firstBIC.equals(firstBIC));
		assertTrue(firstBIC.equals(secondBIC));		
	}
	
	@Test 
	public void twoDifferentBICs() throws InvalidBICException{
		BIC secondBIC = new BIC("12345");
		assertFalse(firstBIC.equals(secondBIC));	
	}

	@Test
	public void inputBICWrongType() throws InvalidBICException{
		Object obj = new Object();
		assertFalse(firstBIC.equals(obj));
	}

}

package SelfCheckOut.App;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GroceryTableElementTest {

	@Test
	public void testDecimal2Digits(){

		GroceryTableElement gte = new GroceryTableElement();
		double num = 68.12950;
		String exp = "68.13";
		String res = gte.toTwoDecimal(num);
		assertEquals(res, exp);
	}

	@Test
	public void testDecimal1Digit(){
		GroceryTableElement gte = new GroceryTableElement();
		double num = 6.129508897860;
		String exp = "6.13";
		String res = gte.toTwoDecimal(num);
		assertEquals(res, exp);
	}
	
	@Test
	public void testDecimal3Digits(){
		GroceryTableElement gte = new GroceryTableElement();
		double num = 698.1295786990;
		String exp = "698.13";
		String res = gte.toTwoDecimal(num);
		assertEquals(res, exp);
	}	
	
	@Test
	public void testDecimal0Digits(){
		GroceryTableElement gte = new GroceryTableElement();
		double num = 0.1298097969750;
		String exp = "0.13";
		String res = gte.toTwoDecimal(num);
		assertEquals(res, exp);
	}

}

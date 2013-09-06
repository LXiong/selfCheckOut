/**
 * 
 */
package SelfCheckOut.App;

import org.junit.Test;

import junit.framework.TestCase;

import java.util.Calendar;

public class TransactionManagerTest extends TestCase {
	
	/**
	 * Ensure that the minutes have been set to 0
	 */
	@Test
	public void testResetMin(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		Calendar date = manager.resetTestDate();
		assertEquals(0, date.get(Calendar.MINUTE));
	}
	/**
	 * Ensure that the hours has been set to 0
	 */
	@Test
	public void testResetHr(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		Calendar date = manager.resetTestDate();
		assertEquals(0, date.get(Calendar.HOUR));
	}

	/**
	 * Ensure that the reset date is before this moment
	 */
	@Test
	public void testResetBefore(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		Calendar date = manager.resetTestDate();
		assertTrue(date.before(Calendar.getInstance()));
	}

	/**
	 * Ensure that the Day is today if days is empty
	 */
	@Test
	public void testGetToday(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		Day date = manager.getTestDay();
		assertEquals(date.getDate().get(Calendar.DATE) , Calendar.getInstance().get(Calendar.DATE));
	}

	/**
	 * Ensure that the last day of the array is returned (as opposed
	 * to a new day being made)
	 */
	@Test
	public void testGetTodayAfterRecords(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		CheckOutCart cart = new CheckOutCart();
		manager.addRecord(cart);
		manager.getTestDay();
		Day lastDay = manager.getDays().lastElement();
		Day date = manager.getTestDay();
		assertEquals(lastDay, date);
	}
	
	/**
	 * Ensure that only one day was created
	 */
	@Test
	public void testDayAmount(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		CheckOutCart cart = new CheckOutCart();
		manager.addRecord(cart);
		CheckOutCart cart2 = new CheckOutCart();
		manager.addRecord(cart2);
		CheckOutCart cart3 = new CheckOutCart();
		manager.addRecord(cart3);
		int size = manager.getDays().size();
		assertEquals(1, size);
	}
	
	/**
	 * Ensure that the record counter starts at 1
	 */
	@Test
	public void testRCounter(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		CheckOutCart cart = new CheckOutCart();
		manager.addRecord(cart);
		int rid = manager.getDays().lastElement().getRecords().lastElement().getRID();
		assertEquals(1, rid);
	}
	
	/**
	 * Ensure that the record counter increments properly
	 */
	@Test
	public void testRCounterIncrement(){
		TransactionManager manager = TransactionManager.getInstance();
		manager.flush();
		CheckOutCart cart = new CheckOutCart();
		manager.addRecord(cart);
		CheckOutCart cart2 = new CheckOutCart();
		manager.addRecord(cart2);
		CheckOutCart cart3 = new CheckOutCart();
		manager.addRecord(cart3);
		int rid = manager.getDays().lastElement().getRecords().lastElement().getRID();
		assertEquals(3, rid);
	}
}

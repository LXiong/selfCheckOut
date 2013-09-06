package SelfCheckOut.App;
import java.util.Random;
/**
 * The TransactionManager is a singleton class responsible for managing and operation of
 * the records, the time, and indirectly, promotions. It maintains a counter to increment
 * record ID in the whole program (to avoid conflicts). The TransactionManager also keeps
 * a vector of Days, and is responsible for creation of new Days when necessary, through
 * time keeping methods. Thus, the only methods that the payForGroceries will need to
 * consistently call are getInstance and addRecord.
 */

import java.util.Calendar;
import java.util.Vector;

public class TransactionManager {

	/**
	 * All transaction records for this particular day
	 */
	private Vector<Day> days = new Vector<Day>();
	
	
	/*
	 * The prize string represents if the most recent transaction has been randomly selected to win a prize
	 * null - if no prize has been awarded
	 * otherwise - string containing why the prize was awarded.
	 */
	
	
	static TransactionManager instance = null;
	
	static int idCounter;
	
	static int under20Counter;
	static int meatCatagoryCounter;
	static int vegiCatagoryCounter;
	
	final static int under20ToPrizeRatio = 50;  	// 1 in 50 transactions win a prize if they're under $20
	final static int meatToPrizeRatio = 100;		// 1 in 100 transactions win a prize if they have a meat item
	final static int vegiToPrizeRatio = 150;		// 1 in 150 transactions win a prize if they have a meat item
	
	/*
	 * Method to get the TransactionManager instance
	 */
	public static TransactionManager getInstance() {
		if (instance == null) {
			instance = new TransactionManager();
			idCounter = 1;
			setUpRandomPrizeCounters();
		}
		return instance;
	}



	public void addRecord(CheckOutCart cart) {
		Day today = getDay();
		Record newRecord = new Record(cart, idCounter);
		today.addRecord(newRecord);
		//randomly award a prize to some transactions (user story #63)
		//awardPrizeRandomly(cart); - this crashes everything
		idCounter++;
	}

	private Day getDay() {
		Day returnDay;
		Calendar today = resetDate();
		// handle first day
		if (days.isEmpty()) {
			returnDay = makeDay();
		}
		else {
			Day lastDay = days.lastElement();
			
			// handle case where the last day in the Vector is not today
			if (!lastDay.getDate().after(today)) {
				returnDay = makeDay();
			}
			
			// handle case where last day in the Vector is today
			else {
				returnDay = lastDay;
			}
		}
		return returnDay;
	}
	
	/* 
	 * Return today's date set to the day's beginning 
	 */
	private Calendar resetDate() {
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int date = now.get(Calendar.DATE);
		now.clear();
		now.set(year, month - 1, date);
		return now;
	}

	/*
	 * Empty out the TransactionManager
	 */
	public void flush() {
		days.clear();
		idCounter = 1;
	}
	
	/* 
	 * Create a new day, add it to the end of the Day vector and return it
	 */
	private Day makeDay() {
		Day newDay = new Day();
		days.add(newDay);
		return newDay;
	}
	
	public Vector<Record> getAllReports() {
		Vector<Record> reports = new Vector<Record>();
		for (Day day : getDays()) {
			for (Record record : day.getRecords()) {
				reports.add(record);
			}
		}
		return reports;
	}
	
	private static void setUpRandomPrizeCounters() {
		Random rand = new Random();
		under20Counter = rand.nextInt(under20ToPrizeRatio);
		meatCatagoryCounter= rand.nextInt(meatToPrizeRatio);
		vegiCatagoryCounter= rand.nextInt(vegiToPrizeRatio);
	}
	
	
/*
 * This method will award a small prize to a semi-randomly selected customer.
 * 		the prize is stored as a string in the CheckOutCart object, 
 * 		and then gets passed to the SelfCheckOut object before it is used by the GUI
 * 
 * 	prizes are awarded for:
 * 			1 in 50 under20ToPrizeRatio  - win a prize if they're under $20
 * 			1 in 100 meatToPrizeRatio - win a prize if they have a meat item
 * 			1 in 150 vegiToPrizeRatio - win a prize if they have a meat item
 */
	
	public void awardPrizeRandomly(CheckOutCart cart) {
		Random rand = new Random();
		double cost = cart.getTotalCost();
		
		// prize for transaction under $20 
		if(cost < 20){
			under20Counter--;
			if(under20Counter <= 0){
				cart.setPrize("a transaction of less than $20\n\n");
				under20Counter = rand.nextInt(under20ToPrizeRatio);
				return;
			}
		}
		
		// prize for item from category 
		GroceryItem item;
		while (cart.listItems().hasMoreElements()) {
			item = cart.listItems().nextElement();
			
			if (item.getInfo().getCategory() == "meat"){
				meatCatagoryCounter--;
				if(meatCatagoryCounter <= 0){
					cart.setPrize("purchasing an item from the 'Meat' catagory\n\n");
					meatCatagoryCounter = rand.nextInt(meatToPrizeRatio);
					return;
				}
			}
			
			else if (item.getInfo().getCategory() == "vegetable"){
				vegiCatagoryCounter--;
				if(vegiCatagoryCounter <= 0){
					cart.setPrize("purchasing an item from the 'Vegetable' catagory\n\n");
					vegiCatagoryCounter = rand.nextInt(vegiToPrizeRatio);
					return;
				}
			}
		}
		
		cart.setPrize(null);
		return;
	}
	
	/*
	 * Accessor method returning the days vector
	 */
	public Vector<Day> getDays() {
		return days;
	}

	///////////////////// temporary methods for testing ////////////////////////////

	/*
	 * Public method for the sake of testing
	 */
	public Day getTestDay() {
		return getDay();
	}


	/*
	 * Public method for the sake of testing
	 */
	public Calendar resetTestDate() {
		return resetDate();
	}
}

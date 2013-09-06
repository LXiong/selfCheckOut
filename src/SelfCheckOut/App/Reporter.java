package SelfCheckOut.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

public class Reporter {

	/*
	 * A set of vectors of all records.
     */
    TransactionManager manager;
	

	/*
	 * Return a Vector of Records between the two give dates.
	 */
	public Vector<Record> getRecordsByDate(Calendar startDate, Calendar endDate) {
		
		manager = TransactionManager.getInstance();
		Vector<Record> limitedRecords = new Vector<Record>();
		Vector<Record> allRecords = manager.getAllReports();
		
		// go forward by a day, to make sure EVERYTHING on given endDate is included
		endDate.add(Calendar.DATE, 1);
		int year = endDate.get(Calendar.YEAR);
		int month = endDate.get(Calendar.MONTH);
		int date = endDate.get(Calendar.DATE);
		endDate.set(year, month, date);

		for (Record record : allRecords) {

		 	if (startDate.before(record.getTime()) && (endDate.after(record.getTime()))) {
		 		limitedRecords.add(record);
		 	}
		 	else {

		 	}
		}
		return limitedRecords;
	}
	
	/**
	 * Return a Calendar object given a string representative of a date in the format
	 * 'dd/mm/yy' 
	 */
	public Calendar processCalendar(String date) {
		
		Calendar cal;
		cal = Calendar.getInstance();
		
		Date df;
		try {
			df = new SimpleDateFormat("dd/MM/yy").parse(date);
			cal.setTime(df);
			//cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cal;
	}
    
	/**
     * Return a nested arrayList of strings. Each element of the ArrayList contains
     * info on grocery items. 
     * 
     * The order is as follows:
     * Product Name, Category, Price, Time, Promotion, Tax
     */
    public Vector<GroceryTableElement> getGroceryInfo(String startDate, String endDate) {
    	
    	/**
    	 * The algorithm is rather interesting. 
    	 * I'm using two parallel vectors, one only contains strings of product names, and the other one contains the full info
    	 * of all the products. I'm using this for hashing purposes, I check if the product exists in the 'container' vector,
    	 * and if it does, then I update the info on the product, otherwise, I add it to both the main and the container vector.
    	 */
            
            Vector<GroceryTableElement> main= new Vector<GroceryTableElement>();
            Vector<String> container = new Vector<String>();
            int numPurchases;
            int index;
            double weight;
            double price;
            double tax;
            //Calendar start = Calendar.getInstance();
            //Calendar end = Calendar.getInstance();
            
            Calendar start = processCalendar(startDate);
            Calendar end = processCalendar(endDate);
                            
            GroceryItem grocery;
            
            for (Record record : getRecordsByDate(start, end)){
            	Enumeration<GroceryItem> itemList = record.getItems();
                while (itemList.hasMoreElements()) {
                    //each subsequent loop requires an empty element.
                    grocery = itemList.nextElement();
                    //add product name, then category, then price, time
                    //promotion then tax respectively.
                    
                    //It's bad assigning variables in a loop, but it has to be done that way. 
                    GroceryTableElement groc = new GroceryTableElement();

                    groc.setProductName(grocery.getInfo().getDescription());
                    groc.setProductCategory(grocery.getInfo().getCategory());
                    groc.setPromotion(grocery.getPromotion());
                    
                    if (container.contains(groc.getProductName())) {
                    	index = container.indexOf(groc.getProductName());
                    	
                    	// get the already present values
                    	numPurchases = main.get(index).getNumPurchases();
                    	weight = main.get(index).getWeight();
                    	price = main.get(index).getTotalPrice();
                    	tax = main.get(index).getTotalTax();
                    	
                    	//increment them
                    	groc.setNumPurchases(numPurchases + 1);
                    	groc.setTotalPrice(price + grocery.getPrice());
                    	groc.setTotalTax(tax + (grocery.getFinalPrice() - grocery.getPrice()));
                    	groc.setWeight(weight + grocery.getWeight());
                    	
                    	//replace the element.
                    	main.setElementAt(groc, index);
                    }
                    else {
                    	//add element to main array.
                        groc.setTotalPrice(grocery.getPrice());
                        groc.setNumPurchases(1);
                        groc.setWeight(grocery.getWeight());
                        groc.setTotalTax(grocery.getFinalPrice() - grocery.getPrice());
                        
                        container.add(groc.getProductName());
                    	main.add(groc);
                    }
                }
            }
            return main;
    }
    
	/**
	 * Given a valid category, return all the items of that category.
	 */
	public Vector<GroceryTableElement> getReportsByCategory(String category, String startDate, String endDate) {
		Vector<GroceryTableElement> items = getGroceryInfo(startDate, endDate);
		if (category == "All Categories") {
			return items;
		}
		Vector<GroceryTableElement> result = new Vector<GroceryTableElement>();
		while(!items.isEmpty()) {
			if(items.get(0).getProductCategory().equals(category)) {
				result.add(items.get(0));
			}
			items.remove(0);
		}
		return result;
	}
}


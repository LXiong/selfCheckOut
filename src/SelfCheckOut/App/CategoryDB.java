/* 
 * The CategoryDB class maintains the items in the database. The items are stored in a hash table.
 */

package SelfCheckOut.App;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import SelfCheckOut.Exceptions.InvalidTaxException;

/**
 * The CategoryDB class encapsulates the list of all categories the products belong to. 
 */
public class CategoryDB {
	/**
	 * This Hashtable is the core of our sample DB.
	 * Contains string categories which maps to their
	 * corresponding tax rate.
	 */
	private static Hashtable<String, Double> categorysHT;
	
	private static  CategoryDB instance = null;
	
	/**
	 * The category hashtable is now a singleton that can be accessed across classes. 
	 */
	public static CategoryDB getInstance() {
		if (instance == null) {
			instance = new CategoryDB();
		}
		return instance;
	}

	/**
	 * Constructs an empty database.
	 */
	public CategoryDB() {
		categorysHT = new Hashtable<String, Double>();
	}

	/**
	 * This method returns a copy of the CategoryDB Hashtable.  If we
	 * provided the original, external code could modify the DB directly.
	 */
	public Hashtable<String, Double> listAll() {
		// make a copy of categorysHT before returning
		Hashtable<String, Double> copyHT = new Hashtable<String, Double>(
				categorysHT);
		return copyHT;
	}
	
	/**
	 * This method checks if the input category already exist inside the database.
	 * @param category The category to check.
	 * @return	True if in database, else false.
	 */
	public boolean existCategory(String category) {
		try {
			return categorysHT.containsKey(category);
		} catch (NullPointerException e) {
			return false;
		}
	}
	
	/**
	 * This method gets the tax rate of the input category.
	 * @param category The category of wanted tax rate.
	 * @return The tax rate of the corresponding category, or 0 if no such category.
	 */
	public double getTaxRate(String category) {
		double taxRate = 0;
		if (existCategory(category)) {
			taxRate = categorysHT.get(category);	
		}
		return taxRate;
	}
	
	/**
	 * This method changes the tax rate of the input category if it already exists, if 
	 * the category does not exist, it sets the category to a tax rate. 
	 * @param category The category to be changed.
	 * @param taxRate The new tax rate.
	 */
	public void setTaxRateForCategory(String category, double taxRate) throws InvalidTaxException {
		if (taxRate >= 0) {
			categorysHT.put(category, taxRate);
		}
		else if (taxRate < 0) {
			throw (new InvalidTaxException("Tax cannot be negative."));
		}
		else {
			throw (new InvalidTaxException("Invalid tax rate."));
		}
	}
	
	/**
	 * 
	 * This method clears the hashtable from all keys if they exist.
	 * 
	 */
	public void clear() {
		categorysHT.clear();
	}
	
	/*
	 * Return all categories present in the database.
	 */
	public String[] getCategories() {
		Vector<String> categories = new Vector<String>();
		categories.add("All Categories");
		Enumeration<String> catList = categorysHT.keys();
		while (catList.hasMoreElements()) {
			String category = catList.nextElement();
			categories.add(category);
		}
		Collections.sort(categories);
		Object[] objList = categories.toArray();
		return Arrays.copyOf(objList, objList.length, String[].class);
	}
}

	

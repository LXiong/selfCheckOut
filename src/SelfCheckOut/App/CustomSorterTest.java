package SelfCheckOut.App;


import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

public class CustomSorterTest {
	
	@BeforeClass
	public static void oneTimeSetUp(){
		try {
			
			CategoryDB catDB = CategoryDB.getInstance();
			catDB.setTaxRateForCategory("Cereal", 1);
			catDB.setTaxRateForCategory("Drink", 2);
			catDB.setTaxRateForCategory("Dairy", 3);
			catDB.setTaxRateForCategory("Cookie", 4);
			
			TransactionManager manager;
			UPC upc1 = new UPC("786936224306");
			UPC upc2 = new UPC("717951000842");
			UPC upc3 = new UPC("024543213710");
			UPC upc4 = new UPC("085392132225");
			
			// Packaged Products consist of a description, UPC, price, weight and category.
			PackagedProduct pp1 = new PackagedProduct("Kellogg Cereal", upc1,
					3.52, 1.35, "Cereal");
			PackagedProduct pp2 = new PackagedProduct("Coca Cola (12 pack)",
					upc2, 3.20, 4, "Drink");
			PackagedProduct pp3 = new PackagedProduct("Ice Cream", upc3, 4.00,
					2.2, "Dairy");
			PackagedProduct pp4 = new PackagedProduct("Oreo Cookies", upc4,
					3.50, 0.8, "Cookie");
			
			
			// Build carts.
			CheckOutCart c1 = new CheckOutCart();
			CheckOutCart c2 = new CheckOutCart();
			CheckOutCart c3 = new CheckOutCart();
			CheckOutCart c4 = new CheckOutCart();
			
			// Build Grocery Items
	        GroceryItem gi1 = new GroceryItem(pp1, pp1.getPrice(), pp1.getWeight());
	        GroceryItem gi2 = new GroceryItem(pp2, pp2.getPrice(), pp2.getWeight());
	        GroceryItem gi3 = new GroceryItem(pp3, pp3.getPrice(), pp3.getWeight());
	        GroceryItem gi4 = new GroceryItem(pp4, pp4.getPrice(), pp4.getWeight());
	        
	        // Populate carts with grocery items
	        c1.addItemToCart(gi1);
	        c1.addItemToCart(gi2);
	        c1.addItemToCart(gi3);
	        
	        c2.addItemToCart(gi2);
	        c2.addItemToCart(gi4);
	        c2.addItemToCart(gi1);
	        c2.addItemToCart(gi1);
	        
	        c3.addItemToCart(gi1);
	        c3.addItemToCart(gi2);
	        c3.addItemToCart(gi3);
	        
	        c4.addItemToCart(gi1);
	        c3.addItemToCart(gi2);
	        c4.addItemToCart(gi4);
	        c1.addItemToCart(gi3);
	        
	        //make tm
	        manager = TransactionManager.getInstance();
	        
	        manager.addRecord(c1);
	        manager.addRecord(c2);
	        manager.addRecord(c3);
	        manager.addRecord(c4);
	        
	        Vector<Record> reports = manager.getAllReports();
	        
	        reports.get(0).modifyTime(2012, 11, 10);
	        reports.get(1).modifyTime(2012, 11, 10);
	        reports.get(2).modifyTime(2012, 11, 13);
	        reports.get(3).modifyTime(2012, 11, 18);
		}
		catch (Exception e){
			
		}
	}
	

	@Test
	public void testProductSort(){
		try {
			Reporter r = new Reporter();
			Vector<GroceryTableElement> sorted;
			Vector<GroceryTableElement> groc;
			
			CustomSorter sort = new CustomSorter();
			
			groc = r.getGroceryInfo("1/1/2010", "20/12/2012");
			sorted = sort.sortByProducts(groc, 0);
			
	        
	        assertEquals(sorted.get(0).getProductName(), "Coca Cola (12 pack)");
	        assertEquals(sorted.get(1).getProductName(), "Ice Cream");
	        assertEquals(sorted.get(2).getProductName(), "Kellogg Cereal");
	        assertEquals(sorted.get(3).getProductName(), "Oreo Cookies");
	        
			
		}
		catch (Exception e){
			assertFalse(true);
		}
	}
	
	@Test
	public void testCategorySort(){
		try {
			Reporter r = new Reporter();
			Vector<GroceryTableElement> sorted;
			Vector<GroceryTableElement> groc;
			
			CustomSorter sort = new CustomSorter();
			
			groc = r.getGroceryInfo("1/1/2010", "20/12/2012");
			sorted = sort.sortByCategory(groc, 0);
			
	        
	        assertEquals(sorted.get(0).getProductCategory(), "Cereal");
	        assertEquals(sorted.get(1).getProductCategory(), "Cookie");
	        assertEquals(sorted.get(2).getProductCategory(), "Dairy");
	        assertEquals(sorted.get(3).getProductCategory(), "Drink");
	        
			
		}
		catch (Exception e){
			assertFalse(true);
		}
	}
	
	@Test
	public void testCategorySortDescending(){
		try {
			Reporter r = new Reporter();
			Vector<GroceryTableElement> sorted;
			Vector<GroceryTableElement> groc;
			
			CustomSorter sort = new CustomSorter();
			
			groc = r.getGroceryInfo("1/1/2010", "20/12/2012");
			sorted = sort.sortByCategory(groc, 1);
			
	        
	        assertEquals(sorted.get(0).getProductCategory(), "Drink");
	        assertEquals(sorted.get(1).getProductCategory(), "Dairy");
	        assertEquals(sorted.get(2).getProductCategory(), "Cookie");
	        assertEquals(sorted.get(3).getProductCategory(), "Cereal");
	        
			
		}
		catch (Exception e){
			assertFalse(true);
		}
	}
	
	@Test
	public void testPurchasesSortDescending(){
		try {
			Reporter r = new Reporter();
			Vector<GroceryTableElement> sorted;
			Vector<GroceryTableElement> groc;
			
			CustomSorter sort = new CustomSorter();
			
			groc = r.getGroceryInfo("1/1/2010", "20/12/2012");
			sorted = sort.sortByPurchases(groc, 0);
			
	        
	        assertEquals(sorted.get(0).getNumPurchases(), 5);
	        assertEquals(sorted.get(1).getNumPurchases(), 4);
	        assertEquals(sorted.get(2).getNumPurchases(), 3);
	        assertEquals(sorted.get(3).getNumPurchases(), 2);
	        
			
		}
		catch (Exception e){
			assertFalse(true);
		}
	}
	
	@Test
	public void testPurchasesSortAscending(){
		try {
			Reporter r = new Reporter();
			Vector<GroceryTableElement> sorted;
			Vector<GroceryTableElement> groc;
			
			CustomSorter sort = new CustomSorter();
			
			groc = r.getGroceryInfo("1/1/2010", "20/12/2012");
			sorted = sort.sortByPurchases(groc, 1);
			
	        
	        assertEquals(sorted.get(0).getNumPurchases(), 2);
	        assertEquals(sorted.get(1).getNumPurchases(), 3);
	        assertEquals(sorted.get(2).getNumPurchases(), 4);
	        assertEquals(sorted.get(3).getNumPurchases(), 5);
	        
			
		}
		catch (Exception e){
			assertFalse(true);
		}
	}

}


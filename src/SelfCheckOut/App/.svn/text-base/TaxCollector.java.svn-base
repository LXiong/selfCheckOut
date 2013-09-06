/*
 * 
 * Creator: Philip Ojha
 * 
 */
package SelfCheckOut.App;

public class TaxCollector {

	/**
	 * Total tax collected and owed to the government.
	 */
	double taxCollected;

	static TaxCollector instance = null;

	/*
	 * create the TaxCollector singleton instance
	 */
	public static TaxCollector getInstance() {
        if (instance == null) {
            instance = new TaxCollector();
        }    
        return instance;
    }

	/*
	 * Add to the total tax collected.
	 */
    public void addTax(double amount) {
    	taxCollected += amount;
    }

	/*
	 * Accessor method returning the total tax collected since program began running.
	 */
    public double getTax() {
    	return taxCollected;
    }
    
    public void clearTax() {
    	taxCollected = 0;
    }
}
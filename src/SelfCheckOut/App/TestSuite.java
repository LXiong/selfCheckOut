package SelfCheckOut.App;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	BICTest.class,
	BulkProductTest.class,
	CategoryDBTest.class,
	CheckOutCartTest.class,
	CustomSorterTest.class,
	DayTest.class,
	GroceryItemTest.class,
	PackagedProductTest.class,
	ProductDBTest.class,
	RecordTest.class,
	ReporterTest.class,
	SelfCheckOutTest.class,
	TaxCollectorTest.class,
	TransactionManagerTest.class,
	UPCTest.class
})

public class TestSuite {
}
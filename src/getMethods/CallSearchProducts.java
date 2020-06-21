package getMethods;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import setMethods.Filter;
import setMethods.ProductDetailPage;
import setMethods.SearchProduct;

public class CallSearchProducts extends SearchProduct{
	
	public Filter cf;
	public ProductDetailPage pdp;

	
	@BeforeTest
	public void openURL() throws IOException, InterruptedException
	{
		CallSearchProducts objProduct = new CallSearchProducts();

		objProduct.getDataFile();
		objProduct.getlog4jFile();
		objProduct.getURL();
		objProduct.searchKeyword();
	}
	
	@Test(priority = 1)
	public void dragSlider() throws InterruptedException
	{
		cf = new Filter();
		cf.sliderRange();
	}
	@Test(priority = 2)
	public void performSorting() throws InterruptedException
	{
		cf = new Filter();
		cf.sortLowtoHigh();
	}
	@Test(priority = 3)
	public void openPDP()
	{
		pdp = new ProductDetailPage();
		pdp.openPDP();
	}
	@Test(priority = 4)
	public void shareProduct() throws InterruptedException
	{
		pdp = new ProductDetailPage();
		pdp.shareProduct();
	}
	@Test(priority = 5)
	public void calculateEMICost() throws InterruptedException
	{
		pdp = new ProductDetailPage();
		pdp.calculateEMICost();
	}
}

package setMethods;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialArray;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailPage {

	By products		=	By.className("_2cLu-l");
	By btnShare		= 	By.className("_2ExopE");
	By btnFacebook	= 	By.className("iTS58f");
	By btnViewPlan	=	By.className("lF1Zia");
	By emiMonths	=	By.xpath("//table[@class='_18YXUf']//td[1]");
	By overallCost	=	By.xpath("//table[@class='_18YXUf']//td[3]");
	By actualPrice	=	By.className("_1uv9Cb");
	
	String childWindow,getMonth,finalPrice;

	public void openPDP() {
		List<WebElement> getProducts = SearchProduct.driver.findElements(products);
		getProducts.get(0).click();
		String productName = getProducts.get(0).getText();
		SearchProduct.log.info("It clicked on " + productName);
	}

	
	public void shareProduct() throws InterruptedException {
		
		Set<String> window = SearchProduct.driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		if(it.hasNext())
		{
			it.next();
			SearchProduct.driver.switchTo().window(it.next());
		}
		
		SearchProduct.log.info("Window title is:- " + SearchProduct.driver.getTitle());
		SearchProduct.driver.findElement(btnShare).click();
		SearchProduct.log.info("It clicked on share button");
		Thread.sleep(2000);
		
		List<WebElement> getFacebookText = SearchProduct.driver.findElements(btnFacebook);
		for(WebElement facebook : getFacebookText) {
			String getFaceBookName = facebook.getText();
			if(getFaceBookName.equalsIgnoreCase("facebook"))
			{
				facebook.click();
				SearchProduct.log.info("It clicked on " + getFaceBookName + " buttton");
			}
		}
		
		if(it.hasNext())
		{
			childWindow = it.next();
			SearchProduct.driver.switchTo().window(childWindow);
		}
		SearchProduct.log.info("Ttile of current window:- "+SearchProduct.driver.getTitle());
		SearchProduct.driver.close();
		SearchProduct.log.info("it closed current window");
		SearchProduct.driver.switchTo().window(childWindow);
	}
	
	public void calculateEMICost() throws InterruptedException
	{
		Set<String> window = SearchProduct.driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		if(it.hasNext())
		{
			it.next();
			SearchProduct.driver.switchTo().window(it.next());
		}
		Thread.sleep(3000);
		String productPrice = SearchProduct.driver.findElement(actualPrice).getText().substring(1).replace(",", "").trim();
		int actualProductPrice = Integer.parseInt(productPrice);
		SearchProduct.log.info("Actual product price:- "+actualProductPrice);
		
		SearchProduct.driver.findElement(btnViewPlan).click();
		SearchProduct.log.info("It clicked on view plan button");
		Thread.sleep(2000);
		
		List<WebElement> getMonths = SearchProduct.driver.findElements(emiMonths);
		for(WebElement months : getMonths)
		{
			String month = months.getText();
			List<WebElement> getFinalCost = SearchProduct.driver.findElements(overallCost);
			if(month.equals("12"))
			{
				 getMonth = months.getText();
				 for(WebElement cost : getFinalCost)
				 {
					 finalPrice = cost.getText().substring(1);
				 }
			}
		}
			int finalPricePerYear = Integer.parseInt(finalPrice);
			int totalMonths = Integer.parseInt(getMonth);
			SearchProduct.log.info("Total months:- "+ totalMonths);
			SearchProduct.log.info("overall cost:- "+finalPricePerYear);
			SearchProduct.log.info("Total EMI per month:- "+finalPricePerYear/totalMonths);
			
			if(finalPricePerYear>actualProductPrice)
			{
				int percentage = ((finalPricePerYear-actualProductPrice)/finalPricePerYear)*100;
				SearchProduct.log.info("Total interest to EMI per month:- "+percentage);
			}
			else
			{
				SearchProduct.log.info("No interest applied on EMI");
			}
			
	}
	
}

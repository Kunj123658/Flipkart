package setMethods;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Filter {

	By mobileCategory		=	By.linkText("Mobiles & Accessories");
	By sourceSlider			=	By.xpath("//div[@class='gl5Kwg']/div[1]");
	By destinationSlider	=	By.xpath("//div[@class='gl5Kwg']/div[2]");
	By minDropdownValue		=	By.xpath("//*[@class='_1qKb_B']//select");
	By maxDropdownValue		=	By.xpath("//*[@class='_1YoBfV']//select");
	By btnPriceLowtoHigh	=	By.xpath("//div[@class='_3ywJNQ']//div[3]");
	By priceLowtoHigh		=	By.xpath("//div[@class='_1uv9Cb']//div[1]");
	By relevance			=	By.className("_1vC4OE");
	
	
	public List<Integer> arrayRelevance = new ArrayList<Integer>();
	public List<Integer> sortedarray = 	 new ArrayList<Integer>();
	public List<Integer> arrayLowtoHigh = new ArrayList<Integer>();

	
	public void sliderRange() throws InterruptedException {

		SearchProduct.driver.findElement(mobileCategory).click();
		SearchProduct.log.info("It clicked on mobile & accessories");
		Thread.sleep(3000);
		Actions action = new Actions(SearchProduct.driver);
		WebElement source = SearchProduct.driver.findElement(sourceSlider);
		WebElement destination = SearchProduct.driver.findElement(destinationSlider);
		action.dragAndDropBy(source, 80, 0).perform();
		Thread.sleep(3000);
		action.dragAndDropBy(destination, -63, 0).perform();

		Select minValue = new Select(SearchProduct.driver.findElement(minDropdownValue));
		Select maxValue = new Select(SearchProduct.driver.findElement(maxDropdownValue));
		Thread.sleep(3000);

		SearchProduct.log.info("Min Dropdown Value:- " + minValue.getFirstSelectedOption().getText());
		SearchProduct.log.info("Max Dropdown Value:- " + maxValue.getFirstSelectedOption().getText());
	}
	
	public void sortLowtoHigh() throws InterruptedException {
		
		List<WebElement> getRelevancePrices = SearchProduct.driver.findElements(relevance);
		Thread.sleep(3000);
		for(WebElement getPrice : getRelevancePrices)
		{
			String priceRelevance = getPrice.getText();
			String numberFormatPrice = priceRelevance.substring(1).replace(",", "").trim();
			int price = Integer.parseInt(numberFormatPrice);
			arrayRelevance.add(price);
		}
		Thread.sleep(2000);
		
		SearchProduct.driver.findElement(btnPriceLowtoHigh).click();
		Thread.sleep(3000);
		List<WebElement> getPriceLowHigh = SearchProduct.driver.findElements(priceLowtoHigh);
		for(WebElement getLowtoHighPrice : getPriceLowHigh)
		{
			String priceLowHigh = getLowtoHighPrice.getText();
			String numberFormatPrice = priceLowHigh.substring(1).replace(",", "").trim();
			int price = Integer.parseInt(numberFormatPrice);
			arrayLowtoHigh.add(price);
		}
		
		Collections.sort(arrayRelevance);
		Thread.sleep(3000);
		
		assertFalse(arrayLowtoHigh.equals(arrayRelevance), "Sorting is wrong");
		SearchProduct.log.info("Sorting is performed successfully");
	}

}

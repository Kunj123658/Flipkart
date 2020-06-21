package setMethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchProduct {

	By btnClosePopup = By.xpath("//div[@class='_3Njdz7']/button");
	By searchTextfield = By.className("LM6RPg");
	By suggestion = By.xpath("//*[@class='_3Wn9Gb']");

	public static WebDriver driver;
	public static Properties dataFile = new Properties();
	public static Logger log = Logger.getLogger(SearchProduct.class);

	public void getURL() {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Kunj R Shah\\eclipse-workspace\\Flipkart\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(dataFile.getProperty("URL"));
		log.info("Opened Flipkart URL:- " + driver.getCurrentUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public Properties getDataFile() throws IOException {

		FileInputStream file = new FileInputStream("C:\\Users\\Kunj R Shah\\eclipse-workspace\\Flipkart\\DataFile");
		try {
			dataFile.load(file);
		} catch (IOException e) {
		}
		return dataFile;
	}

	public void getlog4jFile() throws FileNotFoundException {
		FileInputStream file = new FileInputStream(
				"C:\\Users\\Kunj R Shah\\eclipse-workspace\\Flipkart\\Log4j.properties");
		try {
			dataFile.load(file);
		} catch (IOException e) {
			// TODO: handle exception
		}
		PropertyConfigurator.configure(dataFile);
	}

	public void searchKeyword() throws InterruptedException {
		
		driver.findElement(btnClosePopup).click();
		log.info("Closed login pop-up");
		driver.findElement(searchTextfield).sendKeys(dataFile.getProperty("SearchText"));
		driver.findElement(searchTextfield).sendKeys(Keys.ENTER);
		
		/*Thread.sleep(2000);
		List<WebElement> suggestionValues = driver.findElements(suggestion);
		for (int i = 0; i < suggestionValues.size(); i++) {
			String productNames = suggestionValues.get(i).getText();
			log.info(productNames);
			if (productNames.equalsIgnoreCase("mi")) {
				Thread.sleep(3000);
				suggestionValues.get(i).click();
				log.info("Clicked on:- " + productNames);
			}
		}*/

	}
}

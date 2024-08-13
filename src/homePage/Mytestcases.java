package homePage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mytestcases {

	WebDriver driver = new ChromeDriver();
	String WebSite = "https://global.almosafer.com/en";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Random rand = new Random();

	@BeforeTest
	public void MySetup() {
		driver.manage().window().maximize();
		driver.get(WebSite);

	}

	@Test(enabled = false)
	public void Check() throws IOException, InterruptedException {

		WebElement saudibutton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		saudibutton.click();

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("screenshots/screenshot.png");
		FileUtils.copyFile(screenshot, destination);

		WebElement ContactUs = driver.findElement(By.cssSelector(".sc-hUfwpO.bWcsTG"));
		ContactUs.click();
		driver.navigate().back();
		WebElement Logo = driver.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));
		if (Logo.isDisplayed()) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
		js.executeScript("window.scrollTo(0,8000)", Logo);
		Thread.sleep(2000);
		WebElement Hotelsbutton = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		Hotelsbutton.click();
		Thread.sleep(2000);
		WebElement Flights = driver.findElement(By.cssSelector(
				"body > div:nth-child(1) > section:nth-child(3) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > nav:nth-child(1) > a:nth-child(1) > div:nth-child(1)"));
		Flights.click();
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,300)", Flights);

		WebElement FlightDeparture = driver.findElement(By.cssSelector(".sc-iLVFha.sc-cgzHhG.TmOLL"));
		FlightDeparture.click();

		File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination1 = new File("screenshots1/screenshot.png");
		FileUtils.copyFile(screenshot1, destination1);
	}

	@Test
	public void RandomLanguage() {

		WebElement saudibutton = driver
				.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		saudibutton.click();

		int Randomlanguage = rand.nextInt(2);

		if (Randomlanguage == 0) {
			WebElement englishOption = driver.findElement(By.cssSelector(".sc-gkFcWv.hcOfTd"));
			englishOption.click();
		
		} else {
			WebElement arabicOption = driver.findElement(By.cssSelector(".sc-gkFcWv.jJNggu"));
			arabicOption.click();
		}
	}
		@Test
		public void SwitchHotels() {
	
			WebElement HotelTab=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
			HotelTab.click();
			
			WebElement searchLoaction = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));
			
			if(driver.getCurrentUrl().contains("en")) {
				String [] English = { "Dubai" , "Jeddah" , "Riyadh"};
				int randomIndex = rand.nextInt(English.length);
				searchLoaction.sendKeys(English[randomIndex]);
			}else {
					
				if(driver.getCurrentUrl().contains("ar")) {
					String[] Arabic = { "دبي" , "الرياض"};
					int randomIndex = rand.nextInt(Arabic.length);
					searchLoaction.sendKeys(Arabic[randomIndex]);
					
				}
				
				
			}
			
			
		}
			
	}



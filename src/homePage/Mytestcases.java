package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Mytestcases {

	WebDriver driver = new ChromeDriver();
	String WebSite = "https://global.almosafer.com/en";
	Random rand = new Random();
	String ExpectedDefaultLanage = "en";

	@BeforeTest
	public void MySetup() {
		driver.manage().window().maximize();
		driver.get(WebSite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();

	}

	@Test(priority = 1)
	public void CheckTheDefaultLangugeIsEnglish() {
		;
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		Assert.assertEquals(ActualLanguage, ExpectedDefaultLanage);

	}

	@Test(priority = 2)
	public void CheckdefaultCurrency() {
		String ExpectedCurrency = "SAR";
		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"));

		String ActualCurrency = Currency.getText();

		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}

	@Test(priority = 3)
	public void CheckContactNumber() {
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();

		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4)
	public void CheckQitagLogo() {
		boolean ExpectedResultsForTheLogo = true;
		WebElement theFooter = driver.findElement(By.tagName("footer"));

		WebElement logo = theFooter.findElement(By.cssSelector(".sc-fihHvN.eYrDjb"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ekulBa.eYboXF"));

		boolean ActualResultForThelogo = logo.isDisplayed();

		Assert.assertEquals(ActualResultForThelogo, ExpectedResultsForTheLogo);

	}

	@Test(priority = 5)
	public void TestHotelTabIsNotSelected() {
		String expectedValue = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotelTab.getAttribute("aria-selected");

		Assert.assertEquals(ActualValue, expectedValue);

	}

	@Test
	public void CheckDepatureDate() {

		LocalDate TodayDate = LocalDate.now();

		int Today = TodayDate.getDayOfMonth();
		int tomorrow = TodayDate.plusDays(1).getDayOfMonth();
		int TheDayAfterTomorrow = TodayDate.plusDays(2).getDayOfMonth();

		List<WebElement> departureAndArrival = driver.findElements(By.className("LiroG"));

		String ActualDeparture = departureAndArrival.get(0).getText();
		String ACTUALReturn = departureAndArrival.get(1).getText();

		int ActualDepartureAsInt = Integer.parseInt(ActualDeparture);
		int ActualReturndateASIn = Integer.parseInt(ACTUALReturn);

		Assert.assertEquals(ActualDepartureAsInt, tomorrow);
		Assert.assertEquals(ActualReturndateASIn, TheDayAfterTomorrow);

	}

	@Test(priority = 7)

	public void RandomlyChangeTheLanguage() {
		String[] URLS = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int RandomIndex = rand.nextInt(URLS.length);

		driver.get(URLS[RandomIndex]);

	}

	@Test(priority = 8)
	public void SwitchHotels() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		WebElement searchLoaction = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input "));

		if (driver.getCurrentUrl().contains("en")) {
			String[] English = { "Dubai", "Jeddah", "Riyadh" };
			int randomIndex = rand.nextInt(English.length);
			searchLoaction.sendKeys(English[randomIndex]);
		} else {

			if (driver.getCurrentUrl().contains("ar")) {
				String[] Arabic = { "دبي", "الرياض" };
				int randomIndex = rand.nextInt(Arabic.length);
				searchLoaction.sendKeys(Arabic[randomIndex]);

			}

		}

	}

}

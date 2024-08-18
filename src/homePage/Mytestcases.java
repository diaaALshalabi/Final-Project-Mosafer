package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
		WebElement ListOfLocations = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));

		WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResult.click();

	}

	@Test(priority = 9)
	public void RandomlySelectTheNumberOfVistor() {

		WebElement selector = driver
				.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));

		int randomindex = rand.nextInt(2);
		Select select = new Select(selector);
		select.selectByIndex(randomindex);

		WebElement SearchHotelsButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelsButton.click();
	}

	@Test(priority = 10)

	public void CheckThePageFullyLoaded() throws InterruptedException {

		boolean expectedResult = true;
		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

		Assert.assertEquals(finished, expectedResult);

	}

	@Test(priority = 11)
	public void SortItemsLowestToHighestPrice() {
		 
		boolean ExpectedResult = true; 
		WebElement LowestButton= driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestButton.click();
		
		
		WebElement PricesContainer  = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		
		List<WebElement> Allprices= PricesContainer .findElements(By.className("Price__Value"));
		
		String LowestPrice= Allprices.get(0).getText();
		String HighestPrice=Allprices.get(Allprices.size()-1).getText();
		
		
		int LowestPriceInt = Integer.parseInt(LowestPrice);
		int HighestPriceInt= Integer.parseInt(HighestPrice);
		
		
		boolean ActualResult = LowestPriceInt < HighestPriceInt;
		
		
		Assert.assertEquals(ActualResult, ExpectedResult);
		
		
		
		
	}
	
	
}

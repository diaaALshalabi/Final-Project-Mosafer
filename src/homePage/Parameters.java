package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Parameters {
	
	WebDriver driver = new ChromeDriver();
	String WebSite = "https://global.almosafer.com/en";
	Random rand = new Random();
	String ExpectedDefaultLanage = "en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean ExpectedResultsForTheLogo = true;
	String expectedValue = "false";
	LocalDate TodayDate = LocalDate.now();

	int Today = TodayDate.getDayOfMonth();
	int tomorrow = TodayDate.plusDays(1).getDayOfMonth();
	int TheDayAfterTomorrow = TodayDate.plusDays(2).getDayOfMonth();
	
	String[] English = { "Dubai", "Jeddah", "Riyadh" };
	int randomIndex = rand.nextInt(English.length);
	
	String[] Arabic = { "دبي", "الرياض" };
	int randomIndex1 = rand.nextInt(Arabic.length);
	
	int randomindex = rand.nextInt(2);
	
	boolean expectedResult = true;
	
	boolean ExpectedResult = true; 


	
	public void RandomlyChangeTheLanguage1() {
		
		String[] URLS = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };

		int RandomIndex = rand.nextInt(URLS.length);

		driver.get(URLS[RandomIndex]);
	}
	
	
	public void Mysetupnew() {
		
		driver.manage().window().maximize();
		driver.get(WebSite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}

}

package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Question2 {
	public static void main(String[] args) throws InterruptedException
	{
		String exePath = "C:\\Users\\chand\\Downloads\\chromedriver_win32\\chromedriver.exe";  
		System.setProperty("webdriver.chrome.driver", exePath);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); //maximizes the window
		driver.get("https://www.weightwatchers.com/us/"); //navigates to WW website
		String title = driver.getTitle(); //get's the title of the page
		if (title.equals("WW (Weight Watchers): Weight Loss & Wellness Help | WW USA")) //asserts the title of the page
			System.out.println("Title Asserted true " + title);
		else
			System.out.println("Incorrect Title and correct Title is " + title);
		driver.findElement(By
				.xpath("//span[(@class='MenuItem_menu-item__inner-wrapper__1trJ0')]/span[contains(text(), 'Attend')]"))
				.click(); //clicks the Attend icon at the top right corner
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='MenuItem_subtitle__3LoiE']/span[contains(text(),'Workshops')]"))
				.click(); //clicks the Unlimited Workshops option
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 130);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[@class='buttonText-3DCCO' and contains(text(),'Studio')]")));
		String title2 = driver.getTitle(); //get's the title of new page
		if (title2.equals("Find WW Studios & Meetings Near You | WW USA")) //asserts the title of the page
			System.out.println("Title Asserted true " + title2);
		else
			System.out.println("Incorrect Title2 and correct Title2 is " + title2);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(), 'Studio')]")).click();
		driver.findElement(By.xpath("//input[@id='location-search']")).sendKeys("10011");
		driver.findElement(By.xpath("//button[@id='location-search-cta']")).click();
		Thread.sleep(1000);
		// This will scroll the page till the bottom of the page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(1000);
		List<WebElement> studios = driver.findElements(By.xpath("//div[@class='container-3SE46']"));
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".linkUnderline-1_h4g")));
		WebElement titleWebElement = studios.get(0).findElement(By.cssSelector(".linkUnderline-1_h4g"));
		String title1 = titleWebElement.getText();
		String location = studios.get(0).findElement(By.xpath("//span[@class='distance-OhP63']")).getText();
		WebElement element = driver.findElement(By.xpath("//input[@class='input input-3TfT5']"));
		js.executeScript("arguments[0].scrollIntoView();", element);
		System.out.println(title1);
		System.out.println(location);
		titleWebElement.click();
		Thread.sleep(1000);
		String title3 = driver.getTitle();
		System.out.println(title3);
		String studioName = driver.findElement(By.xpath("//h1[contains(@class,'locationName')]")).getText();
		if (title1.contentEquals(studioName))
			System.out.println("Yes");
		else
			System.out.println("NO");
		driver.findElement(By.xpath("//div[@class='hours-IVyrp']")).click();
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int tempDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("Current Day of Week is: " + tempDayOfWeek);
		int dayOfWeek = tempDayOfWeek - 1;
		List<WebElement> week = driver.findElements(By.xpath("//div[@class='day-CZkDC']"));
		for (int i = 0; i <= week.size(); i++) 
		{

			if (i == dayOfWeek) {
				String businessHr = week.get(i).findElement(By.cssSelector("div.dayName-CTNC6")).getText();
				System.out.println("Today hour's of operation is " + businessHr);

			}

		}
		String dayOfTheWeek = "TUE";
		List<WebElement> schedules = driver.findElements(By.xpath("//div[@class='day-NhBOb']"));
		for (WebElement schedule : schedules) {
			String day = schedule.findElement(By.cssSelector(".dayName-1UpF5")).getText();
			if (day.contains(dayOfTheWeek)) 
			{
				ArrayList<String> tempList = new ArrayList<String>();
				List<WebElement> meetings = schedule.findElements(By.xpath(".//div[@class='meeting-14qIm']"));
				for (WebElement meeting : meetings) 
				{
					String name = meeting.findElement(By.xpath(".//span[2]")).getText();
					tempList.add(name);
				}
				Map<String, Integer> countMap = new HashMap<>();
				for (String item : tempList) 
				{
					if (countMap.containsKey(item)) 
					{
						countMap.put(item, countMap.get(item) + 1);
					} 
					else 
					{
						countMap.put(item, 1);
					}
				}
				String S = "";
				for (Map.Entry<String, Integer> P : countMap.entrySet()) 
				{
					S += P.getKey() + " " + P.getValue();
					S += "\n";
				}
				System.out.println(S);
				//driver.close();

			}
			//driver.close();
		}

	}
}

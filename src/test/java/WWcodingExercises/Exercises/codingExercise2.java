package WWcodingExercises.Exercises;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class codingExercise2 {
	// Make WebDriver global to be accessible from the whole class 
	static WebDriver driver; 
	String driverPath = "/Users/donelysfamilia/Downloads/chromedriver"; /// set the path where your chromedriver is located
	String driverN = "webdriver.chrome.driver";

	
	@Before
	public void startBrowser() {

	}

	

	@Test
	public void exercise() {

		// Setting up the driver
		System.setProperty(driverN, driverPath);
		driver = new ChromeDriver();
		driver.navigate().to("https://www.weightwatchers.com/");
		
		String Title = "WW (Weight Watchers): Weight Loss & Wellness Help | WW USA";
		String GetTitle = driver.getTitle();

		// 2. Assert loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help | WW USA”
		Assert.assertEquals(Title, GetTitle);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.className("SecondaryMenu_menuItem__1m5Ya"));

		// 3. On the right corner of the page, click on “Find a Workshop”
		actions.doubleClick(elementLocator).build().perform();
		driver.get(driver.getCurrentUrl());  
		String title2 = "Find WW Studios & Meetings Near You | WW USA";
		String GetTitle2 = driver.getTitle();

		// 4. Assert loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
		System.out.println(title2);
		
		System.out.println(GetTitle2);
		Assert.assertTrue(GetTitle2.contains(title2));

		
		WebDriverWait wait =new WebDriverWait(driver,10);
		
		
		// 5. In the search field, search for meetings for zip code: 10011
		WebElement locationSearch = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("location-search")));
		locationSearch.sendKeys("10011");
		WebElement locationSearchClick = driver.findElement(By.id("location-search-cta"));
		actions.click(locationSearchClick).build().perform();

		
		WebElement firstloc =  wait.until(ExpectedConditions.presenceOfElementLocated(By.className("linkUnderline-1_h4g")));
		List<WebElement> meetings = driver.findElements(By.className("container-3SE46"));
		String[] meeting = meetings.get(0).getText().split("\\r?\\n");
		System.out.println("---------------------------------------- ");
		
		// 6. Print the title of the first result and the distance (located on the right of location title/name)
		System.out.println("First Result title is: " + meeting[0]);
		System.out.println("First Result distance is: " + meeting[1]);

		// 7. Click on the first search result and then, 
		// verify displayed location name/title matches with the name of the first searched result that was clicked.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		actions.click(firstloc).build().perform();
		
		WebElement firstResAfterClick = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("infoContainer-12kv1")));
		String[] firstResArr = firstResAfterClick.getText().split("\\r?\\n");
		Assert.assertEquals(meeting[0], firstResArr[0]);

		
		// 8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)
		String today = LocalDate.now().getDayOfWeek().name().toString();
		String abreviationToday = today.substring(0, 3).toUpperCase();
		System.out.println("---------------------------------------- ");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("day-NhBOb")));
		List<WebElement> firstResSchedule = driver.findElements(By.className("day-NhBOb"));
		for (WebElement day : firstResSchedule) {

			if (day.getText().contains(abreviationToday)) {
				System.out.println(day.getText());
			}
		}

		printMeetings(today);

	}

/**
 * 
 * @param inputDay
 * @param driver
 * 
 * 9. Create a method to print the number of meeting the each person(under the scheduled time) has a particular day of the week
		e.g. printMeetings("Sun")
		Output should be:
		Person A  3
		Person B  1
 */
	public void printMeetings(String inputDay) {

		// in case the input is the entire name
		String dayOfWeek = inputDay.substring(0, 3).toUpperCase();
		System.out.println("---------------------------------------- ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] infoDay;
		int times = 0;
		// Get Schedule
		WebDriverWait wait =new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("day-NhBOb")));
		List<WebElement> firstResSchedule = driver.findElements(By.className("day-NhBOb"));
		for (WebElement day : firstResSchedule) {

			if (day.getText().contains(dayOfWeek)) {
				// Get info from specific day
				infoDay = day.getText().split("\\r?\\n");
				// Iterate through the info skipping the day name at position 0
				for (int i = 1; i < infoDay.length; i++) {
					// if start with a letter and is not in the map we add it.
					if (Character.isLetter(infoDay[i].charAt(0)) && (!map.containsKey(infoDay[i]))) {
						times = 1;
						map.put(infoDay[i], times);
					}
					// if the name is in the map then increases the his/her value
					else if (map.containsKey(infoDay[i])) {
						times = map.get(infoDay[i]);
						times++;
						map.put(infoDay[i], times);
					} else {
						times = 0;
					}
				}
			}
		}
		// Print all values in map
		for (String key : map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}

	}

}

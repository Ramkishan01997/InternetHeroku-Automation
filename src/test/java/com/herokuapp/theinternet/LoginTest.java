package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	private void setUp(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		default:
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		driver.manage().window().maximize();
	}

	@Test(priority = 1, groups = { "positiveTests", "SmokeTest" })
	public void positiveLoginTest() {
		// create driver

//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/login");
		System.out.println("page is opened");
		sleep();

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys("tomsmith");

		WebElement passsword = driver.findElement(By.name("password"));
		passsword.sendKeys("SuperSecretPassword!");

		WebElement button = driver.findElement(By.className("radius"));
		button.click();

		String expectedUrl = "https://the-internet.herokuapp.com/secure";
		String actualText = driver.getCurrentUrl();
		System.out.println(actualText);
		Assert.assertEquals(actualText, expectedUrl, "actual page url is not same");

//		WebElement succesMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]"));
		WebElement logoutbutton = driver.findElement(By.xpath("/html/body/div[2]/div/div/a/i"));

		Assert.assertTrue(logoutbutton.isDisplayed(), "log out button is not visible");

	}

	private void sleep() {
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Parameters({ "username", "password", "expText" })
	@Test(priority = 2, groups = { "NegativeTest", "SmokeTest" })
	public void negativeLoginTest(String uName, String pass, String exptext) {

//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/login");
		System.out.println("page is opened" + uName + "and " + pass);

//		String expText = "Your username is invalid!";

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pass);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleep();
		WebElement outpTitle = driver.findElement(By.id("flash"));
		String ActTitle = outpTitle.getText();
		System.out.println(ActTitle);
		Assert.assertTrue(ActTitle.contains(exptext),
				"invalid user name \n actual: " + ActTitle + "\n expected" + exptext);

	}

	@AfterMethod(alwaysRun = true)
	private void tearDown() {
		driver.quit();
	}

}

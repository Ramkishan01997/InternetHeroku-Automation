package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExceptionTests {

	private WebDriver driver;

//    @Parameters({"browser"})
	@BeforeMethod(alwaysRun = true)
	private void setUp() {
//    	switch (browser) {
//		case "chrome":
//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//		driver = new ChromeDriver();
//			break;
//		case "edge":
//		System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
//		driver = new EdgeDriver();
//		break;
//
//		default:
//			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//			driver = new ChromeDriver();
//			break;
//		}
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void notVisibleTexxt() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		WebElement button = driver.findElement(By.xpath("//div[@id='start']/button"));
		button.click();

		WebElement outputtext = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(outputtext));

		String actText = "Hello World!";
		String expText = outputtext.getText();

		System.out.println("acttext" + actText);
		Assert.assertTrue(actText.contains(expText), "expected text is \n:" + actText + "but found" + expText);

		driver.quit();
	}

}

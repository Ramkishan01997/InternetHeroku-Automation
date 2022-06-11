package com.herokuapp.theinternet;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimeOutException {

	private WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	private void setUp() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@SuppressWarnings("deprecation")
	@Test(priority = 1)
	public void notVisibleTexxt() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		WebElement button = driver.findElement(By.xpath("//div[@id='start']/button"));
		button.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement outputtext = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish")));

		String actText = "Hello World!";
		String expText = outputtext.getText();

		System.out.println("acttext" + actText);
		Assert.assertTrue(actText.contains(expText), "expected text is \n:" + actText + "but found" + expText);

	}

	@SuppressWarnings("deprecation")
	@Test(priority = 2)
	public void noSuchElementException() {
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
		WebElement button = driver.findElement(By.xpath("//div[@id='start']/button"));
		button.click();

		WebElement outputtext = driver.findElement(By.id("finish"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(outputtext));
		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		String actText = "Hello World!";
		String expText = outputtext.getText();

		System.out.println("acttext" + actText);
		Assert.assertTrue(actText.contains(expText), "expected text is \n:" + actText + "but found" + expText);

	}

	@Test(priority = 3)
	public void staleReferenceException() {
		driver.get("https://the-internet.herokuapp.com/dynamic_controls");

		WebElement checkbox = driver.findElement(By.xpath("//div[@id='checkbox']"));

		WebElement removeButton = driver.findElement(By.xpath("//button[contains (text(),'Remove')]"));

		WebDriverWait wait = new WebDriverWait(driver, 10);

		removeButton.click();
//		wait.until(ExpectedConditionsOfInvisibilityOf(checkbox));
//		Assert.assertFalse(checkbox.isDisplayed());
		// instead inVisibilityOf we can use stalenessOf
		Assert.assertTrue(wait.until(ExpectedConditions.invisibilityOf(checkbox)), "chechbox is still visible");

	}

	private Function ExpectedConditionsOfInvisibilityOf(WebElement checkbox) {
		// TODO Auto-generated method stub
		return null;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}

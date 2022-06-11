package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeUserName {

	// incorrect parameter
	@Parameters({"username" ,"password", "expText"})
	@Test(priority=1 ,groups= {"NegativeTest","SmokeTest"})
	public void negativeLoginTest(String uName ,String pass, String exptext) {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://the-internet.herokuapp.com/login");
		System.out.println("page is opened"+uName+"and "+pass);
		
//		String expText = "Your username is invalid!";

		WebElement username = driver.findElement(By.name("username"));
		username.sendKeys(uName);

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(pass);

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleep1();
		WebElement outpTitle = driver.findElement(By.id("flash"));
		String ActTitle = outpTitle.getText();
		System.out.println(ActTitle);
		Assert.assertTrue( ActTitle.contains(exptext), "invalid user name \n actual: "
		+ ActTitle + "\n expected" +exptext);

		driver.quit();
	}

	private void sleep1() {
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
//	// incorrect usernametest
//		@Test(priority=1,enabled=false  ,groups= {"NegativeTest","SmokeTest"})
//		public void negativeLoginTest() {
//
//			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//			WebDriver driver = new ChromeDriver();
//			driver.manage().window().maximize();
//			driver.get("https://the-internet.herokuapp.com/login");
//			System.out.println("page is opened");
//			
//			String expText = "Your username is invalid!";
//
//			WebElement username = driver.findElement(By.name("username"));
//			username.sendKeys("tomsermith");
//
//			WebElement password = driver.findElement(By.name("password"));
//			password.sendKeys("SuperSecretPassword!");
//
//			driver.findElement(By.xpath("//button[@type='submit']")).click();
//			sleep1();
//			WebElement outpTitle = driver.findElement(By.id("flash"));
//			String ActTitle = outpTitle.getText();
//			System.out.println(ActTitle);
//			Assert.assertTrue( ActTitle.contains(expText), "invalid user name \n actual: "
//			+ ActTitle + "\n expected" +expText);
//
//			driver.quit();
//		}
//
//		private void sleep1() {
//			try {
//				Thread.sleep(3000);
//
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	// incorrect pass word test
//	@Test(priority=2,groups= {"SmokeTest"})
//	public void inCorrectPass() {
//
//		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
////		driver.manage().window().maximize();
//		driver.get("https://the-internet.herokuapp.com/login");
//		System.out.println("page is opened");
//		
//		String expText = "Your password is invalid!";
//
//		WebElement username = driver.findElement(By.name("username"));
//		username.sendKeys("tomsmith");
//
//		WebElement password = driver.findElement(By.name("password"));
//		password.sendKeys("jhbfjjbh!");
//
//		driver.findElement(By.xpath("//button[@type='submit']")).click();
//		sleep();
//		WebElement outpTitle = driver.findElement(By.id("flash"));
//		String ActTitle = outpTitle.getText();
//		System.out.println(ActTitle);
//		Assert.assertTrue( ActTitle.contains(expText), "invalid password name \n actual: "
//		+ ActTitle + "\n expected" +expText);
//
//		driver.quit();
//	}
//
//	private void sleep() {
//		try {
//			Thread.sleep(3000);
//
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

}

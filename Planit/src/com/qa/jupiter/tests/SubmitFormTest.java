package com.qa.jupiter.tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SubmitFormTest {

	WebDriver driver;
	WebDriver wait;

	@BeforeMethod
	public void setUp() {

		System.setProperty("webdriver.chrome.driver","C://chromedriver_win32//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://jupiter.cloud.planittesting.com/#/");

	}

	@Test(priority=1,invocationCount = 5)
	@Parameters({"foreName","email","message"})
	public void clickOnSubmitFormTest(String foreName, String email, String message) throws InterruptedException {


		driver.findElement(By.xpath("//a[@href='#/contact']")).click();
		//Thread.sleep(2000);
		driver.findElement(By.id("forename")).clear();
		driver.findElement(By.id("forename")).sendKeys(foreName);
		
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(email);

	    driver.findElement(By.id("message")).clear();
	    driver.findElement(By.id("message")).sendKeys(message);

		
		//submit button
		driver.findElement(By.xpath("//a[normalize-space()='Submit']")).click();
		Thread.sleep(5000);

		System.out.println("foreName: " +foreName);
		System.out.println("email: " +email);
		System.out.println("message: " +message);

	}

	/**
	 * Test to check Alert message on the screen
	 * @throws InterruptedException
	 */
	@Test(priority=2)
	@Parameters({"foreName"})
	public void verifyAlertMessage(String foreName) throws InterruptedException {
		
			
	//	WebElement msg = driver.findElement(By.xpath("//div[@class='alert alert-success']//strong[@class='ng-binding']"));
	//	String text=msg.getText();
		String expectedmessage = "Thanks <foreName>, we appreciate your feedback.";
		//Assert.assertEquals(text,expectedmessage);
				
	}

	/**
	 * Tear down the set up after the test completes
	 * @throws InterruptedException 
	 */
	
	@AfterMethod
	public void teardownTest() throws InterruptedException {
		
		driver.quit();
		System.out.println("SubmitFormTest completed successfully");
	}

}



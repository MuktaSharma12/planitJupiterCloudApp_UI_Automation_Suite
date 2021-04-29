package com.qa.jupiter.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

public class ContactsLinkTest {

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

	@Test
	public void clickOnContactsLinkTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[@href='#/contact']")).click();
		driver.findElement(By.xpath("//a[contains(.,'Submit')]")).click();
		Thread.sleep(2000);


		//this will capture error message
		String expect_msg="Forename is required"; 
		WebElement element1 =driver.findElement(By.id("forename-err"));

		String actual_msg = element1.getText();
		System.out.println("Error message is: "+actual_msg);

		//verify error message with assertion
		Assert.assertEquals(expect_msg, actual_msg);   


		String exp_msg = "Email is required";
		WebElement element2 = driver.findElement(By.id("email-err"));
		String act_msg= element2.getText();
		System.out.println("Error message is: "+act_msg);

		//verify error message with assertion
		Assert.assertEquals(exp_msg, act_msg); 


		String emsg = "Message is required"; 
		WebElement element3 = driver.findElement(By.id("message-err"));

		String amsg = element3.getText();
		System.out.println("Error message is: "+amsg);

		//verify error message with assertion
		Assert.assertEquals(emsg, amsg); 
		System.out.println("Error validations test completed");

	}

	/**
	 * Tear down the set up after the test completes
	 * @throws InterruptedException 
	 */
	@AfterMethod
	public void teardownTest() throws InterruptedException {
		driver.quit();
		Thread.sleep(1000);
		System.out.println("ContactsLinkTest completed successfully");
	}
}

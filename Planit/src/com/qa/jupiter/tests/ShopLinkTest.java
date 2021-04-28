package com.qa.jupiter.tests;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShopLinkTest {

	WebDriver driver;
	//WebDriverWait wait;

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
	public void clickOnShopLinkTest() throws InterruptedException {

		driver.findElement(By.xpath("//a[text()='Shop']")).click();
		Thread.sleep(2000);
		//identify and click on funny cow
		for (int i = 0; i < 2; i++) {
			WebElement funnycow = driver.findElement(By.xpath("//li[@id='product-6']//a[@class='btn btn-success'][normalize-space()='Buy']"));
			funnycow.click();
		}

		//click on Fluffy Bunny
		driver.findElement(By.cssSelector("li[id='product-4'] a[class='btn btn-success']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@href='#/cart']")).click();
		Thread.sleep(2000);

		//Read the items from the cart using datatable
		WebElement table = driver.findElement(By.xpath("//table[@class='table table-striped cart-items']"));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				System.out.println("content >>   " + cell.getText());
			}
		}
	}

	/**
	 * Tear down the set up after the test completes
	 * @throws InterruptedException 
	 */
	@AfterMethod
	public void teardownTest() {
		driver.quit();
		System.out.println("ShopLinkTest completed successfully");
	}
}

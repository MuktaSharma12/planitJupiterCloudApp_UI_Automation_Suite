package com.qa.jupiter.tests;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TotalPriceTest {
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
		Thread.sleep(3000);
		//identify and click on stuff frogs
		for (int i = 0; i < 2; i++) {
			WebElement stufffrogs = driver.findElement(By.cssSelector("li[id='product-2'] a[class='btn btn-success']"));
			stufffrogs.click();
			Thread.sleep(2000);
		}

		//locate and click on Fluffy Bunny
		for (int i = 0; i < 5; i++) {
			WebElement fluffybunny = driver.findElement(By.cssSelector("li[id='product-4'] a[class='btn btn-success']"));
			fluffybunny.click();
			Thread.sleep(4000);
		}

		for (int i = 0; i < 3; i++) {
			WebElement valentinebear = driver.findElement(By.cssSelector("li[id='product-7'] a[class='btn btn-success']"));
			valentinebear.click();
			Thread.sleep(2000);
		}

		driver.findElement(By.xpath("//a[@href='#/cart']")).click();
		Thread.sleep(2000);

		//WebElement table =driver.findElement(By.xpath("//table[@class='table table-striped cart-items']"));
		//System.out.println(table.getText());

		List<WebElement> list_of_products = driver.findElements(By.xpath("//table[@class='table table-striped cart-items']"));


		List<WebElement> list_of_products_price = driver.findElements(By.xpath("//td[normalize-space()='$10.99']"));
		//Use of HashMaop to store Products and Their prices(after conversion to Integer)
		String product_name;
		String product_price;
		int int_product_price;
		HashMap<Integer, String> map_final_products = new HashMap<Integer,String>();
		for(int i=0;i<list_of_products.size();i++) {
			product_name = list_of_products.get(i).getText();//Iterate and fetch product name

			product_price = list_of_products_price.get(i).getText();//Iterate and fetch product price
			product_price = product_price.replaceAll("[^0-9]", "");//Replace anything with space other than numbers
			int_product_price = Integer.parseInt(product_price);//Convert to Integer
			map_final_products.put(int_product_price,product_name);//Add product and price in HashMap
		}
		Reporter.log("Product Name and price fetched from UI and saved in HashMap as:" + map_final_products.toString() + "<br>",true);

		//locate and get the price for stufffrogs

		String expected_priceitem = "$10.99";
		WebElement price = driver.findElement(By.xpath("//td[normalize-space()='$10.99']"));
		String actual_priceitem =	price.getText();


		//verify the price for stufffrogs
		Assert.assertEquals(expected_priceitem, actual_priceitem); 
		System.out.println("The price of Stufffrogs is " +actual_priceitem);

		//locate and get the price for fluffybunny
		String expected_priceitem_bunny = "$9.99";
		WebElement pricebunny = driver.findElement(By.xpath("//td[normalize-space()='$9.99']"));
		String actual_priceitem_bunny =	pricebunny.getText();

		//verify the price for fluffybunny
		Assert.assertEquals(expected_priceitem_bunny, actual_priceitem_bunny); 
		System.out.println("The price of fluffybunny is " +actual_priceitem_bunny);

		//locate and get the price for valentine bear
		String expected_priceitem_valentine = "$14.99";
		WebElement pricevalentine = driver.findElement(By.xpath("//td[normalize-space()='$14.99']"));
		String actual_pricevalentine = pricevalentine.getText();

		//verify the price for valentine bear
		Assert.assertEquals(expected_priceitem_valentine, actual_pricevalentine); 
		System.out.println("The price of valentinebunny is " +actual_pricevalentine);

		String exp_price_stuffedfrog = "$21.98";
		WebElement quantity = driver.findElement(By.xpath("//input[@value='2']"));
		String act_price_stuffedfrog = quantity.getText();


	}






	@AfterMethod
	public void teardownTest() {
		driver.quit();
		System.out.println("TotalPriceTest completed successfully");
	}
}

package SeleniumFramework.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.CheckoutPage;
import SeleniumFramework.pageobject.ConfirmationPage;
import SeleniumFramework.pageobject.LandingPage;
import SeleniumFramework.pageobject.OrderPage;
import SeleniumFramework.pageobject.ProductCatalogue;
import SeleniumFramework.TestComponents.BaseTest;

public class StandAloneTest_4_Screenshot_eg extends BaseTest{

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase_Screenshot"})
	public void submitOrder_Screenshot(HashMap<String,String> input) throws IOException, InterruptedException
	{
 
       
       ProductCatalogue productCatalog = lPage.loginApp(input.get("email"), input.get("password"));
       List<WebElement> products = productCatalog.getProducts();
       productCatalog.addProductToCart(input.get("productName"));
       CartPage cPageObj = productCatalog.goToCart();
       Boolean match = cPageObj.verifyProductDisplay(input.get("productName"));
       Assert.assertTrue(match);      
       CheckoutPage checkoutpage = cPageObj.goToCheckOut();
       ConfirmationPage confirmPage = checkoutpage.submitOrder("India");
       confirmPage.getConfirmationMsg();
       String confirmMsg = confirmPage.getConfirmationMsg();
       Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));  
      
	}
	
	@Test(dependsOnMethods=("submitOrder_Screenshot"))
	public void orderHistoryCheck() {
		 ProductCatalogue productCatalog = lPage.loginApp("thakorgopu725@gmail.com", "Thakor1!");
		 OrderPage orderpage =  productCatalog.goToOrder();
		 Assert.assertTrue(orderpage.verifyOrderedDisplay(productName));
	}
	
	public String getScreenshot(String testcaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
		
		//Extent Reports - Framwork_5
	}
	
	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map1 = new HashMap<String,String>();
		map1.put("email", "thakorgopu725@gmail.com");
		map1.put("password", "Thakor1!");
		map1.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map2 = new HashMap<String,String>();
		map2.put("email", "thakorgopu726@gmail.com");
		map2.put("password", "Thakor1!");
		map2.put("productName", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map1},{map2}};
	}

}

package SeleniumFramework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class StandAloneTest_4 extends BaseTest{

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(String username, String password, String productName) throws IOException, InterruptedException
	{
 
       
       ProductCatalogue productCatalog = lPage.loginApp(username, password);
       List<WebElement> products = productCatalog.getProducts();
       productCatalog.addProductToCart(productName);
       CartPage cPageObj = productCatalog.goToCart();
       Boolean match = cPageObj.verifyProductDisplay(productName);
       Assert.assertTrue(match);      
       CheckoutPage checkoutpage = cPageObj.goToCheckOut();
       ConfirmationPage confirmPage = checkoutpage.submitOrder("India");
       confirmPage.getConfirmationMsg();
       String confirmMsg = confirmPage.getConfirmationMsg();
       Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));  
      
	}
	
	@Test(dependsOnMethods=("submitOrder"))
	public void orderHistoryCheck() {
		 ProductCatalogue productCatalog = lPage.loginApp("thakorgopu725@gmail.com", "Thakor1!");
		 OrderPage orderpage =  productCatalog.goToOrder();
		 Assert.assertTrue(orderpage.verifyOrderedDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"thakorgopu725@gmail.com","Thakor1!","ZARA COAT 3"},{"thakorgopu726@gmail.com","Thakor1!","ADIDAS ORIGINAL"}};
	}

}

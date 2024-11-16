package SeleniumFramework.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;


import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.CheckoutPage;
import SeleniumFramework.pageobject.ConfirmationPage;
import SeleniumFramework.pageobject.ProductCatalogue;
import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"Errorhandling"}, retryAnalyzer=Retry.class)
	public void checkLoginCreds() throws IOException, InterruptedException
	{
 
       lPage.loginApp("thakorgopu725@gmail.com", "Thakor5!");
       Assert.assertEquals("Incorrect email or password.", lPage.getErrorMsg());
      
      
	}
	
	@Test
	public void checkDesiredProduct() throws IOException, InterruptedException
	{
 
       String productName = "ZARA COAT 3";
       ProductCatalogue productCatalog = lPage.loginApp("thakorgopu725@gmail.com", "Thakor1!");
       List<WebElement> products = productCatalog.getProducts();
       productCatalog.addProductToCart(productName);
       CartPage cPageObj = productCatalog.goToCart();
       Boolean match = cPageObj.verifyProductDisplay(productName);
       Assert.assertTrue(match);      
      
	}
}

package SeleniumFramework.stepDefinations;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.CheckoutPage;
import SeleniumFramework.pageobject.ConfirmationPage;
import SeleniumFramework.pageobject.LandingPage;
import SeleniumFramework.pageobject.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinationImpl extends BaseTest {

	public LandingPage lPage;
	public ProductCatalogue productCatalog;
	public ConfirmationPage confirmPage; 
    @Given("^I landed on Ecommerce page$")
    public void i_landed_on_ecommerce_page() throws Throwable {
    	lPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_with_username_and_password(String name, String password) throws Throwable {
    	 productCatalog = lPage.loginApp(name, password);
    }

    @When("^I add the product (.+) from cart$")
    public void i_add_the_product_from_cart(String productName) throws Throwable {
    	 List<WebElement> products = productCatalog.getProducts();
         productCatalog.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String productName) throws Throwable {
    	CartPage cPageObj = productCatalog.goToCart();
        Boolean match = cPageObj.verifyProductDisplay(productName);
        Assert.assertTrue(match);      
        CheckoutPage checkoutpage = cPageObj.goToCheckOut();
        confirmPage = checkoutpage.submitOrder("India");
        confirmPage.getConfirmationMsg();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void something_message_is_displayed_on_confirmationpage(String string) throws Throwable {
    	String confirmMsg = confirmPage.getConfirmationMsg();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase(string)); 
        driver.close();
        
    }
    
    
    @Then("{string} message is displayed on landingPage")
    public void something_message_is_displayed_on_landingPage(String string) throws Throwable {
    	
        Assert.assertEquals(string, lPage.getErrorMsg());
        driver.close();
        
    }

 

}
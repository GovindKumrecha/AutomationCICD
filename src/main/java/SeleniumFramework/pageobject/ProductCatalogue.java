package SeleniumFramework.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
   
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//  List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement animationT;
	
	@FindBy(css="[routerlink*='cart']")
    WebElement viewCartBtn;
	
	By productBy = By.cssSelector(".mb-3");
	By addCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	
	public List<WebElement> getProducts() {
		waitforElementToAppear(productBy);
		return products;
	}
	
   public WebElement getProductByName(String productName) {
	  WebElement desiredProd = getProducts().stream().filter(product
		       ->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	  return desiredProd;
   }
   
   public void addProductToCart(String pName) throws InterruptedException {
	   WebElement prod = getProductByName(pName);
	   prod.findElement(addCart).click();
	   waitforElementToAppear(toastMsg);
	   waitforElementToDisappear(animationT);
	  
   }
   
  
   
   
}

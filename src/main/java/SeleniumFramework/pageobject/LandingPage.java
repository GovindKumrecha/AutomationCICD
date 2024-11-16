package SeleniumFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
   
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement  = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement uEmail;
	
	@FindBy(id="userPassword")
	WebElement uPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	
	public ProductCatalogue loginApp(String uemail, String upwd) {
		uEmail.sendKeys(uemail);
		uPassword.sendKeys(upwd);
		loginBtn.click();
		ProductCatalogue productCatalog = new ProductCatalogue(driver);
		return productCatalog;
	}
	
	public String getErrorMsg() {
		
		waitforWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
	public void goTo() {
		 driver.get("https://rahulshettyacademy.com/client");
	}
	
}

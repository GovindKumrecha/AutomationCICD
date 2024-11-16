package SeleniumFramework.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFramework.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
    
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement desiredCountry;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitBtn;
	
	By findBy = By.cssSelector(".ta-results");
	
	
	public ConfirmationPage submitOrder(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(desiredCountry, countryName).build().perform();
		waitforElementToAppear(findBy);
		selectCountry.click();
		submitBtn.click();
		return new ConfirmationPage(driver);
		
	}
	   
	
}

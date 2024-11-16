package SeleniumFramework.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFramework.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedList;
	
	
	
	public Boolean verifyOrderedDisplay(String productName) {
		Boolean match = orderedList.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}

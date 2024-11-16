package SeleniumFramework.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFramework.pageobject.CartPage;
import SeleniumFramework.pageobject.OrderPage;

public class AbstractComponents {
       
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersBtn;

	public void waitforElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}

	public void waitforWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	public void waitforElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//	     wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCart() {
		
		cartBtn.click();
		CartPage cPage= new CartPage(driver);
		return cPage;
	}
	
	public OrderPage goToOrder() {
		ordersBtn.click();
		OrderPage ordpg = new OrderPage(driver);
		return ordpg;
	}
}

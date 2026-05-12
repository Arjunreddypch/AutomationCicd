package arjun.academy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arjun.academy.abstractcomponents.AbstractComponents;


public class LandingPage extends AbstractComponents{

	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement user_Email;
	@FindBy(id="userPassword")
	WebElement user_Password;
	@FindBy(name="login")
	WebElement button_login;
	
	public ProductCatalog loginToApplication(String email, String pwd) {
		
		user_Email.sendKeys(email);
		user_Password.sendKeys(pwd);
		button_login.click();
		
		return new ProductCatalog(driver);
	}
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	
	
}

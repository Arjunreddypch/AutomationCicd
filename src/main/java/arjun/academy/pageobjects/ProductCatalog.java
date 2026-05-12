package arjun.academy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import arjun.academy.abstractcomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	@FindBy(css = "button[routerlink*='cart']")
	WebElement number_of_cart_items;
	
	By product_items = By.cssSelector(".mb-3");
	By toastMessage = By.id("toast-container");
	By button_addItemToCart=By.cssSelector(".card-body button:last-of-type");
	
	public List<WebElement> getProductList() {

		waitElementToBeVisible(product_items);
		return products;
	}
	
	public WebElement getProductByName(String name) {
		WebElement element = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().contains(name)).findFirst()
				.orElse(null);
		return element;
	}
	
	public void addItemToCart(String name) {
		getProductByName(name).findElement(button_addItemToCart).click();
		waitElementToBeVisible(toastMessage);
		waitforinvisibilityOfElementLocated(toastMessage);
	}
	
	public void clickOnCartItem() {
		number_of_cart_items.click();
	}

	
}

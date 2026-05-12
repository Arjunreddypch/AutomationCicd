package arjun.academy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import arjun.academy.pageobjects.ProductCatalog;
import arjun.academy.testcomponents.BaseTest;
public class StandAloneTest3 extends BaseTest{

	@Test
	public  void test1() throws IOException {
		
		String name = "ZARA COAT 3";
		
		ProductCatalog pc=lp.loginToApplication("arjunacademy@gmail.com", "ArjunAcademy$9");
		pc.addItemToCart(name);
		pc.clickOnCartItem();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartWrap.ng-star-inserted h3"));
		Boolean isPresent = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(name));
		Assert.assertTrue(isPresent);
		driver.findElements(By.cssSelector(".btn.btn-primary")).get(2).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//Span[contains(text(),'India')])[2]")));
		driver.findElement(By.xpath("(//Span[contains(text(),'India')])[2]")).click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1"))));
		String orderConfirmMessage = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(orderConfirmMessage);
		Assert.assertTrue(orderConfirmMessage.contains("THANKYOU FOR THE ORDER."));

	}
	
	
}

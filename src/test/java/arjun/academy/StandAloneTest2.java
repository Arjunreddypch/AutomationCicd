package arjun.academy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 {

	public static void main(String[] args) {
		String name = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		driver.findElement(By.id("userEmail")).sendKeys("arjunacademy@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("ArjunAcademy$9");
		driver.findElement(By.name("login")).click();

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement element = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().contains(name)).findFirst()
				.orElse(null);

		element.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

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
		driver.quit();

	}
}

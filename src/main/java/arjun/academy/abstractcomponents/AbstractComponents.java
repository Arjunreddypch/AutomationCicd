package arjun.academy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents{
	public WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}
	
	public void waitElementToBeVisible(By Locator){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}
	
	public void waitforinvisibilityOfElementLocated(By Locator){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(Locator));
	}
}

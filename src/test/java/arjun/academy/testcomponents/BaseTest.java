package arjun.academy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

import arjun.academy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	Properties prop;
	public LandingPage lp;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//test//java//arjun//academy//resources//GlobalProperties.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		System.out.println(browserName);
		if (browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=new");
				driver = new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440, 900));
			} else
				driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {

			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {

			// edge driver
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	@BeforeMethod
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo(prop.getProperty("url"));
		return lp;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getData1() {

		return new Object[][] { { "arjunacademy@gmail.com", "ArjunAcademy$9", "ZARA COAT 3" },
				{ "arjunacademy@gmail.com", "ArjunAcademy$9", "ADIDAS ORIGINAL" } };
	}

	@DataProvider
	public Object[][] getDataUsingMap() {

		Map<String, String> map = new HashMap<>();
		map.put("email", "arjunacademy@gmail.com");
		map.put("pwd", "ArjunAcademy$9");
		map.put("Product", "ZARA COAT 3");
		Map<String, String> map1 = new HashMap<>();
		map1.put("email", "arjunacademy@gmail.com");
		map1.put("pwd", "ArjunAcademy$9");
		map1.put("Product", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map1 } };
	}

	@DataProvider
	public Object[][] getDataUsingJson() throws IOException {

		String filePath = System.getProperty("user.dir")
				+ "//src//test//java//arjun//academy//data//PurchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		// read String to HashMap
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new com.fasterxml.jackson.core.type.TypeReference<List<HashMap<String, String>>>() {
				});

		System.out.println(data.get(0));
		System.out.println(data.get(1));

		return data;
	}

	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "//screenshots//" + testcaseName + ".png"));
		return System.getProperty("user.dir") + "//screenshots//" + testcaseName + ".png";
	}
}

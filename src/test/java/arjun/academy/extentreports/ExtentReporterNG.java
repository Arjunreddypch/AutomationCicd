package arjun.academy.extentreports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	ExtentReports er;
	@BeforeTest
	
	public void bt() {
		
		String reportsPath=System.getProperty("user.dir")+"//reports//index.html";
		
		ExtentSparkReporter esr=new ExtentSparkReporter(reportsPath);
		esr.config().setReportName("Web Automation Results");
		esr.config().setDocumentTitle("Test results");
		
		er=new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Tester", "Arjun");
		
	}
	
	
	WebDriver driver;
	@Test
	public void initialDemo() {
		
		ExtentTest test= er.createTest("initialDemo");
		driver =new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com");
		
		driver.close();
		
		er.flush();
		
	}
	
	
	public static ExtentReports getReportObject() {
		
		String reportsPath=System.getProperty("user.dir")+"//reports//index.html";
		
		ExtentSparkReporter esr=new ExtentSparkReporter(reportsPath);
		esr.config().setReportName("Web Automation Results");
		esr.config().setDocumentTitle("Test results");
		
		ExtentReports er=new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Tester", "Arjun");
		er.createTest(reportsPath);
		return er;
	}

}

package arjun.academy.extentreports;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	@Override
	public boolean retry(ITestResult result) {

		int min=0, max=2;
		if(min<max) {
			min++;
			return true;
		}
		return false;
	}

}

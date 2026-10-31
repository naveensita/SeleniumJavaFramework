package com.coforge.sita.iborders.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.coforge.sita.iborders.tests.TestBase;

public class RetryAnalyzer extends TestBase implements IRetryAnalyzer{
	
	int count = 0;
	int maxTry =Integer.parseInt(configProperies.getProperty("retry"));
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count<maxTry) {
			count++;
			return true;
		}
		return false;
	}

}

package com.coforge.sita.iborders.utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.coforge.sita.iborders.tests.CucumberTestBase;

public class TestContextSetup {

	public WebDriver driver;
	public CucumberTestBase cucumberTestBase;

	
	public TestContextSetup() throws IOException {
		cucumberTestBase = new CucumberTestBase();
	}
}

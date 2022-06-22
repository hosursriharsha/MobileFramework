package com.base;


import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"			
				}, 
		glue = { "com.base","com.mobileframework.stepdefs" },
		features = { "src/test/resources/Feature" }
)
public class TestRunner extends AbstractTestNGCucumberTests{

//	@Override
//	@DataProvider(parallel=true)
//    public Object[][] scenarios() {
//       return super.scenarios();
//    }
}

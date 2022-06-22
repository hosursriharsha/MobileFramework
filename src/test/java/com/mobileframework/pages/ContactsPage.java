package com.mobileframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.baseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ContactsPage extends baseClass {

	
	public boolean verifyAppLaunched() {
		return getDriver().findElement((getLocator("appHomePage"))).isDisplayed();
	}

	public void clickOnPlusIcon() {
		getDriver().findElement(getLocator("plusButton")).click();
		getDriver().findElement(getLocator("cancelButton")).click();
		
	}

	public void enterFirstName(String firstName) {
		getDriver().findElement(getLocator("firstName")).sendKeys(firstName);
		
	}

	public void enterSecondName(String lastName) {
		getDriver().findElement(getLocator("lastName")).sendKeys(lastName);
		
	}

	public void enterPhoneNumber(String randomPhoneNumber) {
		getDriver().findElement(getLocator("phone")).sendKeys(randomPhoneNumber);
		
	}

	public void clickOnButton(String button) {
		switch(button){
			case "Save":
				getDriver().findElement(getLocator("saveButton")).click();
				break;
		}
	}

	public void searchForContact(String firstName) {
		getDriver().findElement(getLocator("searchContactButton")).click();
		getDriver().findElement(getLocator("searchContactTextBox")).sendKeys(firstName);
	}

	public boolean verifyContactPresent(String firstName) {
		return getDriver().findElement(getLocator("favorites")).isDisplayed();
	}

	public void navigateBack() {
		((AndroidDriver)getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
		
	}

	public boolean verifyInSearchResults(String firstName) {
		return getDriver().findElement(getLocator("searchResult")).getText().contains(firstName);
	}

}

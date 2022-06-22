package com.mobileframework.stepdefs;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.mobileframework.pages.ContactsPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class ContactsStepDefs {

	ContactsPage contactsPage = new ContactsPage();
	Random random = new Random();
	String firstName;

	@Given("^when I launch contacts app$")
	public void when_i_launch_contacts_app() {
		Assert.assertTrue("App is not launched", contactsPage.verifyAppLaunched());
	}

	@When("^I click on plus icon to create app$")
	public void i_click_on_plus_icon_to_create_app() {
		contactsPage.clickOnPlusIcon();
	}

	@When("^I enter \"([^\"]*)\" as first Name$")
	public void i_enter_something_as_first_name(String word) {
		switch (word) {
		case "RandomFirstName":
			firstName = RandomStringUtils.randomAlphabetic(5);
			contactsPage.enterFirstName(firstName);
		}
	}

	@When("^I enter \"([^\"]*)\" as second Name$")
	public void i_enter_something_as_second_name(String word) {
		switch (word) {
		case "RandomSecondName":
			String secondName = RandomStringUtils.randomAlphabetic(5);
			contactsPage.enterSecondName(secondName);
		}
	}

	@When("^I enter \"([^\"]*)\" as phone number$")
	public void i_enter_something_as_phone_number(String phoneNumber) {
		switch (phoneNumber) {
		case "RandomPhoneNumber":
			String randomPhoneNumber = RandomStringUtils.randomNumeric(6);
			contactsPage.enterPhoneNumber(randomPhoneNumber);
		}
	}

	@When("^I click on \"([^\"]*)\" button$")
	public void i_click_on_something_button(String button) {
		contactsPage.clickOnButton(button);
	}

	@When("^I search for contact$")
	public void i_search_for_contact() {
		contactsPage.searchForContact(firstName);
	}
	
	@When("^I navigate back$")
	public void i_navigate_back() {
		contactsPage.navigateBack();
	}

	@Then("^I verify contact created$")
	public void i_verify_contact_created() {
		Assert.assertTrue("Contact is not displayed", contactsPage.verifyContactPresent(firstName));
	}
	
	@Then("^I verify contact created in search results$")
	public void i_verify_contact_created_in_results() {
		Assert.assertTrue("Contact is not displayed", contactsPage.verifyInSearchResults(firstName));
	}

}

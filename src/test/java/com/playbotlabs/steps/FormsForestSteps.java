package com.playbotlabs.steps;

import com.playbotlabs.pages.FormsForestPage;
import com.playbotlabs.utils.TestDataUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Step definitions for Forms Forest page automation
 * Maps Gherkin steps to page object methods and handles form validation
 */
public class FormsForestSteps {
    
    private FormsForestPage formsForestPage;
    private Map<String, String> testData;

    // Navigation steps
    
    @Given("I navigate to the Forms Forest page")
    public void i_navigate_to_the_forms_forest_page() {
        formsForestPage = new FormsForestPage();
        formsForestPage.navigateToFormsForest();
    }

    @Given("I am on the Forms Forest page")
    public void i_am_on_the_forms_forest_page() {
        i_navigate_to_the_forms_forest_page();
        Assert.assertTrue(formsForestPage.waitForFormToLoad(), "Forms Forest page should be loaded");
    }

    // Form field interaction steps

    @When("I fill the username field with {string}")
    public void i_fill_the_username_field_with(String username) {
        formsForestPage.fillUsername(username);
    }

    @When("I fill the password field with {string}")
    public void i_fill_the_password_field_with(String password) {
        formsForestPage.fillPassword(password);
    }

    @When("I fill the email field with {string}")
    public void i_fill_the_email_field_with(String email) {
        formsForestPage.fillEmail(email);
    }

    @When("I fill the first name field with {string}")
    public void i_fill_the_first_name_field_with(String firstName) {
        formsForestPage.fillFirstName(firstName);
    }

    @When("I fill the last name field with {string}")
    public void i_fill_the_last_name_field_with(String lastName) {
        formsForestPage.fillLastName(lastName);
    }

    @When("I fill the phone number field with {string}")
    public void i_fill_the_phone_number_field_with(String phoneNumber) {
        formsForestPage.fillPhoneNumber(phoneNumber);
    }

    @When("I fill the comments field with {string}")
    public void i_fill_the_comments_field_with(String comments) {
        formsForestPage.fillComments(comments);
    }

    @When("I fill the description field with {string}")
    public void i_fill_the_description_field_with(String description) {
        formsForestPage.fillDescription(description);
    }

    @When("I fill the message field with {string}")
    public void i_fill_the_message_field_with(String message) {
        formsForestPage.fillMessage(message);
    }

    // Combined form filling steps

    @When("I fill the form with username {string} and password {string}")
    public void i_fill_the_form_with_username_and_password(String username, String password) {
        formsForestPage.fillUsername(username);
        formsForestPage.fillPassword(password);
    }

    @When("I fill the complete form with:")
    public void i_fill_the_complete_form_with(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> formData = dataTable.asMap(String.class, String.class);
        formsForestPage.fillCompleteForm(
            formData.get("username"),
            formData.get("password"),
            formData.get("email"),
            formData.get("firstName"),
            formData.get("lastName"),
            formData.get("phoneNumber")
        );
    }

    // Test data driven steps

    @When("I fill the form with test data from {string}")
    public void i_fill_the_form_with_test_data_from(String testDataKey) {
        testData = TestDataUtils.readJsonTestData("forms-forest-data.json");
        String prefix = testDataKey + ".";
        
        formsForestPage.fillCompleteForm(
            testData.get(prefix + "username"),
            testData.get(prefix + "password"),
            testData.get(prefix + "email"),
            testData.get(prefix + "firstName"),
            testData.get(prefix + "lastName"),
            testData.get(prefix + "phoneNumber")
        );
    }

    @When("I fill the form with valid test data")
    public void i_fill_the_form_with_valid_test_data() {
        i_fill_the_form_with_test_data_from("valid");
    }

    @When("I fill the form with invalid test data")
    public void i_fill_the_form_with_invalid_test_data() {
        i_fill_the_form_with_test_data_from("invalid");
    }

    @When("I fill the basic form elements with test data from {string}")
    public void i_fill_the_basic_form_elements_with_test_data_from(String testDataKey) {
        testData = TestDataUtils.readJsonTestData("forms-forest-data.json");
        
        String username = testData.get(testDataKey + ".username");
        String password = testData.get(testDataKey + ".password");
        String email = testData.get(testDataKey + ".email");
        String comments = testData.get(testDataKey + ".comments");
        String description = testData.get(testDataKey + ".description");
        String message = testData.get(testDataKey + ".message");
        String gender = testData.get(testDataKey + ".gender");
        String experience = testData.get(testDataKey + ".experience");
        String preference = testData.get(testDataKey + ".preference");
        String interests = testData.get(testDataKey + ".interests");
        String skills = testData.get(testDataKey + ".skills");
        String terms = testData.get(testDataKey + ".terms");
        String newsletter = testData.get(testDataKey + ".newsletter");
        String notifications = testData.get(testDataKey + ".notifications");
        String marketing = testData.get(testDataKey + ".marketing");
        String privacy = testData.get(testDataKey + ".privacy");
        
        // Fill basic input fields
        if (username != null && !username.isEmpty()) formsForestPage.fillUsername(username);
        if (password != null && !password.isEmpty()) formsForestPage.fillPassword(password);
        if (email != null && !email.isEmpty()) formsForestPage.fillEmail(email);
        
        // Fill text areas
        if (comments != null && !comments.isEmpty()) formsForestPage.fillComments(comments);
        if (description != null && !description.isEmpty()) formsForestPage.fillDescription(description);
        if (message != null && !message.isEmpty()) formsForestPage.fillMessage(message);
        
        // Select radio buttons
        if (gender != null && !gender.isEmpty()) formsForestPage.selectGender(gender);
        if (experience != null && !experience.isEmpty()) formsForestPage.selectExperience(experience);
        if (preference != null && !preference.isEmpty()) formsForestPage.selectPreference(preference);
        
        // Select multiple checkboxes
        if (interests != null && !interests.isEmpty()) {
            List<String> interestsList = Arrays.asList(interests.split(","));
            formsForestPage.selectInterests(interestsList.stream().map(String::trim).toList());
        }
        if (skills != null && !skills.isEmpty()) {
            List<String> skillsList = Arrays.asList(skills.split(","));
            formsForestPage.selectSkills(skillsList.stream().map(String::trim).toList());
        }
        
        // Set single checkboxes
        if ("true".equals(terms)) formsForestPage.setTermsAcceptance(true);
        if ("true".equals(newsletter)) formsForestPage.setNewsletterSubscription(true);
        if ("true".equals(notifications)) formsForestPage.setNotifications(true);
        if ("true".equals(marketing)) formsForestPage.setMarketing(true);
        if ("true".equals(privacy)) formsForestPage.setPrivacy(true);
    }

    @When("I fill the basic form with valid test data")
    public void i_fill_the_basic_form_with_valid_test_data() {
        i_fill_the_basic_form_elements_with_test_data_from("basicFormElementsValid");
    }

    @When("I fill the basic form with minimal test data")
    public void i_fill_the_basic_form_with_minimal_test_data() {
        i_fill_the_basic_form_elements_with_test_data_from("basicFormElementsMinimal");
    }

    @When("I fill the basic form with text area test data")
    public void i_fill_the_basic_form_with_text_area_test_data() {
        i_fill_the_basic_form_elements_with_test_data_from("basicFormElementsTextAreas");
    }

    @When("I fill the basic form with checkbox test data")
    public void i_fill_the_basic_form_with_checkbox_test_data() {
        i_fill_the_basic_form_elements_with_test_data_from("basicFormElementsCheckboxes");
    }

    @When("I fill the basic form with radio button test data")
    public void i_fill_the_basic_form_with_radio_button_test_data() {
        i_fill_the_basic_form_elements_with_test_data_from("basicFormElementsRadioButtons");
    }

    // Dropdown and selection steps

    @When("I select {string} from the country dropdown")
    public void i_select_from_the_country_dropdown(String country) {
        formsForestPage.selectCountry(country);
    }

    @When("I select {string} from the state dropdown")
    public void i_select_from_the_state_dropdown(String state) {
        formsForestPage.selectState(state);
    }

    @When("I select {string} as gender")
    public void i_select_as_gender(String gender) {
        formsForestPage.selectGender(gender);
    }

    // Checkbox and agreement steps

    @When("I accept the terms and conditions")
    public void i_accept_the_terms_and_conditions() {
        formsForestPage.setTermsAcceptance(true);
    }

    @When("I do not accept the terms and conditions")
    public void i_do_not_accept_the_terms_and_conditions() {
        formsForestPage.setTermsAcceptance(false);
    }

    @When("I subscribe to the newsletter")
    public void i_subscribe_to_the_newsletter() {
        formsForestPage.setNewsletterSubscription(true);
    }

    @When("I do not subscribe to the newsletter")
    public void i_do_not_subscribe_to_the_newsletter() {
        formsForestPage.setNewsletterSubscription(false);
    }

    @When("I enable notifications")
    public void i_enable_notifications() {
        formsForestPage.setNotifications(true);
    }

    @When("I disable notifications")
    public void i_disable_notifications() {
        formsForestPage.setNotifications(false);
    }

    @When("I enable marketing")
    public void i_enable_marketing() {
        formsForestPage.setMarketing(true);
    }

    @When("I disable marketing")
    public void i_disable_marketing() {
        formsForestPage.setMarketing(false);
    }

    @When("I accept privacy policy")
    public void i_accept_privacy_policy() {
        formsForestPage.setPrivacy(true);
    }

    @When("I do not accept privacy policy")
    public void i_do_not_accept_privacy_policy() {
        formsForestPage.setPrivacy(false);
    }

    @When("I select interests: {string}")
    public void i_select_interests(String interestsList) {
        List<String> interests = Arrays.asList(interestsList.split(","));
        formsForestPage.selectInterests(interests.stream().map(String::trim).toList());
    }

    @When("I select skills: {string}")
    public void i_select_skills(String skillsList) {
        List<String> skills = Arrays.asList(skillsList.split(","));
        formsForestPage.selectSkills(skills.stream().map(String::trim).toList());
    }

    @When("I clear all interests")
    public void i_clear_all_interests() {
        formsForestPage.clearAllInterests();
    }

    @When("I clear all skills")
    public void i_clear_all_skills() {
        formsForestPage.clearAllSkills();
    }

    @When("I select {string} as experience level")
    public void i_select_as_experience_level(String experience) {
        formsForestPage.selectExperience(experience);
    }

    @When("I select {string} as preference")
    public void i_select_as_preference(String preference) {
        formsForestPage.selectPreference(preference);
    }

    // Form action steps

    @When("I submit the form")
    public void i_submit_the_form() {
        formsForestPage.submitForm();
    }

    @When("I reset the form")
    public void i_reset_the_form() {
        formsForestPage.resetForm();
    }

    @When("I clear the form")
    public void i_clear_the_form() {
        formsForestPage.clearForm();
    }

    // Form validation and assertion steps

    @Then("I should see the form is displayed")
    public void i_should_see_the_form_is_displayed() {
        Assert.assertTrue(formsForestPage.isFormDisplayed(), "Form should be displayed");
    }

    @Then("I should see the form container is displayed")
    public void i_should_see_the_form_container_is_displayed() {
        Assert.assertTrue(formsForestPage.isFormContainerDisplayed(), "Form container should be displayed");
    }

    @Then("I should see all form fields are displayed")
    public void i_should_see_all_form_fields_are_displayed() {
        Assert.assertTrue(formsForestPage.isUsernameFieldDisplayed(), "Username field should be displayed");
        Assert.assertTrue(formsForestPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        Assert.assertTrue(formsForestPage.isEmailFieldDisplayed(), "Email field should be displayed");
    }

    @Then("I should see the submit button is enabled")
    public void i_should_see_the_submit_button_is_enabled() {
        Assert.assertTrue(formsForestPage.isSubmitButtonEnabled(), "Submit button should be enabled");
    }

    @Then("I should see the submit button is displayed")
    public void i_should_see_the_submit_button_is_displayed() {
        Assert.assertTrue(formsForestPage.isSubmitButtonDisplayed(), "Submit button should be displayed");
    }

    @Then("I should see the reset button is displayed")
    public void i_should_see_the_reset_button_is_displayed() {
        Assert.assertTrue(formsForestPage.isResetButtonDisplayed(), "Reset button should be displayed");
    }

    // Form submission result validation steps

    @Then("I should see a success message")
    public void i_should_see_a_success_message() {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        Assert.assertTrue(formsForestPage.isSuccessMessageDisplayed(), "Success message should be displayed");
    }

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        Assert.assertTrue(formsForestPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }

    @Then("I should see validation errors")
    public void i_should_see_validation_errors() {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        Assert.assertTrue(formsForestPage.hasValidationErrors(), "Validation errors should be present");
    }

    @Then("I should see the success message {string}")
    public void i_should_see_the_success_message(String expectedMessage) {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        Assert.assertTrue(formsForestPage.isSuccessMessageDisplayed(), "Success message should be displayed");
        String actualMessage = formsForestPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Success message should match expected text");
    }

    @Then("I should see the error message {string}")
    public void i_should_see_the_error_message(String expectedMessage) {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        Assert.assertTrue(formsForestPage.isErrorMessageDisplayed(), "Error message should be displayed");
        String actualMessage = formsForestPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message should match expected text");
    }

    @Then("I should see the form status {string}")
    public void i_should_see_the_form_status(String expectedStatus) {
        String actualStatus = formsForestPage.getFormStatus();
        Assert.assertEquals(actualStatus, expectedStatus, "Form status should match expected value");
    }

    // Field value validation steps

    @Then("the username field should contain {string}")
    public void the_username_field_should_contain(String expectedValue) {
        String actualValue = formsForestPage.getUsernameValue();
        Assert.assertEquals(actualValue, expectedValue, "Username field should contain expected value");
    }

    @Then("the email field should contain {string}")
    public void the_email_field_should_contain(String expectedValue) {
        String actualValue = formsForestPage.getEmailValue();
        Assert.assertEquals(actualValue, expectedValue, "Email field should contain expected value");
    }

    @Then("the terms checkbox should be checked")
    public void the_terms_checkbox_should_be_checked() {
        Assert.assertTrue(formsForestPage.isTermsAccepted(), "Terms checkbox should be checked");
    }

    @Then("the terms checkbox should not be checked")
    public void the_terms_checkbox_should_not_be_checked() {
        Assert.assertFalse(formsForestPage.isTermsAccepted(), "Terms checkbox should not be checked");
    }

    @Then("the newsletter checkbox should be checked")
    public void the_newsletter_checkbox_should_be_checked() {
        Assert.assertTrue(formsForestPage.isNewsletterSubscribed(), "Newsletter checkbox should be checked");
    }

    @Then("the newsletter checkbox should not be checked")
    public void the_newsletter_checkbox_should_not_be_checked() {
        Assert.assertFalse(formsForestPage.isNewsletterSubscribed(), "Newsletter checkbox should not be checked");
    }

    @Then("the selected gender should be {string}")
    public void the_selected_gender_should_be(String expectedGender) {
        String actualGender = formsForestPage.getSelectedGender();
        Assert.assertEquals(actualGender, expectedGender, "Selected gender should match expected value");
    }

    @Then("the selected experience level should be {string}")
    public void the_selected_experience_level_should_be(String expectedExperience) {
        String actualExperience = formsForestPage.getSelectedExperience();
        Assert.assertEquals(actualExperience, expectedExperience, "Selected experience level should match expected value");
    }

    @Then("the selected preference should be {string}")
    public void the_selected_preference_should_be(String expectedPreference) {
        String actualPreference = formsForestPage.getSelectedPreference();
        Assert.assertEquals(actualPreference, expectedPreference, "Selected preference should match expected value");
    }

    @Then("the comments field should contain {string}")
    public void the_comments_field_should_contain(String expectedValue) {
        String actualValue = formsForestPage.getCommentsValue();
        Assert.assertEquals(actualValue, expectedValue, "Comments field should contain expected value");
    }

    @Then("the description field should contain {string}")
    public void the_description_field_should_contain(String expectedValue) {
        String actualValue = formsForestPage.getDescriptionValue();
        Assert.assertEquals(actualValue, expectedValue, "Description field should contain expected value");
    }

    @Then("the message field should contain {string}")
    public void the_message_field_should_contain(String expectedValue) {
        String actualValue = formsForestPage.getMessageValue();
        Assert.assertEquals(actualValue, expectedValue, "Message field should contain expected value");
    }

    @Then("the notifications checkbox should be checked")
    public void the_notifications_checkbox_should_be_checked() {
        Assert.assertTrue(formsForestPage.isNotificationsEnabled(), "Notifications checkbox should be checked");
    }

    @Then("the notifications checkbox should not be checked")
    public void the_notifications_checkbox_should_not_be_checked() {
        Assert.assertFalse(formsForestPage.isNotificationsEnabled(), "Notifications checkbox should not be checked");
    }

    @Then("the marketing checkbox should be checked")
    public void the_marketing_checkbox_should_be_checked() {
        Assert.assertTrue(formsForestPage.isMarketingEnabled(), "Marketing checkbox should be checked");
    }

    @Then("the marketing checkbox should not be checked")
    public void the_marketing_checkbox_should_not_be_checked() {
        Assert.assertFalse(formsForestPage.isMarketingEnabled(), "Marketing checkbox should not be checked");
    }

    @Then("the privacy checkbox should be checked")
    public void the_privacy_checkbox_should_be_checked() {
        Assert.assertTrue(formsForestPage.isPrivacyEnabled(), "Privacy checkbox should be checked");
    }

    @Then("the privacy checkbox should not be checked")
    public void the_privacy_checkbox_should_not_be_checked() {
        Assert.assertFalse(formsForestPage.isPrivacyEnabled(), "Privacy checkbox should not be checked");
    }

    @Then("the interest {string} should be selected")
    public void the_interest_should_be_selected(String interest) {
        Assert.assertTrue(formsForestPage.isInterestSelected(interest), "Interest '" + interest + "' should be selected");
    }

    @Then("the interest {string} should not be selected")
    public void the_interest_should_not_be_selected(String interest) {
        Assert.assertFalse(formsForestPage.isInterestSelected(interest), "Interest '" + interest + "' should not be selected");
    }

    @Then("the skill {string} should be selected")
    public void the_skill_should_be_selected(String skill) {
        Assert.assertTrue(formsForestPage.isSkillSelected(skill), "Skill '" + skill + "' should be selected");
    }

    @Then("the skill {string} should not be selected")
    public void the_skill_should_not_be_selected(String skill) {
        Assert.assertFalse(formsForestPage.isSkillSelected(skill), "Skill '" + skill + "' should not be selected");
    }

    @Then("I should have {int} interests selected")
    public void i_should_have_interests_selected(int expectedCount) {
        List<String> selectedInterests = formsForestPage.getSelectedInterests();
        Assert.assertEquals(selectedInterests.size(), expectedCount, "Number of selected interests should match expected count");
    }

    @Then("I should have {int} skills selected")
    public void i_should_have_skills_selected(int expectedCount) {
        List<String> selectedSkills = formsForestPage.getSelectedSkills();
        Assert.assertEquals(selectedSkills.size(), expectedCount, "Number of selected skills should match expected count");
    }

    // Validation error specific steps

    @Then("I should see {int} validation error(s)")
    public void i_should_see_validation_errors(int expectedCount) {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        List<String> validationErrors = formsForestPage.getValidationErrors();
        Assert.assertEquals(validationErrors.size(), expectedCount, 
            "Number of validation errors should match expected count");
    }

    @Then("I should see validation error containing {string}")
    public void i_should_see_validation_error_containing(String expectedErrorText) {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
        List<String> validationErrors = formsForestPage.getValidationErrors();
        boolean errorFound = validationErrors.stream()
            .anyMatch(error -> error.contains(expectedErrorText));
        Assert.assertTrue(errorFound, 
            "Should find validation error containing: " + expectedErrorText);
    }

    // Form state validation steps

    @Then("the form should be loaded successfully")
    public void the_form_should_be_loaded_successfully() {
        Assert.assertTrue(formsForestPage.waitForFormToLoad(), "Form should be loaded successfully");
    }

    @Then("the form submission should complete")
    public void the_form_submission_should_complete() {
        Assert.assertTrue(formsForestPage.waitForFormSubmission(), "Form submission should complete");
    }

    // Negative validation steps

    @Then("I should not see a success message")
    public void i_should_not_see_a_success_message() {
        Assert.assertFalse(formsForestPage.isSuccessMessageDisplayed(), "Success message should not be displayed");
    }

    @Then("I should not see an error message")
    public void i_should_not_see_an_error_message() {
        Assert.assertFalse(formsForestPage.isErrorMessageDisplayed(), "Error message should not be displayed");
    }

    @Then("I should not see validation errors")
    public void i_should_not_see_validation_errors() {
        Assert.assertFalse(formsForestPage.hasValidationErrors(), "Validation errors should not be present");
    }

    // ========== FORMS FOREST SPECIFIC VALIDATION STEP DEFINITIONS ==========

    @Then("the form submission should be successful")
    public void the_form_submission_should_be_successful() {
        Assert.assertTrue(formsForestPage.validateFormSubmissionSuccess(), 
            "Form submission should be validated as successful");
    }

    @Then("the form submission should fail with errors")
    public void the_form_submission_should_fail_with_errors() {
        Assert.assertTrue(formsForestPage.validateFormSubmissionError(), 
            "Form submission should be validated as failed with errors");
    }

    @Then("I should see the error message contains {string}")
    public void i_should_see_the_error_message_contains(String expectedErrorText) {
        Assert.assertTrue(formsForestPage.validateErrorMessage(expectedErrorText), 
            "Error message should contain: " + expectedErrorText);
    }

    @Then("I should see validation error for field {string} containing {string}")
    public void i_should_see_validation_error_for_field_containing(String fieldName, String expectedMessage) {
        Assert.assertTrue(formsForestPage.validateFieldValidationError(fieldName, expectedMessage), 
            "Field '" + fieldName + "' should have validation error containing: " + expectedMessage);
    }

    @Then("all required fields should show validation errors")
    public void all_required_fields_should_show_validation_errors() {
        Assert.assertTrue(formsForestPage.validateRequiredFieldErrors(), 
            "All required fields should show validation errors when empty");
    }

    @Then("the email field should show format validation error for {string}")
    public void the_email_field_should_show_format_validation_error_for(String invalidEmail) {
        Assert.assertTrue(formsForestPage.validateEmailFormatError(invalidEmail), 
            "Email field should show format validation error for: " + invalidEmail);
    }

    // Form field state validation steps

    @Then("all form fields should be enabled")
    public void all_form_fields_should_be_enabled() {
        Assert.assertTrue(formsForestPage.validateAllFieldsState(true), 
            "All form fields should be enabled");
    }

    @Then("all form fields should be disabled")
    public void all_form_fields_should_be_disabled() {
        Assert.assertTrue(formsForestPage.validateAllFieldsState(false), 
            "All form fields should be disabled");
    }

    @Then("the username field should be enabled")
    public void the_username_field_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isUsernameFieldEnabled(), 
            "Username field should be enabled");
    }

    @Then("the username field should be disabled")
    public void the_username_field_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isUsernameFieldEnabled(), 
            "Username field should be disabled");
    }

    @Then("the password field should be enabled")
    public void the_password_field_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isPasswordFieldEnabled(), 
            "Password field should be enabled");
    }

    @Then("the password field should be disabled")
    public void the_password_field_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isPasswordFieldEnabled(), 
            "Password field should be disabled");
    }

    @Then("the email field should be enabled")
    public void the_email_field_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isEmailFieldEnabled(), 
            "Email field should be enabled");
    }

    @Then("the email field should be disabled")
    public void the_email_field_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isEmailFieldEnabled(), 
            "Email field should be disabled");
    }

    @Then("the first name field should be enabled")
    public void the_first_name_field_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isFirstNameFieldEnabled(), 
            "First name field should be enabled");
    }

    @Then("the first name field should be disabled")
    public void the_first_name_field_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isFirstNameFieldEnabled(), 
            "First name field should be disabled");
    }

    @Then("the last name field should be enabled")
    public void the_last_name_field_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isLastNameFieldEnabled(), 
            "Last name field should be enabled");
    }

    @Then("the last name field should be disabled")
    public void the_last_name_field_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isLastNameFieldEnabled(), 
            "Last name field should be disabled");
    }

    @Then("the phone number field should be enabled")
    public void the_phone_number_field_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isPhoneNumberFieldEnabled(), 
            "Phone number field should be enabled");
    }

    @Then("the phone number field should be disabled")
    public void the_phone_number_field_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isPhoneNumberFieldEnabled(), 
            "Phone number field should be disabled");
    }

    @Then("the country dropdown should be enabled")
    public void the_country_dropdown_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isCountryDropdownEnabled(), 
            "Country dropdown should be enabled");
    }

    @Then("the country dropdown should be disabled")
    public void the_country_dropdown_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isCountryDropdownEnabled(), 
            "Country dropdown should be disabled");
    }

    @Then("the state dropdown should be enabled")
    public void the_state_dropdown_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isStateDropdownEnabled(), 
            "State dropdown should be enabled");
    }

    @Then("the state dropdown should be disabled")
    public void the_state_dropdown_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isStateDropdownEnabled(), 
            "State dropdown should be disabled");
    }

    @Then("the terms checkbox should be enabled")
    public void the_terms_checkbox_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isTermsCheckboxEnabled(), 
            "Terms checkbox should be enabled");
    }

    @Then("the terms checkbox should be disabled")
    public void the_terms_checkbox_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isTermsCheckboxEnabled(), 
            "Terms checkbox should be disabled");
    }

    @Then("the newsletter checkbox should be enabled")
    public void the_newsletter_checkbox_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isNewsletterCheckboxEnabled(), 
            "Newsletter checkbox should be enabled");
    }

    @Then("the newsletter checkbox should be disabled")
    public void the_newsletter_checkbox_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isNewsletterCheckboxEnabled(), 
            "Newsletter checkbox should be disabled");
    }

    @Then("the reset button should be enabled")
    public void the_reset_button_should_be_enabled() {
        Assert.assertTrue(formsForestPage.isResetButtonEnabled(), 
            "Reset button should be enabled");
    }

    @Then("the reset button should be disabled")
    public void the_reset_button_should_be_disabled() {
        Assert.assertFalse(formsForestPage.isResetButtonEnabled(), 
            "Reset button should be disabled");
    }

    // Field accessibility and attribute validation steps

    @Then("the {string} field should have proper accessibility attributes")
    public void the_field_should_have_proper_accessibility_attributes(String fieldName) {
        Assert.assertTrue(formsForestPage.validateFieldAccessibility(fieldName), 
            "Field '" + fieldName + "' should have proper accessibility attributes");
    }

    @Then("the {string} field should have required attribute")
    public void the_field_should_have_required_attribute(String fieldName) {
        Assert.assertTrue(formsForestPage.validateFieldRequiredAttribute(fieldName), 
            "Field '" + fieldName + "' should have required attribute");
    }

    @Then("the {string} field should not have required attribute")
    public void the_field_should_not_have_required_attribute(String fieldName) {
        Assert.assertFalse(formsForestPage.validateFieldRequiredAttribute(fieldName), 
            "Field '" + fieldName + "' should not have required attribute");
    }

    // Comprehensive form validation steps

    @Then("the complete form state should be valid")
    public void the_complete_form_state_should_be_valid() {
        Assert.assertTrue(formsForestPage.validateCompleteFormState(), 
            "Complete form state should be valid");
    }

    @Then("the form should be interactive and functional")
    public void the_form_should_be_interactive_and_functional() {
        Assert.assertTrue(formsForestPage.validateCompleteFormState(), 
            "Form should be interactive and functional");
    }
}
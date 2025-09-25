package com.playbotlabs.pages;

import com.playbotlabs.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Page Object class for Forms Forest page
 * Handles form interactions, validations, and element state checking
 */
public class FormsForestPage extends BasePage {

    // Form container elements
    @FindBy(css = "form")
    private WebElement mainForm;

    @FindBy(css = ".card")
    private WebElement formContainer;

    // Common form input elements
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "phone")
    private WebElement phoneNumberField;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    // Text area elements
    @FindBy(name = "comments")
    private WebElement commentsTextArea;

    @FindBy(name = "description")
    private WebElement descriptionTextArea;

    @FindBy(name = "message")
    private WebElement messageTextArea;

    // Form action buttons
    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(css = "button[type='reset']")
    private WebElement resetButton;

    @FindBy(css = ".clear-button")
    private WebElement clearButton;

    // Validation and message elements
    @FindBy(css = ".success-message")
    private WebElement successMessage;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    @FindBy(css = ".validation-error")
    private List<WebElement> validationErrors;

    @FindBy(css = ".form-status")
    private WebElement formStatus;

    // Dropdown and select elements
    @FindBy(name = "country")
    private WebElement countryDropdown;

    @FindBy(name = "state")
    private WebElement stateDropdown;

    // Checkbox and radio elements
    @FindBy(name = "terms")
    private WebElement termsCheckbox;

    @FindBy(name = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(name = "notifications")
    private WebElement notificationsCheckbox;

    @FindBy(name = "marketing")
    private WebElement marketingCheckbox;

    @FindBy(name = "privacy")
    private WebElement privacyCheckbox;

    @FindBy(css = "input[name='interests']")
    private List<WebElement> interestsCheckboxes;

    @FindBy(css = "input[name='skills']")
    private List<WebElement> skillsCheckboxes;

    @FindBy(css = "input[name='gender']")
    private List<WebElement> genderRadioButtons;

    @FindBy(css = "input[name='experience']")
    private List<WebElement> experienceRadioButtons;

    @FindBy(css = "input[name='preference']")
    private List<WebElement> preferenceRadioButtons;

    /**
     * Navigate to the Forms Forest page
     */
    public void navigateToFormsForest() {
        navigateTo(ConfigManager.getFormsForestUrl());
    }

    /**
     * Fill username field
     * @param username the username to enter
     */
    public void fillUsername(String username) {
        sendKeys(usernameField, username);
    }

    /**
     * Fill password field
     * @param password the password to enter
     */
    public void fillPassword(String password) {
        sendKeys(passwordField, password);
    }

    /**
     * Fill email field
     * @param email the email to enter
     */
    public void fillEmail(String email) {
        sendKeys(emailField, email);
    }

    /**
     * Fill first name field
     * @param firstName the first name to enter
     */
    public void fillFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
    }

    /**
     * Fill last name field
     * @param lastName the last name to enter
     */
    public void fillLastName(String lastName) {
        sendKeys(lastNameField, lastName);
    }

    /**
     * Fill phone number field
     * @param phoneNumber the phone number to enter
     */
    public void fillPhoneNumber(String phoneNumber) {
        sendKeys(phoneNumberField, phoneNumber);
    }

    /**
     * Fill comments text area
     * @param comments the comments to enter
     */
    public void fillComments(String comments) {
        sendKeys(commentsTextArea, comments);
    }

    /**
     * Fill description text area
     * @param description the description to enter
     */
    public void fillDescription(String description) {
        sendKeys(descriptionTextArea, description);
    }

    /**
     * Fill message text area
     * @param message the message to enter
     */
    public void fillMessage(String message) {
        sendKeys(messageTextArea, message);
    }

    /**
     * Fill complete form with user data
     * @param username the username
     * @param password the password
     * @param email the email
     * @param firstName the first name
     * @param lastName the last name
     * @param phoneNumber the phone number
     */
    public void fillCompleteForm(String username, String password, String email, 
                                String firstName, String lastName, String phoneNumber) {
        if (username != null && !username.isEmpty()) fillUsername(username);
        if (password != null && !password.isEmpty()) fillPassword(password);
        if (email != null && !email.isEmpty()) fillEmail(email);
        if (firstName != null && !firstName.isEmpty()) fillFirstName(firstName);
        if (lastName != null && !lastName.isEmpty()) fillLastName(lastName);
        if (phoneNumber != null && !phoneNumber.isEmpty()) fillPhoneNumber(phoneNumber);
    }

    /**
     * Submit the form
     */
    public void submitForm() {
        click(submitButton);
    }

    /**
     * Reset the form
     */
    public void resetForm() {
        click(resetButton);
    }

    /**
     * Clear the form using clear button
     */
    public void clearForm() {
        click(clearButton);
    }

    /**
     * Select country from dropdown
     * @param country the country to select
     */
    public void selectCountry(String country) {
        selectByText(countryDropdown, country);
    }

    /**
     * Select state from dropdown
     * @param state the state to select
     */
    public void selectState(String state) {
        selectByText(stateDropdown, state);
    }

    /**
     * Check or uncheck terms checkbox
     * @param accept true to check, false to uncheck
     */
    public void setTermsAcceptance(boolean accept) {
        if (termsCheckbox.isSelected() != accept) {
            click(termsCheckbox);
        }
    }

    /**
     * Check or uncheck newsletter checkbox
     * @param subscribe true to check, false to uncheck
     */
    public void setNewsletterSubscription(boolean subscribe) {
        if (newsletterCheckbox.isSelected() != subscribe) {
            click(newsletterCheckbox);
        }
    }

    /**
     * Check or uncheck notifications checkbox
     * @param enable true to check, false to uncheck
     */
    public void setNotifications(boolean enable) {
        if (notificationsCheckbox.isSelected() != enable) {
            click(notificationsCheckbox);
        }
    }

    /**
     * Check or uncheck marketing checkbox
     * @param enable true to check, false to uncheck
     */
    public void setMarketing(boolean enable) {
        if (marketingCheckbox.isSelected() != enable) {
            click(marketingCheckbox);
        }
    }

    /**
     * Check or uncheck privacy checkbox
     * @param enable true to check, false to uncheck
     */
    public void setPrivacy(boolean enable) {
        if (privacyCheckbox.isSelected() != enable) {
            click(privacyCheckbox);
        }
    }

    /**
     * Select multiple interests checkboxes
     * @param interests list of interest values to select
     */
    public void selectInterests(List<String> interests) {
        for (String interest : interests) {
            for (WebElement checkbox : interestsCheckboxes) {
                if (checkbox.getAttribute("value").equals(interest)) {
                    if (!checkbox.isSelected()) {
                        click(checkbox);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Select multiple skills checkboxes
     * @param skills list of skill values to select
     */
    public void selectSkills(List<String> skills) {
        for (String skill : skills) {
            for (WebElement checkbox : skillsCheckboxes) {
                if (checkbox.getAttribute("value").equals(skill)) {
                    if (!checkbox.isSelected()) {
                        click(checkbox);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Clear all interests checkboxes
     */
    public void clearAllInterests() {
        for (WebElement checkbox : interestsCheckboxes) {
            if (checkbox.isSelected()) {
                click(checkbox);
            }
        }
    }

    /**
     * Clear all skills checkboxes
     */
    public void clearAllSkills() {
        for (WebElement checkbox : skillsCheckboxes) {
            if (checkbox.isSelected()) {
                click(checkbox);
            }
        }
    }

    /**
     * Select gender radio button
     * @param gender the gender value to select
     */
    public void selectGender(String gender) {
        for (WebElement radioButton : genderRadioButtons) {
            if (radioButton.getAttribute("value").equals(gender)) {
                click(radioButton);
                break;
            }
        }
    }

    /**
     * Select experience level radio button
     * @param experience the experience level value to select
     */
    public void selectExperience(String experience) {
        for (WebElement radioButton : experienceRadioButtons) {
            if (radioButton.getAttribute("value").equals(experience)) {
                click(radioButton);
                break;
            }
        }
    }

    /**
     * Select preference radio button
     * @param preference the preference value to select
     */
    public void selectPreference(String preference) {
        for (WebElement radioButton : preferenceRadioButtons) {
            if (radioButton.getAttribute("value").equals(preference)) {
                click(radioButton);
                break;
            }
        }
    }

    // Validation and state checking methods

    /**
     * Check if form is displayed
     * @return true if form is visible
     */
    public boolean isFormDisplayed() {
        return isElementDisplayed(mainForm);
    }

    /**
     * Check if form container is displayed
     * @return true if form container is visible
     */
    public boolean isFormContainerDisplayed() {
        return isElementDisplayed(formContainer);
    }

    /**
     * Check if username field is displayed
     * @return true if username field is visible
     */
    public boolean isUsernameFieldDisplayed() {
        return isElementDisplayed(usernameField);
    }

    /**
     * Check if password field is displayed
     * @return true if password field is visible
     */
    public boolean isPasswordFieldDisplayed() {
        return isElementDisplayed(passwordField);
    }

    /**
     * Check if email field is displayed
     * @return true if email field is visible
     */
    public boolean isEmailFieldDisplayed() {
        return isElementDisplayed(emailField);
    }

    /**
     * Check if comments text area is displayed
     * @return true if comments text area is visible
     */
    public boolean isCommentsTextAreaDisplayed() {
        return isElementDisplayed(commentsTextArea);
    }

    /**
     * Check if description text area is displayed
     * @return true if description text area is visible
     */
    public boolean isDescriptionTextAreaDisplayed() {
        return isElementDisplayed(descriptionTextArea);
    }

    /**
     * Check if message text area is displayed
     * @return true if message text area is visible
     */
    public boolean isMessageTextAreaDisplayed() {
        return isElementDisplayed(messageTextArea);
    }

    /**
     * Check if submit button is enabled
     * @return true if submit button is enabled
     */
    public boolean isSubmitButtonEnabled() {
        return submitButton.isEnabled();
    }

    /**
     * Check if submit button is displayed
     * @return true if submit button is visible
     */
    public boolean isSubmitButtonDisplayed() {
        return isElementDisplayed(submitButton);
    }

    /**
     * Check if reset button is displayed
     * @return true if reset button is visible
     */
    public boolean isResetButtonDisplayed() {
        return isElementDisplayed(resetButton);
    }

    /**
     * Check if success message is displayed
     * @return true if success message is visible
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    /**
     * Check if error message is displayed
     * @return true if error message is visible
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    /**
     * Get success message text
     * @return the success message text
     */
    public String getSuccessMessage() {
        return getText(successMessage);
    }

    /**
     * Get error message text
     * @return the error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Get form status text
     * @return the form status text
     */
    public String getFormStatus() {
        return getText(formStatus);
    }

    /**
     * Get all validation error messages
     * @return list of validation error messages
     */
    public List<String> getValidationErrors() {
        return validationErrors.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Check if form has validation errors
     * @return true if validation errors are present
     */
    public boolean hasValidationErrors() {
        return !validationErrors.isEmpty() && 
               validationErrors.stream().anyMatch(this::isElementDisplayed);
    }

    /**
     * Wait for form to be loaded
     * @return true if form is loaded successfully
     */
    public boolean waitForFormToLoad() {
        try {
            return isFormDisplayed() && isSubmitButtonDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for form submission to complete
     * @return true if submission completed (success or error message appears)
     */
    public boolean waitForFormSubmission() {
        try {
            // Wait for either success or error message to appear
            return isSuccessMessageDisplayed() || isErrorMessageDisplayed() || hasValidationErrors();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get username field value
     * @return current value in username field
     */
    public String getUsernameValue() {
        return usernameField.getAttribute("value");
    }

    /**
     * Get email field value
     * @return current value in email field
     */
    public String getEmailValue() {
        return emailField.getAttribute("value");
    }

    /**
     * Get comments text area value
     * @return current value in comments text area
     */
    public String getCommentsValue() {
        return commentsTextArea.getAttribute("value");
    }

    /**
     * Get description text area value
     * @return current value in description text area
     */
    public String getDescriptionValue() {
        return descriptionTextArea.getAttribute("value");
    }

    /**
     * Get message text area value
     * @return current value in message text area
     */
    public String getMessageValue() {
        return messageTextArea.getAttribute("value");
    }

    /**
     * Check if terms checkbox is selected
     * @return true if terms checkbox is checked
     */
    public boolean isTermsAccepted() {
        return termsCheckbox.isSelected();
    }

    /**
     * Check if newsletter checkbox is selected
     * @return true if newsletter checkbox is checked
     */
    public boolean isNewsletterSubscribed() {
        return newsletterCheckbox.isSelected();
    }

    /**
     * Get selected gender value
     * @return the selected gender value or null if none selected
     */
    public String getSelectedGender() {
        for (WebElement radioButton : genderRadioButtons) {
            if (radioButton.isSelected()) {
                return radioButton.getAttribute("value");
            }
        }
        return null;
    }

    /**
     * Get selected experience level value
     * @return the selected experience level value or null if none selected
     */
    public String getSelectedExperience() {
        for (WebElement radioButton : experienceRadioButtons) {
            if (radioButton.isSelected()) {
                return radioButton.getAttribute("value");
            }
        }
        return null;
    }

    /**
     * Get selected preference value
     * @return the selected preference value or null if none selected
     */
    public String getSelectedPreference() {
        for (WebElement radioButton : preferenceRadioButtons) {
            if (radioButton.isSelected()) {
                return radioButton.getAttribute("value");
            }
        }
        return null;
    }

    /**
     * Check if notifications checkbox is selected
     * @return true if notifications checkbox is checked
     */
    public boolean isNotificationsEnabled() {
        return notificationsCheckbox.isSelected();
    }

    /**
     * Check if marketing checkbox is selected
     * @return true if marketing checkbox is checked
     */
    public boolean isMarketingEnabled() {
        return marketingCheckbox.isSelected();
    }

    /**
     * Check if privacy checkbox is selected
     * @return true if privacy checkbox is checked
     */
    public boolean isPrivacyEnabled() {
        return privacyCheckbox.isSelected();
    }

    /**
     * Get selected interests
     * @return list of selected interest values
     */
    public List<String> getSelectedInterests() {
        return interestsCheckboxes.stream()
                .filter(WebElement::isSelected)
                .map(checkbox -> checkbox.getAttribute("value"))
                .collect(Collectors.toList());
    }

    /**
     * Get selected skills
     * @return list of selected skill values
     */
    public List<String> getSelectedSkills() {
        return skillsCheckboxes.stream()
                .filter(WebElement::isSelected)
                .map(checkbox -> checkbox.getAttribute("value"))
                .collect(Collectors.toList());
    }

    /**
     * Check if specific interest is selected
     * @param interest the interest value to check
     * @return true if the interest is selected
     */
    public boolean isInterestSelected(String interest) {
        return interestsCheckboxes.stream()
                .anyMatch(checkbox -> checkbox.getAttribute("value").equals(interest) && checkbox.isSelected());
    }

    /**
     * Check if specific skill is selected
     * @param skill the skill value to check
     * @return true if the skill is selected
     */
    public boolean isSkillSelected(String skill) {
        return skillsCheckboxes.stream()
                .anyMatch(checkbox -> checkbox.getAttribute("value").equals(skill) && checkbox.isSelected());
    }

    // ========== FORMS FOREST SPECIFIC ASSERTIONS AND VALIDATIONS ==========

    /**
     * Validate form submission success
     * Checks for success message display and proper form state after submission
     * @return true if form submission was successful
     */
    public boolean validateFormSubmissionSuccess() {
        try {
            // Wait for submission to complete
            if (!waitForFormSubmission()) {
                return false;
            }
            
            // Check for success message
            if (!isSuccessMessageDisplayed()) {
                return false;
            }
            
            // Validate success message content
            String successText = getSuccessMessage();
            return successText != null && 
                   (successText.toLowerCase().contains("success") || 
                    successText.toLowerCase().contains("submitted") ||
                    successText.toLowerCase().contains("complete"));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate form submission failure with error messages
     * Checks for error message display and validation error presence
     * @return true if form submission failed with proper error indication
     */
    public boolean validateFormSubmissionError() {
        try {
            // Wait for submission to complete
            if (!waitForFormSubmission()) {
                return false;
            }
            
            // Check for error indicators
            return isErrorMessageDisplayed() || hasValidationErrors();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate specific error message content
     * @param expectedErrorMessage the expected error message text
     * @return true if the expected error message is displayed
     */
    public boolean validateErrorMessage(String expectedErrorMessage) {
        try {
            if (!isErrorMessageDisplayed()) {
                return false;
            }
            
            String actualErrorMessage = getErrorMessage();
            return actualErrorMessage != null && 
                   actualErrorMessage.toLowerCase().contains(expectedErrorMessage.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate field-specific validation error
     * @param fieldName the name of the field to check for validation error
     * @param expectedMessage the expected validation message
     * @return true if the field has the expected validation error
     */
    public boolean validateFieldValidationError(String fieldName, String expectedMessage) {
        try {
            By fieldErrorLocator = By.cssSelector("[name='" + fieldName + "'] + .field-error, " +
                                                 "[name='" + fieldName + "'] ~ .field-error, " +
                                                 ".field-error[data-field='" + fieldName + "']");
            
            if (!isElementDisplayed(fieldErrorLocator)) {
                return false;
            }
            
            String actualMessage = getText(fieldErrorLocator);
            return actualMessage != null && 
                   actualMessage.toLowerCase().contains(expectedMessage.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate that all required fields show validation errors when empty
     * @return true if all required fields show appropriate validation errors
     */
    public boolean validateRequiredFieldErrors() {
        try {
            // Submit form without filling required fields
            submitForm();
            
            // Wait for validation to appear
            if (!waitForFormSubmission()) {
                return false;
            }
            
            // Check for validation errors
            return hasValidationErrors() || isErrorMessageDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate email field format validation
     * @param invalidEmail an invalid email format to test
     * @return true if email validation error is shown for invalid format
     */
    public boolean validateEmailFormatError(String invalidEmail) {
        try {
            fillEmail(invalidEmail);
            submitForm();
            
            return validateFieldValidationError("email", "email") || 
                   validateFieldValidationError("email", "invalid") ||
                   validateFieldValidationError("email", "format");
        } catch (Exception e) {
            return false;
        }
    }

    // ========== FORM FIELD STATE VALIDATION METHODS ==========

    /**
     * Check if username field is enabled
     * @return true if username field is enabled for input
     */
    public boolean isUsernameFieldEnabled() {
        try {
            return usernameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if password field is enabled
     * @return true if password field is enabled for input
     */
    public boolean isPasswordFieldEnabled() {
        try {
            return passwordField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if email field is enabled
     * @return true if email field is enabled for input
     */
    public boolean isEmailFieldEnabled() {
        try {
            return emailField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if first name field is enabled
     * @return true if first name field is enabled for input
     */
    public boolean isFirstNameFieldEnabled() {
        try {
            return firstNameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if last name field is enabled
     * @return true if last name field is enabled for input
     */
    public boolean isLastNameFieldEnabled() {
        try {
            return lastNameField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if phone number field is enabled
     * @return true if phone number field is enabled for input
     */
    public boolean isPhoneNumberFieldEnabled() {
        try {
            return phoneNumberField.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if comments text area is enabled
     * @return true if comments text area is enabled for input
     */
    public boolean isCommentsTextAreaEnabled() {
        try {
            return commentsTextArea.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if description text area is enabled
     * @return true if description text area is enabled for input
     */
    public boolean isDescriptionTextAreaEnabled() {
        try {
            return descriptionTextArea.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if message text area is enabled
     * @return true if message text area is enabled for input
     */
    public boolean isMessageTextAreaEnabled() {
        try {
            return messageTextArea.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if country dropdown is enabled
     * @return true if country dropdown is enabled for selection
     */
    public boolean isCountryDropdownEnabled() {
        try {
            return countryDropdown.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if state dropdown is enabled
     * @return true if state dropdown is enabled for selection
     */
    public boolean isStateDropdownEnabled() {
        try {
            return stateDropdown.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if terms checkbox is enabled
     * @return true if terms checkbox is enabled for interaction
     */
    public boolean isTermsCheckboxEnabled() {
        try {
            return termsCheckbox.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if newsletter checkbox is enabled
     * @return true if newsletter checkbox is enabled for interaction
     */
    public boolean isNewsletterCheckboxEnabled() {
        try {
            return newsletterCheckbox.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if reset button is enabled
     * @return true if reset button is enabled for clicking
     */
    public boolean isResetButtonEnabled() {
        try {
            return resetButton.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate that all form fields are in expected enabled/disabled state
     * @param shouldBeEnabled true if fields should be enabled, false if disabled
     * @return true if all fields are in the expected state
     */
    public boolean validateAllFieldsState(boolean shouldBeEnabled) {
        try {
            return isUsernameFieldEnabled() == shouldBeEnabled &&
                   isPasswordFieldEnabled() == shouldBeEnabled &&
                   isEmailFieldEnabled() == shouldBeEnabled &&
                   isFirstNameFieldEnabled() == shouldBeEnabled &&
                   isLastNameFieldEnabled() == shouldBeEnabled &&
                   isPhoneNumberFieldEnabled() == shouldBeEnabled &&
                   isSubmitButtonEnabled() == shouldBeEnabled;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate form field accessibility attributes
     * @param fieldName the name of the field to validate
     * @return true if field has proper accessibility attributes
     */
    public boolean validateFieldAccessibility(String fieldName) {
        try {
            By fieldLocator = By.name(fieldName);
            WebElement field = driver.findElement(fieldLocator);
            
            // Check for required accessibility attributes
            String ariaLabel = field.getAttribute("aria-label");
            String ariaDescribedBy = field.getAttribute("aria-describedby");
            String id = field.getAttribute("id");
            
            // Field should have either aria-label or associated label
            return (ariaLabel != null && !ariaLabel.isEmpty()) ||
                   (id != null && !id.isEmpty()) ||
                   (ariaDescribedBy != null && !ariaDescribedBy.isEmpty());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validate form field required attribute
     * @param fieldName the name of the field to validate
     * @return true if field has required attribute when expected
     */
    public boolean validateFieldRequiredAttribute(String fieldName) {
        try {
            By fieldLocator = By.name(fieldName);
            WebElement field = driver.findElement(fieldLocator);
            
            String required = field.getAttribute("required");
            return required != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Comprehensive form validation check
     * Validates form state, field states, and submission behavior
     * @return true if all form validations pass
     */
    public boolean validateCompleteFormState() {
        try {
            // Check form is displayed and interactive
            if (!isFormDisplayed() || !isSubmitButtonDisplayed()) {
                return false;
            }
            
            // Check all primary fields are enabled
            if (!isUsernameFieldEnabled() || !isEmailFieldEnabled() || !isSubmitButtonEnabled()) {
                return false;
            }
            
            // Check form can handle input
            String testValue = "test";
            fillUsername(testValue);
            if (!getUsernameValue().equals(testValue)) {
                return false;
            }
            
            // Clear the test input
            usernameField.clear();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
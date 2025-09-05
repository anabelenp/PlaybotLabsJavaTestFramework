package com.playbotlabs.pages;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

/**
 * Unit test class for Forms Forest page validation methods
 * Tests the validation method signatures and structure without requiring browser interaction
 */
public class FormsForestPageValidationUnitTest {

    @Test(description = "Test that all required validation methods exist")
    public void testValidationMethodsExist() {
        Class<FormsForestPage> pageClass = FormsForestPage.class;
        
        // Test form submission validation methods exist
        Assert.assertNotNull(getMethod(pageClass, "validateFormSubmissionSuccess"), 
            "validateFormSubmissionSuccess method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateFormSubmissionError"), 
            "validateFormSubmissionError method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateErrorMessage", String.class), 
            "validateErrorMessage method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateFieldValidationError", String.class, String.class), 
            "validateFieldValidationError method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateRequiredFieldErrors"), 
            "validateRequiredFieldErrors method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateEmailFormatError", String.class), 
            "validateEmailFormatError method should exist");
    }

    @Test(description = "Test that all field state validation methods exist")
    public void testFieldStateValidationMethodsExist() {
        Class<FormsForestPage> pageClass = FormsForestPage.class;
        
        // Test field enabled state methods exist
        Assert.assertNotNull(getMethod(pageClass, "isUsernameFieldEnabled"), 
            "isUsernameFieldEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isPasswordFieldEnabled"), 
            "isPasswordFieldEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isEmailFieldEnabled"), 
            "isEmailFieldEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isFirstNameFieldEnabled"), 
            "isFirstNameFieldEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isLastNameFieldEnabled"), 
            "isLastNameFieldEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isPhoneNumberFieldEnabled"), 
            "isPhoneNumberFieldEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isCountryDropdownEnabled"), 
            "isCountryDropdownEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isStateDropdownEnabled"), 
            "isStateDropdownEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isTermsCheckboxEnabled"), 
            "isTermsCheckboxEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isNewsletterCheckboxEnabled"), 
            "isNewsletterCheckboxEnabled method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isResetButtonEnabled"), 
            "isResetButtonEnabled method should exist");
    }

    @Test(description = "Test that comprehensive validation methods exist")
    public void testComprehensiveValidationMethodsExist() {
        Class<FormsForestPage> pageClass = FormsForestPage.class;
        
        // Test comprehensive validation methods exist
        Assert.assertNotNull(getMethod(pageClass, "validateAllFieldsState", boolean.class), 
            "validateAllFieldsState method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateFieldAccessibility", String.class), 
            "validateFieldAccessibility method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateFieldRequiredAttribute", String.class), 
            "validateFieldRequiredAttribute method should exist");
        Assert.assertNotNull(getMethod(pageClass, "validateCompleteFormState"), 
            "validateCompleteFormState method should exist");
    }

    @Test(description = "Test that validation methods return boolean")
    public void testValidationMethodReturnTypes() {
        Class<FormsForestPage> pageClass = FormsForestPage.class;
        
        // Test that validation methods return boolean
        Method validateFormSubmissionSuccess = getMethod(pageClass, "validateFormSubmissionSuccess");
        Assert.assertEquals(validateFormSubmissionSuccess.getReturnType(), boolean.class, 
            "validateFormSubmissionSuccess should return boolean");
        
        Method validateFormSubmissionError = getMethod(pageClass, "validateFormSubmissionError");
        Assert.assertEquals(validateFormSubmissionError.getReturnType(), boolean.class, 
            "validateFormSubmissionError should return boolean");
        
        Method isUsernameFieldEnabled = getMethod(pageClass, "isUsernameFieldEnabled");
        Assert.assertEquals(isUsernameFieldEnabled.getReturnType(), boolean.class, 
            "isUsernameFieldEnabled should return boolean");
        
        Method validateCompleteFormState = getMethod(pageClass, "validateCompleteFormState");
        Assert.assertEquals(validateCompleteFormState.getReturnType(), boolean.class, 
            "validateCompleteFormState should return boolean");
    }

    @Test(description = "Test that existing validation methods still exist")
    public void testExistingValidationMethodsStillExist() {
        Class<FormsForestPage> pageClass = FormsForestPage.class;
        
        // Test that existing methods are still available
        Assert.assertNotNull(getMethod(pageClass, "isFormDisplayed"), 
            "isFormDisplayed method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isSuccessMessageDisplayed"), 
            "isSuccessMessageDisplayed method should exist");
        Assert.assertNotNull(getMethod(pageClass, "isErrorMessageDisplayed"), 
            "isErrorMessageDisplayed method should exist");
        Assert.assertNotNull(getMethod(pageClass, "hasValidationErrors"), 
            "hasValidationErrors method should exist");
        Assert.assertNotNull(getMethod(pageClass, "waitForFormSubmission"), 
            "waitForFormSubmission method should exist");
        Assert.assertNotNull(getMethod(pageClass, "getSuccessMessage"), 
            "getSuccessMessage method should exist");
        Assert.assertNotNull(getMethod(pageClass, "getErrorMessage"), 
            "getErrorMessage method should exist");
        Assert.assertNotNull(getMethod(pageClass, "getValidationErrors"), 
            "getValidationErrors method should exist");
    }

    /**
     * Helper method to get a method by name and parameter types
     * @param clazz the class to search in
     * @param methodName the method name
     * @param parameterTypes the parameter types
     * @return the method or null if not found
     */
    private Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
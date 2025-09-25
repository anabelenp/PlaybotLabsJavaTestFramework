@forms-forest
Feature: Forms Forest Automation
  As a user
  I want to interact with various forms on the Forms Forest page
  So that I can test form functionality and validation

  Background:
    Given I navigate to the Forms Forest page
    And I should see the form is displayed

  @smoke @basic-forms
  Scenario: Basic form display validation
    Then I should see the form container is displayed
    And I should see all form fields are displayed
    And I should see the submit button is displayed
   # And I should see the reset button is displayed
    And I should see the submit button is enabled

  @smoke @basic-forms @wip
  Scenario: Simple form submission with valid data
    When I fill the username field with "testuser"
    And I fill the email field with "test@example.com"
    And I fill the password field with "password123"
    And I fill the phone number field with "(415) 333-5678"
    And I submit the form
    Then I should see a success message

  @smoke @basic-forms
  Scenario: Form submission with minimal required fields
    When I fill the form with username "user123" and password "pass456"
    And I submit the form
    Then the form submission should complete

  @regression
  Scenario: Complete form submission with all fields
    When I fill the complete form with:
      | username    | johndoe        |
      | password    | securepass123  |
      | email       | john@email.com |
      | firstName   | John           |
      | lastName    | Doe            |
      | phoneNumber | 555-0123       |
    And I accept the terms and conditions
    And I subscribe to the newsletter
    And I submit the form
    Then I should see a success message
    And the terms checkbox should be checked
    And the newsletter checkbox should be checked

  @regression
  Scenario: Form validation with empty required fields
    When I submit the form
    Then I should see validation errors
    And I should not see a success message

  @regression
  Scenario: Form validation with invalid email format
    When I fill the username field with "testuser"
    And I fill the password field with "password123"
    And I fill the email field with "invalid-email"
    And I submit the form
    Then I should see validation error containing "email"

  @regression
  Scenario: Form reset functionality
    When I fill the username field with "testuser"
    And I fill the password field with "password123"
    And I fill the email field with "test@example.com"
    And I reset the form
    Then the username field should contain ""
    And the email field should contain ""

  @regression
  Scenario: Form clear functionality
    When I fill the username field with "testuser"
    And I fill the email field with "test@example.com"
    And I accept the terms and conditions
    And I clear the form
    Then the username field should contain ""
    And the email field should contain ""
    And the terms checkbox should not be checked

  @regression @advanced-controls
  Scenario: Dropdown selection functionality
    When I select "United States" from the country dropdown
    And I select "California" from the state dropdown
    And I fill the username field with "testuser"
    And I fill the password field with "password123"
    And I submit the form
    Then the form submission should complete

  @basic-forms
  Scenario: Gender selection functionality
    When I select "female" as gender
    And I fill the username field with "testuser"
    And I fill the password field with "password123"
    And I submit the form
    Then the selected gender should be "male"
    And the form submission should complete

  @regression
  Scenario: Terms and conditions validation
    When I fill the username field with "testuser"
    And I fill the password field with "password123"
    And I do not accept the terms and conditions
    And I submit the form
    Then I should see validation error containing "terms"

  @regression
  Scenario: Newsletter subscription options
    When I fill the username field with "testuser"
    And I fill the password field with "password123"
    And I subscribe to the newsletter
    And I accept the terms and conditions
    And I submit the form
    Then the newsletter checkbox should be checked
    And I should see a success message

  @regression
  Scenario: Form submission with special characters
    When I fill the username field with "user@#$%"
    And I fill the password field with "P@ssw0rd!"
    And I fill the email field with "special+test@example.com"
    And I fill the first name field with "José"
    And I fill the last name field with "O'Connor"
    And I accept the terms and conditions
    And I submit the form
    Then the form submission should complete

  @smoke @regression
  Scenario Outline: Data-driven form testing with valid inputs
    When I fill the username field with "<username>"
    And I fill the password field with "<password>"
    And I fill the email field with "<email>"
    And I fill the first name field with "<firstName>"
    And I fill the last name field with "<lastName>"
    And I accept the terms and conditions
    And I submit the form
    Then I should see a success message

    Examples:
      | username | password    | email              | firstName | lastName |
      | user1    | pass123     | user1@test.com     | Alice     | Smith    |
      | user2    | secure456   | user2@example.org  | Bob       | Johnson  |
      | user3    | mypass789   | user3@domain.net   | Carol     | Williams |
      | testuser | password1   | test@company.com   | David     | Brown    |

  @regression
  Scenario Outline: Data-driven form testing with invalid inputs
    When I fill the username field with "<username>"
    And I fill the password field with "<password>"
    And I fill the email field with "<email>"
    And I submit the form
    Then I should see validation errors
    And I should not see a success message

    Examples:
      | username | password | email           |
      |          | pass123  | user@test.com   |
      | user1    |          | user@test.com   |
      | user2    | pass456  |                 |
      | user3    | pass789  | invalid-email   |
      |          |          |                 |

  @regression
  Scenario: Form field validation with boundary values
    When I fill the username field with "a"
    And I fill the password field with "12"
    And I fill the email field with "a@b.c"
    And I submit the form
    Then I should see validation errors

  @regression
  Scenario: Form field validation with maximum length values
    When I fill the username field with "verylongusernamethatexceedsmaximumlengthallowed"
    And I fill the password field with "verylongpasswordthatexceedsmaximumlengthallowed"
    And I fill the email field with "verylongemailaddressthatexceedsmaximumlength@example.com"
    And I submit the form
    Then I should see validation errors

  @smoke
  Scenario: Test data driven form submission with valid data
    When I fill the form with valid test data
    And I accept the terms and conditions
    And I submit the form
    Then I should see a success message

  @regression
  Scenario: Test data driven form submission with invalid data
    When I fill the form with invalid test data
    And I submit the form
    Then I should see validation errors
    And I should not see a success message

  @regression
  Scenario: Multiple validation errors display
    When I fill the username field with ""
    And I fill the password field with ""
    And I fill the email field with "invalid"
    And I submit the form
    Then I should see 3 validation error(s)
    And I should see validation error containing "username"
    And I should see validation error containing "password"
    And I should see validation error containing "email"

  @regression
  Scenario: Form state persistence after validation error
    When I fill the username field with "persistuser"
    And I fill the email field with "invalid-email"
    And I submit the form
    Then I should see validation errors
    And the username field should contain "persistuser"
    And the email field should contain "invalid-email"

  @regression
  Scenario: Successful form submission with custom success message
    When I fill the username field with "successuser"
    And I fill the password field with "successpass"
    And I fill the email field with "success@test.com"
    And I accept the terms and conditions
    And I submit the form
    Then I should see the success message "Form submitted successfully!"

  @regression
  Scenario: Form error handling with custom error message
    When I fill the username field with "erroruser"
    And I fill the password field with "wrongpass"
    And I submit the form
    Then I should see the error message "Invalid credentials provided"

  @regression
  Scenario: Form status validation during submission
    When I fill the username field with "statususer"
    And I fill the password field with "statuspass"
    And I fill the email field with "status@test.com"
    And I accept the terms and conditions
    And I submit the form
    Then I should see the form status "submitted"

  @regression @file-upload
  Scenario: Single file upload functionality
    When I fill the username field with "fileuser"
    And I fill the password field with "filepass"
    And I upload a file "test-document.pdf"
    And I submit the form
    Then the file upload should be successful
    And I should see a success message

  @regression @file-upload
  Scenario: Multiple file upload functionality
    When I fill the username field with "multifileuser"
    And I fill the password field with "multifilepass"
    And I upload multiple files "document1.pdf,image1.jpg,spreadsheet1.xlsx"
    And I submit the form
    Then all files should be uploaded successfully
    And I should see a success message

  @regression @multi-step
  Scenario: Multi-step form navigation
    When I fill the username field with "stepuser"
    And I fill the password field with "steppass"
    And I proceed to the next step
    Then I should be on step 2
    When I fill additional information
    And I proceed to the next step
    Then I should be on step 3
    When I review and submit the form
    Then I should see a success message

  @regression @multi-step
  Scenario: Multi-step form data persistence
    When I fill the username field with "persistuser"
    And I fill the password field with "persistpass"
    And I proceed to the next step
    And I go back to the previous step
    Then the username field should contain "persistuser"
    And the password field should contain "persistpass"
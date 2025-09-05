@forms-forest @basic-forms
Feature: Basic Form Elements Testing
  As a user
  I want to interact with basic form elements
  So that I can test input fields, text areas, checkboxes, and radio buttons

  Background:
    Given I navigate to the Forms Forest page
    And I should see the form is displayed

  # Input Field Tests (text, password, email)
  
  @basic-forms @smoke
  Scenario: Text input field functionality
    When I fill the username field with "testuser123"
    Then the username field should contain "testuser123"
    And the username field should be enabled

  @basic-forms @smoke
  Scenario: Password input field functionality
    When I fill the password field with "SecurePass123!"
    Then the password field should be enabled
    And I submit the form
    Then I should see validation errors

  @basic-forms @smoke
  Scenario: Email input field functionality
    When I fill the email field with "test@example.com"
    Then the email field should contain "test@example.com"
    And the email field should be enabled

  @basic-forms
  Scenario: Input field validation with empty values
    When I fill the username field with ""
    And I fill the password field with ""
    And I fill the email field with ""
    And I submit the form
    Then I should see validation errors
    And I should see validation error containing "username"
    And I should see validation error containing "password"
    And I should see validation error containing "email"

  @basic-forms
  Scenario: Input field validation with invalid email format
    When I fill the username field with "validuser"
    And I fill the password field with "ValidPass123!"
    And I fill the email field with "invalid-email-format"
    And I submit the form
    Then I should see validation error containing "email"

  @basic-forms
  Scenario: Input field validation with special characters
    When I fill the username field with "user@#$%"
    And I fill the password field with "P@ssw0rd!"
    And I fill the email field with "special+test@example.com"
    And I submit the form
    Then the form submission should complete

  @basic-forms
  Scenario: Input field validation with whitespace
    When I fill the username field with "   user   "
    And I fill the password field with "   Pass123!   "
    And I fill the email field with "   user@example.com   "
    And I submit the form
    Then the form submission should complete

  @basic-forms
  Scenario: Input field validation with maximum length
    When I fill the username field with "verylongusernamethatexceedsmaximumlengthallowed"
    And I fill the password field with "verylongpasswordthatexceedsmaximumlengthallowed"
    And I fill the email field with "verylongemailaddressthatexceedsmaximumlength@example.com"
    And I submit the form
    Then I should see validation errors

  # Text Area Tests

  @basic-forms @smoke
  Scenario: Comments text area functionality
    When I fill the comments field with "This is a test comment for the form"
    Then the comments field should contain "This is a test comment for the form"

  @basic-forms
  Scenario: Description text area functionality
    When I fill the description field with "This is a detailed description of the user's requirements and expectations"
    Then the description field should contain "This is a detailed description of the user's requirements and expectations"

  @basic-forms
  Scenario: Message text area functionality
    When I fill the message field with "Hello, this is a test message with multiple lines.\nSecond line of the message.\nThird line with special characters: @#$%^&*()"
    Then the message field should contain "Hello, this is a test message with multiple lines.\nSecond line of the message.\nThird line with special characters: @#$%^&*()"

  @basic-forms
  Scenario: Text area with long content
    When I fill the comments field with "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    And I submit the form
    Then the form submission should complete

  @basic-forms
  Scenario: Text area with empty content
    When I fill the comments field with ""
    And I fill the description field with ""
    And I fill the message field with ""
    And I submit the form
    Then the form submission should complete

  # Single Checkbox Tests

  @basic-forms @smoke
  Scenario: Terms and conditions checkbox functionality
    When I accept the terms and conditions
    Then the terms checkbox should be checked
    When I do not accept the terms and conditions
    Then the terms checkbox should not be checked

  @basic-forms @smoke
  Scenario: Newsletter subscription checkbox functionality
    When I subscribe to the newsletter
    Then the newsletter checkbox should be checked
    When I do not subscribe to the newsletter
    Then the newsletter checkbox should not be checked

  @basic-forms
  Scenario: Notifications checkbox functionality
    When I enable notifications
    Then the notifications checkbox should be checked
    When I disable notifications
    Then the notifications checkbox should not be checked

  @basic-forms
  Scenario: Marketing checkbox functionality
    When I enable marketing
    Then the marketing checkbox should be checked
    When I disable marketing
    Then the marketing checkbox should not be checked

  @basic-forms
  Scenario: Privacy policy checkbox functionality
    When I accept privacy policy
    Then the privacy checkbox should be checked
    When I do not accept privacy policy
    Then the privacy checkbox should not be checked

  # Multiple Checkbox Tests

  @basic-forms @smoke
  Scenario: Multiple interests selection
    When I select interests: "sports, music, technology"
    Then the interest "sports" should be selected
    And the interest "music" should be selected
    And the interest "technology" should be selected
    And I should have 3 interests selected

  @basic-forms
  Scenario: Multiple skills selection
    When I select skills: "java, python, javascript"
    Then the skill "java" should be selected
    And the skill "python" should be selected
    And the skill "javascript" should be selected
    And I should have 3 skills selected

  @basic-forms
  Scenario: Clear all interests
    When I select interests: "sports, music, technology, reading"
    And I should have 4 interests selected
    When I clear all interests
    Then I should have 0 interests selected

  @basic-forms
  Scenario: Clear all skills
    When I select skills: "java, python, javascript, html, css"
    And I should have 5 skills selected
    When I clear all skills
    Then I should have 0 skills selected

  @basic-forms
  Scenario: Mixed checkbox selections
    When I accept the terms and conditions
    And I subscribe to the newsletter
    And I enable notifications
    And I disable marketing
    And I accept privacy policy
    Then the terms checkbox should be checked
    And the newsletter checkbox should be checked
    And the notifications checkbox should be checked
    And the marketing checkbox should not be checked
    And the privacy checkbox should be checked

  # Radio Button Tests

  @basic-forms @smoke
  Scenario: Gender radio button selection - Male
    When I select "male" as gender
    Then the selected gender should be "male"

  @basic-forms @smoke
  Scenario: Gender radio button selection - Female
    When I select "female" as gender
    Then the selected gender should be "female"

  @basic-forms
  Scenario: Gender radio button selection - Other
    When I select "other" as gender
    Then the selected gender should be "other"

  @basic-forms
  Scenario: Experience level radio button selection
    When I select "beginner" as experience level
    Then the selected experience level should be "beginner"
    When I select "intermediate" as experience level
    Then the selected experience level should be "intermediate"
    When I select "advanced" as experience level
    Then the selected experience level should be "advanced"

  @basic-forms
  Scenario: Preference radio button selection
    When I select "email" as preference
    Then the selected preference should be "email"
    When I select "phone" as preference
    Then the selected preference should be "phone"
    When I select "sms" as preference
    Then the selected preference should be "sms"

  @basic-forms
  Scenario: Radio button mutual exclusivity
    When I select "male" as gender
    Then the selected gender should be "male"
    When I select "female" as gender
    Then the selected gender should be "female"
    # Only female should be selected now, not male

  # Basic Form Validation Tests

  @basic-forms @smoke
  Scenario: Complete basic form submission with valid data
    When I fill the username field with "testuser"
    And I fill the password field with "TestPass123!"
    And I fill the email field with "test@example.com"
    And I fill the comments field with "This is a test comment"
    And I select "male" as gender
    And I accept the terms and conditions
    And I submit the form
    Then I should see a success message

  @basic-forms
  Scenario: Form validation with missing required fields
    When I fill the username field with "testuser"
    And I fill the comments field with "This is a test comment"
    And I select "female" as gender
    And I submit the form
    Then I should see validation errors

  @basic-forms
  Scenario: Form validation with terms not accepted
    When I fill the username field with "testuser"
    And I fill the password field with "TestPass123!"
    And I fill the email field with "test@example.com"
    And I do not accept the terms and conditions
    And I submit the form
    Then I should see validation error containing "terms"

  @basic-forms
  Scenario: Form validation with all basic elements filled
    When I fill the username field with "completeuser"
    And I fill the password field with "CompletePass123!"
    And I fill the email field with "complete@example.com"
    And I fill the comments field with "Complete test with all basic elements"
    And I fill the description field with "Detailed description for testing"
    And I fill the message field with "Test message for validation"
    And I select "other" as gender
    And I select "advanced" as experience level
    And I select "email" as preference
    And I select interests: "technology, reading"
    And I select skills: "java, testing"
    And I accept the terms and conditions
    And I subscribe to the newsletter
    And I enable notifications
    And I accept privacy policy
    And I submit the form
    Then I should see a success message

  # Form Reset and Clear Functionality Tests

  @basic-forms @smoke
  Scenario: Form reset functionality with input fields
    When I fill the username field with "resetuser"
    And I fill the password field with "ResetPass123!"
    And I fill the email field with "reset@example.com"
    And I reset the form
    Then the username field should contain ""
    And the email field should contain ""

  @basic-forms
  Scenario: Form reset functionality with text areas
    When I fill the comments field with "Reset test comment"
    And I fill the description field with "Reset test description"
    And I fill the message field with "Reset test message"
    And I reset the form
    Then the comments field should contain ""
    And the description field should contain ""
    And the message field should contain ""

  @basic-forms
  Scenario: Form reset functionality with checkboxes
    When I accept the terms and conditions
    And I subscribe to the newsletter
    And I enable notifications
    And I enable marketing
    And I reset the form
    Then the terms checkbox should not be checked
    And the newsletter checkbox should not be checked
    And the notifications checkbox should not be checked
    And the marketing checkbox should not be checked

  @basic-forms
  Scenario: Form clear functionality with all elements
    When I fill the username field with "clearuser"
    And I fill the email field with "clear@example.com"
    And I fill the comments field with "Clear test comment"
    And I select "male" as gender
    And I accept the terms and conditions
    And I select interests: "sports, music"
    And I clear the form
    Then the username field should contain ""
    And the email field should contain ""
    And the comments field should contain ""
    And the terms checkbox should not be checked
    And I should have 0 interests selected

  @basic-forms
  Scenario: Form state persistence after validation error
    When I fill the username field with "persistuser"
    And I fill the comments field with "Persist test comment"
    And I select "female" as gender
    And I accept the terms and conditions
    And I fill the email field with "invalid-email"
    And I submit the form
    Then I should see validation errors
    And the username field should contain "persistuser"
    And the comments field should contain "Persist test comment"
    And the selected gender should be "female"
    And the terms checkbox should be checked

  @basic-forms
  Scenario: Form accessibility validation
    Then the "username" field should have proper accessibility attributes
    And the "password" field should have proper accessibility attributes
    And the "email" field should have proper accessibility attributes
    And the "username" field should have required attribute
    And the "password" field should have required attribute
    And the "email" field should have required attribute

  @basic-forms
  Scenario: Form field state validation
    Then all form fields should be enabled
    And the username field should be enabled
    And the password field should be enabled
    And the email field should be enabled
    And the terms checkbox should be enabled
    And the newsletter checkbox should be enabled
    And the reset button should be enabled

  @basic-forms @smoke
  Scenario Outline: Data-driven basic form testing
    When I fill the username field with "<username>"
    And I fill the password field with "<password>"
    And I fill the email field with "<email>"
    And I fill the comments field with "<comments>"
    And I select "<gender>" as gender
    And I accept the terms and conditions
    And I submit the form
    Then I should see a success message

    Examples:
      | username    | password      | email                | comments                    | gender |
      | user1       | Pass123!      | user1@test.com       | First test comment          | male   |
      | user2       | SecurePass1!  | user2@example.org    | Second test comment         | female |
      | user3       | MyPass789!    | user3@domain.net     | Third test comment          | other  |
      | testuser    | TestPass1!    | test@company.com     | Final test comment          | male   |

  @basic-forms
  Scenario Outline: Data-driven validation testing
    When I fill the username field with "<username>"
    And I fill the password field with "<password>"
    And I fill the email field with "<email>"
    And I submit the form
    Then I should see validation errors

    Examples:
      | username | password     | email           |
      |          | Pass123!     | user@test.com   |
      | user1    |              | user@test.com   |
      | user2    | Pass456!     |                 |
      | user3    | Pass789!     | invalid-email   |
      |          |              |                 |
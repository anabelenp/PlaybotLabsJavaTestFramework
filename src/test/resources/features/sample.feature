@smoke
Feature: Sample Feature
  As a user
  I want to test the sample functionality
  So that I can verify the application works correctly

  @regression
  Scenario: Sample test scenario
    Given I navigate to the sample page
    When I perform a sample action
    Then I should see the expected result

  @smoke @regression
  Scenario Outline: Sample parameterized test
    Given I navigate to the sample page
    When I perform a sample action with "<parameter>"
    Then I should see the result "<expected_result>"

    Examples:
      | parameter | expected_result |
      | value1    | result1        |
      | value2    | result2        |
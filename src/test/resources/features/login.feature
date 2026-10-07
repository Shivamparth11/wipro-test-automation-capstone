Feature: Login to SauceDemo application
  As a registered user
  I want to log in to the application
  So that I can view and purchase products

  Background:
    Given the user is on the login page

  @smoke
  Scenario: Successful login with valid credentials
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the inventory page should be displayed
    And at least 1 product should be listed

  @regression
  Scenario Outline: Unsuccessful login with invalid credentials
    When the user logs in with username "<username>" and password "<password>"
    Then an error message should be displayed

    Examples:
      | username        | password       |
      | locked_out_user | secret_sauce   |
      | standard_user   | wrong_password |
      |                 |                |

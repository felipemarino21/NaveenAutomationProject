Feature: User account Functions

  Test cases for user accounts as login and register.

  Background:
    Given User is on the login page

  Scenario: Incorrect  login
    When User logs in with username "1012233567" and password "zapatos123"
    Then User sees error message

  Scenario: Correct homepage login
    When User logs in with username "test1234@gmail.com" and password "zapatos123"
    Then User sees account page

  Scenario Outline: User sign up with new user
    And User clicks continue in the registration menu
    When Signs up with new "<firstname>" and "<lastname>" random "<email>"  "<phone>" "<password>"
    Then User sees registration confirmation
    Examples:
      | firstname | lastname | email          | phone   | password   |
      | luis      | orlando    | asd@gmail.cos | 31063122 | zapatos123 |

  Scenario Outline: User sign up with already existing user
    And User clicks continue in the registration menu
    When User signs up with "<firstname>" "<lastname>" "<email>" "<phone>" "<password>"
    Then User sees registration error
    Examples:
      | firstname | lastname | email          | phone   | password   |
      | jhon      | ortiz    | jhon@gmail.cos | 3106122 | zapatos123 |


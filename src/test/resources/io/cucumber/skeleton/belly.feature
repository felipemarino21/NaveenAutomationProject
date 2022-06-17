Feature: ECommerce Functions


  Scenario: Incorrect homepage login
    Given User is on the login page
    When User logs in with username "1012233567" and password "zapatos123"
    Then User sees error message

  Scenario: Correct homepage login
    Given User is on the login page
    When User logs in with username "test1234@gmail.com" and password "zapatos123"
    Then User sees account page

  Scenario Outline: User sign up with new user
    Given User is on the login page
    And User clicks continue in the registration menu
    When Signs up with new <firstname> and <lastname> random <email>  <phone> <password>
    Then User sees registration confirmation
    Examples:
      | firstname | lastname | email          | phone   | password   |
      | luis      | orlando    | asd@gmail.cos | 31063122 | zapatos123 |

  Scenario Outline: User sign up with already existing user
    Given User is on the login page
    And User clicks continue in the registration menu
    When User signs up with <firstname> and <lastname> <email> <phone> <password>
    Then User sees registration error
    Examples:
      | firstname | lastname | email          | phone   | password   |
      | jhon      | ortiz    | jhon@gmail.cos | 3106122 | zapatos123 |

  Scenario: Search on homepage
    Given User is on the homepage
    When User types in the searchbar the word "apple"
    And Clicks the search button
    Then User sees all the results containing "Apple" in the name

  Scenario: Hover on categories shows elements in the homepage
    Given User is on the homepage
    When User hovers with the mouse over the MP3 players category
    Then User sees all the elements in that category

  Scenario: Element added to cart is present in the cart
    Given User is on the homepage
    When User adds a Macbook to cart
    And User clicks on the cart
    Then User sees the Macbook in the cart

  Scenario: Add to cart notification shows after adding element to cart
    Given User is on the homepage
    When User adds a Macbook to cart
    Then User sees the add to cart notification

  Scenario: Item is added multiple times to cart
    Given User is on the homepage
    When User adds "3" Macbook to cart
    And User clicks on the cart
    And User clicks on view cart
    Then User sees "3" MacBook  in the cart

  Scenario: Change in currency
    Given User is on the homepage
    When User changes currency from dollars to euros
    Then User sees all the prices in euros








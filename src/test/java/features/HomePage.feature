Feature: ECommerce Functions

  Test cases for the different homepage features as search,cart and currency converter.

  Background:
    Given User is on the homepage

  Scenario: Search on homepage
    When User types in the searchbar the word "apple"
    And Clicks the search button
    Then User sees all the results containing "Apple" in the name

  Scenario: Hover on categories shows elements in the homepage
    When User hovers with the mouse over the MP3 players category
    Then User sees all the elements in that category

  Scenario: Element added to cart is present in the cart
    When User adds a Macbook to cart
    And User clicks on the cart
    Then User sees the Macbook in the cart

  Scenario: Add to cart notification shows after adding element to cart
    When User adds a Macbook to cart
    Then User sees the add to cart notification

  Scenario: Item is added multiple times to cart
    When User adds "3" Macbook to cart
    And User clicks on the cart
    And User clicks on view cart
    Then User sees "3" MacBook  in the cart

  Scenario: Change in currency
    When User changes currency from dollars to euros
    Then User sees all the prices in euros
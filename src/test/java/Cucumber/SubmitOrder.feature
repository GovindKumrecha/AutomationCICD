@tag
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file

Background:
    Given I landed on Ecommerce page 

  @submitOrderOnly
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add the product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                    |	password | productName |
      | thakorgopu725@gmail.com | Thakor1! | ZARA COAT 3 |
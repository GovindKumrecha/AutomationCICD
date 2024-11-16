
@tag
Feature: Error Validation
  I want to use this template for my feature file



  @tag2
  Scenario Outline: Validate the user credentials
    Given I landed on Ecommerce page 
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on landingPage
   

    Examples: 
      | name                    |	password  |
      | thakorgopu725@gmail.com | Thakor1!! |
 
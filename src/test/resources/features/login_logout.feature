Feature: Authentication on The Internet app

  Scenario: Login and logout with valid credentials
    Given I open the login page
    When I login with username "tomsmith" and password "SuperSecretPassword!"
    Then I should see success message containing "You logged into a secure area"
    When I logout
    Then I should be redirected to the login page


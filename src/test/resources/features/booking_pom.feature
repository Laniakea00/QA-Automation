Feature: Checkout booking flow (POM)

  Scenario: Successful checkout with valid customer info
    Given I am logged in as a standard user
    When I add product "Sauce Labs Backpack" to the cart
    And I proceed to checkout
    And I enter customer info "Alan" "Berikzhan" "010000"
    And I finish checkout
    Then I should see order confirmation message "Thank you for your order!"

  Scenario: Checkout fails when first name is missing
    Given I am logged in as a standard user
    When I add product "Sauce Labs Backpack" to the cart
    And I proceed to checkout
    And I enter customer info "" "Test" "010000"
    And I continue checkout
    Then I should see checkout error containing "First Name is required"

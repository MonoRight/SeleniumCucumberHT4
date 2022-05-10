Feature: MainFunctionality
  As a User
  I want to test main functions of the site
  So that I can be sure that site works correctly

  Scenario Outline: Check add product to cart
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyWord>'
    And User clicks on the 'Search' button
    And User scrolls to <index> product
    And User clicks <index> product
    And User clicks 'Add to Bag' button
    And User moves to cart button
    And User clicks 'View Bag' button
    Then User checks that product is on the bag page
    Examples:
      | homePage                  | index | keyWord |
      | https://www.asos.com/men/ | 0     | Glasses |

  Scenario Outline: Check count of products in cart
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyWord>'
    And User clicks on the 'Search' button
    And User scrolls to <index> product
    And User clicks <index> product
    And User clicks 'Add to Bag' button
    Then User checks that count of products in cart matches the number of added products
    Examples:
      | homePage                  | index | keyWord |
      | https://www.asos.com/men/ | 0     | Glasses |

  Scenario Outline: Check invalid search request
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyWord>'
    And User clicks on the 'Search' button
    Then User checks that search result is empty
    Examples:
      | homePage                  | keyWord    |
      | https://www.asos.com/men/ | !@#$%^&*() |

  Scenario Outline: Check Sign In with invalid login and password
    Given User opens '<homePage>' page
    And User moves to 'Sign In' button
    And User clicks 'Sign In' button
    When User enter invalid '<login>' and '<password>'
    And User clicks 'Sign In' button on 'Sign in' page
    Then User checks error message upper login input
    Examples:
      | homePage                  | login      | password
      | https://www.asos.com/men/ | !@#$%^&*() | 123123123

  Scenario Outline: Check Sign In with valid login and invalid password
    Given User opens '<homePage>' page
    And User moves to 'Sign In' button
    And User clicks 'Sign In' button
    When User enter valid '<login>' and invalid '<password>'
    And User clicks 'Sign In' button on 'Sign in' page
    Then User checks error message of email address or password are incorrect
    Examples:
      | homePage                  | login                     | password
      | https://www.asos.com/men/ | rainbow77733327@gmail.com | !@#$%^&*()

  Scenario Outline: Check increasing of displayed items on the search page after clicking 'Load More' button
    Given User opens '<homePage>' page
    When User makes search by keyword '<keyWord>'
    And User clicks on the 'Search' button
    And User scrolls to 'Load More' button
    And User checks how many items are displayed on the page
    And User clicks to 'Load More' button
    And User scrolls to 'Load More' button
    Then User checks that count of viewed items increased

    Examples:
      | homePage                  | keyWord  |
      | https://www.asos.com/men/ | Sneakers |

  Scenario Outline: Check decreasing of found products after choosing 'Discount %' to 40 - 50%
    Given User opens '<homePage>' page
    And User makes search by keyword '<keyWord>'
    And User clicks on the 'Search' button
    And User checks count of search elements
    When User click the 'Discount %' button
    And User click the <index> discount selection
    Then User checks that count of found products decreased

    Examples:
      | homePage                  | keyWord  | index |
      | https://www.asos.com/men/ | Sneakers | 3     |

  Scenario Outline: Check header of 'Sale Bestsellers' page
    Given User opens '<homePage>' page
    And User moves to 'Sales' category
    And User moves to 'Sale bestsellers' button
    When User clicks 'Sale bestsellers' button
    Then User checks header on the page, it must be '<header>'
    Examples:
      | homePage                  | header           |
      | https://www.asos.com/men/ | Sale Bestsellers |

  Scenario Outline: Check URL of the page after returning to 'Home Page'
    Given User opens '<homePage>' page
    And User moves to 'Sales' category
    And User moves to 'Sale bestsellers' button
    And User clicks 'Sale bestsellers' button
    When User clicks 'Men' page button
    Then User checks that opened '<homePage>'
    Examples:
      | homePage                  |
      | https://www.asos.com/men/ |

  Scenario Outline: Check the 'Instagram' button for opening correct URL
    Given User opens '<homePage>' page
    And User scrolls to 'Instagram' button
    When User clicks 'Instagram' button
    Then User checks that opened '<instagramPage>' in new tab
    Examples:
      | homePage                  | instagramPage                                         |
      | https://www.asos.com/men/ | https://www.instagram.com/accounts/login/?next=/asos/ |
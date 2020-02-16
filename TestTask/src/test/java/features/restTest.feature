@rest
Feature: Rest test case

  @loginRest
  Scenario Outline: Authorization in the system use rest
    Given Send post on Authorization with login "<user name>" and password "<password>"
    Then Status rest "<result>"
    @smoke
    Examples:
      | user name                  | password  | result  |
      | john_dow@some.domaine.com  | 123456789 | SUCCESS |
      | john_dow@some.domaine.com  | 123       | FAILED  |
      | john_dow@some.domaine.ru   | 123456789 | FAILED  |
      | simon_dow@some.domaine.com | 123456789 | SUCCESS |
      | simon_dow@some.domaine.com | 6f7       | FAILED  |
      | simon_dow@some.domaine.ru  | 123456789 | FAILED  |

  @logout
  Scenario: Sign out use rest
    Given Send post on Authorization with login "john_dow@some.domaine.com" and password "123456789"
    When Send post for logout
    Then Status rest "SUCCESS"

  @delToDoList
  Scenario: Delete case to to-do list
    Given Send post on Authorization with login "john_dow@some.domaine.com" and password "123456789"
    And Add string "The task 1" use rest
    When Delete line use rest
    Then Status rest "SUCCESS"

  @addToDoList
  Scenario: Add case to to-do list use rest
    Given Send post on Authorization with login "john_dow@some.domaine.com" and password "123456789"
    When Add string "The task 1" use rest
    Then Line "The task 1" has been add
@UI
Feature: Tests for UI

  @login
  Scenario Outline: Authorization in the system
    Given Fill in login "<user name>" and password "<password>"
    Then Login <result>
    @smoke
    Examples:
      | user name                 | password  | result     |
      | john_dow@some.domaine.com | 123456789 | successful |
      | john_dow@some.domaine.com | 123       | failed     |
      | john_dow@some.domaine.ru  | 123456789 | failed     |
    @regress
    Examples:
      | user name                  | password  | result     |
      | simon_dow@some.domaine.com | 123456789 | successful |
      | simon_dow@some.domaine.com | 6f7       | failed     |
      | simon_dow@some.domaine.ru  | 123456789 | failed     |

  @logout
  Scenario: Sign out
    Given Fill in login "john_dow@some.domaine.com" and password "123456789"
    When Click on button Sign out
    Then Authorization form is open

  @delToDoList
  Scenario: Delete case to to-do list
    Given Fill in login "john_dow@some.domaine.com" and password "123456789"
    When Delete all line
    Then To-do list contains 0 line

  @addToDoList
  Scenario: Add case to to-do list
    Given Fill in login "john_dow@some.domaine.com" and password "123456789"
    And Delete all line
    When Add string "The task 1"
    Then In line 1 contains "The task 1"
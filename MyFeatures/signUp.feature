Feature:  Sign up

  Scenario Outline:trying to signing up with an existing email
    Given that the user is not logged in
    When the information exists, the email is "<Email>"
    Then signing up fails

    Examples:
      | Email                      |
      | s12112958@stu.najah.edu       |


  Scenario:trying to signing up with incorrect email format
    Given that the user is not logged in
    When the email format is incorrect
    Then signing up fails


  Scenario Outline:trying to signing up with new account
    Given that the user is not logged in
    When the information exists, the email is not "<Email>" and password is "<Password>"
    Then signing up succeeds

    Examples:
      | Email                      ||Password|
      | abdelrahmanmasri33@gmail.com       || 123|
      | s1211295588@stu.najah.edu  |        |   123 |

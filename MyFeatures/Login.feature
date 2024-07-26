Feature:Login

  Scenario Outline : Valid information
    Given that the user is not logged in
    And the information is valid email is "<Email> " and password is "<Password>"
    Then user successfully log in
    Examples:
      | Email                      | Password |
      |abdelrahmanmasri3@gmail.com |123       |
      | s12112958@stu.najah.edu  | 123     |


  Scenario Outline: Invalid email
    Given that the user is not logged in
    And the email is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                       | Password |
      | abdelrahmanmasr@gmail.com   | 123      |
      | s121129588@stu.najah.edu  | 123     |

  Scenario Outline: Invalid password
    Given that the user is not logged in
    And the password is invalid email is "<Email>" and password is "<Password>"
    Then user failed in log in

    Examples:
      | Email                       | Password |
      | abdelrahmanmasri3@gmail.com        | 1235     |
      | s12112958@stu.najah.edu       |       1212   |


  Scenario Outline: Invalid information
    Given that the user is not logged in
    And the information is invalid, email is "<Email>" and password is "<Password>"
    Then user failed in log in
    Examples:
      | Email                       | Password |
      | s121129fdj588@stu.najah.edu  | 123jhfv     |
      |                             |          |

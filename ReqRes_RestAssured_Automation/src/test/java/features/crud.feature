@smoke
Feature: Validate CRUD opeations on reqres.in

Scenario: Create a new user
  Given I create a user with name "Aarti" and job "Senior QA Engineer"
  Then The response status code should be 201
  And The response should contain name "Aarti"
  
Scenario: Get user list
  Given I fetch the user list
  Then The response status code should be 200
  And The response should contain page number "2"

Scenario: Get single user
  When I fetch user with id 2
  Then The response status code should be 200

Scenario: Update user
  When I update the user with id 2 to name "Tom" and job "Manager"
  Then The response status code should be 200


Scenario: Delete user
  When I delete the user with id 2
  Then The response status code should be 204

Scenario: Register a user
  When I register a user with email "eve.holt@reqres.in" and password "pistol"
  Then The response status code should be 200

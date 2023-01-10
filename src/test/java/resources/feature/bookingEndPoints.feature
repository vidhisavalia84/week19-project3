Feature: Testing different request on the restful booker app
  As a user I want to test herokuapp boker application

  Scenario: As a user I create booking
    When I send post request to create booking
    Then I should get back with status code 200

    Scenario: As a user I check that booking is created
      When I send get request with bookingId
      Then I should get back with response 200

  Scenario: As a user I update booking
    When when I send post request with bookingId
    Then I shold see booking is updated and get back with response code 200

    Scenario: As a user I delete booking
      When I send delete request with bookindId
      Then I should see booking is deleted
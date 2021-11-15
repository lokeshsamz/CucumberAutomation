Feature: Ticket Booking

  Scenario Outline: Get all threatre names for the given movie
    Given I login to TicketsNew website
    When I go to 'Movies' tab and select the movie name '<MovieName>'
    Then To verify the page proceeded to selected movie
    When I select the amenities as '<Amenities>'
    When I select the movie date as '<MovieDate>'
    When I retrieve the available threatre names

    @ticketnew
    Examples: 
      | MovieName | Amenities | MovieDate |
      | Enemy     | F&B       | any       |

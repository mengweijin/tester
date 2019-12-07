Feature: Search by keyword

  @Case_02
  Scenario: Searching for a term
    Given today is Friday
    When I ask whether it's Friday yet
    Then I should be told "Sunday"
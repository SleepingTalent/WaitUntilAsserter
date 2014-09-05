Feature: Basic Feature

  Scenario: Assertion Returns True within Timeout Range
    Given a service takes "5000" milliseconds to complete
    When the assertion time out is set to "6000" milliseconds
    Then the assertion will pass

  @wip
  Scenario: Assertion Returns True outwith Timeout Range
    Given a service takes "7000" milliseconds to complete
    When the assertion time out is set to "6000" milliseconds
    Then the assertion will fail
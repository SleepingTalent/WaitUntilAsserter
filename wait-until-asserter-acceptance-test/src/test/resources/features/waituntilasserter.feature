Feature: WaitUntilAsserter Feature

  Scenario: Expected assertion result returns within Timeout Range
    Given a service process takes "2000" milliseconds to complete
    When the assertion time out is set to "4000" milliseconds
    Then the assertion will pass

  Scenario: Expected assertion result returns outwith Timeout Range
    Given a service process takes "4000" milliseconds to complete
    When the assertion time out is set to "2000" milliseconds
    Then the assertion will fail
@Gcms

Feature: Performing gcms core operations in GCMS Application

  Background: Login into GCMS Services region
    Given User is on GCMS Services Homepage
    When User gives credentials and selects language
    And  Clicks on login button
    Then User verifies if login is successful or not
    When User starts jurisprudence search in WIP module
    And  Search by marginal entering the value
    When Click on the marginal numberHighlighted in blue

  @SubtopicCreation @SubtopicGulf @SubtopicSpain
  Scenario: Validate user able to add topic-subtopic entries using filter
    And  Expand analysis data section
    When Click on the New option under documentary relevance topic section
    And  Enter the topic value in the filter box
    When Select the added topic and click on New option in the subtopic section
    And  Enter the subtopic value in the filter box
    Then Verify selected values are displayed under Topic and Subtopic fields

  @SubtopicCreationUsingCode @SubtopicSpain
  Scenario: Validate user able to add topic-subtopic entries using code
    And  Expand analysis data section
    When Click on "Add new using Code" option in the subtopic section
    And  Insert the subtopicCode in the text box
    When Click on Transfer option in the pop up
    Then Verify selected value entries using code displayed under Topic and Subtopic fields

  @DeleteSubtopic @SubtopicSpain @SubtopicGulf
  Scenario: Validate user able to delete topic-subtopic
    And  Expand analysis data section
    When Select with the radio button the subtopic to delete
    And  Click on the Delete button under subtopic section
    Then Verify the popup appears for delete button
    Then Verify topic subtopic is deleted successfully










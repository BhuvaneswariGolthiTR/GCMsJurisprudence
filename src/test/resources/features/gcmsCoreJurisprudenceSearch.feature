Feature: Performing CRUD operations after Jurisprudence search

  Background: Search document using Full Marginal Id
    Given User is logged to into the application
    When User navigates to search option in JURISPRUDENCE

  @search
  Scenario: Validating displayed full document in JURISPRUDENCE
    When User searches the document
    And Clicks on the document to view the details
    Then User validates if document is displayed successfully or not

  @classificationEntry
  Scenario: Validate user able to add and delete unique "Classification Entries" in the Analysis section
    When User views the searched document details
    Then User expands "Analysis Data" section
    And User clicks on "New" button under "Classification Entries" section
    Then User insert the values in Classification entry section
    And User click "Ok" and "Go back" button
    Then User validates if new entries are added in "Classification Entries" or not
    And User clicks on "Delete" button to remove added classification entry
    Then Verify if "Classification Entries" section is empty or not

  @reports
  Scenario: Validate user able view and delete the created reports from Result list
    When User selects "year" and "number" parameters and passes the values
    And User clicks on search button to get list of matching documents
    Then User moves to "Reports" and selects report format
    And User moves to JURISPRUDENCE Report section to view reports
    Then User opens reports
    Then User deletes the generated reports

  @multipleRelationships
  Scenario: Validate user able to add and delete multiple "Relationship" in the Analysis report section
    When User views the searched document details
    Then User expands "Analysis Data" section
    And User clicks on "Add Multiple" button under "Relationships" section
    Then User fills fields in Multiple Relationship Page
    And Adds new relationship for same document
    Then User clicks close button
    And User validates if multiple relationships are added in Relationships or not
    Then User clicks on "Delete" link to delete both created relationships
    Then Verify if "Relationships" section is empty or not

  @subTopic
  Scenario: Validate user able to add topic-subtopic entries using filter
    When User views the searched document details
    Then User expands "Analysis Data" section
    And User clicks on "New" button under "Topic" section
    And Search and select the "Topic" value in the filter box
    When User clicks on "New" button under "SubTopic" section
    And Search and select the "SubTopic" value in the filter box
    Then Verify selected values are displayed under Topic and Subtopic fields
#    And Delete the both added topic and subtopic values
#    Then

    

@GcmsCoreWorkFlow

Feature: Performing gcms core workflow execution

  @WorkflowDelete
  @Workflow
  Scenario: Validate user able to delete existing workflow
    Given User Login to the gcms application "operator"
    When User starts jurisprudence search by marginal value
    When Click on the marginal numberHighlighted in blue
    Then Delete the existing workflow if any
    And  Click on Initialize the full workflow
    When Expand analysis data section
    And  Delete topic subtopic information and Delete practice area if exists
    And  Delete the XML File in the project if exists

  @WorkflowExecution
  @Workflow
  Scenario: Validate user able to complete a full execution of a workflow by login as operator
    Given User Login to the gcms application "operator"
    When User starts jurisprudence search by marginal value
    When Click on the marginal numberHighlighted in blue
    When User click on Load original text button and loads the orginal text
    And  User uploads file and cut up file document
    Then Expand and check control data section
    And  Expand and check analysis data section and text section
    When User navigate to workflow pre assign section and update agent details
    Then Verify control data section of the document
    And  User logoff from the application

  @WorkflowEndPreSelection @Workflow
  Scenario: Validate user by loging as analyst
    Given User Login to the gcms application "Analyst"
    When User navigate to jurisprudence decisions pending inbox
    And  Click on the marginal numberHighlighted in blue
    Then Verify end of document display page should display End preselection button
    And  Click on Edit button and verify document text
    When User expands Analysis data section and update analysis level and quality values
    And  Click on add practice area and update the values
    And  Click on End pre-selection button
    Then Verify control data section of the document after workflow
    And  User logoff from the application
#
  @AssignCitatorName @Workflow
  Scenario: Validate user able to assign citator name by login as operator
    Given User Login to the gcms application "operator"
    When User starts jurisprudence search by marginal value
    When Click on the marginal numberHighlighted in blue
    When User select Workflow Assign text to citation process
    And  User Select a Citator name from the list box and click on assign button
    Then Verify Citator name added successfully
    Then Verify control data section of the document after assign citiator
    And  Verify Text section of the document import button should not be displayed

  @EditDocumentText @Workflow
  Scenario: Validate user able to edit the document text by login as citiator
    Given User Login to the gcms application "citiator"
    When  User navigate to jurisprudence decisions pending inbox
    And   Click on the marginal numberHighlighted in blue
    And   Check on Text section of the document and verify import button is displayed
    And   Click on import cited text button
    Then  Verify popup appears to upload xml file
    And   Click on End citiation activity button to finish the task
    Then  Verify success message displayed
    Then  Verify control data section of the document after editing the document
    And   User logoff from the application

  @AssignAnalystName @Workflow
  Scenario: Validate user able to assign analyst name by login as operator
    Given User Login to the gcms application "operator"
    When User starts jurisprudence search by marginal value
    When Click on the marginal numberHighlighted in blue
    When User select Workflow Assign text to analyst
    And  User Select an analyst name from the list box and click in assign button
    Then Verify analyst name added successfully
    Then Verify control data section of the document after assigning analyst
    And  User logoff from the application

  @EndAnalysis @Workflow
  Scenario: Validate user able to click on end analysis login as operator
    Given User Login to the gcms application "Analyst"
    When User starts jurisprudence search by marginal value
    When Click on the marginal numberHighlighted in blue
    And  Click on End analysis buttom
    Then Check the control section of the document
    And  User logoff from the application

  @WorkflowTextDownload @Workflow
  Scenario: Validate user by login as operator
    Given User Login to the gcms application "operator"
    When User starts jurisprudence search by marginal value
    When Click on the marginal numberHighlighted in blue
    When User navigate to workflow assign publication marginal number form
    And  Click on Renumerar buttom
    Then Verify control data section of the document after assign publication marginal number




















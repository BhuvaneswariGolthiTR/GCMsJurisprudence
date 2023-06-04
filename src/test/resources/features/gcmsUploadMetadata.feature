@GcmsUploadMetaData @Gcms

Feature: Performing Upload Metadata for documenting and deleting

  @uploadingMetadata
  Scenario: Uploading metadata for document
    Given User is logged to into the application
    And  User deleted the document if already existing
    Given User connects to the SFTP server for loading metadata
    And   User places the input file in the inbox folder
    When Click on administration tab
    And  Click on Process management link and navigate to search link
    When User update all dropdown values under process search area
    And  Click on search button
    Then Verify display of all scheduled process and search for the upload document
    And  User update the contents of myPaths textbox in the myPaths textbox.
    And  User update the content of logdirtextbox path to ftpserver path
    Then Once updations are done click on update button
    And  click on the Execute link of the MetadataLoadProcess scheduled process.
    Then Check Metadata Loaded from sftp server
    And  User verify load was successful and document is displayed under resultset




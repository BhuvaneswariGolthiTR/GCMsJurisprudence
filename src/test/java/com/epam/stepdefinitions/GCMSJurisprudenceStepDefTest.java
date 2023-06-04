package com.epam.stepdefinitions;

import com.epam.pages.actions.*;
import com.epam.pages.verifications.GCMSClassificationEntriesPageVerification;
import com.epam.pages.verifications.GCMSJurisprudenceRelationshipPageVerification;
import com.epam.pages.verifications.GCMSJurisprudenceSearchPageVerification;
import com.epam.pages.verifications.GCMSJurisprudenceTopicPageVerification;
import com.epam.setup.systemsettings.ImportApplicationVariables;

import io.cucumber.java.en.*;

import java.awt.*;
import java.io.IOException;

public class GCMSJurisprudenceStepDefTest extends BaseStepDefTest {

    GcmsJurisprudenceLoginPage loginPage = new GcmsJurisprudenceLoginPage(getDriver());
    GCMSJurisprudenceSearchPage searchPage = new GCMSJurisprudenceSearchPage(getDriver());
    GCMSClassificationEntriesPage classificationEntryPage = new GCMSClassificationEntriesPage(getDriver());
    GCMSJurisprudenceReportsPage reportsPage = new GCMSJurisprudenceReportsPage(getDriver());
    GCMSJurisprudenceRelationshipPage relationshipPage = new GCMSJurisprudenceRelationshipPage(getDriver());
    GCMSJurisprudenceTopicPage topicPage = new GCMSJurisprudenceTopicPage(getDriver());
    GCMSJurisprudenceSearchPageVerification searchPageVerification = new GCMSJurisprudenceSearchPageVerification(getDriver());
    GCMSClassificationEntriesPageVerification classificationEntryVerify = new GCMSClassificationEntriesPageVerification(getDriver());
    GCMSJurisprudenceRelationshipPageVerification relationshipVerification = new GCMSJurisprudenceRelationshipPageVerification(getDriver());
    GCMSJurisprudenceTopicPageVerification topicVerification = new GCMSJurisprudenceTopicPageVerification(getDriver());

    public GCMSJurisprudenceStepDefTest() throws AWTException {
    }

    @Given("User is logged to into the application")
    public void userIsLoggedToIntoTheApplication() throws IOException, InterruptedException, AWTException {
        ImportApplicationVariables.setVariables();
        loginPage.accessURL(ImportApplicationVariables.loginURL);
        Thread.sleep(4000);
        loginPage.setLanguage();
        Thread.sleep(4000);
        loginPage.setUsername(ImportApplicationVariables.username);
        loginPage.setPassword(ImportApplicationVariables.password);
        loginPage.clickLoginButton();
    }

    @When("User navigates to search option in JURISPRUDENCE")
    public void userNavigatesToSearchOptionInJURISPRUDENCE() {
        searchPage.navigateToSearchInJurisprudence();
    }

    @When("User searches the document")
    public void userSearchesTheDocument() throws InterruptedException, AWTException {
        searchPage.selectDocumentIdFull();
        Thread.sleep(3000);
        searchPage.enterFullMarginalId(ImportApplicationVariables.fullMarginalId);
        searchPage.clickSearchButton();
    }

    @And("Clicks on the document to view the details")
    public void clicksOnTheDocumentToViewTheDetails() throws InterruptedException {
        searchPage.clickMarginalIdLink();
    }

    @Then("User validates if document is displayed successfully or not")
    public void userValidatesIfDocumentIsDisplayedSuccessfullyOrNot() {
        searchPageVerification.verifyDocumentSearch("'" + ImportApplicationVariables.fullMarginalId + "'");
    }

    @When("User views the searched document details")
    public void userViewsTheSearchedDocumentDetails() throws InterruptedException, AWTException {
        searchPage.selectDocumentIdFull();
        searchPage.enterFullMarginalId(ImportApplicationVariables.fullMarginalId);
        searchPage.clickSearchButton();
        searchPage.clickMarginalIdLink();
    }

    @Then("User expands {string} section")
    public void userExpandsSection(String arg0) throws InterruptedException {
        classificationEntryPage.expandAnalysisDataSection();
        Thread.sleep(4000);
    }

    @And("User clicks on {string} button under {string} section")
    public void userClicksOnButtonUnderSection(String button, String section) throws InterruptedException {
        switch (section) {
            case "Classification Entries":
                classificationEntryPage.clickNewButton(button);
                break;
            case "Relationships":
                relationshipPage.clickAddMultipleButton();
                break;
            case "Topic":
                topicPage.clickNewButton();
                break;
            case "SubTopic":
                if (button.equals("Add new using Code"))
                    topicPage.clickOnAddNewCodeButton();
                else
                    topicPage.clickSubTopicNewButton();
                break;
        }
    }

    @And("User insert the values in Classification entry section")
    public void userInsertTheValuesInClassificationEntrySection() {
        classificationEntryPage.insertValuesInThesauriSection();
        classificationEntryPage.insertValuesInTopicSection();
        classificationEntryPage.insertValuesInAnalystSection();
    }

    @And("User click {string} and {string} button")
    public void userClickAndButton(String arg0, String arg1) {
        classificationEntryPage.clickOkButton();
        classificationEntryPage.clickGoBackButton();
    }

    @Then("User validates if new entries are added in {string} or not")
    public void userValidatesIfNewEntriesAreAddedInOrNot(String arg0) {
        classificationEntryVerify.verifyDisplayOfNewClassificationEntry();
    }

    @And("User clicks on {string} button to remove added classification entry")
    public void userClicksOnButtonToRemoveAddedClassificationEntry(String arg0) {
        classificationEntryPage.deleteClassificationEntry();
    }

    @Then("Verify if Classification Entries section is empty or not")
    public void verifyIfClassificationEntriesSectionIsEmptyOrNot() {

    }

    @When("User selects {string} and {string} parameters and passes the values")
    public void userSelectsAndParametersAndPassesTheValues(String arg0, String arg1) throws InterruptedException, AWTException {
        reportsPage.selectDocumentYear();
        reportsPage.setYear();
        reportsPage.selectDocumentNumber();
        Thread.sleep(4000);
        reportsPage.selectOperator();
        reportsPage.setNumber(ImportApplicationVariables.reportNumber);
        Thread.sleep(5000);

    }

    @And("User clicks on search button to get list of matching documents")
    public void userClicksOnSearchButtonToGetListOfMatchingDocuments() throws InterruptedException {
        reportsPage.clickSearchButton();
        Thread.sleep(6000);
    }

    @Then("User moves to {string} and selects report format")
    public void userMovesToAndSelectsReportFormat(String arg0) throws InterruptedException {
        reportsPage.clickReportsButton();
        Thread.sleep(5000);
        reportsPage.clickReviewFormat();
        reportsPage.clickOkButton();
    }

    @And("User moves to JURISPRUDENCE Report section to view reports")
    public void userMovesToJURISPRUDENCEReportSectionToViewReports() throws InterruptedException {
        reportsPage.clickJurisprudenceReports();
        reportsPage.clickViewLink();
    }

    @Then("User opens reports")
    public void userOpensReports() throws AWTException, InterruptedException {
        reportsPage.openReports();
    }

    @Then("User deletes the generated reports")
    public void userDeletesTheGeneratedReports() throws InterruptedException {
        reportsPage.clickCheckBoxToDelete();
        reportsPage.clickDiscard();

    }

    @Then("User fills fields in Multiple Relationship Page")
    public void userFillsFieldsInMultipleRelationshipPage() throws InterruptedException {
        relationshipPage.fillDetailsInAffectedField();
        relationshipPage.fillDetailsInRelationShipData();
        relationshipPage.clickTickMark();
    }

    @And("Adds new relationship for same document")
    public void addsNewRelationshipForSameDocument() {
        relationshipPage.addNewRelationship();
        relationshipPage.clickTickMark();
    }

    @Then("User clicks close button")
    public void userClicksCloseButton() throws InterruptedException {
        relationshipPage.clickCloseBtn();
    }

    @And("User validates if multiple relationships are added in Relationships or not")
    public void userValidatesIfMultipleRelationshipsAreAddedInRelationshipsOrNot() {
        relationshipVerification.validateMultipleRelationships();
    }

    @Then("User clicks on {string} link to delete both created relationships")
    public void userClicksOnLinkToDeleteBothCreatedRelationships(String arg0) {
        relationshipPage.deleteMultipleRelationships();
    }

    @Then("Verify if {string} section is empty or not")
    public void verifyIfSectionIsEmptyOrNot(String section) {
        if (section.equals("Relationships")) {
            relationshipVerification.verifyRelationshipsDeletion();
        } else {
            classificationEntryVerify.verifyClassificationEntryDeletion();
        }
    }

    @And("Search and select the {string} value in the filter box")
    public void searchAndSelectTheTopicValueInTheFilterBox(String section) {
        switch (section) {
            case "Topic":
                topicPage.searchTopicValue();
                topicPage.selectTopicValue();
                break;
            case "SubTopic":
                topicPage.searchSubTopicValue();
                topicPage.selectSubTopicValue();
                break;
        }

    }

    @Then("Verify selected values are displayed under Topic and Subtopic fields")
    public void verifySelectedValuesAreDisplayedUnderTopicAndSubtopicFields() {
        topicVerification.verifySelectedTopicValueDisplayed();
        topicVerification.verifySelectedSubTopicValueDisplayed();
    }

    @And("Delete the both added topic and subtopic values")
    public void deleteTheBothAddedTopicAndSubtopicValues() {
        topicPage.deleteTopicAndSubTopicEntries();
    }

    @Then("Validate the deletion of topic-subtopic entries")
    public void validateTheDeletionOfTopicSubtopicEntries() {
        topicVerification.verifySubTopicDeletion();
    }

    @Then("Enter topic-subtopic values in text box of new window")
    public void enterTopicSubtopicValuesInTextBoxOfNewWindow() {
        topicPage.enterTopicSuTopicValuesUsingCode();
    }

    @When("Click on Transfer option in the pop up")
    public void clickOnTransferOptionInThePopUp() {
        topicPage.clickOnTransferButton();
    }

    @Then("Verify selected value entries using code displayed under Topic and Subtopic fields")
    public void verifySelectedValueEntriesUsingCodeDisplayedUnderTopicAndSubtopicFields() {
        topicVerification.verifyTopicSubTopicEntriesUsingCodeAreDisplayed();
    }

}

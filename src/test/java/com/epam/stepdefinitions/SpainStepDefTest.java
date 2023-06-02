//package com.epam.stepdefinitions;
//
//import com.epam.setup.systemsettings.ImportApplicationVariables;
//import com.epam.verifications.GcmsCoreDocumentDetailsVerificationPage;
//import com.epam.verifications.GcmsDocumentSearchVerification;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//import java.awt.*;
//import java.io.IOException;
//
//public class SpainStepDefTest extends BaseStepDefTest {
//    GcmsLoginPage loginPage = new GcmsLoginPage(getDriver());
//    GcmsDocumentSearchPage searchPage = new GcmsDocumentSearchPage(getDriver());
//    GcmsDocumentSearchVerification verification = new GcmsDocumentSearchVerification(getDriver());
//    GcmsCoreDocumentDetailPage detailPage =new GcmsCoreDocumentDetailPage(getDriver());
//    GcmsCoreDocumentDetailsVerificationPage detailsVerificationPage=new GcmsCoreDocumentDetailsVerificationPage(getDriver());
//    GcmsUploadingMetadataPage gcmsUploadingMetadataPage = new GcmsUploadingMetadataPage(getDriver());
//
//    @Given("User is on GCMS Services Homepage")
//    public void user_is_on_GCMS_Services_Homepage() throws IOException {
//        ImportApplicationVariables.setVariables();
//        getDriver().get(ImportApplicationVariables.url);
//    }
//
//    @When("User gives credentials and selects language")
//    public void user_gives_credentials_and_selects_language() throws InterruptedException, AWTException {
//        Thread.sleep(5000);
//        gcmsUploadingMetadataPage.clickLanguageDropdown();
//        Thread.sleep(5000);
//        loginPage.setUsername(ImportApplicationVariables.username);
//        loginPage.setPassword(ImportApplicationVariables.password);
//        Thread.sleep(2000);
//    }
//
//    @When("Clicks on login button")
//    public void clicks_on_login_button() throws InterruptedException {
//        loginPage.clickLoginButton();
//        Thread.sleep(3000);
//    }
//
//    @Then("User verifies if login is successful or not")
//    public void userVerifiesIfLoginIsSuccessfulOrNot() {
//        loginPage.verifyLogin();
//    }
//    @When("User starts jurisprudence search in WIP module")
//    public void user_starts_jurisprudence_search_in_WIP_module() throws InterruptedException, AWTException {
//       searchPage.navigateToJurisprudenceSearchPage();
//    }
//    @When("Search by marginal entering the value")
//    public void search_by_marginal_entering_the_value() throws InterruptedException, AWTException {
//        searchPage.deleteExistingMarginalNumber();
//        verification.sendTextUsingRobot(ImportApplicationVariables.marginalNumber);
//        searchPage.clickSearch();
//        Thread.sleep(3000);
//    }
//
//    @When("Click on the marginal numberHighlighted in blue")
//    public void click_on_the_marginal_numberHighlighted_in_blue() throws InterruptedException {
//        searchPage.clickDocument(ImportApplicationVariables.marginalNumber);
//        Thread.sleep(10000);
//    }
//
//    @When("Expand analysis data section")
//    public void expand_analysis_data_section() {
//        detailPage.expandAnalysisDataSection();
//    }
//    @When("Click on the New option under documentary relevance topic section" )
//    public void ClickOnTheNewOptionUnderDocumentaryRelevanceTopicSection() throws InterruptedException {
//        detailsVerificationPage.clickOnNewButtonAndSElectTopicValue(ImportApplicationVariables.topicValue);
//
//    }
//
//    @When("Enter the topic value in the filter box")
//    public void enter_the_topic_value_in_the_filter_box() throws InterruptedException {
//      detailsVerificationPage.enterTopicValue();
//    }
//
//    @When("Select the added topic and click on New option in the subtopic section")
//    public void Select_the_added_topic_and_click_on_option_in_the_subtopic_section() throws InterruptedException {
//      detailsVerificationPage.clickOnNewButtonAndSelectSubTopicValue(ImportApplicationVariables.subTopicValue);
//    }
//
//    @When("Enter the subtopic value in the filter box")
//    public void enter_the_subtopic_value_in_the_filter_box() throws InterruptedException {
//       detailsVerificationPage.enterSubTopicValue();
//    }
//
//    @Then("Verify selected values are displayed under Topic and Subtopic fields")
//    public void verify_selected_values_are_displayed_under_Topic_and_Subtopic_fields() throws InterruptedException {
//        Thread.sleep(5000);
//        detailsVerificationPage.verifyTopicAndSubTopicValue(ImportApplicationVariables.topicValue,ImportApplicationVariables.subTopicValue,true);
//    }
//
//    @When("Click on {string} option in the subtopic section")
//    public void Click_on_option_in_the_subtopic_section(String value) throws InterruptedException {
//        detailsVerificationPage.switchToFrame();
//       detailsVerificationPage.clickOnAddNewCodeButton(value);
//    }
//
//    @When("Insert the subtopicCode in the text box")
//    public void insert_the_subtopicCode_in_the_text_box() throws InterruptedException {
//       detailsVerificationPage.enterSubTopicCodeValue(ImportApplicationVariables.subTopicCodeValue);
//    }
//
//    @When("Click on Transfer option in the pop up")
//    public void click_on_Transfer_option_in_the_pop_up() throws InterruptedException {
//       detailPage.clickOnTransferButton();
//        Thread.sleep(5000);
//    }
//    @Then("Verify selected value entries using code displayed under Topic and Subtopic fields" )
//    public void verifySelectedValueEntriesUsingCodeDisplayedUnderTopicAndSubtopicFields() {
//        detailsVerificationPage.verifyTopicAndSubTopicValue("Civil (Teoría General)","Nacionalidad",true);
//        detailsVerificationPage.verifySelectedTopicValueDisplayed("Actividades y sectores",true);
//        detailsVerificationPage.verifySelectedSubTopicValueDisplayed("Actividades MINP",true);
//    }
//    @When("Select with the radio button the subtopic to delete")
//    public void select_with_the_radio_button_the_subtopic_to_delete() throws InterruptedException {
//        detailsVerificationPage.selectSubTopicDeleteButton(ImportApplicationVariables.subTopicValue,ImportApplicationVariables.topicValue);
//    }
//
//    @When("Click on the Delete button under subtopic section")
//    public void click_on_the_Delete_button_under_subtopic_section() {
//      detailPage.clickOnSubTopicDeleteButton();
//    }
//
//    @Then("Verify the popup appears for delete button")
//    public void verify_the_popup_appears_for_delete_button() throws InterruptedException {
////       detailsVerificationPage.verifyPopUpTextDisplayedForDeleteSubTopic("Va a borrar la ficha de submateria 'Nacionalidad'.\n" +
////               "¡Esta operación es irreversible!");
//       detailPage.clickOnYesButton();
//
//    }
//
//    @Then("Verify topic subtopic is deleted successfully")
//    public void verify_topic_subtopic_is_deleted_successfully() {
//        detailsVerificationPage.verifySelectedSubTopicValueDisplayed(ImportApplicationVariables.subTopicValue,false);
//    }
//
//   }
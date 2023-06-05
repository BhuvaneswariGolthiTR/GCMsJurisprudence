package com.epam.stepdefinitions;

import com.epam.pages.actions.*;
import com.epam.pages.verifications.GCMSJurisprudenceSearchPageVerification;
import com.epam.pages.verifications.GCMSJurisprudenceTopicPageVerification;
import com.epam.pages.verifications.GcmsCoreDocumentWorkFlowVerificationPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import io.cucumber.java.en.*;

import java.awt.*;
import java.io.IOException;

public class WorkFlowStepDefTest extends BaseStepDefTest {
    GcmsJurisprudenceLoginPage loginPage = new GcmsJurisprudenceLoginPage(getDriver());
    GCMSJurisprudenceSearchPage searchPage = new GCMSJurisprudenceSearchPage(getDriver());
    GCMSJurisprudenceSearchPageVerification verification = new GCMSJurisprudenceSearchPageVerification(getDriver());
    GCMSJurisprudenceTopicPage detailPage = new GCMSJurisprudenceTopicPage(getDriver());
    GCMSJurisprudenceTopicPageVerification detailsVerificationPage = new GCMSJurisprudenceTopicPageVerification(getDriver());
    GcmsCoreDocumentWorkFlowPage workFlowPage = new GcmsCoreDocumentWorkFlowPage(getDriver());
    GcmsUploadingMetadataPage gcmsUploadingMetadataPage = new GcmsUploadingMetadataPage(getDriver());
    GcmsCoreDocumentWorkFlowVerificationPage workFlowVerificationPage = new GcmsCoreDocumentWorkFlowVerificationPage(getDriver());
    //    private static String CREATED_DOCUMENT = "PROV\\2000\\9";
    private static String CREATED_DOCUMENT = "PROV\\2019\\89063";

    public WorkFlowStepDefTest() throws AWTException {
    }

    @When("Expand control data section")
    public void expand_control_data_section() {
        workFlowPage.expandControlDataSection();
    }

    @Then("Delete the existing workflow if any")
    public void delete_the_existing_workflow_if_any() {
        workFlowPage.clickOnDeleteWorkFlowButton();
    }

    @Then("Click on Initialize the full workflow")
    public void click_on_Initialize_the_full_workflow() throws InterruptedException {
        workFlowPage.clickOnRestartCompleteProcess();
        workFlowPage.clickOnGoBackButton();
    }

    @When("Delete topic subtopic information and Delete practice area if exists")
    public void delete_topic_subtopic_information_and_Delete_practice_area_if_exists() throws InterruptedException {
        detailsVerificationPage.deleteExistingTopicAndSubTopics();
        getDriver().switchTo().defaultContent();
        // workFlowPage.clickOnEditButton();
        // Thread.sleep(5000);
        //detailsVerificationPage.expandAnalysisDataSection();
        // Thread.sleep(2000);
        // detailsVerificationPage.deletePracticeAreaIfExists();
        // detailsVerificationPage.switchToFrame();
//        detailsVerificationPage.deleteExistingTopicAndSubTopics();
//        getDriver().switchTo().defaultContent();
//        Thread.sleep(2000);
//        workFlowPage.clickOnEditButton();
//        Thread.sleep(20000);
//        //workFlowPage.clickOnTextSection();
//       // Thread.sleep(20000);
//        detailsVerificationPage.expandAnalysisDataSection();
//        detailsVerificationPage.switchToFrame();
//        workFlowPage.setRemovePracticeArea();
//        getDriver().switchTo().defaultContent();
//        workFlowPage.clickOnOkButton();
    }

    @When("User click on Load original text button and loads the orginal text")
    public void user_click_on_Load_original_text_button_and_loads_the_orginal_text() throws InterruptedException {
        workFlowPage.clickOnLoadOrginalTextButton();
        Thread.sleep(3000);
    }

    @And("User uploads file and cut up file document")
    public void userUploadsAndFileDocument() throws InterruptedException {
        workFlowVerificationPage.UploadFile();
        Thread.sleep(2000);
        workFlowPage.clickOnOkButton();
        Thread.sleep(20000);
        workFlowPage.clickOnGoBackButton();
        Thread.sleep(2000);
    }

    @Then("Expand and check control data section")
    public void expand_and_check_control_data_section() throws InterruptedException {
        workFlowPage.expandControlDataSection();
        Thread.sleep(2000);
        //verifications need to be added
    }

    @Then("Expand and check analysis data section and text section")
    public void expand_and_check_analysis_data_section_and_text_section() throws InterruptedException {
        detailPage.clickAnalysisDataLink();
        Thread.sleep(2000);
        //verifications need to be added
        workFlowPage.clickOnTextSection();
        Thread.sleep(2000);
        //verifications need to be added
    }

    @When("User navigate to workflow pre assign section and update agent details")
    public void user_navigate_to_workflow_pre_assign_section_and_update_agent_details() throws InterruptedException {
        workFlowVerificationPage.navigateToWorkFlowPreSelectionPage();
        workFlowVerificationPage.navigateToPreSelectionAndAssignAgentValue(ImportApplicationVariables.agentFilterValue);
    }

    @Then("Verify control data section of the document")
    public void verify_control_data_section_of_the_document() throws InterruptedException {
        verification.navigateToControlSectionWorkFlow(CREATED_DOCUMENT);
        workFlowPage.expandControlDataSection();
        Thread.sleep(2000);
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed(ImportApplicationVariables.agentFilterValue);
        //verification pending for checking end pre selection button
    }

    @Then("User logoff from the application")
    public void user_logoff_from_the_application() {
        workFlowPage.clickOnLogoffButton();

    }

    @When("User navigate to workflow assign publication marginal number form")
    public void user_navigate_to_workflow_assign_publication_marginal_number_form() throws InterruptedException {
        searchPage.navigateToWorkflowAssignPublicationMarginalNumber();
        workFlowPage.enterNMNValue(ImportApplicationVariables.NMNValue);
        workFlowPage.enterNMAValue(ImportApplicationVariables.NMAValue);
    }

    @When("Click on Renumerar buttom")
    public void click_on_Renumerar_buttom() throws InterruptedException {
        workFlowVerificationPage.clickOnRenumberingButton();
        //add verification heere
        workFlowPage.clickOnGoBackButton();

    }


    @Then("Click on Edit button and verify document text")
    public void click_on_Edit_button_and_verify_document_text() {
        workFlowPage.clickOnEditButton();
        workFlowVerificationPage.verifyDocumentEditionTextPageIsDisplayed();

    }

    @When("User expands Analysis data section and update analysis level and quality values")
    public void user_expands_Analysis_data_section_and_update_analysis_level_and_quality_values() throws InterruptedException {
        detailPage.clickAnalysisDataLink();
        workFlowVerificationPage.enterAnalysisAndQualityValuesFromTheList(ImportApplicationVariables.analysisValue, ImportApplicationVariables.qualityValue);
        //need to add verification for added values
    }

    @When("Click on End pre-selection button")
    public void click_on_End_pre_selection_button() throws InterruptedException {
        workFlowPage.clickOnEndPreSelectionButton();
        workFlowPage.clickOnGoBackButton();
    }

    @When("Check on Text section of the document and verify import button is displayed")
    public void check_on_Text_section_of_the_document_and_verify_import_button_is_displayed() throws InterruptedException {
        workFlowPage.clickOnTextSection();
        Thread.sleep(2000);
        workFlowVerificationPage.switchToTextFrame();
        workFlowVerificationPage.verifyImportButtonUnderTextSectionIsDisplayed();
    }

    @When("Navigate to text section and export finalized text using export button")
    public void navigate_to_text_section_and_export_finalized_text_using_export_button() {
        // workFlowPage.clickOnExportButton();

    }

    @When("Edit the downloaded document text")
    public void edit_the_downloaded_document_text() {

    }

    @When("Click on import cited text button")
    public void click_on_import_cited_text_button() {
        workFlowPage.clickOnImportButton();

    }

    @Then("Verify popup appears to upload xml file")
    public void verify_popup_appears_to_upload_xml_file() throws InterruptedException {
        workFlowVerificationPage.uploadDownLoadedFile();
    }

    @Then("Click on End citiation activity button to finish the task")
    public void click_on_End_citiation_activity_button_to_finish_the_task() throws InterruptedException {
        Thread.sleep(3000);
        searchPage.navigateToJurisprudencePendingInboxSection();
        searchPage.clickDocument(CREATED_DOCUMENT);
        Thread.sleep(10000);
        workFlowPage.clickOnEndCitiation();
        Thread.sleep(2000);
    }

    @Then("Verify success message displayed")
    public void verify_success_message_displayed() throws InterruptedException {
        workFlowVerificationPage.verifyOperationSuccessMessageIsDisplayed();
        workFlowPage.clickOnGoBackButton();

    }

    @When("User select Workflow Assign text to citation process")
    public void user_select_text_to_citation_process() throws InterruptedException {
        searchPage.navigateToWorkFlowAssignTextToCitiator();
    }

    @When("User Select a Citator name from the list box and click on assign button")
    public void user_Select_a_Citator_name_from_the_list_box_and_click_on_button() throws InterruptedException {
        workFlowVerificationPage.selectAndAssignCitiatorValueFromTheList(ImportApplicationVariables.citatorValue);
    }

    @Then("Verify Citator name added successfully")
    public void verify_Citator_name_added_successfully() {
        //need to add verification details

    }

    @Then("Verify Text section of the document import button should not be displayed")
    public void verify_Text_section_of_the_document_import_button_should_not_be_displayed() throws Exception {
        workFlowPage.clickOnTextSection();
//       workFlowVerificationPage.downloadAndEditXmlDocument();
//        workFlowVerificationPage.switchToTextFrame();
//        workFlowPage.clickOnExportButton();
//        Robot robot = new Robot();
//        Thread.sleep(200);
//        robot.keyPress(KeyEvent.VK_F11);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_F11);
//        Thread.sleep(2000);
//        Thread.sleep(2000);
//        robot.keyPress(KeyEvent.VK_ALT);
//        robot.keyPress(KeyEvent.VK_N);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_ALT);
//        robot.keyRelease(KeyEvent.VK_N);
//        Thread.sleep(200);
//        robot.keyPress(KeyEvent.VK_TAB);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        Thread.sleep(200);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        Thread.sleep(200);
//        robot.keyPress(KeyEvent.VK_DOWN);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_DOWN);
//        Thread.sleep(200);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        Thread.sleep(200);
//        String text = System.getProperty("user.dir")+"\\src\\test\\resources\\Records\\PROV_2019_89063.XML";
//        StringSelection stringSelection = new StringSelection(text);
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(stringSelection, stringSelection);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_V);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        robot.keyRelease(KeyEvent.VK_V);
//        Thread.sleep(200);
//
//        robot.keyPress(KeyEvent.VK_ENTER);
//        Thread.sleep(200);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//        Thread.sleep(2000);


        //need to add verification details
    }

    @When("User Select an analyst name from the list box and click in {string} butto")
    public void user_Select_an_analyst_name_from_the_list_box_and_click_in_butto(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("Verify analyst name added successfully")
    public void verify_analyst_name_added_successfully() {
        workFlowVerificationPage.verifyOperationSuccessMessageIsDisplayed();
    }

    @Then("Click on End analysis buttom")
    public void click_on_end_analysis_buttom() throws InterruptedException {
        workFlowPage.clickOnEndAnalysisButton();
        workFlowPage.clickOnGoBackButton();

    }

    @Then("Check the control section of the document")
    public void check_the_control_section_of_the_document() {
        workFlowPage.expandControlDataSection();
//        workFlowVerificationPage.switchToControlDataFrame();
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed(ImportApplicationVariables.agentValue);
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed("PARA RENUMERAR");

    }

    @When("User starts jurisprudence search by marginal value")
    public void userStartsJurisprudenceSearchByMarginalValue() throws InterruptedException, AWTException {
        searchPage.navigateToSearchInJurisprudence();
        searchPage.deleteExistingMarginalNumber();
        searchPage.clickOnSearchDropdownTwo("Document id (full");
        searchPage.searchDocument(CREATED_DOCUMENT);
        searchPage.clickSearchButton();
        Thread.sleep(3000);
    }

    @Given("User Login to the gcms application {string}")
    public void userLoginToTheGcmsApplication(String role) throws InterruptedException, AWTException, IOException {
        ImportApplicationVariables.setVariables();
        loginPage.accessURL(ImportApplicationVariables.loginURL);
        Thread.sleep(5000);
        loginPage.setLanguage();
        // loginPage.changeLanguageToEnglish();
        Thread.sleep(2000);
        if (role.equals("operator")) {
            loginPage.setUsername(ImportApplicationVariables.username);
        } else if (role.equals("Analyst")) {
            loginPage.setUsername(ImportApplicationVariables.analystUsername);
        } else {
            loginPage.setUsername("C290276");
        }
        loginPage.setPassword(ImportApplicationVariables.password);
        Thread.sleep(2000);
        loginPage.clickLoginButton();
        Thread.sleep(3000);
    }

    @When("User navigate to jurisprudence decisions pending inbox")
    public void userNavigateToJurisprudenceDecisionsPendingInbox() throws InterruptedException {
        verification.navigateToJurisprudencePendingInboxSection();
    }

    @Then("Verify control data section of the document after workflow")
    public void verify_control_data_section_of_the_document_after_workflow() throws InterruptedException {
        workFlowPage.expandControlDataSection();
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed(ImportApplicationVariables.agentFilterValue);
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed("Workflow");
    }

    @Then("Verify control data section of the document after assign citiator")
    public void verifyControlDataSectionOfTheDocumentAfterAssignCitiator() throws InterruptedException {
        verification.navigateToControlSectionWorkFlow(CREATED_DOCUMENT);
        workFlowPage.expandControlDataSection();
//        workFlowVerificationPage.switchToControlDataFrame();
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed(ImportApplicationVariables.citatorValue);
    }

    @Then("Verify control data section of the document after editing the document")
    public void verifyControlDataSectionOfTheDocumentaftereditingthedocument() throws InterruptedException {
//        verification.navigateToControlSectionWorkFlow(CREATED_DOCUMENT);
        workFlowPage.expandControlDataSection();
//        workFlowVerificationPage.switchToControlDataFrame();
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed("PARA ANALIZAR");

    }

    @When("User select Workflow Assign text to analyst")
    public void userSelectWorkflowAssignTextToAnalyst() throws InterruptedException {
        searchPage.navigateToWorkflowAssignTextToAnalyst();

    }

    @And("User Select an analyst name from the list box and click in assign button")
    public void userSelectAnAnalystNameFromTheListBoxAndClickInAssignButton() throws InterruptedException {
        workFlowVerificationPage.navigateToPreSelectionAndAssignAgentValue(ImportApplicationVariables.agentFilterValue);

    }

    @Then("Verify control data section of the document after assigning analyst")
    public void verifyControlDataSectionOfTheDocumentAfterAssigningAnalyst() throws InterruptedException {
        verification.navigateToControlSectionWorkFlow(CREATED_DOCUMENT);
        workFlowPage.expandControlDataSection();
//        workFlowVerificationPage.switchToControlDataFrame();
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed("ANALIZANDO");

    }

    @Then("Check the control section of the document after assigning analyst")
    public void checkTheControlSectionOfTheDocumentAfterAssigningAnalyst() throws InterruptedException {
        verification.navigateToControlSectionWorkFlow(CREATED_DOCUMENT);
        workFlowPage.expandControlDataSection();
//        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed("OTA TGAC-BOT");
    }

    @Then("Verify end of document display page should display End preselection button")
    public void verifyEndOfDocumentDisplayPageShouldDisplayEndPreselectionButton() {
        workFlowVerificationPage.verifyEndOfTheDocumentEndPreSelectionIsDisplayed();
    }

    @And("Click on add practice area and update the values")
    public void clickOnAddPracticeAreaAndUpdateTheValues() throws InterruptedException, AWTException {
        workFlowPage.clickOnAddPracticeArea();
        workFlowVerificationPage.clickOnAreaValueEllipseAndSelectTheValueFromTheList(ImportApplicationVariables.areaValue);
        workFlowVerificationPage.clickOnAnalistaValueEllipseAndSelectTheValueFromTheList(ImportApplicationVariables.analistaValue);
        workFlowPage.selectPrincipalDropDownValue();
        workFlowPage.clickOnOkButton();
        Thread.sleep(7000);
    }

    @Then("Verify control data section of the document after assign publication marginal number")
    public void verifyControlDataSectionOfTheDocumentAfterAssignPublicationMarginalNumber() throws InterruptedException {
        verification.navigateToControlSectionWorkFlow(CREATED_DOCUMENT);
        workFlowPage.expandControlDataSection();
        workFlowVerificationPage.verifyAssignedAgentValueIsDisplayed("FINALIZADO");
    }
}
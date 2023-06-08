package com.epam.stepdefinitions;

import com.epam.pages.actions.GCMSJurisprudenceSearchPage;
import com.epam.pages.actions.GCMSJurisprudenceTopicPage;
import com.epam.pages.actions.GcmsUploadingMetadataPage;
import com.epam.pages.actions.GCMSWinScpServerConnectionPage;
import com.epam.pages.verifications.GCMSJurisprudenceSearchPageVerification;
import com.epam.pages.verifications.GcmsUploadingMetadataVerificationPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.cucumber.java.en.*;

import java.awt.*;
import java.io.IOException;

public class UploadMetaDataStepDefTest extends BaseStepDefTest {
    GcmsUploadingMetadataPage uploadingMetadataPage = new GcmsUploadingMetadataPage(getDriver());
    GcmsUploadingMetadataVerificationPage uploadingMetadataVerificationPage = new GcmsUploadingMetadataVerificationPage(getDriver());
    GCMSJurisprudenceSearchPage searchPage = new GCMSJurisprudenceSearchPage(getDriver());
    GCMSJurisprudenceSearchPageVerification searchVerification = new GCMSJurisprudenceSearchPageVerification(getDriver());
    GCMSJurisprudenceTopicPage topicPage = new GCMSJurisprudenceTopicPage(getDriver());


    ChannelSftp channelSftp;
    boolean flag;

    @When("Click on administration tab")
    public void click_on_administration_tab() throws InterruptedException {
        uploadingMetadataPage.clickAdministrationTab();
        Thread.sleep(5000);
    }

    @When("Click on Process management link and navigate to search link")
    public void click_on_Process_management_link_and_navigate_to_search_link() {
        uploadingMetadataVerificationPage.navigateToSearchLink();
    }

    @When("User update all dropdown values under process search area")
    public void user_update_all_dropdown_values_under_process_search_area() throws InterruptedException, AWTException {
        uploadingMetadataVerificationPage.enterDropDownValuesOfContentTypeDropDown("All Content Types");
        Thread.sleep(6000);
        uploadingMetadataVerificationPage.enterDropDownValuesOfTypeDropDown("Load");
        uploadingMetadataVerificationPage.enterDropDownValuesOfSubTypeDropDown("GCMS Metadata Load");
        uploadingMetadataVerificationPage.enterDropDownValuesOfProcessDropDown("SCHEDULED");
        Thread.sleep(3000);
    }

    @When("Click on search button")
    public void click_on_search_button() {
        uploadingMetadataPage.clickSearchButton();
    }

    @Then("Verify display of all scheduled process and search for the upload document")
    public void verify_display_of_all_scheduled_process_and_search_for_the_upload_document() throws InterruptedException {
        Thread.sleep(3000);
        uploadingMetadataVerificationPage.searchForUploadDocument();

    }

    @When("User update the contents of myPaths textbox in the myPaths textbox.")
    public void user_update_the_contents_of_myPaths_textbox_in_the_myPaths_textbox() throws InterruptedException {
        uploadingMetadataVerificationPage.updateContentXpath("/"+ImportApplicationVariables.metadataInputFolder);
    }

    @When("User update the content of logdirtextbox path to ftpserver path")
    public void user_update_the_content_of_logdirtextbox_path_to_ftpserver_path() throws InterruptedException {
        Thread.sleep(3000);
        uploadingMetadataVerificationPage.updateContentLogDirPath("/"+ImportApplicationVariables.metadataOutputFolder);
    }

    @Then("Once updations are done click on update button")
    public void once_updations_are_done_click_on_update_button() throws InterruptedException {
        uploadingMetadataPage.clickUpdateButton();
    }

    @Then("click on the Execute link of the MetadataLoadProcess scheduled process.")
    public void click_on_the_link_of_the_MetadataLoadProcess_scheduled_process() {
        uploadingMetadataPage.clickExecuteLink(ImportApplicationVariables.metadataLoadProcessFile);

    }

    @Then("Check Metadata Loaded from sftp server")
    public void checkMetadataLoadedFromSftpServer() throws IOException, JSchException, SftpException, InterruptedException {
        ImportApplicationVariables.setVariables();
        Thread.sleep(20000);
        if (GCMSWinScpServerConnectionPage.findFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataLoadedFolder)) {
            System.err.println("File Exits in the metaData loaded folder");
            this.flag = true;
        } else if (GCMSWinScpServerConnectionPage.findFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataRejectedFolder)) {
            System.err.println("File Exits in the metaData rejected folder");
            this.flag = false;
        }
    }

    @And("User verify load was successful and document is displayed under resultset")
    public void userVerifyLoadWasSuccessfulAndDocumentIsDisplayedUnderResultset() throws InterruptedException, AWTException {
        searchVerification.clickOnWipModuleAndNavigateToJurisprudencePage(ImportApplicationVariables.metadataFileName);
        searchPage.clickDocument(ImportApplicationVariables.documentName);
        Thread.sleep(1000);
        searchPage.clickDeleteButton();
        Thread.sleep(1000);
        topicPage.clickOnYesButton();
        Thread.sleep(1000);
        searchPage.verifyDocumentIsPresent(ImportApplicationVariables.documentName, "Deleted");

    }

    @Given("User connects to the SFTP server for loading metadata")
    public void userConnectsToTheSFTPServerForLoadingMetadata() throws IOException, JSchException {
        ImportApplicationVariables.setVariables();
        ChannelSftp channelSftp = GCMSWinScpServerConnectionPage.setup(ImportApplicationVariables.serverName);
        this.channelSftp = channelSftp;
    }

    @And("User places the input file in the inbox folder")
    public void userPlacesTheInputFileInTheInboxFolder() throws InterruptedException, JSchException, IOException, SftpException {
        if (GCMSWinScpServerConnectionPage.findFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataLoadedFolder)) {
            GCMSWinScpServerConnectionPage.deleteFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataLoadedFolder);
        } else if (GCMSWinScpServerConnectionPage.findFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataRejectedFolder)) {
            GCMSWinScpServerConnectionPage.deleteFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataRejectedFolder);
        } else if (GCMSWinScpServerConnectionPage.findFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataAcceptedFolder)) {
            GCMSWinScpServerConnectionPage.deleteFile(ImportApplicationVariables.serverName, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataAcceptedFolder);
        }
        GCMSWinScpServerConnectionPage.putFile(channelSftp, ImportApplicationVariables.fileName, ImportApplicationVariables.metadataInputFolder);
        Thread.sleep(10000);
    }


    @And("User deleted the document if already existing")
    public void userDeletedTheDocumentIfAlreadyExisting() throws InterruptedException, AWTException {
        searchVerification.clickOnWipModuleAndNavigateToJurisprudencePage(ImportApplicationVariables.metadataFileName);
        if (searchPage.verifyDocumentIsPresent(ImportApplicationVariables.documentName, "Presented")) {
            searchPage.clickDocument(ImportApplicationVariables.documentName);
            Thread.sleep(1000);
            searchPage.clickDeleteButton();
            Thread.sleep(1000);
            topicPage.clickOnYesButton();
            Thread.sleep(1000);
            searchPage.verifyDocumentIsPresent(ImportApplicationVariables.documentName, "Deleted");
        }
    }
}
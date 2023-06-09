package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GcmsCoreDocumentWorkFlowPage extends PageWrapper {
    private static final String controlDataSection = "xpath -> //*[@id='clicker_datosControl']";
    private static final String deleteWFButton = "xpath -> //input[@id='btnDeleteWF']";
    private static final String restartProcess = "xpath -> //input[@id='startProcess']";
    private static final String loadOrginalTextButton = "xpath -> //input[@id='btnLoadOrgininalText']";
    private static final String textSection = "xpath -> //img[@id='clicker_contenidoTextos']";
    private static final String jurisprudenceLink = "xpath -> //a[@id='itemTextLink10']";
    private static final String workflowLink = "xpath -> //a[@id='itemTextLink18']";
    private static final String assignPreSelectionLink = "xpath -> //a[@id='itemTextLink19']";
    private static final String ellipseButton = "xpath -> //input[@id='btnFilter' or @value='...']";
    private static final String topicValueFilter = "xpath -> //input[@id='txtFilter']";
    private static final String selectAgentValue = "xpath -> //a[@id='lnk___']";
    private static final String assignButton = "xpath -> //input[@type='button' and contains(@onclick,'enviarFormulario();')]";
    private static final String okButton = "xpath -> //input[@type='button' and contains(@onclick,'disabled') or @id='btnEdit' or @type='submit']";
    private static final String logoffButton = "xpath -> //a[@id='lnkHeaderLogOut']";
    private static final String editButton = "xpath -> //input[@id='btnEdit']";
    private static final String endPreSelectionButton = "xpath ->  //input[@id='btnEndPreSelection']";
    private static final String goBackButton = "xpath -> //input[@type='button' and contains(@onclick,'javascript:doCloseR();') or @id='btnBack']";
    private static final String exportButton = "xpath -> //td[contains(text(),'Texto Finalizado') or contains(text(),'Finalized text')]/following::a[6]";
    private static final String selectAnalistaValue = "xpath -> //td[text()='OTA TGAC-BOT [ EXPURGADOR ] ']/..//following::a";
    private static final String importButton = "xpath ->  //input[@type='button' and  contains(@onclick,'javascript:importTexto')]";
    private static final String endCitiation = "xpath -> //input[@id='btnEndCitationActivity']";
    private static final String endAnalysis = "xpath -> //input[@id='btnEndAnalysis']";
    private static final String renumberingButton = "xpath -> //input[@type='button' and  @onclick='javascript:submitValor()']";
    private static final String NMATextField = "xpath -> //input[@id='nma']";
    private static final String NMNTextField = "xpath -> //input[@id='nmn']";
    private static final String decisionsLink = "xpath -> //a[@id='itemTextLink11']";
    private static final String resultSetsLink = "xpath -> //a[@id='itemTextLink13']";
    private static final String documentLink = "xpath -> //a[contains(text(),'%s')]";
    private static final String fileBrowseButton = "xpath -> //input[@name='fichero']";
    private static final String cupFileBrowseButton = "xpath -> //input[@name='ficheroTroceado']";
    private static final String agentValue = "xpath -> //td[contains(text(),'%s')]";
    private static final String successMessage = "xpath -> //b[@id='successMessage']";
    private static final String importFilePath = "xpath -> //input[@id='myFile']";
    private static final String messageHeader = "xpath -> //td[@class='messageHeader']";

    public String getFileBrowseButton() {
        return formatLocator(fileBrowseButton);
    }

    public String getImportFilePath() {
        return formatLocator(importFilePath);
    }

    public String getMessageHeader() {
        return formatLocator(messageHeader);
    }

    public String getImportButton() {
        return formatLocator(importButton);
    }

    public String getAgentValue(String value) {
        return formatLocator(String.format(agentValue, value));
    }

    public String getSuccessMessage() {
        return formatLocator(successMessage);
    }

    public String getEndPreSelectionButton() {
        return formatLocator(endPreSelectionButton);
    }

    public String getCutUpFileBrowseButton() {
        return formatLocator(cupFileBrowseButton);
    }

    public GcmsCoreDocumentWorkFlowPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void expandControlDataSection() {
        clickElementUsingJS(controlDataSection, "control data section");
    }

    public void clickOnDeleteWorkFlowButton() {
        clickElementUsingJS(deleteWFButton, "delete work flow");
    }

    public void clickOnRestartCompleteProcess() {
        clickElementUsingJS(restartProcess, "restart complete process");
    }

    public void clickOnLoadOrginalTextButton() {
        clickElementUsingJS(loadOrginalTextButton, "Load orginal text button");
    }

    public void clickOnTextSection() {
        clickElementUsingJS(textSection, "text section");
    }

    public void navigateToWorkFlowPreSelectionPage() throws InterruptedException {
        clickElementUsingJS(jurisprudenceLink, "jurisprudenceLink");
        clickElementUsingJS(workflowLink, "workflow");
        clickElementUsingJS(assignPreSelectionLink, "Assign to preselection");
        Thread.sleep(3000);
    }

    public void clickOnEllipseButton() {
        clickElementUsingJS(ellipseButton, "EllipseButton");
    }

    public void enterAgentValueFromTheList(String value) {
        setTextUsingJS(topicValueFilter, "TopicValueInputBox", value);
    }

    public void clickOnSearchedAgentValue() {
        clickElementUsingJS(selectAgentValue, "Select Agent Value");
    }

    public void clickOnAssignButton() {
        clickElementUsingJS(assignButton, "Assign button");
    }

    public void clickOnOkButton() {
        clickElementUsingJS(okButton, "ok button");
    }

    public void clickOnLogoffButton() {
        clickElementUsingJS(logoffButton, "logoff button");
    }

    public void clickOnEditButton() {
        clickElementUsingJS(editButton, "Edit button");
        System.err.println("Edit");
    }

    public void clickOnEndPreSelectionButton() throws InterruptedException {
        clickElementUsingJS(endPreSelectionButton, "End pre selection");
        Thread.sleep(2000);
    }

    public void clickOnGoBackButton() throws InterruptedException {
        clickElementUsingJS(goBackButton, "Go Back Button");
        Thread.sleep(2000);
    }

    public void clickOnAnalysisValueEllipse() {
        WebElement element = webDriver.findElement(By.cssSelector("input#nivel + input"));
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

    public void clickOnQualityEllipse() {
        WebElement element = webDriver.findElement(By.cssSelector("input#calidad + input"));
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

    public void clickOnExportButton() {
        clickElementUsingJS(exportButton, "Export button");
    }

    public void clickOnAddPracticeArea() {
        WebElement element = webDriver.findElement(By.name("button.annadirAreas"));
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

    public void clickOnAreaValueEllipse() {
        WebElement element = webDriver.findElement(By.cssSelector("input[emsg*='Practice Area'] + input"));
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

    public void clickOnAnalistaValueEllipse() {
        WebElement element = webDriver.findElement(By.cssSelector("input[id*='.analystName'] + input"));
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

    public void selectPrincipalDropDownValue() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        WebElement e = webDriver.findElement(By.cssSelector("select[id*='.principal']"));
        e.sendKeys("");
        String fine = "Y";
        fine = fine.toUpperCase();
        char[] c = fine.toCharArray();
        for (char value : c) {
            int keyCode = (int) value;
            if (keyCode == 32) {
                robot.keyPress(KeyEvent.VK_SPACE);
                Thread.sleep(200);
                robot.keyRelease(KeyEvent.VK_SPACE);
            } else
                robot.keyPress(keyCode);
            Thread.sleep(200);
            robot.keyRelease(keyCode);
        }
    }

    public void selectAnalistaValue() {
        clickElementUsingJS(selectAnalistaValue, "select analista value");
    }

    public void clickOnImportButton() {
        clickElementUsingJS(importButton, "Import Button");
    }

    public void clickOnEndCitiation() {
        clickElementUsingJS(endCitiation, "End Citiation Button");
    }

    public void clickOnEndAnalysisButton() {
        clickElementUsingJS(endAnalysis, "End Analysis Button");
    }

    public void clickOnRenumberingButton() {
        clickElementUsingJS(renumberingButton, "Renumbering Button");
    }

    public void enterNMAValue(String value) {
        setTextUsingJS(NMATextField, "NMA value will be entered", value);
    }

    public void enterNMNValue(String value) {
        setTextUsingJS(NMNTextField, "NMN value will be entered", value);
    }

    public void navigateToJurisprudenceControlDataSection() throws InterruptedException {
        clickElementUsingJS(jurisprudenceLink, "jurisprudenceLink");
        clickElementUsingJS(decisionsLink, "decisionsLink");
        clickElementUsingJS(resultSetsLink, "resultSetLink");
        Thread.sleep(3000);
    }

    public void clickDocument(String value) {
        clickElementUsingJS(String.format(documentLink, value), "documentId");
    }

}



     


    

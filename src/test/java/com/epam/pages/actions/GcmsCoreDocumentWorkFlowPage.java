package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
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
    private static final String textButton = "xpath ->  //input[@id='btnText']";
    private static final String endPreSelectionButton = "xpath ->  //input[@id='btnEndPreSelection']";
    private static final String goBackButton = "xpath -> //input[@type='button' and contains(@onclick,'javascript:doCloseR();') or @id='btnBack']";
    private static final String analysisValueEllipse = "xpath -> //input[@id='nivel']/following-sibling::input[@value='...']";
    private static final String qualityEllipse = "xpath -> //input[@id='calidad']/following-sibling::input[@value='...']";
    private static final String exportButton = "xpath -> //td[contains(text(),'Texto Finalizado') or contains(text(),'Finalized text')]/following::a[6]";
    //"//td[contains(text(),'Finalized text')]/following::a[6]";
    private static final String addPracticeArea = "xpath -> //input[@name='button.annadirAreas']";
    private static final String areaValueEllipse = "xpath -> //input[@id='resDTO.resAreasObj[0].texto']/following-sibling::input[@value='...']";
    private static final String analistaValueEllipse = "xpath -> //input[@id='resDTO.resAreasObj[0].analystName']/following-sibling::input[@value='...']";
    private static final String principalDropDown = "xpath -> //select[@id='resDTO.resAreasObj[0].principal']";
    private static final String selectAnalistaValue= "xpath -> //td[text()='OTA TGAC-BOT [ EXPURGADOR ] ']/..//following::a";
    private static final String importButton = "xpath ->  //input[@type='button' and  contains(@onclick,'javascript:importTexto')]";
    private static final String endCitiation = "xpath -> //input[@id='btnEndCitationActivity']";
    private static final String endAnalysis = "xpath -> //input[@id='btnEndAnalysis']";
    private static final String renumberingButton = "xpath -> //input[@type='button' and  @onclick='javascript:submitValor()']";
    private static final String NMATextField = "xpath -> //input[@id='nma']";
    private static final String NMNTextField = "xpath -> //input[@id='nmn']";
    private static final String removePracticeArea = "xpath -> //input[@name='resDTO.resAreasObj[0].AAA' and @alt='X']";
    private static final String analystLink= "xpath -> //a[@id='itemTextLink20']";

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

    public void navigateToWorkflowAssignToAnalyst() throws InterruptedException {
        clickElementUsingJS(jurisprudenceLink, "jurisprudenceLink");
        clickElementUsingJS(workflowLink, "workflowLink");
        clickElementUsingJS(analystLink, "analystLink");
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
        scrollToBottomOfPage();
        clickElementUsingJS(editButton, "Edit button");
        System.err.println("Edit");
        scrollToBottomOfPage();
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
        clickElementUsingJS(analysisValueEllipse, "Analysis Value ellipse button");
    }

    public void clickOnQualityEllipse() {
        clickElementUsingJS(qualityEllipse, "Quality Value ellipse button");
    }

    public void clickOnExportButton() throws InterruptedException {
    	//return webDriver.findElement(By.xpath("//td[contains(text(),'Texto Finalizado') or contains(text(),'Finalized text')]/following::a[6]"));
        clickElementUsingJS(exportButton, "Export button");

       // switchToDefaultContent();
        // Thread.sleep(2000);

       //switchToNewWindow();
      // webDriver.manage().window().maximize();
    }

    public void clickOnAddPracticeArea() {
        clickElementUsingJS(addPracticeArea, "Add Practice Area");
    }
    public void clickOnAreaValueEllipse() {
        clickElementUsingJS(areaValueEllipse, "Area Value ellipse button");
    }
    public void clickOnAnalistaValueEllipse() {
        clickElementUsingJS(analistaValueEllipse, "Analista Value ellipse button");
    }
    public void selectPrincipalDropDownValue() throws AWTException, InterruptedException {
        //clickElementUsingJS(principalDropDown,"principal drop down value");
        //selectItemFromDropdownByValue("S",principalDropDown,"yes button");
        Robot robot = new Robot();
        WebElement e = getElement(formatLocator(principalDropDown));
        e.sendKeys("");
        String fine = "Y";
        fine = fine.toUpperCase();
        char[] c = fine.toCharArray();
        for (int k = 0; k < c.length; k++) {
            int keyCode = (int) c[k];
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
    public void selectAnalistaValue()
    {
        clickElementUsingJS(selectAnalistaValue,"select analista value");
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
        setTextUsingJS(NMATextField, "NMA value will be entered",value);
    }
    public void enterNMNValue(String value) {
        setTextUsingJS(NMNTextField, "NMN value will be entered",value);
    }
    public void setRemovePracticeArea(){
        clickElementUsingJS(removePracticeArea,"remove practice area");
    }
}



     


    

package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GCMSJurisprudenceTopicPage extends PageWrapper {

    private static final String topicIframe = "xpath -> //iframe[@id='listaMateriaSubmateria']";
    private static final String topicNewButton = "xpath -> //input[@id='btnNewTopic']";
    private static final String topicValueFilter = "xpath -> //input[@id='txtFilter']";
    private static final String topicEllipsiButton = "xpath -> //input[@id='btnFilter']";
    private static final String selectTopicValue = "xpath -> //a[@id='lnk___']";
    private static final String subTopicNewButton = "xpath -> //input[@id='btnNewSubTopic']";
    private static final String topic = "xpath -> //input[@name='listaMateria']/parent::td";
    private static final String subTopic = "xpath -> //input[@name='listaSubmateria']/parent::td";
    private static final String subTopicDeleteButton = "xpath -> //input[@id='btnDeleteSubtopic']";
    private static final String yesButton = "xpath -> //input[@id='btnSubmit']";
    private static final String addNewUsingCodeButton = "xpath -> //input[@id='btnNewUsingCode']";
    private static final String addNewUsingCodeSearch = " xpath -> //input[@id='listaSubmaterias']";
    private static final String transferButton = " xpath -> //input[@id='btnSubmit']";
    private static final String deleteTopic = "xpath -> //input[@id='btnDeleteTopic']";
    private static final String okButton = "xpath -> //input[@type='button' and contains(@onclick,'disabled') or @id='btnEdit' or @type='submit']";
    private static final String removePracticeArea = "xpath -> //input[@name='resDTO.resAreasObj[0].AAA' and @alt='X']";

    public GCMSJurisprudenceTopicPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTopic(String... formatArgs) {
        return formatLocator(topic, formatArgs);
    }

    public String getSubTopic(String... formatArgs) {
        return formatLocator(subTopic, formatArgs);
    }

    public void setTopicValueFilter(String value) {
        setTextUsingJS(topicValueFilter, "TopicValueInputBox", value);
    }

    public void switchToTopicFrame() throws InterruptedException {
//        switchToIframeByElement(topicIframe);
        webDriver.switchTo().frame("listaMateriaSubmateria");
        Thread.sleep(7000);
    }

    public void clickTopicNewButton() {
        clickElementUsingJS(topicNewButton, "New");
    }

    public void clickEllipsiButton() {
        clickElementUsingJS(topicEllipsiButton, "...");
    }

    public void clickNewButton() throws InterruptedException {
        switchToTopicFrame();
        clickTopicNewButton();
    }

    public void clickOnSearchedValue() {
        clickElementUsingJS(selectTopicValue, "Select topic value");
    }

    public void clickOnNewButtonOfSubTopicSection() {
        clickElementUsingJS(subTopicNewButton, "new button sub topic value");
    }

    public void clickOnAddNewCodeButton() throws InterruptedException {
        switchToTopicFrame();
        clickElementUsingJS(addNewUsingCodeButton,"Add new using code");
    }

    public void clickOnYesButton() throws InterruptedException {
        clickElementUsingJS(yesButton, "Yes button" );
        switchToParentWindow();
        Thread.sleep(2000);
    }

    public void clickYesButton() {
        clickElementUsingJS(yesButton, "Yes button");
    }

    public void clickOnTransferButton() {
        clickElementUsingJS(transferButton, "Transfer Button" );
        switchToParentWindow();
    }

    public void clickOnTopicDeleteButton() {
        clickElementUsingJS(deleteTopic, "topic delete button" );
        switchToNewWindow();
    }

    public void clickAnalysisDataLink() {
        WebElement element = webDriver.findElement(By.id("clicker_datosAnalisisFichas"));
        JavascriptExecutor executor = (JavascriptExecutor)webDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    private void setSubTopicValue(String value) {
        setTextUsingJS(topicValueFilter, "TopicValueInputBox", value);
    }

    public void searchTopicValue() {
        switchToNewWindow();
        setTopicValueFilter(ImportApplicationVariables.topic);
        clickEllipsiButton();
    }

    public void selectTopicValue() {
        clickOnSearchedValue();
        switchToParentWindow();
    }

    public void clickSubTopicNewButton() throws InterruptedException {
        switchToTopicFrame();
        clickOnNewButtonOfSubTopicSection();
    }

    public void searchSubTopicValue() {
        switchToNewWindow();
        setSubTopicValue(ImportApplicationVariables.subTopic);
        clickEllipsiButton();
    }

    public void selectSubTopicValue() {
        clickOnSearchedValue();
        switchToParentWindow();
    }

    public void deleteTopicAndSubTopicEntries() throws InterruptedException {
//        clickSubTopicDeleteButton();
        List<WebElement> entries = getElements(subTopic);
        for (int i = 0; i < entries.size(); i++) {
            clickElementUsingJS(subTopic, "Sub Topic Entry");
            clickElementUsingJS(subTopicDeleteButton, "sub topic delete button");
            switchToNewWindow();
            clickYesButton();
            switchToParentWindow();
            switchToTopicFrame();
        }
    }

    public void enterTopicSuTopicValuesUsingCode() {
        switchToNewWindow();
        setTextUsingJS(addNewUsingCodeSearch, "add new using code search", ImportApplicationVariables.topicSubtopicCode);
    }


    public void setRemovePracticeArea(){
        clickElementUsingJS(removePracticeArea,"remove practice area");
    }


    public void clickOnOkButton() {
        clickElementUsingJS(okButton, "ok button");
    }

}

package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import com.epam.setup.systemsettings.ImportApplicationVariables;

import org.openqa.selenium.*;

public class GCMSClassificationEntriesPage extends PageWrapper {

    private static final String analysisDataLink = "xpath -> //img[@id='clicker_datosAnalisisFichas']";
    private static final String classificationEntriesIframe = "xpath -> //iframe[@id='fichas_ic']";
    private static final String classificationEntriesTable = "xpath -> //table[@id='fichasAlfabeticas']";
    private static final String newButton = "xpath -> //table[@id='fichasAlfabeticas']//input[@value='New'or@value='Nueva'or@value='Nova']";
    private static final String thesauriEllipsi = "xpath -> //input[@id='tesauro']/following-sibling::input";
    private static final String thesauriSearchBox = "xpath -> //input[@id='txtFilter']";
    private static final String thesauriBtnFilter = "xpath -> //input[@id='btnFilter']";
    private static final String thesauriTerm = "xpath -> //img[@id='lnk___']";
    private static final String topicEllipsi = "xpath -> //table[@id='tabla_arbol_fic']//input";
    private static final String topicIframe = "xpath -> //iframe[@name='iframeArbolFichaIC']";
    private static final String topicValue = "xpath -> //input[@id='patron']";
    private static final String topicFilterButton = "xpath -> //input[@id='btnSearch']";
    private static final String topicTerm = "xpath -> //a[@id='sel1']";
    private static final String analystEllipsi = "xpath -> //input[@id='btnAnalystList']";
    private static final String commonIndexTextFilter = "xpath -> //input[@id='txtFilter']";
    private static final String commonIndexFilterButton = "xpath -> //input[@id='btnFilter']";
    private static final String commonIndexTerm = "xpath -> //img[@id='lnk___']";
    private static final String okButton = "xpath -> //input[@id='btnOk']";
    private static final String goBackButton = "xpath -> //input[@id='btnGoBack']";
    private static final String newClassificationEntry = "xpath -> //table[@id='fichasAlfabeticas']//td[contains(text(),'%s')]";

    public GCMSClassificationEntriesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getNewClassificationEntry(String value, String... formatArgs) {
        return formatLocator(String.format(newClassificationEntry,value), formatArgs);
    }

    public String getClassificationEntriesIframe(String... formatArgs) {
        return formatLocator(classificationEntriesIframe, formatArgs);
    }

    public void setThesauriTextFilter(String value) {
        setTextUsingJS(thesauriSearchBox, "Thesauri Filter", value);
    }

    public void setTopicValue(String value) {
        setTextUsingJS(topicValue, "Topic Value", value);
    }

    public void setCommonIndexTerm(String value) {
        setTextUsingJS(commonIndexTextFilter, "Common Index Term", value);
    }

    public void clickAnalysisDataLink() {
        clickElementUsingJS(analysisDataLink, "Analysis Data Section");
    }

    public void clickNewButton() {
        clickElementUsingJS(newButton, "New Classification Entry");
    }

    public void clickThesauriEllipsi() {
        clickElementUsingJS(thesauriEllipsi, "Ellipsi...");
    }

    public void clickThesauriBtnFilter() {
        clickElementUsingJS(thesauriBtnFilter, "Button Filter");
    }

    public void clickThesauriTerm() {
        clickElementUsingJS(thesauriTerm, "Thesauri Term");
    }

    public void clickTopicEllipsi() {
        clickElementUsingJS(topicEllipsi, "Topic...");
    }

    public void clickTopicSearchButton() {
        clickElementUsingJS(topicFilterButton, "Search Button");
    }

    public void clickAnalystEllipsi() {
        clickElementUsingJS(analystEllipsi, "Analyst...");
    }

    public void clickCommonIndexFilterButton() {
        clickElementUsingJS(commonIndexFilterButton, "Common Index...");
    }

    public void clickCommonIndexTerm() {
        clickElementUsingJS(commonIndexTerm, "Common Index Term");
    }

    public void clickOkButton() {
        scrollInView(okButton);
        clickElementUsingJS(okButton, "Ok");
    }

    public void clickGoBackButton() {
        scrollInView(goBackButton);
        clickElementUsingJS(goBackButton, "Go back");
    }

    public void selectRequiredTopicTerm() {
        clickElementUsingJS(topicTerm, "Select");
    }

    public void expandAnalysisDataSection() {
        if(ImportApplicationVariables.region.equals("GULF")){
            clickElementUsingJS("xpath -> //input[@id='text']","Text");
        }
        clickAnalysisDataLink();
    }

    public void moveToClassificationEntriesSection() {
        switchToIframeByElement(classificationEntriesIframe);
        scrollInView(classificationEntriesTable);
    }

    public void clickNewButton(String... formatArgs) throws InterruptedException {
        moveToClassificationEntriesSection();
        try {
            if (isElementDisplayed(getNewClassificationEntry(ImportApplicationVariables.fullMarginalId))) {
                deleteClassificationEntry();
                switchToIframeByElement(classificationEntriesIframe);
            }
        }
        catch (TimeoutException ignored) {
            scrollInView(newButton);
        }
        WebElement newBtn = getElement(newButton);
        javascriptExecutor.executeScript("arguments[0].click()",newBtn);
    }

    public void insertValuesInThesauriSection() {
        clickThesauriEllipsi();
        switchToNewWindow();
        setThesauriTextFilter(ImportApplicationVariables.thesauri);
        clickThesauriBtnFilter();
        clickThesauriTerm();
        switchToParentWindow();
    }

    public void insertValuesInTopicSection() {
        switchToIframeByElement(topicIframe);
        clickTopicEllipsi();
        switchToNewWindow();
        setTopicValue(ImportApplicationVariables.classificationEntryTopic);
        clickTopicSearchButton();
        selectRequiredTopicTerm();
        switchToParentWindow();
    }

    public void insertValuesInAnalystSection() {
        clickAnalystEllipsi();
        switchToNewWindow();
        setCommonIndexTerm(ImportApplicationVariables.commonIndexTerm);
        clickCommonIndexFilterButton();
        clickCommonIndexTerm();
        switchToParentWindow();
    }

    public void deleteClassificationEntry() {
        try{
            clickElementUsingJS("xpath -> //a[text()='Delete' or text()='Apagar' or text()='Borrar']", "Delete");
        }catch (StaleElementReferenceException e){
            WebElement delete = getElement("xpath -> //a[text()='Delete' or text()='Apagar' or text()='Borrar']");
            scrollInView("xpath -> //a[text()='Delete' or text()='Apagar' or text()='Borrar']");
            javascriptExecutor.executeScript("arguments[0].click()",delete);
        }
        switchToNewWindow();
        clickElementUsingJS("xpath -> //input[@id='btnSubmit']", "Yes");
        switchToParentWindow();
    }

}

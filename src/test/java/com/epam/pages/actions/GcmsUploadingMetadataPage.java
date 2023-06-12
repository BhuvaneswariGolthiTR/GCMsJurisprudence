package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GcmsUploadingMetadataPage extends PageWrapper {
    private static final String administrationLink = "xpath -> //a[@id='lblAdmin']";
    private static final String processManagement = "xpath -> //a[@id='itemTextLink1']";
    private static final String searchLink = "xpath -> //a[@id='itemTextLink2']";
    private static final String processDropDown = "xpath -> //select[@id='estadoProceso']";
    private static final String searchButton = "xpath -> //input[@id='btnConsultar']";
    private static final String myPathTextValue = "xpath -> //input[@id='myPaths']";
    private static final String logDirTextValue = "xpath -> //input[@id='logDir']";
    private static final String updateButton = "xpath -> //input[@id='update']";
    private static final String executeLink = "xpath -> //td[text()='%s']/following::td/a[@class='referencia'][3]";
    public static final String nextButton = "xpath -> //input[@id='next']";
    protected static final String testingLink = "xpath -> //td[text()='TESTING_Cases_metadata_load']/following::td/a[@class='referencia'][1]";
    private static final String contentTypeDropdown = "xpath -> //select[@id='ambito']";
    private static final String typeDropdown = "xpath -> //select[@id='tipo']";
    private static final String subTypeDropdown = "xpath -> //select[@id='subtipo']";
    private static final String tableValues = "xpath -> //td[@class='ContentHeader']";
    private static final String fileName = "xpath -> //td[text()='TESTING_Cases_metadata_load']";

    public GcmsUploadingMetadataPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTableValues(String... formatArgs) {
        return formatLocator(tableValues, formatArgs);
    }

    public String getFileName(String... formatArgs) {
        return formatLocator(fileName, formatArgs);
    }

    public void clickAdministrationTab() {
        clickElementUsingJS(administrationLink, "administration Link");
    }


    public void clickProcessManagement() {
        clickElementUsingJS(processManagement, "process management");
    }

    public void clickSearchLink() {
        clickElementUsingJS(searchLink, "Search Link");
    }

    public void selectMetaDataDropdowns(String elementLocator, String option) throws AWTException {
        WebElement e = getElement(elementLocator);
        e.sendKeys("");
        Robot robot = new Robot();
        Select select = new Select(e);
        System.err.println("length*********" + select.getOptions().size());
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equals(option)) {
                System.err.println("got correct***");
                break;
            }
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }

    }

    public void clickContentTypeDropDown(String value) throws AWTException {
        selectMetaDataDropdowns(contentTypeDropdown, value);
    }

    public void clickTypeDropDown(String value) throws AWTException {
        selectMetaDataDropdowns(typeDropdown, value);
    }

    public void clickProcessDropDown(String value) throws AWTException {
        selectMetaDataDropdowns(processDropDown, value);
    }

    public void clickSubTypeDropDown(String value) throws AWTException {
        selectMetaDataDropdowns(subTypeDropdown, value);
    }

    public void clickSearchButton() {
        clickElementUsingJS(searchButton, "Search Button");
    }

    public void enterInputFolderPath(String value) {
        setTextUsingJS(myPathTextValue, "My path text value", value);
    }

    public void enterLogDirPath(String value) {
        setTextUsingJS(logDirTextValue, "My log text value", value);
    }

    public void clickUpdateButton() throws InterruptedException {
        clickElementUsingJS(updateButton, "Update Button");
        Thread.sleep(4000);
        switchToParentWindow();
    }

    public void clickExecuteLink(String value) {
        clickElementUsingJS(String.format(executeLink, value), "Update Button");
    }

}

     


    

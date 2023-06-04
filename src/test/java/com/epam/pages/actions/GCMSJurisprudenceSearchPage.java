package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;

import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GCMSJurisprudenceSearchPage extends PageWrapper {
    private static final String jurisprudenceLink = "xpath -> //a[@id='itemTextLink10']";
    private static final String decisionsLink = "xpath -> //a[@id='itemTextLink11']";
    private static final String searchLink = "xpath -> //a[@id='itemTextLink12']";
    private static final String marginalDropdown = "xpath -> //select[@id='searchFields0']";
    private static final String marginalDropdownTwo = "xpath -> //select[@id='searchFields1']";
    private static final String clearSearchField = "xpath -> //input[@id='delete0']";
    private static final String searchField = "xpath -> //input[@id='consulta0']";
    private static final String searchButton = "xpath -> //input[@id='btnSearch']";
    private static final String marginalIdLink = "xpath -> //a[contains(text(),%s)]";
    private static final String documentID = "xpath -> //td[contains(text(),%s)]";
    private static final String documentLink = "xpath -> //a[contains(text(),'%s')]";
    private static final String wipModuleLink = "xpath -> //a[@id='lnkHeaderWIP']";
    private static final String deleteButton = "xpath -> //input[@id='btnDelete']";
    public boolean flag;

    public GCMSJurisprudenceSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getMarginalIdLink(String id,String... formatArgs){
        return formatLocator(String.format(marginalIdLink,id),formatArgs);
    }

    public String getDocumentID(String id,String... formatArgs){
        return formatLocator(String.format(documentID,id),formatArgs);
    }

    public String getSearchField(String...formatArgs){
        return formatLocator(searchField,formatArgs);
    }

    public void setSearchField(String value) throws AWTException {
        sendTextUsingRobot(searchField,"Search Field",value);
    }

    public void clickJurisprudenceLink(String... formatArgs){
        clickElementUsingJS(jurisprudenceLink,"JURISPRUDENCE",formatArgs);
    }

    public void clickDecisionsLink(String... formatArgs){
        clickElementUsingJS(decisionsLink,"Decisions",formatArgs);
    }

    public void clickSearchLink(String... formatArgs){
        clickElementUsingJS(searchLink,"Search",formatArgs);
    }

    public void clickClearSearchField() {
        clickElementUsingJS(clearSearchField, "Clear Search");
    }

    public void clickSearchButton() {
        clickElementUsingJS(searchButton,"Search Button");
    }

    public void clickMarginalIdLink() {
        clickElementUsingJS(getMarginalIdLink("'"+ ImportApplicationVariables.fullMarginalId+"'"),"Searched Document");
    }

    public void clickOnWipModule() {
        clickElementUsingJS(wipModuleLink, "wip module link");
    }

    public void clickDocument(String value) {
        clickElementUsingJS(String.format(documentLink, value), "documentId");
    }

    public void clickDeleteButton() {
        clickElementUsingJS(deleteButton, "delete");
        switchToNewWindow();
    }
    
    public void navigateToSearchInJurisprudence() {
        clickJurisprudenceLink();
        clickDecisionsLink();
        clickSearchLink();
    }

    public void selectDocumentIdFull() throws AWTException, InterruptedException {
        selectOptionFromDropDownUsingRobot(marginalDropdown,"Document id (full");
        selectOptionFromDropDownUsingRobot(marginalDropdownTwo,"Document id (full");
    }

    public void enterFullMarginalId(String fullMarginalId) throws AWTException, InterruptedException {
        Thread.sleep(3000);
        clickClearSearchField();
        setSearchField(fullMarginalId);
    }

    public void clickOnSearchDropdown(String value) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        WebElement e = getElement(formatLocator(marginalDropdown));
        e.sendKeys("");
        String fine = value;
        fine = fine.toUpperCase();
        char[] c = fine.toCharArray();
        for (int k = 0; k < c.length; k++) {
            int keyCode = (int) c[k];
            System.err.println("keycodes:" + keyCode + "its value" + c[k]);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }

    }

    public boolean verifyDocumentIsPresent(String value, String text) {
        if (webDriver.findElements(By.xpath(String.format("//a[contains(text(),'%s')]", value))).size() > 0) {
            if (text.equalsIgnoreCase("presented")) {
                System.err.println("Document is " + text);
            } else {
                System.err.println("Document is not " + text);
            }
            flag = true;

        } else {
            if (text.equalsIgnoreCase("presented")) {
                System.err.println("Document is not " + text);
            } else {
                System.err.println("Document is  " + text);
            }

            flag = false;
        }
        return flag;

    }

}

package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;

import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.WebDriver;

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

    public GCMSJurisprudenceSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getMarginalIdLink(String id,String... formatArgs){
        return formatLocator(String.format(marginalIdLink,id),formatArgs);
    }

    public String getDocumentID(String id,String... formatArgs){
        return formatLocator(String.format(documentID,id),formatArgs);
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
    
    public void navigateToSearchInJurisprudence() {
        clickJurisprudenceLink();
        clickDecisionsLink();
        clickSearchLink();
    }

    public void selectDocumentIdFull() throws AWTException, InterruptedException {
//        clickElementUsingJS(marginalDropdown,"First Dropdown");
        setTextUsingJS(marginalDropdown, "Complete Option", "NM");
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(4000);
        robot.keyRelease(KeyEvent.VK_DOWN);
//        setTextUsingJS(marginalDropdownTwo,"Complete Option","NM");
    }

    public void enterFullMarginalId(String fullMarginalId) throws AWTException, InterruptedException {
        Thread.sleep(3000);
        clickClearSearchField();
        setSearchField(fullMarginalId);
    }

}

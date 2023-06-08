package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GCMSJurisprudenceReportsPage extends PageWrapper {

    private static final String marginalDropdownOne = "xpath -> //select[@id='searchFields0']";
    private static final String marginalDropdownTwo = "xpath -> //select[@id='searchFields1']";
    private static final String year = "xpath -> //input[@id='consulta0']";
    private static final String searchOperator = "xpath -> //select[@id='searchOperators1']";
    private static final String number = "xpath -> //input[@id='consulta1']";
    private static final String searchButton = "xpath -> //input[@id='btnSearch']";
    private static final String reportsButton = "xpath -> //input[@id='btnReports']";
    private static final String reviewFormat = "xpath -> //td[@id='txtoriginal']/preceding-sibling::td/input";
    private static final String okButton = "xpath -> //input[@value='Ok' or @value='Aceitar']";
    private static final String jurisprudenceReports = "xpath -> //a[@id='itemTextLink15']";
    private static final String viewLink = "xpath -> (//a[text()='View' or text()='Visualizar'])[1]";//td[contains(text(),'Correct')]//following::a
    private static final String checkBoxesToDelete = "xpath -> //input[@id='botonSeleccionar']";
    private static final String discard = "xpath -> //input[@value='Discard marked' or @value='Excluir seleção']";

    public GCMSJurisprudenceReportsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectDocumentYear() throws AWTException, InterruptedException {
        selectOptionFromDropDownUsingRobot(marginalDropdownOne,"Document id (year");
    }

    public void selectDocumentNumber() throws AWTException, InterruptedException {
        selectOptionFromDropDownUsingRobot(marginalDropdownTwo,"Document id (number");
    }

    public void selectOperator() {
        setTextUsingJS(searchOperator, "Operator", "<=");
    }

    public void setNumber(String value) {
        setTextUsingJS(number, "Number Field", value);
    }

    public void clickSearchButton() {
        clickElementUsingJS(searchButton, "Search");
    }

    public void clickReportsButton() {
        scrollInView(reportsButton);
        clickElementUsingJS(reportsButton, "Reports");
    }

    public void clickReviewFormat() {
        clickElementUsingJS(reviewFormat, "Review Format Check Box");
    }

    public void clickOkButton() throws InterruptedException {
        clickElementUsingJS(okButton, "Ok");
        Thread.sleep(4000);
    }

    public void clickJurisprudenceReports() throws InterruptedException {
        Thread.sleep(3000);
        clickElementUsingJS(jurisprudenceReports, "Reports");
    }

    public void clickViewLink() {
        clickElementUsingJS(viewLink, "View");

    }

    public void clickCheckBoxToDelete() {
        clickElementUsingJS(checkBoxesToDelete, "[]");
    }

    public void clickDiscard() throws InterruptedException {
        clickElementUsingJS(discard, "Discard");
        Thread.sleep(5000);
    }

    public void downloadReportWith_RTF_Format() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(10000);
//        switchToNewWindow();
//        switchToParentWindow();
        Thread.sleep(6000);
    }

    public void setYear() {
        setTextUsingJS(year,"Year", ImportApplicationVariables.reportYear);
    }

    public void openDownloadedReport() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_J);
        robot.keyRelease(KeyEvent.VK_J);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(20000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
//        Thread.sleep(6000);
    }

}

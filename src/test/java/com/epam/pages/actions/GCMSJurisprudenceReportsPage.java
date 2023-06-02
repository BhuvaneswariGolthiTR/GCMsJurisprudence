package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
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
    private static final String viewLink = "xpath -> //a[text()='View' or text()='Visualizar']";
    private static final String checkBoxesToDelete = "xpath -> //input[@id='botonSeleccionar']";
    private static final String discard = "xpath -> //input[@value='Discard marked' or @value='Excluir seleção']";


    public GCMSJurisprudenceReportsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void selectDocumentYear() {
//        setTextUsingJS(marginalDropdownOne, "Document year", "NMA");
//        select
    }

    public void selectDocumentNumber() {
        setTextUsingJS(marginalDropdownTwo, "Document number", "NMN");
    }

    public void selectOperator() {
        setTextUsingJS(searchOperator, "Operator", "<=");
    }

    public void setYear(String value) {
        setTextUsingJS(year, "Year filed", value);
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

    public void clickOkButton() {
        clickElementUsingJS(okButton, "Ok");
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

    public void openReports() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_ALT);
        Thread.sleep(10000);
        switchToNewWindow();
        switchToParentWindow();
    }
}

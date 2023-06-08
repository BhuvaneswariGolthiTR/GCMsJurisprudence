package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

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

    public GcmsUploadingMetadataPage(WebDriver webDriver) {
        super(webDriver);
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

    public void clickContentTypeDropDown(String value) throws InterruptedException, AWTException {
        WebElement e = webDriver.findElement(By.xpath("//select[@id='ambito']"));
        e.sendKeys("");
        Robot robot = new Robot();
        Select select = new Select(e);
        System.err.println("length*********" + select.getOptions().size());
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equals(value)) {
                System.err.println("got correct***");
                break;
            }
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }

    }

    public void clickTypeDropDown(String value) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        WebElement e = webDriver.findElement(By.xpath("//select[@id='tipo']"));
        e.sendKeys("");
        Select select = new Select(e);
        System.err.println("length*********" + select.getOptions().size());
        for (int i = 0; i < select.getOptions().size(); i++) {
            if (select.getOptions().get(i).getText().equals(value)) {
                System.err.println("got correct***");
                break;
            }
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
    }

    public void clickProcessDropDown(String value) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        WebElement e = getElement(formatLocator(processDropDown));
        e.sendKeys("");
        Select select = new Select(e);
        for (int i = 0; i < select.getOptions().size(); i++) {

            if (select.getOptions().get(i).getText().equals(value)) {
                break;
            }
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
    }

    public void clickSubTypeDropDown(String value) throws InterruptedException, AWTException {
        Robot robot = new Robot();
        WebElement e = webDriver.findElement(By.xpath("//select[@id='subtipo']"));
        e.sendKeys("");
        Select select = new Select(e);
        System.err.println("length*********" + select.getOptions().size());
        for (int i = 0; i < select.getOptions().size(); i++) {

            if (select.getOptions().get(i).getText().equals(value)) {

                // select.selectByIndex(i);
                System.err.println("got correct***");
                break;
            }
            robot.keyPress(KeyEvent.VK_DOWN);
            Thread.sleep(1000);
            robot.keyRelease(KeyEvent.VK_DOWN);
        }
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

     


    

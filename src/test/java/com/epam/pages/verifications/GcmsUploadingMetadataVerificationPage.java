package com.epam.pages.verifications;

import com.epam.pages.actions.GcmsUploadingMetadataPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class GcmsUploadingMetadataVerificationPage extends GcmsUploadingMetadataPage {


    public GcmsUploadingMetadataVerificationPage(WebDriver webDriver) {
        super(webDriver);
    }
    public void navigateToSearchLink()
    {
        clickProcessManagement();
        clickSearchLink();
    }
    public void enterDropDownValuesOfContentTypeDropDown(String value) throws InterruptedException, AWTException {
        Thread.sleep(2000);
        clickContentTypeDropDown(value);
    }
    public void enterDropDownValuesOfTypeDropDown(String value) throws AWTException, InterruptedException {
        clickTypeDropDown(value);
    }
    public void enterDropDownValuesOfSubTypeDropDown(String value) throws InterruptedException, AWTException {
        clickSubTypeDropDown(value);
    }
    public void enterDropDownValuesOfProcessDropDown(String value) throws InterruptedException, AWTException {
        clickProcessDropDown(value);
    }

    public void searchForUploadDocument() throws InterruptedException {
        Thread.sleep(3000);
        WebElement totalElements=webDriver.findElement(By.xpath("//td[@class='ContentHeader']"));
        String number = totalElements.getText().replaceAll("[a-zA-Z]","").trim();
        number= number.replaceAll("-", "").trim();
        System.err.println("text**" + totalElements.getText()+ "number***" + number);
        int n= Integer.parseInt(number);
        int count = n/5;
        for(int i=0; i<count; i++) {
            if (webDriver.getPageSource().contains("TESTING_Cases_metadata_load")){
                if (webDriver.findElement(By.xpath("//td[text()='TESTING_Cases_metadata_load']")).isDisplayed()) {
                       clickElementUsingJS(testingLink, "link");
                       break;
                   }
            } else {
                clickElementUsingJS(nextButton, "nextButton");
                Thread.sleep(3000);
            }
        }
    }
    public void updateContentXpath(String value) throws InterruptedException {
     switchToNewWindow();
     enterInputFolderPath(value);
     Thread.sleep(4000);
    }

    public void updateContentLogDirPath(String value) throws InterruptedException {
        enterLogDirPath(value);
        Thread.sleep(4000);
    }



}

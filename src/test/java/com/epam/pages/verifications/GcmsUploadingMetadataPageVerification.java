package com.epam.pages.verifications;

import com.epam.pages.actions.GcmsUploadingMetadataPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;

public class GcmsUploadingMetadataPageVerification extends GcmsUploadingMetadataPage {


    public GcmsUploadingMetadataPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void navigateToSearchLink() {
        clickProcessManagement();
        clickSearchLink();
    }

    public void enterDropDownValuesOfContentTypeDropDown(String value) throws InterruptedException, AWTException {
        Thread.sleep(2000);
        clickContentTypeDropDown(value);
    }

    public void enterDropDownValuesOfTypeDropDown(String value) throws AWTException {
        clickTypeDropDown(value);
    }

    public void enterDropDownValuesOfSubTypeDropDown(String value) throws AWTException {
        clickSubTypeDropDown(value);
    }

    public void enterDropDownValuesOfProcessDropDown(String value) throws AWTException {
        clickProcessDropDown(value);
    }

    public void searchForUploadDocument() throws InterruptedException {
        Thread.sleep(3000);
        WebElement totalElements = getElement(getTableValues());
        String number = totalElements.getText().replaceAll("[a-zA-Z]", "").trim();
        number = number.replaceAll("-", "").trim();
        System.err.println("text**" + totalElements.getText() + "number***" + number);
        int n = Integer.parseInt(number);
        int count = n / 5;
        for (int i = 0; i < count; i++) {
            if (webDriver.getPageSource().contains("TESTING_Cases_metadata_load")) {
                if (isElementDisplayed(getFileName())) {
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

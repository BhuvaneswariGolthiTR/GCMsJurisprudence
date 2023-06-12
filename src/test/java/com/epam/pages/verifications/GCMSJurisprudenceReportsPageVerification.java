package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSJurisprudenceReportsPage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class GCMSJurisprudenceReportsPageVerification extends GCMSJurisprudenceReportsPage {
    public GCMSJurisprudenceReportsPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void validateDeletionOfReports() {
        try {
            verifyIsElementDisplayed("Deletion of reports is Unsuccessful", getReports(), true, "Reports");
        }
        catch (TimeoutException e) {
            System.err.println("Generated Reports are deleted");
        }
    }

    public void verifyCreationOfReport() {
        if (isElementDisplayed(getReports())) {
            System.err.println("Report is Created Successfully");
        }
    }

}

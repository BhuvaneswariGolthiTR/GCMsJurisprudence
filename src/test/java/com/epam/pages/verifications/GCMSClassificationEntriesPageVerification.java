package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSClassificationEntriesPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class GCMSClassificationEntriesPageVerification extends GCMSClassificationEntriesPage {

    public GCMSClassificationEntriesPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyDisplayOfNewClassificationEntry(){
        expandAnalysisDataSection();
        switchToIframeByElement(getClassificationEntriesIframe());
        verifyElementText("New Classification Entry is not added",getNewClassificationEntry(ImportApplicationVariables.fullMarginalId),ImportApplicationVariables.fullMarginalId);
    }

    public void verifyClassificationEntryDeletion() {
        switchToIframeByElement(getClassificationEntriesIframe());
        try {
            verifyIsElementDisplayed("Deletion of Classification Entry is unsuccessful", getNewClassificationEntry(ImportApplicationVariables.fullMarginalId), true, "Classification Entry");
        }
        catch (TimeoutException e){
            System.err.println("Added Classification Entry is deleted");
        }
    }

}

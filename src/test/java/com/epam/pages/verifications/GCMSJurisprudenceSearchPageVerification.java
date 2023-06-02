package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSJurisprudenceSearchPage;
import org.openqa.selenium.WebDriver;

public class GCMSJurisprudenceSearchPageVerification extends GCMSJurisprudenceSearchPage {

    public GCMSJurisprudenceSearchPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifyDocumentSearch(String value){
//        verifyElementText("Document is not loaded Successfully",getDocumentID(value),value);
        verifyIsElementDisplayed("Document is not displayed",getDocumentID(value),true,"Document ID");
    }
}
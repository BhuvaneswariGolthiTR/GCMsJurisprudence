package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSJurisprudenceRelationshipPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GCMSJurisprudenceRelationshipPageVerification extends GCMSJurisprudenceRelationshipPage {
    public GCMSJurisprudenceRelationshipPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void validateMultipleRelationships() {
        switchToIframeByElement(getRelationshipsIframe());
        List<WebElement> relationships = getElements(getCommonRelationship(ImportApplicationVariables.relationshipDataValue));
        int relationshipCount = relationships.size();
        if (relationshipCount == 2) {
            for (int i = 1; i <= relationshipCount; i++) {
                verifyElementText("Relationship is not created",getCommonRelationship(ImportApplicationVariables.relationshipDataValue) , ImportApplicationVariables.relationshipDataValue);
            }
        }
        switchToDefaultContent();
    }

    public void verifyRelationshipsDeletion() {
        switchToIframeByElement(getRelationshipsIframe());
        try{
            verifyIsElementDisplayed("Deletion is not performed successfully",getDeleteLink(),true,"Relationships");
        }catch (TimeoutException e){
            System.err.println("Added Relationships are deleted");
        }
        switchToDefaultContent();
    }

}

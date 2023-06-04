package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSJurisprudenceTopicPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class GCMSJurisprudenceTopicPageVerification extends GCMSJurisprudenceTopicPage {

    public GCMSJurisprudenceTopicPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifySelectedTopicValueDisplayed() {
        switchToTopicFrame();
        scrollInView(getSubTopic());
        verifyElementText("Selected Topic value is not displayed",getTopic(),ImportApplicationVariables.topic);
    }

    public void verifySelectedSubTopicValueDisplayed() {
        scrollInView(getSubTopic());
        verifyElementText("Selected SubTopic value is not displayed",getSubTopic(),ImportApplicationVariables.subTopic);
    }

    public void verifySubTopicDeletion() {
        try {
            verifyIsElementDisplayed("Selected Topic-SubTopic are not deleted", getSubTopic(), false, "Topic-SubTopic Entry");
        }catch (TimeoutException e){
            System.err.println("Added Topic-SubTopic Entries are deleted successfully");
        }
    }

    public void verifyTopicSubTopicEntriesUsingCodeAreDisplayed() {
        switchToTopicFrame();
        verifyIsElementDisplayed("Selected Topic value displayed", String.format("xpath -> //td[text()[normalize-space()='%s']]","Actividades y sectores"),true, "topic value");
        verifyIsElementDisplayed("Selected sub topic value displayed", String.format("xpath -> //td[text()[normalize-space()='%s']]","Agricultura"),true, "sub topic value");
    }
}

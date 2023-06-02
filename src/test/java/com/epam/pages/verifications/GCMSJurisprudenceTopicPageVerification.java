package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSJurisprudenceTopicPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GCMSJurisprudenceTopicPageVerification extends GCMSJurisprudenceTopicPage {

    public GCMSJurisprudenceTopicPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public void verifySelectedTopicValueDisplayed() {
        switchToTopicFrame();
        verifyElementText("Selected Topic value is not displayed",getTopic(),ImportApplicationVariables.topic);
    }

    public void verifySelectedSubTopicValueDisplayed() {
        verifyElementText("Selected SubTopic value is not displayed",getSubTopic(),ImportApplicationVariables.subTopic);
    }

}

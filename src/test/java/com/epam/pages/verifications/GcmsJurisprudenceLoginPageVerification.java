package com.epam.pages.verifications;

import com.epam.pages.actions.GcmsJurisprudenceLoginPage;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class GcmsJurisprudenceLoginPageVerification extends GcmsJurisprudenceLoginPage {
    public GcmsJurisprudenceLoginPageVerification(WebDriver webDriver) throws AWTException {
        super(webDriver);
    }

    public void verifyLogin(String header) {
        verifyElementTextContains("Login Unsuccessful", getGcmsHomePageHeader(), header);
    }

}

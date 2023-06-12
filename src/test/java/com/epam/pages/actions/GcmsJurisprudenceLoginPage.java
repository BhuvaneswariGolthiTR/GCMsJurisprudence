package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;

import org.openqa.selenium.WebDriver;

import java.awt.*;

import static com.epam.framework.ui.driver.DriverManager.getDriver;

public class GcmsJurisprudenceLoginPage extends PageWrapper {

    public GcmsJurisprudenceLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    private static final String username = "xpath -> //input[normalize-space(@name)='username']";
    private static final String password = "xpath -> //input[normalize-space(@name)='password']";
    private static final String languageDropdown = "xpath -> //select[@id='selectedLanguage']";
    private static final String loginButton = "xpath -> //input[normalize-space(@type)='submit']";
    private static final String gulfHeader = "xpath -> //h1[contains(text(),'GULF')]";

    public void accessURL(String gcmsLoginURL) {
        getDriver().get(gcmsLoginURL);
    }

    public void setUsername(String value, String... formatArgs) {
        setTextUsingJS(username, "Username", value, formatArgs);
    }

    public void setPassword(String value, String... formatArgs) {
        setTextUsingJS(password, "Password", value, formatArgs);
    }

    public void clickLoginButton(String... formatArgs) {
        clickElementUsingJS(loginButton, "Login Button", formatArgs);
    }

    public void setLanguage() throws AWTException, InterruptedException {
        boolean isGulf = false;
        try {
            isGulf = isElementDisplayed(gulfHeader);
        }
        catch (Exception ignored) {
        }
        if (!isGulf) {
            selectOptionFromDropDownUsingRobot(languageDropdown, "Eng");
        }
    }

}

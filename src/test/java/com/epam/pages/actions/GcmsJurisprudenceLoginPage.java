package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static com.epam.framework.ui.driver.DriverManager.getDriver;

public class GcmsJurisprudenceLoginPage extends PageWrapper {

    public GcmsJurisprudenceLoginPage(WebDriver webDriver) throws AWTException {
        super(webDriver);
    }

    Robot robot = new Robot();
    private static final String username = "xpath -> //input[normalize-space(@name)='username']";
    private static final String password = "xpath -> //input[normalize-space(@name)='password']";
    private static final String languageDropdown = "xpath -> //select[@id='selectedLanguage']";
    private static final String loginButton = "xpath -> //input[normalize-space(@type)='submit']";
    private static final String gcmsHomePageHeader = "xpath -> //table[@class='headerTopMenu']//h1";
    private static final String brazilHeader = "xpath -> //h1[contains(text(),'BR')]";
    private static final String spainHeader = "xpath -> //h1[contains(text(),'ARZ')]";
    private static final String gulfHeader = "xpath -> //h1[contains(text(),'GULF')]";

    public String getUsername(String... formatArgs) {
        return formatLocator(username, formatArgs);
    }

    public String getPassword(String... formatArgs) {
        return formatLocator(password, formatArgs);
    }

    public String getLoginButton(String... formatArgs) {
        return formatLocator(loginButton, formatArgs);
    }

    public String getGcmsHomePageHeader(String... formatArgs) {
        return formatLocator(gcmsHomePageHeader, formatArgs);
    }

    public void accessURL(String gcmsLoginURL) {
        getDriver().get(gcmsLoginURL);
    }

    public void setUsername(String value, String... formatArgs) {
        setTextUsingJS(username, "Username", value);
    }

    public void setPassword(String value, String... formatArgs) {
        setTextUsingJS(password, "Password", value);
    }

    public void clickLoginButton(String... formatArgs) {
        clickElementUsingJS(loginButton, "Login Button");
    }
    public void setLanguage() throws AWTException, InterruptedException {

        boolean isGulf = false;
        try {
            isGulf = isElementDisplayed(gulfHeader);
        }
        catch (Exception ignored) {
        }
        if (!isGulf) {
            Robot robot = new Robot();
            WebElement e = getElement(formatLocator(languageDropdown));
            e.sendKeys("");
            String fine = "E";
            fine = fine.toUpperCase();
            char[] c = fine.toCharArray();
            for (int k = 0; k < c.length; k++) {
                int keyCode = (int) c[k];
                if (keyCode == 32) {
                    robot.keyPress(KeyEvent.VK_SPACE);
                    Thread.sleep(200);
                    robot.keyRelease(KeyEvent.VK_SPACE);
                } else
                    robot.keyPress(keyCode);
                Thread.sleep(200);
                robot.keyRelease(keyCode);
            }
        }
    }
}

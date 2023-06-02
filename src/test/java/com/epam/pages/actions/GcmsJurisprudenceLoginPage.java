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

    public void moveToLanguage() {
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    public void setLanguage() throws AWTException, InterruptedException {
        try {
            if (isElementDisplayed(brazilHeader)) {
                moveToLanguage();
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
            } else if (isElementDisplayed(spainHeader)) {
                moveToLanguage();
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
            }
        }
        catch (Exception ignored) {
        }
//        Robot robot = new Robot();
//        WebElement e = getElement(formatLocator(languageDropdown));
//        e.sendKeys("");
//        Select select = new Select(e);
//        int optionsCount = select.getOptions().size();
//        Thread.sleep(3000);
//        List<WebElement> options = select.getOptions();
//        List<String> optionsTextValues = new ArrayList<>();
//        for(int i=0;i<optionsCount;i++){
//            String text = options.get(i).getText().trim();
//            optionsTextValues.add(i,text);
//            System.err.println(optionsTextValues.get(i));
//        }
//        int i=0;
//        for (String optionsTextValue : optionsTextValues) {
//            i++;
//            Thread.sleep(2000);
//            System.err.println("Loop"+i);
//            if (optionsTextValue.equals("Português")) {
//                robot.keyPress(KeyEvent.VK_DOWN);
//                Thread.sleep(3000);
//                robot.keyRelease(KeyEvent.VK_DOWN);
//                Thread.sleep(3000);
//                break;
//            }
//            if(optionsTextValue.equals("Español")){
//                robot.keyPress(KeyEvent.VK_UP);
//                Thread.sleep(3000);
//                robot.keyRelease(KeyEvent.VK_UP);
//                Thread.sleep(3000);
//                break;
//            }
//        }
//        for (int i = 0; i < optionsCount; i++) {
//            Thread.sleep(3000);
//            System.err.println("text:" + options.get(i).getText());
//            if (select.getOptions().get(i).getText().trim().
//                    equals("English")) {
//                break;
//            }
//            robot.keyPress(KeyEvent.VK_DOWN);
//            Thread.sleep(3000);
//            robot.keyRelease(KeyEvent.VK_DOWN);
//            Thread.sleep(3000);
//        }
//        Robot robot = new Robot();
//        WebElement e = getElement(formatLocator(languageDropdown));
//        e.sendKeys("");
//        String fine = "E";
//        fine = fine.toUpperCase();
//        char[] c = fine.toCharArray();
//        for (int k = 0; k < c.length; k++) {
//            int keyCode = (int) c[k];
////            if (keyCode == 32) {
////                robot.keyPress(KeyEvent.VK_SPACE);
////                Thread.sleep(200);
////                robot.keyRelease(KeyEvent.VK_SPACE);
////            } else
//            robot.keyPress(keyCode);
//            Thread.sleep(200);
//            robot.keyRelease(keyCode);
//        }
    }

}
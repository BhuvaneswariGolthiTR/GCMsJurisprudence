package com.epam.framework.ui;

import com.epam.framework.core.TestContext;
import com.epam.framework.core.constants.LoggerConstants;
import com.epam.framework.core.constants.MessageConstants;
import com.epam.framework.core.constants.WaitConstants;
import com.epam.framework.core.logging.logger.LogLevel;
import com.epam.framework.core.reporting.Reporter;
import com.epam.framework.ui.pageobjectgenerator.exceptions.IncorrectFormattersException;
import org.assertj.core.api.Assertions;
import org.jasypt.util.text.BasicTextEncryptor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import static com.epam.framework.ui.driver.DriverManager.getDriver;
import static java.lang.String.format;

import java.time.Duration;

public abstract class PageWrapper extends Browser {

    private WebElement webElement;
    private List<WebElement> webElements;


    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ERROR_MESSAGE = "Element to %S  is not found";
    private static final String CLICK_DESCRIPTION = "Not Clicked";
    private final String parent = getDriver().getWindowHandle();


    public PageWrapper(WebDriver webDriver) {
        super(webDriver);
    }


    public WebElement getElement(String selector) {
        this.webElement = fluentWait(LocatorFormatter.getElementIdentifier(selector))
                .stream().findFirst().orElseThrow(NoSuchElementException::new);
        return this.webElement;
    }

    public List<WebElement> getElements(String selector) {
        this.webElements = fluentWait(LocatorFormatter.getElementIdentifier(selector));
        return this.webElements;
    }

    public String formatLocator(String locator, String... formatters) {
        if (LocatorFormatter.getNumberOfFormatters(locator) == formatters.length) {
            return String.format(locator, formatters);
        } else {
            throw new IncorrectFormattersException(
                    "The number of formatters in locator does not match with number of formatters provided");
        }
    }

    public void click(String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            webElement.click();
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.SHORT));
            Reporter.log(LogLevel.INFO, elementName + " Element has been Clicked");
        } else {
            Reporter.log(LogLevel.ERROR, elementName + " Element could not be Clicked");
            Reporter.addScreenshot("Element Not Clicked", CLICK_DESCRIPTION);
            TestContext.getLogger().log(LogLevel.ERROR, String.format(ERROR_MESSAGE, "click"));
        }
    }

    public void doubleClick(String elementLocator, String elementName, String... formatArgs) {
        if (getElement(formatLocator(elementLocator, formatArgs)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            getActions().doubleClick(webElement).perform();
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.TIMEOUT));
            Reporter.log(LogLevel.INFO, elementName + " Element has been DoubleClicked");
        } else {
            Reporter.log(LogLevel.ERROR, elementName + " Element could not be Clicked");
            Reporter.addScreenshot("Element Not Clicked", CLICK_DESCRIPTION);
            TestContext.getLogger().log(LogLevel.ERROR, String.format(ERROR_MESSAGE, "double click"));
        }
    }

    public String getAttribute(String attributeName, String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            String attributeValue = webElement.getAttribute(attributeName);
            Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_ATTRIBUTE_MESSAGE, attributeName, elementName, attributeValue));
            return attributeValue;
        } else {
            Reporter.log(LogLevel.ERROR, attributeName + " attribute for teh element " + elementName + " could not be retrieved");
            Reporter.addScreenshot("ElementNotClicked", CLICK_DESCRIPTION);
            TestContext.getLogger().log(LogLevel.ERROR, String.format(ERROR_MESSAGE, "get attribute {" + attributeName + "}"));
            return null;
        }
    }

    public void setCredentials(String value, String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            webElement.clear();
            webElement.sendKeys(value);
            Reporter.log(LogLevel.INFO, elementName + " Element has been set with username/password ");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, String.format(ERROR_MESSAGE, "set test {" + value + "}"));
        }
    }

    public void setText(String value, String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            webElement.clear();
            webElement.sendKeys(value);
            Reporter.log(LogLevel.INFO, elementName + " Element has been set with text " + value);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, String.format(ERROR_MESSAGE, "set test {" + value + "}"));
        }
    }

    public void clearField(String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            webElement.clear();
            Reporter.log(LogLevel.INFO, elementName + " text element has been set cleared ");

        } else {
            TestContext.getLogger().log(LogLevel.ERROR, String.format(ERROR_MESSAGE, "clear"));
        }
    }

    public boolean isElementPresent(String elementLocator, String... formatters) {
        return !getElements(formatLocator(elementLocator, formatters)).isEmpty();
    }

    public boolean isElementDisplayed(String elementLocator, String... formatters) {
        return getElement(formatLocator(elementLocator, formatters)).isDisplayed();
    }


    public void scrollInView(String elementLocator, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
            javascriptExecutor.executeScript("window.scrollBy(0,-80)");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element is not found");
        }
    }

    public String getButtonText(String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            SyncUtils.waitForElementVisibility(webDriver, webElement, Duration.ofSeconds(WaitConstants.DEFAULT));
            return getAttribute(ATTRIBUTE_VALUE, elementLocator, elementName, formatters);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "button is not found");
        }
        return null;
    }

    public String getText(String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.DEFAULT));
            String elementText = webElement.getText();
            Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_PROPERTY_MESSAGE, "Text", elementName, elementText));
            return elementText;
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Label is not found");
        }
        return null;
    }

    public String getCssValue(String elementLocator, String propertyName, String elementName, String... formatArgs) {
        if (getElement(formatLocator(elementLocator, formatArgs)) != null) {
            String cssValue = webElement.getCssValue(propertyName);
            Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_PROPERTY_MESSAGE, "CSS property " + propertyName, elementName, cssValue));
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element to get css is not found");
        }
        return null;
    }

    public String getTagName(String elementLocator, String... formatArgs) {
        String tagName = "";
        if (getElement(formatLocator(elementLocator, formatArgs)) != null) {
            tagName = webElement.getTagName();
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element to get tag is not found");
        }
        return tagName;
    }

    public boolean isChecked(boolean expCondition, String elementLocator, String elementName, String... formatArgs) {
        boolean isChecked = false;
        if (getElement(formatLocator(elementLocator, formatArgs)) != null) {
            isChecked = (webElement.isSelected() && expCondition);
            Reporter.log(LogLevel.INFO, elementName + " Element has been checked with status " + isChecked);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element to get selection status is not found");
        }
        return isChecked;
    }

    public void check(boolean expCondition, String elementLocator, String elementName, String... formatArgs) {
        if (getElement(formatLocator(elementLocator, formatArgs)).isSelected() && expCondition) {
            webElement.click();
            Reporter.log(LogLevel.INFO, elementName + " Element has been checked");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Checkbox Element is not found");
        }
    }

    public void checkboxSelectionWithStatus(boolean status, String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            if (status) {
                if (!webElement.isSelected()) {
                    webElement.click();
                    SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.TIMEOUT));
                } else {
                    TestContext.getLogger().log(LogLevel.ERROR, "Element for CheckBox is already checked");
                }
            }
            if (!status) {
                if (webElement.isSelected()) {
                    webElement.click();
                    SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.TIMEOUT));
                } else {
                    TestContext.getLogger().log(LogLevel.ERROR, "Element for CheckBox is already unchecked");
                }
            }
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.CHECKBOX_ERROR_MESSAGE);
        }
    }

    public void selectItemFromDropdownByIndex(int byIndex, String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.selectByIndex(byIndex);
            Reporter.log(LogLevel.INFO, elementName + " drop down element has been selected with index " + byIndex);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }

    public void selectItemFromDropdownByValue(String byValue, String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.selectByValue(byValue);
            Reporter.log(LogLevel.INFO, elementName + " drop down element has been selected with value " + byValue);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }

    public void selectItemFromDropdownByVisibleText(String byVisibleText, String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.selectByVisibleText(byVisibleText);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }

    public void deSelectItemFromDropdownByIndex(int byIndex, String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.deselectByIndex(byIndex);
            Reporter.log(LogLevel.INFO, elementName + " drop down element has been deselected with index " + byIndex);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }


    public void deSelectItemFromDropdownByValue(String byValue, String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.deselectByValue(byValue);
            Reporter.log(LogLevel.INFO, elementName + " drop down element has been selected with value " + byValue);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }

    public void deSelectItemFromDropdownByVisibleText(String byVisibleText, String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.deselectByVisibleText(byVisibleText);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }

    public void deSelectAllItemFromDropdown(String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            dropDownSelect.deselectAll();
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
        }
    }

    public boolean checkDropdownIsMultiple(String dropdownLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(dropdownLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select dropDownSelect = new Select(webElement);
            return dropDownSelect.isMultiple();
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, LoggerConstants.DROPDOWN_ERROR_MESSAGE);
            return false;
        }
    }

    public boolean searchElementInCombobox(String value, String comboboxLocator, String elementName, String... formatters) {
        boolean flag = false;
        if (getElement(formatLocator(comboboxLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select comboboxList = new Select(webElement);
            List<WebElement> optionsList1 = comboboxList.getOptions();
            flag = optionsList1.stream().anyMatch(t -> t.getText().equalsIgnoreCase(value));
            if (!flag)
                TestContext.getLogger().log(LogLevel.ERROR, "Entered element for Combobox is not available " + value);
        }
        return flag;
    }

    public void enterTextInComboBox(String value, String comboboxLocator, String elementName, String... formatters) {
        if (searchElementInCombobox(value, comboboxLocator, elementName, formatters)) {
            webElement.sendKeys(value);
            Reporter.log(LogLevel.INFO, elementName + " Combobox Element has been set with text " + value);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element to set test {" + value + "} is not found");
        }
    }

    public String getComboBoxValue(String comboboxLocator, String elementName, String... formatters) {
        String selectedValue = null;
        if (getElement(formatLocator(comboboxLocator, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            Select select = new Select(webElement);
            selectedValue = select.getFirstSelectedOption().getText();
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element to set test {" + comboboxLocator + "} is not found");
        }
        return selectedValue;
    }

    public boolean validateComboBoxList(List<WebElement> expectedOptions, String comboboxLocator, String elementName, String... formatters) {
        boolean status = true;
        Select comboboxList = new Select(getElement(formatLocator(comboboxLocator, formatters)));
        List<WebElement> optionsList = comboboxList.getOptions();
        List<WebElement> unavailable = optionsList.stream().filter(e -> (expectedOptions.stream().filter(d -> d.equals(e)).count()) < 1)
                .collect(Collectors.toList());
        optionsList.forEach(option -> TestContext.getLogger().log(LogLevel.INFO, option.getText()));
        if (!unavailable.isEmpty()) {
            status = false;
        }
        return status;
    }

    public void verifyElementValue(String expectedValue, String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            String actualValue = getAttribute(ATTRIBUTE_VALUE, elementLocator, elementName, formatters);
            Assertions.assertThat(expectedValue).isEqualTo(actualValue);
            Reporter.log(LogLevel.INFO, "Actual Value " + actualValue + "is matched with " + expectedValue);
            Reporter.addScreenshot("ElementValue", "Verification");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Expected element value is not available");
        }
    }

    public void verifyElementText(String errMsg, String elementLocator, String expectedValue, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            WebElement element = getElement(formatLocator(elementLocator, formatters));
            Assertions.assertThat(element.getText()).withFailMessage(() -> errMsg).isEqualTo(expectedValue);
            Reporter.log(LogLevel.INFO, "Actual Value " + element.getText() + "is matched with " + expectedValue);
            Reporter.addScreenshot("ElementTextVerification", "Verification");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Expected element value is not available");
        }

    }

    public void verifyElementTextContains(String errMsg, String elementLocator, String expectedValue, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            WebElement element = getElement(formatLocator(elementLocator, formatters));
            Assertions.assertThat(element.getText()).withFailMessage(() -> errMsg).contains(expectedValue);
            Reporter.log(LogLevel.INFO, "Actual Value " + element.getText() + "is matched with " + expectedValue);
            Reporter.addScreenshot("ElementTextVerification", "Verification");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Expected element value is not available");
        }

    }

    public void verifyIsElementDisplayed(String errMsg, String elementLocator, boolean isDisplayed, String elementName, String... formatters) {
        Assertions.assertThat(getElement(formatLocator(elementLocator, formatters)).isDisplayed()).withFailMessage(() -> errMsg).isEqualTo(isDisplayed);
        Reporter.addScreenshot("ElementTextVerification", "Verification");
        Reporter.log(LogLevel.INFO, elementName + " is displayed");
    }

    public void verifyIsElementPresent(String errMsg, boolean isDisplayed, String elementLocator, String elementName) {
        Assertions.assertThat(getElements(elementLocator).isEmpty())
                .withFailMessage(() -> errMsg).isEqualTo(isDisplayed);
        Reporter.log(LogLevel.INFO, elementName + " Element is present with status " + isDisplayed);
    }

    public void verifyElementAttribute(String errMsg, String elementLocator, String attributeName, String expValue, String elementName, String... formatters) {
        String actualValue = getElement(formatLocator(elementLocator, formatters)).getAttribute(attributeName);
        Assertions.assertThat(actualValue).withFailMessage(() -> errMsg).isEqualTo(expValue);
        Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_ATTRIBUTE_VERIFICATION_MESSAGE, attributeName, expValue, actualValue, elementName));
    }

    public void verifyElementValue(String errMsg, String elementLocator, String actualValue, String expValue, String elementName, String... formatters) {
        //String actualValue= getElement(formatLocator(elementLocator, formatters)).getAttribute(ATTRIBUTE_VALUE);
        Assertions.assertThat(actualValue).withFailMessage(() -> errMsg).isEqualTo(expValue);
        Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_ATTRIBUTE_VERIFICATION_MESSAGE, ATTRIBUTE_VALUE, expValue, actualValue, elementName));
    }

    public void verifyDropdownValues(String errMsg, List<String> expValues, String elementLocator, String elementName, String... formatters) {
        Select dropDown = new Select(getElement(formatLocator(elementLocator, formatters)));
        List<WebElement> optionsList = dropDown.getOptions();
        Assertions.assertThat(optionsList).usingRecursiveComparison()
                .ignoringCollectionOrder()
                .withFailMessage(() -> errMsg).isEqualTo(expValues);
        Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_ATTRIBUTE_VERIFICATION_MESSAGE, "options", expValues, optionsList, elementName));
    }

    public void getVerifyElementIsChecked(String errMsg, WebElement element, String isChecked) {
        Assertions.assertThat(element.isSelected()).withFailMessage(() -> errMsg).isEqualTo(isChecked);
    }

    public void getVerifyElementIsSelected(String errMsg, WebElement element, String isSelected) {
        Assertions.assertThat(element.isSelected()).withFailMessage(() -> errMsg).isEqualTo(isSelected);

    }

    public void selectRadioButton(String radioButton, String elementName, String... formatters) {
        if (getElement(formatLocator(radioButton, formatters)) != null) {
            SyncUtils.waitUntilClickable(webDriver, webElement, Duration.ofSeconds(WaitConstants.SHORT));
            if (!webElement.isSelected()) {
                click(radioButton, elementName);
                Reporter.log(LogLevel.INFO, elementName + " radio element has been selected");
            }
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "radiobutton is not found");
        }
    }

    public void selectRadioButtonUsingList(String value, String radioButtonSelector, String elementName, String... formatters) {
        getElements(formatLocator(radioButtonSelector, formatters));
        for (WebElement element : webElements) {
            String radioButtonText = element.getAttribute(ATTRIBUTE_VALUE);
            if (radioButtonText.equals(value) && !element.isSelected()) {
                element.click();
            }
        }
    }

    public List<String> getTextsFromListOfElements(List<WebElement> elements) {
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickElementUsingJS(String elementLocator, String elementName, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.LONG));
            javascriptExecutor.executeScript("arguments[0].click();", webElement);
            Reporter.log(LogLevel.INFO, elementName + " Element has been Clicked");
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Element is not found");
        }
    }

    public void setTextUsingJS(String elementLocator, String elementName, String input, String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.LONG));
            javascriptExecutor.executeScript(format("arguments[0].value='%s'", input), webElement);
            Reporter.log(LogLevel.INFO, elementName + " element has been set with text " + input);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Could not set text for element");
        }
    }

    public void switchToIframeByIndex(int index) {
        webDriver.switchTo().frame(index);
        TestContext.getLogger().log(LogLevel.INFO, String.format("Switched to frame number %s", index));
    }

    public void switchToIframeByElement(String locator, String... formatters) {
        if (getElement(formatLocator(locator, formatters)) != null) {
            webDriver.switchTo().frame(webElement);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Could not find required frame");
        }
    }

    public void switchToDefaultContent() {
        webDriver.switchTo().defaultContent();
        TestContext.getLogger().log(LogLevel.INFO, "Switched back to the default frame");
    }

    private Actions getActions() {
        return new Actions(webDriver);
    }

    public void clickEnterKey() {
        getActions().sendKeys(Keys.ENTER).build().perform();
        TestContext.getLogger().log(LogLevel.INFO, "Clicked Enter key");
    }

    public void clickSequenceOfKeys(CharSequence... keys) {
        getActions().sendKeys(Keys.chord(keys)).build().perform();
        TestContext.getLogger().log(LogLevel.INFO, "Clicked sequence of keys");
    }

    public void rightClick() {
        getActions().contextClick().perform();
        TestContext.getLogger().log(LogLevel.INFO, "Performed Right click");
    }

    public void rightClickOnElement(String elementLocator, String... formatters) {
        getElement(formatLocator(elementLocator, formatters));
        getActions().contextClick(webElement).perform();
    }

    public void sendCapitalizedTextToElement(String textToInput, String elementLocator, String... formatters) {
        getElement(formatLocator(elementLocator, formatters));
        getActions().keyDown(webElement, Keys.SHIFT).sendKeys(webElement, textToInput)
                .keyUp(webElement, Keys.SHIFT).build().perform();
    }

    public void verifyTableValue(String errMsg, String actualValue, String expValue, String elementName, String... formatters) {

        Assertions.assertThat(actualValue).withFailMessage(() -> errMsg).isEqualTo(expValue);
        Reporter.log(LogLevel.INFO, String.format(MessageConstants.ELEMENT_ATTRIBUTE_VERIFICATION_MESSAGE, ATTRIBUTE_VALUE, expValue, actualValue, elementName));
    }

    public static String decode(String str) {
        String value = null;
        try {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPasswordCharArray("Value".toCharArray());
            value = textEncryptor.decrypt(str);
        }
        catch (Exception e) {
            TestContext.getLogger().log(LogLevel.ERROR, (e.getClass().getSimpleName() + " - " + e.getMessage()));
        }
        return value;
    }

    public void verifyValues(String errMsg, String elementLocator, String expValue, String elementName,
                             String... formatters) {
        if (getElement(formatLocator(elementLocator, formatters)) != null) {
            String actualValue = getElement(formatLocator(elementLocator, formatters)).getText().substring(6);
            Assertions.assertThat(Integer.parseInt(actualValue)).withFailMessage(() -> errMsg)
                    .isGreaterThan(Integer.parseInt(expValue));
            Reporter.log(LogLevel.INFO,
                    "Actual Value " + actualValue + " is matched with condition of expected value ");
            Reporter.addScreenshot("ElementTextVerification", "Verification");
        } else {
            Reporter.addScreenshot("ElementTextVerification", "Verification");
            TestContext.getLogger().log(LogLevel.INFO, "Expected element value is not available");
        }
    }

    public void sendTextUsingRobot(String elementLocator, String elementName, String input, String... formatters) throws AWTException {
        WebElement element = getElement(formatLocator(elementLocator, formatters));
        if (element != null) {
            SyncUtils.waitUntilPageIsFullyLoaded(webDriver, Duration.ofSeconds(WaitConstants.LONG));
            element.sendKeys("");
            Robot robot = new Robot();
            StringSelection stringSelection = new StringSelection(input);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            Reporter.log(LogLevel.INFO, elementName + " element has been set with text " + input);
        } else {
            TestContext.getLogger().log(LogLevel.ERROR, "Could not set text for element");
        }
    }

    public void switchToParentWindow() {
        getDriver().switchTo().window(parent);
        TestContext.getLogger().log(LogLevel.INFO, "Switched back to the parent window");
    }

    public void switchToNewWindow() {
        Set<String> s = getDriver().getWindowHandles();
        for (String child_window : s) {
            if (!parent.equals(child_window)) {
                getDriver().switchTo().window(child_window);
                getDriver().manage().window().maximize();
                TestContext.getLogger().log(LogLevel.INFO, "Switched to the child window");
            }
        }
    }

    public void selectOptionFromDropDownUsingRobot(String dropDownLocator,String option) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        WebElement e = getElement(formatLocator(dropDownLocator));
        String optionLocator = dropDownLocator+"/option[@selected='selected']";
        e.sendKeys("");
        String uppercaseOption = option.toUpperCase();
        char[] c = uppercaseOption.toCharArray();
        for (char value : c) {
            WebElement selectedOption = getElement(optionLocator);
            if (selectedOption.getText().equals(option)) {
                break;
            }
            Thread.sleep(2000);
            if ((int) value == 40) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                Thread.sleep(200);
                robot.keyPress(KeyEvent.VK_9);
                Thread.sleep(200);
                robot.keyRelease(KeyEvent.VK_9);
                Thread.sleep(200);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            } else if ((int) value == 32) {
                robot.keyPress(KeyEvent.VK_SPACE);
                Thread.sleep(200);
                robot.keyRelease(KeyEvent.VK_SPACE);
            } else {
                robot.keyPress((int) value);
                Thread.sleep(200);
                robot.keyRelease((int) value);
            }
        }
    }

    public void saveAsFileUsingRobot(String filePath) throws InterruptedException, AWTException {
        Robot robot = new Robot();
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_F11);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_F11);
        Thread.sleep(2000);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_N);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_N);
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_TAB);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_DOWN);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_DOWN);
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        StringSelection stringSelection = new StringSelection(filePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, stringSelection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_F11);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_F11);
        Thread.sleep(200);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);

        Thread.sleep(2000);

    }

}

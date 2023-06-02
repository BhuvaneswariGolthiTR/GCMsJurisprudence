package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import com.epam.setup.systemsettings.ImportApplicationVariables;
import org.openqa.selenium.WebDriver;

public class GCMSJurisprudenceTopicPage extends PageWrapper {

    private static final String topicIframe = "xpath -> //iframe[@id='listaMateriaSubmateria']";
    private static final String topicNewButton = "xpath -> //input[@id='btnNewTopic']";
    private static final String topicValueFilter = "xpath -> //input[@id='txtFilter']";
    private static final String topicEllipsiButton = "xpath -> //input[@id='btnFilter']";
    private static final String selectTopicValue = "xpath -> //a[@title='???en.select???']";
    private static final String subTopicNewButton = "xpath -> //input[@id='btnNewSubTopic']";
    private static final String topic = "xpath -> //input[@id='listaMateria_4']/parent::td";
    private static final String topicValue = "xpath -> //input[@id='listaMateria_4']/parent::td/text()";
    private static final String subTopic = "xpath -> //input[@id='listaSubmateria_68523681']/parent::td";;

    public GCMSJurisprudenceTopicPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTopic(String... formatArgs) {
        return formatLocator(topic, formatArgs);
    }

    public String getSubTopic(String... formatArgs) {
        return formatLocator(subTopic,formatArgs);
    }

    public void setTopicValueFilter(String value) {
        setTextUsingJS(topicValueFilter, "TopicValueInputBox", value);
    }

    public void switchToTopicFrame() {
        switchToIframeByElement(topicIframe);
    }

    public void clickTopicNewButton() {
        clickElementUsingJS(topicNewButton, "New");
    }

    public void clickEllipsiButton() {
        clickElementUsingJS(topicEllipsiButton, "...");
    }

    public void clickNewButton() {
        switchToIframeByElement(topicIframe);
        clickTopicNewButton();
    }

    public void clickOnSearchedValue() {
        clickElementUsingJS(selectTopicValue, "Select topic value");
    }

    public void clickOnNewButtonOfSubTopicSection() {
        clickElementUsingJS(subTopicNewButton, "new button sub topic value");
    }

    private void setSubTopicValue(String value) {
        setTextUsingJS(topicValueFilter, "TopicValueInputBox", value);
    }

    public void searchTopicValue() {
        switchToNewWindow();
        setTopicValueFilter(ImportApplicationVariables.topic);
        clickEllipsiButton();
    }

    public void selectTopicValue() {
        clickOnSearchedValue();
        switchToParentWindow();
    }

    public void clickSubTopicNewButton() {
        switchToIframeByElement(topicIframe);
        clickOnNewButtonOfSubTopicSection();
    }

    public void searchSubTopicValue() {
        switchToNewWindow();
        setSubTopicValue(ImportApplicationVariables.subTopic);
        clickEllipsiButton();
    }

    public void selectSubTopicValue() {
        clickOnSearchedValue();
        switchToParentWindow();
    }

}

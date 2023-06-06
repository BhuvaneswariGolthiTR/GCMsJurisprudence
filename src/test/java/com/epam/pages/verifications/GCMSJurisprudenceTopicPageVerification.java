package com.epam.pages.verifications;

import com.epam.pages.actions.GCMSJurisprudenceTopicPage;
import com.epam.setup.systemsettings.ImportApplicationVariables;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GCMSJurisprudenceTopicPageVerification extends GCMSJurisprudenceTopicPage {

    public GCMSJurisprudenceTopicPageVerification(WebDriver webDriver) {
        super(webDriver);
    }
    public boolean flag;
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

    public boolean verifyTopicAndSubTopicDataIsPresent() throws InterruptedException {
        switchToTopicFrame();
        List<WebElement> listMemo = webDriver.findElements(By.xpath("//td[text()='Topic:' or text()='Materia:']/following::input[@name='listaMateria']"));
        System.err.println("size is"+listMemo.size());
        if (listMemo.size() > 0) {
            for(int i=0;i<listMemo.size();i++){
                System.err.println("Topic and SubTopic Data exists");
                clickOnTopicDeleteButton();
                switchToNewWindow();
                clickOnYesButton();
                switchToParentWindow();
                switchToDefaultContent();
                switchToTopicFrame();
                Thread.sleep(3000);
                flag = true;
            }
        } else {
            System.err.println("Topic and SubTopic Data deleted");
        }
        flag = false;
        return flag;
    }

    public void deleteExistingTopicAndSubTopics() throws InterruptedException {
            verifyTopicAndSubTopicDataIsPresent();
    }

    public void deletePracticeAreaIfExists() {
        List<WebElement> element = webDriver.findElements(By.xpath("//table[@id='tablaPlantillaAreas0']"));
        if (element.size() > 0) {
            System.err.println("Practice Area exists");
            for (int i = 0; i < element.size(); i++) {
                setRemovePracticeArea();
                clickOnOkButton();
            }
        } else {
            System.err.println("Practice Area deleted");
        }
    }

}

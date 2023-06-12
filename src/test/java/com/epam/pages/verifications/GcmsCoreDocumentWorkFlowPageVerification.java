package com.epam.pages.verifications;

import com.epam.pages.actions.GcmsCoreDocumentWorkFlowPage;
import org.openqa.selenium.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GcmsCoreDocumentWorkFlowPageVerification extends GcmsCoreDocumentWorkFlowPage {

    public GcmsCoreDocumentWorkFlowPageVerification(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean flag;

    public void UploadFile() throws InterruptedException {

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reference\\" + "PROV_2019_89063.original.XML";
        String cutupfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reference\\" + "PROV_2019_89063.finalized.XML";
        setText(filePath,getFileBrowseButton(),"Browse Button");
        Thread.sleep(10000);
        setText(cutupfilePath, getCutUpFileBrowseButton(),"Cut up Browse Button");
        Thread.sleep(6000);
    }


    public void navigateToPreSelectionAndAssignAgentValue(String value) throws InterruptedException {
        Thread.sleep(3000);
        clickOnEllipseAndSelectTheValueFromTheList(value);
        clickOnAssignButton();
        Thread.sleep(3000);
    }

    public void verifyEndOfTheDocumentEndPreSelectionIsDisplayed() {
        verifyIsElementDisplayed("End pre selection text is displayed", getEndPreSelectionButton(), true, "end pre selection text");
    }

    public void enterAnalysisAndQualityValuesFromTheList(String value, String qualityValue) throws InterruptedException {
        clickOnAnalysisLevelEllipseAndSelectTheValueFromTheList(value);
        clickOnQualityEllipseAndSelectTheValueFromTheList(qualityValue);

    }

    public void clickOnAnalysisLevelEllipseAndSelectTheValueFromTheList(String value) throws InterruptedException {
        clickOnAnalysisValueEllipse();
        Thread.sleep(2000);
        switchToNewWindowAndEnterAndSearchAgentValue(value);

    }

    public void clickOnQualityEllipseAndSelectTheValueFromTheList(String value) throws InterruptedException {
        clickOnQualityEllipse();
        Thread.sleep(2000);
        switchToNewWindowAndEnterAndSearchAgentValue(value);

    }

    public void switchToNewWindowAndEnterAndSearchAgentValue(String value) throws InterruptedException {
        switchToNewWindow();
        enterAgentValueFromTheList(value);
        clickOnEllipseButton();
        clickOnSearchedAgentValue();
        switchToParentWindow();
        Thread.sleep(2000);
    }

    public void selectAndAssignCitiatorValueFromTheList(String value) throws InterruptedException {
        clickOnEllipseAndSelectTheValueFromTheList(value);
        clickOnAssignButton();
        Thread.sleep(2000);
    }

    public void verifyAssignedAgentValueIsDisplayed(String value) {
        switchToControlDataFrame();
        verifyIsElementDisplayed("Agent value displayed",getAgentValue(value), true, "Agent value");
        switchToDefaultContent();
    }

    public void clickOnEllipseAndSelectTheValueFromTheList(String value) throws InterruptedException {
        clickOnEllipseButton();
        Thread.sleep(2000);
        switchToNewWindow();
        enterAgentValueFromTheList(value);
        clickOnEllipseButton();
        clickOnSearchedAgentValue();
        switchToParentWindow();
        Thread.sleep(2000);
    }


    public void switchToControlDataFrame() {
        webDriver.switchTo().frame("fichas_datosControl");

    }

    public void switchToTextFrame() {
        webDriver.switchTo().frame("fichas_textos");

    }

    public void verifyImportButtonUnderTextSectionIsDisplayed() {
        verifyIsElementDisplayed("Import button is displayed",getImportButton(), true, "Import Button");
    }

    public boolean verifyOperationSuccessMessageIsDisplayed() {
        verifyIsElementDisplayed("Operation success message displayed", getSuccessMessage(), true, "Success Message");
        return true;
    }

    public void clickOnAreaValueEllipseAndSelectTheValueFromTheList(String value) throws InterruptedException {
        clickOnAreaValueEllipse();
        Thread.sleep(2000);
        switchToNewWindowAndEnterAndSearchAgentValue(value);

    }

    public void clickOnAnalistaValueEllipseAndSelectTheValueFromTheList(String value) throws InterruptedException {
        clickOnAnalistaValueEllipse();
        Thread.sleep(2000);
        switchToNewWindow();
        enterAgentValueFromTheList(value);
        clickOnEllipseButton();
        selectAnalistaValue();
        switchToParentWindow();
        Thread.sleep(2000);
    }

    public void editXMLDocument(String param) throws InterruptedException, AWTException {
        saveAsFileUsingRobot(param);
        try {
            String filepath = param;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);
            Node startingTagName = doc.getElementsByTagName("en-origen").item(0);
            String textValue = startingTagName.getNodeValue();
            System.err.println("present value:" + textValue);
            startingTagName.setTextContent("CITEDVERSION T.S.J.ASTURIAS SALA CIV/PE");
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
            System.err.println("Done");
        }
        catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public void uploadDownLoadedFile() throws InterruptedException {
        switchToNewWindow();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reference\\" + "PROV_2019_89063.XML";
        setText(filePath,getImportFilePath(),"Import File Button");
        Thread.sleep(2000);
        clickOnOkButton();
        Thread.sleep(2000);
        webDriver.close();
        Thread.sleep(2000);
        switchToParentWindow();
        Thread.sleep(5000);

    }

    public void navigateToControlSectionWorkFlow(String value) throws InterruptedException {
        navigateToJurisprudenceControlDataSection();
        clickDocument(value);
    }

    public void verifyUploadXmlDocumentsSuccessfully(String document) throws InterruptedException {
        if (getElement(getMessageHeader()).getText().contains("Error")) {
            clickOnGoBackButton();
            System.err.println("load is Unsuccessful");
            navigateToControlSectionWorkFlow(document);
            Thread.sleep(2000);
            clickOnLoadOrginalTextButton();
            UploadFile();
            Thread.sleep(2000);
            clickOnOkButton();
            Thread.sleep(30000);
            verifyOperationSuccessMessageIsDisplayed();
            clickOnGoBackButton();

        } else {
            verifyOperationSuccessMessageIsDisplayed();
            System.err.println("load is successful");
            Thread.sleep(2000);
            clickOnGoBackButton();
            Thread.sleep(2000);
        }
    }

    public void deleteTheExistingFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

}










     


    

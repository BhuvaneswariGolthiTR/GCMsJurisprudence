package com.epam.pages.verifications;

import com.epam.pages.actions.GcmsCoreDocumentWorkFlowPage;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class GcmsCoreDocumentWorkFlowVerificationPage extends GcmsCoreDocumentWorkFlowPage {

    public GcmsCoreDocumentWorkFlowVerificationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean flag;

    public void UploadFile() throws InterruptedException {

        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reference\\" + "PROV_2019_89063.original.XML";
        String cutupfilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reference\\" + "PROV_2019_89063.finalized.XML";
       // webDriver.findElement(By.xpath("//input[@name='fichero']")).sendKeys(Keys.SPACE);
        webDriver.findElement(By.xpath("//input[@name='fichero']")).sendKeys(filePath);
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//input[@name='ficheroTroceado']")).sendKeys(cutupfilePath);
        Thread.sleep(2000);
    }


    public void navigateToPreSelectionAndAssignAgentValue(String value) throws InterruptedException {
       // navigateToWorkFlowPreSelectionPage();
        Thread.sleep(3000);
        clickOnEllipseAndSelectTheValueFromTheList(value);
        clickOnAssignButton();
        Thread.sleep(3000);
    }

    public void verifyEndOfTheDocumentEndPreSelectionIsDisplayed() {
        verifyIsElementDisplayed("End pre selection text is displayed", "xpath -> //input[@id='btnEndPreSelection']", true, "end pre selection text");
    }

    public void verifyDocumentEditionTextPageIsDisplayed() {
        WebElement element = webDriver.findElement(By.id("btnText"));
        verifyIsElementDisplayed("Document Edition text is displayed", "xpath -> //input[@id='btnText']", true, "Document Edition text");
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
        verifyIsElementDisplayed("Agent value displayed", String.format("xpath -> //td[contains(text(),'%s')]", value), true, "Agent value");
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
        verifyIsElementDisplayed("Import button is displayed", "xpath ->  //input[@type='button' and contains(@onclick,'javascript:importTextosCitadoNuevo(216628602);')]", true, "Import Button");
    }

    public void verifyOperationSuccessMessageIsDisplayed() {
        verifyIsElementDisplayed("Operation success message displayed", "xpath ->  //b[@id='successMessage']", true, "Success Message");
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

    public void downloadAndEditXmlDocument() throws Exception {
        switchToTextFrame();
        clickOnExportButton();
        Thread.sleep(5000);
        switchToNewWindow();
        // SaveAndViewDownloadedFile();
        Dimension i = webDriver.manage().window().getSize();
        System.out.println("Dimension x and y :" + i.getWidth() + " " + i.getHeight());
        //3. Get the height and width of the screen
//        int x = (i.getWidth()/4)+20;
//        int y = (i.getHeight()/10)+50;
        Thread.sleep(5000);
        Robot robot = new Robot();
        robot.mouseMove(129, 573);
        robot.delay(2000);
        Thread.sleep(8000);
        //Clicks Left mouse button
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        System.out.println("Browse button clicked");
        Thread.sleep(2000);
        //editXMLDocument("CITED VERSION");
        // waitForDownloadToComplete(new File("C:\\Users\\C288577\\Documents"),"PROV_2019_89063");
    }

    public void SaveAndViewDownloadedFile() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        // webDriver.findElement(By.xpath("//td[contains(text(),'Texto Finalizado')]/following::a[6]")).sendKeys("");
        //  Thread.sleep(1000);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(9000);
        robot.setAutoDelay(250);
        robot.keyPress(KeyEvent.VK_CONTROL);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_ALT);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_S);
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        System.err.print("before title of the page *************************");
        js.executeScript("return document.title");
        // return the title of the page
        String title = (String) js.executeScript("return document.title");
        System.err.print("title of the page *************************" + title);
        Thread.sleep(10000);
    }

    public static File waitForDownloadToComplete(File downloadPath, String fileName) throws Exception {
        boolean isFileFound = false;
        int waitCounter = 0;
        while (!isFileFound) {
            System.out.println("Waiting For Download To Complete....");
            for (File tempFile : downloadPath.listFiles()) {
                if (tempFile.getName().contains(fileName)) {
                    String tempEx = FilenameUtils.getExtension(tempFile.getName());
                    // crdownload - For Chrome, part - For Firefox
                    if (tempEx.equalsIgnoreCase("crdownload") || tempEx.equalsIgnoreCase("part")) {
                        Thread.sleep(1000);
                    } else {
                        isFileFound = true;
                        System.out.println("Download To Completed....");
                        return tempFile;
                    }
                }
            }
            Thread.sleep(1000);
            waitCounter++;
            if (waitCounter > 25) {
                isFileFound = true;
            }
        }
        throw new Exception("File Not Downloaded");
    }

    public void editXMLDocument(String param) throws ParserConfigurationException, IOException, SAXException, TransformerException, InterruptedException, AWTException {
        saveAsFileUsingRobot(param);
        try {
            String filepath = param;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);

            Node data= doc.getFirstChild();

            Node startDate = doc.getElementsByTagName("en-origen").item(0);

            // I am not doing any thing with it just for showing you
            String currentStartDate = startDate.getNodeValue();
            System.err.println("present value:" +currentStartDate);
            startDate.setTextContent("CITEDVERSION T.S.J.ASTURIAS SALA CIV/PE");

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);

            System.err.println("Done");

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void uploadDownLoadedFile() throws InterruptedException {
        switchToNewWindow();
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\reference\\" + "PROV_2019_89063.XML";
        webDriver.findElement(By.xpath("//input[@id='myFile']")).sendKeys(filePath);
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
        WebElement element = webDriver.findElement(By.xpath("//td[@class='messageHeader']"));
        if (element.getText().contains("Error")) {
            clickOnGoBackButton();
            System.err.println("load is Unsuccessful");
            navigateToControlSectionWorkFlow(document);
            Thread.sleep(2000);
            clickOnLoadOrginalTextButton();
            UploadFile();
            Thread.sleep(2000);
            clickOnOkButton();
            Thread.sleep(2000);
            verifyOperationSuccessMessageIsDisplayed();
            Thread.sleep(2000);
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
        if(file.exists()){
            file.delete();
        }
    }

}










     


    

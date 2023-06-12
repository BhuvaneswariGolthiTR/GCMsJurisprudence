package com.epam.pages.actions;

import com.epam.framework.ui.PageWrapper;
import com.epam.setup.systemsettings.ImportApplicationVariables;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GCMSJurisprudenceRelationshipPage extends PageWrapper {

    private static final String relationshipsIframe = "xpath -> //iframe[@id='fichas_relaciones']";
    private static final String relationship = "xpath -> //td[contains(text(),'RELATIONSHIPS')]";
    private static final String relationshipTable = "xpath -> //table[@id='tablaRelaciones']";
    private static final String multipleButton = "xpath -> //input[@id='btnAddMultiple']";
    private static final String newRelationIframe = "xpath -> //iframe[@name='nuevaRelacion']";
    private static final String affectedDocIdCode = "xpath -> //input[@id='relacion.afectado.marginal.descripcionNmp']";
    private static final String affectedYear = "xpath -> //input[@id='afectado_nma']";
    private static final String affectedNum = "xpath -> //input[@id='afectado_nmn']";
    private static final String affectedUnitIdCode = "xpath -> //input[@id='relacion.afectado.unidad.precu']";
    private static final String affectedPart = "xpath -> //input[@id='afectado_precpDesc']";
    private static final String relationshipValue = "xpath -> //input[@id='relacion.datosRelacion.tipoRelacion.description']";
    private static final String relationshipNote = "xpath -> //textarea[@id='relacion.datosRelacion.notaAlTipo']";
    private static final String tickMark = "xpath -> //img[@id='botonGuardar']";
    private static final String closeBtn = "xpath -> //input[@id='btnClose']";
    private static final String commonRelationship = "xpath -> //td[text()='%s']";
    private static final String deleteLink = "xpath -> //table[@id='tablaRelaciones']//tr[@class='tdContent']//a[@id='lnkDelete[0]']";
    private static final String confirmationButton = "xpath -> //input[@id='btnSubmit']";

    public GCMSJurisprudenceRelationshipPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getRelationshipsIframe() {
        return formatLocator(relationshipsIframe);
    }

    public String getCommonRelationship(String value) {
        return formatLocator(String.format(commonRelationship, value));
    }

    public String getDeleteLink() {
        return formatLocator(deleteLink);
    }

    public void setAffectedDocIdCode(String value) {
        setTextUsingJS(affectedDocIdCode, "Cod", value);
    }

    public void setAffectedYear(String value) {
        setTextUsingJS(affectedYear, "Year", value);
    }

    public void setAffectedNum(String value) {
        setTextUsingJS(affectedNum, "Num", value);
    }

    public void setAffectedUnitIdCode(String value) {
        setTextUsingJS(affectedUnitIdCode, "Unit Identifier Code", value);
    }

    public void setAffectedPart(String value) {
        try {
            setTextUsingJS(affectedPart, "Part", value);
        }
        catch (StaleElementReferenceException e) {
            WebElement part = getElement(affectedPart);
            javascriptExecutor.executeScript("arguments[0].value='" + value + "'", part);
        }
    }

    public void setRelationshipValue(String value) {
        setTextUsingJS(relationshipValue, "Relationship", value);
    }

    public void setNote(String value) {
        try {
            setTextUsingJS(relationshipNote, "Note", value);
        }
        catch (StaleElementReferenceException e) {
            WebElement note = getElement(relationshipNote);
            javascriptExecutor.executeScript("arguments[0].value='" + value + "'", note);
        }
    }

    public void clickAddMultiple() {
        clickElementUsingJS(multipleButton, "Add Multiple");
    }

    public void clickTickMark() {
        clickElementUsingJS(tickMark, "Tick Mark");
    }

    public void clickCloseBtn() throws InterruptedException {
        Thread.sleep(5000);
        switchToDefaultContent();
        scrollToBottomOfPage();
        clickElementUsingJS(closeBtn, "Close");
        switchToParentWindow();
    }

    public void clickAddMultipleButton() {
        switchToIframeByElement(relationshipsIframe);
        scrollInView(relationship);
        try {
            if (isElementDisplayed(getCommonRelationship(ImportApplicationVariables.relationshipDataValue))) {
                switchToDefaultContent();
                deleteMultipleRelationships();
                switchToIframeByElement(relationshipsIframe);
            }
        }
        catch (TimeoutException ignored) {
        }
        clickAddMultiple();
    }

    public void fillDetailsInAffectedField() {
        switchToNewWindow();
        fillDetailsOfDocID();
        fillDetailsOfUnitIdentifier();
    }

    private void fillDetailsOfUnitIdentifier() {
        setAffectedUnitIdCode(ImportApplicationVariables.affectedUnitCode);
        setAffectedPart(ImportApplicationVariables.affectedUnitPartOne);
    }

    private void fillDetailsOfDocID() {
        switchToIframeByElement(newRelationIframe);
        setAffectedDocIdCode(ImportApplicationVariables.affectedCod);
        setAffectedYear(ImportApplicationVariables.affectedDocYear);
        setAffectedNum(ImportApplicationVariables.affectedDocNumber);
    }

    public void fillDetailsInRelationShipData() {
        setRelationshipValue(ImportApplicationVariables.relationshipDataValue);
        setNote(ImportApplicationVariables.relationshipDataNoteOne);
    }

    public void addNewRelationship() {
        setAffectedPart(ImportApplicationVariables.affectedUnitPartTwo);
        setNote(ImportApplicationVariables.relationshipDataNoteTwo);
    }

    public void deleteMultipleRelationships() {
        switchToIframeByElement(relationshipsIframe);
        List<WebElement> relationships = getElements(getCommonRelationship(ImportApplicationVariables.relationshipDataValue));
        int relationshipCount = relationships.size();
        for (int i = 0; i < relationshipCount; i++) {
            scrollInView(relationshipTable);
            clickElementUsingJS(deleteLink, "Delete");
            switchToNewWindow();
            clickElementUsingJS(confirmationButton, "Confirm Operation");
            switchToParentWindow();
            switchToIframeByElement(relationshipsIframe);
        }
        switchToDefaultContent();
    }

}

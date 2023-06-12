package com.epam.setup.systemsettings;

import com.epam.framework.ui.PageWrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ImportApplicationVariables {

    public static String loginURL;
    public static String username;
    public static String analystUsername;
    public static String citatorUsername;
    public static String password;
    public static String region;
    public static String fullMarginalId;
    public static String thesauri;
    public static String classificationEntryTopic;
    public static String commonIndexTerm;
    public static String reportYear;
    public static String reportNumber;
    public static String affectedCod;
    public static String affectedDocYear;
    public static String affectedDocNumber;
    public static String affectedUnitCode;
    public static String affectedUnitPartOne;
    public static String affectedUnitPartTwo;
    public static String relationshipDataValue;
    public static String relationshipDataNoteOne;
    public static String relationshipDataNoteTwo;
    public static String topic;
    public static String subTopic;
    public static String topicSubtopicCode;
    public static String hostFileName;
    public static String serverName;
    public static String fileName;
    public static String metadataLoadedFolder;
    public static String metadataRejectedFolder;
    public static String metadataAcceptedFolder;
    public static String metadataInputFolder;
    public static String metadataOutputFolder;
    public static String metadataLoadProcessFile;
    public static String metadataFileName;
    public static String documentName;
    public static String marginalNumber;
    public static String serverUsername;
    public static String serverPassword;
    public static String agentFilterValue;
    public static String agentValue;
    public static String NMNValue;
    public static String NMAValue;
    public static String analysisValue;
    public static String qualityValue;
    public static String citatorValue;
    public static String areaValue;
    public static String analistaValue;
    public static String workflowAgentValue;
    public static String citatorAgentValue;
    public static String assignedAgentValue;
    public static String analysisAgentValue;
    public static String marginalAgentValue;
    public static String workFlowMarginalNumber;
    public static String topicUsingCode;
    public static String subTopicUsingCode;
    public static Properties prop;

    public static void setVariables() throws IOException {
        prop = new Properties();
        String userPath = System.getProperty("user.dir");
        FileInputStream fs = new FileInputStream(userPath + "\\src\\test\\resources\\gcms.properties");
        prop.load(fs);

        loginURL = prop.getProperty("gcms.login.url");
        username = PageWrapper.decode(prop.getProperty("gcms.login.username"));
        password = PageWrapper.decode(prop.getProperty("gcms.login.password"));
        region = prop.getProperty("gcms.login.applicationTitle");

        fullMarginalId = prop.getProperty("gcms.jurisprudence.marginalID");
        thesauri = prop.getProperty("gcms.thesauriValue");
        classificationEntryTopic = prop.getProperty("gcms.classification.topicValue");
        commonIndexTerm = prop.getProperty("gcms.analystCommonIndexTerm");

        reportYear = prop.getProperty("gcms.reports.year");
        reportNumber = prop.getProperty("gcms.reports.number");

        affectedCod = prop.getProperty("gcms.relationship.affectedCode");
        affectedDocYear = prop.getProperty("gcms.relationship.affectedDocYear");
        affectedDocNumber = prop.getProperty("gcms.relationship.affectedDocNumber");
        affectedUnitCode = prop.getProperty("gcms.relationship.affectedUnitCode");
        affectedUnitPartOne = prop.getProperty("gcms.relationship.affectedUnitPartOne");
        affectedUnitPartTwo = prop.getProperty("gcms.relationship.affectedUnitPartTwo");
        relationshipDataValue = prop.getProperty("gcms.relationship.dataValue");
        relationshipDataNoteOne = prop.getProperty("gcms.relationship.dataNoteOne");
        relationshipDataNoteTwo = prop.getProperty("gcms.relationship.dataNoteTwo");

        topic = prop.getProperty("gcms.filter.topicValue");
        subTopic = prop.getProperty("gcms.filter.subTopicValue");

        topicSubtopicCode = prop.getProperty("gcms.topicSubTopicCode");

        hostFileName = prop.getProperty("gcms.uploadMetaData.hostFileName");
        serverUsername = prop.getProperty("gcms.uploadMetaData.server.username");
        serverPassword = prop.getProperty("gcms.uploadMetaData.server.password");
        serverName = prop.getProperty("gcms.uploadMetaData.serverName");
        fileName = prop.getProperty("gcms.uploadMetaData.file");
        metadataLoadedFolder = prop.getProperty("gcms.uploadMetaData.loadedFolder");
        metadataRejectedFolder = prop.getProperty("gcms.uploadMetaData.rejectedFolder");
        metadataAcceptedFolder = prop.getProperty("gcms.uploadMetaData.acceptedFolder");
        metadataInputFolder = prop.getProperty("gcms.uploadMetaData.inputFolderPath");
        metadataOutputFolder = prop.getProperty("gcms.uploadMetaData.outputFolderPath");
        metadataLoadProcessFile = prop.getProperty("gcms.uploadMetaData.loadProcessFile");
        metadataFileName = prop.getProperty("gcms.uploadMetaData.fileName");
        documentName = prop.getProperty("gcms.uploadMetaData.documentName");
        marginalNumber = prop.getProperty("marginalNumber");

        agentValue = prop.getProperty("gcms.workflow.agentValue");
        agentFilterValue = prop.getProperty("gcms.workflow.agentFilterValue");
        areaValue = prop.getProperty("gcms.workflow.areaValue");
        analistaValue = prop.getProperty("gcms.workflow.analistaValue");
        analysisValue = prop.getProperty("gcms.workflow.analysisValue");
        NMNValue = prop.getProperty("gcms.workflow.NMNValue");
        NMAValue = prop.getProperty("gcms.workflow.NMAValue");
        citatorValue = prop.getProperty("gcms.workflow.citatorValue");
        qualityValue = prop.getProperty("gcms.workflow.qualityValue");
        workFlowMarginalNumber = prop.getProperty("gcms.workflow.marginalNumber");
        workflowAgentValue = prop.getProperty("gcms.workflow.value");
        citatorAgentValue = prop.getProperty("gcms.workflow.citator.agentValue");
        assignedAgentValue = prop.getProperty("gcms.workflow.assignedAgentValue");


        analystUsername = PageWrapper.decode(prop.getProperty("gcms.workflow.analyst.username"));
        citatorUsername = PageWrapper.decode(prop.getProperty("gcms.workflow.citator.username"));

        marginalAgentValue = prop.getProperty("gcms.workflow.marginalAgentValue");
        analysisAgentValue = prop.getProperty("gcms.workflow.analysisAgentValue");


        topicUsingCode = prop.getProperty("gcms.code.topic");
        subTopicUsingCode = prop.getProperty("gcms.code.subTopic");
    }

}

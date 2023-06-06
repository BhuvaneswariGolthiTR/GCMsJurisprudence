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
    public static Properties prop;

    public static void setVariables() throws IOException {
        prop = new Properties();
        String userPath = System.getProperty("user.dir");
        FileInputStream fs = new FileInputStream(userPath + "\\src\\test\\resources\\gcms-arz-client.properties");
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

        topic = prop.getProperty("gcms.topicValue");
        subTopic = prop.getProperty("gcms.subTopicValue");

        topicSubtopicCode = prop.getProperty("gcms.topicSubTopicCode");

        hostFileName = prop.getProperty("hostFileName");
        serverUsername = prop.getProperty("username");
        serverPassword = prop.getProperty("password");
        serverName = prop.getProperty("serverName");
        fileName = prop.getProperty("file");
        metadataLoadedFolder = prop.getProperty("metadataLoadedFolder");
        metadataRejectedFolder = prop.getProperty("metadataRejectedFolder");
        metadataAcceptedFolder = prop.getProperty("metadataAcceptedFolder");
//		url = prop.getProperty("gcms.br.direct.url");
        metadataInputFolder = prop.getProperty("metadataInputFolderPath");
        metadataOutputFolder = prop.getProperty("metadataOutputFolderPath");
        metadataLoadProcessFile = prop.getProperty("metadataLoadProcessFile");
        metadataFileName = prop.getProperty("metadataFileName");
        documentName = prop.getProperty("documentName");
        marginalNumber = prop.getProperty("marginalNumber");

        agentValue = prop.getProperty("agentValue");
        agentFilterValue = prop.getProperty("agentFilterValue");
        areaValue = prop.getProperty("areaValue");
        analistaValue = prop.getProperty("analistaValue");
        analysisValue = prop.getProperty("AnalysisValue");
        NMNValue = prop.getProperty("NMNValue");
        NMAValue = prop.getProperty("NMAValue");
        citatorValue = prop.getProperty("citatorValue");
        qualityValue = prop.getProperty("QualityValue");
        workFlowMarginalNumber = prop.getProperty("workFlowMarginalNumber");
        workflowAgentValue = prop.getProperty("workflowValue");
        citatorAgentValue = prop.getProperty("citatorAgentValue");
        assignedAgentValue = prop.getProperty("assignedAgentValue");
        analysisAgentValue = prop.getProperty("analysisAgentValue");
        marginalAgentValue = prop.getProperty("marginalAgentValue");

        analystUsername = PageWrapper.decode(prop.getProperty("analyst.username"));
        citatorUsername = PageWrapper.decode(prop.getProperty("citator.username"));
    }

}

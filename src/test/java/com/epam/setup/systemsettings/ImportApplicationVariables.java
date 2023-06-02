package com.epam.setup.systemsettings;

import com.epam.framework.ui.PageWrapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ImportApplicationVariables {
	
	public static String loginURL;
	public static String username;
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
	public static Properties prop;

	public static void setVariables() throws IOException {
		prop = new Properties();
		String userPath = System.getProperty("user.dir");
		FileInputStream fs = new FileInputStream(userPath + "\\src\\test\\resources\\gcms-gulf-client.properties");
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
	}

}

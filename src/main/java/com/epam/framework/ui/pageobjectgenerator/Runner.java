package com.epam.framework.ui.pageobjectgenerator;

/*
 * This class calls helper to read a file and write objects based on language from config properties.
 * languageType java or python.
 */

import com.epam.framework.ui.pageobjectgenerator.helper.HelperReadJsonAndCreateClass;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Random;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] strings) {
//        HelperReadJsonAndCreateClass helperReadJsonAndCreateClass = new HelperReadJsonAndCreateClass();
//        helperReadJsonAndCreateClass.cleanRecordDirectory();
//        helperReadJsonAndCreateClass.readJSON();


        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray("Value".toCharArray());
        String encryptUsername = textEncryptor.encrypt("C290266");
        System.out.println(encryptUsername);
//        String encryptPassword=textEncryptor.encrypt("***");
//        System.out.println(encryptPassword);
//        String str = "Test: 286/202";
//        String reg = "Test: [0-9]+/[0-9]+";
//
//       if(str.matches(reg)){
//           System.out.println("Testyjf");
//       }
//        int number = 1;
//        String name = "Name[%s]";
//        System.out.println("The name is "+String.format(name,String.valueOf(number)));


    }

}
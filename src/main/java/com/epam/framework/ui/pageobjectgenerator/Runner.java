package com.epam.framework.ui.pageobjectgenerator;

/*
 * This class calls helper to read a file and write objects based on language from config properties.
 * languageType java or python.
 */

import com.epam.framework.ui.pageobjectgenerator.helper.HelperReadJsonAndCreateClass;
import org.jasypt.util.text.BasicTextEncryptor;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Pattern;

public class Runner {
    public static void main(String[] strings) throws IOException, UnsupportedFlavorException {
//        HelperReadJsonAndCreateClass helperReadJsonAndCreateClass = new HelperReadJsonAndCreateClass();
//        helperReadJsonAndCreateClass.cleanRecordDirectory();
//        helperReadJsonAndCreateClass.readJSON();


//        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
//        textEncryptor.setPasswordCharArray("Value".toCharArray());
//        String encryptUsername = textEncryptor.encrypt("C290276");
//        System.out.println(encryptUsername);
//        String encryptPassword=textEncryptor.encrypt("2EsteEsNuestro!LifeSaver");
//        System.out.println("Encrypted Password :"+encryptPassword);
//

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
        System.out.println("Text is"+x);
        String original = "Arz=DESCRIPCIÓN DE LA RESOLUCIÓN";
        if(x.equals(original)){
            System.err.println("TRUE");
        }

    }

}
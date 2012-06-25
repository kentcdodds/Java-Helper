package com.kentcdodds.javahelper.test;

import com.kentcdodds.javahelper.helpers.IOHelper;
import com.kentcdodds.javahelper.helpers.OtherHelper;
import com.kentcdodds.javahelper.helpers.PrinterHelper;
import com.kentcdodds.javahelper.helpers.SwingHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 *
 * @author Kent
 */
public class TestClass {

  public static String testImageLocation = "/com/kentcdodds/javahelper/resources/iSayHiGuy.jpg";
  public static String progressImageUrl = "http://c96265.r65.cf3.rackcdn.com/indicator_medium.gif";
  public static File ioPlaygroundDir = new File(IOHelper.homeDir + "\\IOPlayground\\");
  public static String prodDatabaseUrl;
  public static String devDatabaseUrl;
  public static String dbPassword;
  public static String dbUser;
  public static String gmailUser;
  public static String gmailPassword;
  public static String ldsUser;
  public static String ldsPassword;
  public static Properties mailServerProperties;
  public static Properties sqlProperties;

  public static void main(String[] args) throws Exception {
    setStuff();
    String fileToString = IOHelper.fileToString(ioPlaygroundDir.getPath() + "\\zipMeTest1.txt");
    System.out.println(fileToString);
//    TestArchive.email();
//    OtherHelper.scrambleString("Brooke is great!");
//    TestArchive.email();
//    System.out.println(System .getProperty("os.name"));
    System.exit(0);
  }

  /**
   * The stuff I want to set at the beginning of all my tests. Loads a sqlProperties file which holds the values to
   * personal variables (passwords, etc.)
   */
  public static void setStuff() throws IOException {
    SwingHelper.setSystemLookAndFeel();
    PrinterHelper.setInstancePrint(true);
    Properties prop = new Properties();

    prop.load(new FileInputStream("../java-helper.properties"));

    devDatabaseUrl = prop.getProperty("devDatabase");
    prodDatabaseUrl = prop.getProperty("prodDatabase");
    dbPassword = OtherHelper.descrambleString(prop.getProperty("dbScrambledPassword"));
    dbUser = prop.getProperty("dbUser");
    sqlProperties = new Properties();
    sqlProperties.put("user", dbUser);
    sqlProperties.put("password", dbPassword);
    
    mailServerProperties = new Properties();
    mailServerProperties.setProperty("mail.smtp.host", prop.getProperty("mail.smtp.host"));
    mailServerProperties.setProperty("mail.smtp.port", prop.getProperty("mail.smtp.port"));

    gmailUser = prop.getProperty("gmailUser");
    gmailPassword = OtherHelper.descrambleString(prop.getProperty("gmailScrambledPassword"));

    ldsUser = prop.getProperty("ldsUser");
    ldsPassword = OtherHelper.descrambleString(prop.getProperty("ldsScrambledPassword"));

  }
}

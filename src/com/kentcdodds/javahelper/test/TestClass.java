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
  public static Map<String, String> properties;

  public static void main(String[] args) throws Exception {
    setStuff();
    TestArchive.email();
    System.exit(0);
  }

  /**
   * The stuff I want to set at the beginning of all my tests. Loads a properties file which holds the values to
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
    properties = new TreeMap<>();
    properties.put("user", dbUser);
    properties.put("password", dbPassword);
    
    gmailUser = prop.getProperty("gmailUser");
    gmailPassword = OtherHelper.descrambleString(prop.getProperty("gmailScrambledPassword"));
    
    ldsUser = prop.getProperty("ldsUser");
    ldsPassword = OtherHelper.descrambleString(prop.getProperty("ldsScrambledPassword"));

  }


}

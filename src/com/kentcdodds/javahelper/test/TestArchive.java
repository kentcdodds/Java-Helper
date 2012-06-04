package com.kentcdodds.javahelper.test;

import com.kentcdodds.javahelper.helpers.*;
import com.kentcdodds.javahelper.model.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JWindow;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *
 * @author kentcdodds
 */
public class TestArchive {

    public static String testImageLocation = TestClass.testImageLocation;
  public static String progressImageUrl = TestClass.progressImageUrl;
  public static File ioPlaygroundDir = TestClass.ioPlaygroundDir;
  public static String prodDatabaseUrl = TestClass.prodDatabaseUrl;
  public static String devDatabaseUrl = TestClass.devDatabaseUrl;
  public static String dbPassword = TestClass.dbPassword;
  public static String dbUser = TestClass.dbUser;
  public static Map<String, String> properties = TestClass.properties;
  
    public static void executQueries() throws SQLException {
    HelperConnection helperConnection = new HelperConnection(devDatabaseUrl, properties);
    helperConnection.addQueryToQueue(new HelperQuery("select * from dual"));
    helperConnection.addQueryToQueue(new HelperQuery("select * from dual"));
    helperConnection.addQueryToQueue(new HelperQuery("select * from dual"));
    helperConnection.addQueryToQueue(new HelperQuery("select * from dual"));
    List<HelperQuery> executeQueue = helperConnection.executeQueue();
    for (int i = 0; i < executeQueue.size(); i++) {
      HelperQuery executedQuery = executeQueue.get(i);
      PrinterHelper.println(StringHelper.newline + "Query " + i + StringHelper.newline);
      SQLHelper.printResultSet(executedQuery.getResultSet());
    }
  }

  public static void executeAndPrintQuery() throws SQLException, FileNotFoundException, IOException {
    Map<String, String> props = new TreeMap<>();
    props.put("user", dbUser);
    props.put("password", dbPassword);
    ResultSet rs = SQLHelper.executeQuery(devDatabaseUrl, props, "select 'h' \"question\" from dual");
    SQLHelper.resultSetToCSVFile(rs, ioPlaygroundDir.getPath() + "test.csv");
  }

  public static void progressImage() throws IOException, InterruptedException {
    URL url = new URL(progressImageUrl);
    java.awt.Image image = Toolkit.getDefaultToolkit().createImage(url);
    JWindow window = SwingHelper.getProgressWheelWindow(new ImageIcon(image));
    window.setVisible(true);
    Thread.sleep(3000);
    window.setVisible(false);
  }

  public static void parseNumber() throws Exception {
    long numberHelperTotal = 0;
    long numberUtilsTotal = 0;
    long regExTotal = 0;
    long bruteForceTotal = 0;
    long scannerTotal = 0;
    int iterations = 5;
    for (int i = 0; i < iterations; i++) {
      long numberHelper = 0;
      long numberUtils = 0;
      long regEx = 0;
      long bruteForce = 0;
      long scanner = 0;
      for (int j = 0; j < 99999; j++) {
        long start;
        long end;
        Random rand = new Random();
        String string = ((rand.nextBoolean()) ? "" : "-") + String.valueOf(rand.nextDouble() * j);
        //NumberHelper
        start = System.nanoTime();
        NumberHelper.isValidNumber(double.class, string);
        end = System.nanoTime();
        numberHelper += end - start;

        //NumberUtils
        start = System.nanoTime();
        NumberUtils.isNumber(string);
        end = System.nanoTime();
        numberUtils += end - start;

        //RegEx
        start = System.nanoTime();
        Pattern p = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+$");
        Matcher m = p.matcher(string);
        if (m.matches()) {
          Double.parseDouble(string);
        }
        end = System.nanoTime();
        regEx += end - start;

        //Brute Force (not international support) and messy support for E and negatives
        //This is not the way to do it...
        start = System.nanoTime();
        int decimalpoints = 0;
        for (char c : string.toCharArray()) {
          if (Character.isDigit(c)) {
            continue;
          }
          if (c != '.') {
            if (c == '-' || c == 'E') {
              decimalpoints--;
            } else {
              //return false
              //because it should never return false in this test, I will throw an exception here if it does.
              throw new Exception("Brute Force returned false! It doesn't work! The character is " + c + " Here's the number: " + string);
            }
          }
          if (decimalpoints > 0) {
            //return false
            //because it should never return false in this test, I will throw an exception here if it does.
            throw new Exception("Brute Force returned false! It doesn't work! The character is " + c + " Here's the number: " + string);
          }
          decimalpoints++;
        }
        end = System.nanoTime();
        bruteForce += end - start;

        //Scanner
        start = System.nanoTime();
        Scanner scanNumber = new Scanner(string);
        if (scanNumber.hasNextDouble()) {//check if the next chars are integer
          //return true;
        } else {
          //return false;
          //because it should never return false in this test, I will throw an exception here if it does.
          throw new Exception("Scanner returned false! It doesn't work! Here's the number: " + string);
        }
        end = System.nanoTime();
        scanner += end - start;

        //Increase averages
        //For debug:
        //System.out.println("String: " + string);
        //System.out.println("NumberHelper: " + numberHelper);
        //System.out.println("NumberUtils: " + numberUtils);
        //System.out.println("RegEx: " + regEx);
        //System.out.println("Brute Force: " + bruteForce);
        //System.out.println("Scanner: " + scanner);
      }
      numberHelperTotal += numberHelper;
      numberUtilsTotal += numberUtils;
      regExTotal += regEx;
      bruteForceTotal += bruteForce;
      scannerTotal += scanner;
    }

    long numberHelperAvg = numberHelperTotal / iterations;
    long numberUtilsAvg = numberUtilsTotal / iterations;
    long regExAvg = regExTotal / iterations;
    long bruteForceAvg = bruteForceTotal / iterations;
    long scannerAvg = scannerTotal / iterations;
    System.out.println("NumberHelper: " + (numberHelperAvg / 1000000) + " milliseconds -> " + (numberHelperAvg / 1000000000) + " seconds");
    System.out.println("NumberUtils: " + (numberUtilsAvg / 1000000) + " milliseconds -> " + (numberUtilsAvg / 1000000000) + " seconds");
    System.out.println("RegEx: " + (regExAvg / 1000000) + " milliseconds -> " + (regExAvg / 1000000000) + " seconds");
    System.out.println("Brute Force: " + (bruteForceAvg / 1000000) + " milliseconds -> " + (bruteForceAvg / 1000000000) + " seconds");
    System.out.println("Scanner: " + (scannerAvg / 1000000) + " milliseconds -> " + (scannerAvg / 1000000000) + " seconds");
  }

  /**
   * Tests for unzip methods
   *
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void unzip() throws FileNotFoundException, IOException, Exception {
//    File zipped = new File(ioPlaygroundDir, "unzip-haha.zip");
//    File zipOutput = new File(ioPlaygroundDir, "unzip-hahe\\");
//    zipOutput.mkdir();
//    IOHelper.unzipFiles(zipped, zipOutput);


    HelperFile hFile1 = new HelperFile(new File(ioPlaygroundDir, "wiki1.txt"));
    HelperFile hFile2 = new HelperFile(new File(ioPlaygroundDir, "wiki2.txt"));
    byte[] zipFiles = IOHelper.zipFiles(hFile1, hFile2);
    List<HelperFile> unzipFiles = IOHelper.unzipFiles(new HelperFile(zipFiles, "This is the zip"));
    for (HelperFile helperFile : unzipFiles) {
      IOHelper.saveBytesToFile(helperFile.getBytes(), ioPlaygroundDir + "\\helperout\\" + helperFile.getName());
    }
  }

  /**
   * Tests for zip methods and benchmarking
   *
   * @throws FileNotFoundException
   * @throws IOException
   * @throws Exception
   */
  public static void zip() throws FileNotFoundException, IOException, Exception {
    //Setup files
    File file1 = new File(IOHelper.homeDir + "\\wiki1.txt");
    File file2 = new File(IOHelper.homeDir + "\\wiki2.txt");
    HelperFile hFile1 = new HelperFile(file1);
    HelperFile hFile2 = new HelperFile(file2);

    long start1 = System.nanoTime();
    for (int i = 0; i < 10; i++) {
      IOHelper.zipFiles(new File(IOHelper.homeDir + "\\Test With Files.zip"), file1, file2);
    }
    long end1 = System.nanoTime();
    long diff1 = end1 - start1;
    System.out.println("Test with files time: " + diff1);


    long start2 = System.nanoTime();
    for (int i = 0; i < 10; i++) {
      byte[] zipFiles = IOHelper.zipFiles(hFile1, hFile2);
      IOHelper.saveBytesToFile(zipFiles, IOHelper.homeDir + "\\Test with Helpers.zip");
    }
    long end2 = System.nanoTime();
    long diff2 = end2 - start2;
    System.out.println("Test with Helpers time: " + diff2);
    System.out.println("Time difference (diff1 - diff2): " + (diff1 - diff2));
  }

  public static void email() throws MessagingException {
    String from = "from";
    List<String> to = new ArrayList<>();
    to.add("dfkewofds@mailinator.com");
    List<String> cc = new ArrayList<>();
    cc.add("dfkefofds@mailinator.com");
    List<String> bcc = new ArrayList<>();
    bcc.add("gfdjakl@mailinator.com");
    String subject = "This is a test subject!";
    Email email = new Email(from, to, cc, bcc, subject, "This is the message!");
    EmailAttachment attachment = new EmailAttachment();
//    attachment.setFile(new File("C:\\Users\\kentcdodds\\Documents\\test attachment.txt"));
    attachment.setFile(new File("C:\\Users\\Kent\\test.txt"));
    email.addEmailAttachment(attachment);
    Session session = EmailHelper.getYahooSession("yahoo_user", "yahoo_password");
    session.setDebug(true);
    EmailHelper.sendEmail(session, email);
//    System.out.println(ReflectionHelper.getObjectInString(email, true, 1, true, 1));
    System.out.println("Email sent!");
  }

  /**
   * Test resizeImage functionality
   */
  public static void resizeImage() throws IOException {
    BufferedImage im = ImageIO.read(TestClass.class.getResource(testImageLocation));
    Image resizeImage = SwingHelper.resizeImage(im, 500, 500, true);
    JLabel label = new JLabel(new ImageIcon(resizeImage));
    JDialog dialog = new JDialog();

    dialog.setModal(
            true); //Important if System.exit(0) is called :)
    dialog.getContentPane().add(label);
    SwingHelper.centerAndPack(dialog);

    dialog.setVisible(
            true);
  }

  public static void random() {
    boolean testDate = false;
    boolean testRandomStrings = true;
    int numberOfTests = 100;

    if (testDate) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date oldest = RandomHelper.getRandomDate(2012, 2012);
      Date newest = RandomHelper.getRandomDate(2012, 2012);
      for (int i = 0; i < numberOfTests; i++) {
        Date randomDate = RandomHelper.getRandomDate(2012, 2012);
        System.out.println(sdf.format(randomDate));
        if (randomDate.getTime() < oldest.getTime()) {
          oldest = randomDate;
        }
        if (randomDate.getTime() > newest.getTime()) {
          newest = randomDate;
        }
      }
      System.out.println();
      System.out.println("Oldest: " + sdf.format(oldest));
      System.out.println("Newest: " + sdf.format(newest));
    }
    if (testRandomStrings) {
      for (int i = 0; i < numberOfTests; i++) {
        String randomFirstName = RandomHelper.getRandomFirstName();
        String randomLastName = RandomHelper.getRandomLastName();
        String randomState = RandomHelper.getRandomState();
        String state = RandomHelper.getState(randomState);
        String randomCity = RandomHelper.getRandomCity(randomState);
        System.out.println(randomFirstName + " " + randomLastName);
        System.out.println(RandomHelper.getRandomPhoneNumber() + ", " + RandomHelper.getEmail(randomFirstName, randomLastName, "MyStuff"));
        System.out.println(RandomHelper.getRandomStreetAddress() + ", " + randomCity + ", "
                + randomState + " (" + state + ") " + RandomHelper.getRandomZipCode());
        System.out.println("Computer ID: " + RandomHelper.getRandomMacAddress());
        System.out.println();
      }
    }
  }

  /**
   * Tests some of the date helper stuff
   */
  public static void dateHelper() {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    //Cal 1: January 1st, 2012 12:00:00.000
    cal1.set(2012, 1, 1, 12, 0, 0);
    cal1.set(Calendar.MILLISECOND, 000);
    //Cal 2: January 2nd, 2012 11:59:59.999
    System.out.println(cal1.getTime());
    cal2.set(2012, 1, 2, 12, 59, 59);
    cal2.set(Calendar.MILLISECOND, 999);
    System.out.println("Cal1: " + cal1.getTime());
    System.out.println("Cal2: " + cal2.getTime());
    System.out.println("DaysDifference: " + DateHelper.getAbsoluteDaysDifference(cal1.getTime(), cal2.getTime()));

    //Cal 1: December 31st, 1999 23:59:59.999
    cal1.set(Calendar.YEAR, 1999);
    cal1.set(Calendar.DAY_OF_YEAR, cal1.getActualMaximum(Calendar.DAY_OF_YEAR));
    cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMaximum(Calendar.HOUR_OF_DAY));
    cal1.set(Calendar.MINUTE, cal1.getActualMaximum(Calendar.MINUTE));
    cal1.set(Calendar.SECOND, cal1.getActualMaximum(Calendar.SECOND));
    cal1.set(Calendar.MILLISECOND, cal1.getActualMaximum(Calendar.MILLISECOND));
    //Cal 2: January 1st, 2000 00:00:00.000
    cal2.set(Calendar.YEAR, 2000);
    cal2.set(Calendar.DAY_OF_YEAR, cal2.getActualMinimum(Calendar.DAY_OF_YEAR));
    cal2.set(Calendar.HOUR_OF_DAY, cal2.getActualMinimum(Calendar.HOUR_OF_DAY));
    cal2.set(Calendar.MINUTE, cal2.getActualMinimum(Calendar.MINUTE));
    cal2.set(Calendar.SECOND, cal2.getActualMinimum(Calendar.SECOND));
    cal2.set(Calendar.MILLISECOND, cal2.getActualMinimum(Calendar.MILLISECOND));

    System.out.println("Cal1: " + cal1.getTime());
    System.out.println("Cal2: " + cal2.getTime());
    System.out.println("DaysDifference: " + DateHelper.getDaysDifference(cal1.getTime(), cal2.getTime()));
  }
  
}

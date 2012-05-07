package com.kentcdodds.javahelper.test;

import com.kentcdodds.javahelper.helpers.*;
import com.kentcdodds.javahelper.model.Email;
import com.kentcdodds.javahelper.model.EmailAttachment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author Kent
 */
public class TestClass {
  
  public static String testImageLocation = "/com/kentcdodds/javahelper/resources/iSayHiGuy.jpg";
  
  public static void main(String[] args) throws Exception {
    setStuff();
    String st = "This is a test. I hope this works!";
    IOHelper.saveBytesToFile(st.getBytes(), "C:\\Users\\kentcdodds\\tester.txt");
//    email();
    //    List<File>[] replaced = IOHelper.replaceInAllFiles(new File("C:\\Users\\Kent\\Documents\\My Dropbox\\Work\\MGF\\NetBeansProjects\\MyVideoFacilitator\\src"),
    //            5, "org/mvf/resources/", "/org/mvf/resources/");
    //    PrinterHelper.setInstancePrint(true);
    //    IOHelper.sendReplaceInAllFilesToPrinter(replaced);
    System.exit(0);
  }
  
  public static void email() throws MessagingException {
    String from = "test@example.org";
    List<String> to = new ArrayList<>();
    to.add("dfkewofds@mailinator.com");
    List<String> cc = new ArrayList<>();
    cc.add("dfkefofds@mailinator.com");
    List<String> bcc = new ArrayList<>();
    bcc.add("me@kentcdodds.com");
    String subject = "This is a test subject!";
    Email email = new Email(from, to, cc, bcc, subject, "This is the message!");
    EmailAttachment attachment = new EmailAttachment();
    attachment.setFile(new File("C:\\Users\\kentcdodds\\Documents\\test attachment.txt"));
    email.addEmailAttachment(attachment);
    EmailHelper.sendEmail(EmailHelper.getGoogleSession("tester", "yourpassword"), email);
//    System.out.println(ReflectionHelper.getObjectInString(email, true, 1, true, 1));
    System.out.println("Email sent!");
  }

  /**
   * The stuff I want to set at the beginning of all my tests.
   */
  public static void setStuff() {
    SwingHelper.setSystemLookAndFeel();
    PrinterHelper.setInstancePrint(true);
  }

  /**
   * Test resizeImage functionality
   */
  public static void resizeImage() throws IOException {
    BufferedImage im = ImageIO.read(TestClass.class.getResource(testImageLocation));
    Image resizeImage = SwingHelper.resizeImage(im, 500, 500, true);
    JLabel label = new JLabel(new ImageIcon(resizeImage));
    JDialog dialog = new JDialog();
    dialog.setModal(true); //Important if System.exit(0) is called :)
    dialog.getContentPane().add(label);
    SwingHelper.centerAndPack(dialog);
    dialog.setVisible(true);
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

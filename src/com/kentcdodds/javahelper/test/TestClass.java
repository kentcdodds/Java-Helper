package com.kentcdodds.javahelper.test;

import com.kentcdodds.javahelper.helpers.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import javax.imageio.ImageIO;
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
    resizeImage();
    //    List<File>[] replaced = IOHelper.replaceInAllFiles(new File("C:\\Users\\Kent\\Documents\\My Dropbox\\Work\\MGF\\NetBeansProjects\\MyVideoFacilitator\\src"),
    //            5, "org/mvf/resources/", "/org/mvf/resources/");
    //    PrinterHelper.setInstancePrint(true);
    //    IOHelper.sendReplaceInAllFilesToPrinter(replaced);
    System.exit(0);
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
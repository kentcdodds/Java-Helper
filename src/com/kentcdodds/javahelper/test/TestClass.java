package com.kentcdodds.javahelper.test;

import com.kentcdodds.javahelper.helpers.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kent
 */
public class TestClass {

  public static void main(String[] args) throws Exception {
    SwingHelper.setSystemLookAndFeel();
    PrinterHelper.setInstancePrint(true);
    for (int i = 0; i < 10; i++) {
      PrinterHelper.print("Hi!");
      PrinterHelper.printErr("Err!");
    }
    PrinterHelper.showMessageConsole("Output info");
    for (int i = 0; i < 300; i++) {
      Thread.currentThread().sleep(200);
      PrinterHelper.print("Hi! - 2");
      PrinterHelper.printErr("Err! - 2");
    }
    Thread.currentThread().sleep(1000);
    PrinterHelper.showMessageConsole();
    for (int i = 0; i < 10; i++) {
      Thread.currentThread().sleep(500);
      PrinterHelper.print("Hi! - 3");
      PrinterHelper.printErr("Err! - 3");
    }
    System.exit(0);
    //    List<File>[] replaced = IOHelper.replaceInAllFiles(new File("C:\\Users\\Kent\\Documents\\My Dropbox\\Work\\MGF\\NetBeansProjects\\MGFEncode\\src"), 5, "System.out", "System.out");
    //    List<File> appliedFiles = replaced[0];
    //    List<File> unappliedFiles = replaced[1];
    //    List<File> errorFiles = replaced[2];
    //    System.out.println("Applied files");
    //    for (File file : appliedFiles) {
    //      System.out.println(file);
    //    }
    //    System.out.println("Total Applied Files: " + appliedFiles.size());
    //    System.out.println("Unapplied files");
    //    for (File file : unappliedFiles) {
    //      System.out.println(file);
    //    }
    //    System.out.println("Total Unapplied Files: " + unappliedFiles.size());
    //    System.out.println("Error files");
    //    for (File file : errorFiles) {
    //      System.out.println(file);
    //    }
    //    System.out.println("Total Error Files: " + errorFiles.size());
  }

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
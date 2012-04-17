package com.kentcdodds.javahelper.test;

import com.kentcdodds.javahelper.helpers.DateHelper;
import com.kentcdodds.javahelper.helpers.OtherHelper;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Kent
 */
public class TestClass {

  public static void main(String[] args) throws Exception {
    //    IOHelper.print(new File(IOHelper.homeDir + "\\test.txt"), "Test File - 總會成員");
    //    String fileToString = IOHelper.fileToString(IOHelper.homeDir + "\\test.txt");
    //    System.out.println(fileToString);
    //    Calendar cal1 = Calendar.getInstance();
    //    cal1.set(Calendar.YEAR, -1);
    //    cal1.set(Calendar.DAY_OF_YEAR, cal1.getActualMaximum(Calendar.DAY_OF_YEAR));
    //    cal1.set(Calendar.HOUR_OF_DAY, cal1.getActualMaximum(Calendar.HOUR_OF_DAY));
    //    cal1.set(Calendar.MINUTE, cal1.getActualMaximum(Calendar.MINUTE));
    //    cal1.set(Calendar.SECOND, cal1.getActualMaximum(Calendar.SECOND));
    //    cal1.set(Calendar.MILLISECOND, cal1.getActualMaximum(Calendar.MILLISECOND));
    //    System.out.println(cal1.getTime());
    //    System.out.println(DateHelper.getDayOfEra(cal1.get(Calendar.ERA), cal1.get(Calendar.YEAR), cal1.get(Calendar.DAY_OF_YEAR)));
    //    
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
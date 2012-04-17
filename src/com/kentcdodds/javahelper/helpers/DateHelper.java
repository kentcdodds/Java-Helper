package com.kentcdodds.javahelper.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Kent
 */
public class DateHelper {

  /**
   * Our standard SimpleDateFormat: ("MMM dd, yyyy")
   */
  public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");

  /**
   * Gives the difference in days between the two days. This does not round up, so, if you were to give it
   * January 1st, 2012 12:00:00.000 and January 2nd, 2012 11:59:59.999 it would return 0 because even thought
   * that's close to a full day (one millisecond off) it is not a full days difference. If you would rather
   * have this functionality, use the getDaysDifference method.
   *
   * @param oldDate
   * @param recentDate
   * @return
   */
  public static int getAbsoluteDaysDifference(Date oldDate, Date recentDate) {
    long millis = recentDate.getTime() - oldDate.getTime();
    return (int) TimeUnit.MILLISECONDS.toDays(millis);
  }

  /**
   * Gets the absolute days difference. This is different than getAbsoluteDaysDifference because this will not
   * take anything below the day into account (so, for example, if you give it December 31st, 1999 at
   * 23:59:59.999 and January 1st, 2000 at 00:00:00.000 it will return 1, whereas getAbsoluteDaysDifference on
   * those days would return 0 because the actual difference between those two times is 1 millisecond
   *
   * @param oldDate
   * @param recentDate
   * @return
   */
  public static int getDaysDifference(Date oldDate, Date recentDate) {
    Calendar recent = Calendar.getInstance();
    recent.setTime(recentDate);

    Calendar old = Calendar.getInstance();
    old.setTime(oldDate);

    int eraRecent = recent.get(Calendar.ERA);
    int yearRecent = recent.get(Calendar.YEAR);
    int dayRecent = recent.get(Calendar.DAY_OF_YEAR);

    int eraOld = old.get(Calendar.ERA);
    int yearOld = old.get(Calendar.YEAR);
    int dayOld = old.get(Calendar.DAY_OF_YEAR);

    return getDayOfEra(eraRecent, yearRecent, dayRecent) - getDayOfEra(eraOld, yearOld, dayOld);

  }

  /**
   * Gets the day of the era by creating a calendar instance, setting the era and then iterating from 0 to the
   * given year getting the maximum day of the year for each of those years and adding that number until
   * finally it reaches the year given and then adds the dayOfYear given.
   *
   * @param era
   * @param yearOfEra
   * @param dayOfYear
   * @return
   */
  public static int getDayOfEra(int era, int yearOfEra, int dayOfYear) {
    int days = 0;
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.ERA, era);
    for (int i = 0; i < yearOfEra; i++) {
      cal.set(Calendar.YEAR, i);
      days += cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }
    days += dayOfYear;
    return (era == 1) ? days : -days;
  }
}

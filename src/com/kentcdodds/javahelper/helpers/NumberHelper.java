package com.kentcdodds.javahelper.helpers;

import java.math.BigDecimal;

/**
 *
 * @author Kent
 */
public class NumberHelper {

  /**
   * Convenience method. Calls isValidNumber on parameters with the given class.
   *
   * @param c the number type. Valid types are (wrappers included): double, int, float, long.
   * @param numStrings the string/strings/array of strings to check
   * @return false if any return false on the isValidNumber call
   */
  public static boolean areValidNumbers(Class c, String... numStrings) {
    for (String numString : numStrings) {
      if (!isValidNumber(c, numString)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Does a try catch (Exception) on parsing the given string to the given type
   *
   * @param c the number type. Valid types are (wrappers included): double, int, float, long.
   * @param numString number to check
   * @return false if there is an exception, true otherwise
   */
  public static boolean isValidNumber(Class c, String numString) {
    try {
      if (c == double.class || c == Double.class) {
        Double.parseDouble(numString);
      } else if (c == int.class || c == Integer.class) {
        Integer.parseInt(numString);
      } else if (c == float.class || c == Float.class) {
        Float.parseFloat(numString);
      } else if (c == long.class || c == Long.class) {
        Long.parseLong(numString);
      }
    } catch (Exception ex) {
      return false;
    }
    return true;
  }

  /**
   * Rounds the given amount half up by two digits (good for money)
   *
   * @param amount the double to round
   * @return the rounded amount
   */
  public static double round(double amount) {
    return round(amount, 2);
  }

  /**
   * Rounds the given amount half up to the given digits
   *
   * @param amount the double to round
   * @param digits the digits to round to
   * @return the rounded amount
   */
  public static double round(double amount, int digits) {
    return round(amount, digits, BigDecimal.ROUND_HALF_UP);
  }

  /**
   * Rounds the given amount by the given digits in the given direction
   *
   * @param amount the double to round
   * @param digits the digits to round to
   * @param round the type of round to perform
   * @return the rounded amount for example BigDecimal.ROUND_HALF_UP
   */
  public static double round(double amount, int digits, int round) {
    return Double.parseDouble(new BigDecimal(amount).setScale(digits, round).toString());
  }

  /**
   * Gets the percent of the subtotal verses the total
   *
   * @param subtotal
   * @param total
   * @return
   */
  public static int getPercentFromTotal(int subtotal, int total) {
    return (int) (((float) subtotal / (float) total) * 100);
  }

  /**
   * Removes all non number characters. Note: does not remove decimals (.) so a given number like this: 93)23.42-235.234
   * will come out looking like this 9323.42235.234
   *
   * @param number
   * @return
   */
  public static String cleanNumber(String number) {
    char[] toCharArray = number.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (char c : toCharArray) {
      if (Character.isDigit(c) || c == '.') {
        sb.append(c);
      }
    }
    return sb.toString();
  }
}

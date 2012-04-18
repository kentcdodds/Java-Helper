package com.kentcdodds.javahelper.helpers;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Kent
 */
public class StringHelper {

  /**
   * System.getProperty("line.separator"); This way you can make sure you have the right newline character
   * regardless of the system the user is using.
   */
  public static final String newline = System.getProperty("line.separator");

  /**
   * Performs a wordwrap-like function on the given string limiting the line length to the given lineLimit.
   * The lineLimit starts over based on the paragraph. It is defined by the
   * System.getProperty("line.separator"). Words are defined by spaces.
   *
   * @param string
   * @param lineLimit
   * @return
   */
  public static String wordWrap(String string, int lineLimit) {
    StringBuilder sb = new StringBuilder();
    String[] paragraphs = string.split("[" + newline + "]");
    for (int i = 0; i < paragraphs.length; i++) {
      String paragraph = paragraphs[i];
      if (!paragraph.isEmpty()) {
        String[] words = paragraph.split(" ");
        int lineLength = 0;
        for (String word : words) {
          if (word.length() + lineLength > lineLimit) {
            lineLength = 0;
            sb.append(newline);
          }
          lineLength += word.length();
          sb.append(word).append(" ");
        }
        sb.append(newline);
      }
    }
    return sb.toString();
  }

  /**
   * Calls isNull from OtherHelper on the given parameters first, then calls isEmpty on them. Returns true on
   * first true result
   *
   * @param string the string/strings/array of strings to check
   * @return
   */
  public static boolean isNullOrEmpty(String... string) {
    if (OtherHelper.isNull((Object) string)) {
      return true;
    }
    return isEmpty(string);
  }

  /**
   * Checks whether the given parameters are empty (or .equals(""))
   *
   * @param string the string/strings/array of strings to check
   * @return true if any given string is empty
   */
  public static boolean isEmpty(String... string) {
    for (String string1 : string) {
      if (string1.equals("") || string1.isEmpty()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes all non number characters. Note: does not remove decimals (.) so a given number like this:
   * 93)23.42-235.234 will come out looking like this 9323.42235.234
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

  /**
   * Will insert the given inserts between each character of the given string. Here's an example:
   * insertIntoString("1234", "a", "", "c", "+", "-", "32342") would return "a12c3+4".
   * insertIntoString("1234", "", "hello", "", "world") would return "1hello23world4". Or a more useful
   * example: insertIntoString("1231234567", "(", "", "", ") ", "", "", "-") would return "(123) 123-4567"
   *
   * @param string
   * @param inserts
   * @return
   */
  public static String insertIntoString(String string, String... inserts) {
    char[] numbers = string.toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numbers.length; i++) {
      if (i < inserts.length) {
        sb.append(inserts[i]);
      }
      sb.append(numbers[i]);
    }
    return sb.toString();
  }

  /**
   * Exact code: String fixedNumber = cleanNumber(number).replace(".", ""); return
   * insertIntoString(fixedNumber, char0, "", "", char3, "", "", char6);
   *
   * @param number
   * @param char0
   * @param char3
   * @param char6
   * @return
   */
  public static String formatPhoneNumber(String number, String char0, String char3, String char6) {
    String fixedNumber = cleanNumber(number).replace(".", "");
    return insertIntoString(fixedNumber, char0, "", "", char3, "", "", char6);
  }

  /**
   * Calls cleanPhoneNumber(number).replace("."); and then formats a number to look like this: (555) 123-4567.
   * Note: If you give too many numbers they'll just be added to the end of the number. Like this (555)
   * 123-456789101112
   *
   * @param number
   * @return formatted phone number
   */
  public static String formatPhoneNumber(String number) {
    return formatPhoneNumber(number, "(", ") ", "-");
  }

  /**
   * If the call was formatCreditCard("1234567812345678", "-") the result would be: 1234-5678-1234-5678. This
   * will continue if you give too long or too short of a number. If you give 12345, the result will be:
   * 1234-5. If you give 12345678123456789, it will return 1234-5678-1234-5678-9. NOTE: Also first calls
   * cleanNumber(creditCard).replace(".", "");
   *
   * @param creditCard the number string to format like a credit card number
   * @param separator the item to put between every 4 numbers
   * @return formatted credit card
   */
  public static String formatCreditCard(String creditCard, String separator) {
    String fixedCreditCard = cleanNumber(creditCard).replace(".", "");
    char[] numbers = fixedCreditCard.toCharArray();
    //1234567812345678
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numbers.length; i++) {
      if (i % 4 == 0 && i > 0) {
        sb.append(separator);
      }
      sb.append(numbers[i]);
    }
    return sb.toString();
  }

  /**
   * This method will make the display text for the current folders
   *
   * @param originalString the text to be shortened
   * @param maxLength maximum length of the string
   * @param percentStart the percentage of the string length to go before inserting the dots (for 50% give .5)
   * @return the text to be displayed
   */
  public static String shortenString(String originalString, int maxLength, double percentStart) {
    String newString;
    double endLength = (maxLength * percentStart) - 3;
    double beginLength = maxLength * (1 - percentStart);
    if (originalString.length() > maxLength + 3) {
      newString = originalString.substring(0, (int) beginLength) + "..." + originalString.substring(originalString.length() - (int) endLength);
    } else {
      newString = originalString;
    }
    return newString;
  }

  /**
   * This is posting something in a URL. It replaces anything in the string given with percentEncoded values
   *
   * @param originalString
   * @return
   */
  public static String percentEncodeString(String originalString) {
    //It is important that % is first, if you put it any later it will replace % with anything that's replace before.
    //Try it, you'll see what I mean.
    Map<Character, String> replaceMap = new TreeMap<>();
    //<editor-fold defaultstate="collapsed" desc="Set Map">
    replaceMap.put('%', "%25");
    replaceMap.put('!', "%21");
    replaceMap.put('*', "%2A");
    replaceMap.put('\'', "%27"); //Escape character. This value is: '
    replaceMap.put('(', "%28");
    replaceMap.put(')', "%29");
    replaceMap.put(';', "%3B");
    replaceMap.put(':', "%3A");
    replaceMap.put('@', "%40");
    replaceMap.put('&', "%26");
    replaceMap.put('=', "%3D");
    replaceMap.put('+', "%2B");
    replaceMap.put('$', "%24");
    replaceMap.put(',', "%2C");
    replaceMap.put('/', "%2F");
    replaceMap.put('?', "%3F");
    replaceMap.put('#', "%23");
    replaceMap.put('[', "%5B");
    replaceMap.put(']', "%5D");
    replaceMap.put(' ', "%20");
    //</editor-fold>
    char[] charArry = originalString.toCharArray();
    StringBuilder fixedString = new StringBuilder();
    for (int i = 0; i < charArry.length; i++) {
      Character c = charArry[i];
      String replacement = replaceMap.get(c);
      if (replacement != null) {
        fixedString.append(replacement);
      } else {
        fixedString.append(c);
      }
    }
    return fixedString.toString();
  }
}

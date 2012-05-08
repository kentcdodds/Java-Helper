package com.kentcdodds.javahelper.helpers;

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
   * Simple method. Read it like this: Replace "items" with "replace" in "string"
   *
   * @param string the string to replace items in
   * @param replace the text to replace the items with
   * @param items the items to replace
   * @return the replaced string
   */
  public static String replaceInString(String string, String replace, String... items) {
    for (String item : items) {
      string = string.replace(item, replace);
    }
    return string;
  }

  /**
   * Calls isNull from OtherHelper on the given parameters first, then calls isEmpty on them. Returns true on
   * first true result
   *
   * @param string the string/strings/array of strings to check
   * @return
   */
  public static boolean isNullOrEmpty(String... string) {
    if (OtherHelper.isNull((Object[]) string)) {
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
   * Separates the given strings with the given separator (", " for example). Does not add the separator to
   * the end of the list
   *
   * @param separator
   * @param strings
   * @return
   */
  public static String splitBy(String separator, String... strings) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strings.length; i++) {
      if (i > 0) {
        sb.append(separator);
      }
      sb.append(strings[i]);
    }
    return sb.toString();
  }
}

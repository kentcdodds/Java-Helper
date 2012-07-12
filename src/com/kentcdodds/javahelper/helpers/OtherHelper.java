package com.kentcdodds.javahelper.helpers;

import java.io.Console;
import java.util.Scanner;

/**
 * Holds helper methods that haven't found a home yet
 *
 * @author Kent
 */
public class OtherHelper {

  /**
   * Checks whether any of the given parameters are null
   *
   * @param object the object/objects/array of objects to check
   * @return true if any give parameter is null
   */
  public static boolean isNull(Object... object) {
    for (Object object1 : object) {
      if (object1 == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * This removes the items given in the first list from the second list
   *
   * @param thisList
   * @param thatList
   */
  public static void removeThisFromThat(java.util.List thisList, java.util.List thatList) {
    for (Object removeObject : thisList) {
      thatList.remove(removeObject);
    }
  }

  /**
   * Read a password from the consol (hides the user's input)
   *
   * @param message
   * @return
   */
  public static String readPassword(String message, Scanner input) {
    String password;
    Console console = System.console();
    if (console != null) {
      char[] secretValue = System.console().readPassword(message);
      password = new String(secretValue);
    } else {
      System.out.print(message);
      password = input.nextLine();
    }
    return password;
  }

  /**
   * Scrambles the password into a comma separated string of bytes (and then prints out a descramble to check it
   * worked). REALLY simple algorithm. Pretty much only good for being able to have someone look at your code without
   * being able to remember your password, but I wouldn't recommend posting what the scrambler results are online. It'd
   * be really easy to figure out.
   */
  public static void scrambleString(String string) {
    StringBuilder byteString = new StringBuilder();
    for (byte b : string.getBytes()) {
      b = (byte) (b + 3);
      byteString.append(b).append(",");
    }
    System.out.println(byteString);
    System.out.println(descrambleString(byteString.toString()));
  }

  /**
   * Descrambles a password which was scrambled with the scrambler. REALLY simple algorithm. Pretty much only good for
   * being able to have someone look at your code without being able to remember your password, but I wouldn't recommend
   * posting what the scrambler results are online. It'd be really easy to figure out.
   *
   * @param scramble
   * @return
   */
  public static String descrambleString(String scramble) {
    String[] split = scramble.split(",");
    byte[] bytes = new byte[split.length];
    for (int i = 0; i < split.length; i++) {
      bytes[i] = (byte) (Byte.valueOf(split[i]).byteValue() - 3);
    }
    return new String(bytes);
  }
}

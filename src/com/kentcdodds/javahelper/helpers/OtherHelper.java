package com.kentcdodds.javahelper.helpers;

import java.io.File;

/**
 * Holds helper methods that haven't found a home yet
 *
 * @author Kent
 */
public class OtherHelper {

  /**
   * Makes sure that the given file's parent directory exists. Creates it if not.
   *
   * @param file the file to check
   */
  public static void checkDirectory(File file) {
    if (!new File(file.getParent()).exists()) {
      new File(file.getParent()).mkdir();
    }
  }

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
}

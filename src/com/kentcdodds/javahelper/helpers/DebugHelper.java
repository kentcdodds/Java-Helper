package com.kentcdodds.javahelper.helpers;

/**
 *
 * @author kentcdodds
 */
public class DebugHelper {

  /**
   * Calls getLineNumber(3). It's doing three because the method which calls this method will be 3 away from the
   * beginning of the stack trace
   *
   * @return
   * @see getLineNumber(int numberBack)
   */
  public static int getLineNumber() {
    return getLineNumber(3);
  }

  /**
   * Returns the line number on the stack trace the given number back on the trace. Normally, if you want the line
   * number of the caller of this method you'll give 2 (or call the convenience method which defaults to 2). This kind
   * of method is useful for debugging purposes mostly
   *
   * @param numberBack the number on the stack trace to get the line number from.
   * @return the line number on the stack trace numberBack away from given trace (the method which calls this would be
   * "2" numberBack
   */
  public static int getLineNumber(int numberBack) {
    return Thread.currentThread().getStackTrace()[numberBack].getLineNumber();
  }

  /**
   * Helpful for debugging. Give 2 for the line which is calling this method.
   *
   * @param numberBack
   * @return className + "." + methodName + "():" + lineNumber of the specified number back on the stack trace.
   * @see getDebugInfo()
   */
  public static String getDebugInfo(int numberBack) {
    String className = Thread.currentThread().getStackTrace()[numberBack].getClassName();
    String methodName = Thread.currentThread().getStackTrace()[numberBack].getMethodName();
    int lineNumber = getLineNumber(numberBack + 1);

    return className + "." + methodName + "(): " + lineNumber;
  }

  public static String getDebugInfo() {
    return getDebugInfo(3);
  }
}

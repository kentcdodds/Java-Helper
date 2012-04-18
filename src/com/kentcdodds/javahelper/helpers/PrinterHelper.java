package com.kentcdodds.javahelper.helpers;

/**
 * Good for debugging, this class allows you to send your prints somewhere and change whether it all prints.
 * Sometimes you want to reduce the amount your code prints to isolate a problem. This way you just set the
 * PrintHelper instance.setPrint(true) and it'll print everything you send to the printALot method.
 *
 * @author Kent
 */
public class PrinterHelper {

  private boolean print = false;
  private static PrinterHelper instance;

  private PrinterHelper() {
  }

  public static PrinterHelper getInstance() {
    if (instance == null) {
      instance = new PrinterHelper();
    }
    return instance;
  }

  /**
   * Prints the given String if the print variable is true
   *
   * @param string
   */
  public static void print(String string) {
    System.out.print((getInstance().isPrint()) ? string + StringHelper.newline : "");
  }

  /**
   * Prints the given String in error type if the print variable is true
   *
   * @param string
   */
  public static void printErr(String string) {
    System.err.print((getInstance().isPrint()) ? string + StringHelper.newline : "");
  }

  /**
   * @return the print
   */
  public boolean isPrint() {
    return print;
  }

  /**
   * @param print 
   */
  public void setPrint(boolean print) {
    this.print = print;
  }
}

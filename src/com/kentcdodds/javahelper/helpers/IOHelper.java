package com.kentcdodds.javahelper.helpers;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kent
 */
public class IOHelper {

  public static final String homeDir = System.getProperty("user.home");

  /**
   * Prints the given file string tot he file destination. Does not check the parent directory first
   *
   * @param destination
   * @param string
   * @throws FileNotFoundException
   */
  public static void print(File destination, String string) throws FileNotFoundException {
    try (PrintWriter pw = new PrintWriter(destination, "UTF-8")) {
      pw.print(string);
    } catch (UnsupportedEncodingException ex) {
      Logger.getLogger(IOHelper.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Removes unsafe filename characters from the given string.
   *
   * @param string
   * @return filenameSafeString
   */
  public static String makeFilenameSafe(String string) {
    StringBuilder filenameSafeString = new StringBuilder(string.length());
    filenameSafeString.setLength(string.length());
    char[] badCharacters = new char[]{'\\', '/', ':', '*', '?', '\"', '<', '>', '|'};
    int current = 0;
    for (int i = 0; i < string.length(); i++) {
      char cur = string.charAt(i);
      boolean found = false;
      for (int j = 0; j < badCharacters.length; j++) {
        if (cur == badCharacters[j]) {
          found = true;
          break;
        }
      }
      if (!found) {
        filenameSafeString.setCharAt(current++, cur);
      }
    }
    return filenameSafeString.toString();
  }

  /**
  * Takes the file and returns it in a string
  *
  * @param location
  * @return
  * @throws IOException
  */
  public static String fileToString(String location) throws IOException {
    FileReader fr = new FileReader(new File(location));
    return readerToString(fr);
  }

  /**
  * Takes the given resource (based on the given class) and returns that as a string.
  *
  * @param location
  * @param c
  * @return
  * @throws IOException
  */
  public static String resourceToString(String location, Class c) throws IOException {
    InputStream is = c.getResourceAsStream(location);
    InputStreamReader r = new InputStreamReader(is);
    return readerToString(r);
  }

  /**
  * Returns all the lines in the scanner's stream as a String
  *
  * @param r
  * @return
  * @throws IOException
  */
  public static String readerToString(InputStreamReader r) throws IOException {
    StringWriter sw = new StringWriter();
    char[] buf = new char[1024];
    int len;
    while ((len = r.read(buf)) > 0) {
      sw.write(buf, 0, len);
    }
    r.close();
    sw.close();
    return sw.toString();
  }

  /**
   * Copies the given resource file to the given destination. Does not check whether the destination exists.
   *
   * @param resourceLocation
   * @param destinationFilepath
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void copyResourceFile(String resourceLocation, String destinationFilepath) throws FileNotFoundException, IOException {
    InputStream is = StringHelper.class.getResourceAsStream(resourceLocation);
    saveInputStream(is, destinationFilepath);
  }

  /**
   * Copies the given source to the given destination. Does not check whether the destination exists.
   *
   * @param sourceFilepath
   * @param destinationFilepath
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void copyFile(String sourceFilepath, String destinationFilepath) throws FileNotFoundException, IOException {
    InputStream is = new FileInputStream(new File(destinationFilepath));
    saveInputStream(is, destinationFilepath);
  }

  /**
   * Saves the given InputStream to a file at the destination. Does not check whether the destination exists.
   *
   * @param in
   * @param destination
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void saveInputStream(InputStream in, String destination) throws FileNotFoundException, IOException {
    File outputFile = new File(destination);
    try (OutputStream out = new FileOutputStream(outputFile)) {
      byte[] buf = new byte[1024];
      int len;
      while ((len = in.read(buf)) > 0) {
        out.write(buf, 0, len);
      }
      in.close();
    }
  }

  /**
   * Using the given urlString, this method creates and returns the buffered reader for that URL. Specifies
   * UTF-8 format
   *
   * @param urlString
   * @return
   * @throws MalformedURLException
   * @throws IOException
   */
  public synchronized static BufferedReader getBufferedReader(String urlString) throws MalformedURLException, IOException {
    URL url = new URL(urlString);
    InputStream is = url.openStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    return br;
  }
}

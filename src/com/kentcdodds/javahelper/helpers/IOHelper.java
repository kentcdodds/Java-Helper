package com.kentcdodds.javahelper.helpers;

import com.kentcdodds.javahelper.model.HelperFile;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Kent
 */
public class IOHelper {

  public static final String homeDir = System.getProperty("user.home");
  public static final String[] badFileNameCharacters = new String[]{"\\", "/", ":", "*", "?", "\"", "<", ">", "|"};

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
   * @param string the string to make filename safe
   * @param replace what you want bad characters to be replaced with (note, if you give an invalid character for this
   * parameter, it will be automatically replaced with an empty string, the same applies if null is given).
   * @return the string with characters replaced
   */
  public static String makeFilenameSafe(String string, String replace) {
    if (replace == null) {
      replace = "";
    } else {
      replace = StringHelper.replaceInString(replace, "", badFileNameCharacters);
    }
    return StringHelper.replaceInString(string, replace, badFileNameCharacters);
  }

  /**
   * Checks whether the given string contains any bad filename characters.
   *
   * @param string the string to check
   * @return whether the given string has bad filename characters
   */
  public static boolean hasBadFileNameCharacters(String string) {
    for (String badChar : badFileNameCharacters) {
      if (string.contains(badChar)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Takes the file and returns it in a string
   *
   * @param fileLocation
   * @return the file in String form
   * @throws IOException when trying to read from the file
   */
  public static String fileToString(String fileLocation) throws IOException {
    InputStreamReader streamReader = new InputStreamReader(new FileInputStream(fileLocation), "UTF-8");
    return readerToString(streamReader);
  }

  /**
   * Takes the given resource (based on the given class) and returns that as a string.
   *
   * @param resourceLocation of the file
   * @param klass class to get the resource from
   * @return the file as a string
   * @throws IOException when trying to read from the file
   */
  public static String resourceToString(Class klass, String resourceLocation) throws IOException {
    InputStream inputStream = klass.getResourceAsStream(resourceLocation);
    InputStreamReader streamReader = new InputStreamReader(inputStream, "UTF-8");
    return readerToString(streamReader);
  }

  /**
   * Returns all the lines in the scanner's stream as a String
   *
   * @param streamReader
   * @return
   * @throws IOException when trying to read from the file
   */
  public static String readerToString(InputStreamReader streamReader) throws IOException {
    StringWriter stringWriter = new StringWriter();
    char[] buffer = new char[1024];
    int length;
    while ((length = streamReader.read(buffer)) > 0) {
      stringWriter.write(buffer, 0, length);
    }
    streamReader.close();
    stringWriter.close();
    return stringWriter.toString();
  }

  /**
   * Copies the given resource file to the given destination. Does not check whether the destination exists.
   *
   * @param klass
   * @param resourceLocation
   * @param outputFile
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void copyResourceFile(Class klass, String resourceLocation, File outputFile) throws FileNotFoundException, IOException {
    InputStream inputStream = klass.getResourceAsStream(resourceLocation);
    saveInputStream(inputStream, outputFile);
  }

  /**
   * Copies the given source to the given destination. Does not check whether the destination exists.
   *
   * @param sourceFilepath
   * @param outputFile
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void copyFile(File sourceFile, File outputFile) throws FileNotFoundException, IOException {
    InputStream inputStream = new FileInputStream(sourceFile);
    saveInputStream(inputStream, outputFile);
  }

  /**
   * Saves the given bytes to the output file. Creates a HelperFile with the given bytes and location then calls
   * copyBytes() on it.
   *
   * @param bytes
   * @param location
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void saveBytesToFile(byte[] bytes, String location) throws FileNotFoundException, IOException {
    HelperFile helperFile = new HelperFile(bytes, location);
    helperFile.saveBytes();
  }

  /**
   * Saves the given InputStream to a file at the destination. Does not check whether the destination exists.
   *
   * @param inputStream
   * @param destination
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void saveInputStream(InputStream inputStream, File outputFile) throws FileNotFoundException, IOException {
    int size = 4096;
    try (OutputStream out = new FileOutputStream(outputFile)) {
      byte[] buffer = new byte[size];
      int length;
      while ((length = inputStream.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
      inputStream.close();
    }
  }

  /**
   * Reads the given bytes into an array. The bytes must not exceed the array size limit of Integer.MAX_VALUE (which is
   * 2^31 -1). This means you can only use this method with files under 2 gigabytes
   *
   * @param file
   * @return
   * @throws FileNotFoundException when trying to get the file
   * @throws IOException when trying to read the file
   * @throws Exception when trying to read a file too big for a byte[]
   */
  public static byte[] getFileBytes(File file) throws FileNotFoundException, IOException, Exception {
    HelperFile helperFile = new HelperFile(file);
    return helperFile.getBytes();
  }

  /**
  * This uses the java.util.zip library to zip the given files to the given destination.
  *
  * @param destination
  * @param files
  * @throws FileNotFoundException
  * @throws IOException
  */
  public static void zipFiles(File destination, File... files) throws FileNotFoundException, IOException {
    try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destination))) {
      byte[] buffer = new byte[1024];
      for (int i = 0; i < files.length; i++) {
        try (FileInputStream in = new FileInputStream(files[i])) {
          out.putNextEntry(new ZipEntry(files[i].getName()));

          // Transfer bytes from the file to the ZIP file
          int length;
          while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
          }

          // Complete the entry
          out.closeEntry();
        }
      }
    }
  }

  /**
   * Using the given urlString, this method creates and returns the buffered reader for that URL. Specifies UTF-8 format
   *
   * @param urlString
   * @return
   * @throws MalformedURLException
   * @throws IOException
   */
  public static BufferedReader getBufferedReader(String urlString) throws MalformedURLException, IOException {
    URL url = new URL(urlString);
    InputStream is = url.openStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
    return br;
  }

  /**
   * Replaces the given find with the given replace in the given file. Reads the file to a string, replaces the find
   * with the replace in the string and prints the new string on top of the original file.
   *
   * @param file the file to conduct the find/replace on
   * @param find the find to replace
   * @param replace the text to replace the find with
   * @return whether anything was different in the replace call. It does not print over the original file if nothing is
   * different
   * @throws IOException
   */
  public static boolean replaceInFile(File file, String find, String replace) throws IOException {
    String fileString = fileToString(file.getPath());
    String newString = fileString.replace(find, replace);
    if (fileString.equals(newString)) {
      return false;
    }
    print(file, newString);
    return true;
  }

  /**
   * Replaces the given find with the given replace in all the files under the given directory of the given file
   * extension(s). If the file extension is null (or omitted), it will ignore the file extension and apply to all files
   * regardless of their extension. Calls replaceInFile(file, find, replace) on each of the files. Skips any files which
   * throw IO exceptions and adds it to the list which is returned. You can call
   * sendReplaceInAllFilesToPrinter(List<File>[]) on the returned array and it will send information to the Printer
   *
   * @param topDirectory the topDirectory to find the files in
   * @param sublevels determines how many subdirectories to go before it stops adding files. Give 0 to get files only in
   * the given directory
   * @param find the text to be replaced by the given replace
   * @param replace the text to replace the given find with
   * @param extension the any-number of extensions to apply the find/replace to. Omit for any type
   * @return An array of Lists of Files: arry[0] = files which applied and were successful in the find/replace
   * operation; arry[1] = files which may have been successful, but there would have been no change to the file during
   * the find/replace operation so the file was not overwritten; arry[2] = files which threw an IOException for any
   * reason.
   */
  public static java.util.List<File>[] replaceInAllFiles(File topDirectory, int sublevels, String find, String replace, String... extension) {
    java.util.List<File> files = getAllFiles(topDirectory, sublevels, extension);
    java.util.List<File> appliedFiles = new java.util.ArrayList<>();
    java.util.List<File> errorFiles = new java.util.ArrayList<>();
    java.util.List<File> unappliedFiles = new java.util.ArrayList<>();
    for (File file : files) {
      try {
        boolean applied = replaceInFile(file, find, replace);
        if (applied) {
          appliedFiles.add(file);
        } else {
          unappliedFiles.add(file);
        }
      } catch (IOException ex) {
        errorFiles.add(file);
        Logger.getLogger(IOHelper.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return new java.util.List[]{appliedFiles, unappliedFiles, errorFiles};
  }

  /**
   * Prints the given list array of files. It's pretty much only useful for the return on the replaceInAllFiles method
   *
   * @param replaced
   */
  public static void sendReplaceInAllFilesToPrinter(java.util.List<File>[] replaced) {
    java.util.List<File> appliedFiles = replaced[0];
    java.util.List<File> unappliedFiles = replaced[1];
    java.util.List<File> errorFiles = replaced[2];
    PrinterHelper.print("Applied files");
    for (File file : appliedFiles) {
      PrinterHelper.print(file);
    }
    PrinterHelper.print("Total Applied Files: " + appliedFiles.size());
    PrinterHelper.print(StringHelper.newline);
    PrinterHelper.print("Unapplied files");
    for (File file : unappliedFiles) {
      PrinterHelper.print(file);
    }
    PrinterHelper.print("Total Unapplied Files: " + unappliedFiles.size());
    PrinterHelper.print(StringHelper.newline);
    PrinterHelper.print("Error files");
    for (File file : errorFiles) {
      PrinterHelper.print(file);
    }
    PrinterHelper.print("Total Error Files: " + errorFiles.size());
  }

  /**
   * Gets all the files under the given file (not including the given file).
   *
   * @param file the file to get files under
   * @param sublevels determines how many subdirectories to go before it stops adding files. Give 0 to get files only in
   * the given directory (or just call getDirectoryFiles(file, extension) instead).
   * @param extension the extension(s) to add to the returned list. If null or omitted will add all files to the
   * returned list (not including directories)
   * @return
   */
  public static java.util.List<File> getAllFiles(File file, int sublevels, String... extension) {
    java.util.List<File> filesList = new java.util.ArrayList<>();
    if (sublevels < -1) {
      return filesList; //Important it doesn't return null.
    }
    if (file.isDirectory()) {
      File[] listFiles = file.listFiles();
      if (listFiles != null) {
        for (File file1 : listFiles) {
          filesList.addAll(getAllFiles(file1, sublevels - 1, extension));
        }
      }
    } else if (file.isFile()) {
      if (extension == null || extension.length == 0) {
        filesList.add(file);
      } else {
        for (String string : extension) {
          if (file.getName().endsWith(string)) {
            filesList.add(file);
          }
        }
      }
    }
    return filesList;
  }

  /**
   * Gets files in the given directory.
   *
   * @param directory the directory to search for files in.
   * @param extension the text the file should end with to be added to the returned list
   * @return a list of the files in the directory
   */
  public static java.util.List<File> getDirectoryFiles(File directory, String... extension) {
    File[] listFiles = directory.listFiles();
    java.util.List<File> filesList = new java.util.ArrayList<>();
    for (File file : listFiles) {
      if (file.isFile()) {
        if (extension == null || extension.length == 0) {
          filesList.add(file);
        } else {
          for (String ext : extension) {
            if (file.getName().endsWith(ext)) {
              filesList.add(file);
            }
          }
        }
      }
    }
    return filesList;
  }

  /**
   * Gets directories in the given directory.
   *
   * @param directory the directory to search for files in.
   * @return a list of the files in the directory
   */
  public static java.util.List<File> getDirectoryDirectories(File directory) {
    File[] listFiles = directory.listFiles();
    java.util.List<File> filesList = new java.util.ArrayList<>();
    for (File file : listFiles) {
      if (file.isDirectory()) {
        filesList.add(file);
      }
    }
    return filesList;
  }

  /**
   * Saves the given object to the given destination. The object and all it's variables must implement
   * java.io.Serializable or the variables must have the keyword "transient" in front of it.
   *
   * @param object
   * @param savePath
   * @throws IOException
   */
  public static void saveObject(Object object, String savePath) throws IOException {
    FileOutputStream f_out = new FileOutputStream(savePath);
    ObjectOutputStream o_out = new ObjectOutputStream(f_out);
    o_out.writeObject(object);
  }

  /**
   * Opens an object from the given openPath and returns it
   *
   * @param openPath
   * @return
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public static Object loadObject(String openPath) throws IOException, ClassNotFoundException {
    FileInputStream f_in = new FileInputStream(openPath);
    ObjectInputStream o_in = new ObjectInputStream(f_in);
    return o_in.readObject();
  }
}

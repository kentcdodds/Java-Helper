package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.IOHelper;
import java.io.*;

/**
 *
 * @author kentcdodds
 */
public class HelperFile extends File {

  private byte[] bytes;

  /**
   * Constructor for HelperFile
   *
   * @param path the path to the file
   * @throws FileNotFoundException when trying to read the file
   * @throws IOException when trying to read the bytes into the byte[]
   * @throws Exception when trying to read the bytes into the array
   */
  public HelperFile(String path) throws FileNotFoundException, IOException, Exception {
    super(path);
    generateBytes();
  }

  /**
   * Convenience constructor. Just gets the path of the file and does the exact same thing as the constructor
   * requiring a path
   *
   * @param file getPath is called in super()
   * @throws FileNotFoundException when trying to read the file
   * @throws IOException when trying to read the bytes into the byte[]
   * @throws Exception when trying to read the bytes into the array
   */
  public HelperFile(File file) throws FileNotFoundException, IOException, Exception {
    super(file.getPath());
    generateBytes();
  }

  /**
   * Constructor: Helpful when the file only exists in memory.
   *
   * @param bytes
   * @param location
   */
  public HelperFile(byte[] bytes, String location) {
    super(location);
    this.bytes = bytes;
  }

  /**
   * Generates the file bytes
   */
  private void generateBytes() throws FileNotFoundException, IOException, Exception {
    InputStream stream = new FileInputStream(this);
    if (length() > Integer.MAX_VALUE) {
      throw new Exception("Cannot generate bytes. File must be less than " + Integer.MAX_VALUE + " bytes.");
    }
    bytes = new byte[(int) length()];
    stream.read(bytes);
  }

  /**
   * Saves the bytes in this HelperFile to the location in this HelperFile
   *
   * @throws FileNotFoundException if the file cannot be found
   * @throws IOException when writing the bytes to the disk
   */
  public void saveBytes() throws FileNotFoundException, IOException {
    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this))) {
      out.write(bytes);
    }
  }

  /**
   * Copies this file's bytes to the given file path. Calls IOHelper.saveBytesToFile(bytes, file.getPath());
   *
   * @param file the location of the file.
   * @throws FileNotFoundException if the file cannot be found
   * @throws IOException when writing the bytes to the disk
   */
  public void copyBytes(File file) throws FileNotFoundException, IOException {
    IOHelper.saveBytesToFile(bytes, file.getPath());
  }

  //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
  /**
   * @return the bytes
   */
  public byte[] getBytes() {
    return bytes;
  }

  /**
   * @param bytes the bytes to set
   */
  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }
  //</editor-fold>
}

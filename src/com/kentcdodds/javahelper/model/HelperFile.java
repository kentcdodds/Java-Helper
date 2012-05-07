package com.kentcdodds.javahelper.model;

import java.io.*;

/**
 *
 * @author kentcdodds
 */
public class HelperFile extends File {

  private byte[] bytes;

  /**
   * Constructor for HelperFile
   */
  public HelperFile(String location) throws FileNotFoundException, IOException, Exception {
    super(location);
    generateBytes();
  }

  /**
   * Constructor for HelperFile
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
   * Saves the bytes in this HelperFile to the location specified
   *
   * @throws FileNotFoundException if the file cannot be found
   * @throws IOException when writing the bytes to the disk
   */
  public void saveBytes(File file) throws FileNotFoundException, IOException {
    BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
    out.write(bytes);
  }

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
}

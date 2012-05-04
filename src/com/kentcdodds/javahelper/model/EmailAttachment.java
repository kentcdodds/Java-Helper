package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.IOHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Kent Dodds
 */
public class EmailAttachment {

  private File file;
  private byte[] fileBytes;
  private String fileName;
  private String extension;
  private String fullFilename;

  /**
   * Don't forget to set filetype, filename, and file
   */
  public EmailAttachment() {
  }

  /**
   * Don't forget to set filetype, filename, and file
   */
  public EmailAttachment(File file, byte[] fileBytes) {
    this.file = file;
    this.fileBytes = fileBytes;
  }

  /**
   * Gets the filename of the file ("document.doc" returns "document")
   *
   * @return the filename of the file
   */
  public String getFileName() {
    if (fileName == null) {
      generateFileInfo();
    }
    return fileName;
  }

  public String getExtension() {
    if (extension == null) {
      generateFileInfo();
    }
    return extension;
  }

  public String getFullFilename() {
    if (fileName == null
            || extension == null) {
      generateFileInfo();
    }
    if (fullFilename == null) {
      fullFilename = fileName + "." + extension;
    }
    return fullFilename;
  }

  /**
   * Generates the file info
   */
  private void generateFileInfo() {
    if (file != null) {
      String name = file.getName();
      int dot = name.lastIndexOf(".");
      fileName = name.substring(0, dot);
      extension = name.substring(dot + 1); // + 1 to get rid of the dot
    }
  }

  /**
   * Generates the bytes of the file from the current file. WARNING: This will not work if the file is greater than 2 MB
   * because that's the maximum size of an array
   *
   * @return the file bytes
   */
  public byte[] generateFileBytes() throws FileNotFoundException, IOException {
    this.fileBytes = IOHelper.getFileBytes(file);
    return getFileBytes();
  }

  /**
   * @return the file
   */
  public File getFile() {
    return file;
  }

  /**
   * @param file the file to set
   */
  public void setFile(File file) {
    this.file = file;
  }

  /**
   * @return the fileBytes
   */
  public byte[] getFileBytes() {
    return fileBytes;
  }

  /**
   * @param fileBytes the fileBytes to set
   */
  public void setFileBytes(byte[] fileBytes) {
    this.fileBytes = fileBytes;
  }
}

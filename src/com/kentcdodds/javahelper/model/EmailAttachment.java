package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.IOHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Kent Dodds
 */
public class EmailAttachment {

  private File file;
  private byte[] fileBytes;
  private String fileName;
  private String extension;
  private String fullFileName;
  private MimeBodyPart bodyPart;

  /**
   * Don't forget to set filetype, filename, and file
   */
  public EmailAttachment() {
  }

  /**
   * Constructor: This is the minimum you need to send the attachment.
   *
   * @param fileBytes
   * @param fullFileName example: "document.txt"
   */
  public EmailAttachment(byte[] fileBytes, String fullFileName) {
    this.fileBytes = fileBytes;
    this.fullFileName = fullFileName;
  }

  /**
   * Constructor: This is the minimum you need to send the attachment. The file must be valid
   *
   * @param file
   */
  public EmailAttachment(File file) {
    this.file = file;
  }

  /**
   * Generates and sets the MimeBodyPart for this attachment. First checks whether the attachment has bytes
   * already. This allows you to give an email attachment which has bytes in memory and doesn't have an actual
   * file. If the bytes are null it reads the bytes from the file. If the file is null returns false.
   * **Important** Be sure the at least one of the following groups of this Email attachment has been set
   * before calling this method, otherwise you'll get a null pointer exception: fullFileName, file, or
   * fileName and extension (both)
   *
   * @return whether the MimeBodyPart was successfully generated
   * @throws MessagingException when setting DataHandler and the filename
   */
  public boolean generateMimeBodyPart() throws MessagingException {
    DataSource source;
    if (fileBytes != null) {
      source = new ByteArrayDataSource(fileBytes, Message.ATTACHMENT) {
        
        @Override
        public String getContentType() {
          return "application/octet-stream";
        }
      };
    } else if (file != null) {
      source = new FileDataSource(file) {

        @Override
        public String getContentType() {
          return "application/octet-stream";
        }
      };
    } else {
      return false;
    }
    bodyPart = new MimeBodyPart();
    bodyPart.setDataHandler(new DataHandler(source));
    bodyPart.setFileName(getFullFileName());
    return true;
  }

  /**
   * Gets the filename of the attachment ("document.doc" returns "document"). If the fileName is null, calls
   * generateFileInfo(). Important: If the file of this attachment is null and the fileName has not already
   * been set, you'll get a null value back.
   *
   * @return the filename of the file
   */
  public String getFileName() {
    if (fileName == null) {
      generateFileInfo();
    }
    return fileName;
  }

  /**
   * Gets the extension of the attachment ("document.doc" returns "doc"). If the extension is null, calls
   * generateFileInfo(). Important: If the file of this attachment is null and the extension has not already
   * been set, you'll get a null value back.
   *
   * @return the extension of this EmailAttachment
   */
  public String getExtension() {
    if (extension == null) {
      generateFileInfo();
    }
    return extension;
  }

  /**
   * If the full filename is null, uses file.getName(). If file is null, generates it with getFileName() + "."
   * + getExtension().
   *
   * @return the fullFilename
   */
  public String getFullFileName() {
    if (fullFileName == null) {
      if (file != null) {
        fullFileName = file.getName();
      } else {
        fullFileName = getFileName() + "." + getExtension();
      }
    }
    return fullFileName;
  }

  /**
   * Generates the file info based on the file or the fullFileName. If the file is null then fullFileName will
   * be used. If fullFileName is null then nothing will be set.
   */
  public void generateFileInfo() {
    String name;
    if (file != null) {
      name = file.getName();
    } else if (fullFileName != null) {
      name = fullFileName;
    } else {
      return;
    }
    int dot = name.lastIndexOf(".");
    fileName = name.substring(0, dot);
    extension = name.substring(dot + 1); // + 1 to get rid of the dot
  }

  /**
   * Generates the bytes of the file from the current file and sets this object's file bytes. WARNING: This
   * will not work if the file is greater than 2 GB because that's the maximum size of an array. This is
   * really unlikely to happen for e-mail attachments
   *
   * @return the file bytes
   * @throws FileNotFoundException if the file does not exist on the system
   * @throws IOException when reading the file
   * @throws when reading fileBytes
   */
  public byte[] generateFileBytes() throws FileNotFoundException, IOException, Exception {
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

  /**
   * @return the bodyPart
   */
  public MimeBodyPart getBodyPart() {
    return bodyPart;
  }

  /**
   * @param bodyPart the bodyPart to set
   */
  public void setBodyPart(MimeBodyPart bodyPart) {
    this.bodyPart = bodyPart;
  }
}

package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.IOHelper;
import com.kentcdodds.javahelper.helpers.StringHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
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
  private URL url;
  private byte[] fileBytes;
  private String fileName;
  private String attachmentName;
  private String extension;
  private String fullFileName;
  private MimeBodyPart bodyPart;
  /**
   * Used when generating the body part
   */
  private String contentId, contentType, disposition;

  /**
   * Don't forget to set filetype, filename, contentType (defaults to "application/octet-stream"), disposition (defaults
   * to Message.ATTACHMENT", and file/url/filebytes.
   */
  public EmailAttachment() {
    contentType = "application/octet-stream";
    disposition = Message.ATTACHMENT;
  }

  /**
   * Constructor: This is the minimum you need to send the attachment.
   *
   * @param fileBytes
   * @param fullFileName example: "document.txt"
   */
  public EmailAttachment(byte[] fileBytes, String fullFileName, String contentType, String disposition) {
    this.fileBytes = fileBytes;
    this.fullFileName = fullFileName;
    this.contentType = contentType;
    this.disposition = disposition;
  }

  /**
   * Constructor: This is the minimum you need to send the attachment. The file must be valid
   *
   * @param file
   */
  public EmailAttachment(File file, String contentType, String disposition) {
    this.file = file;
    this.contentType = contentType;
    this.disposition = disposition;
  }

  /**
   * Constructor: This is the minimum you need to send the attachment. The url must be valid
   *
   * @param file
   */
  public EmailAttachment(URL url, String contentType, String disposition) {
    this.url = url;
    this.contentType = contentType;
    this.disposition = disposition;
  }

  /**
   * Generates and sets the MimeBodyPart for this attachment. First checks whether the attachment has bytes already.
   * This allows you to give an email attachment which has bytes in memory and doesn't have an actual file. If the bytes
   * are null it reads the bytes from the file. If the file is null returns false. **Important** Be sure the at least
   * one of the following groups of this Email attachment has been set before calling this method, otherwise you'll get
   * a null pointer exception: fullFileName, file, or fileName and extension (both)
   *
   * @return whether the MimeBodyPart was successfully generated
   * @throws MessagingException when setting DataHandler and the filename
   */
  public boolean generateMimeBodyPart() throws MessagingException, FileNotFoundException, IOException, Exception {
    DataSource source;
    if (bodyPart != null) {
      return true;
    }
    bodyPart = new MimeBodyPart();
    if (file != null) {
      source = getFileBodyPart();
      bodyPart.setDisposition(disposition);
    } else if (fileBytes != null) {
      source = getByteArrayBodyPart();
    } else if (url != null) {
      source = getURLBodyPart();
      bodyPart.setDisposition(disposition);
    } else {
      bodyPart = null; //Because it was set to a new one before but it doesn't have a datahandler.
      return false;
    }
    bodyPart.setDataHandler(new DataHandler(source));
    bodyPart.setFileName(getAttachmentName());
    if (contentId != null) {
      bodyPart.setContentID(contentId);
    }
    return true;
  }

  /**
   * Returns a new FileDataSource with the file
   *
   * @return
   */
  private DataSource getFileBodyPart() {
    return new FileDataSource(file) {

      @Override
      public String getContentType() {
        return contentType;
      }
    };

  }

  /**
   * Returns a new ByteArrayDataSource with the filebytes and disposition
   *
   * @return
   */
  private DataSource getByteArrayBodyPart() {
    return new ByteArrayDataSource(fileBytes, disposition) {

      @Override
      public String getContentType() {
        return contentType;
      }
    };
  }

  /**
   * Returns a new URLDataSource with the url.
   *
   * @return
   */
  private DataSource getURLBodyPart() {
    return new URLDataSource(url) {

      @Override
      public String getContentType() {
        return contentType;
      }
    };
  }

  /**
   * Sets the attachment as an inline attachment.
   *
   * @param contentType the type of content ("image/jpeg" for example).
   * @param contentId
   * @throws MessagingException
   */
  public void setBodyPartAsInlineAttachment(String contentType, String contentId) throws MessagingException {
    setContentType(contentType);
    setDisposition(MimeBodyPart.INLINE);
    setContentId("<" + contentId + ">");
  }

  /**
   * Gets the size of the attachment (from the fileBytes if this is not null, or the file if the file is not null).
   *
   * @return
   */
  public long getSize() {
    if (fileBytes != null) {
      return fileBytes.length;
    } else if (file != null) {
      return file.length();
    }
    return -1;
  }

  /**
   * Gets the filename of the attachment ("document.doc" returns "document"). If the fileName is null, calls
   * generateFileInfo(). Important: If the file of this attachment is null and the fileName has not already been set,
   * you'll get a null value back.
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
   * generateFileInfo(). Important: If the file of this attachment is null and the extension has not already been set,
   * you'll get a null value back.
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
   * If the full filename is null, uses file.getName(). If file is null, generates it with getFileName() + "." +
   * getExtension().
   *
   * @return the fullFilename
   */
  public String getFullFileName() {
    if (fullFileName == null) {
      if (file != null) {
        fullFileName = file.getName();
      } else if (url != null) {
        fullFileName = url.getFile().substring(1);
      } else {
        fullFileName = getFileName() + "." + getExtension();
      }
    }
    return fullFileName;
  }

  /**
   * Generates the file info based on the file or the fullFileName. If the file is null then fullFileName will be used.
   * If fullFileName is null then nothing will be set.
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
   * Generates the bytes of the file from the current file and sets this object's file bytes. WARNING: This will not
   * work if the file is greater than 2 GB because that's the maximum size of an array. This is really unlikely to
   * happen for e-mail attachments
   *
   * @return the file bytes
   * @throws FileNotFoundException if the file does not exist on the system
   * @throws IOException when reading the file
   * @throws when reading fileBytes
   */
  public void generateFileBytes() throws FileNotFoundException, IOException, Exception {
    if (file != null) {
      this.fileBytes = IOHelper.getFileBytes(file);
    } else if (url != null) {
      this.fileBytes = IOHelper.downloadFile(url);
    }
  }

  @Override
  public String toString() {
    return StringHelper.splitBy(
            StringHelper.newline, "Attachment Name: " + ((attachmentName != null) ? attachmentName : "null"),
            "File: " + ((file != null) ? file.getPath() : "null"),
            "File Name: " + ((fileName != null) ? fileName : "null"),
            "Extension: " + ((extension != null) ? extension : "null"),
            "Full File Name: " + ((fullFileName != null) ? fullFileName : "null"),
            "URL: " + ((url != null) ? url : "null"),
            "File Bytes size: " + ((fileBytes != null) ? fileBytes.length : "null"),
            "Mime Body Part: " + ((bodyPart != null) ? "exists" : "null"));
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

  /**
   * @return the attachmentName. Sets it to getFullFileName() if attachmentName is null
   */
  public String getAttachmentName() {
    if (attachmentName == null) {
      attachmentName = getFullFileName();
    }
    return attachmentName;
  }

  /**
   * @param attachmentName the attachmentName to set
   */
  public void setAttachmentName(String attachmentName) {
    this.attachmentName = attachmentName;
  }

  /**
   * @return the url
   */
  public URL getUrl() {
    return url;
  }

  /**
   * @param url the url to set
   */
  public void setUrl(URL url) {
    this.url = url;
  }

  /**
   * @param contentId the contentId to set. Note: If this is to be a disposition of MimeBodyPart.INLINE, surround the
   * contentId with "<" + contentId + ">". This is something special with inline attachments.
   */
  public void setContentId(String contentId) {
    this.contentId = contentId;
  }

  /**
   * @param contentType the contentType to set
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * @param disposition the disposition to set
   */
  public void setDisposition(String disposition) {
    this.disposition = disposition;
  }
}

package com.kentcdodds.javahelper.model;

import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author kentcdodds
 */
public class Email {

  private String from;
  private List<String> to;
  private List<String> cc;
  private List<String> bcc;
  private String subject = "";
  private String body = "";
  private List<MimeBodyPart> bodyParts = new ArrayList<>();

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Constructor: Don't forget to set variables!
   */
  public Email() {
  }

  /**
   * Constructor: Use for e-mails without attachments
   *
   * @throws MessagingException when trying to add the body to the message
   */
  public Email(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String body) throws MessagingException {
    this.from = from;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.body = body;
  }

  /**
   * Constructor: Use for e-mails with attachments (the message should be included in the body parts)
   */
  public Email(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String body, List<MimeBodyPart> bodyParts) {
    this.from = from;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.bodyParts = bodyParts;
  }
  //</editor-fold>

  /**
   * Adds the given attachment as a body part. First checks whether the attachment has bytes already. This allows you to
   * give an email attachment which has bytes in memory and doesn't have an actual file. If the bytes are null it reads
   * the bytes from the file. If the file is null no attachment is added.
   *
   * @param attachment
   * @return whether the attachment was successfully added to the bodyParts list
   */
  public boolean addEmailAttachment(EmailAttachment attachment) throws MessagingException {
    DataSource source;
    if (attachment.getFileBytes() != null) {
      source = new ByteArrayDataSource(attachment.getFileBytes(), Message.ATTACHMENT);
    } else if (attachment.getFile() != null) {
      source = new FileDataSource(attachment.getFile()) {

        @Override
        public String getContentType() {
          return "application/octet-stream";
        }
      };
    } else {
      return false;
    }
    MimeBodyPart messageBodyPart = new MimeBodyPart();

    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(attachment.getFullFilename());
    bodyParts.add(messageBodyPart);
    return true;
  }

  //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
  /**
   * @return the from
   */
  public String getFrom() {
    return from;
  }

  /**
   * @param from the from to set
   */
  public void setFrom(String from) {
    this.from = from;
  }

  /**
   * @return the to
   */
  public List<String> getTo() {
    return to;
  }

  /**
   * @param to the to to set
   */
  public void setTo(List<String> to) {
    this.to = to;
  }

  /**
   * @return the cc
   */
  public List<String> getCc() {
    return cc;
  }

  /**
   * @param cc the cc to set
   */
  public void setCc(List<String> cc) {
    this.cc = cc;
  }

  /**
   * @return the bcc
   */
  public List<String> getBcc() {
    return bcc;
  }

  /**
   * @param bcc the bcc to set
   */
  public void setBcc(List<String> bcc) {
    this.bcc = bcc;
  }

  /**
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * @param subject the subject to set
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * @return the bodyParts
   */
  public List<MimeBodyPart> getBodyParts() {
    return bodyParts;
  }

  /**
   * @param bodyParts the bodyParts to set
   */
  public void setBodyParts(List<MimeBodyPart> bodyParts) {
    this.bodyParts = bodyParts;
  }

  /**
   * @return the body
   */
  public String getBody() {
    return body;
  }

  /**
   * @param body the body to set
   */
  public void setBody(String body) {
    this.body = body;
  }
  //</editor-fold>
}

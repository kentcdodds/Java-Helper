package com.kentcdodds.javahelper.model;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

/**
 *
 * @author kentcdodds
 */
public class Email {

  private String from = "";
  private List<String> replyTo = new ArrayList<>();
  private List<String> to = new ArrayList<>();
  private List<String> cc = new ArrayList<>();
  private List<String> bcc = new ArrayList<>();
  private String subject = "";
  private String body = "";
  private boolean html = false;
  private List<MimeBodyPart> bodyParts = new ArrayList<>();

  //<editor-fold defaultstate="collapsed" desc="Constructors">
  /**
   * Constructor: Don't forget to set variables!
   */
  public Email() {
  }

  /**
   * Convenience Constructor: The bare minimum information you need to send an e-mail.
   *
   * @param from
   * @param to
   * @param body
   * @param subject
   */
  public Email(String from, String to, String subject, String body) {
    this.from = from;
    this.to.add(to);
    this.subject = subject;
    this.body = body;
  }

  /**
   * Constructor: Use for e-mails without attachments
   *
   * @param from
   * @param to
   * @param body
   * @param cc
   * @param bcc
   * @param subject
   */
  public Email(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String body) {
    this.from = from;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.body = body;
  }

  /**
   * Full Constructor: Use for e-mails with attachments
   *
   * @param from
   * @param to
   * @param bodyParts
   * @param cc
   * @param subject
   * @param bcc
   * @param body
   */
  public Email(String from, List<String> replyTo, List<String> to, List<String> cc, List<String> bcc, String subject, String body, boolean html, List<MimeBodyPart> bodyParts) {
    this.from = from;
    this.replyTo = replyTo;
    this.to = to;
    this.cc = cc;
    this.bcc = bcc;
    this.subject = subject;
    this.html = html;
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
   * @throws MessagingException
   */
  public boolean addEmailAttachment(EmailAttachment attachment) throws MessagingException {
    if (attachment.getBodyPart() == null) {
      boolean success = attachment.generateMimeBodyPart();
      if (!success) {
        return false;
      }
    }
    bodyParts.add(attachment.getBodyPart());
    return true;
  }

  /**
   * Gets the body body part with proper encoding
   *
   * @return
   * @throws MessagingException
   */
  public MimeBodyPart getContentBodyPart() throws MessagingException {
    MimeBodyPart contentBodyPart = new MimeBodyPart();
    if (html) {
      contentBodyPart.setHeader("Content-Type", "text/html");
      contentBodyPart.setContent(body, "text/html");
    } else {
      contentBodyPart.setContent(body, "text/plain");
    }
    return contentBodyPart;
  }
  
  /**
   * Adds the given address(s) to the replyTo list
   */
  public void addReplyTo(String... address) {
    replyTo.addAll(java.util.Arrays.asList(address));
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

  /**
   * @return the html
   */
  public boolean isHtml() {
    return html;
  }

  /**
   * @param html the html to set
   */
  public void setHtml(boolean html) {
    this.html = html;
  }

  /**
   * @return the replyTo
   */
  public List<String> getReplyTo() {
    return replyTo;
  }

  /**
   * @param replyTo the replyTo to set
   */
  public void setReplyTo(List<String> replyTo) {
    this.replyTo = replyTo;
  }
  //</editor-fold>
}

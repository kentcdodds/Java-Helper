package com.kentcdodds.javahelper.model;

import com.kentcdodds.javahelper.helpers.StringHelper;
import java.io.FileNotFoundException;
import java.io.IOException;
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
   * Convenience method. Allows adding of many attachments on one method call. Does not allow you to handle your own
   * exceptions.
   *
   * @param attachment
   * @return all the attachments which failed either because the mimeBodyPart generation failed or there was another
   * exception.
   */
  public List<EmailAttachment> addEmailAttachments(EmailAttachment... attachment) {
    List<EmailAttachment> failedAttachments = new ArrayList<>();
    for (EmailAttachment emailAttachment : attachment) {
      try {
        boolean success = addEmailAttachment(emailAttachment);
        if (!success) {
          failedAttachments.add(emailAttachment);
        }
      } catch (Exception ex) {
        failedAttachments.add(emailAttachment);
      }
    }
    return failedAttachments;
  }

  /**
   * Adds the given attachment as a body part. First checks whether the attachment has bytes already. This allows you to
   * give an email attachment which has bytes in memory and doesn't have an actual file. If the bytes are null it reads
   * the bytes from the file. If the file is null no attachment is added.
   *
   * @param attachment
   * @return whether the attachment was successfully added to the bodyParts list
   * @throws MessagingException
   */
  public boolean addEmailAttachment(EmailAttachment attachment) throws MessagingException, FileNotFoundException, IOException, Exception {
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
   * Returns information about the email object.
   *
   * @return
   */
  public String getEmailInString() {
    String shortenedBody = StringHelper.shortenString(body, 75, .5);
    String indentedBody = "\t" + shortenedBody.replace(StringHelper.newline, StringHelper.newline + "\t");
    String bodyLine = "(Shortened) Body (html >>> " + html + "):" + StringHelper.newline + indentedBody;

    StringBuilder bodyPartBuilder = new StringBuilder("bodyParts:");
    for (MimeBodyPart mimeBodyPart : bodyParts) {
      bodyPartBuilder.append(StringHelper.newline).append("\t");
      try {
        bodyPartBuilder.append(mimeBodyPart.getFileName());
      } catch (MessagingException ex) {
        bodyPartBuilder.append("N/A");
      }
    }
    return StringHelper.splitBy(StringHelper.newline,
            "from: " + from,
            "replyTo: " + StringHelper.splitBy(", ", replyTo),
            "to: " + StringHelper.splitBy(", ", to),
            "cc: " + StringHelper.splitBy(", ", cc),
            "bcc: " + StringHelper.splitBy(", ", bcc),
            "subject: " + subject,
            bodyLine,
            bodyPartBuilder.toString());
  }

  //<editor-fold defaultstate="collapsed" desc="Add participants">
  /**
   * Adds the given address(es) to the to list
   *
   * @param address
   */
  public void addTo(String... address) {
    to.addAll(java.util.Arrays.asList(address));
  }

  /**
   * Adds the given address(es) to the cc list
   *
   * @param address
   */
  public void addCc(String... address) {
    cc.addAll(java.util.Arrays.asList(address));
  }

  /**
   * Adds the given address(es) to the bcc list
   *
   * @param address
   */
  public void addBcc(String... address) {
    bcc.addAll(java.util.Arrays.asList(address));
  }

  /**
   * Adds the given address(es) to the replyTo list
   *
   * @param address
   */
  public void addReplyTo(String... address) {
    replyTo.addAll(java.util.Arrays.asList(address));
  }

  /**
   * Adds the given address(es) to the to list
   *
   * @param addresses
   */
  public void addTo(List<String> addresses) {
    to.addAll(addresses);
  }

  /**
   * Adds the given address(es) to the cc list
   *
   * @param address
   */
  public void addCc(List<String> addresses) {
    cc.addAll(addresses);
  }

  /**
   * Adds the given address(es) to the bcc list
   *
   * @param address
   */
  public void addBcc(List<String> addresses) {
    bcc.addAll(addresses);
  }

  /**
   * Adds the given address(es) to the replyTo list
   *
   * @param address
   */
  public void addReplyTo(List<String> addresses) {
    replyTo.addAll(addresses);
  }
  //</editor-fold>

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

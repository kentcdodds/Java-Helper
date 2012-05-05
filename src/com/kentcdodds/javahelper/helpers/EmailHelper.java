package com.kentcdodds.javahelper.helpers;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import com.kentcdodds.javahelper.model.Email;

/**
 *
 * @author kentcdodds
 */
public class EmailHelper {

  public static final int GOOGLE_APPS = 123;

  /**
   * Convenience method. Sends an e-mail using the javax.mail Library with the given service (EmailHelper.GOOGLE_APPS
   * for example). In some cases which don't require authentication, null may be used for username and password.
   *
   * @param service an integer representing the service you wish to send the e-mail with. Like EmailHelper.GOOGLE_APPS.
   * @param email the e-mail to send
   * @param username the username for authentication
   * @param password the password for authentication
   * @throws MessagingException when trying 
   */
  public static void sendEmail(int service, Email email, final String username, final String password) throws MessagingException {
    Session session = null;
    switch (service) {
      case GOOGLE_APPS:
        session = getGoogleSession(username, password);
        break;
    }
    sendEmail(session, email);
  }

  /**
   * Gets a javax.mail.Session for Google apps and Gmail
   *
   * @param username
   * @param password
   * @return
   */
  public static Session getGoogleSession(final String username, final String password) {
    Properties properties = new Properties();
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.socketFactory.port", "465");
    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.port", "465");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });
    return session;
  }

  /**
   * Sends the given email on the session
   *
   * @param session
   * @param email
   * @throws MessagingException
   */
  public static void sendEmail(Session session, Email email) throws MessagingException {
    session.setDebug(true);
    Message message = new MimeMessage(session);

    //Set participants
    message.setFrom(new InternetAddress(email.getFrom()));

    for (String address : email.getTo()) {
      message.addRecipient(Message.RecipientType.TO,
              new InternetAddress(address));
    }
    for (String address : email.getCc()) {
      message.addRecipient(Message.RecipientType.CC,
              new InternetAddress(address));
    }
    for (String address : email.getBcc()) {
      message.addRecipient(Message.RecipientType.BCC,
              new InternetAddress(address));
    }

    //Set content
    Multipart multipart = new MimeMultipart();
    message.setSubject(email.getSubject());
    MimeBodyPart contentPart = new MimeBodyPart();
    contentPart.setText(email.getBody());
    multipart.addBodyPart(contentPart);
    for (MimeBodyPart mimeBodyPart : email.getBodyParts()) {
      multipart.addBodyPart(mimeBodyPart);
    }
    message.setContent(multipart);
    
    //Send the message
    Transport.send(message);
  }

  /**
   * Creates a MimeBodyPart for an attachment in an e-mail with the given bytes and filename
   *
   * @param attachmentBytes
   * @param filename
   * @return
   * @throws MessagingException
   */
  public static MimeBodyPart getAttachment(byte[] attachmentBytes, String filename) throws MessagingException {
    MimeBodyPart attachmentPart = new MimeBodyPart();
    DataSource source = new ByteArrayDataSource(attachmentBytes, Message.ATTACHMENT) {

      @Override//must override for the save function on msg to work
      public String getContentType() {
        return "application/octet-stream";
      }
    };
    attachmentPart.setDataHandler(new DataHandler(source));
    attachmentPart.setFileName(filename);
    return attachmentPart;
  }
}

package com.kentcdodds.javahelper.helpers;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Holds helper methods that haven't found a home yet
 *
 * @author Kent
 */
public class OtherHelper {

  /**
   * Makes sure that the given file's parent directory exists. Creates it if not.
   *
   * @param file the file to check
   */
  public static void checkDirectory(File file) {
    if (!new File(file.getParent()).exists()) {
      new File(file.getParent()).mkdir();
    }
  }

  /**
   * Checks whether any of the given parameters are null
   *
   * @param object the object/objects/array of objects to check
   * @return true if any give parameter is null
   */
  public static boolean isNull(Object... object) {
    for (Object object1 : object) {
      if (object1 == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * Convenience method. Allows you to call the sendEmail method with a List object. Just calls the other
   * sendEmail();
   *
   * @param fromAddress
   * @param username
   * @param password
   * @param subject
   * @param content
   * @param destinationAddress
   * @throws MessagingException
   */
  public static void sendEmail(String fromAddress, final String username, final String password, String subject, String content, java.util.List<String> destinationAddress) throws MessagingException {
    sendEmail(fromAddress, username, password, subject, content, destinationAddress.toArray(new String[destinationAddress.size()]));
  }

  /**
   * Sends an e-mail to the given address with the given message and subject
   *
   * @param fromAddress
   * @param destinationAddress
   * @param username
   * @param password for the from address
   * @param subject
   * @param content
   * @throws MessagingException
   */
  public static void sendEmail(String fromAddress, final String username, final String password, String subject, String content, String... destinationAddress) throws MessagingException {
    Properties props = new Properties();
    if (username.contains("@gmail.com")) {
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.port", "465");
    } else {
      //TODO: Make this support more than just gmail!
      PrinterHelper.printErr("username: " + username + " in sendEmail must be a Google Apps or Gmail account. Nothing else is supported yet!");
    }

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {

      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(fromAddress));
    for (String address : destinationAddress) {
      message.addRecipient(Message.RecipientType.TO,
              new InternetAddress(address));
    }
    message.setSubject(subject);
    message.setText(content);

    Transport.send(message);
  }

  /**
   * This removes the items given in the first list from the second list
   *
   * @param thisList
   * @param thatList
   */
  public static void removeThisFromThat(java.util.List thisList, java.util.List thatList) {
    for (Object removeObject : thisList) {
      thatList.remove(removeObject);
    }
  }
}

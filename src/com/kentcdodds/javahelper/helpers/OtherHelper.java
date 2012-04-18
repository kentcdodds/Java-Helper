package com.kentcdodds.javahelper.helpers;

import java.io.File;
import java.util.Properties;
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
   * Convenience method. Allows you to call the sendEmail method with a List object. Just converts the
   * destinationAddressList to an array and calls the other sendEmail() with the same parameters
   *
   * @param fromAddress the address to set as the fromAddress
   * @param username the username for authentication
   * @param password the password for authentication
   * @param subject the subject of the e-mail
   * @param content the content of the e-mail
   * @param destinationAddressList the destinationAddresses
   * @throws MessagingException
   */
  public static void sendEmail(String fromAddress, final String username, final String password, String subject, String content, java.util.List<String> destinationAddressList) throws MessagingException {
    sendEmail(fromAddress, username, password, subject, content, destinationAddressList.toArray(new String[destinationAddressList.size()]));
  }

  /**
   * Sends an e-mail using the javax.mail Library. Currently does not support anything but Google Apps
   * accounts (gmail included);
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
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

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

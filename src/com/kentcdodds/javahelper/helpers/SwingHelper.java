package com.kentcdodds.javahelper.helpers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.psafix.folderchooser.JFolderChooser;

/**
 *
 * @author Kent
 */
public class SwingHelper {

  /**
   * The number of characters used when wordWrappedMessage(String message) is called. Defaults to 50
   */
  private static int wordWrapChars = 50;
  private static int windowXCenter = -1;
  private static int windowYCenter = -1;

  //<editor-fold defaultstate="collapsed" desc="Window size/position Methods">
  /**
   * This method packs, sets the size, and sets the position of the window given
   *
   * @param window
   */
  public static void centerAndPack(Window window) {
    window.pack();
    int x = getWindowXCenter() - (window.getWidth() / 2);
    int y = getWindowYCenter() - (window.getHeight() / 2);
    window.setLocation(x, y);
    window.setMinimumSize(new Dimension(window.getWidth(), window.getHeight()));
  }

  /**
   * Maximizes the given JFrame
   *
   * @param frame
   */
  public static void maximizeWindow(JFrame frame) {
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Image methods">
  /**
   * Returns the image pointed at by the given urlString
   *
   * @param urlString the location of the image
   * @return the image
   * @throws MalformedURLException
   */
  public static Image getImageFromUrl(String urlString) throws MalformedURLException {
    URL url = new URL(urlString);
    return Toolkit.getDefaultToolkit().createImage(url);
  }

  //<editor-fold defaultstate="collapsed" desc="Resize Image Methods">
  /**
   * This method resizes the given image using Image.SCALE_SMOOTH.
   *
   * @param image the image to be resized
   * @param width the desired width of the new image. Negative values force the only constraint to be height.
   * @param height the desired height of the new image. Negative values force the only constraint to be width.
   * @param max if true, sets the width and height as maximum heights and widths, if false, they are minimums.
   * @return the resized image.
   */
  public static Image resizeImage(Image image, int width, int height, boolean max) {
    if (width < 0 && height > 0) {
      return resizeImageBy(image, height, false);
    } else if (width > 0 && height < 0) {
      return resizeImageBy(image, width, true);
    } else if (width < 0 && height < 0) {
      PrinterHelper.printErr("Setting the image size to (width, height) of: ("
              + width + ", " + height + ") effectively means \"do nothing\"... Returning original image");
      return image;
      //alternatively you can use System.err.println("");
      //or you could just ignore this case
    }
    int currentHeight = image.getHeight(null);
    int currentWidth = image.getWidth(null);
    int expectedWidth = (height * currentWidth) / currentHeight;
    //Size will be set to the height
    //unless the expectedWidth is greater than the width and the constraint is maximum
    //or the expectedWidth is less than the width and the constraint is minimum
    int size = height;
    if (max && expectedWidth > width) {
      size = width;
    } else if (!max && expectedWidth < width) {
      size = width;
    }
    return resizeImageBy(image, size, (size == width));
  }

  /**
   * Resizes the given image using Image.SCALE_SMOOTH.
   *
   * @param image the image to be resized
   * @param size the size to resize the width/height by (see setWidth)
   * @param setWidth whether the size applies to the height or to the width
   * @return the resized image
   */
  public static Image resizeImageBy(Image image, int size, boolean setWidth) {
    if (setWidth) {
      return image.getScaledInstance(size, -1, Image.SCALE_SMOOTH);
    } else {
      return image.getScaledInstance(-1, size, Image.SCALE_SMOOTH);
    }
  }

  /**
   * Convenience method. Creates a BufferedImage from the given file location and returns resizeImage(image, width,
   * height, max).
   *
   * @param location the location of the image
   * @param width the desired width of the new image. Negative values force the only constraint to be height.
   * @param height the desired height of the new image. Negative values force the only constraint to be width.
   * @param max if true, sets the width and height as maximum heights and widths, if false, they are minimums.
   * @return the resized image.
   * @throws IOException when trying to load a file from the given location
   */
  public static Image resizeImageFromFile(String location, int width, int height, boolean max) throws IOException {
    BufferedImage image = ImageIO.read(new File(location));
    return resizeImage(image, width, height, max);
  }

  /**
   * Resizes the given image using Image.SCALE_SMOOTH.
   *
   * @param location the location of the image
   * @param size the size to resize the width/height by (see setWidth)
   * @param setWidth whether the size applies to the height or to the width
   * @return the resized image
   * @throws IOException when loading the image from the file
   */
  public static Image resizeImageFromFileBy(String location, int size, boolean setWidth) throws IOException {
    BufferedImage image = ImageIO.read(new File(location));
    return resizeImageBy(image, size, setWidth);
  }

  /**
   * Convenience method. Creates a BufferedImage from the given resource location (respective to the given class) and
   * returns resizeImage(image, width, height, max).
   *
   * @param klass the class to get the resource from
   * @param location the location of the resource respective to the class
   * @param width the desired width of the new image. Negative values force the only constraint to be height.
   * @param height the desired height of the new image. Negative values force the only constraint to be width.
   * @param max if true, sets the width and height as maximum heights and widths, if false, they are minimums.
   * @return the resized image.
   * @throws IOException
   */
  public static Image resizeImageFromResource(Class klass, String location, int width, int height, boolean max) throws IOException {
    BufferedImage image = ImageIO.read(klass.getResource(location));
    return resizeImage(image, width, height, max);
  }

  /**
   * Resizes the given image using Image.SCALE_SMOOTH.
   *
   * @param klass the class to get the resource relative of
   * @param location the location of the resource to be resized
   * @param size the size to resize the width/height by (see setWidth)
   * @param setWidth whether the size applies to the height or to the width
   * @return the resized image
   * @throws IOException when reading the resource
   */
  public static Image resizeImageFromResourceBy(Class klass, String location, int size, boolean setWidth) throws IOException {
    BufferedImage image = ImageIO.read(klass.getResource(location));
    return resizeImageBy(image, size, setWidth);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Add Image Methods">
  /**
   * Adds a given image to the specified side of the given container
   *
   * @param container where you want the image
   * @param image the image to add
   * @param setWidth if true will set the width as the given size, if false, will set the height as the given size
   * @param size 0 - 4 is smallest to biggest
   * @param anchor use java.awt.GridBagConstraints.CENTER (for example)
   * @param bottom if true, image will appear on the bottom, if false, image will appear on the right.
   */
  public static void addImage(Container container, Image image, boolean setWidth, int size, int anchor, boolean bottom) {
    GridBagLayout gbl = (java.awt.GridBagLayout) container.getLayout();
    int[][] dim = gbl.getLayoutDimensions();
    int cols = dim[0].length;
    int rows = dim[1].length;
    java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
    if (bottom) {
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = rows;
      gridBagConstraints.gridwidth = cols;
    } else {
      gridBagConstraints.gridy = 0;
      gridBagConstraints.gridx = cols;
      gridBagConstraints.gridheight = rows;
    }
    gridBagConstraints.anchor = anchor;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
    JLabel logo = new JLabel();
    ImageIcon newImage;
    if (setWidth) {
      newImage = new ImageIcon(resizeImage(image, size, -1, true));
    } else {
      newImage = new ImageIcon(resizeImage(image, -1, size, true));
    }
    logo.setIcon(newImage);
    container.add(logo, gridBagConstraints);
  }

  /**
   * Convenience method.
   *
   * @param container where you want the image
   * @param location the location of the file
   * @param setWidth if true will set the width as the given size, if false, will set the height as the given size
   * @param size 0 - 4 is smallest to biggest
   * @param anchor use java.awt.GridBagConstraints.CENTER (for example)
   * @param bottom if true, image will appear on the bottom, if false, image will appear on the right.
   * @throws IOException
   */
  public static void addImageFromFile(Container container, String location, boolean setWidth, int size, int anchor, boolean bottom) throws IOException {
    BufferedImage image = ImageIO.read(new File(location));
    addImage(container, image, setWidth, size, anchor, bottom);
  }

  /**
   * Convenience method.
   *
   * @param klass the class to get the resource from
   * @param container where you want the image
   * @param location the location of the resource with respect to the given class
   * @param setWidth if true will set the width as the given size, if false, will set the height as the given size
   * @param size 0 - 4 is smallest to biggest
   * @param anchor use java.awt.GridBagConstraints.CENTER (for example)
   * @param bottom if true, image will appear on the bottom, if false, image will appear on the right.
   * @throws IOException
   */
  public static void addImageFromResource(Class klass, Container container, String location, boolean setWidth, int size, int anchor, boolean bottom) throws IOException {
    BufferedImage image = ImageIO.read(klass.getResource(location));
    addImage(container, image, setWidth, size, anchor, bottom);
  }
  //</editor-fold>
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Other Methods">
  /**
   * Gets the top parent Window of the given component. There is a limit to how far up the parent stack it'll go.
   *
   * @param <T>
   * @param jComponent
   * @return
   */
  public static <T extends Window> T getParentWindow(Component jComponent) {
    Container parent = jComponent.getParent();
    int i = 0;
    int limit = 100;
    while (true) {
      parent = parent.getParent();
      if (parent instanceof Window) {
        return (T) parent;
      }
      i++;
      if (i > limit) {
        return null; //Just to prevent an infinite loop...
      }
    }
  }

  /**
   * Returns a window with a partially opaque progress Icon. Sets the opacity, location and size (to the given icon
   * size) (does not pack the image)
   *
   * @param icon the icon to set in the progress window
   * @param opacity a float value from 0-1
   * @param x the x location of the window
   * @param y the y location of the window
   * @return a jWindow of the progress wheel
   */
  public static JWindow getProgressWheelWindow(final Icon icon, final Float opacity, final int x, final int y) {
    JWindow jWindow = new JWindow() {

      {
        setOpacity(opacity);
        setLocation(x, y);
        setSize(icon.getIconWidth(), icon.getIconHeight());
        add(new JLabel(icon));
      }
    };

    return jWindow;
  }

  /**
   * Convenience method. Returns a window with a partially opaque progress Icon in the center of the screen
   *
   * @param icon the icon to set in the progress window
   * @return a jWindow of the progress wheel
   */
  public static JWindow getProgressWheelWindow(final Icon icon) {
    JWindow window = getProgressWheelWindow(icon, .85f, 0, 0);
    //It's faster and simpler just to set the location to 0, 0 and then call center and pack on it
    centerAndPack(window);
    return window;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="File Open, Save, and Folder Choose Methods">
  /**
   * This method will allow for the saving of files
   *
   * @param startingPath
   * @param parent
   * @param title
   * @return the selected file
   */
  public static String fileSaver(String startingPath, Component parent, String title) {
    try {
      JFileChooser chooser = new JFileChooser();
      chooser.setPreferredSize(new Dimension(500, 350));
      File outputFile = new File(startingPath);
      if (outputFile.exists()) {
        chooser.setCurrentDirectory(outputFile);
      }
      chooser.setDialogTitle(title);
      int chosenOption = chooser.showSaveDialog(parent);
      if (chosenOption == JFileChooser.APPROVE_OPTION) {
        return chooser.getSelectedFile().getPath();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "There has been an error:" + StringHelper.newline + e);
    }
    return null;
  }

  /**
   * This method will allow for the opening of files
   *
   * @param startingPath
   * @param parent
   * @param title
   * @return the selected file
   */
  public static String fileOpener(String startingPath, Component parent, String title) {
    try {
      JFileChooser chooser = new JFileChooser();
      chooser.setPreferredSize(new Dimension(500, 350));
      File outputFile = new File(startingPath);
      if (outputFile.exists()) {
        chooser.setCurrentDirectory(outputFile);
      }
      chooser.setDialogTitle(title);
      int chosenOption = chooser.showOpenDialog(parent);
      if (chosenOption == JFileChooser.APPROVE_OPTION) {
        return chooser.getSelectedFile().getPath();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "There has been an error:" + StringHelper.newline + e);
    }
    return null;
  }

  /**
   * Opens a JFolderChooser of the given dimensions.
   *
   * @param startingPath The selected file path will be assigned to this String.
   * @param parent of the JFolderChooser
   * @param title of the JFolderChooser
   * @param dimension of the JFolderChooser
   * @return the selected path. Returns null if the user did not activate the approve option (closed the window or
   * clicked "Cancel")
   */
  public static String chooseFolder(String startingPath, Component parent, String title, Dimension dimension) {
    try {
      JFolderChooser chooser = new JFolderChooser();
      chooser.setPreferredSize(dimension);
      File outputFile = new File(startingPath);
      if (outputFile.exists()) {
        chooser.setCurrentDirectory(outputFile);
      }
      chooser.setTitle(title);
      int choice = chooser.showOpenDialog(parent);
      if (choice == JFolderChooser.APPROVE_OPTION) {
        return chooser.getSelectedFile().getPath();
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "There has been an error:" + StringHelper.newline + e);
    }
    return null;
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="JOptionPane Methods">
  /**
   * Convenience Method: Calls StringHelper.wordWrappedMessage(message, getWordWrapChars()). A method like this allows
   * you to keep everything standard for JOptionPane messages
   *
   * @param message
   * @return
   */
  public static String wordWrappedMessage(String message) {
    return StringHelper.wordWrap(message, getWordWrapChars());
  }

  /**
   * Shows an error message with the given message. Calls wordWrappedMessage(message). The title and the content have
   * preset text.
   *
   * @param parent the parent for the JOptionPane
   * @param message the message for the error
   */
  public static void showErrorMessage(Component parent, String message) {
    message = wordWrappedMessage("There has been an error! Here's the message: " + StringHelper.newline + message);
    JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows an error message with a preset message. Adds decimal if decimal is true
   *
   * @param parent the parent for the JOptionPane
   * @param decimal whether to include a decimal in the message
   */
  public static void showNumberInputErrorMessage(Component parent, boolean decimal) {
    String message = wordWrappedMessage("Please input a valid number with the following format:"
            + StringHelper.newline
            + "123");
    message += (decimal) ? ".45" : StringHelper.newline + "(Note, no decimal).";
    showErrorMessage(parent, message);
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Look and Feel (and color) Methods">
  /**
   * Set the System look and feel to the System standard
   */
  public static void setSystemLookAndFeel() {
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(SwingHelper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  /**
   * Sets the look and feel to the given type (like "Nimbus") Learn more here:
   * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
   *
   * @param lookAndFeel to set (like "Nimbus")
   */
  public static void setLookAndFeel(String lookAndFeel) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ((info.getName()).equals(lookAndFeel)) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(SwingHelper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
  }

  /**
   * Sets the background color of all the components in the frame Color the frame what you want the background for all
   * components to be
   *
   * @param container (start with the frame, it will recursively go through containers
   * @param color
   */
  public static void setUnifiedColor(Container container, Color color) {
    for (Component component : container.getComponents()) {
      component.setBackground(color);
      if (component instanceof Container) {
        setUnifiedColor((Container) component, color);
      }
    }
  }
  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
  /**
   * @return the wordWrapChars. This is the number of characters to wrap JOptionPane messages with when you call
   * wordWrappedMessage(String message)
   */
  public static int getWordWrapChars() {
    return wordWrapChars;
  }

  /**
   * @param aWordWrapChars the wordWrapChars to set. This is the number of characters to wrap JOptionPane messages with
   * when you call wordWrappedMessage(String message)
   */
  public static void setWordWrapChars(int aWordWrapChars) {
    wordWrapChars = aWordWrapChars;
  }

  /**
   * Don't use this method unless you want all the centerAndPack calls to set the center of the window somewhere other
   * than the center!
   *
   * @return the windowXCenter
   */
  public static int getWindowXCenter() {
    if (windowXCenter == -1) {
      windowXCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
    }
    return windowXCenter;
  }

  /**
   * Don't use this method unless you want all the centerAndPack calls to set the center of the window somewhere other
   * than the center!
   *
   * @param aWindowXCenter the windowXCenter to set
   */
  public static void setWindowXCenter(int aWindowXCenter) {
    windowXCenter = aWindowXCenter;
  }

  /**
   * @return the windowYCenter
   */
  public static int getWindowYCenter() {
    if (windowYCenter == -1) {
      windowYCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
    }
    return windowYCenter;
  }

  /**
   * @param aWindowYCenter the windowYCenter to set
   */
  public static void setWindowYCenter(int aWindowYCenter) {
    windowYCenter = aWindowYCenter;
  }
  //</editor-fold>
}

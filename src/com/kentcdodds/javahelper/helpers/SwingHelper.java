package com.kentcdodds.javahelper.helpers;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import org.psafix.folderchooser.JFolderChooser;

/**
 *
 * @author Kent
 */
public class SwingHelper {

  private static int wordWrapChars = 50;

  /**
   * This method packs, sets the size, and sets the position of the window given
   *
   * @param window
   */
  public static void centerAndPack(Window window) {
    window.pack();
    int y = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2) - window.getHeight() / 2;
    int x = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2) - window.getWidth() / 2;
    window.setLocation(x, y);
    window.setMinimumSize(new Dimension(window.getWidth(), window.getHeight()));
  }

  /**
   * this method maximizes the given item
   *
   * @param frame
   */
  public static void maximizeWindow(JFrame frame) {
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
  }

  /**
   * This method resizes the given image
   *
   * @param location
   * @param setWidth set as true if the given size is for the width, false if it's for the height
   * @param size the size of the width or height
   * @return
   */
  public static ImageIcon resizeImage(String location, boolean setWidth, int size) {
    if (setWidth) {
      return resizeImage(location, size, -1, true);
    } else {
      return resizeImage(location, -1, size, true);
    }
  }

  /**
   * Convenience method: Just calls the other resizeImage method. Makes it possible for you to pass in a
   * dimension object
   *
   * @param location
   * @param dimension
   * @param max
   * @return
   */
  public static ImageIcon resizeImage(String location, Dimension dimension, boolean max) {
    return resizeImage(location, (int) dimension.getWidth(), (int) dimension.getHeight(), max);
  }

  /**
   * This method resizes the given image
   *
   * @param location
   * @param width
   * @param height
   * @param max if true, sets the width and height as maximum heights and widths, if false, they are minimums
   * @return
   */
  public static ImageIcon resizeImage(String location, int width, int height, boolean max) {
    ImageIcon imageIcon;
    try {
      imageIcon = new ImageIcon(SwingHelper.class.getResource(location));
    } catch (NullPointerException e) {
      imageIcon = new ImageIcon(location);
    }
    Image image = imageIcon.getImage();
    Image newimg = image.getScaledInstance(-1, height, java.awt.Image.SCALE_SMOOTH);
    Image image1 = new ImageIcon(newimg).getImage();
    int width1 = image1.getWidth(null);
    if ((max && width1 > width) || (!max && width1 < width)) {
      newimg = image.getScaledInstance(width, -1, java.awt.Image.SCALE_SMOOTH);
    }
    return new ImageIcon(newimg);
  }

  /**
   * this method adds a given image to the bottom right of the layout
   *
   * @param container where you want the object
   * @param file the image you wish to add
   * @param setWidth if true will set the width as the given size, if false, will set the height as the given
   * size
   * @param size 0 - 4 is smallest to biggest
   * @param anchor use java.awt.GridBagConstraints.CENTER (for example)
   * @param bottom if true, image will appear on the bottom, if false, image will appear on the right.
   */
  public static void addImage(Container container, String file, boolean setWidth, int size, int anchor, boolean bottom) {
    GridBagLayout gbl = (java.awt.GridBagLayout) container.getLayout();
    JPanel logoPanel = new JPanel();
    logoPanel.setLayout(new java.awt.GridBagLayout());
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
    container.add(logoPanel, gridBagConstraints);
    JLabel logo = new JLabel();
    logo.setIcon(resizeImage(file, true, size));
    logoPanel.add(logo);
  }

  /**
   *
   * @param <T>
   * @param jComponent
   * @return
   */
  public static <T extends Window> T getParentWindow(Component jComponent) {
    Container parent = jComponent.getParent();
    int i = 0;
    int limit = 20;
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
   * @return the selected path. Returns null if the user did not activate the approve option (closed the
   * window or clicked "Cancel")
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

  /**
   * Convenience Method: Calls StringHelper.wordWrappedMessage(message, 50). A method like this allows you to
   * keep everything standard for JOptionPane messages
   *
   * @param message
   * @return
   */
  public static String wordWrappedMessage(String message) {
    return StringHelper.wordWrap(message, wordWrapChars);
  }

  /**
   * Shows an error message with the given message
   *
   * @param parent
   * @param message
   */
  public static void showErrorMessage(Component parent, String message) {
    message = wordWrappedMessage("There has been an error! Here's the message: " + StringHelper.newline + message);
    JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.ERROR_MESSAGE);
  }

  public static void showNumberInputErrorMessage(Component parent, Class c) {
    String message = wordWrappedMessage("Please input a valid number with the following format:" + StringHelper.newline);
    if (c == double.class || c == Double.class
            || c == float.class || c == Float.class
            || c == long.class || c == Long.class) {
      message += "123.45";
    } else if (c == int.class || c == Integer.class) {
      message += "123" + StringHelper.newline + "(Note, no decimal).";
    }
    showErrorMessage(parent, message);
  }

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
   * Sets the look and feel to the given type (like "Nimbus")... Er... Learn more here:
   * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
   *
   * @param lookAndFeel
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
   * Sets the background color of all the components in the frame Color the frame what you want the background
   * for all components to be
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

  /**
   * Returns a window with a partially opaque progress Icon
   *
   * @return
   */
  public static JWindow getProgressWheelWindow() {
    JWindow jWindow = new JWindow() {

      final Icon icon = resizeImage("/resources/icons/progressIcon.gif", 132, 132, true);

      {
        setOpacity(.842f);
        setLocation(0, 0);
        setSize(icon.getIconWidth(), icon.getIconHeight());
        add(new JLabel(icon));
        pack();
      }
    };

    centerAndPack(jWindow);

    return jWindow;
  }

  /**
   * @return the wordWrapChars
   */
  public static int getWordWrapChars() {
    return wordWrapChars;
  }

  /**
   * @param aWordWrapChars the wordWrapChars to set
   */
  public static void setWordWrapChars(int aWordWrapChars) {
    wordWrapChars = aWordWrapChars;
  }
}

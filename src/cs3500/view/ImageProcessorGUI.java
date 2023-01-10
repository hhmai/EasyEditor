package cs3500.view;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class for GUI.
 */
public class ImageProcessorGUI {

  /**
   * Main method.
   * @param args arguments in
   */
  public static void main(String[] args) {
    ImageProcessorGUIFrame.setDefaultLookAndFeelDecorated(false);
    ImageProcessorGUIFrame frame = new ImageProcessorGUIFrame();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
             IllegalAccessException e) {
      // handle exception
    } catch (Exception ignored) {
    }
  }
}

package cs3500.view;

import java.io.IOException;

/**
 * Handles the view and outputs messages and commands for the user.
 */
public interface ImageProcessorView {

  /**
   * Sends a message to the appendable object for user to view.
   * @param message string to be outputted.
   */
  void renderMessage(String message) throws IOException;
}

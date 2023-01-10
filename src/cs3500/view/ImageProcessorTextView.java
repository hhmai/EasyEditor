package cs3500.view;

import java.io.IOException;

/**
 * Text view for image processor for user commands not image rendering.
 */
public class ImageProcessorTextView implements ImageProcessorView {
  private final Appendable append;

  /**
   * Constructor denoting where output goes to.
   * @param append appendable object to transmit output to
   */
  public ImageProcessorTextView(Appendable append) {
    if (append == null) {
      throw new IllegalArgumentException();
    }
    this.append = append;
  }

  /**
   * Constructor defaulting output to System.out.
   */
  public ImageProcessorTextView() {
    this.append = System.out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.append.append(message);
  }
}

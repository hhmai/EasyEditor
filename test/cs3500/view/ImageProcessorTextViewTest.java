package cs3500.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Test class for image processor text view.
 */
public class ImageProcessorTextViewTest {
  ImageProcessorView view1;
  Appendable append;

  @Before
  public void init() {
    append = new StringBuilder();
    view1 = new ImageProcessorTextView(append);
  }

  @Test
  public void testRenderMessage() {
    try {
      view1.renderMessage("Hi");
    } catch (IOException ignored) {
    }
    assertEquals("Hi", append.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppend() {
    new ImageProcessorTextView(null);
  }
}
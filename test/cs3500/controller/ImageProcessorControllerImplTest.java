package cs3500.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.NoSuchElementException;

import cs3500.model.MockImageProcessorModel;
import cs3500.view.ImageProcessorTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests controller methods with mock model.
 */
public class ImageProcessorControllerImplTest {
  ImageProcessorController controller1;
  MockImageProcessorModel mock;
  StringBuilder out;

  @Before
  public void init() {
    out = new StringBuilder();
    mock = new MockImageProcessorModel(out);
  }

  @Test
  public void testStartProcessorLoad() {
    StringReader read = new StringReader("load images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried load images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorSave() {
    StringReader read = new StringReader("save images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried save images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorFlipHorizontal() {
    StringReader read = new StringReader("flipHorizontal images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried flipHorizontal images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorFlipVertical() {
    StringReader read = new StringReader("flipVertical images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried flipVertical images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorBrighten() {
    StringReader read = new StringReader("brighten 10 images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried brighten 10.0 images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorRedComponent() {
    StringReader read = new StringReader("red-component images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried greyScale red-component images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorGreenComponent() {
    StringReader read = new StringReader("green-component images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried greyScale green-component images/koala koala1", out.toString());
  }

  @Test
  public void testStartProcessorBlueComponent() {
    StringReader read = new StringReader("blue-component images/koala koala1 q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried greyScale blue-component images/koala koala1", out.toString());
  }

  @Test(expected = NoSuchElementException.class)
  public void testNoLeftReadable() {
    StringReader read = new StringReader("blue-component");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
  }

  @Test
  public void testBlur() {
    StringReader read = new StringReader("blur cat catBlur q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried blur cat catBlur", out.toString());
  }

  @Test
  public void testSharpening() {
    StringReader read = new StringReader("sharpen cat catSharp q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried sharpening cat catSharp", out.toString());
  }

  @Test
  public void testSepia() {
    StringReader read = new StringReader("sepia cat catSharp q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried sepia cat catSharp", out.toString());
  }

  @Test
  public void testGreyscaled() {
    StringReader read = new StringReader("greyscale cat catSharp q");
    controller1 = new ImageProcessorControllerImpl(mock, new ImageProcessorTextView(), read);
    controller1.startProcessor();
    assertEquals("tried greyscaled cat catSharp", out.toString());
  }
}
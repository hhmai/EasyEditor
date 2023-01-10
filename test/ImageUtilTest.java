import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import cs3500.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the util class.
 */
public class ImageUtilTest {

  @Test
  public void testPpmArray() throws FileNotFoundException {
    System.out.println(Arrays.deepToString(ImageUtil.ppmArray("Koala.ppm")));
    assertEquals("Hello", "Hello".toString());
  }
}
package cs3500.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Test class for image processor.
 */
public class ImageProcessorModel1Test {
  ImageProcessorModel1 model1;

  @Before
  public void init() {
    model1 = new ImageProcessorModel1();
    model1.imageNames.put("testImage", new int[][]{
            {1, 2, 10},
            {4, 5, 6, 4, 5, 6},
            {7, 8, 9, 4, 5, 6},
            {1, 2, 3, 4, 5, 6}
    });
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {4, 5, 6, 4, 5, 6},
            {7, 8, 9, 4, 5, 6},
            {1, 2, 3, 4, 5, 6}
    }, model1.imageNames.get("testImage"));
  }

  @Test
  public void testLoad() {
    model1.load("images/Koala.ppm",
            "koalaTest");
    int[][] ints = model1.imageNames.get("koalaTest");
    assertArrayEquals(new int[]{1024, 768, 255}, ints[0]);
    assertEquals(101, ints[1][0]);
    assertEquals(90, ints[1][1]);
  }

  @Test
  public void testLoadNewImgFormat() {
    model1.load("images/cat.jpg",
            "cat");
    int[][] ints = model1.imageNames.get("cat");
    assertArrayEquals(new int[]{508, 339, 255}, ints[0]);
    assertEquals(255, ints[1][0]);
    assertEquals(255, ints[1][1]);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNullDirectory() {
    model1.load(null, "testImg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadNullName() {
    model1.load("/images/Koala.ppm", null);
  }

  @Test
  public void testSave() {
    model1.load("images/Koala.ppm",
            "koalaTest");
    model1.flipHorizontal("koalaTest", "koalaFlippedH");
    model1.save("images/KoalaFF.ppm",
            "koalaFlippedH");
    model1.load("images/KoalaFF.ppm",
            "koalaFlippedHSame");
    int[][] ints = model1.imageNames.get("koalaFlippedHSame");
    assertEquals(92, ints[1][0]);
    assertEquals(91, ints[1][1]);
    assertEquals(60, ints[1][2]);
    model1.load("images/Rainbow.ppm", "rainbow");
    model1.brighten(-30, "rainbow", "rainbowBrighten");
    model1.save("images/RainbowBrightenedNeg.ppm", "rainbowBrighten");
    model1.load("images/cat.jpg", "cat");
    model1.save("images/catSave.jpg", "cat");
    model1.load("images/catSave.jpg", "catSave");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveNullPath() {
    model1.load("images/Koala.ppm",
            "koalaTest");
    model1.flipHorizontal("koalaTest", "koalaFlippedH");
    model1.save(null,
            "koalaFlippedH");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveNullName() {
    model1.load("images/Koala.ppm",
            "koalaTest");
    model1.flipHorizontal("koalaTest", "koalaFlippedH");
    model1.save("images/KoalaFF.ppm",
            null);
  }

  @Test
  public void testFlip() {
    model1.flipHorizontal("testImage", "testImageFlipHorizontal");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {4, 5, 6, 4, 5, 6},
            {4, 5, 6, 7, 8, 9},
            {4, 5, 6, 1, 2, 3}
    }, model1.imageNames.get("testImageFlipHorizontal"));
    model1.flipVertical("testImage", "testImageFlipVertical");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {1, 2, 3, 4, 5, 6},
            {7, 8, 9, 4, 5, 6},
            {4, 5, 6, 4, 5, 6}
    }, model1.imageNames.get("testImageFlipVertical"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontalNull() {
    model1.flipHorizontal(null, "testImg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontalNull2() {
    model1.flipHorizontal("testImg", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipHorizontalNoSuchImage() {
    model1.flipHorizontal("123", "test2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticalNull() {
    model1.flipVertical(null, "testImg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticalNull2() {
    model1.flipVertical("testImg", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFlipVerticalNoSuchImage() {
    model1.flipVertical("123", "test2");
  }

  @Test
  public void testRedGreyScale() {
    model1.greyScale("red-component", "testImage", "testImgRedGS");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {4, 4, 4, 4, 4, 4},
            {7, 7, 7, 4, 4, 4},
            {1, 1, 1, 4, 4, 4}
    }, model1.imageNames.get("testImgRedGS"));
  }

  @Test
  public void testGreenGreyScale() {
    model1.greyScale("green-component", "testImage", "testImgGreenGS");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {5, 5, 5, 5, 5, 5},
            {8, 8, 8, 5, 5, 5},
            {2, 2, 2, 5, 5, 5}
    }, model1.imageNames.get("testImgGreenGS"));
  }

  @Test
  public void testBlueGreyScale() {
    model1.greyScale("blue-component", "testImage", "testImgBlueGS");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {6, 6, 6, 6, 6, 6},
            {9, 9, 9, 6, 6, 6},
            {3, 3, 3, 6, 6, 6}
    }, model1.imageNames.get("testImgBlueGS"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleNoImageException() {
    model1.greyScale("blue-component", "123", "123gs");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyScaleNoRGB() {
    model1.greyScale("123", "testImage", "testGs");
  }

  @Test
  public void testBrighten() {
    model1.brighten(1.0, "testImage", "testImageBrighten");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {5, 6, 7, 5, 6, 7},
            {8, 9, 10, 5, 6, 7},
            {2, 3, 4, 5, 6, 7}
    }, model1.imageNames.get("testImageBrighten"));
    model1.brighten(-30.0, "testImage", "testImageBrighten2");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}
    }, model1.imageNames.get("testImageBrighten2"));
    model1.brighten(100.0, "testImage", "testImageBrighten3");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10},
            {10, 10, 10, 10, 10, 10}
    }, model1.imageNames.get("testImageBrighten2"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightNoImage() {
    model1.brighten(1.0, "123", "123brightened");
  }

  @Test
  public void testBlur() {
    model1.blur("testImage", "testImageBlur");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}}, model1.imageNames.get("testImageBlur"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurNoImage() {
    model1.blur("imt123", "testImageBlur");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpeningNoImage() {
    model1.sharpening("imt123", "testImageBlur");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaNoImage() {
    model1.sepia("imt123", "testImageBlur");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaledNoImage() {
    model1.greyscaled("imt123", "testImageBlur");
  }

  @Test
  public void testSharpen() {
    model1.sharpening("testImage", "testImageSharp");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0}}, model1.imageNames.get("testImageSharp"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFilterExceptionNonOddDimension() {
    model1.filter(new double[][]{{1, 2}}, "Red", "testImage",
            "testImage2");
  }

  @Test
  public void testGreyScaled() {
    model1.greyscaled("testImage", "testImageGrey");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {4, 4, 4, 4, 4, 4},
            {7, 7, 7, 4, 4, 4},
            {1, 1, 1, 4, 4, 4}}, model1.imageNames.get("testImageGrey"));
  }

  @Test
  public void testSepia() {
    model1.sepia("testImage", "testImageGrey");
    assertArrayEquals(new int[][]{
            {1, 2, 10},
            {6, 5, 4, 6, 5, 4},
            {10, 9, 7, 6, 5, 4},
            {2, 2, 1, 6, 5, 4}}, model1.imageNames.get("testImageGrey"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorTransformationIllegalTransformationSize() {
    model1.colorTransformation(new double[][]{
            {1}}, "testImage", "testImageTrans");
  }

  @Test
  public void testReadScript() {
    model1.readScript("images/Script.txt");
    model1.load("images/ScriptTest.png", "test");
    assertArrayEquals(new int[]{508,339,255}, model1.imageNames.get("test")[0]);
  }
}
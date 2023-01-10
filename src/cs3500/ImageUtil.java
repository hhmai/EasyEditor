package cs3500;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Reads a file (that is not PPM) and converts it to a 2D array of its RBG elements.
   * @param filename name of the file
   * @return 2D array of RGB
   */
  public static int[][] readFile(String filename) {
    BufferedImage image = null;
    int[][] imgArray;
    try {
      image = ImageIO.read(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    } catch (IOException e) {
      System.out.println("IO Exception");
    }
    if (image == null) {
      throw new IllegalArgumentException();
    } else {
      imgArray = new int[image.getHeight() + 1][];
      imgArray[0] = new int[]{image.getWidth(), image.getHeight(), 255};
      for (int i = 0; i < image.getHeight(); i++) {
        int[] row = new int[image.getWidth() * 3];
        for (int j = 0; j < image.getWidth(); j++) {
          int pixel = image.getRGB(j, i);
          Color pixelColor = new Color(pixel);
          int r = pixelColor.getRed();
          row[j * 3] = r;
          int g = pixelColor.getGreen();
          row[j * 3 + 1] = g;
          int b = pixelColor.getBlue();
          row[j * 3 + 2] = b;
        }
        imgArray[i + 1] = row;
      }
      return imgArray;
    }
  }

  /**
   * Converts a ppm file to an array of ints.
   * @param filename name of the file to load
   * @return 2D array of ints
   */
  public static int[][] ppmArray(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;
    int[] row1 = new int[3];
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    row1[0] = width;
    int height = sc.nextInt();
    row1[1] = height;
    int maxValue = sc.nextInt();
    row1[2] = maxValue;

    int[][] ppm = new int[height + 1][];
    ppm[0] = row1;

    for (int i = 0; i < height; i++) {
      int[] rowx = new int[width * 3];
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        rowx[j * 3] = r;
        int g = sc.nextInt();
        rowx[j * 3 + 1] = g;
        int b = sc.nextInt();
        rowx[j * 3 + 2] = b;
      }
      ppm[i + 1] = rowx;
    }
    return ppm;
  }

  /**
   * Main method for the util.
   * @param args arguments
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      //filename = "sample.ppm";
      filename = "images/cat.jpeg";
    }
    System.out.println(Arrays.deepToString(ImageUtil.readFile(filename)));
    //ImageUtil.readPPM(filename);
  }
}


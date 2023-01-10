package cs3500.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cs3500.ImageUtil;
import cs3500.controller.ImageProcessorController;
import cs3500.controller.ImageProcessorControllerImpl;
import cs3500.view.ImageProcessorTextView;

/**
 * Class representing an implementation of an image processor, but can only modify ppm image files.
 */
public class ImageProcessorModel1 implements ImageProcessorModel {
  protected HashMap<String, int[][]> imageNames;

  /**
   * Constructor for ImageProcessorModel and initializes the hashMap.
   */
  public ImageProcessorModel1() {
    this.imageNames = new HashMap<>();
  }

  private int[][] checkImage(String imageName) {
    if (this.imageNames.containsKey(imageName)) {
      return this.imageNames.get(imageName).clone();
    } else {
      throw new IllegalArgumentException("Cannot find " + imageName);
    }
  }

  @Override
  public void load(String fileDirectory, String fileName) {
    if (fileName == null || fileDirectory == null) {
      throw new IllegalArgumentException("null filename given");
    }
    if (fileDirectory.endsWith("ppm")) {
      this.imageNames.put(fileName, ImageUtil.ppmArray(fileDirectory));
    } else {
      this.imageNames.put(fileName, ImageUtil.readFile(fileDirectory));
    }
  }

  @Override
  public void flipHorizontal(String imageName, String imageDestName) {
    int[][] image = checkImage(imageName);
    int[][] flippedPPM = new int[image.length][image[1].length];
    flippedPPM[0] = image[0];
    for (int r = 1; r < image.length; r++) {
      for (int c = 0; c < image[1].length / 3; c++) {
        flippedPPM[r][c * 3] = image[r][image[r].length - (c * 3) - 3];
        flippedPPM[r][c * 3 + 1] = image[r][image[r].length - (c * 3) - 2];
        flippedPPM[r][c * 3 + 2] = image[r][image[r].length - (c * 3) - 1];
      }
    }
    this.imageNames.put(imageDestName, flippedPPM);
  }

  @Override
  public void flipVertical(String imageName, String imageDestName) {
    int[][] image = checkImage(imageName);
    int[][] flippedPPM = new int[image.length][];
    flippedPPM[0] = image[0];
    for (int r = 1; r < image.length; r++) {
      flippedPPM[r] = image[image.length - r];
    }
    this.imageNames.put(imageDestName, flippedPPM);
  }

  @Override
  public void greyScale(String component, String imageName, String imageDestName) {
    int[][] image = checkImage(imageName);
    switch (component) {
      case "red-component":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            int red;
            if (c % 3 == 0) {
              red = image[r][c];
              image[r][c + 1] = red;
              image[r][c + 2] = red;
            }
          }
        }
        break;
      case "green-component":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            int green;
            if (c % 3 == 1) {
              green = image[r][c];
              image[r][c - 1] = green;
              image[r][c + 1] = green;
            }
          }
        }
        break;
      case "blue-component":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            int blue;
            if (c % 3 == 2) {
              blue = image[r][c];
              image[r][c - 2] = blue;
              image[r][c - 1] = blue;
            }
          }
        }
        break;
      default:
        throw new IllegalArgumentException("Did not recognize command " + component);
    }
    this.imageNames.put(imageDestName, image);
  }

  @Override
  public void brighten(double brightness, String imageName, String imageDestName) {
    int[][] image = checkImage(imageName);
    int brightened;

    if (brightness > 0) {
      for (int r = 1; r < image.length; r++) {
        for (int c = 0; c < image[r].length; c++) {
          brightened = (int) (image[r][c] + brightness);
          image[r][c] = Math.min(brightened, image[0][2]);
        }
      }
    } else {
      for (int r = 1; r < image.length; r++) {
        for (int c = 0; c < image[r].length; c++) {
          brightened = (int) (image[r][c] + brightness);
          image[r][c] = Math.max(brightened, 0);
        }
      }
    }
    this.imageNames.put(imageDestName, image);
  }

  @Override
  public void saveWithFile(String path, String name, File f) {
    int[][] image = checkImage(name);
    if (path == null || name == null) {
      throw new IllegalArgumentException("Path or name cannot be null");
    }
    if (path.endsWith("ppm")) {
      try {
        FileWriter imageWriter = new FileWriter(f, false);
        imageWriter.write("P3 \n");
        imageWriter.write("# image created by ImageProcessorModel1\n");
        imageWriter.write(Integer.toString(image[0][0]) + " " + (image[0][1])
                + System.lineSeparator());
        imageWriter.write(Integer.toString(image[0][2]) + "\n");
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            imageWriter.write(image[r][c] + "\n");
          }
        }
        imageWriter.close();
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }
    } else {
      BufferedImage buffered = new BufferedImage(image[0][0], image[0][1],
              BufferedImage.TYPE_INT_RGB);
      for (int y = 1; y < image.length; y++) {
        for (int x = 0; x < image[y].length / 3; x++) {
          int rgb = new Color(image[y][x * 3], image[y][x * 3 + 1], image[y][x * 3 + 2]).getRGB();
          buffered.setRGB(x, y - 1, rgb);
        }
      }
      try {
        int index = path.indexOf(".");
        ImageIO.write(buffered, path.substring(index + 1), f);
      } catch (IOException e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  @Override
  public void save(String path, String name) {
    File imageFile = new File(path);
    this.saveWithFile(path, name, imageFile);
  }

  protected void filter(double[][] filterArray, String channel, String imageName,
                        String imageDestName) {
    int[][] image = checkImage(imageName);
    int[][] image2 = new int[image.length][];
    for (int i = 0; i < image.length; i++) {
      image2[i] = Arrays.copyOf(image[i], image[i].length);
    }
    if (filterArray.length % 2 == 0 || filterArray[0].length % 2 == 0) {
      throw new IllegalArgumentException("Cannot have even dimensions");
    }

    switch (channel) {
      case "red":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 0) {
              if (r - 1 < (filterArray.length - 1) / 2
                      || r > image.length - 1 - (filterArray.length - 1) / 2
                      || c < (filterArray[0].length - 1) / 2 * 3
                      || c > image[r].length - 1 - (filterArray[0].length - 1) / 2 * 3) {
                image2[r][c] = 0;
              } else {
                int sum = 0;
                for (int fr = 0; fr < filterArray.length; fr++) {
                  for (int fc = 0; fc < filterArray[fr].length; fc++) {
                    sum += filterArray[fr][fc] * image[r - ((filterArray.length - 1) / 2) + fr]
                            [c - ((filterArray[fr].length - 1) / 2 * 3) + (fc * 3)];
                  }
                }
                image2[r][c] = Math.max(Math.min(sum, 255), 0);
              }
            }
          }
        }
        break;
      case "green":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 1) {
              if (r - 1 < (filterArray.length - 1) / 2
                      || r > image.length - 1 - (filterArray.length - 1) / 2
                      // remove / 3 if does not work and work out index
                      || c < (filterArray[0].length - 1) / 2 * 3
                      || c > image[r].length - 1 - (filterArray[0].length - 1) / 2 * 3) {
                image2[r][c] = 0;
              } else {
                int sum = 0;
                for (int fr = 0; fr < filterArray.length; fr++) {
                  for (int fc = 0; fc < filterArray[fr].length; fc++) {
                    sum += filterArray[fr][fc] * image[r - ((filterArray.length - 1) / 2) + fr]
                            [c - ((filterArray[fr].length - 1) / 2 * 3) + (fc * 3)];
                  }
                }
                image2[r][c] = Math.max(Math.min(sum, 255), 0);
              }
            }
          }
        }
        break;
      case "blue":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 2) {
              if (r - 1 < (filterArray.length - 1) / 2
                      || r > image.length - 1 - (filterArray.length - 1) / 2
                      // remove / 3 if does not work and work out index
                      || c < (filterArray[0].length - 1) / 2 * 3
                      || c > image[r].length - 1 - (filterArray[0].length - 1) / 2 * 3) {
                image2[r][c] = 0;
              } else {
                int sum = 0;
                for (int fr = 0; fr < filterArray.length; fr++) {
                  for (int fc = 0; fc < filterArray[fr].length; fc++) {
                    sum += filterArray[fr][fc] * image[r - ((filterArray.length - 1) / 2) + fr]
                            [c - ((filterArray[fr].length - 1) / 2 * 3) + (fc * 3)];
                  }
                }
                image2[r][c] = Math.max(Math.min(sum, 255), 0);
              }
            }
          }
        }
        break;
      default:
        throw new IllegalArgumentException();
    }
    this.imageNames.put(imageDestName, image2);
  }

  @Override
  public void blur(String imageName, String imageDestName) {
    double[][] filter = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    this.filter(filter, "red", imageName, imageDestName);
    this.filter(filter, "green", imageDestName, imageDestName);
    this.filter(filter, "blue", imageDestName, imageDestName);
  }

  @Override
  public void sharpening(String imageName, String imageDestName) {
    double[][] filter = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    this.filter(filter, "red", imageName, imageDestName);
    this.filter(filter, "green", imageDestName, imageDestName);
    this.filter(filter, "blue", imageDestName, imageDestName);
  }

  protected void colorTransformation(double[][] trans, String imageName, String imageDestName) {
    int[][] image = checkImage(imageName);
    if (trans.length != 3 || trans[0].length != 3) {
      throw new IllegalArgumentException("Non 3");
    }
    for (int r = 1; r < image.length; r++) {
      for (int c = 0; c < image[r].length; c++) {
        if (c % 3 == 0) {
          int red = (int) (Math.min(trans[0][0] * image[r][c] + trans[0][1] * image[r][c + 1] +
                  trans[0][2] * image[r][c + 2], 255));
          int green = (int) (Math.min(trans[1][0] * image[r][c] + trans[1][1] * image[r][c + 1] +
                  trans[1][2] * image[r][c + 2], 255));
          int blue = (int) (Math.min(trans[2][0] * image[r][c] + trans[2][1] * image[r][c + 1] +
                  trans[2][2] * image[r][c + 2], 255));
          image[r][c] = red;
          image[r][c + 1] = green;
          image[r][c + 2] = blue;
        }
      }
    }
    this.imageNames.put(imageDestName, image);
  }

  @Override
  public void greyscaled(String imageName, String imageDestName) {
    colorTransformation(new double[][]{
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}}, imageName, imageDestName);
  }

  @Override
  public void sepia(String imageName, String imageDestName) {
    colorTransformation(new double[][]{
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}}, imageName, imageDestName);
  }

  @Override
  public void readScript(String scriptPath) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(scriptPath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + scriptPath + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    ImageProcessorController controller1;
    StringReader read = new StringReader(builder.toString());
    controller1 = new ImageProcessorControllerImpl(this, new ImageProcessorTextView(), read);
    controller1.startProcessor();
  }

  @Override
  public int[] makeHistogram(String type, String imageName) throws IllegalArgumentException {
    int[][] image = checkImage(imageName);
    int[] histogram = new int[256];

    switch (type) {
      case "r":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 0) {
              histogram[image[r][c]]++;
            }
          }
        }
        return histogram;
      case "g":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 1) {
              histogram[image[r][c]]++;
            }
          }
        }
        return histogram;
      case "b":
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 2) {
              histogram[image[r][c]]++;
            }
          }
        }
        return histogram;
      case "intensity":
        int intensity;
        for (int r = 1; r < image.length; r++) {
          for (int c = 0; c < image[r].length; c++) {
            if (c % 3 == 0) {
              intensity = (image[r][c] + image[r][c + 1] + image[r][c + 2]) / 3;
              histogram[intensity]++;
            }
          }
        }
        return histogram;
      default:
        throw new IllegalArgumentException("Invalid type");
    }
  }

  @Override
  public byte[] imageData(String name) {
    int[][] image = checkImage(name);
    byte[] result = new byte[image[0][0] * image[0][1]];
    int index = 0;
    for (int i = 1; i < image.length; i++) {
      for (int k = 0; k < image[i].length; k++) {
        result[index] = (byte) image[i][k];
        index++;
      }
    }
    return result;
  }
}
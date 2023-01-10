package cs3500.model;

import java.io.File;

/**
 * Represents a model for image processor and has the methods required to modify images.
 */
public interface ImageProcessorModel {

  /**
   * Flips an image horizontally.
   *
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void flipHorizontal(String imageName, String imageDestName);

  /**
   * Flips an image vertically.
   *
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void flipVertical(String imageName, String imageDestName);

  /**
   * Grey scales an image.
   *
   * @param rgb           color component to greyscale from
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void greyScale(String rgb, String imageName, String imageDestName);

  /**
   * Brighten the image by the given increment to create a new image.
   *
   * @param brightness    increment to brighten or dim the image by (can be positive or negative).
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void brighten(double brightness, String imageName, String imageDestName);

  /**
   * Saves the image to the given path and with the given name.
   *
   * @param path directory to be saved at
   * @param name name of the image file
   */
  void save(String path, String name);

  /**
   * Saves the image to the given path, name, and filetype.
   *
   * @param path directory to be saved at
   * @param name name of the image file
   * @param f filetype
   */
  void saveWithFile(String path, String name, File f);

  /**
   * Loads an image into the processor.
   *
   * @param fileName      name of the image you want to load
   * @param fileDirectory directory of the image
   */
  void load(String fileDirectory, String fileName);

  /**
   * Blurs an image.
   *
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void blur(String imageName, String imageDestName);

  /**
   * Sharpens an image.
   *
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void sharpening(String imageName, String imageDestName);

  /**
   * Converts a color image into a greyscale image.
   *
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void greyscaled(String imageName, String imageDestName);

  /**
   * Converts a color image into a sepia image.
   *
   * @param imageName     name of the image you want to load
   * @param imageDestName directory of the image
   */
  void sepia(String imageName, String imageDestName);

  /**
   * Reads the script and executes the methods.
   * @param scriptPath path of folder.
   */
  void readScript(String scriptPath);

  /**
   * Makes a histogram of the values in an image based on the given type.
   * @param type can be red, blue, green, or intensity
   * @param imageName name of the image you want to create a histogram of
   * @return an array representing the specified histogram
   */
  int[] makeHistogram(String type, String imageName);

  /**
   * Converts image to data.
   * @param name of file
   * @return byte array
   */
  public byte[] imageData(String name);

}
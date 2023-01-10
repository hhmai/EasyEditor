package cs3500.model;

import java.io.File;

/**
 * Mock class used to test controller implementation.
 */
public class MockImageProcessorModel implements ImageProcessorModel {
  protected StringBuilder log;

  /**
   * Constructor for mock model.
   *
   * @param log string builder given to check input
   */
  public MockImageProcessorModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void flipHorizontal(String imageName, String imageDestName) {
    log.append("tried flipHorizontal ").append(imageName).append(" ").append(imageDestName);
  }

  @Override
  public void flipVertical(String imageName, String imageDestName) {
    log.append("tried flipVertical ").append(imageName).append(" ").append(imageDestName);
  }

  @Override
  public void greyScale(String rgb, String imageName, String imageDestName) {
    log.append("tried greyScale ").append(rgb).append(" ").append(imageName).append(" ")
            .append(imageDestName);
  }

  @Override
  public void brighten(double brightness, String imageName, String imageDestName) {
    log.append("tried brighten ").append(brightness).append(" ").append(imageName).append(" ")
            .append(imageDestName);
  }

  @Override
  public void blur(String imageName, String imageDestName) {
    log.append("tried blur ").append(imageName).append(" ").append(imageDestName);
  }

  @Override
  public void sharpening(String imageName, String imageDestName) {
    log.append("tried sharpening ").append(imageName).append(" ").append(imageDestName);
  }

  @Override
  public void greyscaled(String imageName, String imageDestName) {
    log.append("tried greyscaled ").append(imageName).append(" ").append(imageDestName);
  }

  @Override
  public void sepia(String imageName, String imageDestName) {
    log.append("tried sepia ").append(imageName).append(" ").append(imageDestName);
  }

  @Override
  public void readScript(String scriptPath) {
    //empty to stay in interface, this never goes through the controller.
  }

  @Override
  public int[] makeHistogram(String type, String imageName) {
    return new int[0];
  }

  @Override
  public byte[] imageData(String name) {
    return new byte[0];
  }

  @Override
  public void save(String path, String name) {
    log.append("tried save ").append(path).append(" ").append(name);
  }

  @Override
  public void saveWithFile(String path, String name, File f) {
    log.append("tried save with file ").append(path).append(" ").append(name);
  }

  @Override
  public void load(String fileDirectory, String fileName) {
    log.append("tried load ").append(fileDirectory).append(" ").append(fileName);
  }
}
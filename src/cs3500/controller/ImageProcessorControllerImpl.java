package cs3500.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.model.ImageProcessorModel;
import cs3500.view.ImageProcessorView;

/**
 * Represents a controller for the user to input commands to run the image processor.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {
  private final ImageProcessorModel model;
  private final ImageProcessorView view;
  protected Readable read;

  /**
   * Constructor which takes in a readable object.
   *
   * @param model imageprocessor model handling the processing
   * @param view  imageprocessor view which handles output
   * @param read  where input comes from
   */
  public ImageProcessorControllerImpl(ImageProcessorModel model, ImageProcessorView view,
                                      Readable read) {
    this.model = model;
    this.view = view;
    this.read = read;
  }

  @Override
  public void startProcessor() {
    Scanner scan = new Scanner(read);
    boolean quit = false;
    this.printCommands();

    while (!quit) {
      this.message("Input Command: ");
      if (!scan.hasNext()) {
        throw new IllegalStateException("No next in readable");
      }
      String command = scan.next();
      switch (command) {
        case "load":
          try {
            model.load(scan.next(), scan.next());
            message("Successfully loaded image" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "save":
          try {
            model.save(scan.next(), scan.next());
            message("Successfully saved image as ppm" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "red-component":
          try {
            model.greyScale("red-component", scan.next(), scan.next());
            message("Successfully greyscaled the image's red component" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "blue-component":
          try {
            model.greyScale("blue-component", scan.next(), scan.next());
            message("Successfully greyscaled the image's blue component" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "green-component":
          try {
            model.greyScale("green-component", scan.next(), scan.next());
            message("Successfully greyscaled the image's green component" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "flipHorizontal":
          try {
            model.flipHorizontal(scan.next(), scan.next());
            message("Successfully flipped the image horizontally" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "flipVertical":
          try {
            model.flipVertical(scan.next(), scan.next());
            message("Successfully flipped the image vertically" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "brighten":
          try {
            model.brighten(scan.nextInt(), scan.next(), scan.next());
            message("Successfully brightened the image" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "blur":
          try {
            model.blur(scan.next(), scan.next());
            message("Successfully blurred the image" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "sharpen":
          try {
            model.sharpening(scan.next(), scan.next());
            message("Successfully sharpened the image" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "greyscale":
          try {
            model.greyscaled(scan.next(), scan.next());
            message("Successfully converted the image to greyscale" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "sepia":
          try {
            model.sepia(scan.next(), scan.next());
            message("Successfully converted the image to sepia" + System.lineSeparator());
          } catch (IllegalArgumentException e) {
            message("Error" + e.getMessage() + System.lineSeparator());
          }
          break;
        case "q":
          message("Quitting processor..." + System.lineSeparator());
          quit = true;
          break;
        default:
          message("Unrecognized command, please try again \n");
      }
    }
    message("Thanks for running the Image Processor.");
  }

  private void printCommands() {
    try {
      this.view.renderMessage("Available commands:" + "\n");
      this.view.renderMessage("load file-Directory file-Name" + "\n");
      this.view.renderMessage("save file-Directory file-Name" + "\n");
      this.view.renderMessage("red-component image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("green-component image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("blue-component image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("flipHorizontal image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("flipVertical image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("brighten increment image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("blur image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("sharpen image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("greyscale image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("sepia image-Name image-Dest-Name" + "\n");
      this.view.renderMessage("q: To quit the program." + "\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private void message(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
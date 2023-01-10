package cs3500;

import java.io.InputStreamReader;

import cs3500.controller.ImageProcessorController;
import cs3500.controller.ImageProcessorControllerImpl;
import cs3500.model.ImageProcessorModel1;
import cs3500.view.ImageProcessorTextView;

/**
 * Runs the image processor and contains the main() method.
 */
public class ImageProcessorApp {

  /**
   * Main method.
   * @param args arguments
   */
  public static void main(String[] args) {
    ImageProcessorModel1 model2 = new ImageProcessorModel1();
    ImageProcessorController control = new ImageProcessorControllerImpl(model2,
            new ImageProcessorTextView(), new InputStreamReader(System.in));
    control.startProcessor();
  }
}

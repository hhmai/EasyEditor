Overview of ImageProcessor
Class Diagram: https://imgur.com/a/pNIJQOf

Interfaces: 

ImageProcessorController - Interface for the controller in an image processor, allows for user input (Controller)

ImageProcessorModel - Represents a model for image processor and has the methods required to modify images. (Model)

ImageProcessorView - Handles the view and outputs messages and commands for the user. (View)


Classes:
All classes are complete and fully functional for their intended purpose.

ImageProcessorControllerImpl - Represents a controller for the user to imput commands to run the image processor, implements ImageProcessorController.
					 Allows for the image processor to run through text based commands in the console.

ImageProcessorModel1 - Class representing an implementation of an image processor, can only modify ppm image files, implements ImageProcessorModel.
                       Allows the iamge processor to process images through blurs, color transformations, etc. Houses the image in a hashmap and allows
                       for saving and loading an image.
			     Changes made: added a save with file method so we could delete the
			     file after saving if we wanted to (used for refreshing image)

ImageProcessorTextView - Text view for image processor for user commands not image rendering, implements ImageProcessorView.
                         Outputs commands and textlines through the console for the user to see.

ImageUtil - Class containing utility methods to read a PPM image from file and simply print its contents.

ImageProcessorApp - Class used to run the program using user inputs.

ImageProcessorGui - Class with main(String[] args) method to run the GUI

ImageProcessorGuiFrame - Class that handles all the features to output a GUI, has action
				 listeners and handles view output


List of supported commands: 

load image-path image-name: Load an image from the specified path and refer it to henceforth in the program by the given image name.

save image-path image-name: Save the image with the given name to the specified path which should include the name of the file.

red-component image-name dest-image-name: Create a greyscale image with the red-component of the image with the given name, and refer to it henceforth 
                                          in the program by the given destination name. 

green-component image-name dest-image-name: Create a green image with the red-component of the image with the given name, and refer to it henceforth 
                                          in the program by the given destination name. 

blue-component image-name dest-image-name: Create a blue image with the red-component of the image with the given name, and refer to it henceforth 
                                          in the program by the given destination name. 

flipHorizontal image-name dest-image-name: Flip an image horizontally to create a new image, referred to henceforth by the given destination name.

flipVertical image-name dest-image-name: Flip an image vertically to create a new image, referred to henceforth by the given destination name.

brighten increment image-name dest-image-name: Brighten the image by the given increment to create a new image, referred to henceforth by the given destination 
                                               name. The increment may be positive (brightening) or negative (darkening)
blur image-name dest-image-name: Blurs the image with the gaussian kernel

sharpen image-name dest-image-name: Sharpens the image with the kernel given by the assignment to make the image have sharper edges

greyscale image-name dest-image-name: greyscales the image through a color transformation, different from channel greyscaling

sepia image-name dest-image-name: gives the image a sepia tone and makes the picture look like an old sepia photo

q: Quits the program.

Citation:
The image 'rainbow' is our own work and we permit the use of this image in this program and assignment.

Eriklam. “European Short Haired Cat in Front of a White Background.” IStock, https://www.istockphoto.com/photo/european-short-haired-cat-gm1072769156-287096258.

“Dog PNG Images.” Free Icons PNG, https://www.freeiconspng.com/images/dog-png.  
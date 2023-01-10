package cs3500.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import cs3500.model.ImageProcessorModel;
import cs3500.model.ImageProcessorModel1;

/**
 * Handles the gui processing for ImageProcessorGUI.
 */
public class ImageProcessorGUIFrame extends JFrame implements ActionListener,
        ItemListener, ListSelectionListener {

  ImageProcessorModel model;
  private JPanel histogram;

  private JPanel imagePanel;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;
  private JTextField brightenInput;

  String imageName;
  private String imageType;

  /**
   * Constructor for GUI Frame, does not take in another besides file chooses after launch.
   */
  public ImageProcessorGUIFrame() {
    super();
    setTitle("Image Processor GUI");
    setSize(1600, 800);
    model = new ImageProcessorModel1();
    FlowLayout layout = new FlowLayout();

    JPanel mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //dialog boxes
    JPanel dialogBoxesPanel = new JPanel();
    //dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Dialog boxes"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    mainPanel.add(dialogBoxesPanel);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileopenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(this);
    fileopenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileopenPanel.add(fileOpenDisplay);

    JPanel filters = new JPanel();
    filters.setLayout(layout);

    //blur
    dialogBoxesPanel.add(filters);
    JButton blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);
    filters.add(blurButton);
    JLabel blurDisplay = new JLabel("");
    filters.add(blurDisplay);

    //sharpen
    dialogBoxesPanel.add(filters);
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(this);
    filters.add(sharpenButton);
    JLabel sharpenDisplay = new JLabel("");
    filters.add(sharpenDisplay);

    //sepia
    dialogBoxesPanel.add(filters);
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);
    filters.add(sepiaButton);
    JLabel sepiaDisplay = new JLabel("");
    filters.add(sepiaDisplay);

    //greyscale
    dialogBoxesPanel.add(filters);
    JButton greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale");
    greyscaleButton.addActionListener(this);
    filters.add(greyscaleButton);
    JLabel greyscaleDisplay = new JLabel("");
    filters.add(greyscaleDisplay);

    //redComponent
    dialogBoxesPanel.add(filters);
    JButton redComponentButton = new JButton("Red Component");
    redComponentButton.setActionCommand("Red Component");
    redComponentButton.addActionListener(this);
    filters.add(redComponentButton);
    JLabel redComponentDisplay = new JLabel("");
    filters.add(redComponentDisplay);

    //blueComponent
    dialogBoxesPanel.add(filters);
    JButton blueComponentButton = new JButton("Blue Component");
    blueComponentButton.setActionCommand("Blue Component");
    blueComponentButton.addActionListener(this);
    filters.add(blueComponentButton);
    JLabel blueComponentDisplay = new JLabel("");
    filters.add(blueComponentDisplay);

    //greenComponent
    dialogBoxesPanel.add(filters);
    JButton greenComponentButton = new JButton("Green Component");
    greenComponentButton.setActionCommand("Green Component");
    greenComponentButton.addActionListener(this);
    filters.add(greenComponentButton);
    JLabel greenComponentDisplay = new JLabel("");
    filters.add(greenComponentDisplay);

    //flipVertical
    dialogBoxesPanel.add(filters);
    JButton flipVerticalButton = new JButton("Flip Vertical");
    flipVerticalButton.setActionCommand("Flip Vertical");
    flipVerticalButton.addActionListener(this);
    filters.add(flipVerticalButton);
    JLabel flipVerticalDisplay = new JLabel("");
    filters.add(flipVerticalDisplay);

    //flipHorizontal
    dialogBoxesPanel.add(filters);
    JButton flipHorizontalButton = new JButton("Flip Horizontal");
    flipHorizontalButton.setActionCommand("Flip Horizontal");
    flipHorizontalButton.addActionListener(this);
    filters.add(flipHorizontalButton);
    JLabel flipHorizontalDisplay = new JLabel("");
    filters.add(flipHorizontalDisplay);

    //brighten
    brightenInput = new JTextField("Input brightness modifier");
    dialogBoxesPanel.add(filters);
    JButton brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    brightenButton.addActionListener(this);
    filters.add(brightenInput);
    filters.add(brightenButton);
    JLabel brightenDisplay = new JLabel("");
    filters.add(brightenDisplay);


    //show an image with a scrollbar
    imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);

    String image = fileOpenDisplay.getText();
    JLabel[] imageLabel = new JLabel[1];
    JScrollPane[] imageScrollPane = new JScrollPane[1];


    imageLabel[0] = new JLabel();
    imageScrollPane[0] = new JScrollPane(imageLabel[0]);
    imageLabel[0].setIcon(new ImageIcon(image));
    imageScrollPane[0].setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane[0]);


    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(filesavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(this);
    filesavePanel.add(fileSaveButton);
    fileSaveDisplay = new JLabel("File path will appear here");
    filesavePanel.add(fileSaveDisplay);
  }


  @Override
  public void actionPerformed(ActionEvent arg) {
    switch (arg.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Images", "jpg", "gif", "png", "ppm");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(ImageProcessorGUIFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileOpenDisplay.setText(f.getAbsolutePath());
          String image = fileOpenDisplay.getText();
          this.imageName = image.substring(image.lastIndexOf("\\") + 1, image.length() - 4);
          this.imageType = image.substring(image.length() - 3);
          File file = new File(imageName + ".jpg");
          model.load(image, imageName);
          model.saveWithFile(imageName + ".jpg", imageName, file);
          JLabel imageLabel = new JLabel();
          JScrollPane imageScrollPane = new JScrollPane(imageLabel);
          imageScrollPane.setPreferredSize(new Dimension(100, 600));
          imageLabel.setIcon(new ImageIcon(file.getAbsolutePath()));
          imagePanel.remove(0);
          imagePanel.add(imageScrollPane);
          imageLabel.revalidate();
          file.delete();
          if (histogram != null) {
            imagePanel.remove(0);
          }
          histogram = new Histogram(this.imageName, this.model);
          JScrollPane histoScrollPane = new JScrollPane(histogram);
          imagePanel.add(histoScrollPane);
        }
      }
      break;
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog(ImageProcessorGUIFrame.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          fileSaveDisplay.setText(f.getAbsolutePath());
          model.save(f.getAbsolutePath(), imageName);
        }
      }
      break;
      case "Blur": {
        model.blur(imageName, imageName + "Blur");
        imageName = imageName + "Blur";
        imageUpdate();
      }
      break;
      case "Sharpen": {
        model.sharpening(imageName, imageName + "Sharpen");
        imageName = imageName + "Sharpen";
        imageUpdate();
      }
      break;
      case "Sepia": {
        model.sepia(imageName, imageName + "Sepia");
        imageName = imageName + "Sepia";
        imageUpdate();
      }
      break;
      case "Red Component": {
        model.greyScale("red-component", imageName, imageName + "RedComponent");
        imageName = imageName + "RedComponent";
        imageUpdate();
      }
      break;
      case "Green Component": {
        model.greyScale("green-component", imageName, imageName + "GreenComponent");
        imageName = imageName + "GreenComponent";
        imageUpdate();
      }
      break;
      case "Blue Component": {
        model.greyScale("blue-component", imageName, imageName + "BlueComponent");
        imageName = imageName + "BlueComponent";
        imageUpdate();
      }
      break;
      case "Flip Horizontal": {
        model.flipHorizontal(imageName, imageName + "Horizontal");
        imageName = imageName + "Horizontal";
        imageUpdate();
      }
      break;
      case "Flip Vertical": {
        model.flipHorizontal(imageName, imageName + "Vertical");
        imageName = imageName + "Vertical";
        imageUpdate();
      }
      break;
      case "Greyscale": {
        model.greyscaled(imageName, imageName + "Greyscale");
        imageName = imageName + "Greyscale";
        imageUpdate();
      }
      break;
      case "Brighten": {
        int brightenAmt;
        try {
          brightenAmt = Integer.parseInt(brightenInput.getText());
        } catch (NumberFormatException e) {
          brightenInput.setText("Try again. Input integer:");
          break;
        }
        model.brighten(brightenAmt, imageName, imageName + "Brighten");
        imageName = imageName + "Brighten";
        imageUpdate();
        brightenInput.setText("Input brightness modifier");
      }
      break;
      default: {
        //do nothing, nothing to do here
      }
    }
  }

  //refreshes the image in the gui to reflect filters, etc
  private void imageUpdate() {
    File f = new File(imageName + "." + imageType);
    model.saveWithFile(imageName + "." + imageType, imageName, f);
    JLabel imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imageLabel.setIcon(new ImageIcon(f.getAbsolutePath()));
    imagePanel.remove(0);
    imagePanel.remove(0);
    imagePanel.add(imageScrollPane);
    histogram = new Histogram(this.imageName, this.model);
    JScrollPane histoScrollPane = new JScrollPane(histogram);
    imagePanel.add(histoScrollPane);
    imagePanel.revalidate();
    f.delete();
  }

  @Override
  public void itemStateChanged(ItemEvent arg) {
    String who = ((JCheckBox) arg.getItemSelectable()).getActionCommand();
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    JOptionPane.showMessageDialog(ImageProcessorGUIFrame.this,
            "The source object is " + e.getSource(), "Source", JOptionPane.PLAIN_MESSAGE);
    JOptionPane.showMessageDialog(ImageProcessorGUIFrame.this,
            "The changing index is " + e.getFirstIndex(), "Index", JOptionPane.PLAIN_MESSAGE);
  }

  private static class Histogram extends JPanel {
    String imageName;
    ImageProcessorModel model;

    public Histogram(String imageName, ImageProcessorModel model) {
      this.imageName = imageName;
      this.model = model;
    }

    @Override
    protected void paintComponent(Graphics g) {
      int[] red = model.makeHistogram("r", this.imageName);
      int[] green = model.makeHistogram("g", this.imageName);
      int[] blue = model.makeHistogram("b", this.imageName);
      int[] intensity = model.makeHistogram("intensity", this.imageName);

      int width = model.makeHistogram("r", this.imageName).length;
      int height = 600;
      int interval = 8;
      int individualWidth = 4;
      Graphics2D g2d = (Graphics2D) g;
      g2d.rotate(-Math.PI, width, height);
      super.paintComponents(g);
      //starting position
      int x = 30;
      for (int i = 0; i < width; i++) {
        g.setColor(new Color(127,127,127,140));
        g.fillRect(x, height, individualWidth, intensity[i]);
        g.setColor(new Color(0,0,255,60));
        g.fillRect(x, height, individualWidth, blue[i]);
        g.setColor(new Color(0,255,0,60));
        g.fillRect(x, height, individualWidth, green[i]);
        g.setColor(new Color(255,0,0,60));
        g.fillRect(x, height, individualWidth, red[i]);
        x += interval;
      }
    }
  }
}

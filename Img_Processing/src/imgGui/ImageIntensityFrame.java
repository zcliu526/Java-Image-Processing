package imgGui;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;

import image_processing.ImageArithmetic;
import image_processing.ImageIntesity;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Label;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ImageIntensityFrame {

	private JFrame frame;
	private static String filePath;
	private static JLabel imgLabel, label;
	private static JComboBox comboBox;
	private static ImageIntesity intensity;
	private static JTextField textField;
	private static JTextField textField_1;
	// initialising frame 
	public ImageIntensityFrame(){
		createWindow();
	}
	public static void main(String[] args) {
		createWindow();
	}

	private static void createWindow() {
		
		//initalising frame preferences
		JFrame frmImageIntensityChanges = new JFrame("Image manipulation");
		frmImageIntensityChanges.setTitle("Image Intensity Changes");
		frmImageIntensityChanges.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frmImageIntensityChanges);
		frmImageIntensityChanges.setSize(560, 470);
		frmImageIntensityChanges.setResizable(false);
		frmImageIntensityChanges.setLocationRelativeTo(null);
		frmImageIntensityChanges.setVisible(true);
	}
	// create UI elements
	private static void createUI(final JFrame frame) {
		JPanel fileChooserPanel = new JPanel();
		fileChooserPanel.setBackground(new Color(70, 130, 180));
		
//		initialise a button
		JButton btnSelectAImage = new JButton("Select a Image!");
		btnSelectAImage.setBounds(185, 11, 166, 23);
		
		imgLabel = new JLabel();
		label = new JLabel();
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBounds(53, 210, 220, 220);
		
		
		imgLabel.setVerticalAlignment(SwingConstants.TOP);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setBounds(290, 210, 220, 220);
//	    button click handler
		btnSelectAImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// swing fileChooser 
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new ImageFilter());
				fileChooser.setAcceptAllFileFilterUsed(false);
				int option = fileChooser.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
//					get the file path
					filePath = file.getAbsolutePath();
//					create the image icon
					ImageIcon img = new ImageIcon(filePath);
					img =  utils.scaleImage(img, 200 ,200);
					label.setIcon(img);
//					Create an image intensity object
					intensity = new ImageIntesity(filePath);
					comboBox.setEnabled(true);

				}
			}
		});
		frame.getContentPane().add(fileChooserPanel);
		fileChooserPanel.setLayout(null);
		fileChooserPanel.add(btnSelectAImage);
		
		
		fileChooserPanel.add(label);
		fileChooserPanel.add(imgLabel);
		
		JLabel lblBefore = new JLabel("Before");
		lblBefore.setBounds(53, 185, 46, 14);
		fileChooserPanel.add(lblBefore);
		
		JLabel lblAfter = new JLabel("After");
		lblAfter.setBounds(290, 185, 46, 14);
		fileChooserPanel.add(lblAfter);
		
		// Combo Box or drop down
		String[] options = new String[]{"Select" , "logarithmc_transformation","negative","power_law"};
		comboBox = new JComboBox( options);
		comboBox.setBounds(185, 45, 166, 20);
		
		comboBox.setEnabled(false);
		textField = new JTextField();
		textField.setText("0");
		textField.setBounds(255, 76, 96, 20);
		fileChooserPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setBounds(255, 107, 96, 20);
		fileChooserPanel.add(textField_1);
		textField_1.setColumns(10);
		
		
//		combo box change handler
		comboBox.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						intensity.doProcessing(comboBox.getSelectedItem().toString() ,Integer.parseInt(textField.getText())  , Float.parseFloat(textField_1.getText()) );
						imgLabel.setIcon(utils.scaleImage(new ImageIcon(filePath+"-manipulated.png"), 200, 200));
					}
				}
				
				);
				
						
		fileChooserPanel.add(comboBox);
		
		
		JLabel lblCValue = new JLabel("C value");
		lblCValue.setBounds(185, 79, 46, 14);
		fileChooserPanel.add(lblCValue);
		
		JLabel lblGamma = new JLabel("Gamma");
		lblGamma.setBounds(185, 110, 46, 14);
		fileChooserPanel.add(lblGamma);
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new MainFrame();
			}
		});
		btnGoBack.setForeground(new Color(0, 0, 0));
		btnGoBack.setBackground(new Color(255, 0, 0));
		btnGoBack.setBounds(455, 0, 89, 23);
		fileChooserPanel.add(btnGoBack);
	}
}

package imgGui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import image_processing.ImageArithmetic;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Font;

public class ImgArithmeticFrame {

	private static JFrame frmImageManipulation;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImgArithmeticFrame window = new ImgArithmeticFrame();
					window.frmImageManipulation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImgArithmeticFrame() {
		initialize();
		frmImageManipulation.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	// all the components
	
	// holding the location of image1
	static String img;
	// holding the location of image2
	static String img2;
	
	//Image arithmetic object which does all the image arithmetic operation
	private static ImageArithmetic arithmetic;
	
	// the drop down or combobox
	private static JComboBox comboBox;
	
	// Labels
	private static JLabel lblNewLabel;
	private static JLabel lblNewLabel_1;
	private static JLabel lblNewLabel_2;
	private static JLabel lblOperationWillBe;
	
	// initialize the content of the frame
	private static void initialize() {
		
		
		// initialize the new frame
		frmImageManipulation = new JFrame();
		frmImageManipulation.setResizable(false);
		frmImageManipulation.setTitle("Image manipulation");
		
		// set background colors
		frmImageManipulation.getContentPane().setBackground(new Color(70, 130, 180));
		frmImageManipulation.setBounds(100, 100, 725, 484);
		frmImageManipulation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImageManipulation.getContentPane().setLayout(null);
		
		
		// select image button 
		JButton btn = new JButton("select image");
		btn.setForeground(Color.DARK_GRAY);
		btn.setBackground(Color.WHITE);
		btn.addActionListener(new ActionListener() {
			// open a file chooser on click
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new ImageFilter());
				fileChooser.setAcceptAllFileFilterUsed(false);
				int option = fileChooser.showOpenDialog(frmImageManipulation);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					//get the first image location
					if(null==img)
						img = file.getAbsolutePath();
					else if(null == img2){
						// get the second image location
						img2 = file.getAbsolutePath();
					}
//					ImageIcon img = new ImageIcon(img1);
					
					if(null==img2 && null!=img){
						// change the text of the button if first image is selected
						btn.setText("Choose second image");
						// scale and display image
						ImageIcon image1 =  utils.scaleImage(new ImageIcon(img), 150, 150);
						lblNewLabel_1.setIcon(image1);
					}
					else{
						// change the text of the button if first image is selected
						ImageIcon image2 = utils.scaleImage(new ImageIcon(img2), 150, 150);
						lblNewLabel_2.setIcon(image2);
						arithmetic = new ImageArithmetic(img , img2);
						comboBox.setEnabled(true);
//					
					}
				}
				}
			
		});
		btn.setBounds(276, 28, 199, 30);
		
		frmImageManipulation.getContentPane().add(btn);
		
		String options[] = {"addition" , "subtraction","multiplication" , "divison"};
		 comboBox = new JComboBox(options);
		
		
		comboBox.setEnabled(null!=img && null!=img2 );
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = comboBox.getSelectedItem().toString();
				lblOperationWillBe.setText(select);
				arithmetic.doProcessing(select);
				ImageIcon img = utils.scaleImage(new ImageIcon("output.png"), 313, 229);
				lblNewLabel.setIcon(img);
			}
		});
		
		comboBox.setBounds(276, 69, 199, 20);
	
		frmImageManipulation.getContentPane().add(comboBox);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(398, 88, 300, 300);
		frmImageManipulation.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(80, 88, 150, 150);
		frmImageManipulation.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(80, 249, 150, 150);
		frmImageManipulation.getContentPane().add(lblNewLabel_2);
		
		lblOperationWillBe = new JLabel("Operation will be shown here..");
		lblOperationWillBe.setFont(new Font("Leelawadee UI", Font.BOLD, 15)); 
		lblOperationWillBe.setBackground(SystemColor.activeCaption);
		lblOperationWillBe.setForeground(Color.WHITE);
		lblOperationWillBe.setBounds(434, 399, 228, 20);
		frmImageManipulation.getContentPane().add(lblOperationWillBe);
		
		// Go back button handler
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmImageManipulation.dispose();
				MainFrame m = new MainFrame();
			
			}
		});
		btnGoBack.setForeground(new Color(0, 0, 205));
		btnGoBack.setBackground(new Color(220, 20, 60));
		btnGoBack.setBounds(620, 11, 89, 23);
		frmImageManipulation.getContentPane().add(btnGoBack);	


	}
}

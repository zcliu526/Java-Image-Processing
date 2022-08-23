package imgGui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import image_processing.ImageArithmetic;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.getContentPane().setBackground(new Color(70, 130, 180));
		frame.setBackground(new Color(70, 130, 180));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//Arithmetic window button handler
		JButton btnNewButton = new JButton("Arithmetic Manipulation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //on click dispose the current frame and launch a new image arithmetic frame
				 frame.dispose();
				ImgArithmeticFrame c = new ImgArithmeticFrame();
			}
		});
		btnNewButton.setBounds(131, 45, 192, 36);
		frame.getContentPane().add(btnNewButton);

		//Intensity window button handler
		JButton btnNewButton_1 = new JButton("Intensity Manipulation");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//on click dispose the current frame and launch a new image intensity frame
				frame.dispose();
				ImageIntensityFrame newF = new ImageIntensityFrame();

			}
		});
		// setting button location and add it to frame
		btnNewButton_1.setBounds(131, 105, 192, 36);
		frame.getContentPane().add(btnNewButton_1);
		
	}

}

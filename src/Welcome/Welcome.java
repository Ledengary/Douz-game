package Welcome;
import Main.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Welcome {

	private JFrame frame;

	/**f
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1008, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JButton start = new JButton("START");
		start.setFocusable(false);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Toolkit.getDefaultToolkit().beep();
				LogIn logIn = new LogIn();
				frame.dispose();
				logIn.setVisible(true);
			}
		});
		start.setFont(new Font("Tahoma", Font.PLAIN, 19));
		start.setBounds(438, 519, 116, 43);
		start.setForeground(new Color(35, 34, 23));
		start.setBackground(new Color(202, 192, 106));
		start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		frame.getContentPane().add(start);
		
		JLabel mainTheme = new JLabel("");
		mainTheme.setBounds(0, 0, 1010, 580);
		frame.getContentPane().add(mainTheme);
		Image imgMainTheme = new ImageIcon(this.getClass().getResource("/Welcome.jpg")).getImage();
		mainTheme.setIcon(new ImageIcon(imgMainTheme));
		playSound("Welcome.wav");
	}

	public static void playSound(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
		}
	}
}

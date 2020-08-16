package Main;
import Welcome.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FileChooserUI;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Classes.*;
public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtN;
	private JTextField txtM;
	private JTextField txtP;
	private JTextField txtC;
	public static ArrayList<Players> playersList = new ArrayList<Players>();
	public static String profilePictureAddress = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblClose = new JLabel("");
		lblClose.setBounds(854, 10, 32, 32);
		contentPane.add(lblClose);
		setLocationRelativeTo(null);
		setUndecorated(true);
		Image imgClose = new ImageIcon(this.getClass().getResource("/Close.png")).getImage();
		lblClose.setIcon(new ImageIcon(imgClose));
		lblClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		Image imglblMainTheme = new ImageIcon(this.getClass().getResource("/LogInFinal.jpg")).getImage();

		JLabel contactImage = new JLabel("");
		contactImage.setBounds(583, 99, 256, 264);
		if (!profilePictureAddress.equals("")) {
			ImageIcon imgProfilePicture = new ImageIcon(new ImageIcon(profilePictureAddress).getImage()
					.getScaledInstance(contactImage.getWidth(), contactImage.getHeight(), Image.SCALE_DEFAULT));
			contactImage.setIcon(imgProfilePicture);
		} else {
			Image imgProfilePicture = new ImageIcon(this.getClass().getResource("/Contact.png")).getImage()
					.getScaledInstance(contactImage.getWidth(), contactImage.getHeight(), Image.SCALE_DEFAULT);
			contactImage.setIcon(new ImageIcon(imgProfilePicture));
		}
		contentPane.add(contactImage);
		JButton btnAttach = new JButton("Attach Image");
		btnAttach.setForeground(new Color(35, 34, 23));
		btnAttach.setBackground(new Color(202, 192, 106));
		btnAttach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * inja aval JFileChooser o baz mikonim va dialog oon dar ayande mohtavaye fle
				 * ro migire va to khodesh addressesh ro negah midare, man oon address ro ke
				 * motmaen misham male ye akse ro negah midaram va be onvan profile picture
				 * taraf save mikonam
				 */
				try {
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filename = f.getAbsolutePath();
					LogIn.profilePictureAddress = filename;
					String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
					if (extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg")
							|| extension.equals("JPG") || extension.equals("JPEG") || extension.equals("gif")
							|| extension.equals("PNG")) {
						ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(
								contactImage.getWidth(), contactImage.getHeight(), Image.SCALE_DEFAULT));
						contactImage.setIcon(imageIcon);
					} else {
						JOptionPane.showMessageDialog(null, "Please choose an Image !");
					}
				} catch (Exception e2) {

				}
			}

		});
		btnAttach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAttach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAttach.setBounds(626, 372, 165, 39);
		contentPane.add(btnAttach);

		JLabel lblFirstName = new JLabel("Name :");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblFirstName.setForeground(new Color(202, 192, 106));
		lblFirstName.setBounds(37, 123, 89, 39);
		contentPane.add(lblFirstName);

		txtFirstName = new JTextField((!Main.playerName.equals("")) ? Main.playerName : "");
		txtFirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtFirstName.setBounds(113, 127, 398, 32);
		contentPane.add(txtFirstName);
		txtFirstName.setBackground(new Color(35, 34, 23));
		txtFirstName.setForeground(Color.WHITE);
		txtFirstName.setBorder(BorderFactory.createLineBorder(new Color(202, 192, 106)));
		txtFirstName.setColumns(10);

		JLabel lblN = new JLabel("N ");
		lblN.setForeground(new Color(202, 192, 106));
		lblN.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblN.setBounds(75, 234, 43, 39);
		contentPane.add(lblN);

		JLabel lblSplitter = new JLabel("____________________________________________");
		lblSplitter.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSplitter.setBounds(2, 172, 541, 32);
		contentPane.add(lblSplitter);
		lblSplitter.setForeground(new Color(202, 192, 106));

		JLabel lblM = new JLabel("M");
		lblM.setForeground(new Color(202, 192, 106));
		lblM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblM.setBounds(205, 234, 43, 39);
		contentPane.add(lblM);

		JLabel lblP = new JLabel("P");
		lblP.setForeground(new Color(202, 192, 106));
		lblP.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblP.setBounds(331, 234, 43, 39);
		contentPane.add(lblP);

		JLabel lblC = new JLabel("C");
		lblC.setForeground(new Color(202, 192, 106));
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblC.setBounds(458, 234, 10, 39);
		contentPane.add(lblC);

		txtN = new JTextField();
		txtN.setForeground(Color.WHITE);
		txtN.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtN.setColumns(10);
		txtN.setBorder(BorderFactory.createLineBorder(new Color(202, 192, 106)));
		txtN.setBackground(new Color(35, 34, 23));
		txtN.setBounds(37, 294, 89, 32);
		contentPane.add(txtN);

		txtM = new JTextField();
		txtM.setForeground(Color.WHITE);
		txtM.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtM.setColumns(10);
		txtM.setBorder(BorderFactory.createLineBorder(new Color(202, 192, 106)));
		txtM.setBackground(new Color(35, 34, 23));
		txtM.setBounds(166, 294, 89, 32);
		contentPane.add(txtM);

		txtP = new JTextField();
		txtP.setForeground(Color.WHITE);
		txtP.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtP.setColumns(10);
		txtP.setBorder(BorderFactory.createLineBorder(new Color(202, 192, 106)));
		txtP.setBackground(new Color(35, 34, 23));
		txtP.setBounds(295, 294, 89, 32);
		contentPane.add(txtP);

		txtC = new JTextField();
		txtC.setForeground(Color.WHITE);
		txtC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtC.setColumns(10);
		txtC.setBorder(BorderFactory.createLineBorder(new Color(202, 192, 106)));
		txtC.setBackground(new Color(35, 34, 23));
		txtC.setBounds(422, 294, 89, 32);
		contentPane.add(txtC);

		JLabel label = new JLabel("____________________________________________");
		label.setForeground(new Color(202, 192, 106));
		label.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label.setBounds(2, 185, 533, 32);
		contentPane.add(label);

		Border roundedBorder = new LineBorder(Color.BLACK, 2, true);

		JButton btnStartTheGame = new JButton("Start The Game");
		btnStartTheGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStartTheGame.setBorder(BorderFactory.createLineBorder(new Color(35, 34, 23)));
				btnStartTheGame.setBorder(roundedBorder);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnStartTheGame.setBorder(BorderFactory.createLineBorder(new Color(202, 192, 106)));
			}
		});
		btnStartTheGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStartTheGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtFirstName.getText().isEmpty() && !txtN.getText().isEmpty() && !txtM.getText().isEmpty()
						&& !txtP.getText().isEmpty() && !txtC.getText().isEmpty()) {
					if (Integer.parseInt(txtN.getText()) >= 3 && Integer.parseInt(txtM.getText()) >= 3) {
						/*
						 * address sound effect monaseb ro be function playSound pass midam
						 */
						playSound("check.wav");
						Main.whichId = playersList.size();
						Players player = new Players(playersList.size(), txtFirstName.getText(), profilePictureAddress,
								Integer.parseInt(txtN.getText()), Integer.parseInt(txtM.getText()),
								Integer.parseInt(txtP.getText()), Integer.parseInt(txtC.getText()));
						playersList.add(player);
						/**
						 * hala mirim soragh profile picture i ke addressesh ro darim, va oono too
						 * imgSrc mirizim, imgCopy address jaie ke mikhaim oon aks user ro toosh copy
						 * konim ke hamoon ==>(address bin project e !) ke hamin ro bayesti bedast
						 * biarim ==> folder bin ghatan hamoonjai e ke alan safhe LogIn dare ejra mishe
						 * pas mirim soragh directory too khat 276
						 */
						// ---------------------------------------------------------------------------------------------
						String extension = profilePictureAddress.substring(profilePictureAddress.lastIndexOf(".") + 1,
								profilePictureAddress.length());
						// bebinim ke alan in project to kodoom directory dar hal ejrast ! ke mishe ==>
						// C:\Users\Ledengary\first\Project 5
						String javaApplicationPath = null;
						try {
							javaApplicationPath = new File(".").getCanonicalPath();
						} catch (IOException e1) {

						}
						// till here

						if (!profilePictureAddress.equals("")) {
							Path imgSrc = Paths.get(profilePictureAddress);
							Path imgCopy = Paths
									.get(javaApplicationPath + "\\bin\\" + txtFirstName.getText() + "." + extension);
							File fileCopy = new File(
									javaApplicationPath + "\\bin\\" + txtFirstName.getText() + "." + extension);

							if (fileCopy.exists()) {
								try {
									Files.delete(imgCopy);
								} catch (IOException e1) {

								}
							}
							try {
								/*
								 * anjam amal copy !
								 */
								Files.copy(imgSrc, imgCopy);
								player.setProfilePictureAddress(imgCopy.toString());
							} catch (IOException e1) {

							}
							// ---------------------------------------------------------------------------------------------
						} else {
							player.setProfilePictureAddress(javaApplicationPath + "\\bin\\Contact.png");
						}
						// the next two lines are just incase :)
						Main.n = Integer.parseInt(txtN.getText());
						Main.m = Integer.parseInt(txtM.getText());
						Main.deletedBlocksCount = 0;
						Main.remainingBlocksCount = Integer.parseInt(txtN.getText()) * Integer.parseInt(txtM.getText());
						Main.allButtonsHaveBeenDeleted = false;
						Main.gameIsFinished = false;
						Main.profilePictureAddress = profilePictureAddress;
						player.setProfilePictureAddress(profilePictureAddress);
						if (!Main.firstRun) {
							Main.secondsPassed = -1;
						}
						Main main = new Main();
						dispose();
						main.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Wrong Dimensions given !");
					}
				} else {
					playSound("danger.wav");
					JOptionPane.showMessageDialog(null, "Empty Text Field Found !");
				}
			}
		});
		btnStartTheGame.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnStartTheGame.setBounds(540, 445, 346, 73);
		btnStartTheGame.setForeground(new Color(35, 34, 23));
		btnStartTheGame.setBackground(new Color(202, 192, 106));
		contentPane.add(btnStartTheGame);

		JLabel lblNewLabel = new JLabel("Welcome To   TIC - TAC - TOE !");
		lblNewLabel.setFont(new Font("Trajan Pro 3", Font.PLAIN, 28));
		lblNewLabel.setBounds(37, 27, 474, 45);
		lblNewLabel.setForeground(new Color(202, 192, 106));
		contentPane.add(lblNewLabel);

		JLabel lblWelcomeEmoji = new JLabel("");
		lblWelcomeEmoji.setBounds(37, 378, 102, 99);
		contentPane.add(lblWelcomeEmoji);
		Image imgWelcome = new ImageIcon(this.getClass().getResource("/Welcome.png")).getImage()
				.getScaledInstance(lblWelcomeEmoji.getWidth(), lblWelcomeEmoji.getHeight(), Image.SCALE_DEFAULT);
		lblWelcomeEmoji.setIcon(new ImageIcon(imgWelcome));

		JLabel lblNewLabel_1 = new JLabel("Welcome Buddy !  I'm tito !\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(166, 380, 345, 39);
		contentPane.add(lblNewLabel_1);

		JLabel lblGimmeTheN = new JLabel("Gimme the N, M, P, and C in order to\r\n");
		lblGimmeTheN.setForeground(Color.WHITE);
		lblGimmeTheN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGimmeTheN.setBounds(166, 415, 276, 32);
		contentPane.add(lblGimmeTheN);

		JLabel lblMoveOnTo = new JLabel("move on to the Next Level !\r\n");
		lblMoveOnTo.setForeground(Color.WHITE);
		lblMoveOnTo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMoveOnTo.setBounds(166, 445, 206, 26);
		contentPane.add(lblMoveOnTo);

		JLabel removeProfilePicture = new JLabel("");
		removeProfilePicture.setBounds(801, 373, 38, 46);
		contentPane.add(removeProfilePicture);
		Image imgRemoveProfilePicture = new ImageIcon(this.getClass().getResource("/Close.png")).getImage();
		removeProfilePicture.setIcon(new ImageIcon(imgRemoveProfilePicture));
		removeProfilePicture.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		removeProfilePicture.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!profilePictureAddress.equals("")) {
					Image imgProfilePicture = new ImageIcon(this.getClass().getResource("/Contact.png")).getImage()
							.getScaledInstance(contactImage.getWidth(), contactImage.getHeight(), Image.SCALE_DEFAULT);
					contactImage.setIcon(new ImageIcon(imgProfilePicture));
					profilePictureAddress = "";
				}
			}
		});

		JLabel lblMainTheme = new JLabel("");
		lblMainTheme.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMainTheme.setBounds(0, 0, 895, 528);
		contentPane.add(lblMainTheme);
		lblMainTheme.setIcon(new ImageIcon(imglblMainTheme));
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

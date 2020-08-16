package Main;
import Classes.*;
import Welcome.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.ChangedCharSetException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Font;

public class Main extends JFrame {

	/*
	 * firstRun motaghayerie ke agar true bashe ma timer ro set mikonim, va agar
	 * false bashe dafe badi i ke az logIn page miaim inja dg timer ro set nemikonim
	 * var dar avaz secondsPassed ro = -1 mizarim
	 */
	public static boolean firstRun = true;
	public static int whichId;
	public static String playerName = "";
	public static String profilePictureAddress;
	public static int n = 10;
	public static int m = 10;
	public static int p;
	public static int c;
	public static int deletedBlocksCount = 0;
	public static int remainingBlocksCount = 0;
	private static JPanel contentPane;
	private Color[] myColors;
	private static JButton button[][];
	private static boolean buttonBoolean[][];
	private static String commonColoredButtons = "";
	private static JLabel lblName;
	public static JLabel label_2;
	public static JLabel label_3;
	public static JLabel lblTimer;
	public static int secondsPassed = 0;
	public static Timer gameTimer = new Timer();
	public static TimerTask gameTimerTask = new TimerTask() {
		public void run() {
			secondsPassed++;
			lblTimer.setText(Integer.toString(secondsPassed));
		}
	};
	private static int finalTime = 0;
	private static JLabel lblTillNow;
	private static String record = "";
	private static JLabel lblTellTheScore;
	private static JLabel lblEmoji;
	private static JLabel lblWordsToSay;
	/*
	 * allButtonsHaveBeenDeleted va gameIsFinished baraye tito e ke bebine agar yaro
	 * border bege YOU WON !
	 */
	public static boolean allButtonsHaveBeenDeleted = false;
	public static boolean gameIsFinished = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		if (firstRun) {
			gameTimer.scheduleAtFixedRate(gameTimerTask, 1000, 1000);
		}
		playerName = LogIn.playersList.get(whichId).getName();
		profilePictureAddress = LogIn.playersList.get(whichId).getProfilePictureAddress();
		n = LogIn.playersList.get(whichId).getN();
		m = LogIn.playersList.get(whichId).getM();
		p = LogIn.playersList.get(whichId).getP();
		c = LogIn.playersList.get(whichId).getC();
		buttonBoolean = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				buttonBoolean[i][j] = true;
			}
		}
		/**
		 * ye arayei az colors be andaze c dorost mikonim va har kodoomesh ro be soorat
		 * random por mikonim ! (KAMELAAAAAAN RANDOM !)
		 */
		myColors = new Color[c];
		for (int i = 0; i < c; i++) {
			myColors[i] = new Color((int) (Math.random() * 0x1000000));
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		Image imgMainTeme = new ImageIcon(this.getClass().getResource("/LogIn.jpg")).getImage();
		setUndecorated(true);
		setLocationRelativeTo(null);
		setUndecorated(true);
		Image imgClose = new ImageIcon(this.getClass().getResource("/Close.png")).getImage();

		JLabel lblClose = new JLabel("");
		lblClose.setBounds(854, 10, 32, 32);
		contentPane.add(lblClose);
		lblClose.setIcon(new ImageIcon(imgClose));
		lblClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		lblTillNow = new JLabel("0");
		lblTillNow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTillNow.setForeground(new Color(202, 190, 106));
		lblTillNow.setBounds(177, 283, 53, 26);
		contentPane.add(lblTillNow);

		JLabel lblLeftMoves = new JLabel(Integer.toString(p));
		lblLeftMoves.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblLeftMoves.setForeground(new Color(202, 196, 106));
		lblLeftMoves.setBounds(418, 20, 50, 40);
		contentPane.add(lblLeftMoves);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(35, 34, 23));
		panel.setBounds(418, 67, 438, 387);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(n, m, 0, 0));

		button = new JButton[n][m];
		String[][] inds = new String[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				inds[i][j] = Integer.toString(i) + Integer.toString(j);
			}
		}

		// KOLI
		/**
		 * hala dakhel do ta for miaim har khoone array 2D button ro ba button por
		 * mikonim va baraye harkodoomeshooon ye action listner mizarim ke age click
		 * shod roosh bere findNeighbour kone barash va...
		 */

		// commonColoredButtons
		/*
		 * commonColoredButtons motaghayerie ke miad ba function find neighbour hameye
		 * hamsayehash por mish vali masale injast ke index hamoon dokmei ke roosh click
		 * shod toosh nemimoone ! pas harvaght button i click shod aval
		 * commonColoredButtons ro ba index button source por mikonim !
		 * commonColoredButtons = i@j_i2@j2_i3@j3_i4@j4 ...
		 */

		// findNeighbour
		/**
		 * pas az khoondan method findNeighbour commonColored too khode method por mishe
		 */

		// checkForCommonIndexes
		/**
		 * checkForCommonIndexes method i e ke agar 1% khoonei dobar khoonde shod az
		 * tekrar oon khoone jelogiri kone
		 */

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				button[i][j] = new JButton();
				button[i][j].setName(Integer.toString(i) + "_" + Integer.toString(j));
				button[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				button[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent eventButton) {
						String btnName = ((JButton) eventButton.getSource()).getName().toString();
						commonColoredButtons = btnName.substring(0, btnName.indexOf("_")) + "@"
								+ btnName.substring(btnName.indexOf("_") + 1, btnName.length());
						Main.findNeighbours(((JButton) eventButton.getSource()), "#");
						commonColoredButtons = Main.checkForCommonIndexes(commonColoredButtons);
						String[] indexesToDelete = commonColoredButtons.split("_");
						if (indexesToDelete.length > 3) {
							p--;
							lblTillNow.setText(Integer.toString(LogIn.playersList.get(whichId).getP() - p));
							lblLeftMoves.setText(Integer.toString(p));
							deletedBlocksCount += indexesToDelete.length;
							label_2.setText(Integer.toString(deletedBlocksCount));
							label_3.setText(Integer.toString(remainingBlocksCount - deletedBlocksCount));
							for (int j2 = 0; j2 < indexesToDelete.length; j2++) {
								Main.playSound("bloop.wav");
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {

								}
								int indexesToDeleteI = Integer
										.parseInt(indexesToDelete[j2].substring(0, indexesToDelete[j2].indexOf("@")));
								int indexesToDeleteJ = Integer.parseInt(indexesToDelete[j2]
										.substring(indexesToDelete[j2].indexOf("@") + 1, indexesToDelete[j2].length()));

								buttonBoolean[indexesToDeleteI][indexesToDeleteJ] = false;
								button[indexesToDeleteI][indexesToDeleteJ].setBackground(lblName.getBackground());
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {

								}
								// ta inja tamami button haye commonColoredButtons "*" daran, pas hamashoon ro
								// "" mikonim !
								button[indexesToDeleteI][indexesToDeleteJ].setText("");
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {

								}
							}
						} else {
							// going backwards !
							for (int j2 = 0; j2 < indexesToDelete.length; j2++) {
								int indexesToDeleteI = Integer
										.parseInt(indexesToDelete[j2].substring(0, indexesToDelete[j2].indexOf("@")));
								int indexesToDeleteJ = Integer.parseInt(indexesToDelete[j2]
										.substring(indexesToDelete[j2].indexOf("@") + 1, indexesToDelete[j2].length()));

								buttonBoolean[indexesToDeleteI][indexesToDeleteJ] = true;
								button[indexesToDeleteI][indexesToDeleteJ].setText("");
							}
						}
						Main.clearItUp();
						Main.disableDeletedButtons();
						checkEndGame();
						checkTito();
					}

					protected void checkTito() {
						int range = (n * m) / 3;
						int partOne = range;
						int partTwo = range * 2;
						int partThree = range * 3;
						int rightNowWeAreAt = Integer.parseInt(label_3.getText());
						if (!gameIsFinished) {
							if (rightNowWeAreAt >= 0 && rightNowWeAreAt < partOne) {
								Image imgWelcome = new ImageIcon(this.getClass().getResource("/Angry.png")).getImage()
										.getScaledInstance(lblEmoji.getWidth(), lblEmoji.getHeight(),
												Image.SCALE_DEFAULT);
								lblEmoji.setIcon(new ImageIcon(imgWelcome));
								lblWordsToSay.setText("\" What The **** ! \"");
							} else if (rightNowWeAreAt >= partOne && rightNowWeAreAt < partTwo) {
								Image imgWelcome = new ImageIcon(this.getClass().getResource("/Wow.png")).getImage()
										.getScaledInstance(lblEmoji.getWidth(), lblEmoji.getHeight(),
												Image.SCALE_DEFAULT);
								lblEmoji.setIcon(new ImageIcon(imgWelcome));
								lblWordsToSay.setText("\" How is that even possible ! \"");
							} else {
								Image imgWelcome = new ImageIcon(this.getClass().getResource("/Meh.png")).getImage()
										.getScaledInstance(lblEmoji.getWidth(), lblEmoji.getHeight(),
												Image.SCALE_DEFAULT);
								lblEmoji.setIcon(new ImageIcon(imgWelcome));
								lblWordsToSay.setText("\" Damn son ya good ! \"");
							}
						} else {

							if (allButtonsHaveBeenDeleted) {
								Image imgWelcome = new ImageIcon(this.getClass().getResource("/Cry.png")).getImage()
										.getScaledInstance(lblEmoji.getWidth(), lblEmoji.getHeight(),
												Image.SCALE_DEFAULT);
								lblWordsToSay.setText("\" You Won ! \"");
							} else {
								Image imgWelcome = new ImageIcon(this.getClass().getResource("/Haha.png")).getImage()
										.getScaledInstance(lblEmoji.getWidth(), lblEmoji.getHeight(),
												Image.SCALE_DEFAULT);
								lblEmoji.setIcon(new ImageIcon(imgWelcome));
								lblWordsToSay.setText("\" You Lost ! \"");
							}
						}
					}

					/**
					 * in ghesmat aval check mikone ke agar tamami khoone ha disable hastan yani
					 * taraf border ! agar na aval mibine ke p taraf chandtast, agar 0 e pas
					 * harekatash tamoom shode, agar ham p dasht mibine ke aya asan khooneye dgei
					 * hast ke beshe delete beshe ! (AI :D)
					 */

					public void checkEndGame() {
						boolean thereAreMoreAvailableButtons = false;
						for (int j2 = 0; j2 < n; j2++) {
							for (int j3 = 0; j3 < m; j3++) {
								if (button[j2][j3].isEnabled()) {
									thereAreMoreAvailableButtons = true;
								}
							}
						}
						if (thereAreMoreAvailableButtons) {
							if (p == 0) {
								finalTime = secondsPassed;
								Main.calculateRecord();
								Main.printRecord();
								lblTellTheScore.setText("Final Score : \t " + record);
								contentPane.remove(lblTimer);
								JOptionPane.showMessageDialog(null, "You're outta Amo !");
								gameIsFinished = true;
								for (int i = 0; i < n; i++) {
									for (int j = 0; j < m; j++) {
										button[i][j].setEnabled(false);
										if (button[i][j].getBackground() != lblName.getBackground()) {
											button[i][j].setBackground(Color.GRAY);
										}
									}
								}
							} else {
								boolean thereAreMoreButtonsToBeDeleted = false;
								for (int j2 = n - 1; j2 >= 0 && !thereAreMoreButtonsToBeDeleted; j2--) {
									for (int j3 = 0; j3 < m; j3++) {
										if (button[j2][j3].isEnabled()) {
											commonColoredButtons = Integer.toString(j2) + "@" + Integer.toString(j3);
											Main.findNeighbours(button[j2][j3], "#");
											String[] foundCollors = commonColoredButtons.split("_");
											commonColoredButtons = checkForCommonIndexes(commonColoredButtons);
											if (foundCollors.length > 3) {
												thereAreMoreButtonsToBeDeleted = true;
												break;
											}
										}
									}
								}
								for (int j2 = 0; j2 < n; j2++) {
									for (int j3 = 0; j3 < m; j3++) {
										if (button[j2][j3].getText().equals("*")) {
											button[j2][j3].setText("");
										}
									}
								}
								if (!thereAreMoreButtonsToBeDeleted) {
									finalTime = secondsPassed;
									Main.calculateRecord();
									Main.printRecord();
									lblTellTheScore.setText("Final Score : \t " + record);
									contentPane.remove(lblTimer);
									JOptionPane.showMessageDialog(null, "No more Available Buttons to Delete \t :(");
									gameIsFinished = true;
									for (int i = 0; i < n; i++) {
										for (int j = 0; j < m; j++) {
											button[i][j].setEnabled(false);
											if (button[i][j].getBackground() != lblName.getBackground()) {
												button[i][j].setBackground(Color.GRAY);
											}
										}
									}
								}
							}
						} else {
							finalTime = secondsPassed;
							Main.calculateRecord();
							Main.printRecord();
							lblTellTheScore.setText("Final Score : \t " + record);
							contentPane.remove(lblTimer);
							JOptionPane.showMessageDialog(null, "You Won !");
							gameIsFinished = true;
							allButtonsHaveBeenDeleted = true;
						}
					}
				});
				button[i][j].setBackground(myColors[(int) (Math.random() * myColors.length)]);
				panel.add(button[i][j]);
			}
		}

		JButton start = new JButton("New Game !");
		start.setFocusable(false);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstRun = false;
				LogIn logIn = new LogIn();
				dispose();
				LogIn.profilePictureAddress = profilePictureAddress;
				logIn.setVisible(true);
			}
		});
		start.setFont(new Font("Tahoma", Font.PLAIN, 19));
		start.setBounds(218, 67, 147, 43);
		start.setForeground(new Color(35, 34, 23));
		start.setBackground(new Color(202, 192, 106));
		start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(start);

		lblName = new JLabel("Player Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setForeground(new Color(202, 192, 106));
		lblName.setBackground(new Color(35, 34, 23));
		lblName.setBounds(29, 217, 127, 32);
		contentPane.add(lblName);

		JLabel lblNameMain = new JLabel(playerName);
		lblNameMain.setForeground(new Color(202, 192, 106));
		lblNameMain.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNameMain.setBounds(29, 247, 198, 32);
		contentPane.add(lblNameMain);

		JLabel lblProfilePicture = new JLabel("");
		lblProfilePicture.setBounds(29, 67, 138, 140);
		if (!profilePictureAddress.equals("")) {
			ImageIcon imgProfilePicture = new ImageIcon(
					new ImageIcon(profilePictureAddress).getImage().getScaledInstance(lblProfilePicture.getWidth(),
							lblProfilePicture.getHeight(), Image.SCALE_DEFAULT));
			lblProfilePicture.setIcon(imgProfilePicture);
		} else {
			Image imgProfilePicture = new ImageIcon(this.getClass().getResource("/Contact.png")).getImage()
					.getScaledInstance(lblProfilePicture.getWidth(), lblProfilePicture.getHeight(),
							Image.SCALE_DEFAULT);
			lblProfilePicture.setIcon(new ImageIcon(imgProfilePicture));
		}
		contentPane.add(lblProfilePicture);

		JLabel lblMovesLeft = new JLabel("Moves Left");
		lblMovesLeft.setForeground(new Color(202, 192, 106));
		lblMovesLeft.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMovesLeft.setBounds(464, 37, 147, 17);
		contentPane.add(lblMovesLeft);

		JLabel label = new JLabel("Chosen Blocks till now :");
		label.setForeground(new Color(202, 192, 106));
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(29, 282, 138, 32);
		contentPane.add(label);

		JLabel lblDeletedBlocksTill = new JLabel("Deleted Blocks till now :");
		lblDeletedBlocksTill.setForeground(new Color(202, 192, 106));
		lblDeletedBlocksTill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeletedBlocksTill.setBounds(29, 310, 138, 32);
		contentPane.add(lblDeletedBlocksTill);

		label_2 = new JLabel("0");
		label_2.setForeground(new Color(202, 192, 106));
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_2.setBounds(177, 311, 53, 26);
		contentPane.add(label_2);

		JLabel lblRemainingBlocks = new JLabel("remaining Blocks :");
		lblRemainingBlocks.setForeground(new Color(202, 192, 106));
		lblRemainingBlocks.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRemainingBlocks.setBounds(29, 342, 138, 26);
		contentPane.add(lblRemainingBlocks);

		label_3 = new JLabel(Integer.toString(n * m));
		label_3.setForeground(new Color(202, 192, 106));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_3.setBounds(177, 342, 53, 26);
		contentPane.add(label_3);

		lblTimer = new JLabel("");
		lblTimer.setForeground(new Color(202, 192, 106));
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTimer.setBounds(282, 172, 124, 40);
		contentPane.add(lblTimer);

		JLabel lblSecondsPssed = new JLabel("Timer :");
		lblSecondsPssed.setForeground(new Color(202, 192, 106));
		lblSecondsPssed.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSecondsPssed.setBounds(218, 180, 75, 32);
		contentPane.add(lblSecondsPssed);

		JButton btnRecordsList = new JButton("Records List");
		btnRecordsList.setFocusable(false);
		btnRecordsList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecordsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecordsList recordsList = new RecordsList();
				recordsList.setVisible(true);
			}
		});
		btnRecordsList.setForeground(new Color(35, 34, 23));
		btnRecordsList.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRecordsList.setBackground(new Color(202, 192, 106));
		btnRecordsList.setBounds(218, 119, 147, 43);
		contentPane.add(btnRecordsList);

		lblTellTheScore = new JLabel("");
		lblTellTheScore.setForeground(new Color(202, 192, 106));
		lblTellTheScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTellTheScore.setBackground(new Color(35, 34, 23));
		lblTellTheScore.setBounds(418, 464, 303, 40);
		contentPane.add(lblTellTheScore);

		lblEmoji = new JLabel("");
		lblEmoji.setBounds(29, 378, 81, 76);
		contentPane.add(lblEmoji);
		Image imgWelcome = new ImageIcon(this.getClass().getResource("/Thug.png")).getImage()
				.getScaledInstance(lblEmoji.getWidth(), lblEmoji.getHeight(), Image.SCALE_DEFAULT);
		lblEmoji.setIcon(new ImageIcon(imgWelcome));

		lblWordsToSay = new JLabel("\" There are a lot of buttons left buddy... NO WAY ! \"");
		lblWordsToSay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWordsToSay.setForeground(Color.LIGHT_GRAY);
		lblWordsToSay.setBounds(29, 464, 377, 40);
		contentPane.add(lblWordsToSay);

		JLabel lblNewLabel = new JLabel("Tito Says :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(120, 436, 75, 26);
		contentPane.add(lblNewLabel);

		JButton stop = new JButton("Stop");
		stop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						if (button[i][j].isEnabled()) {
							button[i][j].setBackground(Color.GRAY);
						}
					}
				}
				finalTime = secondsPassed;
				Main.calculateRecord();
				Main.printRecord();
				lblTellTheScore.setText("Final Score : \t " + record);
				contentPane.remove(lblTimer);
				gameIsFinished = true;
				allButtonsHaveBeenDeleted = true;
			}
		});
		stop.setForeground(new Color(35, 34, 23));
		stop.setFont(new Font("Tahoma", Font.PLAIN, 19));
		stop.setFocusable(false);
		stop.setBackground(new Color(202, 192, 106));
		stop.setBounds(747, 464, 109, 43);
		contentPane.add(stop);
		JLabel lblMainTheme = new JLabel("");
		lblMainTheme.setBounds(0, 0, 896, 528);
		contentPane.add(lblMainTheme);
		lblMainTheme.setIcon(new ImageIcon(imgMainTeme));
		lblClose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
	}

	protected static void printRecord() {
		String recordToPrint = record;
		String fullStr = "";
		try {
			FileReader fr = new FileReader("recordsList.txt");
			BufferedReader br = new BufferedReader(fr);
			fullStr = br.readLine();
		} catch (Exception e2) {

		}
		PrintWriter fileOutMatris = null;
		try {
			fileOutMatris = new PrintWriter("recordsList.txt");
		} catch (FileNotFoundException e) {

		}
		if (fullStr == null) {
			fileOutMatris.print(playerName + "_" + recordToPrint);
		} else {
			if (fullStr.indexOf(playerName) != -1) {
				String[] fullSentence = new String[2];
				fullSentence[0] = fullStr.substring(0, fullStr.indexOf(playerName));
				fullSentence[1] = fullStr.substring(fullStr.indexOf(playerName), fullStr.length());
				String[] fullSentenceTwoAllParts = fullSentence[1].split("/");
				if (Integer.parseInt(fullSentenceTwoAllParts[0].substring(fullSentenceTwoAllParts[0].indexOf("_") + 1,
						fullSentenceTwoAllParts[0].length())) < Integer.parseInt(recordToPrint)) {
					fullSentenceTwoAllParts[0] = fullSentenceTwoAllParts[0].substring(0,
							fullSentenceTwoAllParts[0].indexOf("_") + 1) + recordToPrint;
					fullSentence[1] = "";
					for (int i = 0; i < fullSentenceTwoAllParts.length; i++) {
						fullSentence[1] += "/" + fullSentenceTwoAllParts[i];
					}
					fullSentence[1] = fullSentence[1].substring(1, fullSentence[1].length());
					fileOutMatris.print(fullSentence[0] + fullSentence[1]);
				}
				// haji inja be ye moshkeli khordam ke agar score bedast oomade highest score
				// taraf nabood insert nemikone haaaaa, vali moshkel inja e ke harci to file
				// hast ro ham delete mikone !
				// pas dar natije ye else statement i ham mizaram inja ke harchi hast ro dobare
				// toosh write kone !
				else {
					fileOutMatris.print(fullSentence[0] + fullSentence[1]);
				}
			} else {
				fileOutMatris.print(fullStr);
				fileOutMatris.print("/" + playerName + "_" + recordToPrint);
			}
		}
		fileOutMatris.close();
	}

	protected static void calculateRecord() {
		int t = finalTime;
		int c = Integer.parseInt(lblTillNow.getText());
		int a = n * m;
		int h = Integer.parseInt(label_2.getText());
		String multiplication = Power.multiply(Integer.toString(a), Integer.toString(h));
		String recordCalculation = Power.dividePanel(multiplication, Double.toString(Math.pow(c, Math.pow(t, 1 / 3))));
		record = recordCalculation;
		if (record.equals("NaN")) {
			record = "0";
			recordCalculation = "0";
		} else {
			if (record.indexOf(".") != -1) {
				record = record.substring(0, record.indexOf("."));
			}
		}
	}

	protected static void disableDeletedButtons() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (button[i][j].getBackground() == lblName.getBackground()) {
					button[i][j].setEnabled(false);
				}
			}
		}
	}

	protected static String checkForCommonIndexes(String commonColoredButtons2) {
		String[] inputIndexes = commonColoredButtons2.split("_");
		ArrayList<String> outputIndexes = new ArrayList<String>();
		boolean foundCommon = false;
		for (int i = 0; i < inputIndexes.length; i++) {
			for (int j = 0; j < outputIndexes.size(); j++) {
				if (inputIndexes[i].equals(outputIndexes.get(j))) {
					foundCommon = true;
				}
			}
			if (!foundCommon) {
				outputIndexes.add(inputIndexes[i]);
			}
			foundCommon = false;
		}
		String outputIndexesString = "";
		for (int i = 0; i < outputIndexes.size(); i++) {
			outputIndexesString += "_" + outputIndexes.get(i);
		}
		outputIndexesString = outputIndexesString.substring(1, outputIndexesString.length());
		return outputIndexesString;
	}

	/**
	 * khob to method clearItUp miaim do method shift down va sepas shift left ro
	 * mikhonim, shift down miad az i akhar jadval va j ham az 0 shoroo mikone be
	 * bala oomadan dar hamoon j va i ke dare doone doone kam mishe yeki azash, be
	 * avalin khoonei ke background esh default e va disable shode hast mokhtasat
	 * oon dokme ro be soorat default be onvan firstWhiteColoredButtonIndex dar naar
	 * migirim va dobare hamon sootoon ro az bad index i o j
	 * firstWhiteColoredButtonIndex peimayesh mikonim, be avalin khoone rangi i ke
	 * residim va firstWhiteColoredButtonIndex mosavi ba pishfarz yani # nabood oon
	 * i o j ro baraye firstColoredButtonAfterWhiteColoredButtonIndex dar nazar
	 * migirim va jashoon ro avaz mikonim va dobare hameye in kararo az aval hamoon
	 * sotoon anjam midim. iin bood shift down, hamin kararo baraye shift left anjam
	 * midim faghat jahat peimayesh be i sabet va j motaghayer ke dare ++ mishe
	 * taghir mikone.
	 */

	protected static void clearItUp() {
		try {
			shiftDown();
			shiftLeft();
		} catch (InterruptedException e) {

		}
	}

	private static void shiftDown() throws InterruptedException {
		String firstWhiteColoredButtonIndex = "#";
		String firstColoredButtonAfterWhiteColoredButtonIndex = "#";
		int jCounter = 0;
		while (jCounter < m) {
			for (int iCounter = n - 1; iCounter >= 0; iCounter--) {
				if (button[iCounter][jCounter].getBackground() == lblName.getBackground()) {
					if (firstWhiteColoredButtonIndex.equals("#")) {
						firstWhiteColoredButtonIndex = Integer.toString(iCounter) + "@" + Integer.toString(jCounter);
					}
				} else {
					if (!firstWhiteColoredButtonIndex.equals("#")) {
						firstColoredButtonAfterWhiteColoredButtonIndex = Integer.toString(iCounter) + "@"
								+ Integer.toString(jCounter);
						// alan code ye jaye khali peida karde va yedoone por, hala jahashoon ro baham
						// avaz mikonim
						int firstWhiteColoredButtonIndexI = Integer.parseInt(
								firstWhiteColoredButtonIndex.substring(0, firstWhiteColoredButtonIndex.indexOf("@")));
						int firstWhiteColoredButtonIndeJ = Integer.parseInt(firstWhiteColoredButtonIndex.substring(
								firstWhiteColoredButtonIndex.indexOf("@") + 1, firstWhiteColoredButtonIndex.length()));
						int firstColoredButtonAfterWhiteColoredButtonIndexI = Integer
								.parseInt(firstColoredButtonAfterWhiteColoredButtonIndex.substring(0,
										firstColoredButtonAfterWhiteColoredButtonIndex.indexOf("@")));
						int firstColoredButtonAfterWhiteColoredButtonIndexJ = Integer.parseInt(
								firstWhiteColoredButtonIndex.substring(firstWhiteColoredButtonIndex.indexOf("@") + 1,
										firstWhiteColoredButtonIndex.length()));
						;
						Thread.sleep(10);
						button[firstWhiteColoredButtonIndexI][firstWhiteColoredButtonIndeJ].setBackground(
								button[firstColoredButtonAfterWhiteColoredButtonIndexI][firstColoredButtonAfterWhiteColoredButtonIndexJ]
										.getBackground());
						button[firstColoredButtonAfterWhiteColoredButtonIndexI][firstColoredButtonAfterWhiteColoredButtonIndexJ]
								.setBackground(lblName.getBackground());
						firstWhiteColoredButtonIndex = "#";
						iCounter = n - 1;
					}
				}
			}
			firstWhiteColoredButtonIndex = "#";
			firstColoredButtonAfterWhiteColoredButtonIndex = "#";
			jCounter++;
		}
	}

	private static void shiftLeft() throws InterruptedException {
		String firstWhiteColoredButtonIndex = "#";
		String firstColoredButtonAfterWhiteColoredButtonIndex = "#";
		int iCounter = n - 1;
		while (iCounter >= 0) {
			for (int jCounter = 0; jCounter < m; jCounter++) {
				if (button[iCounter][jCounter].getBackground() == lblName.getBackground()) {
					if (firstWhiteColoredButtonIndex.equals("#")) {
						firstWhiteColoredButtonIndex = Integer.toString(iCounter) + "@" + Integer.toString(jCounter);
					}
				} else {
					if (!firstWhiteColoredButtonIndex.equals("#")) {
						firstColoredButtonAfterWhiteColoredButtonIndex = Integer.toString(iCounter) + "@"
								+ Integer.toString(jCounter);
						// alan code ye jaye khali peida karde va yedoone por, hala jahashoon ro baham
						// avaz mikonim
						int firstWhiteColoredButtonIndexI = Integer.parseInt(
								firstWhiteColoredButtonIndex.substring(0, firstWhiteColoredButtonIndex.indexOf("@")));
						int firstWhiteColoredButtonIndeJ = Integer.parseInt(firstWhiteColoredButtonIndex.substring(
								firstWhiteColoredButtonIndex.indexOf("@") + 1, firstWhiteColoredButtonIndex.length()));
						int firstColoredButtonAfterWhiteColoredButtonIndexI = Integer
								.parseInt(firstColoredButtonAfterWhiteColoredButtonIndex.substring(0,
										firstColoredButtonAfterWhiteColoredButtonIndex.indexOf("@")));
						int firstColoredButtonAfterWhiteColoredButtonIndexJ = Integer
								.parseInt(firstColoredButtonAfterWhiteColoredButtonIndex.substring(
										firstColoredButtonAfterWhiteColoredButtonIndex.indexOf("@") + 1,
										firstColoredButtonAfterWhiteColoredButtonIndex.length()));
						;
						Thread.sleep(10);
						button[firstWhiteColoredButtonIndexI][firstWhiteColoredButtonIndeJ].setBackground(
								button[firstColoredButtonAfterWhiteColoredButtonIndexI][firstColoredButtonAfterWhiteColoredButtonIndexJ]
										.getBackground());
						button[firstColoredButtonAfterWhiteColoredButtonIndexI][firstColoredButtonAfterWhiteColoredButtonIndexJ]
								.setBackground(lblName.getBackground());
						firstWhiteColoredButtonIndex = "#";
						jCounter = -1;
					}
				}
			}
			firstWhiteColoredButtonIndex = "#";
			firstColoredButtonAfterWhiteColoredButtonIndex = "#";
			iCounter--;
		}
	}

	// KOLI
	/**
	 * khob miresim be tabe recursive findNeighbour ke motaghayer jalebi be nam
	 * ausserDem dare !
	 * 
	 * @param recievedButton
	 * @param ausserDem
	 */

	// ausserDem
	/*
	 * ausserDem almanie, be english mishe exception yani motaghayerie ke mige moghe
	 * jelo raftan yevaght ro khodesh bar nagarde va recursive bi nahayat beshe ! be
	 * soorat pishfarz barash # ferestade mishe dakhel tabe index dokme ghablesh ro
	 * behesh pass midim
	 */

	protected static void findNeighbours(JButton recievedButton, String ausserDem) {
		int iMain = Integer.parseInt(recievedButton.getName().substring(0, recievedButton.getName().indexOf("_")));
		int jMain = Integer.parseInt(recievedButton.getName().substring(recievedButton.getName().indexOf("_") + 1,
				recievedButton.getName().length()));
		button[iMain][jMain].setText("*");
		String ausserDemNew = Integer.toString(iMain) + "@" + Integer.toString(jMain);
		int iToCheck = 0;
		int jToCheck = 0;
		int counter = 0;
		while (counter <= 8) {
			counter++;
			switch (counter) {
			case 1:
				iToCheck = iMain - 1;
				jToCheck = jMain;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;
			case 2:
				iToCheck = iMain - 1;
				jToCheck = jMain + 1;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;
			case 3:
				iToCheck = iMain;
				jToCheck = jMain + 1;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;
			case 4:
				iToCheck = iMain + 1;
				jToCheck = jMain + 1;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;
			case 5:
				iToCheck = iMain + 1;
				jToCheck = jMain;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;
			case 6:
				iToCheck = iMain + 1;
				jToCheck = jMain - 1;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;
			case 7:
				iToCheck = iMain;
				jToCheck = jMain - 1;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				;
			case 8:
				iToCheck = iMain - 1;
				jToCheck = jMain - 1;
				if (checkDimensions(iToCheck, jToCheck) && checkAusserDem(iToCheck, jToCheck, ausserDem)) {
					if (button[iToCheck][jToCheck].getBackground() == button[iMain][jMain].getBackground()) {
						button[iToCheck][jToCheck].setText("*");
						commonColoredButtons = commonColoredButtons + "_" + Integer.toString(iToCheck) + "@"
								+ Integer.toString(jToCheck);
						findNeighbours(button[iToCheck][jToCheck], ausserDemNew);
					}
				}
				break;

			default:
				break;
			}
		}
	}

	/**
	 * agar oonjai ke mikhad checj kone (oonja : iToCheck , jToCheck) ba ausserDem
	 * esh barabar bood yani mikhad bargardde roo khodesh ke az jelogiri mikone
	 * 
	 * @param iToCheck
	 * @param jToCheck
	 * @param ausserDem
	 * @return
	 */
	private static boolean checkAusserDem(int iToCheck, int jToCheck, String ausserDem) {
		if (ausserDem.equals("#")) {
			return true;
		} else {
			int iAusserDem = Integer.parseInt(ausserDem.substring(0, ausserDem.indexOf("@")));
			int jAusserDem = Integer.parseInt(ausserDem.substring(ausserDem.indexOf("@") + 1, ausserDem.length()));
			if (iToCheck == iAusserDem && jAusserDem == jToCheck) {
				return false;
			}
			if (button[iToCheck][jToCheck].getText().equals("*")) {
				return false;
			}
			return true;
		}
	}

	private static boolean checkDimensions(int iToCheck, int jToCheck) {
		if (iToCheck >= 0 && iToCheck < n) {
			if (jToCheck >= 0 && jToCheck < m) {
				return true;
			}
		}
		return false;
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

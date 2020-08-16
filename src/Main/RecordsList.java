package Main;
import Classes.*;
import Welcome.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JScrollPane;

public class RecordsList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblScore;
	private JScrollPane scrollPane;
	private JLabel lblsortedList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecordsList frame = new RecordsList();
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
	public RecordsList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JLabel lblMainTheme = new JLabel("");
		lblMainTheme.setBackground(new Color(240, 240, 240));
		lblMainTheme.setBounds(0, 0, 553, 328);
		Image imgMainTheme = new ImageIcon(this.getClass().getResource("/RecordsListTheme.jpg")).getImage();
		lblMainTheme.setIcon(new ImageIcon(imgMainTheme));

		JLabel lblClose = new JLabel("");
		Image imgClose = new ImageIcon(this.getClass().getResource("/Close_24.png")).getImage();
		lblClose.setIcon(new ImageIcon(imgClose));
		lblClose.setBounds(524, 0, 24, 34);
		contentPane.add(lblClose);

		String[] colNames = { "Name", "Score" };
		DefaultTableModel model = new DefaultTableModel(colNames, 0);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 57, 504, 246);
		scrollPane.getViewport().setBackground(new Color(35, 34, 23));
		contentPane.add(scrollPane);
		table = new JTable(model);
		table.setTableHeader(null);
		table.setShowGrid(false);
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(35, 34, 23));
		table.setEnabled(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(30);
		String fullStr = "";
		try {
			FileReader fr = new FileReader("recordsList.txt");
			BufferedReader br = new BufferedReader(fr);
			fullStr = br.readLine();
		} catch (Exception e2) {

		}
		if (fullStr != null) {
			String[] records = sort(fullStr).split("/");
			for (String record : records) {
				String[] part = record.split("_");
				String[] Object = { part[0], part[1] };
				model.addRow(Object);
			}
		}
		scrollPane.setViewportView(table);
		lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(202, 192, 106));
		lblNewLabel.setBackground(new Color(35, 34, 23));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(26, 21, 68, 26);
		contentPane.add(lblNewLabel);

		lblScore = new JLabel("High Score");
		lblScore.setForeground(new Color(202, 192, 106));
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblScore.setBackground(new Color(35, 34, 23));
		lblScore.setBounds(259, 21, 109, 26);
		contentPane.add(lblScore);

		lblsortedList = new JLabel("(Sorted List)");
		lblsortedList.setForeground(new Color(202, 192, 106));
		lblsortedList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblsortedList.setBackground(new Color(35, 34, 23));
		lblsortedList.setBounds(425, 21, 89, 26);
		contentPane.add(lblsortedList);

		contentPane.add(lblMainTheme);
		lblClose.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	/**
	 * hala az bubble sort estefade mikonim ke betoonim list ro sort konim, ba in
	 * tafavot ke khoone score haro to araye avaz mikonim, khoone esm haram hamzaman
	 * ba score ha avaz mikonim !
	 * 
	 * @param fullStr
	 * @return
	 */
	private String sort(String fullStr) {
		String[] betweenSlashes = fullStr.split("/");
		String[] names = new String[betweenSlashes.length];
		Double[] scores = new Double[betweenSlashes.length];
		for (int i = 0; i < betweenSlashes.length; i++) {
			String[] betweenUnderLines = betweenSlashes[i].split("_");
			names[i] = betweenUnderLines[0];
			scores[i] = Double.parseDouble(betweenUnderLines[1]);
		}
		boolean changed = true;
		int j = 0;
		double temp = 0;
		String tempStr = "";
		while (changed) {
			changed = false;
			j++;
			for (int i = 0; i < scores.length - j; i++) {
				if (scores[i] > scores[i + 1]) {
					temp = scores[i];
					scores[i] = scores[i + 1];
					scores[i + 1] = temp;
					tempStr = names[i];
					names[i] = names[i + 1];
					names[i + 1] = tempStr;
					changed = true;
				}
			}
		}
		String answer = "";
		for (int i = names.length - 1; i >= 0; i--) {
			answer += "/" + names[i] + "_"
					+ Double.toString(scores[i]).substring(0, Double.toString(scores[i]).length() - 2);
		}
		answer = answer.substring(1, answer.length());
		return answer;
	}
}

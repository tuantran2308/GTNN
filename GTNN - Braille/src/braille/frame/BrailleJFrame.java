package braille.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import brai.java.Communicator;
import brai.java.Translate;
import brai.java.TranslatePara;
import braille.parse.ReadFromFile;

public class BrailleJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Communicator communicator = new Communicator();

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea textInput;
	private JTextArea textOut;
	private JButton btnNewButton;
	private JButton btnConnect;
	private JComboBox<String> comboBox;
	private String comName;
	private JLabel lblStatusBar;
	private JMenuBar menuBar;
	private JSeparator separator_1;
	
	private int index = 0;
	private File openFile = null;
	private JMenuItem mntmSaveAs;
	private JSeparator separator_2;
	/**
	 * Create the frame.
	 */
	public BrailleJFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BrailleJFrame.class.getResource("/source/image/braille - icon.png")));
		init();
	}

	private void init() {
		setTitle("Braille Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 500);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setMnemonic('F');
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmOpen = new JMenuItem("Open File ...");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpen.addActionListener(new openFileListener());
		mntmOpen.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/open-file-icon.png")));
		mnNewMenu.add(mntmOpen);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblStatusBar.setText("");
				textInput.setText("");
				textOut.setText("");
				openFile = null;
			}
		});
		
		mntmSaveAs = new JMenuItem("Save As ...");
		mntmSaveAs.addActionListener(new saveFileListener());
		mntmSaveAs.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/Save-icon.png")));
		mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnNewMenu.add(mntmSaveAs);
		
		separator_2 = new JSeparator();
		mnNewMenu.add(separator_2);
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu.add(mntmRefresh);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mntmExit.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/Close-2-icon.png")));
		mnNewMenu.add(mntmExit);
		
		JMenu mnAbout = new JMenu("Help");
		mnAbout.setMnemonic('H');
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setMinimumSize(new Dimension(720, 500));

		JButton btnNext = new JButton("Convert");
		btnNext.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/convert-icon.PNG")));
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNext.addActionListener(new nextButtonListener());

		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Input", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new TitledBorder(null, "Output", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnNextToComm = new JButton("Next To COM");
		btnNextToComm.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/arrow-right-icon.png")));
		btnNextToComm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNextToComm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = textInput.getText();
				TranslatePara param = new TranslatePara(data);
				ArrayList<String> listData = param.getArrayList();
				Translate tranlate = new Translate();

				if (index < listData.size()) {
					lblStatusBar.setText("Transfering data: " + listData.get(index));
					tranlate.setData(listData.get(index++));
					byte[] send = tranlate.getBrailleByte();
					communicator.writeData( send );
					
					
					for(int i = 0; i < send.length; i++){
						System.out.print(send[i] + " ");
					}
					System.out.println();
				} else {
					lblStatusBar.setText("See you again !");
					index = 0;
				}
			}
		});

		btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/search-icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				communicator.searchForPorts();
				String[] ports = communicator.getAvailablePorts();
				//ports = new String[]{"123", "456","789"};
				for(int i = 0; i < ports.length; i++){
					if(ports[i] != null)
					comboBox.addItem(ports[i]);
				}
			}
		});
	
		comboBox = new JComboBox<String>();
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//System.out.println(e.getItem().toString());
				comName = e.getItem().toString();
				
			}
		});

		btnConnect = new JButton("Connect");
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnConnect.setIcon(new ImageIcon(BrailleJFrame.class.getResource("/source/image/connect-icon.png")));
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comName);
				communicator.connect(comName);
				if(communicator.getConnected() == true ){
					lblStatusBar.setText("Status:Successful to Connect");
					if(communicator.initIOStream())
						lblStatusBar.setText("Successful Connection");
					else lblStatusBar.setText("Faile to create Output");
					
				} else {
					lblStatusBar.setText("Status:Failed to Connect");
				}
			}
		});
		
		lblStatusBar = new JLabel("");
		lblStatusBar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblStatusBar.setBorder(new MatteBorder(1, 0, 0, 0, (Color) SystemColor.activeCaptionBorder));
		lblStatusBar.setForeground(Color.BLACK);
		lblStatusBar.setBackground(Color.DARK_GRAY);
		
		separator_1 = new JSeparator();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
									.addComponent(btnNext))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
							.addGap(7))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnConnect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
							.addComponent(btnNextToComm))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
							.addGap(0)))
					.addGap(41))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblStatusBar, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addComponent(btnConnect)
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(btnNextToComm)
						.addComponent(btnNext))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStatusBar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
		);

		textOut = new JTextArea();
		scrollPane_1.setViewportView(textOut);

		textInput = new JTextArea();
		scrollPane.setViewportView(textInput);
		contentPane.setLayout(gl_contentPane);
	}

	private class nextButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String data = textInput.getText();
			TranslatePara param = new TranslatePara(data);
			ArrayList<String> listData = param.getArrayList();
			Translate tranlate = new Translate();

			String out = new String();
			for(int i = 0; i < listData.size(); i++){
				tranlate.setData(listData.get(i));
				String word = tranlate.getBrailleString();

				out += word + "\n";

			}
			textOut.setText(out);

		}
	}
	
	private class openFileListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			int chooseType = fileChooser.showOpenDialog(null);
			if (chooseType == JFileChooser.APPROVE_OPTION) {
				openFile = fileChooser.getSelectedFile();
				lblStatusBar.setText("You have chosen file: " + openFile.getAbsolutePath());
				
				ReadFromFile readFile = new ReadFromFile(openFile.getAbsolutePath());
				textInput.setText(readFile.getFileContents());
			}
		}
	}
	
	private class saveFileListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String textContent = textInput.getText();
			try {
				File saveFile;
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showSaveDialog(null);
				if (fileChooser.getSelectedFile() != null) {
					saveFile = fileChooser.getSelectedFile();
					OutputStream os = new FileOutputStream(saveFile.getAbsolutePath());
					PrintStream printStream = new PrintStream(os);
					printStream.print(textContent);
					printStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}

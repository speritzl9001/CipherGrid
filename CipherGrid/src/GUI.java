import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class GUI{

	private JFrame frmEncoderfinalProject;
	private JTextField txtFilePath;
	private JTextField txtEncodedFileName;
	private JPasswordField passwordField;
	@SuppressWarnings("unused")
	private final Action action = new SwingAction();


	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				         GUI window = new GUI();
					window.frmEncoderfinalProject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmEncoderfinalProject = new JFrame();
		frmEncoderfinalProject.getContentPane().setForeground(Color.LIGHT_GRAY);
		frmEncoderfinalProject.setTitle("Playfair Cipher");
		frmEncoderfinalProject.setBounds(100, 100, 930, 635);
		frmEncoderfinalProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(315, 137, 105, 23);
		btnBrowse.setEnabled(false);

		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame readpathframe = new JFrame();
				JFileChooser readpath = new JFileChooser();
				int status = readpath.showOpenDialog(readpathframe);
				if (status == JFileChooser.APPROVE_OPTION)
				{
					File readpathfile = readpath.getSelectedFile();
					String path = readpathfile.getPath();
					txtFilePath.setText(path);
				}
			}
		});

		txtFilePath = new JTextField();
		txtFilePath.setEditable(false);
		txtFilePath.setBounds(15, 137, 293, 23);
		txtFilePath.setColumns(10);
		txtFilePath.setBackground(Color.WHITE);

		final JLabel lblEnterCodeword = new JLabel("Enter Keyword:");
		lblEnterCodeword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterCodeword.setBounds(232, 39, 118, 14);

		final JLabel lblChooseFile = new JLabel("Input File");
		lblChooseFile.setHorizontalAlignment(SwingConstants.LEFT);
		lblChooseFile.setBounds(15, 103, 111, 23);
		lblChooseFile.setFont(new Font("Tahoma", Font.BOLD, 14));

		final JLabel lblInput = new JLabel("Input Message");
		lblInput.setHorizontalAlignment(SwingConstants.LEFT);
		lblInput.setBounds(629, 103, 118, 23);
		lblInput.setFont(new Font("Tahoma", Font.BOLD, 14));

		final JLabel lblOutput = new JLabel("Output Message");
		lblOutput.setHorizontalAlignment(SwingConstants.LEFT);
		lblOutput.setBounds(629, 299, 140, 23);
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtEncodedFileName = new JTextField();
		txtEncodedFileName.setEditable(false);
		txtEncodedFileName.setBounds(15, 249, 405, 23);
		txtEncodedFileName.setColumns(10);
		txtEncodedFileName.setBackground(Color.WHITE);
		txtEncodedFileName.setEnabled(false);

		JButton btnEncode = new JButton("Encode");
		btnEncode.setBounds(85, 534, 235, 44);
		btnEncode.setFont(new Font("Tahoma", Font.BOLD, 18));

		JButton btnDecode = new JButton("Decode");
		btnDecode.setBounds(545, 534, 235, 44);
		btnDecode.setFont(new Font("Tahoma", Font.BOLD, 18));
		frmEncoderfinalProject.getContentPane().setLayout(null);
		frmEncoderfinalProject.getContentPane().add(btnBrowse);
		frmEncoderfinalProject.getContentPane().add(txtFilePath);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(590, 137, 229, 134);
		frmEncoderfinalProject.getContentPane().add(scrollPane);

		final JTextArea txtAreaInput = new JTextArea();
		txtAreaInput.setText("Insert a short message to encrypt or\ndecrypt");
		txtAreaInput.setLineWrap(true);
		scrollPane.setViewportView(txtAreaInput);
		txtAreaInput.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		txtAreaInput.setBackground(Color.WHITE);
		frmEncoderfinalProject.getContentPane().add(lblEnterCodeword);
		frmEncoderfinalProject.getContentPane().add(lblChooseFile);
		frmEncoderfinalProject.getContentPane().add(lblInput);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(590, 333, 229, 134);
		frmEncoderfinalProject.getContentPane().add(scrollPane_1);

		final JTextArea txtAreaOutput = new JTextArea();
		txtAreaOutput.setForeground(Color.BLACK);
		txtAreaOutput.setText("Output message will be displayed\nhere.");
		txtAreaOutput.setEditable(false);
		txtAreaOutput.setLineWrap(true);
		scrollPane_1.setViewportView(txtAreaOutput);
		txtAreaOutput.setBorder(BorderFactory.createMatteBorder( 2, 2, 2, 2, Color.black));
		txtAreaOutput.setBackground(Color.WHITE);
		txtAreaOutput.setFocusable(false);

		frmEncoderfinalProject.getContentPane().add(lblOutput);
		frmEncoderfinalProject.getContentPane().add(txtEncodedFileName);
		frmEncoderfinalProject.getContentPane().add(btnEncode);
		frmEncoderfinalProject.getContentPane().add(btnDecode);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(0, 74, 929, 7);
		frmEncoderfinalProject.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(-46, 508, 988, 2);
		frmEncoderfinalProject.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(new Color(0, 0, 0));
		separator_2.setBounds(440, 74, 14, 436);
		frmEncoderfinalProject.getContentPane().add(separator_2);

		final JRadioButton rdbtnQuickMode = new JRadioButton("Quick Mode");
		rdbtnQuickMode.setToolTipText("Activate the quick input/output side.");
		rdbtnQuickMode.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnQuickMode.setBounds(668, 33, 123, 25);
		frmEncoderfinalProject.getContentPane().add(rdbtnQuickMode);

		final JRadioButton rdbtnFileMode = new JRadioButton("File Mode");
		rdbtnFileMode.setToolTipText("Activate the file input side.");
		rdbtnFileMode.setFont(new Font("Tahoma", Font.BOLD, 14));
		rdbtnFileMode.setBounds(86, 34, 123, 25);
		frmEncoderfinalProject.getContentPane().add(rdbtnFileMode);
		rdbtnFileMode.setSelected(false);

		final JLabel lblEncodedFile = new JLabel("Output File Name");
		lblEncodedFile.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEncodedFile.setBounds(15, 225, 165, 16);
		frmEncoderfinalProject.getContentPane().add(lblEncodedFile);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Type in keyword to determine cipher encryption/decryption.");
		passwordField.setEchoChar('*');
		passwordField.setBounds(354, 39, 205, 18);
		frmEncoderfinalProject.getContentPane().add(passwordField);

		final JLabel lblNewLabel = new JLabel("show password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(494, 56, 103, 16);
		frmEncoderfinalProject.getContentPane().add(lblNewLabel);

		final JCheckBox chckbxshowPassword = new JCheckBox("");
		chckbxshowPassword.setSelected(false);
		chckbxshowPassword.setBounds(472, 57, 21, 14);
		frmEncoderfinalProject.getContentPane().add(chckbxshowPassword);

		final JLabel lblNewLabel_1 = new JLabel("Must be between 5 - 255");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(356, 56, 109, 14);
		frmEncoderfinalProject.getContentPane().add(lblNewLabel_1);

		// END GUI CREATION ********************************************************************************

		//ShowPassword CheckBox ActionListner:
		chckbxshowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxshowPassword.isSelected()) {
					passwordField.setEchoChar((char)0); // make the password into readable letters
				}
				else passwordField.setEchoChar('*'); //set default EchoChar to '*'
			}
		});



		// MENU BAR:
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 914, 21);
		frmEncoderfinalProject.getContentPane().add(menuBar);

		JMenu mnCustomize = new JMenu("Customize"); // to add color choosers
		menuBar.add(mnCustomize);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Text Color");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color textColor;
				textColor= JColorChooser.showDialog(null, "Select a Text Color", Color.black);

				lblEnterCodeword.setForeground(textColor);
				lblChooseFile.setForeground(textColor);
				lblInput.setForeground(textColor);
				lblOutput.setForeground(textColor);
				lblNewLabel_1.setForeground(textColor);
				lblNewLabel.setForeground(textColor);
				txtAreaInput.setForeground(textColor);
				txtAreaOutput.setForeground(textColor);
				rdbtnFileMode.setForeground(textColor);
				rdbtnQuickMode.setForeground(textColor);
				lblEncodedFile.setForeground(textColor);
			}
		});
		mnCustomize.add(mntmNewMenuItem_2);

		JMenuItem mntmBoxes = new JMenuItem("Text Box Color");
		mntmBoxes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color boxColor;
				boxColor= JColorChooser.showDialog(null, "Select a TextBox Color", Color.white);
				txtEncodedFileName.setBackground(boxColor);
				txtFilePath.setBackground(boxColor);
				passwordField.setBackground(boxColor);
				txtAreaOutput.setBackground(boxColor);
				txtAreaInput.setBackground(boxColor);
			}
		});
		mnCustomize.add(mntmBoxes);

		JMenuItem mntmBackground = new JMenuItem("Background Color");
		mntmBackground.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color selectedBackGroundColor;
				selectedBackGroundColor= JColorChooser.showDialog(null, "Select a Background Color", Color.white);
				frmEncoderfinalProject.getContentPane().setBackground(selectedBackGroundColor);
				rdbtnQuickMode.setBackground(selectedBackGroundColor);
				rdbtnFileMode.setBackground(selectedBackGroundColor);
				chckbxshowPassword.setBackground(selectedBackGroundColor);
			}
		});
		mnCustomize.add(mntmBackground);

		JMenu mnNeedHelp = new JMenu("Help"); // to add help menu
		menuBar.add(mnNeedHelp);


		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "CipherGrid: A Java based implementation of the Playfair cipher." +
													"\n\nhttp://en.wikipedia.org/wiki/Playfair_cipher\n\n", "About CipherGrid", 2);
			}
		});
		mnNeedHelp.add(mntmAbout);

		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);

		final JButton btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtAreaInput.setText("");
				txtAreaOutput.setText("");
			}
		});
		btnClear.setBounds(472, 137, 108, 31);
		frmEncoderfinalProject.getContentPane().add(btnClear);



		// Quick mode radio button ActionListener.
		rdbtnQuickMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBrowse.setEnabled(false);		   		
				txtAreaInput.setEnabled(true);   		
				txtAreaOutput.setEnabled(true);  
				txtAreaOutput.setEditable(true);
				btnClear.setEnabled(true);
				rdbtnFileMode.setSelected(false);
				txtFilePath.setEnabled(false);
				txtEncodedFileName.setEnabled(false);

			}
		});// END rdbtnQuickMode ActionListener

		// File mode radio button listener.
		rdbtnFileMode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBrowse.setEnabled(true);		   		
				txtAreaInput.setEnabled(false);   		
				txtAreaOutput.setEnabled(false);  		
				rdbtnQuickMode.setSelected(false);
				btnClear.setEnabled(false);
				txtFilePath.setEnabled(true);
				txtEncodedFileName.setEnabled(true);
			}
		});// END rdbtnFileMode ActionListener

		// Encode button ActionListener.
		btnEncode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//String keywordAsString = txtKeyword.getText();
				char[] passwordArr = passwordField.getPassword();
				String password = new String(passwordArr);

				if (password.length()<5 || password.length()>255)
				{
					JOptionPane.showMessageDialog(null, "Keyword must be greater than 5 and less then 255 characters long. Please try again.");
				}
				else { // OK to process
					if (rdbtnFileMode.isSelected()) { // File Mode selected	
						if (txtFilePath.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please enter a file location and try again.");
						}
						else { // OK to process further.
							String filePath = txtFilePath.getText();
							// Create Message class object.
							Message message = new Message();
							// Create ArrayList to store the message from the file (as Integers)
							ArrayList<Integer> messageAsInts = new ArrayList<Integer>(0);
							messageAsInts = message.readMessageFile(filePath);

							// Create ciphergrid object.
							Ciphergrid ciphergrid = new Ciphergrid();
							// Create ArrayList to store the encoded message from the file (as Integers)
							ArrayList<Integer> encodedMessage = new ArrayList<Integer>(0);
							// Send the keyword and message to the encode method.
							encodedMessage = ciphergrid.encode(password, messageAsInts);
							boolean GUI_Debug1 = false;
							if (GUI_Debug1) {
								System.out.println("\n" + "GUI_Debug1: Printing encoded message from the GUI:");
								System.out.println(encodedMessage);
							}

							// Add "_encoded.txt" to the end of the file name.
							filePath = filePath.substring(0, filePath.lastIndexOf('.'));
							filePath = filePath.concat("_encoded.txt");

							// Create a new CipherFileWriter object.
							CipherFileWriter ciyWriter = new CipherFileWriter();
							// Pass the encodedMessage and filePath to the cyWriter
							try {
								ciyWriter.writeEncodedMsgToFile(encodedMessage, filePath);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							// Display the encoded file name and path in the JTextField.
							txtEncodedFileName.setText(filePath);
						}
					}
					else if (rdbtnQuickMode.isSelected()) { // Quick Mode selected	
						if (txtAreaInput.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Please enter a message to encode.");
						}
						else { // OK to process further.
							String quickMessage = txtAreaInput.getText();
							Message message = new Message();
							ArrayList<Integer> messageAsIntArr = new ArrayList<Integer>(0);
							// Pass the message string to the quickmessage method which
							// will convert it to a Integer ArrayList.
							// Then store the result in messageAsIntArr.
							messageAsIntArr = message.quickmessage(quickMessage);
							// Create ciphergrid object.
							Ciphergrid ciphergrid = new Ciphergrid();
							// Create arrayList to store the encoded message.
							ArrayList<Integer> encodedMessage = new ArrayList<Integer>(0);
							// Pass the keyword and the message (messageAsIntArr) to the 
							// encode method. Store the result in the encodedMessage ArrayList.							
							encodedMessage = ciphergrid.encode(password, messageAsIntArr);
							// Process the encoded message for display in the JTextArea (txtAreaOutput)
							String encodedMessageAsString = encodedMessage.toString();
							// Take out the brackets and white space that appear in a ArrayList.
							encodedMessageAsString = encodedMessageAsString.replaceAll("\\[|\\]|\\s+", "");	
							txtAreaOutput.setText(encodedMessageAsString);
						}
					}
				}
			}
		});// END btnEncode ActionListener


		// Decode button listener.
		btnDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					// String keywordAsString = txtKeyword.getText();
					char[] passwordArr = passwordField.getPassword();
					String password = new String(passwordArr);

					if (password.length()<5 || password.length()>255)
					{
						JOptionPane.showMessageDialog(null, "Keyword must be greater then five and less then 255 characters long. Please try again.");
					}
					else { // OK to process
						if (rdbtnFileMode.isSelected()) { // File Mode selected	
							if (txtFilePath.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Please enter a file location and try again.");
							}
							else { // OK to process further.
								// Create a new CipherFileReader object.
								CipherFileReader ciyReader = new CipherFileReader();
								// ArrayList to store the read file.
								ArrayList<Integer> encodedMessageFile = new ArrayList<Integer>(0);
								try { // Read the message from the file.
									encodedMessageFile = ciyReader.readEncodedMsgFile(txtFilePath.getText());
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								// Create ciphergrid object.
								Ciphergrid ciphergrid = new Ciphergrid();
								String decodedMessageFile = "";
								// Send the encoded message file to the decode method and store 
								// the resulting String in the decodedMessageFile String.
								decodedMessageFile = ciphergrid.decode(password, encodedMessageFile);
								boolean GUI_Debug2 = false;
								if (GUI_Debug2) { System.out.println("decodedMessageFile = " + decodedMessageFile); }

								// Create a CipherFileWriter to write the decoded message to file.
								CipherFileWriter ciyWriter = new CipherFileWriter();
								try {
									ciyWriter.writeDecodedMsgToFile(decodedMessageFile, txtFilePath.getText());
								} catch (IOException e1) {
									e1.printStackTrace();
								}

								// Add "_decoded.txt" to the end of the file name and display it in the JTextField.
								String pathToDecodedFile = "";
								pathToDecodedFile = txtFilePath.getText().substring(0, txtFilePath.getText().lastIndexOf("_encoded.txt"));
								pathToDecodedFile = pathToDecodedFile.concat("_decoded.txt");
								txtEncodedFileName.setText("");
								txtEncodedFileName.setText(pathToDecodedFile);
							}
						}
					}// END File Mode Decode.

					// Begin Quick Mode Decode.
					if (rdbtnQuickMode.isSelected()) {
						if (txtAreaOutput.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please encode a message first, then try again.");
						}
						else {
							String messageToDecode = txtAreaOutput.getText();
							Message message = new Message();
							ArrayList<Integer> messageToDecodeArr = new ArrayList<Integer>(0);
							// Convert message to Integer ArrayList.
							messageToDecodeArr = message.quickMessageDecode(messageToDecode);
							Ciphergrid ciphergrid = new Ciphergrid();
							// Pass the messageToDecodeArr ArrayList the be decoded.
							String decodeMessage = "";
							decodeMessage = ciphergrid.decode(password, messageToDecodeArr);
							// Trim off any end of string padding characters.
							decodeMessage = decodeMessage.replaceAll("Ý", "");
							txtAreaOutput.setText(decodeMessage);
						}
					}

				}//end try
				catch (Exception e5) {

				}
			}
		});// END btnDecode ActionListener	

	}// END initialize()
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
} // END CLASS
package com.vismark.Humanity.panel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserRegistrationPanel extends JPanel {

	//TODO: ADD FUNCTIONALITY SO THAT WHEN THE USER HAS CONNECTED, AND DECIDES TO disconnect.
	
	
	//Logger for debugging purposes:
	private static final Logger LOGGER = Logger.getLogger(UserRegistrationPanel.class.getName());
	
	private JLabel fullNameLabel;
	private JTextField fullNameTextField;

	private JLabel userNameLabel;
	private JTextField userNameTextField;

	private JLabel hostAddressLabel;
	private JTextField hostAddressTextField;

	private JLabel portLabel;
	private JTextField portTextField;

	private JButton connectionButton;
	
	//Networking components
	private int portNumber;
	private String host;
	//private static ServerConnection connectionToServer;
	private static Socket connectionToServer;
	private String fullName;
	private String userName;

	// Default constructor
	public UserRegistrationPanel() {
		setupUserRegistrationPanel();
	}

	// method initialized the default components of the UserRegistrationPanel
	public void setupUserRegistrationPanel() {

		// panel settings
		setLayout(new FlowLayout());
		setSize(550, 100);

		// components and their configurations
		// Full name JLabel and JTextField
		fullNameLabel = new JLabel("Full Name:");
		fullNameTextField = new JTextField();
		fullNameTextField.setColumns(10); // length of the JTextField

		add(fullNameLabel);
		add(fullNameTextField);

		// UserName JLabel and JTextField
		userNameLabel = new JLabel("Username:");
		userNameTextField = new JTextField();
		userNameTextField.setColumns(10);

		add(userNameLabel);
		add(userNameTextField);

		// HostIp JLabel and JTextField
		hostAddressLabel = new JLabel("Host IP:");
		hostAddressTextField = new JTextField();
		hostAddressTextField.setColumns(10);

		add(hostAddressLabel);
		add(hostAddressTextField);

		// Port # JLabel and JTextField
		portLabel = new JLabel("Port:");
		portTextField = new JTextField();
		portTextField.setColumns(4);

		add(portLabel);
		add(portTextField);

		// Connection JButton
		connectionButton = new JButton("Connect");
		add(connectionButton);
	}

	public void validateAllUserInput() {
	
		// revert background of all fields to white, if appliclable (this will execute
		// when re-trying after exception
		revertBackgroundColors();

		try {
			
			validateEachInputField();
			
			//If validation is successful, store data entered by user:
			storeInput();
			
		} 
		catch (ValidationFailedException e) {
			
			//TODO: GUI simply halts execution.  Invalid fields are highlighted in red, suggesting to the user
			//that he/she needs to try again.
		}

	}
	

	public void validateEachInputField() throws ValidationFailedException {
		
		boolean allInputValuesPassedValidation = true;
		
		if (fullNameTextField.getText().length() < 2) {
			// full name JTextField is invalid
			fullNameTextField.setBackground(Color.RED);
			
			//allInputValuesPassedValidation fails
			allInputValuesPassedValidation = false;
		}
		if (userNameTextField.getText().length() == 0) {
			// username JtextField value is invalid
			userNameTextField.setBackground(Color.RED);
			
			//allInputValuesPassedValidation fails
			allInputValuesPassedValidation = false;
		}
		if (hostAddressTextField.getText().length() < 7) {
			// hostAddress JtextField value is invalid
			hostAddressTextField.setBackground(Color.RED);
			
			//allInputValuesPassedValidation fails
			allInputValuesPassedValidation = false;
		}
		if (Integer.parseInt((portTextField.getText())) < 1) {
			// port JtextField value is invalid
			portTextField.setBackground(Color.RED);
			
			//allInputValuesPassedValidation fails
			allInputValuesPassedValidation = false;
		}
		
		if(!allInputValuesPassedValidation)
			throw new ValidationFailedException();
		
		
	}
	
	public void storeInput() {
		
		setHost(this.getHostAddressTextField().getText());
		setPortNumber(Integer.parseInt(this.getPortTextField().getText()));
		setFullName(this.getFullNameTextField().getText());
		setUserName(this.getUserNameTextField().getText());
		
		LOGGER.log(Level.INFO, "stored port #: " + this.getPortNumber());
		LOGGER.log(Level.INFO, "stored hostname: " + this.getHost());
		LOGGER.log(Level.INFO, "stored fullName: " + this.getFullName());
		LOGGER.log(Level.INFO, "stored userName: " + this.getUserName());
	}

	public class ValidationFailedException extends Exception {

		public ValidationFailedException() {
			System.out.println("inside of the validationfailedexception");
		}

	}
	
	public class ConnectionFailedException extends Exception {
		
		public ConnectionFailedException() {
			//set portTextField and hostnametextField to red in color
			portTextField.setBackground(Color.RED);
			hostAddressTextField.setBackground(Color.RED);
			System.out.println("inside of the connectionfailedexception");
		}
	}
	

	public void revertBackgroundColors() {
		fullNameTextField.setBackground(Color.white);
		userNameTextField.setBackground(Color.white);
		hostAddressTextField.setBackground(Color.white);
		portTextField.setBackground(Color.white);
	}
	
	
	public void attemptConnectionToServer() throws ConnectionFailedException {

		// take hostname and port number provided by user, and attempt to make a
		// connection to it.
		// if connection fails, a ConnectionFailedException error will be thrown.

		
		//connectionToServer.start();

//			if (!connectionToServer.getConnectionToServer().isConnected())
//				throw new ConnectionFailedException();
	
	}
	
	
	public void turnAllPanelItemsIndeditable() {
		//now that a connection to the server has successfully been made, 
		//we want to make all fields in the panel ineditable, 
		fullNameTextField.setEditable(false);
		userNameTextField.setEditable(false);
		hostAddressTextField.setEditable(false);
		portTextField.setEditable(false);
	}
	
	public void changeTextOnConnectionButton() {
		this.getConnectionButton().setText("Disconnect");
	}
	
	public JLabel getFullNameLabel() {
		return fullNameLabel;
	}

	public JTextField getFullNameTextField() {
		return fullNameTextField;
	}

	public JLabel getUserNameLabel() {
		return userNameLabel;
	}

	public JTextField getUserNameTextField() {
		return userNameTextField;
	}

	public JLabel getHostAddressLabel() {
		return hostAddressLabel;
	}

	public JTextField getHostAddressTextField() {
		return hostAddressTextField;
	}

	public JLabel getPortLabel() {
		return portLabel;
	}

	public JTextField getPortTextField() {
		return portTextField;
	}

	public JButton getConnectionButton() {
		return connectionButton;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public String getHost() {
		return host;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUserName() {
		return userName;
	}

//	public static ServerConnection getConnectionToServer() {
//		return connectionToServer;
//	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setFullNameLabel(JLabel fullNameLabel) {
		this.fullNameLabel = fullNameLabel;
	}

	public void setFullNameTextField(JTextField fullNameTextField) {
		this.fullNameTextField = fullNameTextField;
	}

	public void setUserNameLabel(JLabel userNameLabel) {
		this.userNameLabel = userNameLabel;
	}

	public void setUserNameTextField(JTextField userNameTextField) {
		this.userNameTextField = userNameTextField;
	}

	public void setHostAddressLabel(JLabel hostAddressLabel) {
		this.hostAddressLabel = hostAddressLabel;
	}

	public void setHostAddressTextField(JTextField hostAddressTextField) {
		this.hostAddressTextField = hostAddressTextField;
	}

	public void setPortLabel(JLabel portLabel) {
		this.portLabel = portLabel;
	}

	public void setPortTextField(JTextField portTextField) {
		this.portTextField = portTextField;
	}

	public void setConnectionButton(JButton connectionButton) {
		this.connectionButton = connectionButton;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public void setHost(String host) {
		this.host = host;
	}

}

	
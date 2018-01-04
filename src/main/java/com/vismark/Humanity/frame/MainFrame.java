package com.vismark.Humanity.frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.vismark.Humanity.panel.ChatAreaPanel;
import com.vismark.Humanity.panel.ConnectedUsersPanel;
import com.vismark.Humanity.panel.UserInputPanel;
import com.vismark.Humanity.panel.UserRegistrationPanel;

public class MainFrame extends JFrame {

	// all panels needed for chat
	private static final UserRegistrationPanel userRegistrationPanel = new UserRegistrationPanel();
	private PrintWriter writeToServer;
	ConnectedUsersPanel connectedUsersPanel;
	ChatAreaPanel chatAreaPanel;
	UserInputPanel userInputPanel;
	String myUserName;

	// networking components
	private static Socket connectionToServer;
	private static String hostname;
	private static int port;

	public MainFrame(String frameName) {
		super(frameName);
		initializeFrame();

		// JButton configuration
		userRegistrationPanel.getConnectionButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				try {
					userRegistrationPanel.validateAllUserInput();
					myUserName = userRegistrationPanel.getUserName();
					hostname = userRegistrationPanel.getHost();
					hostname = userRegistrationPanel.getHost();
					port = userRegistrationPanel.getPortNumber();
					connectionToServer = new Socket(hostname, port);
					System.out.println("connection to the server was made!!!");
					userRegistrationPanel.turnAllPanelItemsIndeditable();
					userRegistrationPanel.changeTextOnConnectionButton();
					userInputPanel.enableUserInputPanel();
					listenForMessagesFromServer();
					
					connectedUsersPanel.addNewUser(myUserName);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		final JTextArea userInputTextArea = userInputPanel.getUserInputTextArea();

		userInputPanel.getSendButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				// read the input (expected) in the message text area.

				Thread userInputListener = new Thread(new Runnable() {

					public void run() {

						try {
							// only send message if there is an actual message (at least 1 char in length)
							// in message textarea
							writeToServer = new PrintWriter(connectionToServer.getOutputStream(), true);
							JTextArea chatTextArea = chatAreaPanel.getChatRoomMessages();
							
							if (userInputTextArea.getText().length() > 0) {
								String myMessage = userInputTextArea.getText();
								writeToServer.println(myUserName + ": " + myMessage);	
								userInputTextArea.setText(""); //clear input area once msg is sent
							}
						} catch (Exception e) {

						} 
					}
				});
				
				userInputListener.start();
			}

		});

	}

	public void listenForMessagesFromServer() {

		Thread listenerThread = new Thread(new Runnable() {

			public void run() {

				// invoke a thread that just listens for messages from server
				try {
					BufferedReader br = new BufferedReader(new InputStreamReader(connectionToServer.getInputStream()));
					String messageFromServer = "";
					JTextArea chatArea = chatAreaPanel.getChatRoomMessages();

					while (true) {
						messageFromServer = br.readLine();
						chatArea.append(messageFromServer + "\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		
		listenerThread.start();

	}

	public void initializeFrame() {
		this.setSize(900, 500);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// adding user registration panel to the very top of the MainFrame
		this.add(userRegistrationPanel, BorderLayout.NORTH);

		// adding the list of connected users
		connectedUsersPanel = new ConnectedUsersPanel();
		add(connectedUsersPanel, BorderLayout.EAST);

		// adding chat-room JTextArea
		chatAreaPanel = new ChatAreaPanel();
		add(chatAreaPanel, BorderLayout.CENTER);

		// add user-input panel
		userInputPanel = new UserInputPanel();
		add(userInputPanel, BorderLayout.SOUTH);

		// set JFrame visible
		this.setVisible(true);
	}

}

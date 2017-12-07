package com.vismark.Humanity.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.vismark.Humanity.panel.ChatAreaPanel;
import com.vismark.Humanity.panel.ConnectedUsersPanel;
import com.vismark.Humanity.panel.UserInputPanel;
import com.vismark.Humanity.panel.UserRegistrationPanel;

import javafx.scene.paint.Color;

public class MainFrame extends JFrame {

	public MainFrame(String frameName) {
		super(frameName);
		initializeFrame();
	}

	public void initializeFrame() {
		this.setSize(900, 500);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// adding user registration panel to the very top of the MainFrame
		this.add(new UserRegistrationPanel(), BorderLayout.NORTH);
		
		//adding the list of connected users 
		ConnectedUsersPanel connectedUsersPanel = new ConnectedUsersPanel();
		add(connectedUsersPanel, BorderLayout.EAST);
		
		//adding chat-room JTextArea
		ChatAreaPanel chatAreaPanel = new ChatAreaPanel();
		add(chatAreaPanel, BorderLayout.CENTER);
		
		//add user-input panel
		UserInputPanel userInputPanel = new UserInputPanel();
		add(userInputPanel, BorderLayout.SOUTH);
		
		//set JFrame visible
		this.setVisible(true);

	}

}

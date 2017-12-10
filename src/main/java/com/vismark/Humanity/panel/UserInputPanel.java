package com.vismark.Humanity.panel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UserInputPanel extends JPanel{
	
	private JTextArea userInputTextArea;
	private JScrollPane scrollPane;
	private JButton sendButton;
	
	public UserInputPanel() {
		
		//configure panel settings
		setLayout(new FlowLayout());
		
		//configure user-input JTextArea
		userInputTextArea = new JTextArea(5, 40);
		userInputTextArea.setEditable(false);
		
		//wrap user-input JTextArea into JScollPane
		scrollPane = new JScrollPane(userInputTextArea);
		add(scrollPane);
		
		//configure JButton
		sendButton = new JButton("Send");
		sendButton.setEnabled(false);
		add(sendButton);		
	}
	
	//will be activated once a connection to server has been established
	public void enableUserInputPanel() {
		userInputTextArea.setEditable(true);
		sendButton.setEnabled(true);
	}
	
	public JButton getSendButton() {
		return this.sendButton;
	}
	
	public JTextArea getUserInputTextArea() {
		return userInputTextArea;
	}
	
}

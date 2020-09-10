package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.FeedbackDAOImpl;
import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.models.Feedback;
import com.xlguru.pro1.models.User;

public class AddFeedbackGUI implements ActionListener{
	User user;
	JFrame addFeedback;
	JTextArea tname;
	//JTextField tname,tduration;
	FeedbackDAOImpl dao = new FeedbackDAOImpl();
	JButton b1,b2;
	JPanel p1;
	public AddFeedbackGUI(User user) {
		// TODO Auto-generated constructor stub
		this.user = user;
		addFeedback = new JFrame("Write Your Valuable Feedback");
		tname = new JTextArea();
		//duration = new JLabel("Duration ");
		//tname = new JTextField(30);
		//tduration = new JTextField(30);
		b1 =new JButton("Submit");
		b2 = new JButton("Cancel");
		p1 = new JPanel();
		p1.add(b1);
		p1.add(b2);
		//addFeedback.setLayout(new GridLayout(2,2));
		addFeedback.setSize(200,300);
		addFeedback.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		addFeedback.add(tname,BorderLayout.CENTER);
		//addFeedback.add(aids);
		//addFeedback.add(duration);
		addFeedback.add(p1,BorderLayout.SOUTH);
		
	}
	
	public void clearFields() {
		tname.setText(null);
		//tduration.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String name = tname.getText();
			int duration = user.getUserId();
			Feedback feedback = new Feedback();
			feedback.setStudentId(duration);
			feedback.setFeedbackMsg(name);
			if(dao.addFeedback(feedback)) {
				JOptionPane.showMessageDialog(null, "Feedback added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	/*public static void main(String[] args) {
		AddFeedbackGUI gui = new AddFeedbackGUI();
	}*/
}

package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.xlguru.pro1.models.User;

public class StudentMenuGUI implements ActionListener{
	JFrame f;
	JButton fb,tMod,cMod,mMod,aMod,qMod,cpwd,profile;
	User user;
	
	public void showMenu(User user){
		this.user = user;
		f = new JFrame("Welcome "+user.getUserName());
		/*sMod = new JButton("My Courses");
		tMod = new JButton("My Faculty");
		cMod = new JButton("Course Menu");
		mMod = new JButton("Module Menu");
		aMod = new JButton("Assignment Menu");
		qMod = new JButton("Question Menu");
		*/
		fb = new JButton("Write Feedback");
		cpwd = new JButton("Change Password");
		profile = new JButton("View / Edit Profile");
		f.setLayout(new GridLayout(2,2));
		f.setVisible(true);
		/*f.add(sMod);
		f.add(tMod);
		f.add(mMod);
		f.add(cMod);
		f.add(qMod);
		f.add(aMod);
		*/
		f.add(fb);
		f.add(cpwd);
		f.add(profile);
		cpwd.addActionListener(this);
		profile.addActionListener(this);
		fb.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==cpwd) {
			new ChangePassword(user);
		}
		else if(ae.getSource()==profile) {
			new ChangeProfile(user);
		}
		else if(ae.getSource()==fb) {
			new AddFeedbackGUI(user);
		}
	}
	
}

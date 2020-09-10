package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.xlguru.pro1.models.User;

public class AdminMenuGUI implements ActionListener{
	JFrame f;
	JButton sMod,tMod,bMod,fMod,cMod,mMod,aMod,qMod,cpwd,profile;
	User user=null;
	
	public void showMenu(User user) {
		this.user = user;
		f = new JFrame("Welcome ");//+user.getUserName());
		sMod = new JButton("Student Menu");
		tMod = new JButton("Teacher Menu");
		cMod = new JButton("Course Menu");
		mMod = new JButton("Module Menu");
		aMod = new JButton("Assignment Menu");
		qMod = new JButton("Question Menu");
		fMod = new JButton("Show All Feedbacks");
		bMod = new JButton("Batch Menu");
		cpwd = new JButton("Change Password");
		profile = new JButton("View / Edit Profile");
		sMod.addActionListener(this);
		tMod.addActionListener(this);
		mMod.addActionListener(this);
		cMod.addActionListener(this);
		qMod.addActionListener(this);
		aMod.addActionListener(this);
		fMod.addActionListener(this);
		bMod.addActionListener(this);
		cpwd.addActionListener(this);
		profile.addActionListener(this);
		f.setLayout(new GridLayout(5,2));
		f.setVisible(true);
		f.setSize(300,350);
		f.add(sMod);
		f.add(tMod);
		f.add(mMod);
		f.add(cMod);
		f.add(qMod);
		f.add(aMod);
		f.add(bMod);
		f.add(fMod);
		f.add(cpwd);
		f.add(profile);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==sMod) {
			new StudentMenu();
		}
		else if(ae.getSource()==tMod) {
			new TeacherMenu();
		}
		else if(ae.getSource()==mMod) {
			new ModuleMenu();
		}
		else if(ae.getSource()==cMod) {
			new CourseMenu();
		}
		else if(ae.getSource()==qMod) {
			new QuestionMenu();
		}
		else if(ae.getSource()==aMod) {
			new AssignmentMenu();
		}
		else if(ae.getSource()==cpwd) {
			new ChangePassword(user);
		}
		else if(ae.getSource()==profile) {
			new ChangeProfile(user);
		}
		else if(ae.getSource()==bMod) {
			new BatchMenu();
		}
		else if(ae.getSource()==fMod) {
			new DisplayAllFeedbacks();
		}
		
	}
	
}

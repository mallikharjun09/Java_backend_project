package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StudentMenu implements ActionListener{
	JFrame f;
	JButton add, voe, displayAll,cancel;
	public StudentMenu() {
		f = new JFrame("Student Menu");
		add = new JButton("Add Student");
		voe = new JButton("View or Edit Student");
		cancel = new JButton("Cancel");
		displayAll = new JButton("Display All Students");
		//delete = new JButton("Delete Student");
		//delete.addActionListener(this);
		displayAll.addActionListener(this);
		add.addActionListener(this);
		voe.addActionListener(this);
		cancel.addActionListener(this);
		f.setLayout(new GridLayout(2,2));
		f.setVisible(true);
		f.setSize(200,200);
		f.add(add);
		f.add(voe);
		f.add(displayAll);
		f.add(cancel);
		//f.add(delete);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == add) {
			new AddUserGUI();
			f.setVisible(false);
			
		}
		else if(ae.getSource() == voe) {
			f.setVisible(false);
			new ViewOrEditStudent();
		}
		else if(ae.getSource() == displayAll) {
			new DisplayAllStudents();
			f.setVisible(false);
		}
		else if(ae.getSource() == cancel) {
			f.setVisible(false);
			AdminMenuGUI gui = new AdminMenuGUI();
			gui.f.setVisible(true);
		}
	}
}

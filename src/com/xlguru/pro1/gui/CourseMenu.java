package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CourseMenu implements ActionListener{
	JFrame f;
	JButton add, voe, displayAll, cancel;
	public CourseMenu() {
		f = new JFrame("Course Menu");
		add = new JButton("Add Course");
		voe = new JButton("View or Edit Course");
		cancel = new JButton("Cancel");
		displayAll = new JButton("Display All Courses");
		//delete = new JButton("Delete Course");
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
			f.setVisible(false);
			new AddCourseGUI();
		}
		else if(ae.getSource() == voe) {
			f.setVisible(false);
			new ViewOrEditCourse();
		}
		else if(ae.getSource() == displayAll) {
			f.setVisible(false);
			new DisplayAllCourses();
		}
		else if(ae.getSource() == cancel) {
			f.setVisible(false);
			AdminMenuGUI gui = new AdminMenuGUI();
			gui.f.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		new CourseMenu();
	}
}

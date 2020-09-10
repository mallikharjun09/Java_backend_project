package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.TeacherDAOImpl;
import com.xlguru.pro1.models.Teacher;

public class DisplayAllTeachers {
	JFrame f;
	Object[] teachers=  new TeacherDAOImpl().displayAllUsers().toArray();
	JList<Teacher> teacherList;
	public DisplayAllTeachers() {
		f = new JFrame("All Teachers");
		teacherList = new JList(teachers);
		f.add(new Panel().add(new JScrollPane(teacherList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	
}

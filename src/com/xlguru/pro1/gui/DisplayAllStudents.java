package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.StudentDAOImpl;
import com.xlguru.pro1.models.Student;

public class DisplayAllStudents {
	JFrame f;
	Object[] students=  new StudentDAOImpl().displayAllUsers().toArray();
	JList<Student> studentList;
	public DisplayAllStudents() {
		f = new JFrame("All Students");
		studentList = new JList(students);
		f.add(new Panel().add(new JScrollPane(studentList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	
}

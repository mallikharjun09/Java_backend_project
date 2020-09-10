package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.models.Course;

public class DisplayAllCourses {
	JFrame f;
	Object[] courses=  new CourseDAOImpl().displayAllCourses().toArray();
	JList<Course> courseList;
	public DisplayAllCourses() {
		f = new JFrame("All Courses");
		courseList = new JList(courses);
		f.add(new Panel().add(new JScrollPane(courseList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new DisplayAllCourses();
	}
}

package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.models.Course;

public class AddCourseGUI implements ActionListener{
	JFrame addCourse;
	Object[] mods=  new ModuleDAOImpl().displayAllCourses().toArray();
	JList<Module> tmodules;
	JLabel name,duration,cost,modules;
	JTextField tname,tduration,tcost,tcourseid,tmoduleid;
	CourseDAOImpl dao = new CourseDAOImpl();
	JButton b1,b2;
	public AddCourseGUI() {
		// TODO Auto-generated constructor stub
		addCourse = new JFrame("Add new Course");
		name = new JLabel("Course Name ");
		duration = new JLabel("Duration ");
		cost = new JLabel("Cost");
		modules = new JLabel("Modules");
		tname = new JTextField(30);
		tduration = new JTextField(30);
		tcost = new JTextField(30);
		tmodules = new JList(mods);
		tmodules.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		tmodules.setVisibleRowCount(5);
		addCourse.setLayout(new GridLayout(7,2));
		addCourse.setSize(200,300);
		addCourse.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		addCourse.add(name);
		addCourse.add(tname);
		addCourse.add(duration);
		addCourse.add(tduration);
		addCourse.add(cost);
		addCourse.add(tcost);
		addCourse.add(modules);
		addCourse.add(new Panel().add(new JScrollPane(tmodules)));
		addCourse.add(b1);
		addCourse.add(b2);
		
	}
	
	public void clearFields() {
		tname.setText(null);
		tduration.setText(null);
		tcost.setText(null);
		//tmodules.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String name = tname.getText();
			int duration = Integer.parseInt(tduration.getText());
			float cost = Float.parseFloat(tcost.getText());
			Object indices[]=tmodules.getSelectedValues();
			//String[] modules = tmodules.getText().split(",");
			List<Integer> myList = new LinkedList<Integer>();
			for(Object mod : indices) {
				com.xlguru.pro1.models.Module m = (com.xlguru.pro1.models.Module)mod;
				myList.add(m.getModuleId());
			}
			Course course = new Course();
			course.setCourseDuration(duration);
			course.setCourseName(name);
			course.setCost(cost);
			course.setModules(myList);
			if(dao.addCourse(course)) {
				JOptionPane.showMessageDialog(null, "Course added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	public static void main(String[] args) {
		AddCourseGUI gui = new AddCourseGUI();
	}
}

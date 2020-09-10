package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Course;

public class ViewOrEditCourse implements ActionListener{
	JFrame addCourse;
	JPanel p1,p2;
	JLabel id;
	JTextField tid;
	JButton view,delete;
	
	JList<Module> tmodules;
	JLabel name,duration,cost,modules;
	JTextField tname,tduration,tcost,tcourseid,tmoduleid;
	CourseDAOImpl dao = new CourseDAOImpl();
	ArrayList<String> assignments =dao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox aids = new JComboBox(aid);
	
	JButton b1,b2;
	public ViewOrEditCourse() {
		// TODO Auto-generated constructor stub
		addCourse = new JFrame("Add new Course");
		p1 = new JPanel();
		p2 = new JPanel();
		id = new JLabel("Course Id");
		tid = new JTextField(10);
		view = new JButton("View Course");
		delete = new JButton("Delete Course");
		
		name = new JLabel("Course Name ");
		duration = new JLabel("Duration ");
		cost = new JLabel("Cost");
		modules = new JLabel("Modules");
		tname = new JTextField(30);
		tduration = new JTextField(30);
		tcost = new JTextField(30);
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		p2.setLayout(new GridLayout(7,2));
		p2.setSize(200,300);
		p2.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		p2.add(name);
		p2.add(tname);
		p2.add(duration);
		p2.add(tduration);
		p2.add(cost);
		p2.add(tcost);
		p2.add(modules);
		p2.add(new Panel().add(new JScrollPane(tmodules)));
		p2.add(b1);
		p2.add(b2);
	
		view.addActionListener(this);
		delete.addActionListener(this);
		p1.add(id);
		p1.add(aids);
		p1.add(view);
		p1.add(delete);
		
		addCourse.add(p1,BorderLayout.NORTH);
		addCourse.add(p2,BorderLayout.CENTER);
		addCourse.setVisible(true);
		addCourse.setSize(450,500);
	}
	public void clearFields() {
		tname.setText(null);
		tduration.setText(null);
		tcost.setText(null);
		//tmodules.clearSelection();
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(aids.getSelectedItem().toString());
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
			Course course = dao.displayCourse(id);
			course.setCourseDuration(duration);
			course.setCourseName(name);
			course.setCost(cost);
			course.setModules(myList);
			if(dao.editCourse(course)) {
				JOptionPane.showMessageDialog(null, "Course added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == view) {
			Course course = dao.displayCourse(Integer.parseInt(tid.getText()));
			tname.setText(course.getCourseName());
			tduration.setText(""+course.getCourseDuration());
			tcost.setText(""+course.getCost());
			Object[] mods=  new ModuleDAOImpl().displayAllCourses().toArray();
			tmodules = new JList(mods);
			tmodules.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tmodules.setVisibleRowCount(5);
			
		}
		else if(ae.getSource() == delete) {
			if(dao.deleteCourse((Integer.parseInt(tid.getText())))) {
				JOptionPane.showMessageDialog(null, "Course deleted successfully");
			}
		}
		
	}
	public static void main(String[] args) {
		new ViewOrEditCourse();
	}
}

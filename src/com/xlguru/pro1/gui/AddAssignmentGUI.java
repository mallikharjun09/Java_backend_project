package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.xlguru.pro1.dao.AssignmentDAOImpl;
import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Assignment;
import com.xlguru.pro1.models.Course;
import com.xlguru.pro1.models.Question;

public class AddAssignmentGUI implements ActionListener, FocusListener{
	JFrame addCourse;
	JLabel name,duration,questions,cid,mid;
	JTextField tname,tduration,tcid,tmid;
	JList<Question> tmodules;
	Panel p1,p2,p3;
	AssignmentDAOImpl dao = new AssignmentDAOImpl();
	JButton b1,b2;
	public AddAssignmentGUI() {
		// TODO Auto-generated constructor stub
		addCourse = new JFrame("Create an Assignment");
		name = new JLabel("Title ");
		duration = new JLabel("Duration ");
		cid = new JLabel("Course Id ");
		mid = new JLabel("Module Id");
		questions = new JLabel("Qustions");
		tname = new JTextField(30);
		tcid = new JTextField(30);
		tmid = new JTextField(30);
		tduration = new JTextField(30);
		p1 = new Panel();
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		p3 = new Panel();
		p2 = new Panel();
		p2.setVisible(false);
		tmid.addFocusListener(this);
		p1.setLayout(new GridLayout(4,2));
		addCourse.setLayout(new GridLayout(3,1));
		addCourse.setSize(200,300);
		addCourse.setVisible(true);
		p2.setLayout(new GridLayout(1,2));
		p3.setLayout(new GridLayout(1,2));
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		p1.add(name);
		p1.add(tname);
		p1.add(duration);
		p1.add(tduration);
		p1.add(cid);
		p1.add(tcid);
		p1.add(mid);
		p1.add(tmid);
		
		p3.add(b1);
		p3.add(b2);
		
		addCourse.add(p1);
		addCourse.add(p2);
		addCourse.add(p3);
		
	}
	
	public void clearFields() {
		tname.setText(null);
		tduration.setText(null);
		tcid.setText(null);
		tmid.setText(null);
		p2.setVisible(false);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String name = tname.getText();
			int duration = Integer.parseInt(tduration.getText());
			Assignment assignment = new Assignment();
			assignment.setTitle(name);
			assignment.setDuration(duration);
			Object indices[]=tmodules.getSelectedValues();
			//String[] modules = tmodules.getText().split(",");
			List<Integer> myList = new LinkedList<Integer>();
			for(Object mod : indices) {
				System.out.println(mod);
				Question m = (Question)mod;
				myList.add(m.getQuestionId());
			}
			assignment.setQuestions(myList);
			if(dao.addAssignment(assignment)) {
				JOptionPane.showMessageDialog(null, "Assignment added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	public static void main(String[] args) {
		AddAssignmentGUI gui = new AddAssignmentGUI();
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void focusLost(FocusEvent fe) {
		// TODO Auto-generated method stub
		p2.removeAll();
		int courseid = Integer.parseInt(tcid.getText());
		int moduleid = Integer.parseInt(tmid.getText());
		Object[] mods=  new QuestionDAOImpl().displayAllQuestions(courseid, moduleid).toArray();
		tmodules = new JList(mods);
		tmodules.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tmodules.setVisibleRowCount(5);
		p2.add(questions);
		p2.add(new JScrollPane(tmodules));
		p2.setVisible(true);
	}
}


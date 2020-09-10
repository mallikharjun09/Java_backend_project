package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
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

import com.xlguru.pro1.dao.AssignmentDAOImpl;
import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Assignment;
import com.xlguru.pro1.models.Course;
import com.xlguru.pro1.models.Question;

public class ViewOrEditAssignment implements ActionListener{
	JFrame addAssignment; 
	JPanel p4,p5;
	JLabel id;
	JTextField tid;
	JButton view,delete;
	JLabel name,duration,questions,cid,mid;
	JTextField tname,tduration,tcid,tmid;
	JList<Question> tmodules;
	Panel p1,p2,p3;
	AssignmentDAOImpl dao = new AssignmentDAOImpl();
	ArrayList<String> assignments =dao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox aids = new JComboBox(aid);
	JButton b1,b2;
	public ViewOrEditAssignment() {
		// TODO Auto-generated constructor stub
		addAssignment = new JFrame("View or Edit an Assignment");
		p4 = new JPanel();
		p5 = new JPanel();
		id = new JLabel("Assignment Id");
		tid = new JTextField(10);
		view = new JButton("View Assignment");
		delete = new JButton("Delete Assignment");
		
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
		p2.setVisible(true);
		//tmid.addFocusListener(this);
		p1.setLayout(new GridLayout(4,2));
		p5.setLayout(new GridLayout(3,1));
		p5.setSize(200,300);
		p5.setVisible(true);
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
		p2.add(questions);
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
	
		view.addActionListener(this);
		delete.addActionListener(this);
		p4.add(id);
		p4.add(aids);
		p4.add(view);
		p4.add(delete);
		
		addAssignment.add(p4,BorderLayout.NORTH);
		addAssignment.add(p5,BorderLayout.CENTER);
		addAssignment.setVisible(true);
		addAssignment.setSize(450,500);
	}
	public void clearFields() {
		tname.setText(null);
		tduration.setText(null);
		tcid.setText(null);
		tmid.setText(null);
		p2.setVisible(false);
		tid.setText(null);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(aids.getSelectedItem().toString());
		if(ae.getSource() == b1) {
			String name = tname.getText();
			int duration = Integer.parseInt(tduration.getText());
			Assignment assignment = dao.displayAssignment(id);
			assignment.setTitle(name);
			assignment.setDuration(duration);
			Object indices[]=tmodules.getSelectedValues();
			//String[] modules = tmodules.getText().split(",");
			List<Integer> myList = new LinkedList<Integer>();
			for(Object mod : indices) {
				Question m = (Question)mod;
				myList.add(m.getQuestionId());
			}
			assignment.setQuestions(myList);
			if(dao.editAssignment(assignment)) {
				JOptionPane.showMessageDialog(null, "Assignment edited successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == view) {
			Assignment assignment = dao.displayAssignment(id);
			tname.setText(assignment.getTitle());
			tduration.setText(""+assignment.getDuration());
			Question q = new QuestionDAOImpl().displayQuestion(assignment.getQuestions().get(0));
			tcid.setText(""+q.getCourseId());
			tmid.setText(""+q.getModuleId());
			int courseid = Integer.parseInt(tcid.getText());
			int moduleid = Integer.parseInt(tmid.getText());
			Object[] mods=  new QuestionDAOImpl().displayAllQuestions(courseid, moduleid).toArray();
			tmodules = new JList(mods);
			tmodules.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			tmodules.setVisibleRowCount(5);
			p2.add(new JScrollPane(tmodules));
		}
		else if(ae.getSource() == delete) {
			if(dao.deleteAssignment(id)) {
				JOptionPane.showMessageDialog(null, "Assignment deleted successfully");
				clearFields();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		new ViewOrEditAssignment();
	}
}

package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.AssignmentDAOImpl;
import com.xlguru.pro1.models.Assignment;

public class DisplayAllAssignments {
	JFrame f;
	Object[] assignments=  new AssignmentDAOImpl().displayAllAssignments().toArray();
	JList<Assignment> assignmentList;
	public DisplayAllAssignments() {
		f = new JFrame("All Assignments");
		assignmentList = new JList(assignments);
		f.add(new Panel().add(new JScrollPane(assignmentList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new DisplayAllAssignments();
	}
}

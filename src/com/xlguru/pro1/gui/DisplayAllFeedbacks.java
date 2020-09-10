package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.FeedbackDAOImpl;
import com.xlguru.pro1.models.Feedback;

public class DisplayAllFeedbacks {
	JFrame f;
	Object[] feedbacks=  new FeedbackDAOImpl().displayAllCourses().toArray();
	JList<Feedback> feedbackList;
	public DisplayAllFeedbacks() {
		f = new JFrame("All Feedbacks");
		feedbackList = new JList(feedbacks);
		f.add(new Panel().add(new JScrollPane(feedbackList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}	
}

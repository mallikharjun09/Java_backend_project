package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Question;

public class DisplayAllQuestions {
	JFrame f;
	Object[] questions=  new QuestionDAOImpl().displayAllQuestions().toArray();
	JList<Question> questionList;
	public DisplayAllQuestions() {
		f = new JFrame("All Questions");
		questionList = new JList(questions);
		f.add(new Panel().add(new JScrollPane(questionList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new DisplayAllQuestions();
	}
	
}

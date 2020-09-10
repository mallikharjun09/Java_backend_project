package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class QuestionMenu implements ActionListener{
	JFrame f;
	JButton add, voe, displayAll, cancel;
	public QuestionMenu() {
		f = new JFrame("Question Menu");
		add = new JButton("Add Question");
		voe = new JButton("View or Edit Question");
		cancel = new JButton("cancel");
		displayAll = new JButton("Display All Questions");
		//delete = new JButton("Delete Question");
		//delete.addActionListener(this);
		displayAll.addActionListener(this);
		add.addActionListener(this);
		voe.addActionListener(this);
		cancel.addActionListener(this);
		f.setLayout(new GridLayout(2,2));
		f.setVisible(true);
		f.setSize(200,200);
		f.add(add);
		f.add(voe);
		f.add(displayAll);
		f.add(cancel);
		//f.add(delete);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == add) {
			f.setVisible(false);
			new AddQuestionGUI();
		}
		else if(ae.getSource() == voe) {
			f.setVisible(false);
			new ViewOrEditQuestion();
		}
		else if(ae.getSource() == displayAll) {
			f.setVisible(false);
			new DisplayAllQuestions();
		}
		else if(ae.getSource() == cancel) {
			f.setVisible(false);
			AdminMenuGUI gui = new AdminMenuGUI();
			gui.f.setVisible(true);
		}
	}
}

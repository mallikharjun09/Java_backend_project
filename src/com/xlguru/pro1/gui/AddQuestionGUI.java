package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Question;

public class AddQuestionGUI implements ActionListener{
	JFrame addQuestion;
	JLabel cid,mid,desc,opt1,opt2,opt3,opt4,ans;
	JTextField tdesc,topt1,topt2,topt3,topt4,tans;
	QuestionDAOImpl dao = new QuestionDAOImpl();
	CourseDAOImpl cdao = new CourseDAOImpl();
	ArrayList<String> assignments =cdao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox tcid = new JComboBox(aid);
	ModuleDAOImpl mdao = new ModuleDAOImpl();
	ArrayList<String> mods =dao.displayIds();
	Object[] mids = mods.toArray();
	JComboBox tmid = new JComboBox(mids);
	
	JButton b1,b2;
	public AddQuestionGUI() {
		// TODO Auto-generated constructor stub
		addQuestion = new JFrame("Add new Question");
		cid = new JLabel("Course Id ");
		mid = new JLabel("Module Id ");
		desc = new JLabel("Description");
		opt1 = new JLabel("Option 1");
		opt2 = new JLabel("Option 2");
		opt3 = new JLabel("Option 3");
		opt4 = new JLabel("Option 4");
		ans = new JLabel("Answer");
		
		//tcid = new JTextField(30);
		//tmid = new JTextField(30);
		tdesc = new JTextField(30);
		topt1 = new JTextField(30);
		topt2 = new JTextField(30);
		topt3 = new JTextField(30);
		topt4 = new JTextField(30);
		tans = new JTextField(30);
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		
		addQuestion.setLayout(new GridLayout(9,2));
		addQuestion.setSize(200,300);
		addQuestion.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		addQuestion.add(cid);
		addQuestion.add(tcid);
		addQuestion.add(mid);
		addQuestion.add(tmid);
		addQuestion.add(desc);
		addQuestion.add(tdesc);
		addQuestion.add(opt1);
		addQuestion.add(topt1);
		addQuestion.add(opt2);
		addQuestion.add(topt2);
		addQuestion.add(opt3);
		addQuestion.add(topt3);
		addQuestion.add(opt4);
		addQuestion.add(topt4);
		addQuestion.add(ans);
		addQuestion.add(tans);
		addQuestion.add(b1);
		addQuestion.add(b2);
		
	}
	
	public void clearFields() {
		//tmid.setText(null);
		tdesc.setText(null);
		topt1.setText(null);
		topt2.setText(null);
		topt3.setText(null);
		topt4.setText(null);
		tans.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String desc = tdesc.getText();
			String opt3 = topt3.getText();
			String opt2 = topt2.getText();
			String opt1 = topt1.getText();
			String opt4 = topt4.getText();
			String ans = tans.getText();
			int cid = Integer.parseInt(tcid.getSelectedItem().toString());
			int mid = Integer.parseInt(tmid.getSelectedItem().toString());
			Question question = new Question();
			question.setAnswer(ans);
			question.setCourseId(cid);
			question.setDescription(desc);
			question.setModuleId(mid);
			question.setOption1(opt1);
			question.setOption2(opt2);
			question.setOption3(opt3);
			question.setOption4(opt4);
			
			if(dao.addQuestion(question)) {
				JOptionPane.showMessageDialog(null, "Question added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	public static void main(String[] args) {
		AddQuestionGUI gui = new AddQuestionGUI();
	}
}

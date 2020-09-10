package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Question;

public class ViewOrEditQuestion implements ActionListener{
	JFrame addQuestion;
	JPanel p1,p2;
	JLabel id;
	JTextField tid;
	JButton view,delete;
	JLabel cid,mid,desc,opt1,opt2,opt3,opt4,ans;
	JTextField tcid,tmid,tdesc,topt1,topt2,topt3,topt4,tans;
	QuestionDAOImpl dao = new QuestionDAOImpl();
	ArrayList<String> assignments =dao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox aids = new JComboBox(aid);
	
	JButton b1,b2;
	public ViewOrEditQuestion() {
		// TODO Auto-generated constructor stub
		addQuestion = new JFrame("Add new Question");
		p1 = new JPanel();
		p2 = new JPanel();
		id = new JLabel("Question Id");
		tid = new JTextField(10);
		view = new JButton("View Question");
		delete = new JButton("Delete Question");
		cid = new JLabel("Course Id ");
		mid = new JLabel("Module Id ");
		desc = new JLabel("Description");
		opt1 = new JLabel("Option 1");
		opt2 = new JLabel("Option 2");
		opt3 = new JLabel("Option 3");
		opt4 = new JLabel("Option 4");
		ans = new JLabel("Answer");
		
		tcid = new JTextField(30);
		tmid = new JTextField(30);
		tdesc = new JTextField(30);
		topt1 = new JTextField(30);
		topt2 = new JTextField(30);
		topt3 = new JTextField(30);
		topt4 = new JTextField(30);
		tans = new JTextField(30);
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		
		p2.setLayout(new GridLayout(9,2));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		p2.add(cid);
		p2.add(tcid);
		p2.add(mid);
		p2.add(tmid);
		p2.add(desc);
		p2.add(tdesc);
		p2.add(opt1);
		p2.add(topt1);
		p2.add(opt2);
		p2.add(topt2);
		p2.add(opt3);
		p2.add(topt3);
		p2.add(opt4);
		p2.add(topt4);
		p2.add(ans);
		p2.add(tans);
		addQuestion.setSize(450,300);
		addQuestion.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		view.addActionListener(this);
		delete.addActionListener(this);
		p1.add(id);
		p1.add(aids);
		p1.add(view);
		p1.add(delete);
		p2.add(b1);
		p2.add(b2);
		
		addQuestion.add(p1,BorderLayout.NORTH);
		addQuestion.add(p2,BorderLayout.CENTER);
		
	}
	public void clearFields() {
		tcid.setText(null);
		tmid.setText(null);
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
		int id = Integer.parseInt(aids.getSelectedItem().toString());
		if(ae.getSource() == b1) {
			String desc = tdesc.getText();
			String opt3 = topt3.getText();
			String opt2 = topt2.getText();
			String opt1 = topt1.getText();
			String opt4 = topt4.getText();
			String ans = tans.getText();
			int cid = Integer.parseInt(tcid.getText());
			int mid = Integer.parseInt(tmid.getText());
			Question question = dao.displayQuestion(id);
			question.setAnswer(ans);
			question.setCourseId(cid);
			question.setDescription(desc);
			question.setModuleId(mid);
			question.setOption1(opt1);
			question.setOption2(opt2);
			question.setOption3(opt3);
			question.setOption4(opt4);
			
			if(dao.editQuestion(question)) {
				JOptionPane.showMessageDialog(null, "Question updated successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == view) {
			Question q = dao.displayQuestion(id);
			tans.setText(q.getAnswer());
			tcid.setText(""+q.getCourseId());
			tdesc.setText(""+q.getDescription());
			tmid.setText(""+q.getModuleId());
			topt1.setText(""+q.getOption1());
			topt2.setText(""+q.getOption2());
			topt3.setText(""+q.getOption3());
			topt4.setText(""+q.getOption4());
			topt1.setText(""+q.getOption1());
			//t.setText(""+q.getType_of_question());
			
		}
		else if(ae.getSource() == delete) {
			if(dao.deleteQuestion(id)) {
				JOptionPane.showMessageDialog(null, "Question deleted successfully");
			}
		}
		
	}
	/*public static void main(String[] args) {
		new ViewOrEditQuestion();
	}*/
}

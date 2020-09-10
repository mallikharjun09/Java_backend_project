package com.xlguru.pro1.gui;

import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.xlguru.pro1.dao.AssignmentDAOImpl;
import com.xlguru.pro1.dao.StudentDAOImpl;
import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.dao.QuestionDAOImpl;
import com.xlguru.pro1.models.Student;

import javafx.scene.control.RadioButton;

public class AddUserGUI implements ActionListener{
	JFrame addStudent;
	JLabel name,gender,role,password,qualification,mail,phone;
	JTextField tname,tqualification,tmail,tphone;
	JPasswordField tpassword;
	JRadioButton male,female;
	JComboBox trole;
	ButtonGroup cg;
	Panel p1;
	UserDAOImpl dao = new UserDAOImpl();
	JButton b1,b2;
	public AddUserGUI() {
		// TODO Auto-generated constructor stub
		addStudent = new JFrame("Register new User");
		name = new JLabel("Full Name ");
		gender = new JLabel("Gender ");
		password = new JLabel("Password ");
		qualification = new JLabel("Qualification ");
		mail = new JLabel("Mail Id ");
		phone = new JLabel("Phone Number ");
		tname = new JTextField();
		tqualification = new JTextField();
		tpassword = new JPasswordField();
		tmail = new JTextField();
		tphone = new JTextField();
		male = new JRadioButton("Male");
		female = new JRadioButton("Female");
		role = new JLabel("Role");
		String roles[] = {"Select Role...","Student","Teacher","Admin"};
		trole = new JComboBox(roles);
		cg = new ButtonGroup();
		cg.add(male);
		cg.add(female);
		p1 = new Panel();
		b1 = new JButton("Submit");
		b2 = new JButton("Cancel");
		b1.addActionListener(this);
		b2.addActionListener(this);
		p1.add(male);
		p1.add(female);
		addStudent.setLayout(new GridLayout(8,2));
		addStudent.setSize(300,350);
		addStudent.setVisible(true);
		addStudent.add(name);
		addStudent.add(tname);
		addStudent.add(mail);
		addStudent.add(tmail);
		addStudent.add(phone);
		addStudent.add(tphone);
		addStudent.add(qualification);
		addStudent.add(tqualification);
		addStudent.add(gender);
		addStudent.add(p1);
		addStudent.add(role);
		addStudent.add(trole);
		addStudent.add(password);
		addStudent.add(tpassword);
		addStudent.add(b1);
		addStudent.add(b2);
		
	}
	
	public void clearFields() {
		tname.setText(null);
		cg.clearSelection();
		tmail.setText(null);
		tpassword.setText(null);
		tphone.setText(null);
		tqualification.setText(null);
		trole.setSelectedIndex(0);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String gender="";
			if(male.isSelected()) {
				gender = "Male";
			}
			else if(female.isSelected()) {
				gender = "Female";
			}
			long phone = Long.parseLong(tphone.getText());
			String name = tname.getText();
			String mail = tmail.getText();
			String pwd = tpassword.getText();
			String qualification = tqualification.getText();
			String role = trole.getSelectedItem().toString();
			Student st = new Student();
			st.setCurrentQualification(qualification);
			st.setGender(gender);
			st.setMail(mail);
			st.setPassword(pwd);
			st.setPhone(phone);
			st.setRole(role);
			st.setUserName(name);
			if(dao.addUser(st)) {
				JOptionPane.showMessageDialog(null, "Registered Successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	/*public static void main(String[] args) {
		AddUserGUI gui = new AddUserGUI();
		gui.addUserGUI();
	}*/

}


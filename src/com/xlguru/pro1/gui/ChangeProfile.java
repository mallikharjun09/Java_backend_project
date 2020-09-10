package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.models.Student;
import com.xlguru.pro1.models.User;

public class ChangeProfile implements ActionListener{
	User user;
	JFrame addStudent;
	JLabel name,gender,role,qualification,mail,phone;
	JTextField tname,tqualification,tmail,tphone;
	
	//JRadioButton male,female;
	JTextField trole,tgender;
	Panel p1;
	UserDAOImpl dao = new UserDAOImpl();
	JButton b1,b2;
	
	public ChangeProfile(User user) {
		this.user = user;
		addStudent = new JFrame("Update user");
		name = new JLabel("Full Name ");
		gender = new JLabel("Gender ");
		qualification = new JLabel("Qualification ");
		mail = new JLabel("Mail Id ");
		phone = new JLabel("Phone Number ");
		tname = new JTextField();
		tqualification = new JTextField();
		tmail = new JTextField();
		tphone = new JTextField();
		tgender = new JTextField();
		role = new JLabel("Role");
		trole = new JTextField();
		b1 = new JButton("Update");
		b2 = new JButton("Cancel");
		b1.addActionListener(this);
		b2.addActionListener(this);
		addStudent.setLayout(new GridLayout(7,2));
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
		addStudent.add(tgender);
		addStudent.add(role);
		addStudent.add(trole);
		addStudent.add(b1);
		addStudent.add(b2);

		tgender.setText(user.getGender());
		tqualification.setText(user.getCurrentQualification());
		tmail.setText(user.getMail());
		tname.setText(user.getUserName());
		tphone.setText(""+user.getPhone());
		trole.setText(user.getRole());
		trole.disable();		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==b1) {
			
			String gender=tgender.getText();
			long phone = Long.parseLong(tphone.getText());
			String name = tname.getText();
			String mail = tmail.getText();
			//String pwd = tpassword.getText();
			String qualification = tqualification.getText();
			String role = trole.getText();
			user.setCurrentQualification(qualification);
			//st.setUserId(id);
			user.setGender(gender);
			user.setMail(mail);
			//st.setPassword(pwd);
			user.setPhone(phone);
			user.setRole(role);
			user.setUserName(name);
			if(dao.editUser(user)) {
				JOptionPane.showMessageDialog(null, "User edited successfully");
			}
		}
	}
}

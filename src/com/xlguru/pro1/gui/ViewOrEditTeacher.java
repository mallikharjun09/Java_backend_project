package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.models.Module;
import com.xlguru.pro1.models.User;

public class ViewOrEditTeacher implements ActionListener{
	JFrame addUser;
	JPanel p2,p3;
	JLabel id;
	JTextField tid;
	JButton view,delete;
	JLabel name,gender,role,password,qualification,mail,phone;
	JTextField tname,tqualification,tmail,tphone;
	JPasswordField tpassword;
	JRadioButton male,female;
	JTextField trole;
	ButtonGroup cg;
	Panel p1;
	UserDAOImpl dao = new UserDAOImpl();
	ArrayList<String> assignments =dao.displayTeacherIds();
	Object[] aid = assignments.toArray();
	JComboBox aids = new JComboBox(aid);
	
	JButton b1,b2;
	public ViewOrEditTeacher() {
		// TODO Auto-generated constructor stub
		addUser = new JFrame("update User");
		p3 = new JPanel();
		p2 = new JPanel();
		id = new JLabel("User Id");
		tid = new JTextField(10);
		view = new JButton("View User");
		delete = new JButton("Delete User");
		
		addUser.setSize(450,300);
		addUser.setVisible(true);
		b1 = new JButton("Update");
		b2 = new JButton("Cancel");
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
		//String roles[] = {"Select Role...","Student","Teacher","Admin"};
		trole = new JTextField();
		trole.disable();
		cg = new ButtonGroup();
		cg.add(male);
		cg.add(female);
		p1 = new Panel();
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		view.addActionListener(this);
		delete.addActionListener(this);
		p2.add(id);
		p2.add(aids);
		p2.add(view);
		p2.add(delete);
	
		p1.add(male);
		p1.add(female);
		p3.setLayout(new GridLayout(7,2));
		p3.setSize(300,350);
		p3.setVisible(true);
		p3.add(name);
		p3.add(tname);
		p3.add(mail);
		p3.add(tmail);
		p3.add(phone);
		p3.add(tphone);
		p3.add(qualification);
		p3.add(tqualification);
		p3.add(gender);
		p3.add(p1);
		p3.add(role);
		p3.add(trole);
		
		p3.add(b1);
		p3.add(b2);
		
		addUser.add(p2,BorderLayout.NORTH);
		addUser.add(p3,BorderLayout.CENTER);
		
	}
	public void clearFields() {
		tname.setText(null);
		cg.clearSelection();
		tmail.setText(null);
		tpassword.setText(null);
		tphone.setText(null);
		tqualification.setText(null);
		trole.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(aids.getSelectedItem().toString());
		if(ae.getSource() == b1) {
			User user = new User();
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
			String role = trole.getText();
			User st = dao.displayUser(id);
			st.setCurrentQualification(qualification);
			st.setUserId(id);
			st.setGender(gender);
			st.setMail(mail);
			st.setPassword(pwd);
			st.setPhone(phone);
			st.setRole(role);
			st.setUserName(name);
			if(dao.editUser(user)) {
				JOptionPane.showMessageDialog(null, "User edited successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == view) {
			User user = dao.displayUser(id);
			tname.setText(user.getUserName());
			tmail.setText(user.getMail());
			tphone.setText(""+user.getPhone());
			tqualification.setText(user.getCurrentQualification());
			String gender = user.getGender();
			if(gender.equals("Male")) {
				male.setSelected(true);
			}else {
				female.setSelected(true);
			}
			trole.setText(user.getRole());
		}
		else if(ae.getSource() == delete) {
			if(dao.deleteUser(id)) {
				JOptionPane.showMessageDialog(null, "User deleted successfully");
			}
		}
		
	}
	/*public static void main(String[] args) {
		new ViewOrEditStudent();
	}*/
}

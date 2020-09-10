package com.xlguru.pro1.gui;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.models.User;

public class MainPageGUI implements ActionListener{
	JFrame f;
	JLabel id,pwd;//role;
	JTextField tid;
	JPasswordField tpwd;
	//JComboBox trole;
	UserDAOImpl dao = new UserDAOImpl();
	JButton b1,b2;
	public void menu() {
		f = new JFrame("Login page");
		id = new JLabel("User Id");
		pwd = new JLabel("Password ");
		//role = new JLabel("Role");
		b1 = new JButton("Login");
		b2 = new JButton("Cancel");
		tid = new JTextField();
		tpwd = new JPasswordField();
		//String roles[] = {"Select Role...","Student","Teacher","Admin"};
		//trole = new JComboBox(roles);
		b1.addActionListener(this);
		b2.addActionListener(this);
		f.setLayout(new GridLayout(3,2));
		f.setSize(300,200);
		f.setVisible(true);
		
		f.add(id);
		f.add(tid);
		f.add(pwd);
		f.add(tpwd);
		//f.add(role);
		//f.add(trole);
		f.add(b1);
		f.add(b2);
	}
	void clearFields(){
		tid.setText(null);
		tpwd.setText(null);
	}
	@Override//Madhuri@143
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			int userId = Integer.parseInt(tid.getText());
			String password = tpwd.getText();
			System.out.println(password);
			User user = dao.validateUser(userId, password);
			if(user.getUserId()==0) {
				JOptionPane.showMessageDialog(null, "Sorry Invalid User Details");
				clearFields();
			}
			
			else if(user.getRole().equals("Admin")){
				clearFields();
				AdminMenuGUI gui = new AdminMenuGUI();
				gui.showMenu(user);
			}
			else if(user.getRole().equals("Student")){
				clearFields();
				StudentMenuGUI gui = new StudentMenuGUI();
				gui.showMenu(user);
				
			}else if(user.getRole().equals("Teacher")){
				clearFields();
				TeacherMenuGUI gui = new TeacherMenuGUI();
				gui.showMenu(user);
			}
		}
		else if(ae.getSource() == b2) {
			
		}
	}
	public static void main(String[] args) {
		
		// TODO Auto-generated constructor stub
		MainPageGUI page = new MainPageGUI();
		page.menu();
	}
}

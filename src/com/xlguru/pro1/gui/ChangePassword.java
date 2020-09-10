package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.models.User;

public class ChangePassword implements ActionListener{
	public User user;
	
	JFrame f1;
	JLabel old, pnew, pcon;
	JPasswordField told, tpnew, tpcon;
	JButton b1, b2;
	public ChangePassword(User user) {
		this.user = user;
		
		f1 = new JFrame("Change Password");
		old = new JLabel("Old Password ");
		pnew = new JLabel("New Password ");
		pcon = new JLabel("Confirm Password ");
		told = new JPasswordField();
		tpnew = new JPasswordField();
		tpcon = new JPasswordField();
		b1 = new JButton("Change Password");
		b2 = new JButton("Reset Password");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		f1.setVisible(true);
		f1.setSize(300,200);
		f1.setLayout(new GridLayout(4, 2));
		f1.add(old);
		f1.add(told);
		f1.add(pnew);
		f1.add(tpnew);
		f1.add(pcon);
		f1.add(tpcon);
		f1.add(b1);
		f1.add(b2);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==b1) {
			String old = told.getText();
			String pnew = tpnew.getText();
			String pcon = tpcon.getText();
			User nuser = new UserDAOImpl().validateUser(user.getUserId(), user.getPassword());
			if(nuser.getUserId()!=0) {
			if(pnew.equals(pcon)) {
				if(new UserDAOImpl().editPassword(user.getUserId(), pnew)) {
					JOptionPane.showMessageDialog(null, "password updated Successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Sorry unable to update try again");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "New Password and Confirmed Password did't match");
			}
			}
			else {
				JOptionPane.showMessageDialog(null, "The password you've entered is not matched");
			}
		}
	}
	
	
	
}

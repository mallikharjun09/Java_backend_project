package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.models.Module;

public class AddModuleGUI implements ActionListener{
	JFrame addModule;
	JLabel name,duration;
	JTextField tname,tduration;
	ModuleDAOImpl dao = new ModuleDAOImpl();
	JButton b1,b2;
	public AddModuleGUI() {
		// TODO Auto-generated constructor stub
		addModule = new JFrame("Add new Module");
		name = new JLabel("Module Name ");
		duration = new JLabel("Duration ");
		tname = new JTextField(30);
		tduration = new JTextField(30);
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		
		addModule.setLayout(new GridLayout(3,2));
		addModule.setSize(200,300);
		addModule.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		addModule.add(name);
		addModule.add(tname);
		addModule.add(duration);
		addModule.add(tduration);
		addModule.add(b1);
		addModule.add(b2);
		
	}
	
	public void clearFields() {
		tname.setText(null);
		tduration.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String name = tname.getText();
			int duration = Integer.parseInt(tduration.getText());
			Module module = new Module();
			module.setDuration(duration);
			module.setModuleName(name);
			if(dao.addModule(module)) {
				JOptionPane.showMessageDialog(null, "Module added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	public static void main(String[] args) {
		AddModuleGUI gui = new AddModuleGUI();
	}
}

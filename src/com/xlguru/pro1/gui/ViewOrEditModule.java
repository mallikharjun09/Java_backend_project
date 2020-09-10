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

import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.models.Module;

public class ViewOrEditModule implements ActionListener{
	JFrame addModule;
	JPanel p1,p2;
	JLabel id;
	JTextField tid;
	JButton view,delete;
	JLabel name,duration;
	JTextField tname,tduration;
	
	ModuleDAOImpl dao = new ModuleDAOImpl();
	ArrayList<String> assignments =dao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox aids = new JComboBox(aid);
	
	JButton b1,b2;
	public ViewOrEditModule() {
		// TODO Auto-generated constructor stub
		addModule = new JFrame("Add new Module");
		p1 = new JPanel();
		p2 = new JPanel();
		id = new JLabel("Module Id");
		tid = new JTextField(10);
		view = new JButton("View Module");
		delete = new JButton("Delete Module");
		
		name = new JLabel("Module Name ");
		duration = new JLabel("Duration ");
		tname = new JTextField(30);
		tduration = new JTextField(30);
		b1 =new JButton("Update");
		b2 = new JButton("Cancel");
		p2.setLayout(new GridLayout(3,2));
		addModule.setSize(450,300);
		addModule.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		view.addActionListener(this);
		delete.addActionListener(this);
		p1.add(id);
		p1.add(aids);
		p1.add(view);
		p1.add(delete);
		
		p2.add(name);
		p2.add(tname);
		p2.add(duration);
		p2.add(tduration);
		p2.add(b1);
		p2.add(b2);
		
		addModule.add(p1,BorderLayout.NORTH);
		addModule.add(p2,BorderLayout.CENTER);
		
	}
	public void clearFields() {
		tname.setText(null);
		tduration.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(aids.getSelectedItem().toString());
		if(ae.getSource() == b1) {
			Module module = dao.displayModule(id);
			String name = tname.getText();
			int duration = Integer.parseInt(tduration.getText());
			module.setDuration(duration);
			module.setModuleName(name);
			if(dao.editModule(module)) {
				JOptionPane.showMessageDialog(null, "Module edited successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == view) {
			Module module = dao.displayModule(id);
			tname.setText(module.getModuleName());
			tduration.setText(""+module.getDuration());
		}
		else if(ae.getSource() == delete) {
			if(dao.deleteModusle(id)) {
				JOptionPane.showMessageDialog(null, "Module deleted successfully");
			}
		}
		
	}
	/*public static void main(String[] args) {
		new ViewOrEditModule();
	}*/
}

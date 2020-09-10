package com.xlguru.pro1.gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.dao.StudentDAOImpl;
import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.dao.BatchDAOImpl;
import com.xlguru.pro1.models.Batch2;
import com.xlguru.pro1.models.User;

public class AddBatchGUI implements ActionListener{
	JFrame addBatch;
	JLabel cid,mid,desc,timeslot,students;
	JTextField tdesc,ttimeslot;
	BatchDAOImpl dao = new BatchDAOImpl();
	CourseDAOImpl cdao = new CourseDAOImpl();
	ArrayList<String> assignments =cdao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox tcid = new JComboBox(aid);
	UserDAOImpl mdao = new UserDAOImpl();
	ArrayList<String> mods =mdao.displayTeacherIds();
	Object[] mids = mods.toArray();
	JComboBox tmid = new JComboBox(mids);
	Object[] sts=  new StudentDAOImpl().displayAllUsers().toArray();
	JList<User> tmodules = new JList(sts);
	
	JButton b1,b2;
	public AddBatchGUI() {
		// TODO Auto-generated constructor stub
		addBatch = new JFrame("Add new Batch");
		cid = new JLabel("Course Id ");
		mid = new JLabel("Faculty Id ");
		desc = new JLabel("Description");
		timeslot = new JLabel("Time Slot");
		students = new JLabel("Students");
		//tcid = new JTextField(30);
		//tmid = new JTextField(30);
		tdesc = new JTextField(30);
		ttimeslot = new JTextField(30);
		b1 =new JButton("Add");
		b2 = new JButton("Cancel");
		tmodules = new JList(sts);
		tmodules.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		addBatch.setLayout(new GridLayout(6,2));
		addBatch.setSize(200,300);
		addBatch.setVisible(true);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		addBatch.add(cid);
		addBatch.add(tcid);
		addBatch.add(mid);
		addBatch.add(tmid);
		addBatch.add(desc);
		addBatch.add(tdesc);
		addBatch.add(timeslot);
		addBatch.add(ttimeslot);
		addBatch.add(students);
		addBatch.add(new Panel().add(new JScrollPane(tmodules)));
		addBatch.add(b1);
		addBatch.add(b2);
		
	}
	
	public void clearFields() {
		//tmid.setText(null);
		tdesc.setText(null);
		ttimeslot.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource() == b1) {
			String desc = tdesc.getText();
			String timeslot = ttimeslot.getText();
			int cid = Integer.parseInt(tcid.getSelectedItem().toString());
			int mid = Integer.parseInt(tmid.getSelectedItem().toString());
			Batch2 batch = new Batch2();
			batch.setCourseId(cid);
			batch.setDescription(desc);
			batch.setTimeslot(timeslot);
			batch.setFacultyId(mid);
			Object indices[]=tmodules.getSelectedValues();
			//String[] modules = tmodules.getText().split(",");
			List<Integer> myList = new LinkedList<Integer>();
			for(Object mod : indices) {
				User m = (User)mod;
				myList.add(m.getUserId());
				//System.out.println(m.getUserId());
			}
			batch.setLst(myList);
			System.out.println(batch.getLst());
			if(dao.addBatch(batch)) {
				JOptionPane.showMessageDialog(null, "Batch added successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == b2) {
			clearFields();
		}
	}
	public static void main(String[] args) {
		AddBatchGUI gui = new AddBatchGUI();
	}
}

package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
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

import com.xlguru.pro1.dao.BatchDAOImpl;
import com.xlguru.pro1.dao.CourseDAOImpl;
import com.xlguru.pro1.dao.UserDAOImpl;
import com.xlguru.pro1.models.Batch2;
import com.xlguru.pro1.models.User;

public class ViewOrEditBatch implements ActionListener{
	JFrame addBatch;
	JPanel p1,p2;
	JLabel id;
	JTextField tid;
	JButton view,delete;
	JLabel cid,mid,desc,timeslot,students;
	JTextField tdesc,ttimeslot,tcid;
	BatchDAOImpl cdao = new BatchDAOImpl();
	ArrayList<String> assignments =cdao.displayIds();
	Object[] aid = assignments.toArray();
	JComboBox aids = new JComboBox(aid);
	UserDAOImpl mdao = new UserDAOImpl();
	ArrayList<String> mods =mdao.displayTeacherIds();
	Object[] mids = mods.toArray();
	JComboBox tmid = new JComboBox(mids);
	Object[] sts=  new UserDAOImpl().displayStudentIds().toArray();
	JList<User> tmodules = new JList(sts);
	
	JButton b1,b2;
	public ViewOrEditBatch() {
		// TODO Auto-generated constructor stub
		addBatch = new JFrame("Edit Batch");
		id = new JLabel("Batch Id");
		cid = new JLabel("Course Id ");
		mid = new JLabel("Faculty Id ");
		desc = new JLabel("Description");
		timeslot = new JLabel("Time Slot");
		students = new JLabel("Students");
		tcid = new JTextField(30);
		p2 = new JPanel();
		//tmid = new JTextField(30);
		tdesc = new JTextField(30);
		ttimeslot = new JTextField(30);
		b1 =new JButton("Update");
		b2 = new JButton("Cancel");
		tmodules = new JList(sts);
		tmodules.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//p2.setSize(200,300);
		//p2.setVisible(true);
		view = new JButton("View");
		delete = new JButton("Delete");
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		
		
		p1 = new JPanel();
		p2 = new JPanel();
		p2.setLayout(new GridLayout(6,2));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		addBatch.setSize(450,300);
		addBatch.setVisible(true);
		
		view.addActionListener(this);
		delete.addActionListener(this);
		p1.add(id);
		p1.add(aids);
		p1.add(view);
		p1.add(delete);
		tcid.disable();
		p2.add(cid);
		p2.add(tcid);
		p2.add(mid);
		p2.add(tmid);
		p2.add(desc);
		p2.add(tdesc);
		p2.add(timeslot);
		p2.add(ttimeslot);
		p2.add(students);
		p2.add(new Panel().add(new JScrollPane(tmodules)));
		p2.add(b1);
		p2.add(b2);
		
		addBatch.add(p1,BorderLayout.NORTH);
		addBatch.add(p2,BorderLayout.CENTER);
		
	}
	public void clearFields() {
		tcid.setText(null);
		tdesc.setText(null);
		ttimeslot.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(aids.getSelectedItem().toString());
		if(ae.getSource() == b1) {
			Batch2 batch = cdao.displayBatch(id);
			String desc = tdesc.getText();
			String timeslot = ttimeslot.getText();
			int cid = Integer.parseInt(tcid.getText());
			int mid = Integer.parseInt(tmid.getSelectedItem().toString());
			batch.setCourseId(cid);
			batch.setDescription(desc);
			batch.setTimeslot(timeslot);
			batch.setFacultyId(mid);
			Object indices[]=tmodules.getSelectedValues();
			//String[] modules = tmodules.getText().split(",");
			List<Integer> myList = new LinkedList<Integer>();
			for(Object mod : indices) {
				com.xlguru.pro1.models.Module m = (com.xlguru.pro1.models.Module)mod;
				myList.add(m.getModuleId());
			}

			if(cdao.editBatch(batch)) {
				JOptionPane.showMessageDialog(null, "Batch updated successfully");
				clearFields();
			}
		}
		else if(ae.getSource() == view) {
			Batch2 q = cdao.displayBatch(id);
			tcid.setText(""+q.getCourseId());
			tdesc.setText(""+q.getDescription());
			ttimeslot.setText(""+q.getTimeslot());
			
		}
		else if(ae.getSource() == delete) {
			if(cdao.deleteBatch(id)) {
				JOptionPane.showMessageDialog(null, "Batch deleted successfully");
			}
		}
		
	}
	public static void main(String[] args) {
		new ViewOrEditBatch();
	}
}

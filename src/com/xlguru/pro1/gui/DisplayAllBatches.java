package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.BatchDAOImpl;
import com.xlguru.pro1.models.Batch2;

public class DisplayAllBatches {
	JFrame f;
	Object[] batchs=  new BatchDAOImpl().displayAllBatchs().toArray();
	JList<Batch2> batchList;
	public DisplayAllBatches() {
		f = new JFrame("All Batchs");
		batchList = new JList(batchs);
		f.add(new Panel().add(new JScrollPane(batchList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		DisplayAllBatches b = new DisplayAllBatches();
	}
}

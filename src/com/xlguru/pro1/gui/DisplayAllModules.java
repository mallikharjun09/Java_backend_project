package com.xlguru.pro1.gui;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.xlguru.pro1.dao.ModuleDAOImpl;
import com.xlguru.pro1.models.Module;

public class DisplayAllModules {
	JFrame f;
	Object[] modules=  new ModuleDAOImpl().displayAllCourses().toArray();
	JList<Module> moduleList;
	public DisplayAllModules() {
		f = new JFrame("All Modules");
		moduleList = new JList(modules);
		f.add(new Panel().add(new JScrollPane(moduleList)),BorderLayout.CENTER);
		f.setSize(500,300);
		f.setVisible(true);
	}
	
}

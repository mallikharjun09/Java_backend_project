package com.xlguru.pro1.models;

import java.util.LinkedList;
import java.util.List;

public class Student extends User {
	
	List<Integer> selectedCourses = new LinkedList<Integer>();
	
	
	public List<Integer> getSelectedCourses() {
		return selectedCourses;
	}

	public void setSelectedCourses(List<Integer> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}

	
}

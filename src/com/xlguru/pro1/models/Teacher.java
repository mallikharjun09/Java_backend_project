package com.xlguru.pro1.models;

import java.util.LinkedList;
import java.util.List;

public class Teacher extends User {
	String qualification;
	String experience;
	float salary;
	List<Integer> selectedCourses = new LinkedList<Integer>();
	public List<Integer> getSelectedCourses() {
		return selectedCourses;
	}
	public void setSelectedCourses(List<Integer> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperienceInYears(String experience) {
		this.experience = experience;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
}

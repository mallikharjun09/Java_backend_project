package com.xlguru.pro1.models;

import java.util.LinkedList;
import java.util.List;

public class Course {
	public int courseId, courseDuration;
	String courseName;
	List<Integer> modules = new LinkedList<Integer>();
	float cost;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<Integer> getModules() {
		return modules;
	}
	public void setModules(List<Integer> modules) {
		this.modules = modules;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return courseId + "\t" + courseName + "\t" + courseDuration + "\t" + cost ;
	}
}

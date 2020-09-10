package com.xlguru.pro1.models;

import java.util.LinkedList;
import java.util.List;

public class Batch {
	int batchId, courseId, facultyId;
	String description, timeslot;
	List<Integer> students = new LinkedList<Integer>();
	public List<Integer> getStudents() {
		return students;
	}
	public void setStudents(List<Integer> studentList) {
		this.students = students;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+batchId + "\t"+description;
	}
	
	public static void main(String[] args) {
		List<Integer> lst = new LinkedList<Integer>();
		lst.add(1001);
		lst.add(10002);
		lst.add(10004);
		Batch b2 = new Batch();
		b2.setStudents(lst);
		System.out.println(b2.getStudents());
	}
}

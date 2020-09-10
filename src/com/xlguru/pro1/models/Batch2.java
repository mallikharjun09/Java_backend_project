package com.xlguru.pro1.models;

import java.util.LinkedList;
import java.util.List;

public class Batch2 {
	List<Integer> lst = new LinkedList<Integer>();
	int batchId, courseId, facultyId;
	String description, timeslot;
	
	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
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

	public List<Integer> getLst() {
		return lst;
	}

	public void setLst(List<Integer> lst) {
		this.lst = lst;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+batchId + "\t"+description+"\t"+timeslot;
	}
	
	/*public static void main(String[] args) {
		List<Integer> lst = new LinkedList<Integer>();
		lst.add(1001);
		lst.add(10002);
		lst.add(10004);
		Batch2 b2 = new Batch2();
		b2.setLst(lst);
		System.out.println(b2.getLst());
	}*/
}

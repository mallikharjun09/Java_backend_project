package com.xlguru.pro1.models;

import java.util.LinkedList;
import java.util.List;

public class Assignment {
	int assignmentId, duration;
	String title;
	List<Integer> questions = new LinkedList<Integer>();
	public int getAssignmentId() {
		return assignmentId;
	}
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Integer> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Integer> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return assignmentId+"\t"+title+"\t";
	}
}

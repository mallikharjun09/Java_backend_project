package com.xlguru.pro1.models;

public class Feedback {
	int feedbackId,studentId;
	String feedbackMsg;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getFeedbackMsg() {
		return feedbackMsg;
	}
	public void setFeedbackMsg(String feedbackMsg) {
		this.feedbackMsg = feedbackMsg;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return studentId+"\t"+feedbackMsg;
	}
}

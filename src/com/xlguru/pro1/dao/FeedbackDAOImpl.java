package com.xlguru.pro1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.Feedback;

public class FeedbackDAOImpl {
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	Connection conn = DBConnection.getConnection();
	
	public ArrayList<String> displayIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select feedbackid from feedbacks";	
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr.add(rs.getString(1));
			}
			
			/*for(int i=0;i<arr.size();i++) {
				ids[i] = arr.get(i);
			}*/
			
		}catch(Exception e) {
			
		}
		return arr;
	}
	
	public boolean addFeedback(Feedback course) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		//String query2 = "insert into coursedetails values(userid.currval, null";
		query = "insert into feedbacks values(feedbackid.nextval,?,?)";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, course.getFeedbackMsg());
			ps.setInt(2, course.getStudentId());
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Feedback displayFeedback(int id) {
		Feedback course = new Feedback();
		query = "select * from feedbacks where feedbackid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				course.setStudentId(rs.getInt("studentid"));
				course.setFeedbackId(rs.getInt("feedbackid"));
				course.setFeedbackMsg(rs.getString("feedbackmsg"));
				
			}
			else {
				course.setFeedbackId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return course;
	}
	public List<Feedback> displayAllCourses() {
		// TODO Auto-generated method stub
		query = "select * from feedbacks";
		List<Feedback> courses = new LinkedList<Feedback>();
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Feedback course = new Feedback();
				course.setStudentId(rs.getInt("studentid"));
				course.setFeedbackId(rs.getInt("feedbackid"));
				course.setFeedbackMsg(rs.getString("feedbackmsg"));
				courses.add(course);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return courses;	
	}
	public boolean deleteFeedback(int id) {
		// TODO Auto-generated method stub
		query = "delete feedbacks where feedbackid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeQuery();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
}

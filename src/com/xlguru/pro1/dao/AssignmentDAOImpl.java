package com.xlguru.pro1.dao;

import java.sql.Connection;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.Assignment;
//import com.xlguru.pro1.dbConfig.DBConne

public class AssignmentDAOImpl{
	
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	Connection conn = DBConnection.getConnection();
	
	
	public boolean addAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		//String query2 = "insert into assignmentdetails values(userid.currval, null";
		query = "insert into assignments values(assignmentid.nextval,?,?,?)";
		String selected_courses = "";
		
		for(int x : assignment.getQuestions()) {
			selected_courses += x+",";
		}
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assignment.getTitle());
			ps.setInt(2, assignment.getDuration());
			ps.setString(3, selected_courses);
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateQuestions(int id, List<Integer> assignments) {
		String selected_assignments = "";
		
		for(int x : assignments) {
			selected_assignments += assignments+",";
		}
		query = "update assignments set questions = (select questions from assignments) +','+ ? where userid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, selected_assignments);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to update data ");
			return false;
		}
	}
	public boolean editAssignment(Assignment assignment) {
		// TODO Auto-generated method stub
		query = "update assignments set title = ?, duration = ? where assignmentid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, assignment.getTitle());
			ps.setInt(2, assignment.getDuration());
			//ps.setFloat(3, assignment.getCost());
			ps.setInt(3, assignment.getAssignmentId());
			ps.execute();
			return true;	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public ArrayList<String> displayIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select assignmentid from assignments";	
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
	public Assignment displayAssignment(int id) {
		Assignment assignment = new Assignment();
		query = "select * from assignments where assignmentid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				//assignment.setCost(rs.getFloat("cost"));
				assignment.setDuration(rs.getInt("duration"));
				assignment.setAssignmentId(id);
				assignment.setTitle(rs.getString("title"));
				List<Integer> questions = new LinkedList<Integer>();
				String[] mods= rs.getString("questions").split(",");
				for(String mod : mods) {
					int x = Integer.parseInt(mod);
					System.out.println(x);
					questions.add(x);
				}
				assignment.setQuestions(questions);
			}
			else {
				assignment.setAssignmentId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return assignment;
	}
	public List<Assignment> displayAllAssignments() {
		// TODO Auto-generated method stub
		query = "select * from assignments";
		List<Assignment> assignments = new LinkedList<Assignment>();
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Assignment assignment = new Assignment();
				assignment.setDuration(rs.getInt("duration"));
				assignment.setAssignmentId(rs.getInt("assignmentid"));
				assignment.setTitle(rs.getString("title"));
				String mons = rs.getString("questions");
				List<Integer> questions = new LinkedList<Integer>();
				String[] mods= mons.substring(0, mons.length()-1).split(",");
				for(String mod : mods) {
					int x = Integer.parseInt(mod);
					questions.add(x);
				}
				assignment.setQuestions(questions);
				assignments.add(assignment);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return assignments;	
	}
	public boolean deleteAssignment(int id) {
		// TODO Auto-generated method stub
		query = "delete assignments where assignmentid = ?";
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
	/*public static void main(String[] args) {
		for(String s : new AssignmentDAOImpl().displayIds())
		System.out.println(s);
	}*/
}

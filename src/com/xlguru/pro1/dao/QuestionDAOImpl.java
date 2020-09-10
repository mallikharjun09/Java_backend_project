package com.xlguru.pro1.dao;

import java.sql.Connection;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.Question;
//import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.User;

public class QuestionDAOImpl{
	
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	Connection conn = DBConnection.getConnection();
	
	public ArrayList<String> displayIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select questionid from questions";	
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
	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		//String query2 = "insert into questiondetails values(userid.currval, null";
		query = "insert into questions values(questionid.nextval,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(3, question.getDescription());
			ps.setInt(1, question.getCourseId());
			ps.setInt(2, question.getModuleId());
			ps.setString(4, question.getType_of_question());
			ps.setString(5, question.getOption1());
			ps.setString(6, question.getOption2());
			ps.setString(7, question.getOption3());
			ps.setString(8, question.getOption4());
			ps.setString(9, question.getAnswer());
			
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean editQuestion(Question question) {
		// TODO Auto-generated method stub
		query = "update questions set courseid = ?,moduleid = ?,description = ?,type_of_question = ?,option1 = ?,option2 = ?,option3 = ?,option4 = ?, answer = ? where questionid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(3, question.getDescription());
			ps.setInt(1, question.getCourseId());
			ps.setInt(2, question.getModuleId());
			ps.setString(4, question.getType_of_question());
			ps.setString(5, question.getOption1());
			ps.setString(6, question.getOption2());
			ps.setString(7, question.getOption3());
			ps.setString(8, question.getOption4());
			ps.setString(9, question.getAnswer());
			ps.setInt(10, question.getQuestionId());
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Question displayQuestion(int id) {
		Question question = new Question();
		query = "select * from questions where questionid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				question.setAnswer(rs.getString("answer"));
				question.setCourseId(rs.getInt("courseid"));
				question.setDescription(rs.getString("description"));
				question.setModuleId(rs.getInt("moduleid"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				question.setQuestionId(id);
			}
			else {
				question.setQuestionId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return question;
	}
	public List<Question> displayAllQuestions() {
		// TODO Auto-generated method stub
		query = "select * from questions";
		List<Question> questions = new LinkedList<Question>();
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Question question = new Question();

				question.setAnswer(rs.getString("answer"));
				question.setCourseId(rs.getInt("courseid"));
				question.setQuestionId(rs.getInt("questionid"));
				question.setDescription(rs.getString("description"));
				question.setModuleId(rs.getInt("moduleid"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				questions.add(question);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return questions;	
	}
	public List<Question> displayAllQuestions(int cid,int mid) {
		// TODO Auto-generated method stub
		query = "select * from questions where courseid = ? and moduleid = ?";
		List<Question> questions = new LinkedList<Question>();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cid);
			ps.setInt(2, mid);
			rs = ps.executeQuery();
			while(rs.next()) {
				Question question = new Question();
				question.setQuestionId(rs.getInt("questionid"));
				question.setAnswer(rs.getString("answer"));
				question.setCourseId(rs.getInt("courseid"));
				question.setDescription(rs.getString("description"));
				question.setModuleId(rs.getInt("moduleid"));
				question.setOption1(rs.getString("option1"));
				question.setOption2(rs.getString("option2"));
				question.setOption3(rs.getString("option3"));
				question.setOption4(rs.getString("option4"));
				questions.add(question);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return questions;	
	}

	public boolean deleteQuestion(int id) {
		// TODO Auto-generated method stub
		query = "delete questions where questionid = ?";
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
	public static void main(String[] args) {
		List<Question> qlist = new QuestionDAOImpl().displayAllQuestions(101, 1001);
		for(Question q : qlist) {
			System.out.println(q);
		}
	}
}

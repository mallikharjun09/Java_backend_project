package com.xlguru.pro1.dao;

import java.util.LinkedList;
import java.util.List;

import com.xlguru.pro1.models.Course;
import com.xlguru.pro1.models.Student;
import com.xlguru.pro1.models.User;


public class StudentDAOImpl extends UserDAOImpl{
	CourseDAOImpl dao = new CourseDAOImpl();
	public boolean updateQualification(Student user) {
		query = "update userdetails set qualification = ? where userid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getCurrentQualification());
			ps.setInt(2,  user.getUserId());
			ps.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	@Override
	public List<User> displayAllUsers() {
		// TODO Auto-generated method stub
		query = "select * from userdetails where role='Student'";
		userList.clear();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setGender(rs.getString("gender"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getLong("phone"));
				user.setRole(rs.getString("role"));
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("username"));
				userList.add(user);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userList;
		
	}
	public List<Course> displayCourses(int id) {
		query = "select courses from coursedetails userid = ?";
		List<Course> courses = new LinkedList<Course>();
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				String[] myCourses = rs.getString(1).split(",");
				for(String str : myCourses) {
					Course course = dao.displayCourse(Integer.parseInt(str));
					courses.add(course);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to update data ");
			//return false;
		}
		return courses;
	}
	
	public boolean updateCourses(int id, List<Integer> courses) {
		String selected_courses = "";
		for(int x : courses) {
			selected_courses += courses+",";
		}
		query = "update coursedetails set courses = ? where userid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to update data ");
			return false;
		}
	}
	public static void main(String[] args) {
		
	}
}

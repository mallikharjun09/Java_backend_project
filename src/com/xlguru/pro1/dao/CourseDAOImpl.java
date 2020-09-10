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
import com.xlguru.pro1.models.Course;
//import com.xlguru.pro1.dbConfig.DBConne

public class CourseDAOImpl{
	
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	Connection conn = DBConnection.getConnection();
	
	public ArrayList<String> displayIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select courseid from courses";	
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
	public String[] displayModuleIds(int courseId) {
		String[] arr = {};
		//String ids[] = new String[arr.size()];
		query = "select modules from courses where courseid = ?";	
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, courseId);
			rs = ps.executeQuery();
			while(rs.next()) {
				arr=rs.getString(1).split(",");
			}
			
			/*for(int i=0;i<arr.size();i++) {
				ids[i] = arr.get(i);
			}*/
			
		}catch(Exception e) {
			
		}
		return arr;
	}
	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		query = "insert into courses values(courseid.nextval,?,?,?,?)";
		String selected_courses = "";
		int len = 0;
		for(int x : course.getModules()) {
			if(len < (course.getModules().size()-1))
			selected_courses += x+",";
			else if(len < course.getModules().size()) {
				selected_courses += x;
			}
		}
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getCourseDuration());
			ps.setFloat(3, course.getCost());
			ps.setString(4, selected_courses);
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateModules(int id, List<Integer> courses) {
		String selected_courses = "";
		
		for(int x : courses) {
			selected_courses += courses+",";
		}
		query = "update courses set modules = (select modules from courses) +','+ ? where userid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, selected_courses);
			ps.setInt(2, id);
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to update data ");
			return false;
		}
	}
	public boolean editCourse(Course course) {
		// TODO Auto-generated method stub
		query = "update courses set coursename = ?, duration = ?, cost = ? where courseid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getCourseDuration());
			ps.setFloat(3, course.getCost());
			ps.setInt(4, course.getCourseId());
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Course displayCourse(int id) {
		Course course = new Course();
		query = "select * from courses where courseid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				course.setCost(rs.getFloat("cost"));
				course.setCourseDuration(rs.getInt("duration"));
				course.setCourseId(id);
				course.setCourseName(rs.getString("coursename"));
				String mons=rs.getString("modules");
				//System.out.println(modules.substring(0,modules.length()-1));
				List<Integer> modules = new LinkedList<Integer>();
				String[] mods= mons.substring(0, mons.length()-1).split(",");
				for(String mod : mods) {
					int x = Integer.parseInt(mod);
					modules.add(x);
				}
				course.setModules(modules);
			}
			else {
				course.setCourseId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return course;
	}
	public List<Course> displayAllCourses() {
		// TODO Auto-generated method stub
		query = "select * from courses";
		List<Course> courses = new LinkedList<Course>();
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			int k = 0;
			while(rs.next()) {
				Course course = new Course();
				course.setCost(rs.getFloat("cost"));
				course.setCourseDuration(rs.getInt("duration"));
				course.setCourseId(rs.getInt("courseid"));
				//System.out.println(rs.getInt("courseid"));
				course.setCourseName(rs.getString("coursename"));
				String mons=rs.getString("modules");
				//System.out.println(modules.substring(0,modules.length()-1));
				List<Integer> modules = new LinkedList<Integer>();
				String[] mods= mons.substring(0, mons.length()-1).split(",");
				for(String mod : mods) {
					int x = Integer.parseInt(mod);
					modules.add(x);
				}
				course.setModules(modules);
				courses.add(course);
				k++;
			}
			System.out.println("k = "+k);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return courses;	
	}
	public boolean deleteCourse(int id) {
		// TODO Auto-generated method stub
		query = "delete courses where courseid = ?";
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
		List<Course> p = new CourseDAOImpl().displayAllCourses();
		for(Course c : p) {
			System.out.println(c);
		}
	}
}

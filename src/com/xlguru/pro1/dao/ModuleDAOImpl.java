package com.xlguru.pro1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.Module;

public class ModuleDAOImpl {
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	Connection conn = DBConnection.getConnection();
	
	public ArrayList<String> displayIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select moduleid from modules";	
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
	
	public boolean addModule(Module course) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		//String query2 = "insert into coursedetails values(userid.currval, null";
		query = "insert into modules values(moduled.nextval,?,?)";
		
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, course.getModuleName());
			ps.setInt(2, course.getDuration());
			
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean editModule(Module course) {
		// TODO Auto-generated method stub
		query = "update modules set modulename = ?, duration = ? where moduleid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, course.getModuleName());
			ps.setInt(2, course.getDuration());
			ps.setFloat(3, course.getModuleId());
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public Module displayModule(int id) {
		Module course = new Module();
		query = "select * from modules where moduleid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				course.setDuration(rs.getInt("duration"));;
				course.setModuleId(rs.getInt("moduleid"));;
				course.setModuleName(rs.getString("modulename"));
				
				
			}
			else {
				course.setModuleId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return course;
	}
	public List<Module> displayAllCourses() {
		// TODO Auto-generated method stub
		query = "select * from modules";
		List<Module> courses = new LinkedList<Module>();
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Module course = new Module();
				course.setDuration(rs.getInt("duration"));
				course.setModuleId(rs.getInt("moduleid"));
				course.setModuleName(rs.getString("modulename"));
				courses.add(course);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return courses;	
	}
	public boolean deleteModusle(int id) {
		// TODO Auto-generated method stub
		query = "delete modules where moduleid = ?";
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

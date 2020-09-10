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
import com.xlguru.pro1.models.Batch2;
//import com.xlguru.pro1.dbConfig.DBConne

public class BatchDAOImpl{
	
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	Connection conn = DBConnection.getConnection();
	
	
	public boolean addBatch(Batch2 batch) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		//String query2 = "insert into batchdetails values(userid.currval, null";
		query = "insert into batches values(batchid.nextval,?,?,?,?,?)";
		String selected_courses = "";
		
		for(int x : batch.getLst()) {
			selected_courses += x+",";
		}
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, batch.getDescription());
			ps.setInt(2, batch.getCourseId());
			ps.setInt(3, batch.getFacultyId());
			ps.setString(4, batch.getTimeslot());
			ps.setString(5, selected_courses);
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	public boolean editBatch(Batch2 batch) {
		// TODO Auto-generated method stub
		String selected_batches = "";
		
		for(int x : batch.getLst()) {
			selected_batches += x+",";
		}
		query = "update batches set description=?,facultyid=?,timeslot=?,students=? where batchid=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, batch.getDescription());
			ps.setInt(2, batch.getFacultyId());
			ps.setString(3, batch.getTimeslot());
			ps.setString(4, selected_batches);
			ps.setInt(5, batch.getBatchId());
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("unable to update data ");
			return false;
		}
	}
	public ArrayList<String> displayIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select batchid from batches";	
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
	public Batch2 displayBatch(int id) {
		Batch2 batch = new Batch2();
		query = "select * from batches where batchid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				batch.setCourseId(rs.getInt("courseid"));
				batch.setBatchId(rs.getInt("batchid"));
				batch.setDescription(rs.getString("description"));
				batch.setFacultyId(rs.getInt("facultyid"));
				batch.setTimeslot(rs.getString("timeslot"));
				List<Integer> students = new LinkedList<Integer>();
				String[] mods= rs.getString("students").split(",");
				for(String mod : mods) {
					int x = Integer.parseInt(mod);
					students.add(x);
				}
				batch.setLst(students);
			}
			else {
				batch.setBatchId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return batch;
	}
	public List<Batch2> displayAllBatchs() {
		// TODO Auto-generated method stub
		query = "select * from batches";
		List<Batch2> batches = new LinkedList<Batch2>();
		try {
			ps = conn.prepareStatement(query);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Batch2 batch = new Batch2();
				batch.setCourseId(rs.getInt("courseid"));
				batch.setBatchId(rs.getInt("batchid"));
				batch.setDescription(rs.getString("description"));
				batch.setFacultyId(rs.getInt("facultyid"));
				batch.setTimeslot(rs.getString("timeslot"));
				List<Integer> students = new LinkedList<Integer>();
				//System.out.println(rs.getString(6));
				String[] mods= rs.getString("students").split(",");
				for(String mod : mods) {
					int x = Integer.parseInt(mod);
					students.add(x);
				}
				batch.setLst(students);
				batches.add(batch);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return batches;	
	}
	public boolean deleteBatch(int id) {
		// TODO Auto-generated method stub
		query = "delete batches where batchid = ?";
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
		for(Batch2 s : new BatchDAOImpl().displayAllBatchs())
		System.out.println(s);
	}
}

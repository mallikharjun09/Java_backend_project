package com.xlguru.pro1.dao;

//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.xlguru.pro1.dbConfig.DBConnection;
import com.xlguru.pro1.models.User;

public class UserDAOImpl implements UserDAO{
	
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	public String query = null;
	
	public ArrayList<String> displayAdminIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select userid from userdetails where role='Admin'";	
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
	public ArrayList<String> displayTeacherIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select userid from userdetails where role='Teacher'";	
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
	public ArrayList<String> displayStudentIds() {
		ArrayList<String> arr = new ArrayList<String>();
		//String ids[] = new String[arr.size()];
		query = "select userid from userdetails where role='Student'";	
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
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		//query = "insert into userdetails values(userid.nextval,?,?,?,?,?,null,0,0,?)";
		String query2 = "insert into coursedetails values(userid.currval, null)";
		query = "insert into userdetails values(userid.nextval,'"+user.getUserName()+"','"+user.getGender()+"','"+user.getMail()+"','"+user.getRole()+"','"+user.getPassword()+"','"+user.getCurrentQualification()+"',0,0,"+user.getPhone()+")";
		
		try {
			/*ps = conn.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2,  user.getGender());
			ps.setString(3,  user.getMail());
			ps.setString(4,  user.getRole());
			ps.setString(5,  user.getPassword());
			ps.setLong(6, user.getPhone());
			*/
			Statement st = conn.createStatement();
			st.addBatch(query);
			st.addBatch(query2);
			st.executeBatch();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean editUser(User user) {
		// TODO Auto-generated method stub
		query = "update userdetails set username = ?, gender = ?, mail = ?, role = ?, password = ?, phone = ? where userid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getUserName());
			ps.setString(2,  user.getGender());
			ps.setString(3,  user.getMail());
			ps.setString(4,  user.getRole());
			ps.setString(5,  user.getPassword());
			ps.setLong(6, user.getPhone());
			ps.setInt(7,  user.getUserId());
			ps.execute();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public User displayUser(int id) {
		User user = new User();
		query = "select * from userdetails where userid = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setGender(rs.getString("gender"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getLong("phone"));
				user.setRole(rs.getString("role"));
				user.setCurrentQualification(rs.getString("qualification"));
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("username"));
			}
			else {
				user.setUserId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return user;
	}
	@Override
	public List<User> displayAllUsers() {
		// TODO Auto-generated method stub
		query = "select * from userdetails";
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
	public List<User> displayAllUsers(String role) {
		// TODO Auto-generated method stub
		query = "select * from userdetails";
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
	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		query = "delete userdetails where userid = ?";
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

	@Override
	public User validateUser(int userId, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		query = "select * from userdetails where userid = ? and password = ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				user.setGender(rs.getString("gender"));
				user.setMail(rs.getString("mail"));
				user.setPassword(rs.getString("password"));
				user.setPhone(rs.getLong("phone"));
				user.setRole(rs.getString("role"));
				user.setUserId(rs.getInt("userid"));
				user.setUserName(rs.getString("username"));
			}
			else {
				user.setUserId(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return user;
	}

	@Override
	public boolean editPassword(int userId, String newPassword) {
		// TODO Auto-generated method stub
		User user = displayUser(userId);
		if(user.getUserId() == 0)
			return false;
		else {
			try {
				query = "update uerdetails set password = ? where userid = ?";
				ps = conn.prepareStatement(query);
				ps.setInt(2, userId);
				ps.setString(1, newPassword);
				ps.execute();
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}

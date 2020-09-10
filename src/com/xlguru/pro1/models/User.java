package com.xlguru.pro1.models;

public class User {
	int userId;
	String currentQualification;
	String userName, gender, mail, role, password;
	
	public User(int userId,String qualification) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		currentQualification = qualification;
	}
	
	public User() {
		
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCurrentQualification() {
		return currentQualification;
	}
	public void setCurrentQualification(String currentQualification) {
		this.currentQualification = currentQualification;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	long phone;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public String toString() {
		return userId + "\t" + userName + "\t" + gender + "\t"+ mail + "\t" + phone;
	}
}

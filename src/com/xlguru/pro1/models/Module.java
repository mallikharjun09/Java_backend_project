package com.xlguru.pro1.models;

public class Module implements Comparable<Module>{
	int moduleId, duration;
	String moduleName;
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return moduleId + "\t" + moduleName + "\t" + duration;
	}
	@Override
	public int compareTo(Module m) {
		// TODO Auto-generated method stub
		return m.moduleId;
	}
}

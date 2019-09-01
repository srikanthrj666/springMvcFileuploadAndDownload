package com.sri.entity;

public class File {

	private int id;
	
	private String fName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", fName=" + fName + "]";
	}
	
	
}

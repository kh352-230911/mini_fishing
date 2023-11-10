package com.sh.member.model.entity;

public class Member {
	
	private String id;
	private String pw; 
	private String name; 
	private int sumPoint;
	private int sumMilli;

	private int scorePerSecond;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Member(String id, String pw) {
		this(id, pw, "", 0);
		// TODO Auto-generated constructor stub
	}
	
	public Member(String id, String pw, String name) {
		this(id, pw, name, 0);
		// TODO Auto-generated constructor stub
	}
	
	public Member(String id, String pw, String name, int sumPoint) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.sumPoint = sumPoint;
	}
	

	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSumPoint() {
		return sumPoint;
	}

	public void setSumPoint(int sumPoint) {
		this.sumPoint = sumPoint;
	}

	public int getSumMilli() {
		return sumMilli;
	}

	public void setSumMilli(int sumMilli) {
		this.sumMilli = sumMilli;
	}

	public int getScorePerSecond() {
		return scorePerSecond;
	}

	public void setScorePerSecond(int scorePerSecond) {
		this.scorePerSecond = scorePerSecond;
	}

	public boolean isNull() {
		if(this.getId() == null &&
			this.getPw() == null &&
			this.getName() == null &&
			(this.getSumPoint() == 0) &&
			(this.getSumMilli() == 0))
			return true;
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", sumPoint=" + sumPoint + ", sumMilli="
				+ sumMilli + ", scorePerSecond=" + scorePerSecond + "]";
	}
	
	
	
}

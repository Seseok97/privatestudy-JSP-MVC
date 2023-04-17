package com.seseok.Member;

import java.sql.Date;

public class MemberDTO {
	//jspdb > itwill_member 테이블 정보 저장 객체
	private String id;
	private String pw;
	private String name;
	private String gender;
	private int age;
	private String email;
	private Date regdate;
	
	// getter / setter
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	// getter / setter end
	
	// toString() method override
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", gender=" + gender + ", age=" + age
				+ ", email=" + email + ", regdate=" + regdate + "]";
	}
	// toString() method end

}// public class end

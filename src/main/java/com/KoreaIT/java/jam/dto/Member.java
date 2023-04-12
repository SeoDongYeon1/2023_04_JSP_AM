package com.KoreaIT.java.jam.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {
	public int id;
	public LocalDateTime regDate;
	public String loginId;
	public String loginPw;
	public String name;
	
	public Member(Map<String, Object> memberRow) {
		this.id = (int) memberRow.get("id");
		this.regDate = (LocalDateTime) memberRow.get("regDate");
		this.loginId = (String) memberRow.get("loginId");
		this.loginPw = (String) memberRow.get("loginPw");
		this.name = (String) memberRow.get("name");

	}

}

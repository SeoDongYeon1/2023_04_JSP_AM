package com.KoreaIT.java.jam.dao;

import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.jam.dto.Member;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

public class MemberDao {
	Connection conn;
	
	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?;", loginId);
		
		Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);
		
		Member member = new Member(memberRow);
		
		return member;
	}
	
	
}

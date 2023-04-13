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
		
		if(memberRow.isEmpty()) {
			return null;
		}
		
		Member member = new Member(memberRow);
		
		return member;
	}

	public boolean isJoinAbleLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT COUNT(*) AS cnt ");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?;", loginId);
		
		boolean isJoinAbleLoginId = DBUtil.selectRowIntValue(conn, sql)==0;
		return isJoinAbleLoginId;
	}

	public void doJoin(String loginId, String loginPw, String name) {
		SecSql sql = SecSql.from("INSERT INTO `member`");
		sql.append("SET regDate = NOW(),");
		sql.append("loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("`name` = ?;", name);
		
		DBUtil.insert(conn, sql);
	}
	
	
}

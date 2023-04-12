package com.KoreaIT.java.jam.service;

import java.sql.Connection;

import com.KoreaIT.java.jam.dao.MemberDao;
import com.KoreaIT.java.jam.dto.Member;

public class MemberService {
	private Connection conn;
	private MemberDao memberDao;

	public MemberService(Connection conn) {
		this.conn = conn;
		this.memberDao = new MemberDao(conn);
	}

	public Member getMemberByLoginId(String loginId) {
		Member member = memberDao.getMemberByLoginId(loginId);
		
		return member;
	}

	public boolean isJoinAbleLoginId(String loginId) {
		boolean isJoinAbleLoginId = memberDao.isJoinAbleLoginId(loginId);
		return isJoinAbleLoginId;
	}

	public void doJoin(String loginId, String loginPw, String name) {
		memberDao.doJoin(loginId, loginPw, name);
	}

}

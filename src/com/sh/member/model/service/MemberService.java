package com.sh.member.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.entity.Member;
import com.sh.member.model.exception.MemberException;

public class MemberService {
	
	MemberDao memberDao = new MemberDao();

	public Member startLogin(Member member) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Member memberM = memberDao.startLogin(conn, member);
			try {
				close(conn);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				close(conn);
			}
			return memberM;
	}

	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection conn = getConnection();
		try {
			result = memberDao.insertMember(conn, member);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return result;
	
	}

	public void deleteInfo(Member member) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		memberDao.deleteInfo(conn, member);
		try {
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
			throw new MemberException();
		}
		finally {
			close(conn);
		}
		
	}

	public List<Member> showRank() {
		// TODO Auto-generated method stub
		List<Member> members = null;
		Connection conn = getConnection();
		try {
			members = memberDao.showRank(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(conn);
		}
		return members;
	}

	public void updatePlayTime(Member memberInfo, int second) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		try {
			new MemberDao().updatePlayTime(conn, memberInfo, second);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
	}

	public void updatePlayScorePerSecond(Member memberInfo) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		try {
			new MemberDao().updatePlayScorePerSecond(conn, memberInfo);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
	}

	public List<Member> getUpdatedMember(Member memberInfo) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<Member> members = null;
		try {
			members = new MemberDao().getUpdatedMember(conn, memberInfo);
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			throw e;
		}
		finally {
			close(conn);
		}
		return members;
	}
	
}
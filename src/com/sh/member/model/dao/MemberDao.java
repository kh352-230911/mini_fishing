package com.sh.member.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.fish.model.exception.FishException;
import com.sh.member.model.entity.Member;
import com.sh.member.model.exception.MemberException;

public class MemberDao {

	Properties prop = new Properties();
	
	public MemberDao() {
		
		try {
			prop.load(new FileReader("resources\\member-query.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Member startLogin(Connection conn, Member member) {
		String sql = prop.getProperty("startLogin");
		Member resultMember = new Member();
		try {
			//미완성 쿼리를 완성시켜 질의 결과를 결과 집합 rset에 대입
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			ResultSet rset = pstmt.executeQuery();
		
			while(rset.next()) {
				//쿼리를 통해서 가져온 결과집합의 값을 통해 resultMember객체의 멤버 변수에 대입
				resultMember.setId(rset.getString("id"));
				resultMember.setPw(rset.getString("pw"));
				resultMember.setName(rset.getString("name"));
				resultMember.setScorePerSecond(rset.getInt("score_per_second"));
				resultMember.setSumPoint(rset.getInt("sum_point"));		
				resultMember.setSumMilli(rset.getInt("sum_milli"));		
			}
		
		}
		//예외상황발생시 해당 사항을 Controller까지 알려주는 역할을 수행
		catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new MemberException("회원조회 오류!", e);
		}
		
		//쿼리를 통해 resultMember에 값이 제대로 대입 됐는지 확인
		if(resultMember.isNull()) {
			return null;
		}
		//Null이 아니라면 resultMember객체의 레퍼런스 변수 값을 리턴
		else {
			return resultMember;
		}
	}

	public int insertMember(Connection conn, Member member) {
		// TODO Auto-generated method stub
		String sql = prop.getProperty("insertMember");
		int result = 0;
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPw());
				pstmt.setString(3, member.getName());			
				result = pstmt.executeUpdate();
			}
			catch(SQLIntegrityConstraintViolationException e) {
				System.out.println("이미 사용중인 사용자 id입니다. 다시 시도 하십시오.");
				throw new MemberException();
			}
			catch(Exception e) {
				throw new MemberException("회원가입 오류!", e);
			}

		return result;
	}

	public void deleteInfo(Connection conn, Member member) {
		// TODO Auto-generated method stub
		//deleteInfo = delete from person where id = ?
		String sql = prop.getProperty("deleteInfo");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, member.getId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("회워정보삭제!");
			throw new FishException();
		}
		
	}
	
	
	//showRank = select * from person order by sum_point desc
	public List<Member> showRank(Connection conn) {
		// TODO Auto-generated method stub
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("showRank");
		try (PreparedStatement mstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = mstmt.executeQuery()){
				while(rset.next()) {
					Member member = new Member();
					member.setId(rset.getString("id"));
					member.setPw(rset.getString("pw"));
					member.setName(rset.getString("name"));
					member.setScorePerSecond(rset.getInt("score_per_second"));
					member.setSumPoint(rset.getInt("sum_point"));
					member.setSumMilli(rset.getInt("sum_milli"));
					members.add(member);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}

	public void updatePlayTime(Connection conn, Member memberInfo, int second) {
		// TODO Auto-generated method stub
		String sql = prop.getProperty("updatePlayTime");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, second);
			pstmt.setString(2, memberInfo.getId());
			result = pstmt.executeUpdate();				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updatePlayScorePerSecond(Connection conn, Member memberInfo) {
		// TODO Auto-generated method stub
		String sql = prop.getProperty("updatePlayScorePerSecond");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, memberInfo.getSumPoint());
			pstmt.setInt(2, memberInfo.getSumMilli());
			pstmt.setString(3, memberInfo.getId());
			result = pstmt.executeUpdate();				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Member> getUpdatedMember(Connection conn, Member memberInfo) {
		// TODO Auto-generated method stub
		//getUpdatedMember = select * from person where id = ?
		String sql = prop.getProperty("getUpdatedMember");
		List<Member> members = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberInfo.getId());
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					Member member = new Member();
					member.setId(rset.getString("id"));
					member.setPw(rset.getString("pw"));
					member.setName(rset.getString("name"));
					member.setScorePerSecond(rset.getInt("score_per_second"));
					member.setSumPoint(rset.getInt("sum_point"));
					member.setSumMilli(rset.getInt("sum_milli"));	
					members.add(member);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
	}

}

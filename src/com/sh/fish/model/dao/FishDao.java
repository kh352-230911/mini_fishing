package com.sh.fish.model.dao;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.fish.model.entity.Fish;
import com.sh.fish.model.entity.FishCatchInfo;
import com.sh.fish.model.exception.FishException;
import com.sh.member.model.entity.Member;
import com.sh.member.model.exception.MemberException;


public class FishDao {

	Properties prop = new Properties();
	
	
	public FishDao(){
		
		try {
			prop.load(new FileReader("resources\\fish-query.properties"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<Fish> startFishing(Connection conn) {
		List<Fish> fishes = new ArrayList<>();
		String sql = prop.getProperty("startFishing");
		Fish fish = null;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			try(ResultSet rset = pstmt.executeQuery()){
				
				while(rset.next()) {
					fish = new Fish();
					fish.setFish_name(rset.getString("fish_name"));
					fish.setFish_id(rset.getInt("fish_id"));
					fish.setPoint(rset.getInt("point"));
					fish.setMax_size(rset.getInt("max_size"));
					fish.setMin_size(rset.getInt("min_size"));
					fishes.add(fish);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FishException();
		}
		return fishes;
	}


	public int insertFish(Connection conn, Fish fish, Member memberInfo) {
		String sql = prop.getProperty("insertFish");
		//insertFish = insert into catchFishes values(?, ?, ?, ?, default)
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberInfo.getId());
			pstmt.setString(2, fish.getFish_name());
			pstmt.setInt(3, fish.getPoint());
			pstmt.setInt(4, fish.getPoint());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * catchFishSet = select p.name, c.fish_name, c.catch_fish_size, c.catch_at from person p join catchFishes c on p.id = c.id where p.id = ?
	 */
	public List<FishCatchInfo> catchFishSet(Connection conn, Member memberInfo) {
		// TODO Auto-generated method stub
		String sql = prop.getProperty("catchFishSet");
		List<FishCatchInfo> fishCatchInfoList = new ArrayList<FishCatchInfo>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, memberInfo.getId());
				try(ResultSet rset = pstmt.executeQuery()){
					while(rset.next()) {
						FishCatchInfo fishCatchInfo = new FishCatchInfo();	
						fishCatchInfo.setId(rset.getString("name"));
						fishCatchInfo.setFishName(rset.getString("fish_name"));
						fishCatchInfo.setCatchFishSize(rset.getInt("catch_fish_size"));
						fishCatchInfo.setCatch_at(rset.getDate("catch_at"));
						fishCatchInfoList.add(fishCatchInfo);
					}
					
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fishCatchInfoList;
	}
	
	public List<Member> showRanking(Connection conn) {
		List<Member> members = new ArrayList<>();
		String sql = prop.getProperty("showRanking");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					members.add(setRsetHandle_r(rset));
				}
			}
		} catch (SQLException e) {
			throw new MemberException("랭크조회실패", e);
		}
		return members;
	}

	private Member setRsetHandle_r(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setId(rset.getString("id"));
		member.setName(rset.getString("name"));
		member.setSumPoint(rset.getInt("sum_point"));
		return member;
	}

	public int insertRankScore(Connection conn, int sumPoint, String id, String name) {
		String sql = prop.getProperty("insertRankScore");
		int result = 0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, sumPoint);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("랭크입력실패", e);
		}
		return result;
	}
	
}

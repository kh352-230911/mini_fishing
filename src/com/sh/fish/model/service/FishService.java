package com.sh.fish.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.fish.model.dao.FishDao;
import com.sh.fish.model.entity.Fish;
import com.sh.fish.model.entity.FishCatchInfo;
import com.sh.fish.model.exception.FishException;
import com.sh.member.model.entity.Member;

public class FishService {

	public List<Fish> startFishing() {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		List<Fish> fishes = new FishDao().startFishing(conn);
		try {
			close(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new FishException();
		}
		return fishes;
	}

	public static void insertFish(Fish fish, Member memberInfo) {

		Connection conn = getConnection();
		new FishDao().insertFish(conn, fish, memberInfo);
		try {
			commit(conn);
		}
		catch(Exception e) {
			rollback(conn);
			e.printStackTrace();
			throw new FishException();
		}
		finally {
			close(conn);
		}
		
	}

	public List<FishCatchInfo> catchFishSet(Member memberInfo) {
		// TODO Auto-generated method stub
		List<FishCatchInfo> fishCatchInfoList = null;
		Connection conn = getConnection();
		FishDao fishDao = new FishDao();
		fishCatchInfoList = fishDao.catchFishSet(conn, memberInfo);
		try {
			close(conn);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return fishCatchInfoList;
	}

	public List<Member> showRanking() {
		FishDao fishDao = new FishDao();
		Connection conn = getConnection();
		List<Member> members = fishDao.showRanking(conn);
		close(conn);
		return members;
	}

	public int insertRankScore(int sumPoint, String id, String name) {
		int result = 0;
		FishDao fishDao = new FishDao();
		Connection conn = getConnection();
		result = fishDao.insertRankScore(conn, sumPoint, id, name);
		commit(conn);
		return 0;
	}

}

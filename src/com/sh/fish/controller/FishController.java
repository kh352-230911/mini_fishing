package com.sh.fish.controller;

import java.util.List;

import com.sh.fish.model.entity.Fish;
import com.sh.fish.model.entity.FishCatchInfo;
import com.sh.fish.model.service.FishService;
import com.sh.member.model.entity.Member;

public class FishController {

	FishService fishService = null;
	
	public List<Fish> startFishing() {
		// TODO Auto-generated method stub
		fishService = new FishService();
		return fishService.startFishing();
	
	}

	public void insertFish(Fish fish, Member memberInfo) {
		// TODO Auto-generated method stub
		fishService = new FishService();
		fishService.insertFish(fish, memberInfo);
	}

	public List<FishCatchInfo> catchFishSet(Member memberInfo) {
		// TODO Auto-generated method stub
		fishService = new FishService();
		List<FishCatchInfo> fishCatchInfoList = fishService.catchFishSet(memberInfo);
		
		return fishCatchInfoList;
	}

	public List<Member> showRanking() {
		List<Member> members = null;
		fishService = new FishService();
		try {
			members = fishService.showRanking();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return members;
	}
	
	public int insertRankScore(int sumPoint, String id, String name) {
		int result = 0;
		fishService = new FishService();
		try {
			return fishService.insertRankScore(sumPoint, id, name);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return result;
	}

}

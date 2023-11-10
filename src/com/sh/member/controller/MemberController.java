package com.sh.member.controller;

import java.util.List;

import com.sh.member.model.entity.Member;
import com.sh.member.model.service.MemberService;

public class MemberController {

	MemberService memberService = new MemberService();
	
	public Member startLogin(Member member) {
		// TODO Auto-generated method stub
		return memberService.startLogin(member);
	}

	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		int successRow = 0;
		try {
			successRow = memberService.insertMember(member);
		}
		catch(Exception e) {
			
		}
		finally {
			return successRow;
		}
	}

	public void deleteInfo(Member member) {
		// TODO Auto-generated method stub
		try {
			memberService.deleteInfo(member);
		}
		catch(Exception e) {
			return;
		}
	}

	public List<Member> showRank() {
		// TODO Auto-generated method stub
		return memberService.showRank();
	}

	public void updatePlayTime(Member memberInfo, int second) {
		// TODO Auto-generated method stub
		try {
			new MemberService().updatePlayTime(memberInfo, second);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void updatePlayScorePerSecond(Member memberInfo) {
		// TODO Auto-generated method stub
		try {
			new MemberService().updatePlayScorePerSecond(memberInfo);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Member> getUpdatedMember(Member memberInfo) {
		// TODO Auto-generated method stub
		List<Member> members = null;
		try {
			members = memberService.getUpdatedMember(memberInfo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return members;
		
	}


}

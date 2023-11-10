package com.sh.member.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sh.fish.model.entity.FishCatchInfo;
import com.sh.fish.view.FishMainMenu;
import com.sh.member.controller.MemberController;
import com.sh.member.model.entity.Member;

public class MemberMenu {

	
	Scanner sc = new Scanner(System.in);
	MemberController memberController = new MemberController();
	FishMainMenu fishMainMenu = new FishMainMenu();
	List<Member> members = new ArrayList<Member>();
	
	public void mainMenu() {
		
		String menu = """
				===============================
		        낚시 게임 메인 메뉴 입니다.
				=============================== 
				1. 로그인
				2. 회원가입
				3. 회원정보삭제
				4. 점수 랭킹 조회
				5. 프로그램 종료
				===============================
				선택>>""";
		
		String selection = "";
		String id = "";
		String pw = "";
		String name = "";
		Member member;
		List<Member> members = null;
		
		while(true) {
			System.err.print(menu);
			selection = sc.next();
			switch(selection){
			 //로그인
			 case "1": 
				 System.out.println("로그인 선택");
				 System.out.print("아이디를 입력하십시오.");
				 id = sc.next();
				 System.out.print("패스워드를 입력하십시오.");
				 pw = sc.next();
				 //id와 pw를 입력받아 새로운 member객체 생성
				 member = new Member(id, pw);
				 //위에서 생성한 member객체를 통해 login기능을 구현, member객체는 로그인한 유저의 정보를 담고 있음.
				 member = memberController.startLogin(member);
				 //로그인한 유저의 정보를 담고 있어야할 객체member가 비어있다면, 로그인 실패로 간주하고 다음 반복을 수행
				 if(member == null) {
					 System.out.println("로그인 실패");
					 continue;
				 }
				 System.out.println("로그인중..... 잠시만 기다려주십시오.\n");
				 System.out.println(member.getId() + "에 로그인 성공!\n");
				 System.out.println("물고기 잡기 게임 메뉴에 접속합니다.\n");
				 //member의 정보를 가지고 낚시 게임 메뉴로 이동
				 fishMainMenu.fishMenu(member);
				 break;
			 
			 
			 //회원가입
			 case "2":
				 System.out.println("회원가입 선택");
				 System.out.print("아이디를 입력하십시오.");
				 id = sc.next();
				 System.out.print("패스워드를 입력하십시오.");
				 pw = sc.next();
				 System.out.print("이름을 입력하십시오.");
				 name = sc.next();
				 //id와 pw, name을 입력받아 새로운 member객체 생성
				 member = new Member(id, pw, name);
				 //member의 정보를 가지고 정보를 저장하고 처리된 행의 수를 successRow에 대입
				 int successRow = memberController.insertMember(member);
				 //처리된 행의 수가 0이 아닐때 정상적인 작업이라고 판단
				 if(successRow != 0) {
					 member = new Member(id, pw);
					 //회원가입과 동시에 자동 로그인되도록 설정
					 member = memberController.startLogin(member);
					 if(member == null) {
						 System.out.println("로그인 실패");
						 continue;
					 }
					 
					 System.out.println("로그인 성공!" + member.getId() + "에 로그인 중...");
					 System.out.println("물고기 잡기 게임 메뉴에 접속합니다.");
					 //자동 로그인된 후 유저 정보를 가지고 바로 게임 메뉴로 이동
					 fishMainMenu.fishMenu(member);
				 }
				 break;
			 case "3": 
				 System.out.println("회원정보 삭제 선택");
				 System.out.print("아이디를 입력하십시오.");
				 id = sc.next();
				 System.out.print("패스워드를 입력하십시오.");
				 pw = sc.next();
				 //id와 pw를 입력받아 새로운 member객체 생성
				 member = new Member(id, pw);
				 //존재하는 유저인지 확인하기 위해 로그인 시도, 로그인 되지 않으면 없는 회원이라고 판단 후 다음 반복 실행
				 member = memberController.startLogin(member);
				 if(member == null) {
					 System.out.println("삭제에 실패했습니다. 존재하지 않는 회원입니다.");
					 continue;
				 } 
//				 resultInfo(member);
				 //존재하는 회원임이 확인되면 유저 정보 삭제 작업
				 memberController.deleteInfo(member);
				 System.out.println("회원정보 삭제 완료...");
				 break;
			 case "4": 
				 //회원정보를 가져와 랭킹현황을 출력
				 members = memberController.showRank();
				 showRankList(members);
				 break;		 
			 case "5": 
				 System.out.println("프로그램을 종료합니다. ");
				 return;
			 //선택지에 존재하지 않는 입력값을 입력받으면 재 선택 하도록 유도
			 default :
				 System.out.println("유효하지 않습니다. 다시 선택하십시오.");
				 break;
				
			}
			
		}
		
	}

		private void showRankList(List<Member> members) {
			System.out.println("\n---------------------------------------------------");
			System.out.printf("%-10s%-20s%-15s%-20s%-20s\n",
					"랭킹", "단위 시간 획득 점수", "Name", "Total Score", "플레이타임(단위: 초)");
			System.out.println("---------------------------------------------------");
			//가져온 회원정보 집합이 자체가 null이거나 집합의 요소가 없는 경우 조회된 결과가 없다고 안내
			if(members == null || members.isEmpty()) {
				System.out.println("\t\t 조회된 결과가 없습니다.");
			}
			else {
				int count = 1;
				//요소들을 순회하면서 내용을 요소별로 출력
				for(Member member : members) {				
					if(count == 1) {
						System.err.printf("%-10s%-20s%-15s%-20s%-20s%-20s\n\n",
								count++ + "등",
								member.getScorePerSecond(),
								member.getId(),
								member.getSumPoint(),
								member.getSumMilli(),
								"호수의 지배자 " + member.getId());
						
					}
					else if(count != members.size()){
						System.out.printf("%-10s%-20s%-15s%-20s%-20s\n\n",
								count++ + "등",
								member.getScorePerSecond(),
								member.getId(),
								member.getSumPoint(),
								member.getSumMilli());
					}
					else {
						System.err.printf("%-10s%-20s%-15s%-20s%-20s%-20s\n\n",
								count++ + "등",
								member.getScorePerSecond(),
								member.getId(),
								member.getSumPoint(),
								member.getSumMilli(),
								"평화주의자 " + member.getId());
					}
				}
			}
		}
		


	

	
	
}

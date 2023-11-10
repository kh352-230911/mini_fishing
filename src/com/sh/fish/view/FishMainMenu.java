package com.sh.fish.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sh.fish.controller.FishController;
import com.sh.fish.gui.FishGui;
import com.sh.fish.model.entity.Fish;
import com.sh.fish.model.entity.FishCatchInfo;
import com.sh.member.controller.MemberController;
import com.sh.member.model.entity.Member;
import com.sh.thread.TimerThread;

public class FishMainMenu {
	
	public void fishMenu(Member memberInfo) {
		
		String fishMenu = """
				===============================
		        낚시게임
				===============================
				1. 물고기 낚시 시작하기.
				2. 잡은 물고기 정보 출력 
				3. 현재 진행 시간 조회
				0. 로그아웃
				===============================
				선택>>"""
				;

				System.out.println("타이머를 동작시킵니다. 실력을 보여주세요!");

				Scanner sc = new Scanner(System.in);
				FishController fishController = new FishController();
				List<Fish> fishes = new ArrayList<Fish>();
				List<FishCatchInfo> fishCatchInfoList = null;
				List<Member> members = null;
				int delayTime = (int)(Math.random() * 5000) + 1000;
				Fish fish = null;
				FishGui fishGui = null;
				
				String fishName = null;
				String selection = null;
				TimerThread timerThread = new TimerThread();
				timerThread.start();
				
				while(true) {
					System.err.print(fishMenu);
					selection = sc.next();
					switch(selection) {
						case "1" :
							System.out.println("물고기 낚시를 시작합니다.");
							
								try {
									System.out.println("낚시는 기다림의 미학");
									//현재 쓰레드(Main쓰레드)를 랜덤한 시간 만큼 재움.
									Thread.currentThread().sleep(delayTime);
									//쓰레드를 재운 시간 만큼 잡은걸로 인식하도록 함
									System.out.println((int)(delayTime/1000) + "초 만에 잡으셨습니다.");
									//낚시를 시작하면 물고기 정보 가져온 후 그 중 한 마리를 랜덤으로 뽑음.
									fishes = fishController.startFishing();
									fish = selectFish(fishes, memberInfo);
									//유저가 잡은 물고기를 회원정보와 함께 DB table에 저장
									fishController.insertFish(fish, memberInfo);
									viewFish(fish);
									//이미지 출력을 위한 그래픽 작업 수행
									System.out.println(fish.nameToImagePath(fish.getFish_name()));
									FishGui fishgui = new FishGui(fish.nameToImagePath(fish.getFish_name()));
									fishgui.setImagePath(fish.nameToImagePath(fish.getFish_name()));
								
								} 
								//쓰레드에 대한 인터럽트 예외 처리
								catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							break;
						case "2" :
							System.out.println("잡은 물고기 정보를 출력합니다.");
							//로그인한 유저가 잡은 모든 물고기 정보를 가져와서 출력
							fishCatchInfoList = fishController.catchFishSet(memberInfo);
							catchSetPrint(fishCatchInfoList);
							break;
							
						case "3" :
							//내부적으로 동작하던 타이머 쓰레드가 기록한 시간(초)를 출력
							System.out.println("현재 진행시간을 조회합니다.");
							System.out.println(timerThread.getSecond() + "초 동안 게임을진행하셨습니다.");
							break;
						case "0" :		
							
							Member UpdatedMember = null;
							MemberController memberController = new MemberController();
							System.out.println("로그아웃...");
							//내부적으로 타이머를 동작시키던 쓰레드에 강제적으로 인터럽트 처리를 하여 쓰레드 동작을 멈춤
							timerThread.interrupt();
							//타이머 쓰레드가 기록한 최종 시간을 로그아웃시에 출력
							System.out.println(timerThread.getSecond() + "초 동안 게임을진행하셨습니다.");
//							timerThread.interrupt();
							//로그인한 정보와 플레이 시간을 테이블에 저장하는 작업을 수행
							memberController.updatePlayTime(memberInfo, timerThread.getSecond());
							//처음 가져온 유저정보를 갱신하도록 함.
							UpdatedMember = memberController.getUpdatedMember(memberInfo).get(0);
							memberController.updatePlayScorePerSecond(UpdatedMember);
							//마지막으로 메인 메뉴로 돌아기전 로그인한 유저의 정보를 파기
							memberInfo = null;
							//가비지 컬렉션을 호출하여 JVM이 가비지를 처리하도록 '요청'
							//(단, 이는 요청일 뿐 반드시 수행되지는 않음에 유의)
							System.gc();
							//메인 메뉴로 복귀
							return;
						
				}
			}
	}

//	private void updateMemberInfo(Member memberInfo) {
//		// TODO Auto-generated method stub
//		memberInfo = 
//		
//	}

	private Fish selectFish(List<Fish> fishes, Member memberInfo) {
		// TODO Auto-generated method stub
		Fish fish = null;
		int randomFishIndex = (int)(Math.random() * fishes.size());
		fish = fishes.get(randomFishIndex);
		int sizeRange = fish.getMax_size() - fish.getMin_size();
		int addPoint = (int)(Math.random() * sizeRange) + fishes.get(randomFishIndex).getMin_size();
		fish.setPoint(fish.getPoint() + addPoint);
		
		System.out.println("이번 낚시에서 " + fish.getFish_name() + "을(를) 잡으셨습니다. "	
				+ "사이즈는 " + addPoint 
				+ "cm 점수는 "+ fish.getPoint()
				+ "입니다."
				);
		
		return fish;
	}

	private void viewFish(Fish fish) {
		// TODO Auto-generated method stub
//			System.out.println("이번 낚시로 " + fish.getFish_name() + "를 잡으셨습니다." );
	}

	private void catchSetPrint(List<FishCatchInfo> fishCatchInfoList) {
		System.out.println("--------------------------------------");
		System.out.printf("%-11s%-10s%-20s%-24s\n",
				"Name", "FishName", "FishSize", "Catch_at");
		System.out.println("--------------------------------------");
		if(fishCatchInfoList == null || fishCatchInfoList.isEmpty()) {
			System.out.println("\t\t 조회된 결과가 없습니다.");
		}
		else {
			for(FishCatchInfo fishCatchInfo : fishCatchInfoList) {
				System.out.printf("%-10s%-10s%-10d%20s\n",
						fishCatchInfo.getId(),
						fishCatchInfo.getFishName(),
						fishCatchInfo.getCatchFishSize(),
						new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fishCatchInfo.getCatch_at()));
			}
		}
	}
	
	private void showAllRank(List<Member> members) {
		if(members == null || members.isEmpty()) {
			System.out.println("조회결과없음");
		}
		else {
			int num = 1;
			System.out.println("=========================RANKING=========================");
			System.out.println();
			for(Member member : members) {
				System.out.printf("%d등 id : %s name : %s 최고점수 : %d \n",
						num,
						member.getId(),
						member.getName(),
						member.getSumPoint()
						);
				num++;
				if(num > 10) {
					break;
				}
			}
			System.out.println();
			System.out.println("=========================================================");
		}
	}
	
	
	
}

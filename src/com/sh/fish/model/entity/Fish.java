package com.sh.fish.model.entity;

public class Fish {
	private int fish_id;
	private String fish_name;
	private int point;
	private int max_size;
	private int min_size;
	
	String fishNameArray[] = {
			"멸치", "청어", "전어", "밴댕이", "꽁치", "볼락", "고등어",
			"삼치", "다랑어", "농어", "감성동", "참돔", "돌돔", "민어",
			"참조기", "갈돔", "날새기", "전갱이", "방어", "황새치", "붉바리",
			"메기", "연어", "점성어", "은어", "잉어", "병어", "보리멸", "장어",
			"붕어", "임연수어", "조기", "산천어", "쥐치", " 열빙어", "우럭", "광어",
			"부세", "쏘가리", "독가시치", "배스", "가물치", "대구", "망상어", "물메기",
			"달고기", "청새치", "시샤모", "만새기", "재방어"
	};
	
	public String[] getFishNameArray() {
		return fishNameArray;
	}

	public void setFishNameArray(String[] fishNameArray) {
		this.fishNameArray = fishNameArray;
	}

	public String[] getFishImagePathArray() {
		return fishImagePathArray;
	}

	public void setFishImagePathArray(String[] fishImagePathArray) {
		this.fishImagePathArray = fishImagePathArray;
	}

	String fishImagePathArray[] = {
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_01.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_02.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_03.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_04.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_05.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_06.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_07.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_08.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_09.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_10.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_11.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_12.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_13.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_14.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_15.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_16.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_17.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_18.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_19.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_20.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_21.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_22.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_23.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_24.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_25.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_26.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_27.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_28.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_29.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_30.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_31.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_32.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_33.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_34.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_35.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_36.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_37.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_38.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_39.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_40.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_41.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_42.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_43.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_44.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_45.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_46.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_47.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_48.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_49.png",
			"C:\\Workspaces\\jdbc_workspace\\FishingGame\\FishingGame\\ImageFile\\fish_50.png"
		};
	
	public Fish() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Fish(String fish_name, int fish_id, int point, int max_size, int min_size) {
		super();
		this.fish_id = fish_id;
		this.fish_name = fish_name;
		this.point = point;
		this.max_size = max_size;
		this.min_size = min_size;
	}
	
	public String getFish_name() {
		return fish_name;
	}
	public void setFish_name(String fish_name) {
		this.fish_name = fish_name;
	}
	public int getFish_id() {
		return fish_id;
	}
	public void setFish_id(int fish_id) {
		this.fish_id = fish_id;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getMax_size() {
		return max_size;
	}
	public void setMax_size(int max_size) {
		this.max_size = max_size;
	}
	public int getMin_size() {
		return min_size;
	}
	public void setMin_size(int min_size) {
		this.min_size = min_size;
	}
	
	@Override
	public String toString() {
		return "Fish [fish_name=" + fish_name + ", fish_id=" + fish_id + ", point=" + point + ", max_size=" + max_size
				+ ", min_size=" + min_size + "]";
	}
	
	public String nameToImagePath(String fish_name) {
		for(int i = 0; i < fishNameArray.length; i++ ) {
			
			if(fishNameArray[i].equals(fish_name)) {
				return fishImagePathArray[i];
			}
		 
		}
		return null;
	}
				
}

package com.sh.fish.model.entity;

import java.sql.Date;

public class FishCatchInfo {
			/*
			 * catchFishSet = select p.name, c.fishName, c.catch_at from person p join catchFishes p.id = c.id 
			 */
			
			String id;
			String fishName;
			int catchFishSize;
			Date catch_at;
			public FishCatchInfo() {
				super();
				// TODO Auto-generated constructor stub
			}
			
			
			
			public FishCatchInfo(String id, String fishName, int catchFishSize, Date catch_at) {
				super();
				this.id = id;
				this.fishName = fishName;
				this.catchFishSize = catchFishSize;
				this.catch_at = catch_at;
			}


			
			

			public String getId() {
				return id;
			}



			public void setId(String id) {
				this.id = id;
			}



			public String getFishName() {
				return fishName;
			}



			public void setFishName(String fishName) {
				this.fishName = fishName;
			}



			public int getCatchFishSize() {
				return catchFishSize;
			}



			public void setCatchFishSize(int catchFishSize) {
				this.catchFishSize = catchFishSize;
			}



			public Date getCatch_at() {
				return catch_at;
			}



			public void setCatch_at(Date catch_at) {
				this.catch_at = catch_at;
			}



			@Override
			public String toString() {
				return "FishCatchInfo [id=" + id + ", fishName=" + fishName + ", catchFishSize=" + catchFishSize
						+ ", catch_at=" + catch_at + "]";
			}
			
			
			
}

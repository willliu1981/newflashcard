package com.model;

public class Score {
	private Integer starcoin;
	private	Integer experience;

	public Integer getStarcoin() {
		return starcoin;
	}

	public void setStarcoin(Integer starcoin) {
		this.starcoin = starcoin;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	public void addExperience(int exp) {
		this.experience+=exp;
	}
	
	public void addCoin(int coin) {
		this.starcoin+=coin;
	}

	
}

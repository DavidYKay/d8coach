package com.d8coach.android.d8coach.model;

public class Target {

	private String name = "Jane Doe";
	private Gender gender = Gender.UNKNOWN;
	private int age = -1;
	private HairColor hairColor = HairColor.UNKNOWN;

	public Target(String name) {
		setName(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Gender getGender() {
		return gender;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}
	public HairColor getHairColor() {
		return hairColor;
	}


	public String toString() {
		//return "Fwhwgds";
		return name;
	}

}

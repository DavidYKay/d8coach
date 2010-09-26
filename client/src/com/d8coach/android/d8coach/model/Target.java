package com.d8coach.android.d8coach.model;

import android.content.res.Resources;

import com.d8coach.android.d8coach.R;
import com.d8coach.android.d8coach.util.RawReader;

public class Target {

	public static final String KEY = "tid";
	public static final String HAIR = "hair";
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String BUILD = "build";
	public static final String ETHNICITY = "ethnicity:";

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

	public static String createTableStmt(Resources resourceManager) {
		// TODO Auto-generated method stub
		return RawReader.readString(
			resourceManager,
			R.raw.create_targets
		);
	}

}

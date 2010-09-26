package com.d8coach.android.d8coach.model;

public class Objective {
	public enum ActionType {
		PICK_UP_LINE,
		ACTION,
		QUESTION
	}
	
	public Objective(String title, ActionType actionType) {
		this.title = title;
		this.actionType = actionType;
	}
	
	private String title;
	private ActionType actionType;
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	public ActionType getActionType() {
		return actionType;
	}
	
	public String toString() {
		return title;
	}

}

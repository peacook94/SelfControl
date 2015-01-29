package de.dresden.es.inf.Selfcontrol.Util;

public enum AppId {
	PHONEUNLOCKED("0"),
	BROWSER("1"),
	SMS("2");
	
	String type;
	
	private AppId(String type){
		this.type = type;
	}
	
	public static AppId parseString(int type){
		switch(type){
		case 0:
			return PHONEUNLOCKED;
		case 1:
			return BROWSER;
		case 2:
			return SMS;
		default:
			return null;
		}
	}
}

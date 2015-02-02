package de.dresden.es.inf.Selfcontrol.Util;

import java.util.Date;

/**
 * Diese Klasse ist wie ein Container. Wenn eine App gestartet wurde, wird der Container gefüllt und an die 
 * Datenbank weitergegeben, damit er eingetragen werden kann.
 * 
 * @author User
 *
 */

public class App {

	AppId id;
	Date startingTimstamp;
	int lockstate;
	int wifistate;
	
	public App(Date startingTimestamp, AppId id){
		this.id = id;
		this.startingTimstamp = startingTimestamp;
		
	}

	public AppId getId() {
		return id;
	}

	public Date getStartingTimstamp() {
		return startingTimstamp;
	}

	public void setStartingTimstamp(Date startingTimstamp) {
		this.startingTimstamp = startingTimstamp;
	}
	
	public void setLockstate(int lockstate){
		this.lockstate = lockstate;
	}
	
	public void setWifistate(int wifistate){
		this.wifistate = wifistate;
	}
	
	public int getWifistate(){
		return wifistate;
	}
	
	public int getLockstate(){
		return lockstate;
	}
}

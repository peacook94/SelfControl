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
	
	public App(Date startingTimestamp, String label, AppId id){
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
}

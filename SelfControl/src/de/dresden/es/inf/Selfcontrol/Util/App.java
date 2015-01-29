package de.dresden.es.inf.Selfcontrol.Util;

import java.util.Date;

public class App {

	AppId id;
	Date startingTimstamp;
	String label;
	long runtime;
	
	public App(Date startingTimestamp, String label, AppId id){
		this.id = id;
		this.startingTimstamp = startingTimestamp;
		this.label = label;
		
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public long getRuntime() {
		return runtime;
	}

	public void setRuntime(long runtime) {
		this.runtime = runtime;
	}
}

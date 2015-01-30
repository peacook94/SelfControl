package de.dresden.es.inf.Selfcontrol.Util;

/**
 * Dieser Enum repräsentiert den Typ der App. Wir prüfen nur auf eine abzählbar viele Anzahl von Apps, welche
 * nicht variabel sind. Deshalb können wir diesen EMun benutzen, welkcher jede App eindeutig identifiziert
 * 
 * @author User
 *
 */

public enum AppId {
	PHONEUNLOCKED("0"),
	BROWSER("1"),
	HANGOUTS("2");
	
	String type;
	
	private AppId(String type){
		this.type = type;
	}
	
	/**
	 * Gegensatz zu toString(). Wandelt einen String ("0", "1", "2") in eine AppId um
	 * 
	 * @param type
	 * @return
	 */
	
	public static AppId parseString(int type){
		switch(type){
		case 0:
			return PHONEUNLOCKED;
		case 1:
			return BROWSER;
		case 2:
			return HANGOUTS;
		default:
			return null;
		}
	}
	
	/**
	 * Die Methode bringt eine Konvertierung des AppId-Typs in einen String, welcher den Status repräsentiert
	 * und welcher mit der Methode parseString(int type) wieder in eine AppId verwandelt werden kann.
	 * 
	 * @param type welche AppId soll in einen String gewandelt werden
	 * @return "0","1","2" as Strings
	 */
	
	
	public String toString(AppId type){
		if(type == PHONEUNLOCKED){
			return "0";
		}
		else if(type == BROWSER)
			return "1";
		else if(type == HANGOUTS)
			return "2";
		else return null;
			
	}
}

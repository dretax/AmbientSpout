package me.dretax.ambientspout.model.datatype;


public class Song{

	/**
	 * 
	 */
	private String url;
	private int duration;
	
	public Song(String url, int duration){
		this.url = url;
		this.duration = duration;
	}
	
	
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public String toString(){
		return duration + " " + url.replace(" ", "%20");
	}
	
	/**
	 * @param s duration + " " + url
	 * @return Song built from the string.
	 */
	public static Song buildFromString(String s){
		return new Song (s.split(" ")[1], Integer.parseInt(s.split(" ")[0]));
	}
}

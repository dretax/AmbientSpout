package me.dretax.ambientspout.model.datatype;

import java.util.ArrayList;


import org.bukkit.Location;

public class MusicArea {

	private ArrayList<Song> playList = new ArrayList<Song>();
	private Location loc1;
	private Location loc2;
	private int priority;
	
	/**
	 * @param playList
	 * @param priority
	 */
	public MusicArea(ArrayList<Song> playList, int priority) {
		this.setPlayList(playList);
		this.setPriority(priority);
	}
	
	public boolean isInArea(Location loc){
		return (((loc.getBlockX() <= loc1.getBlockX() && loc.getBlockX() >= loc2.getBlockX())) || ((loc.getBlockX() >= loc1.getBlockX() && loc.getBlockX() <= loc2.getBlockX())) 
		&& ((loc.getBlockY() <= loc1.getBlockY() && loc.getBlockY() >= loc2.getBlockY())) || ((loc.getBlockY() >= loc1.getBlockY() && loc.getBlockY() <= loc2.getBlockY())) 
		&& ((loc.getBlockZ() <= loc1.getBlockZ() && loc.getBlockZ() >= loc2.getBlockZ())) || ((loc.getBlockZ() >= loc1.getBlockZ() && loc.getBlockZ() <= loc2.getBlockZ())));
	}

	public ArrayList<Song> getPlayList() {
		return playList;
	}

	public void setPlayList(ArrayList<Song> playList) {
		this.playList = playList;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
}

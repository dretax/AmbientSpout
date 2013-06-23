package me.dretax.ambientspout.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dretax.ambientspout.AmbientSpout;
import me.dretax.ambientspout.controller.FileHandler;
import me.dretax.ambientspout.model.datatype.EffectLoop;
import me.dretax.ambientspout.model.datatype.MusicArea;
import me.dretax.ambientspout.model.datatype.Song;
import me.dretax.ambientspout.view.MainGUI;

import org.bukkit.entity.Player;

public class Model {

	public static Map<String, Integer> playerTaskID = new HashMap<String, Integer>();
	public static Map<String, String> playerCurrentSong = new HashMap<String, String>();
	public static Map<Player, MusicArea> playerMusicArea = new HashMap<Player, MusicArea>();
	public static Map<String, MainGUI> playerPopup = new HashMap<String, MainGUI>();
	public static Map<String, Boolean> playerMusicEnabled = new HashMap<String, Boolean>();
	
	public static List<Song> ambientMusic = new ArrayList<Song>();
	public static List<Song> effectLoopsMusic = new ArrayList<Song>();
	
	public static List<EffectLoop> effectLoops = new ArrayList<EffectLoop>();
	
	public static void updateGUI(Player p){
		if (playerPopup.containsKey(p.getName())) {
			playerPopup.get(p.getName()).updateCurrentSong();
		}
	}
	
	/**
	 *  Serialize!
	 */
	public static void savePlayerSettings(){
		try {
			FileHandler.save(playerMusicEnabled, "plugins/AmbientSpout/playerdata1.dat");
		} catch (Exception e) {
			AmbientSpout.log.severe("[AmbientSpout] Something went wrong saving player settings to files!");
			e.printStackTrace();
		}
	}
	
	/**
	 *  Deserialize!
	 */
	@SuppressWarnings("unchecked")
	public static void loadPlayerSettings(){
		try {
			File f = new File("plugins/AmbientSpout/playerdata1.dat");
			if (!f.exists()) {
				f.createNewFile();
				System.out.println("Creating playerdata1.dat");
				savePlayerSettings();
				return;
			}
			playerMusicEnabled = (Map<String, Boolean>) FileHandler.load("plugins/AmbientSpout/playerdata1.dat");
		} catch (Exception e) {
			AmbientSpout.log.severe("[AmbientSpout] Something went wrong loading player settings from files!");
			e.printStackTrace();
		}
	}
	
	
}

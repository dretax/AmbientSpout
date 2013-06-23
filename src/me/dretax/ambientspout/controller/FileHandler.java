package me.dretax.ambientspout.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.ChatColor;

import me.dretax.ambientspout.AmbientSpout;
import me.dretax.ambientspout.model.Model;
import me.dretax.ambientspout.model.datatype.EffectLoop;
import me.dretax.ambientspout.model.datatype.Song;


public class FileHandler {

	/**
	 * @param name Ambient for example
	 */
	public static void loadSongs(String name, List<Song> listToAddTo){
		try {
			File f = new File("plugins/AmbientSpout/Resources/" + name + ".txt");
			
			if (f.createNewFile()){
				AmbientSpout.log.info("[AmbientSpout] Resources/" + name + ".txt was not found, created one for you :)! Example line: 123 google.com/derp.ogg");
				return;
			}
			
			FileReader fr = new FileReader("plugins/AmbientSpout/Resources/" + name + ".txt");
			BufferedReader bw = new BufferedReader (fr);
			String string;
			while ((string = bw.readLine()) != null){
				listToAddTo.add(Song.buildFromString(string));
			}
			bw.close();
			fr.close();
		}
		catch(Exception e){
			AmbientSpout.log.severe("[AmbientSpout]" + ChatColor.RED + "Something went from loading " + name + ".txt");
			e.printStackTrace();
		}
			
	}
	
	public static void saveEffectLoops(){
		try {
			FileWriter fw = new FileWriter ("plugins/AmbientSpout/effectloops.txt");
			BufferedWriter bw = new BufferedWriter (fw );
			for(EffectLoop ccdo : Model.effectLoops){
				if (ccdo != null)
					bw.write(ccdo.toString());
				bw.newLine();
				
			}
			bw.close();
			fw.close();
		}
		catch (Exception e){
			AmbientSpout.log.severe("[AmbientSpout]" + ChatColor.RED + "Something went from saving effectloops.txt!!");
			e.printStackTrace();
		}
	}
	
	public static void loadEffectLoops(){
		try {
		File fw = new File("plugins/AmbientSpout/effectloops.txt");
		if (!fw.exists()){
			AmbientSpout.log.info("[AmbientSpout] effectloops.txt not found, probably not used yet.");
			Model.effectLoops.add(new EffectLoop(new Song("http://www.ilovewavs.com/Effects/Birds/Loon.wav", 302), "RiverLoon", "world", -93, 65, 165, 90, 20, false));
			return;
		}
	
		FileReader fr = new FileReader("plugins/AmbientSpout/effectloops.txt");
		BufferedReader bw = new BufferedReader (fr);
		String string;
		while ((string = bw.readLine()) != null){
			Model.effectLoops.add(EffectLoop.buildFromString(string));
		}
		bw.close();
		fr.close();
		}
		catch(Exception e){
			AmbientSpout.log.severe("[AmbientSpout]" + ChatColor.RED + "Something went from loading effectloops.txt!!");
			e.printStackTrace();
		}
	}

	     
	public static void save(Object obj,String path) throws Exception
	    {
		    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		    oos.writeObject(obj);
		    oos.flush();
		    oos.close();
	    }
	public static Object load(String path) throws Exception
	    {
		    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		    Object result = ois.readObject();
		    ois.close();
		    return result;
	    }

	
	
}

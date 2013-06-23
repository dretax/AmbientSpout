package me.dretax.ambientspout.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


import me.dretax.ambientspout.AmbientSpout;
import me.dretax.ambientspout.model.datatype.Song;

public class ResourcesMain {

	AmbientSpout plugin;
	private static ResourcesMain callback;
	@SuppressWarnings("unused")
	private WebServer webserver;
	
	
	public ResourcesMain(AmbientSpout instance){
		plugin = instance;
	}
	

	protected static ResourcesMain getCallback() {
		return callback;
	}
	
	public void startResources() {
		callback = this;

		File resourcesDir = getResourceDir();

		if(!resourcesDir.exists()) {
			resourcesDir.mkdir();
		}
		createFolder("Ambient");
		createFolder("EffectLoops");
		
		try {
			webserver = new WebServer(plugin.getConfig().getInt("HostPort"), resourcesDir);
		} catch (IOException e) {
				System.out.print("Could not start the webserver.");
				e.printStackTrace();
		}
		
		System.out.print("[Resources] Resources for AmbientSpout loaded.");
	}
	
	
	
	public File getResourceDir() {
		return new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator);
	}
	
	/*
	
	public ArrayList<String> getSongURLs(String path) throws ResourceDoesNotExist{
		File[] fileArray = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator + path).listFiles();
		//plugin.debug("Getting png files from " + path);
		ArrayList<String> urlList = new ArrayList<String>();
		
		for(int i = 0; i < fileArray.length ; i++){
			if (fileArray[i].getName().endsWith(".ogg") || fileArray[i].getName().endsWith(".wav")){
				urlList.add(Resources.get(fileArray[i].getAbsolutePath().replace(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator, "")));
			}
		}
		
		return urlList;
	}*/
	
	public ArrayList<Song> getSongList(String path) throws ResourceDoesNotExist, UnsupportedAudioFileException, IOException{
		File[] fileArray = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator + path).listFiles();
		//plugin.debug("Getting png files from " + path);
		ArrayList<Song> songList = new ArrayList<Song>();
		
		for(int i = 0; i < fileArray.length ; i++){
			if (fileArray[i].getName().endsWith(".wav")){
				File f = fileArray[i];
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
				AudioFormat format = audioInputStream.getFormat();
				long frames = audioInputStream.getFrameLength();
				double durationInSeconds = (frames+0.0) / format.getFrameRate();  
				
				songList.add(new Song(Resources.get(fileArray[i].getAbsolutePath().replace(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator, "")), (int) durationInSeconds +1));
			}
			else if (fileArray[i].getName().endsWith(".ogg")){
				String s = fileArray[i].getAbsolutePath().replace(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator, "");
				String r;
				int songLength = 210;
				//System.out.println("Start s: " + s);
				s = s.replace(".ogg", "");
				//System.out.println("Removed .ogg " + s);
				if (s.length() > 2) {
					r = s.substring(s.length()-3, s.length());
					try {
						songLength = Integer.parseInt(r);
					}
					catch (NumberFormatException e){
						AmbientSpout.log.severe("[AmbientSpout] Song length of \n" + fileArray[i].getAbsolutePath().replace(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator, "") 
								+ " unknown! \nDefaulting to 3min -> Last 3 chars of filename = length!\n");
					}
				}
				else{
					AmbientSpout.log.severe("[AmbientSpout] Song length of \n" + fileArray[i].getAbsolutePath().replace(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator, "") 
							+ " unknown! \nDefaulting to 3min -> Last 3 chars of filename= length!\n");
				}
				
				songList.add(new Song(Resources.get(fileArray[i].getAbsolutePath().replace(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator, "")), songLength));
			}
			
		}
		
		return songList;
	}
	
	public ArrayList<String> getSubFolders(String path){
		ArrayList<String> fileList = new ArrayList<String>();
		//plugin.debug("Getting subfolders of " + path);
		File[] fileArray = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator + path).listFiles();
		
		for(int i = 0; i < fileArray.length ; i++){
			if (fileArray[i].isDirectory()){
				fileList.add(fileArray[i].getName());
			}
		}
		
		return fileList;
		
	}
	
	/**
	 * @param path from the Resources folder.
	 */
	public void createFolder(String path){
		File f = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "Resources" + File.separator + path);
		if (!f.exists()){
		//	plugin.debug("Creating folder " + path);
			f.mkdir();
		}
	}

	
	
	
	
}

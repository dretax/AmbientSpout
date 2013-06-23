package me.dretax.ambientspout.model;

import me.dretax.ambientspout.AmbientSpout;

public class Settings {

	
	private static int delayBetweenSongs = 20;
	private static int delayRandomness = 2;
	public static int fadeOutAreaChange = 4;
	public static boolean defaultOn = true;
	public static boolean musicAreaEnabled = false;
	public static boolean enableWebServer = true;
	public static boolean useBackgroundImage = true;
	public static boolean resetSongOnRespawn = true;
	public static boolean showCurrentSong = true;
	public static boolean effectLoopsEnabled = true;
	public static boolean notifyPlayerEffectLoopDownload = true;
	/**
	 *  Loads values from config.yml (should be used for reload)
	 */
	public static void loadConfig(){
		delayBetweenSongs 	= AmbientSpout.callback.getConfig().getInt(		"Settings.DelayBetweenSongs", 20);
		delayRandomness 	= AmbientSpout.callback.getConfig().getInt(		"Settings.DelayRandomness"	, 2);
		fadeOutAreaChange 	= AmbientSpout.callback.getConfig().getInt(		"Settings.FadeOutLength", 3);
		defaultOn			= AmbientSpout.callback.getConfig().getBoolean(	"Settings.DefaultOn"		 , true);
		musicAreaEnabled 	= AmbientSpout.callback.getConfig().getBoolean(	"Settings.MusicAreaEnabled"	 , true);
		enableWebServer 	= AmbientSpout.callback.getConfig().getBoolean(	"Settings.EnableWebServer"	 , true);
		useBackgroundImage  = AmbientSpout.callback.getConfig().getBoolean(	"Settings.UseBackgroundImage", true);
		resetSongOnRespawn  = AmbientSpout.callback.getConfig().getBoolean(	"Settings.ResetSongOnRespawn", true);
		showCurrentSong 	= AmbientSpout.callback.getConfig().getBoolean( "Settings.ShowCurrentSong"	 , true);
		effectLoopsEnabled	= AmbientSpout.callback.getConfig().getBoolean( "Settings.EffectLoopsEnabled", true);
		notifyPlayerEffectLoopDownload = AmbientSpout.callback.getConfig().getBoolean("Settings.NotifyPlayerEffectLoopDownload", true);
	}
	
	/**
	 * @return delay to be had between songs.
	 */
	public static int getDelayBetweenSongs(){
		return (AmbientSpout.random.nextInt(delayRandomness*2)-delayRandomness + delayBetweenSongs);
	}
	
	
	
	
}

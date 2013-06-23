package me.dretax.ambientspout;

import java.util.Random;
import java.util.logging.Logger;
import me.dretax.ambientspout.controller.AmbientSpoutKeyListener;
import me.dretax.ambientspout.controller.AmbientSpoutListener;
import me.dretax.ambientspout.controller.AmbientSpoutScreenListener;
import me.dretax.ambientspout.controller.FileHandler;
import me.dretax.ambientspout.metrics.MetricsHandler;
import me.dretax.ambientspout.model.Model;
import me.dretax.ambientspout.model.Settings;
import me.dretax.ambientspout.model.datatype.EffectLoop;
import me.dretax.ambientspout.resources.ResourcesMain;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;


public class AmbientSpout extends JavaPlugin {

	public static Random random = new Random();
	public static AmbientSpout callback;
	public static Logger log = Logger.getLogger("Minecraft");
	public int DelayBetweenSongs, DelayRandomness, FadeOutLength;
	public String BackgroundImage;
	public boolean DefaultOn, MusicAreaEnabled, DevMessage, UseBackgroundImage, ResetSongOnRespawn, ShowCurrentSong, EffectLoopsEnabled, NotifyPlayerEffectLoopDownload, EnableWebServer;
	
	
	@Override
	public void onDisable() {
		Model.savePlayerSettings();
	}
	
	@Override
	public void onEnable() {
		callback = this;
		getConfig().options().copyDefaults(true);
		
		saveConfig();
		Settings.loadConfig();
		saveConfig();
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new AmbientSpoutListener(), this);
		pm.registerEvents(new AmbientSpoutScreenListener(), this);
		
		
		if (Settings.enableWebServer) 
			webServerInit();
		
		FileHandler.loadSongs("Ambient", Model.ambientMusic);
		FileHandler.loadSongs("EffectLoops", Model.effectLoopsMusic);
		
		Model.loadPlayerSettings();
		
		log.info("[AmbientSpout] Found AmbientMusic: \n" + Model.ambientMusic.toString());
		log.info("[AmbientSpout] Found EffectLoopSounds: \n" + Model.effectLoopsMusic.toString());
		
		if (Settings.effectLoopsEnabled) {
			FileHandler.loadEffectLoops();
			System.out.println("[AmbientSpout] Starting EffectLoops!");
			for (EffectLoop e : Model.effectLoops) {
				if (e.isActive())
					e.startEffect();
			}
			FileHandler.saveEffectLoops();
		}
		getCommand("ambientspout").setExecutor(this);
		
		
		
		SpoutManager.getKeyBindingManager().registerBinding("AmbientSpout_MainPopup", Keyboard.KEY_EQUALS, "Opens the AmbientSpout main screen.", new AmbientSpoutKeyListener(), this);
		
		MetricsHandler.metricsGo();
		System.out.println("[AmbientSpout] All systems GO!");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("amb.reload")) {
					ConfigReload();
				}
				else sender.sendMessage(ChatColor.RED + "You Don't Have Permission to do this!");
				
			}
		}
		return false;
	}
	
	public void ConfigReload() {
		reloadConfig();
		DelayBetweenSongs = getConfig().getInt("Settings.DelayBetweenSongs");
		DelayRandomness = getConfig().getInt("Settings.DelayRandomness"); 
		FadeOutLength = getConfig().getInt("Settings.FadeOutLength");
		DefaultOn = getConfig().getBoolean("Settings.DefaultOn");
		MusicAreaEnabled = getConfig().getBoolean("Settings.MusicAreaEnabled"); 
		DevMessage = getConfig().getBoolean("Settings.DevMessage"); 
		UseBackgroundImage = getConfig().getBoolean("Settings.UseBackgroundImage"); 
		ResetSongOnRespawn = getConfig().getBoolean("Settings.ResetSongOnRespawn");
		ShowCurrentSong = getConfig().getBoolean("Settings.ShowCurrentSong"); 
		EffectLoopsEnabled = getConfig().getBoolean("Settings.EffectLoopsEnabled");
		NotifyPlayerEffectLoopDownload = getConfig().getBoolean("Settings.NotifyPlayerEffectLoopDownload");
		EnableWebServer = getConfig().getBoolean("Settings.EnableWebServer");
		BackgroundImage = getConfig().getString("Settings.BackgroundImage");
		reloadConfig();
	}
	
	
	
	
	
	
	
	private void webServerInit(){
		//debug("Starting Resources server for AmbientSpout.");
		ResourcesMain resMain = new ResourcesMain(this);
		resMain.startResources();
		try {
			Model.ambientMusic = resMain.getSongList("Ambient");
			Model.effectLoopsMusic = resMain.getSongList("EffectLoops");
			
			//debug(resourcesMain.getSubFolders("PubArea" + File.separator + "asasass" + File.separator).toString());
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
}

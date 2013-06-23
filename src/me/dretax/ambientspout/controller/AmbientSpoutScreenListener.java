package me.dretax.ambientspout.controller;

import me.dretax.ambientspout.model.Model;
import me.dretax.ambientspout.view.EffectLoopGUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class AmbientSpoutScreenListener implements Listener{
	
	
	@EventHandler
    public void onButtonClick(ButtonClickEvent event) {
		SpoutPlayer player = event.getPlayer(); //The SpoutPlayer that pressed the button.
		   
        if (!(Model.playerPopup.containsKey(player.getName()))){ //Have we NOT ever added any information in the playerPopup hashmap linked with this player?
            return;
        }
   
        if (Model.playerPopup.get(player.getName()) != event.getButton().getScreen()){ //Is the screen that the button was pressed on NOT the screen that we once saved to the hashmap?
            return; // If this is the case, it's probably a screen that is part of MineCraft or of another plugin.
        }
        
       if ( event.getButton().getText().equals("Next Song") || event.getButton().getText().equals("Music On")){
    	   Model.playerMusicEnabled.put(player.getName(), true);
    	   MusicHandler.nextSong(event.getPlayer(), false);
    	   Model.playerPopup.get(player.getName()).updateMusicEnabled();
    	   Model.savePlayerSettings();
    	   
       }
       else if (event.getButton().getText().equals("Music Off")){
    	 
    	   Model.playerMusicEnabled.put(player.getName(), false);
    	   MusicHandler.stopMusic(player, false);
    	   Model.playerPopup.get(player.getName()).updateMusicEnabled();
    	   Model.savePlayerSettings();
       }
       
       else if (event.getButton().getText().equals("Manage EffectLoops")){
    	  player.getMainScreen().closePopup();
    	  player.getMainScreen().attachPopupScreen(new EffectLoopGUI());
       }
    }
	
}

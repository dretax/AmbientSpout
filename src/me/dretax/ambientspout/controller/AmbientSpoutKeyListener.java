package me.dretax.ambientspout.controller;

import me.dretax.ambientspout.model.Model;
import me.dretax.ambientspout.view.MainGUI;

import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;

public class AmbientSpoutKeyListener implements BindingExecutionDelegate{


	@Override
	public void keyPressed(KeyBindingEvent arg0) {
		if (!Model.playerPopup.containsKey(arg0.getPlayer().getName())) {
			MainGUI pop = new MainGUI(arg0.getPlayer());
			Model.playerPopup.put(arg0.getPlayer().getName(), pop);
		}
			Model.playerPopup.get(arg0.getPlayer().getName()).setVisible(true);
			Model.playerPopup.get(arg0.getPlayer().getName()).setDirty(true);
		arg0.getPlayer().getMainScreen().attachPopupScreen(Model.playerPopup.get(arg0.getPlayer().getName()));
	}

	@Override
	public void keyReleased(KeyBindingEvent arg0) {
		
	}

}

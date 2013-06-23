package me.dretax.ambientspout.view;

import me.dretax.ambientspout.AmbientSpout;
import me.dretax.ambientspout.model.Model;
import me.dretax.ambientspout.model.datatype.EffectLoop;

import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.GenericPopup;
import org.getspout.spoutapi.gui.ListWidgetItem;
import org.getspout.spoutapi.gui.WidgetAnchor;

public class EffectLoopGUI extends GenericPopup{

	
	
	private GenericListWidget list;

	public EffectLoopGUI(){
		list = new GenericListWidget();
		list.setAnchor(WidgetAnchor.TOP_LEFT);
		//list.shiftYPos(- list.getHeight() / 2); 
		list.setHeight(180).setWidth(240);
		
		for (EffectLoop el : Model.effectLoops) {
			list.addItem(buildItem(el));
		}
		this.attachWidget(AmbientSpout.callback, list);
		
	}
	
	private ListWidgetItem buildItem(EffectLoop e){
		return new ListWidgetItem(e.getName(), e.getSong().getUrl().split("/")[e.getSong().getUrl().split("/").length-1], "http://dl.dropbox.com/u/43693599/SoftRedux/effectloop.png");
	}
	
}

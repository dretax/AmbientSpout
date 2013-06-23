package me.dretax.ambientspout.metrics;

import java.io.IOException;

import org.bukkit.Bukkit;

import me.dretax.ambientspout.AmbientSpout;
import me.dretax.ambientspout.metrics.Metrics.Graph;
import me.dretax.ambientspout.model.Model;
import me.dretax.ambientspout.model.Settings;

public class MetricsHandler {

	public static void metricsGo(){
		try {
		    Metrics metrics = new Metrics(AmbientSpout.callback);
		    
		    
		    metrics.addCustomData(new Metrics.Plotter("Total Ambient Song Count") {

		        @Override
		        public int getValue() {
		            return Model.ambientMusic.size();
		        }

		    });
		    
		    metrics.addCustomData(new Metrics.Plotter("Total EffectLoop Count") {

		        @Override
		        public int getValue() {
		            return Model.effectLoops.size();
		        }

		    });
		    
		 // Construct a graph, which can be immediately used and considered as valid
		    Graph graph = metrics.createGraph("WebServer Usage");

		    // Diamond sword
		    graph.addPlotter(new Metrics.Plotter("Enabled") {

		            @Override
		            public int getValue() {
		                    return Settings.enableWebServer ? 1 : 0;
		            }

		    });

		    // Iron sword
		    graph.addPlotter(new Metrics.Plotter("Disabled") {

		            @Override
		            public int getValue() {
		                    return Settings.enableWebServer ? 0 : 1;
		            }

		    });
		    if (Settings.enableWebServer && !AmbientSpout.callback.getConfig().getString("HostAddress").equals("127.0.0.1"))  {
		    	Graph graph2 = metrics.createGraph("Most Populated Server w/ WebServer Enabled");
		    	graph2.addPlotter(new Metrics.Plotter( AmbientSpout.callback.getConfig().getString("HostAddress") ) {
		    		
		    		@Override
		            public int getValue() {
		                    return Bukkit.getServer().getOnlinePlayers().length;
		            }
		    	});
		    }
		  
		    
		    
		    metrics.start();
		    
		} catch (IOException e) {
			System.out.println("Failed to submit stats to Metrics for AmbientSpout :< Nothing to worry about..");
		    // Failed to submit the stats :-(
		}
		
		
	}
}

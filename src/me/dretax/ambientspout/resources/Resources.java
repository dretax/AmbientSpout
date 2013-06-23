package me.dretax.ambientspout.resources;

import java.io.File;


public class Resources {
	public static String get(String resource) throws ResourceDoesNotExist {
		if(!doesResoruceExists(resource))
			throw new ResourceDoesNotExist(resource);
		
		String s = "http://"+ResourcesMain.getCallback().plugin.getConfig().getString("HostAddress")+":"+ResourcesMain.getCallback().plugin.getConfig().getInt("HostPort")+"/"+resource;
		return s.replace(File.separator, "/");
		
		
	}


	private static boolean doesResoruceExists(String resource) {
		resource = resource.replace("/", File.separator);
		return new File(ResourcesMain.getCallback().getResourceDir().getAbsolutePath()+File.separator+resource).exists();
	}
}

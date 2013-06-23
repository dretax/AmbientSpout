package me.dretax.ambientspout.resources;

public class ResourceDoesNotExist extends Exception{
	private static final long serialVersionUID = 1L;

	public ResourceDoesNotExist(String resource) {
		super("The resource "+ resource +" does not exist.");
	}

}

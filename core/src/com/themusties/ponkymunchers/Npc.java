package com.themusties.ponkymunchers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Npc {
	public String name;
	public final Vector2 position;
	
	public Texture texture;
	
	public Npc(String name, Vector2 position, Texture texture) {
		this.name = name;
		this.position = position;
		this.texture = texture;
	}
	
	boolean shouldInteractWith(Vector2 position) {
		return this.position.cpy().sub(position).len() < 50.0f;
	}
	
	public void interact(Player player) {
		if (!shouldInteractWith(player.position)) {
			return;
		}
		
		System.out.println("My name is " + this.name + " and I like your hair!!");
	}
}

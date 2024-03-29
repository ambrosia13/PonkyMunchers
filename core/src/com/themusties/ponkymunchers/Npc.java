package com.themusties.ponkymunchers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Npc implements DrawableSprite {
	public String name;
	public final Vector2 position;
	
	public Texture texture;
	
	public Npc(String name, Vector2 position, Texture texture) {
		this.name = name;
		this.position = position;
		this.texture = texture;
	}
	
	boolean shouldInteractWith(Vector2 position) {
		return this.position.cpy().sub(position).len() < Math.max(this.texture.getWidth(), this.texture.getHeight());
	}
	
	public void interact(Player player) {
		if (!shouldInteractWith(player.position)) {
			return;
		}
		
		System.out.println("My name is " + this.name + " and I like your hair!!");
	}
	
	@Override
	public void drawTo(SpriteBatch batch) {
		float halfTextureBoundsX = this.texture.getWidth() / 2.0f;
		float halfTextureBoundsY = this.texture.getHeight() / 2.0f;
		
		batch.draw(
			this.texture,
			this.position.x - halfTextureBoundsX,
			this.position.y - halfTextureBoundsY,
			this.texture.getWidth(),
			this.texture.getHeight()
		);
	}
}

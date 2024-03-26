package com.themusties.ponkymunchers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class PonkyMunchersGame extends ApplicationAdapter {
	SpriteBatch batch;
	
	Player player;
	ArrayList<Npc> npcs;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		player = new Player(
			new Vector2(),
			new Vector2(),
			30.0f,
			0.9f,
			new Texture("badlogic.jpg")
		);
		
		npcs = new ArrayList<>();
		
		npcs.add(new Npc("Susan Bones", new Vector2(150.0f, 150.0f), new Texture("badlogic.jpg")));
		npcs.add(new Npc("Grirasa", new Vector2(450.0f, 150.0f), new Texture("badlogic.jpg")));
		npcs.add(new Npc("Tithany", new Vector2(150.0f, 450.0f), new Texture("badlogic.jpg")));
		npcs.add(new Npc("Jumasa", new Vector2(450.0f, 450.0f), new Texture("badlogic.jpg")));
	}
	
	@Override
	public void render() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.F)) {
			System.out.println(player.position);
		}
		
		ScreenUtils.clear(0.75f, 0.75f, 0.75f, 1);
		batch.begin();
		
		player.updateVelocity();
		player.applyFriction();
		player.updatePosition();
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		int middleX = width / 2;
		int middleY = height / 2;
		
		batch.draw(player.texture, middleX, middleY, 50, 50);
		
		npcs.forEach(npc -> batch.draw(
			npc.texture,
			(int) (npc.position.x - player.position.x + middleX),
			(int) (npc.position.y - player.position.y + middleY),
			50,
			50
		));
		
		npcs.forEach(npc -> npc.interact(player));
		
		batch.end();
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		player.texture.dispose();
		
		npcs.forEach(npc -> npc.texture.dispose());
	}
}

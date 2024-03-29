package com.themusties.ponkymunchers;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class PonkyMunchersGame implements ApplicationListener {
	SpriteBatch batch;
	
	OrthographicCamera camera;
	
	Player player;
	ArrayList<Npc> npcs;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		float aspectRatio = (float) Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
		float viewportSize = 300.0f; // meters
		
		camera = new OrthographicCamera(viewportSize, viewportSize * aspectRatio);
		//camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 0.0f);
		camera.update();
		
		player = new Player(
			new Vector2(),
			new Vector2(),
			30.0f,
			0.9f
		);
		
		npcs = new ArrayList<>();
		
		npcs.add(new Npc("Susan Bones", new Vector2(150.0f, 150.0f), new Texture("bufftomato.png")));
		npcs.add(new Npc("Grirasa", new Vector2(450.0f, 150.0f), new Texture("bufftomato.png")));
		npcs.add(new Npc("Tithany", new Vector2(150.0f, 450.0f), new Texture("bufftomato.png")));
		npcs.add(new Npc("Jumasa", new Vector2(450.0f, 450.0f), new Texture("bufftomato.png")));
	}
	
	@Override
	public void resize(int width, int height) {
	
	}
	
	@Override
	public void render() {
		ScreenUtils.clear(0.75f, 0.95f, 0.75f, 1.0f);
		
		player.updateVelocity();
		player.applyFriction();
		player.updatePosition();
		
		npcs.forEach(npc -> npc.interact(player));
		
		camera.position.set(new Vector3(player.position.x, player.position.y, 0.0f));
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		// Draw to the screen
		batch.begin();
		
		player.drawTo(batch);
		npcs.forEach(npc -> npc.drawTo(batch));
		
		batch.end();
	}
	
	@Override
	public void pause() {
		System.out.println("Game paused");
	}
	
	@Override
	public void resume() {
		System.out.println("Game resumed");
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		player.texture.dispose();
		
		npcs.forEach(npc -> npc.texture.dispose());
	}
}

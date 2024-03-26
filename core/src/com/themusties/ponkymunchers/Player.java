package com.themusties.ponkymunchers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {
	public Vector2 position;
	public Vector2 velocity;
	
	public float movementSpeed;
	public float frictionCoefficient;
	
	public Texture texture;
	
	public Player(Vector2 position, Vector2 velocity, float movementSpeed, float frictionCoefficient, Texture texture) {
		this.position = position;
		this.velocity = velocity;
		
		this.movementSpeed = movementSpeed;
		this.frictionCoefficient = frictionCoefficient;
		
		this.texture = texture;
	}
	
	public void updateVelocity() {
		var velocityDiff = new Vector2();
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			velocityDiff.y += 1.0f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			velocityDiff.y -= 1.0f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			velocityDiff.x += 1.0f;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			velocityDiff.x -= 1.0f;
		}
		
		velocityDiff.nor();
		velocityDiff.scl(movementSpeed);
		
		velocityDiff.scl(Gdx.graphics.getDeltaTime());
		
		this.velocity.add(velocityDiff);
	}
	
	public void applyFriction() {
		this.velocity.scl(this.frictionCoefficient);
	}
	
	public void updatePosition() {
		this.position.add(this.velocity);
	}
}

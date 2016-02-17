/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaterasu.flappynerd.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 * @author Carl
 */
public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private int framesToAnimate;
    private Sound flap;
    
    
    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        texture =  new Texture ("aniviaAnimation.png");
        framesToAnimate = 3;
        birdAnimation = new Animation(texture, framesToAnimate, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth()/framesToAnimate, texture.getHeight()/framesToAnimate);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }
    
    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        getPosition().add(MOVEMENT*dt, velocity.y, 0);
        if(position.y < 0)
            position.y = 0;
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }
    
    public Rectangle getBounds(){
        return bounds;
    }
    
    /**
     * @return the position
     */
    public Vector3 getPosition() {
        return position;
    }

    /**
     * @return the bird
     */
    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    
    public void jump(){
        velocity.y = 300;
        flap.play(0.5f);
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}

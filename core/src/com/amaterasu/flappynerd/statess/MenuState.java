/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaterasu.flappynerd.statess;

import com.amaterasu.flappynerd.FlappyNerd;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Carl
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyNerd.WIDTH/2, FlappyNerd.HEIGHT/2);
        background = new Texture("menuBG.png");
        playBtn = new Texture("shittyPlay.png");
    }

    @Override
    public void handleInput() {
         if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(playBtn, cam.position.x - (playBtn.getWidth()/2), cam.position.y);
        sb.end();
        
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaterasu.flappynerd.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

/**
 *
 * @author Carl
 */
public class Tube {
    public static final int TUBE_WIDTH = 50;
    public static final int COLLISION_OFFSET = 20;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, botTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle boundsTop, boundsBot;
    private Random rand;
    
    
    public Tube(float x){
        topTube = new Texture("icePillarTop.png");
        botTube = new Texture("icePillarBottom.png");
        rand = new Random();
        
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, getPosTopTube().y - TUBE_GAP - getBottomTube().getHeight());
        
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y + COLLISION_OFFSET, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y - COLLISION_OFFSET, botTube.getWidth(), botTube.getHeight());
    }
    
    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, getPosTopTube().y - TUBE_GAP - getBottomTube().getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y+ COLLISION_OFFSET);
        boundsBot.setPosition(posBotTube.x, posBotTube.y- COLLISION_OFFSET);
    }
    
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }
    
    /**
     * @return the posTopTube
     */
    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    /**
     * @return the posBotTube
     */
    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    /**
     * @return the topTube
     */
    public Texture getTopTube() {
        return topTube;
    }

    /**
     * @return the botTube
     */
    public Texture getBottomTube() {
        return botTube;
    }

    public void dispose() {
        topTube.dispose();
        botTube.dispose();
    }
}

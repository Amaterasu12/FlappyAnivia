/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaterasu.flappynerd.statess;

import com.amaterasu.flappynerd.FlappyNerd;
import com.amaterasu.flappynerd.sprites.Bird;
import com.amaterasu.flappynerd.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Carl
 */
public class PlayState extends State{
    private static final int TUBE_SPACING = 175;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_OFFSET = -50;
    
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private boolean collided = false;
    private Array<Tube> tubes;
    
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(FlappyNerd.WIDTH/10, FlappyNerd.HEIGHT/4);
        cam.setToOrtho(false, FlappyNerd.WIDTH/2, FlappyNerd.HEIGHT/2);
        bg = new Texture("bg.png");
        tubes = new Array<Tube>();
        ground = new Texture("ground.png");
        groundPos1 =  new Vector2(cam.position.x-cam.viewportWidth/2, GROUND_OFFSET);
        groundPos2 = new Vector2((cam.position.x-cam.viewportWidth/2) + ground.getWidth(), GROUND_OFFSET);
        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        updateGround();
        cam.position.x = bird.getPosition().x + 80;
        
        for(Tube tube: tubes){
            
            if((cam.position.x-(cam.viewportWidth/2)) > (tube.getPosTopTube().x + Tube.TUBE_WIDTH))
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT);
            
            if(tube.collides(bird.getBounds())){
                collided = true;
            }
        }
        if(bird.getPosition().y <= ground.getHeight() + GROUND_OFFSET)
            collided = true;
        
        if(collided)
            gsm.set(new PlayState(gsm));
        
        cam.update();;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, (cam.position.x - cam.viewportWidth/2), 0, cam.viewportWidth, cam.viewportHeight);
        sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube: tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        bg.dispose();
        ground.dispose();
        for(Tube tube: tubes){
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }
    
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth()*2, 0);
        if(cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth()*2, 0);
    }
}

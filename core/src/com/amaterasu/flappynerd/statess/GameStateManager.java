/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amaterasu.flappynerd.statess;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;

/**
 *
 * @author Carl
 */
public class GameStateManager {
    private Stack<State> stateStack;
    
    public GameStateManager(){
        stateStack = new Stack<State>();
    }
    
    public void push(State state){
        stateStack.push(state);
    }
    
    public void pop(){
        stateStack.pop().dispose();
    }
    
    public void set(State state){
        stateStack.pop().dispose();
        stateStack.push(state);
    }
    
    public void update(float dt){
        stateStack.peek().update(dt);
    }
    
    public void render(SpriteBatch sb){
        stateStack.peek().render(sb);
    }
}

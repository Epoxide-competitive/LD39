package org.epoxide.ld39.client.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import org.epoxide.ld39.*;
import org.epoxide.ld39.entity.Entity;

public class ScreenGame implements Screen {
    private LD39 game;
    
    public ScreenGame(LD39 game) {
        this.game = game;
        Gdx.input.setInputProcessor(game.getInputHandler());
    }
    
    @Override
    public void show() {
    
    }
    
    @Override
    public void render(float delta) {
        game.setAccumulator(game.getAccumulator() + delta); 
    
        while (game.getAccumulator() >= game.getStep()) {
            game.setAccumulator(game.getAccumulator()- game.getStep());
            if (game.getState() == GameState.RUNNING) {
                updateGame(delta);
            }
        }
    
        renderGame(delta);
    }
    private void renderGame (float delta) {
        
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getCamera().update();
        game.getBatch().setProjectionMatrix(game.getCamera().combined);
        game.getBatch().setShader(game.getDefaultShader());
        game.getLightMap().render(game.getBatch(), delta);
        game.getRenderManager().renderGame(game.getBatch(), delta);
    }
    
    private void updateGame (float delta) {
        LD39.instance.update(delta);
    }
    @Override
    public void resize (int width, int height) {
        game.getCamera().setToOrtho(false, width, height);
        game.getCamera().update();
        
        game.getLightMap().adjustSize(width, height);
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    @Override
    public void hide() {
    
    }
    
    @Override
    public void dispose() {
    
    }
}

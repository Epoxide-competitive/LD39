package org.epoxide.ld39.client.screens;

import com.badlogic.gdx.*;
import org.epoxide.ld39.LD39;

public class ScreenMainMenu implements Screen {
    private LD39 game;
    
    public ScreenMainMenu(LD39 game) {
        this.game = game;
    }
    
    @Override
    public void show() {
    
    }
    
    @Override
    public void render(float delta) {
        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "LD39", game.getCamera().position.x, game.getCamera().position.y);
        game.getFont().draw(game.getBatch(), "Press SPACE to start", game.getCamera().position.x, game.getCamera().position.y-20);
    
        game.getBatch().end();
        //TODO somehow move this to the Keybindings section, problem is that keys don't update in this class
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new ScreenGame(game));
        }
    }
    
    @Override
    public void resize(int width, int height) {
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

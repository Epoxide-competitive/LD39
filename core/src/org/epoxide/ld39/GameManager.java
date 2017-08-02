package org.epoxide.ld39;

import com.badlogic.gdx.Game;
import org.epoxide.ld39.client.render.RenderManager;

public class GameManager extends Game {
    private GameState gameState;
    //managers
    private RenderManager renderManager;

    public GameState getGameState()
    {
        return gameState;
    }
    /* TODO create eventlisteners to fire */
    public void setGameState(GameState to)
    {
        switch(to)
        {
            case RUNNING:
            {

                break;
            }
            case PAUSED:
            {

                break;
            }
            case STOPPED:
            {

                break;
            }
            case LOADING:
            {

                break;
            }
        }
    }

    public RenderManager getRenderManager() {
        return renderManager;
    }

    public void setRenderManager(RenderManager renderManager) {
        this.renderManager = renderManager;
    }

    @Override
    public void create() {
        //input
        //
        try {
            throw new  Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

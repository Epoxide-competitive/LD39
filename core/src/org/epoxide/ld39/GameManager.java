package org.epoxide.ld39;

import com.badlogic.gdx.Game;
import org.epoxide.ld39.client.render.RenderManager;

public class GameManager extends Game {
    private GameState gameState;
    //managers
    private RenderManager renderManager;
    private static GameManager instance;
    public static GameManager getInstance()
    {
        if(instance == null)
            setInstance(new GameManager());
        return instance;
    }
    public static void setInstance(GameManager g)
    {
        if(g != null)
            instance = g;
    }
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

    public static RenderManager getRenderManager() {
        return instance.renderManager;
    }

    public static void setRenderManager(RenderManager renderManager) {
        instance.renderManager = renderManager;
    }

    @Override
    public void create() {

    }
}

package org.epoxide.ld39;

import com.badlogic.gdx.Game;

public class GameManager extends Game {
    private GameState gameState;
    //managers
    private static GameManager instance;
    public static GameManager getInstance()
    {
        if(instance == null)
            setInstance(new GameManager());
        return instance;
    }
    public static void setInstance(GameManager to)
    {
        if(instance != null)
            return;
        if(to != null)
            instance = to;
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

    @Override
    public void create() {

    }

    public static void Begin()
    {

    }
}

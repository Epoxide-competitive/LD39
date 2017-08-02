package org.epoxide.ld39;

import com.badlogic.gdx.*;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.lighting.LightMap;
import org.epoxide.ld39.client.screens.ScreenMainMenu;
import org.epoxide.ld39.entity.*;
import org.epoxide.ld39.input.InputHandler;
import org.epoxide.ld39.logging.LogManager;
import org.epoxide.ld39.logging.Logger;
import org.epoxide.ld39.world.MapHandler;
import org.epoxide.ld39.world.World;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.*;

public class LD39 extends Game {

    public static final float tileWidth = 32f;
    public static final String ID = "ld39";
    public static final Logger LOG = LogManager.createLogger(ID);

    public static LD39 instance;

    private boolean debug = true;
    private GameState state;
    private RenderManager renderManager;
    private OrthographicCamera camera;
    private ShaderProgram defaultShader;
    private BitmapFont font;
    private LightMap lightMap;
    private final double step = 1d / 120d;
    private double accumulator = 0;
    private World world;
    private EntityPlayer entityPlayer;
    private final InputHandler inputHandler = new InputHandler();
    
    @Override
    public void create () {

        LOG.log("Creating game");
        this.state = GameState.LOADING;
        
        printSystemInfo();
        
        LD39.instance = this;
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.lightMap = new LightMap();
        this.defaultShader = new ShaderProgram(Gdx.files.internal("assets/ld39/shaders/main.vert"), Gdx.files.internal("assets/ld39/shaders/main.frag"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("assets/ld39/textures/misc/cursor_64.png")), 0, 0));
        this.renderManager = new RenderManager();
        final int[][] map = new MapHandler(100, 100).map;
        this.world = new World(map);
        this.entityPlayer = new EntityPlayer(this.world);
        world.getEntities().add(entityPlayer);
        setScreen(new ScreenMainMenu(this));
        
        this.state = GameState.RUNNING;
    }

    @Override
    public void render () {
        super.render();
    }

    public GameState getState () {

        return this.state;
    }

    public void setState (GameState state) {
        /* fire events related to state change here probably */
        this.state = state;
    }

    public RenderManager getRenderManager () {

        return this.renderManager;
    }

    public SpriteBatch getBatch () {

        return this.renderManager.getBatch();
    }

    public OrthographicCamera getCamera () {

        return this.camera;
    }

    public ShaderProgram getDefaultShader () {

        return this.defaultShader;
    }

    public BitmapFont getFont () {

        return this.font;
    }

    public LightMap getLightMap () {

        return this.lightMap;
    }

    public double getStep () {

        return this.step;
    }

    public World getWorld () {

        return this.world;
    }

    public EntityPlayer getEntityPlayer () {

        return this.entityPlayer;
    }

    public double getAccumulator () {

        return this.accumulator;
    }

    public boolean isDebugEnabled () {

        return this.debug;
    }

    public void toggleDebug () {

        this.debug = !this.debug;
    }
    public List<Entity> getEntities() {
        return world.getEntities();
    }
    
    public InputHandler getInputHandler() {
        return inputHandler;
    }
    
    public int getMouseWorldX() {
        
        return Math.round(((Gdx.input.getX()-LD39.instance.getCamera().position.x)/32) + this.entityPlayer.x);
    }
    
    public int getMouseWorldY() {
        
        return Math.round((-(Gdx.input.getY()-LD39.instance.getCamera().position.y)/32) + this.entityPlayer.y);
    }

    //TODO re-added but no
    public void setAccumulator (double d) {
        
        this.accumulator = d;
    }
    
    public void printSystemInfo() {
        
        LOG.log("OS Name : %s (%s) version ", System.getProperty("os.name"), System.getProperty("os.arch"), System.getProperty("os.version"));
        LOG.log("Java Version: %s - %s", System.getProperty("java.version"), System.getProperty("java.vendor"));
        
        Runtime runtime = Runtime.getRuntime();
        
        long totalMB = runtime.totalMemory() / 1024L / 1024L;
        long freeMB = runtime.freeMemory() / 1024L / 1024L;
        LOG.log("Memory: %d MB / %d MB", totalMB - freeMB, totalMB);
    }
}
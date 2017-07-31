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

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.*;

public class LD39 extends Game {

    public static final float tileWidth = 32f;
    public static final String ID = "ld39";
    public static final Logger log = LogManager.createLogger(ID);

    public static LD39 instance;

    private boolean debug = true;
    private GameState state;
    private RenderManager renderManager;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ShaderProgram defaultShader;
    private BitmapFont font;
    private LightMap lightMap;
    private final double step = 1d / 120d;
    private double accumulator = 0;
    private World world;
    private EntityPlayer entityPlayer;
    private final InputHandler inputHandler = new InputHandler();
    private List<Entity> entities = new ArrayList<>();
    
    @Override
    public void create () {
        LD39.instance = this;
        this.batch = new SpriteBatch();
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
        this.state = GameState.RUNNING;
        entities.add(entityPlayer);
        setScreen(new ScreenMainMenu(this));
    }

    @Override
    public void render () {
        super.render();
    }

    public GameState getState () {

        return this.state;
    }

    public void setState (GameState state) {

        this.state = state;
    }

    public RenderManager getRenderManager () {

        return this.renderManager;
    }

    public SpriteBatch getBatch () {

        return this.batch;
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
    
    public void setAccumulator(double accumulator) {
        this.accumulator = accumulator;
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
    
    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
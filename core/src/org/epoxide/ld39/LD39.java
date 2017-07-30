package org.epoxide.ld39;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.TimeUtils;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.lighting.LightMap;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.world.MapHandler;
import org.epoxide.ld39.world.World;

public class LD39 extends ApplicationAdapter {

    public static final float tileWidth = 32f;
    public static final String ID = "ld39";
    private static boolean DEBUG = true;

    public static LD39 instance;

    private double STEP = 1d / 120d;
    private double accumulator = 0;

    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;
    private RenderManager renderManager;
    public EntityPlayer entityPlayer;
    private World world;
    public LightMap lightMap;

    public ShaderProgram defaultShader;
    
    private GameState state;

    @Override
    public void create() {
        
        LD39.instance = this;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.lightMap = new LightMap();
        
        this.defaultShader = new ShaderProgram(Gdx.files.internal("assets/ld39/shaders/main.vert"), Gdx.files.internal("assets/ld39/shaders/main.frag"));

        this.renderManager = new RenderManager();
        int[][] map = new MapHandler(100, 100).map;
        this.world = new World(map);
        this.entityPlayer = new EntityPlayer(this.world);
        state = GameState.RUNNING;
        
        Pixmap pm = new Pixmap(Gdx.files.internal("assets/ld39/textures/misc/cursor_64.png"));
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0));
    }

    @Override
    public void render() {
        final float delta = Gdx.graphics.getDeltaTime();

        accumulator += delta;
        if(state == GameState.RUNNING) {
            while(accumulator >= STEP) {
                accumulator -= STEP;
                updateGame(delta);
            }
        }else{
            if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                state = GameState.RUNNING;
            }
        }

        renderGame(delta);
    }

    private void renderGame(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.setShader(this.defaultShader);
        this.lightMap.render(this.batch, delta);
        this.renderManager.renderGame(batch, delta);

        if (DEBUG) {
            this.batch.begin();
            this.batch.setShader(this.defaultShader);
            float textX = camera.position.x - Gdx.graphics.getWidth() / 2 + 10;
            float textY = camera.position.y + Gdx.graphics.getHeight() / 2;
            this.font.draw(this.batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), textX, textY - 10);
            this.font.draw(this.batch, "GL_RENDERER = " + Gdx.gl.glGetString(GL20.GL_RENDERER), textX, textY - 30);
            this.font.draw(this.batch, "GL_VENDOR = " + Gdx.gl.glGetString(GL20.GL_VENDOR), textX, textY - 50);
            this.font.draw(this.batch, "GL_VERSION = " + Gdx.gl.glGetString(GL20.GL_VERSION), textX, textY - 70);
            this.font.draw(this.batch, "WIDTH = " + Gdx.graphics.getWidth(), textX, textY - 90);
            this.font.draw(this.batch, "HEIGHT = " + Gdx.graphics.getHeight(), textX, textY - 110);
            this.font.draw(this.batch, "X = " + entityPlayer.x, textX, textY - 130);
            this.font.draw(this.batch, "Y = " + entityPlayer.y, textX, textY - 150);
            this.font.draw(this.batch, "GAMESTATE = " + state, textX, textY - 170);
            this.font.draw(this.batch, "STEP = " + this.STEP, textX, textY - 190);
            this.font.draw(this.batch, "ACCUMULATOR = " + this.accumulator, textX, textY - 210);
            this.font.draw(this.batch, "DELTA = " + delta, textX, textY - 230);
            this.font.draw(this.batch, "MX = " + Gdx.input.getX(), textX, textY - 250);
            this.font.draw(this.batch, "MY = " + Gdx.input.getY(), textX, textY - 270);
    
    
            this.batch.end();
        }
    }

    
    private final int movementDelayDefault = 10;
    private int movementDelay = movementDelayDefault;
    
    private void updateGame(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.B)){
            DEBUG = !DEBUG;
        }
        if (movementDelay > 0) {
            movementDelay--;
        } else {
            boolean moved = false;
            float prevX = entityPlayer.x, prevY = entityPlayer.y;
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if(world.getTileState((int)entityPlayer.x, (int)entityPlayer.y+1).tile.isCollidable()) {
                    if(entityPlayer.y + 1 > world.getMapHeight() - 1) {
                        entityPlayer.y = world.getMapHeight() - 1;
                    } else {
                        entityPlayer.y++;
                    }
                    moved = true;
                }
            }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if(world.getTileState((int)entityPlayer.x, (int)entityPlayer.y-1).tile.isCollidable()) {
                    if(entityPlayer.y - 1 < 0) {
                        entityPlayer.y = 0;
                    } else {
                        entityPlayer.y--;
                    }
                    moved = true;
                }
            }else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                if(world.getTileState((int)entityPlayer.x-1, (int)entityPlayer.y).tile.isCollidable()) {
                    if(entityPlayer.x - 1 < 0) {
                        entityPlayer.x = 0;
                    } else {
                        entityPlayer.x--;
                    }
                    moved = true;
                }
            }else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if(world.getTileState((int) entityPlayer.x + 1, (int) entityPlayer.y).tile.isCollidable()) {
                    if(entityPlayer.x + 1 > world.getMapWidth() - 1) {
                        entityPlayer.x = world.getMapWidth() - 1;
                    } else {
                        entityPlayer.x++;
                    }
                    moved = true;
                }
            }
            if(moved){
                if(entityPlayer.power>0){
                    entityPlayer.power--;
                }else{
                    entityPlayer.power=0;
                    entityPlayer.x = prevX;
                    entityPlayer.y = prevY;
                }
                movementDelay = movementDelayDefault;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            this.state = GameState.PAUSED;
        }

    }

    @Override
    public void resize(int width, int height) {
    	
        this.camera.setToOrtho(false, width, height);
        camera.update();

        this.lightMap.adjustSize(width, height);
    }

    @Override
    public void dispose() {
    }
    
    public Camera getCamera() {
    	
    	return this.camera;
    }
}
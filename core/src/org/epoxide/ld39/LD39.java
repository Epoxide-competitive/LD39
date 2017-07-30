package org.epoxide.ld39;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private static final boolean DEBUG = true;

    public static LD39 instance;

    private double STEP = 1d / 120d;
    private double prevTime;
    private double accumulator = 0;

    private SpriteBatch batch;
    private BitmapFont font;

    private OrthographicCamera camera;
    private RenderManager renderManager;
    public EntityPlayer entityPlayer;
    private World world;
    public LightMap lightMap;

    private ShaderProgram defaultShader;
    private ShaderProgram lightShader;

    @Override
    public void create() {
        LD39.instance = this;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.defaultShader = new ShaderProgram(Gdx.files.internal("assets/ld39/shaders/main.vert"), Gdx.files.internal("assets/ld39/shaders/main.frag"));

        this.lightShader = new ShaderProgram(Gdx.files.internal("assets/ld39/shaders/main.vert"), Gdx.files.internal("assets/ld39/shaders/light.frag"));
        this.lightShader.begin();
        this.lightShader.setUniformf("ambientColor", 0.3f, 0.38f, 0.4f, 0.25f);
        this.lightShader.setUniformi("u_lightmap", 1);
        this.lightShader.end();

        this.renderManager = new RenderManager();
        int[][] map = new MapHandler(100, 100).map;
        this.world = new World(map);
        this.entityPlayer = new EntityPlayer(this.world);
        this.lightMap = new LightMap();
    }

    @Override
    public void render() {
        double currentTime = TimeUtils.millis() / 1000.0;
        double frameTime = Math.min(currentTime - prevTime, 0.25);
        final float delta = (float) frameTime;

        prevTime = currentTime;
        accumulator += frameTime;

        while (accumulator >= STEP) {
            accumulator -= STEP;
            updateGame(delta);
        }

        renderGame(delta);
    }

    private void renderGame(float delta) {
        this.lightMap.addLight(camera.position.x - Gdx.graphics.getWidth() / 2 + Gdx.input.getX(), camera.position.y + Gdx.graphics.getHeight() / 2 - Gdx.input.getY(), 150, Color.WHITE);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.setShader(this.defaultShader);
        this.lightMap.render(this.batch, delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.setColor(Color.WHITE);
        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.setShader(this.lightShader);
        this.lightMap.resetBinds();
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
            this.font.draw(this.batch, "CAM = " + camera.position, textX, textY - 170);
            this.batch.end();
        }
    }

    private int movementDelay = 20;

    private void updateGame(float delta) {
        if (movementDelay > 0) {
            movementDelay--;
        } else {
            float movementX = 0;
            float movementY = 0;
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                movementY += 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                movementY -= 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                movementX -= 1;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                movementX += 1;
            }
            if (movementX != 0 || movementY != 0) {
                if (entityPlayer.x + movementX > 0) {
                    entityPlayer.x += movementX;
                } else {
                    entityPlayer.x = 0;
                }
                if (entityPlayer.y + movementY > 0) {
                    entityPlayer.y += movementY;
                } else {
                    entityPlayer.y = 0;
                }
                movementDelay = 20;
            }
        }

    }

    @Override
    public void resize(int width, int height) {
        this.camera.setToOrtho(false, width, height);
        camera.update();

        this.lightMap.adjustSize(width, height);

        this.lightShader.begin();
        this.lightShader.setUniformf("resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.lightShader.end();
    }

    @Override
    public void dispose() {
    }
}

package org.epoxide.ld39;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.client.render.hud.HudDebugInfo;
import org.epoxide.ld39.client.render.hud.IHud;
import org.epoxide.ld39.client.render.lighting.LightMap;
import org.epoxide.ld39.entity.EntityPlayer;
import org.epoxide.ld39.input.InputHandler;
import org.epoxide.ld39.world.MapHandler;
import org.epoxide.ld39.world.World;

public class LD39 extends ApplicationAdapter {

    public static final float tileWidth = 32f;
    public static final String ID = "ld39";

    public static LD39 instance;

    private boolean debug = true;
    private GameState state;
    private RenderManager renderManager;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private ShaderProgram defaultShader;   
    private BitmapFont font;
    private LightMap lightMap;
    private double step = 1d / 120d;
    private double accumulator = 0;
    private World world;
    private EntityPlayer entityPlayer;
    private InputHandler inputHandler = new InputHandler();

    @Override
    public void create() {
        
        LD39.instance = this;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.lightMap = new LightMap();
        
        this.defaultShader = new ShaderProgram(Gdx.files.internal("assets/ld39/shaders/main.vert"), Gdx.files.internal("assets/ld39/shaders/main.frag"));

        Gdx.input.setInputProcessor(inputHandler);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("assets/ld39/textures/misc/cursor_64.png")), 0, 0));
        
        this.renderManager = new RenderManager();
        int[][] map = new MapHandler(100, 100).map;
        this.world = new World(map);
        this.entityPlayer = new EntityPlayer(this.world);
        state = GameState.RUNNING;
    }

    @Override
    public void render() {
        final float delta = Gdx.graphics.getDeltaTime();

        accumulator += delta;
        if(state == GameState.RUNNING) {
            while(accumulator >= step) {
                accumulator -= step;
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
    }
    
    private void updateGame(float delta) {
        
        inputHandler.onUpdate(delta);
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

	public GameState getState() {
		return state;
	}
	
	public void setState(GameState state) {
	    
	    this.state = state;
	}

	public RenderManager getRenderManager() {
		return renderManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public ShaderProgram getDefaultShader() {
		return defaultShader;
	}

	public BitmapFont getFont() {
		return font;
	}

	public LightMap getLightMap() {
		return lightMap;
	}

	public double getStep() {
		return step;
	}

	public World getWorld() {
		return world;
	}

	public EntityPlayer getEntityPlayer() {
		return entityPlayer;
	}
	
	public double getAccumulator() {
		
		return this.accumulator;
	}
	
	public boolean isDebugEnabled() {
		
		return this.debug;
	}
	
	public void toggleDebug() {
	    
	    this.debug = !this.debug;
	}
}
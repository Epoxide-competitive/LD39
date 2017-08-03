package org.epoxide.ld39.world;

import java.util.*;
import java.util.function.Predicate;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sun.org.apache.bcel.internal.generic.IREM;
import org.epoxide.ld39.GameObject;
import org.epoxide.ld39.LD39;
import org.epoxide.ld39.client.render.IRenderable;
import org.epoxide.ld39.client.render.RenderManager;
import org.epoxide.ld39.entity.Entity;
import org.epoxide.ld39.tile.Tile;
import org.epoxide.ld39.tile.TileState;
import org.epoxide.ld39.tile.TileStateTorch;

public class World {

    class WorldCell implements IRenderable {
        private List<GameObject> cellContent;
        private List<IRenderable> renderables;
        public WorldCell()
        {
            cellContent = new ArrayList<>();
            renderables = new ArrayList<>();
        }

        public List<GameObject> getCellContent() {
            return Collections.unmodifiableList(cellContent);
        }

        public void add(GameObject o)
        {
            cellContent.add(o);
            if(o instanceof IRenderable)
            {
                renderables.add((IRenderable)o);
            }
            renderables.sort(IRenderable.RENDER_COMPARATOR);
        }
        public void remove(GameObject o)
        {
            cellContent.remove(o);
            if(o instanceof IRenderable)
            {
                renderables.add((IRenderable)o);
            }
        }
        @Override
        public void render(SpriteBatch batch,Vector2 screenPos, float delta) {
            int i = renderables.size()-1;
            for (; i >= 0; i--) {
                if(renderables.get(i).isOpaque())
                    break;
            }
            //render only sublist from i to size()
            for(;i<renderables.size();i++)
                renderables.get(i).render(batch, screenPos, delta);
        }

        @Override
        public int renderLayer() {
            return -1;
        }

        @Override
        public int renderPriority() {
            return -1;
        }
    }
    private final WorldCell[][] map;
    private final int width;
    private final int height;

    private World()
    {
        this.width = 0;
        this.height = 0;
        this.map = new WorldCell[width][height];
    }
    public World (int[][] map) {
        this.width = map.length;
        this.height = map[0].length;
        this.map = new WorldCell[this.width][this.height];

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.map[i][j] = new WorldCell();
                Tile genTile = Tile.REGISTRY.getRandomValue(new Random());
                
                if (genTile == Tile.VOID || !genTile.isSolid())
                    genTile = Tile.WALL;
                this.map[i][j].add(map[i][j] == 1 ? new TileState(genTile, i, j) : new TileState(Tile.FLOOR, i, j));
                if (map[i][j] != 1 && Math.random() > 0.99) {
                    this.map[i][j].add(new TileStateTorch(Tile.FLOOR, i, j));
                }
            }
        }
        cellsToUpdate = new ArrayList<>();
    }

    public int getMapWidth () {
        return this.width;
    }

    public int getMapHeight () {
        return this.height;
    }

    public List<GameObject> getGameObjectsAt (int x, int y) {
        if (!inWorldBounds(x,y)) {
            return new ArrayList<>();
        }
        return this.map[x][y].getCellContent();
    }
    public boolean inWorldBounds(int x, int y)
    {
        return !(x < 0 || y < 0 || x >= this.map.length || y >= this.map[0].length);
    }
    ///CellsToUpdate is a cache to prevent need to constantly traverse 100x100 arrays to update everything
    private List<WorldCell> cellsToUpdate;
    public void addToWorldCell(int x, int y, GameObject o)
    {
        if(inWorldBounds(x,y))
        {
            if(!o.getIgnoreUpdate())
                cellsToUpdate.add(map[x][y]);
            map[x][y].add(o);
        }
    }
    public boolean removeFromWorldCell(int x, int y, Predicate<? super GameObject> filter)
    {
        if(inWorldBounds(x,y)) {
            map[x][y].getCellContent().removeIf(filter);
            return true;
        }
        return false;
    }
    //dangerous, need to likely notify all objects that they are being destroyed when this is called
    public void clearWorldCell(int x, int y)
    {
        if(inWorldBounds(x,y)) {
            map[x][y].getCellContent().forEach(gameObject -> gameObject.destroy());
            map[x][y].getCellContent().clear();
        }
    }
    public boolean checkClear(int x, int y)
    {
        if(!inWorldBounds(x,y))
            return false;
        else if(map[x][y].getCellContent().stream().filter(entity -> entity instanceof ICollider && ((ICollider)entity).isSolid()).count() > 0)
            return false;
        return true;
    }
    public void renderWorld(SpriteBatch batch, float delta)
    {
        RenderManager render = LD39.instance.getRenderManager();

        batch.begin();
        for(int x = render.getScreenLeftEdge();x < render.getScreenRightEdge(); x++)
        {
            if(this.inWorldBounds(x,0)) {

                for (int y = render.getScreenBottomEdge(); y < render.getScreenTopEdge(); y++) {
                    if(this.inWorldBounds(x,y)) {
                        for (IRenderable r : map[x][y].renderables) {
                            r.render(batch, RenderManager.posRelativeToCamera(new Vector2(x, y)), delta);
                        }
                    }
                }
            }
        }
        batch.end();
    }
    public void update(float delta)
    {
        for (WorldCell c : cellsToUpdate) {
            c.getCellContent().forEach(gameObject -> gameObject.update(delta));
        }
    }
}

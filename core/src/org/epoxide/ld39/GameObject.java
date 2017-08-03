package org.epoxide.ld39;

import org.epoxide.ld39.world.ICollider;

import java.lang.reflect.Type;
import java.util.LinkedList;

public abstract class GameObject {
    public String name;
    public GameObject()
    {
        layer = 0;
        name = "";
    }
    private int layer;
    private boolean ignoreUpdate = false;
    public boolean getIgnoreUpdate()
    {
        return ignoreUpdate;
    }
    public int getLayer() {
        return layer;
    }

    public GameObject setLayer(int layer) {
        this.layer = layer;
        return this;
    }

    public String getName() {

        return name;
    }

    public GameObject setName(String name) {
        this.name = name;
        return this;
    }

    public void update(float delta)
    {

    }

    public void destroy(GameObject sender)
    {

    }

    public void destroy()
    {
        destroy(null);
    }
}

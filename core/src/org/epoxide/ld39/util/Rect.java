package org.epoxide.ld39.util;

import com.badlogic.gdx.math.Vector2;

public class Rect {
    //x,y are centre of rect
    private float x;
    private float y;
    private float length;
    private float height;
    public Rect()
    {
        x=0f;
        y=0f;
        length=0f;
        height=0f;
    }
    public Rect(float centreX, float centreY, float length, float height)
    {
        this.x = centreX;
        this.y = centreY;
        this.length = length;
        this.height = height;
    }
    public Rect(Vector2 centre, Vector2 edgeSize)
    {
        this.x = centre.x;
        this.y = centre.y;
    }
    public Rect(float centreX, float centreY)
    {

    }
    public boolean inBounds(float x, float y)
    {
        return inXRange(x) && inYRange(y);
    }
    private boolean inXRange(float x)
    {
        if (this.x == x)
            return true;
        if(this.x > x && x < (this.x+(length/2)))
                return true;
        if(this.x < x && x > (this.x-(length/2)))
                return true;
        return false;
    }
    private boolean inYRange(float x)
    {
        if (this.y == y)
            return true;
        if(this.y > y && y < (this.y+(height/2)))
            return true;
        if(this.y < y && y > (this.y-(height/2)))
            return true;
        return false;
    }
    public void setCentreX(float x)
    {
        this.x = x;
    }
    public void setCentreY(float y)
    {
        this.y = y;
    }
    public void setCentre(float x, float y)
    {
        setCentreX(x);
        setCentreY(y);
    }
    public void setCentre(Vector2 centre)
    {
        setCentre(centre.x,centre.y);
    }
    public void setDimensions(float length, float height)
    {
        this.length = length;
    }
}

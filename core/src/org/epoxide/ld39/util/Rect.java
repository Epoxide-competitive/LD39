package org.epoxide.ld39.util;

import com.badlogic.gdx.math.Vector2;

/**
 *  Rectangle with the origin specified as it's centre rather than a corner
 */
public class Rect {
    //x,y are centre of rect
    private float x;
    private float y;
    private float length;
    private float height;

    /**
     * Constructs Rect with all values default initialised to 0 (0x0unit rectangle at the origin)
     */
    public Rect()
    {
        x=0f;
        y=0f;
        length=0f;
        height=0f;
    }

    /**
     * Float based constructor
     * @param centreX Global x-axis centre of the rectangle
     * @param centreY Global y-axis centre of the rectangle
     * @param length X-axis edge length of the rectangle
     * @param height Y-axis edge length of the rectangle
     */
    public Rect(float centreX, float centreY, float length, float height)
    {
        this.x = centreX;
        this.y = centreY;
        this.length = length;
        this.height = height;
    }

    /**
     * Vector2 based constructor
     * @param centre Centre of the rectangle
     * @param edgeSize Edge length of rectangle
     */
    public Rect(Vector2 centre, Vector2 edgeSize)
    {
        this.x = centre.x;
        this.y = centre.y;
        this.length = edgeSize.x;
        this.height = edgeSize.y;
    }

    /**
     * Float constructor which defaults to a 1x1 edge length
     * @param centreX x-axis global centre
     * @param centreY y-axis global centre
     */
    public Rect(float centreX, float centreY)
    {
        this.x = centreX;
        this.y = centreY;
        this.length = 1;
        this.height = 1;
    }

    /**
     * Float constructor which creates a square
     * @param centreX Global x centre
     * @param centreY Global y centre
     * @param edgeLength Edge length of square
     */
    public Rect(float centreX, float centreY, float edgeLength)
    {
        this.x = centreX;
        this.y = centreY;
        this.length = edgeLength;
        this.height = edgeLength;
    }

    /**
     * Detects if the specified point is within the bounds of this rect
     * @param x x-axis coordinate to check
     * @param y y-axis coordinate to check
     * @return Whether the specified point is within or on this rectangle's bounds
     */
    public boolean inBounds(float x, float y) {return inXRange(x) && inYRange(y);}

    /**
     * Helper method to simplify inBounds for readability
     * @param x x point to test
     * @return if within x bounds
     */
    private boolean inXRange(float x)
    {
        if (this.x == x)
            return true;
        if(this.x > x && x <= (this.x+(length/2)))
                return true;
        if(this.x < x && x >= (this.x-(length/2)))
                return true;
        return false;
    }

    /**
     * Helper method to simplify inBounds for readability
     * @param y y point to test
     * @return if y is in the y bound
     */
    private boolean inYRange(float y)
    {
        if (this.y == y)
            return true;
        if(this.y > y && y <= (this.y+(height/2)))
            return true;
        if(this.y < y && y >= (this.y-(height/2)))
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

    /**
     * Shifts the centre of the rect to the specified global coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setCentre(float x, float y)
    {
        setCentreX(x);
        setCentreY(y);
    }
    public void setCentre(Vector2 centre)
    {
        setCentre(centre.x,centre.y);
    }

    /**
     * Sets edge length to specified values
     * @param length x-axis edge length
     * @param height y-axis edge length
     */
    public void setDimensions(float length, float height)
    {
        this.length = length;
        this.height = height;
    }

    /**
     * Sets edge length to 1 unit in both axis
     */
    public void setDimensions()
    {
        this.length = 1;
        this.height = 1;
    }
}

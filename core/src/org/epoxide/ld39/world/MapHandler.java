package org.epoxide.ld39.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapHandler {

    // Random seed that seemed to give us a good generation
    Random rand = new Random(2906);

    public int[][] map;

    public int mapWidth;
    public int mapHeight;
    public int percentAreWalls;

    public MapHandler (int width, int height) {
        this.mapWidth = width;
        this.mapHeight = height;
        // Any more and a lot of walls are generated
        this.percentAreWalls = 40;

        for (int i = 0; i < 2; i++) {
            this.randomFillMap();
            this.makeCaverns();
        }
    }

    public void makeCaverns () {

        // By initializing column in the outter loop, its only created once
        for (int column = 0, row = 0; row <= this.mapHeight - 1; row++) {
            for (column = 0; column <= this.mapWidth - 1; column++) {
                this.map[column][row] = this.placeWallLogic(column, row);
            }
        }
    }

    public int placeWallLogic (int x, int y) {

        final int numWalls = this.getAdjacentWalls(x, y, 1, 1);

        if (this.map[x][y] == 1) {
            if (numWalls >= 4) {
                return 1;
            }
            if (numWalls < 2) {
                return 0;
            }

        }
        else {
            if (numWalls >= 5) {
                return 1;
            }
        }
        return 0;
    }

    public int getAdjacentWalls (int x, int y, int scopeX, int scopeY) {

        final int startX = x - scopeX;
        final int startY = y - scopeY;
        final int endX = x + scopeX;
        final int endY = y + scopeY;

        int iX = startX;
        int iY = startY;

        int wallCounter = 0;

        for (iY = startY; iY <= endY; iY++) {
            for (iX = startX; iX <= endX; iX++) {
                if (!(iX == x && iY == y)) {
                    if (this.isWall(iX, iY)) {
                        wallCounter += 1;
                    }
                }
            }
        }
        return wallCounter;
    }

    boolean isWall (int x, int y) {

        // Consider out-of-bound a wall
        if (this.isOutOfBounds(x, y)) {
            return true;
        }

        if (this.map[x][y] == 1) {
            return true;
        }

        if (this.map[x][y] == 0) {
            return false;
        }
        return false;
    }

    boolean isOutOfBounds (int x, int y) {

        if (x < 0 || y < 0) {
            return true;
        }
        else if (x > this.mapWidth - 1 || y > this.mapHeight - 1) {
            return true;
        }
        return false;
    }

    public String mapToString () {

        String returnString = String.join(" ", new String[] { "Width:", this.mapWidth + "", "\tHeight:", this.mapHeight + "", "\t% Walls:", this.percentAreWalls + "", "\n" });

        final List<String> mapSymbols = new ArrayList<>();
        mapSymbols.add(".");
        mapSymbols.add("#");
        mapSymbols.add("+");

        for (int column = 0, row = 0; row < this.mapHeight; row++) {
            for (column = 0; column < this.mapWidth; column++) {
                returnString += mapSymbols.get(this.map[column][row]);
            }
            returnString += "\n";
        }
        return returnString;
    }

    public void blankMap () {

        for (int column = 0, row = 0; row < this.mapHeight; row++) {
            for (column = 0; column < this.mapWidth; column++) {
                this.map[column][row] = 0;
            }
        }
    }

    public void randomFillMap () {

        // New, empty map
        this.map = new int[this.mapWidth][this.mapHeight];

        int mapMiddle = 0; // Temp variable
        for (int column = 0, row = 0; row < this.mapHeight; row++) {
            for (column = 0; column < this.mapWidth; column++) {
                // If coordinants lie on the the edge of the map (creates a border)
                if (column == 0) {
                    this.map[column][row] = 1;
                }
                else if (row == 0) {
                    this.map[column][row] = 1;
                }
                else if (column == this.mapWidth - 1) {
                    this.map[column][row] = 1;
                }
                else if (row == this.mapHeight - 1) {
                    this.map[column][row] = 1;
                }
                // Else, fill with a wall a random percent of the time
                else {
                    mapMiddle = this.mapHeight / 2;

                    if (row == mapMiddle) {
                        this.map[column][row] = 0;
                    }
                    else {
                        this.map[column][row] = this.randomPercent(this.percentAreWalls);
                    }
                }
            }
        }
    }

    int randomPercent (int percent) {

        if (percent >= this.rand.nextInt(100) + 1) {
            return 1;
        }
        return 0;
    }

    public MapHandler (int mapWidth, int mapHeight, int[][] map, int percentWalls) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.percentAreWalls = percentWalls;
        this.map = new int[this.mapWidth][this.mapHeight];
        this.map = map;
    }

    public int getMapWidth () {

        return this.mapWidth;
    }

    public void setMapWidth (int mapWidth) {

        this.mapWidth = mapWidth;
    }

    public int getMapHeight () {

        return this.mapHeight;
    }

    public void setMapHeight (int mapHeight) {

        this.mapHeight = mapHeight;
    }

    public int getPercentAreWalls () {

        return this.percentAreWalls;
    }

    public void setPercentAreWalls (int percentAreWalls) {

        this.percentAreWalls = percentAreWalls;
    }
}
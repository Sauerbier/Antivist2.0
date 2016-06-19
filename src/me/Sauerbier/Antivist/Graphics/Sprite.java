package me.Sauerbier.Antivist.Graphics;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Sprite {

    private int sizeX;
    private int sizeY;
    private int x;
    private int y;
    private int[] pixels;
    private SpriteSheet sheet;

    protected Sprite(int sizeX,int sizeY, SpriteSheet sheet){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sheet = sheet;
    }

    public Sprite(int sizeX, int sizeY, Color color){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pixels = new int[sizeX*sizeY];

        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++) {
                pixels[x+y*sizeX] = color.getRGB();
            }
        }
    }

    public Sprite(int sizeX,int sizeY, int x, int y, SpriteSheet sheet) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x * sizeX;
        this.y = y * sizeY;
        this.sheet = sheet;
        pixels = new int[sizeX*sizeY];
        load();
    }

    private void load(){
        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++){
                pixels[x+y*sizeX] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }

    public void changeColor(Color color){
        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++) {
                pixels[x+y*sizeX] = color.getRGB();
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public SpriteSheet getSheet() {
        return sheet;
    }

    public void setSheet(SpriteSheet sheet) {
        this.sheet = sheet;
    }
}

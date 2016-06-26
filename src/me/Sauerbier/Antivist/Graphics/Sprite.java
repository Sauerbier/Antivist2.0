package me.Sauerbier.Antivist.Graphics;

import me.Sauerbier.Antivist.FrameWork.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private boolean darker;

    protected Sprite(int sizeX,int sizeY, SpriteSheet sheet, boolean darker){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sheet = sheet;
        this.darker = darker;
    }

    public Sprite(int sizeX, int sizeY, Color color){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        pixels = new int[sizeX*sizeY];

        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++) {
                if(darker)  pixels[x+y*sizeX] = Utils.darker(color.getRGB(),0.23f);
                else pixels[x+y*sizeX] = color.getRGB();
            }
        }
    }

    public Sprite(BufferedImage image,boolean darker) {
        sizeX = image.getWidth();
        sizeY = image.getHeight();
        pixels = new int[sizeX*sizeY];
        image.getRGB(0 ,0,image.getWidth(),image.getHeight(),pixels,0,image.getWidth());
        if(darker){
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = Utils.darker(pixels[i],0.23f);
            }
        }
    }


    public Sprite(int sizeX,int sizeY, int x, int y, SpriteSheet sheet, boolean darker) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x * sizeX;
        this.y = y * sizeY;
        this.sheet = sheet;
        pixels = new int[sizeX*sizeY];
        load(darker);
    }


    private void load(boolean darker){
        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++){
                if(darker) pixels[x+y*sizeX] = Utils.darker(sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()],0.23f);
                else pixels[x+y*sizeX] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.getSize()];
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


    public void changeBrigthness(float f){
        for(int y = 0; y < sizeY; y++){
            for(int x = 0; x < sizeX; x++) {
                pixels[x+y*sizeX] = Utils.darker(pixels[x+y*sizeX],f);
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

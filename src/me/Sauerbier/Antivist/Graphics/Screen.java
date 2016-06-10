package me.Sauerbier.Antivist.Graphics;

import me.Sauerbier.Antivist.Level.Block;
import me.Sauerbier.Antivist.ResourceManagement.Resources;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Screen {
    public static final int FLIP_Y = 1, FLIP_X = 2, FLIP_BOTH = 3;
    private int width,height, tileSize,tileSizeMask, mapSize, xOffset, yOffset;
    private int[] pixels;
    private int[] tiles ; //map size
    Random random = new Random();

    public Screen(Resources resources, int width, int height, int mapSize, int tileSize, int tileSizeMask) {
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        this.tileSizeMask = tileSizeMask;
        this.mapSize = mapSize;
        pixels = new int[width*height];
        tiles = new int[mapSize*mapSize];

    }

    public void renderBackground(int[] pixels, int width, int height){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int col = pixels[x + y * width];
                this.pixels[x + y * width] = col;
            }
        }
    }

    public BufferedImage resizeImage(BufferedImage image, int width, int height) {
        int type=0;
        type = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
        BufferedImage resizedImage = new BufferedImage(width, height,type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    public void renderBlocks(Block block, int xp, int yp){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < block.getSprite().getSizeY(); y++){
            int ya = y + yp;
            for(int x = 0; x < block.getSprite().getSizeX(); x++){
                int xa = x + xp;
                if(xa < - block.getSprite().getSizeX() || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0 ) xa = 0;
                int col = block.getSprite().getPixels()[x + y * block.getSprite().getSizeX()];
                if(col != 0x00ffffff)pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < sprite.getSizeY(); y++){
            int ya = y + yp;
            for(int x = 0; x < sprite.getSizeX(); x++){
                int xa = x + xp;
                if(xa < - sprite.getSizeX() || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0 ) xa = 0;
                int col = sprite.getPixels()[x + y * sprite.getSizeX()];
                if(col != 0x00ffffff)pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderReversedSprite(int xp, int yp, Sprite sprite, int flip){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y < sprite.getSizeY(); y++){
            int ya = y + yp;
            int ys = y;
            if(flip == 2 || flip == 3){
                ys = sprite.getSizeY() - 1 - y;
            }
            for(int x = 0; x < sprite.getSizeX(); x++){
                int xa = x + xp;
                int xs = x;
                if(flip == 1 || flip == 3) {
                    xs = sprite.getSizeX() - 1 - x;
                }
                if(xa < - sprite.getSizeX() || xa >= width || ya < 0 || ya >= height) break;
                if(xa < 0 ) xa = 0;
                int col = sprite.getPixels()[xs + ys * sprite.getSizeX()];
                if(col != 0x00ffffff)pixels[xa + ya * width] = col;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void clear(){
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public int getTileSizeMask() {
        return tileSizeMask;
    }

    public void setTileSizeMask(int tileSizeMask) {
        this.tileSizeMask = tileSizeMask;
    }
}

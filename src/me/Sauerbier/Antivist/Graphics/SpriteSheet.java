package me.Sauerbier.Antivist.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class SpriteSheet {

    private String path;
    private int size;
    private int[] pixels;

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        pixels = new int[size*size];
        loadSheet();
    }

    private void loadSheet(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0 ,0,w,h,pixels,0,w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }
}

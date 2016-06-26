package me.Sauerbier.Antivist.FrameWork;

import java.util.Random;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Utils {


    public static int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }


    public static int interpolateColor(int ca, int cb) {
        int r = (ca >> 16) & 0x000000ff;
        int g = (ca >> 8) & 0x000000ff; //m
        int b = ca & 0x000000ff;

        int r1 = (cb >> 16) & 0x000000ff;
        int g1 = (cb >> 8) & 0x000000ff;  //i
        int b1 = cb & 0x000000ff;

        int or =(r+r1);
        int og =(g+g1);
        int ob =(b+b1);
        if(or > 255) or = 255;
        if(og > 255) og = 255;
        if(ob > 255) ob = 255;
        return (or << 16 | og << 8 | ob);

    }

    public static int darker(int ca, float f){
        int r = (ca >> 16) & 0x000000ff;
        int g = (ca >> 8) & 0x000000ff;
        int b = ca & 0x000000ff;
        r = (int) (r * f);
        g = (int) (g * f);
        b = (int) (b * f);
        return (r << 16 | g << 8 | b);
    }


    public static int subColor(int ca, int cb) {
        int r = (ca >> 16) & 0x000000ff;
        int g = (ca >> 8) & 0x000000ff; //m
        int b = ca & 0x000000ff;

        int r1 = (cb >> 16) & 0x000000ff;
        int g1 = (cb >> 8) & 0x000000ff;  //i
        int b1 = cb & 0x000000ff;

        int or =(r-r1);
        int og =(g-g1);
        int ob =(b-b1);
        if(or > 255) or = 255;
        if(og > 255) og = 255;
        if(ob > 255) ob = 255;
        return (or << 16 | og << 8 | ob);

    }

    public static int changeBrightness(int col, int amount) {
        int r = (col & 0xff0000) >> 16;
        int g = (col & 0xff00) >> 8;
        int b = (col & 0xff);
        if (amount > 0) amount = 0;
        if (amount < -150) amount = -150;

        r += amount;
        g += amount;
        b += amount;

        if (r < 0) r = 0;
        if (g < 0) g = 0;
        if (b < 0) b = 0;
        if (r > 255) r = 255;
        if (g > 255) g = 255;
        if (b > 255) b = 255;

        return r << 16 | g << 8 | b;
    }

}

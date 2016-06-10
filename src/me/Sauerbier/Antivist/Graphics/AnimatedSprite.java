package me.Sauerbier.Antivist.Graphics;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class AnimatedSprite {

    private int frame;
    private Sprite currentFrame;
    private int rate = 5, animationSize = -1, time=0;
    private Sprite[] sprites;
    public AnimatedSprite(Sprite ... frames) {
        this.animationSize = frames.length -1;
        sprites = frames;
    }


    public void update(){
        time++;
        if(time % rate == 0) {
            if (frame >= animationSize) frame = 0;
            else frame++;
            currentFrame = sprites[frame];
        }
    }


    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public Sprite getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(Sprite currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAnimationSize() {
        return animationSize;
    }

    public void setAnimationSize(int animationSize) {
        this.animationSize = animationSize;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setSprites(Sprite[] sprites) {
        this.sprites = sprites;
    }
}

package me.Sauerbier.Antivist.Entity.Mobs;

import me.Sauerbier.Antivist.FrameWork.Keyboard;
import me.Sauerbier.Antivist.Graphics.AnimatedSprite;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Player extends  Mob{

    private Keyboard keyboard;
    private Sprite idle_left,idle_right,walk_left,walk_right, currentSprite;
    private AnimatedSprite animation_left;
    private int speed = 4;
    private boolean walking;

    public Player(Level level, Keyboard keyboard){
        this.keyboard = keyboard;
        setLevel(level);
        setSprite(getLevel().getResources().getSpriteByName("player0"));
        idle_left = getLevel().getResources().getSpriteByName("player1");
        idle_right = getLevel().getResources().getSpriteByName("player2");
        walk_left = getLevel().getResources().getSpriteByName("player3");
        walk_right = getLevel().getResources().getSpriteByName("player4");
        currentSprite = getSprite();
        animation_left = new AnimatedSprite(idle_left,walk_left);
        setX(0);
        setY(0);
    }

    public Player(Level level,Keyboard keyboard, int xSpawn, int ySpawn){
        this.keyboard = keyboard;
        setLevel(level);
        setSprite(getLevel().getResources().getSpriteByName("player0"));
        idle_left = getLevel().getResources().getSpriteByName("player1");
        idle_right = getLevel().getResources().getSpriteByName("player2");
        walk_left = getLevel().getResources().getSpriteByName("player3");
        walk_right = getLevel().getResources().getSpriteByName("player4");
        currentSprite = getSprite();
        animation_left = new AnimatedSprite(idle_left,walk_left);
        setX(xSpawn*level.getScreen().getTileSize());
        setY(ySpawn*level.getScreen().getTileSize());
    }

    @Override
    public void move(int xa,int ya) {
        if(xa > 0) setDirection(1);
        if(xa < 0) setDirection(3);
        if(ya > 0) setDirection(2);
        if(ya < 0) setDirection(0);
        if(!collision(xa, ya)) {
            setX(getX() + xa);
            setY(getY() + ya);
        }
    }

    @Override
    public boolean collision(int xa, int ya) {
        boolean solid = false;
        if (getLevel().getBlock((getX() + xa)>>getLevel().getScreen().getTileSizeMask(),(getY() + ya)>>getLevel().getScreen().getTileSizeMask()).isSolid()) solid = true;
        return solid;
    }

    @Override
    public void update() {
        animation_left.update();
        int xa = 0, ya = 0;
        if(keyboard.isUp()) ya-=speed;
        if(keyboard.isDown()) ya+=speed;
        if(keyboard.isLeft()) xa-=speed;
        if(keyboard.isRight()) xa+=speed;

        if(xa != 0 || ya != 0){
            move(xa,ya);
            walking = true;
        }else walking = false;
    }

    @Override
    public void render(Screen screen) {
        if(!walking){
            screen.renderSprite(getX(),getY(),getSprite());
            return;
        }
        if(getDirection() == 0) screen.renderSprite(getX(), getY(), getSprite());
        if(getDirection() == 1) screen.renderSprite(getX(),getY(),animation_left.getCurrentFrame());
        if(getDirection() == 2) screen.renderSprite(getX(),getY(),getSprite());
        if(getDirection() == 3) screen.renderReversedSprite(getX(),getY(),animation_left.getCurrentFrame(),Screen.FLIP_Y);


    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Sprite getIdle_left() {
        return idle_left;
    }

    public void setIdle_left(Sprite idle_left) {
        this.idle_left = idle_left;
    }

    public Sprite getIdle_right() {
        return idle_right;
    }

    public void setIdle_right(Sprite idle_right) {
        this.idle_right = idle_right;
    }

    public Sprite getWalk_left() {
        return walk_left;
    }

    public void setWalk_left(Sprite walk_left) {
        this.walk_left = walk_left;
    }

    public Sprite getWalk_right() {
        return walk_right;
    }

    public void setWalk_right(Sprite walk_right) {
        this.walk_right = walk_right;
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Sprite currentSprite) {
        this.currentSprite = currentSprite;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

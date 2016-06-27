package me.Sauerbier.Antivist.Entity.Mobs;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.Entity.Projectiles.Bullet;
import me.Sauerbier.Antivist.FrameWork.Keyboard;
import me.Sauerbier.Antivist.FrameWork.Mouse;
import me.Sauerbier.Antivist.FrameWork.MouseCallBack;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.AnimatedSprite;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Player extends Mob {

    //TODO should be moved to the gun item
    public static int FIRERATE = 5;

    private String name;
    private Keyboard keyboard;
    private Sprite idle_left, idle_right, walk_left, walk_right, currentSprite;
    private AnimatedSprite animation_left;
    private int speed = 4;
    private boolean walking;
    private int gravity, maxSpeed = 10;
    private float jumpboost;
    private double energy = 1, mana = 1;
    private MouseCallBack mouseCallBack;
    public Player(Level level, JsonObject metadata) {
        super(level, metadata);
            name = metadata.get("name").getAsString();
            this.keyboard = level.getKeyboard();
            setSprite(getLevel().getResources().getSpriteByName("player0"));
            idle_left = getLevel().getResources().getSpriteByName("player1");
            idle_right = getLevel().getResources().getSpriteByName("player2");
            walk_left = getLevel().getResources().getSpriteByName("player3");
            walk_right = getLevel().getResources().getSpriteByName("player4");
            gravity = getLevel().getMetadata().get("gravity").getAsInt();
            currentSprite = getSprite();
            animation_left = new AnimatedSprite(idle_left, walk_left);
            mouseCallBack = new MouseCallBack() {
                @Override
                public void call(Mouse e) {

                    if(FIRERATE > 0 ) {
                        FIRERATE--;
                        if (e.getDown() == 1 && FIRERATE == 0) {
                            if(mana > 0) {
                                double dx = e.getX() - Antivist.getInstance().getWidth() / 2;
                                double dy = e.getY() - Antivist.getInstance().getHeight() / 2 + 32;
                                double dir = Math.atan2(dy, dx);
                                Bullet projectile = new Bullet(getLevel(), new JsonObject());
                                projectile.setVelocity(Vector2i.fromAngle(dir));
                                shoot(projectile);
                                if (mana > 0) {
                                    mana -= 0.02;
                                    if (mana < 0) mana = 0;
                                }
                            }
                        }
                    }else{
                        FIRERATE = 5;
                    }
                }
            };

        mouseCallBack.setCallOnMove(false);
        level.getMouse().addCallBack(100,mouseCallBack);
    }


    @Override
    public void move(int xa, int ya) {
        if (xa != 0 && ya != 0) {
            if(energy > 0){
                move(0, ya);
                move(xa, 0);
            }else{
                move(0, ya/2);
                move(xa/2, 0);
            }

            return;
        }

        if (xa > 0) setDirection(1);
        else if (xa < 0) setDirection(3);
        else if (ya > 0) setDirection(2);
        else if (ya < 0) setDirection(0);

        if (!collision(xa, ya)) {
            getPosition().setX( getPosition().getX() + xa);
            getPosition(). setY(getPosition().getY() + ya);
        }

    }

    @Override
    public boolean collision(int x, int y) {
        return defaultBlockCollision(x, y, 2, 2);
    }

    @Override
    public void update() {
        animation_left.update();
        int xa = 0, ya = 0;
        if (isOnGround() && keyboard.isJump()) {
            jumpboost = -gravity * speed + gravity*1.5f;
            setOnGround(false);
        }

        if (jumpboost > maxSpeed) jumpboost = maxSpeed;
        ya += jumpboost;
        jumpboost++;

        if (keyboard.isLeft()) xa -= speed;
        if (keyboard.isRight()) xa += speed;

        if(keyboard.isRight() || keyboard.isLeft() || keyboard.isJump()){
            walking = true;
            if(energy > 0){
                energy -= 0.001;
                if(energy < 0 ) energy = 0;
            }
        }else{
            walking = false;
            if(energy < 1){
                energy += 0.0005;
                if(energy > 1) energy = 1;
            }
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
        }
        if(mana < 1){
            mana += 0.002;
            if(mana > 1 ) mana = 1;
        }

    }

    @Override
    public void render(Screen screen) {
        if (!walking) {
            screen.renderSprite((int) getPosition().getX(),(int)  getPosition().getY(), getSprite());
            return;
        }
        if (getDirection() == 0) screen.renderSprite((int) getPosition().getX(),(int)  getPosition().getY(), getSprite());
        if (getDirection() == 1) screen.renderSprite((int) getPosition().getX(), (int) getPosition().getY(), animation_left.getCurrentFrame());
        if (getDirection() == 3)
            screen.renderReversedSprite((int) getPosition().getX(), (int) getPosition().getY(), animation_left.getCurrentFrame(), Screen.FLIP_Y);
        if (getDirection() == 2) screen.renderSprite((int) getPosition().getX(),(int)  getPosition().getY(), getSprite());


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }
}

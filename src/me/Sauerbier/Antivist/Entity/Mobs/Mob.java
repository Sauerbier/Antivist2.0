package me.Sauerbier.Antivist.Entity.Mobs;

import me.Sauerbier.Antivist.Entity.Projectiles.Projectile;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class Mob extends Entity {

    private Sprite sprite;
    private int direction = 0;
    private boolean moving = false, onGround = true;
    private List<Projectile> projectiles = new ArrayList<>();

    public abstract void move(int xa, int ya);

    public abstract boolean collision(int xa, int ya);

    public boolean defaultBlockCollision(int x, int y, int xOffset, int yOffset) {
        boolean solid = false;
        for (int i = 0; i < 4; i++) {
            int xt = ((getPosition().getX() + x) + i % 2 * (getSprite().getSizeX() - xOffset)) >> getLevel().getScreen().getTileSizeMask();
            int yt = ((getPosition().getY() + y) + i / 2 * (getSprite().getSizeY() - yOffset)) >> getLevel().getScreen().getTileSizeMask();
            Block block = getLevel().getBlock(xt, yt);
            if (block.isSolid()){
                solid = true;
                if(direction == 2){
                    onGround = true;
                }
            }
        }

        return solid;
    }

    public void shoot( Projectile projectile){
        getLevel().add(projectile);
        //projectiles.add(projectile);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

}

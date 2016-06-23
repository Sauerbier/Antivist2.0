package me.Sauerbier.Antivist.Entity.Projectiles;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Entity.Mobs.Mob;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Block;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class Projectile extends Entity{
    private Vector2i spawn;
    private double  speed, fireRate,damage,range;
    private Sprite sprite;
    private Vector2d velocity, positionD;
    private Level level;
    private Entity shooter, collided;

    public Projectile(Level level, JsonObject metadata) {
        super(level, metadata);
        positionD = new Vector2d(getPosition());
        this.level = level;
    }


    abstract void damage(Mob mob);
    abstract boolean collision(double x, double y);

    public boolean defaultBlockCollision(double x, double y, int xOffset, int yOffset) {
        boolean solid = false;
        for (int i = 0; i < 4; i++) {
            int xt = (((int) x + sprite.getSizeX()) + i % 2 - sprite.getSizeX() + xOffset) / getLevel().getScreen().getTileSize();
            int yt = (((int) y + sprite.getSizeY()) + i / 2 - sprite.getSizeY() + yOffset) / getLevel().getScreen().getTileSize();
            Block block = getLevel().getBlock(xt, yt);
            if (block.isSolid()){
                solid = true;
                collided = block;
            }
        }

        return solid;
    }




    public Vector2i getSpawn() {
        return spawn;
    }

    public void setSpawn(Vector2i spawn) {
        this.spawn = spawn;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }


    public Vector2d getVelocity() {
        return velocity;
    }

    public double getDamage() {
        return damage;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }


    public Entity getShooter() {
        return shooter;
    }

    public void setShooter(Entity shooter) {
        this.shooter = shooter;
    }

    public Entity getCollided() {
        return collided;
    }

    public void setCollided(Entity collided) {
        this.collided = collided;
    }

    public Vector2d getPositionD() {
        return positionD;
    }

    public void setPositionD(Vector2d positionD) {
        this.positionD = positionD;
    }
}

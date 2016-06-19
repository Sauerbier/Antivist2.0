package me.Sauerbier.Antivist.Entity.Entities;

import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Particle extends Entity {

    private Vector2i spawn;
    private Vector2d position,velocity;
    private Sprite sprite;
    private int life;

    public Particle(Level level,Sprite sprite, Vector2i spawn, int life) {
        setLevel(level);
        this.sprite = sprite;
        this.spawn = spawn;
        position = new Vector2d(spawn.getX(),spawn.getY());
        velocity = new Vector2d(Math.sin(getRandom().nextGaussian()),Math.sin(getRandom().nextGaussian()));
        this.life = life;
    }

    public Particle(Level level,Sprite sprite, Vector2i spawn, int life, int amount) {
        this(level,sprite, spawn, life);

        for (int i = 0; i < amount - 1; i++) {
            level.add(new Particle(level,sprite, spawn, getRandom().nextInt(life/2) + life));
        }
    }

    @Override
    public void update() {
        if(life > 0){
            position.add(velocity);
            life--;
        }else{
            getLevel().remove(this);
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)position.getX(),(int)position.getY(),sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2i getSpawn() {
        return spawn;
    }

    public void setSpawn(Vector2i spawn) {
        this.spawn = spawn;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }
}

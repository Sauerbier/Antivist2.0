package me.Sauerbier.Antivist.Entity;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Level.Level;

import java.util.Random;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class Entity {

    private Vector2d position = new Vector2d(0,0);
    private boolean removed = false, gravity=true;
    private Level level;
    private final Random random = new Random();
    private JsonObject metadata;

    public Entity(Level level, JsonObject metadata){
        this.level = level;
        this.metadata = metadata;
    }

    public abstract void update();
    public abstract void render(Screen screen);

    public void remove(){
        removed = true;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Random getRandom() {
        return random;
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public JsonObject getMetadata() {
        return metadata;
    }

    public void setMetadata(JsonObject metadata) {
        this.metadata = metadata;
    }
}

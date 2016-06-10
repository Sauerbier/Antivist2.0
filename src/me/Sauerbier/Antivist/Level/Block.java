package me.Sauerbier.Antivist.Level;

import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Graphics.Sprite;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class Block extends Entity {

    private String id;
    public Sprite sprite;

    public Block(String id, Sprite sprite) {
        this.id = id;
        this.sprite = sprite;
    }

    public abstract boolean isSolid();


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

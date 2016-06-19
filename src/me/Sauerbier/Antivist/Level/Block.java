package me.Sauerbier.Antivist.Level;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Graphics.Sprite;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class Block extends Entity {

    private String id;
    private JsonObject metadata;
    public Sprite sprite;

    public Block(Level level, JsonObject metadata) {
        super(level, metadata);

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

    public JsonObject getMetaData() {
        return metadata;
    }

    public void setMetaData(JsonObject metaData) {
        this.metadata = metaData;
    }
}

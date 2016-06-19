package me.Sauerbier.Antivist.Level.Tiles;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Block;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class DefaultSolidBlock extends Block {

    public DefaultSolidBlock(String id, Sprite sprite, JsonObject metaData) {
        super(id, sprite, metaData);
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderBlocks(this,  getPosition().getX() << screen.getTileSizeMask(),  getPosition().getY() << screen.getTileSizeMask());
    }
}

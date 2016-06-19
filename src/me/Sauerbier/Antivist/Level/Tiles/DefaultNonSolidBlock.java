package me.Sauerbier.Antivist.Level.Tiles;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Level.Block;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class DefaultNonSolidBlock extends Block{


    public DefaultNonSolidBlock(Level level, JsonObject metadata) {
        super(level, metadata);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderBlocks(this,  getPosition().getX() << screen.getTileSizeMask(),  getPosition().getY() << screen.getTileSizeMask());
    }
}

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
public class AirBlock extends Block{


    public AirBlock(Level level, JsonObject metadata) {
        super(level, metadata);
    }

    @Override
    public void render( Screen screen) {
       // screen.renderBlocks(this, getX() << screen.getTileSizeMask(),getY() << screen.getTileSizeMask());
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isSolid() {
        return false;
    }
}

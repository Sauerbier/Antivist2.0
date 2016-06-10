package me.Sauerbier.Antivist.Level.Tiles;

import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Block;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class DefaultNonSolidBlock extends Block{
    public DefaultNonSolidBlock(String id, Sprite sprite) {
        super(id, sprite);
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
        screen.renderBlocks(this, getX() << screen.getTileSizeMask(), getY() << screen.getTileSizeMask());
    }
}

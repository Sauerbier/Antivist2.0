package me.Sauerbier.Antivist.Level.Tiles;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.Entity.Entities.Spawner;
import me.Sauerbier.Antivist.Entity.EntityType;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Block;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class SpawnerBlock extends Block{
    private EntityType type;
    private int amount,totalamount,spawnDelay;
    private boolean added = false, infinite;

    public SpawnerBlock(String id, Sprite sprite, JsonObject metaData) {
        super(id, sprite, metaData);
        type = EntityType.valueOf(metaData.get("type").getAsString());
         amount = metaData.get("amountPerSpawn").getAsInt();
        totalamount = metaData.get("totalAmount").getAsInt();
         spawnDelay = metaData.get("spawnDelay").getAsInt();
        infinite = metaData.get("infinite").getAsBoolean();
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void update() {
        if(!added) {
            Vector2i ps = new Vector2i(getPosition());
            ps.multiply(Antivist.getInstance().getLevel().getScreen().getTileSize());
            Spawner spawner = new Spawner(ps, type, amount, totalamount, spawnDelay, infinite);
            Antivist.getInstance().getLevel().add(spawner);
            added = true;
        }
    }

    @Override
    public void render(Screen screen) {
      //  screen.renderBlocks(this,  getPosition().getX() << screen.getTileSizeMask(),  getPosition().getY() << screen.getTileSizeMask());
    }
}

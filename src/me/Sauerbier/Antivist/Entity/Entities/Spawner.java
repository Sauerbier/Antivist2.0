package me.Sauerbier.Antivist.Entity.Entities;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Entity.EntityType;
import me.Sauerbier.Antivist.Entity.Mobs.Player;
import me.Sauerbier.Antivist.Entity.Projectiles.Bullet;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Level.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Spawner extends Entity{

    private Vector2i spawn;
    private int amount, totalAmount;
    private int spawnDelay, delay ;
    private EntityType type;
    private List<Entity> entities = new ArrayList<>();
    private boolean infinite;

    public Spawner(Level level, JsonObject metadata) {
        super(level, metadata);
        spawn = new Vector2i(metadata.get("posX").getAsInt(),metadata.get("posY").getAsInt());
        amount = metadata.get("amountPerSpawn").getAsInt();
        totalAmount = metadata.get("totalAmount").getAsInt();
        spawnDelay = metadata.get("spawnDelay").getAsInt();
        type = EntityType.valueOf(metadata.get("type").getAsString());
        infinite = metadata.get("infinite").getAsBoolean();
    }


    private void spawn(){
        if(delay > 0) {
            delay --;
        }else{
            if(infinite || entities.size() <= totalAmount)
            switch (type) {
                case PARTICLE:
                    for (int i = 0; i < amount; i++) {
                        Particle particle = new Particle(Antivist.getInstance().getLevel(),getMetadata().get("particle").getAsJsonObject());
                        particle.setPosition(new Vector2i(getPosition()));
                        particle.setPosition(new Vector2d(getPosition()));
                        entities.add(particle);
                    }
                    break;
                case PLAYER:
                    for (int i = 0; i < amount; i++) {
                        entities.add(new Player(Antivist.getInstance().getLevel(), new JsonObject()));
                    }
                    break;
                case BULLET:
                    JsonObject object = new JsonObject();
                    object.addProperty("sprite","null");
                    object.addProperty("life", 200);
                    for (int i = 0; i < amount; i++) {
                        Antivist.getInstance().getLevel().add(new Bullet(Antivist.getInstance().getLevel(),object));
                    }
                    break;
            }
            delay = spawnDelay;
        }
    }

    @Override
    public void update() {
            spawn();
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).isRemoved()){
                entities.remove(i);
                continue;
            }
            entities.get(i).update();
        }
    }

    @Override
    public void render(Screen screen) {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(Antivist.getInstance().getLevel().getScreen());
        }
    }
}

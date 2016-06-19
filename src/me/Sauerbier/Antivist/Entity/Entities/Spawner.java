package me.Sauerbier.Antivist.Entity.Entities;

import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Entity.EntityType;
import me.Sauerbier.Antivist.Entity.Mobs.Player;
import me.Sauerbier.Antivist.Entity.Projectiles.Bullet;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;

import java.awt.*;
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

    public Spawner(Vector2i spawn, EntityType type, int amount, int totalAmount, int spawnDelay, boolean infinite) {
        this.spawn = spawn;
        this.type = type;
        this.amount = amount;
        this.totalAmount = totalAmount;
        this.infinite = infinite;
        this.spawnDelay = spawnDelay;
        this.delay = spawnDelay;
    }


    private void spawn(){
        if(delay > 0) {
            delay --;
        }else{
            if(infinite || entities.size() <= totalAmount)
            switch (type) {
                case PARTICLE:
                    for (int i = 0; i < amount; i++) {
                        Antivist.getInstance().getLevel().add(new Particle(Antivist.getInstance().getLevel(),
                                new Sprite(2, 2, new Color(getRandom().nextInt(0xffffff))), spawn, 200));
                    }
                    break;
                case PLAYER:
                    for (int i = 0; i < amount; i++) {
                        Antivist.getInstance().getLevel().add(new Player(Antivist.getInstance().getLevel(), Antivist.getInstance().getLevel().getKeyboard()));
                    }
                    break;
                case BULLET:
                    for (int i = 0; i < amount; i++) {
                        Antivist.getInstance().getLevel().add(new Bullet(Antivist.getInstance().getLevel(), this, spawn, 1));
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

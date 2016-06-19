package me.Sauerbier.Antivist.Entity.Projectiles;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entities.DefaultSystem;
import me.Sauerbier.Antivist.Entity.Entities.Particle;
import me.Sauerbier.Antivist.Entity.Mobs.Mob;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Bullet extends Projectile {


    public Bullet(Level level, JsonObject metadata) {
        super(level, metadata);
        setRange(300);
        setDamage(20);
        setFireRate(15);
        setSprite(level.getResources().getSpriteByName("bullet"));
        setSpeed(8);
    }

    @Override
    public void damage(Mob mob) {

    }

    @Override
    boolean collision(double x, double y) {
        return defaultBlockCollision(x,y,12,11);
    }

    @Override
    public void update() {
        if(getPosition().distance(getSpawn()) > getRange()){
            getLevel().remove(this);
            return;
        }
        move();
    }

    public void move() {
        if(!collision(getPosition().getX(),getPosition().getY())) {
           getPosition().add(getVelocity());
        }else{
            getLevel().remove(this);

            JsonObject object = new JsonObject();
            object.addProperty("sprite","null");
            object.addProperty("life", 200);
            for (int i = 0; i < 100; i++) {
                Particle particle = new Particle(getLevel(),object);
                particle.setSystem(new DefaultSystem(particle, null));
                particle.setPosition(new Vector2i(getPosition()));
                particle.setPosition(new Vector2d(getPosition()));
                getLevel().add(particle);

            }

        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite( getPosition().getX(),getPosition().getY(),getSprite());
    }
}

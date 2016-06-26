package me.Sauerbier.Antivist.Entity.Projectiles;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entities.Particle.FadingSystem;
import me.Sauerbier.Antivist.Entity.Entities.Particle.Particle;
import me.Sauerbier.Antivist.Entity.Mobs.Mob;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
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
        if(getPositionD().distance(getSpawn()) > getRange()){
            getLevel().remove(this);
            return;
        }
        move();
    }

    public void move() {
        if(!collision(getPositionD().getX()- (getSprite().getSizeX() >> 1),getPositionD().getY())) {
           getPositionD().add(getVelocity());
        }else{
            JsonObject object = new JsonObject();
            object.addProperty("sprite","null");
            object.addProperty("life", 200);
            object.addProperty("collide", true);
            JsonObject system = new JsonObject();
            system.addProperty("startColor",getRandom().nextInt(0xffffff));
            system.addProperty("endColor",getRandom().nextInt(0xffffff));
            system.addProperty("life", 200);
            Particle particle = null;
            for (int i = 0; i < 100; i++) {
                 particle = new Particle(getLevel(),object);
                particle.setSystem(new FadingSystem(particle, system));
                particle.setPosition(new Vector2d(getPositionD().getX() + (getSprite().getSizeX() >> 1),getPositionD().getY() ));
                getLevel().add(particle);

            }

            getLevel().remove(this);
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite( (int)getPositionD().getX(),(int)getPositionD().getY(),getSprite());
    }
}

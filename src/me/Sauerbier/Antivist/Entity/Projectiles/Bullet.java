package me.Sauerbier.Antivist.Entity.Projectiles;

import me.Sauerbier.Antivist.Entity.Entities.Particle;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Entity.Mobs.Mob;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Level;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Bullet extends Projectile {


    public Bullet(Level level, Entity shooter, Vector2i spawn, double angle) {
        super(level,shooter, spawn);
        setRange(300);
        setDamage(20);
        setFireRate(15);
        setSprite(getLevel().getResources().getSpriteByName("bullet"));
        setSpeed(8);
        setVelocity(Vector2i.fromAngle(angle));
        getVelocity().multiply( getSpeed());
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

            new Particle(getLevel(),new Sprite(2,2,new Color(getRandom().nextInt(0xffffff))),getPosition(),200,100);


        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) getPosition().getX(), (int) getPosition().getY(),getSprite());
    }
}

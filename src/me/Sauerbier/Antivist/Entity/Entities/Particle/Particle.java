package me.Sauerbier.Antivist.Entity.Entities.Particle;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.FrameWork.Utils;
import me.Sauerbier.Antivist.FrameWork.Vector2d;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Level;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Particle extends Entity {

    private Vector2d velocity;
    private Sprite sprite;
    private int life;
    private ParticleSystem system;
    private boolean collide;
    public Particle(Level level, JsonObject metadata) {
        super(level, metadata);

        if(metadata.has("sprite") && !metadata.get("sprite").getAsString().equals("null")){
            sprite = level.getResources().getSpriteByName(metadata.get("sprite").getAsString());
        }else{
            Color color = new Color(getRandom().nextInt(0xffffff));
            sprite = new Sprite(1,1,color);
        }
        collide = metadata.get("collide").getAsBoolean();;
        velocity = new Vector2d(getRandom().nextGaussian(),getRandom().nextGaussian());
        life = metadata.get("life").getAsInt();
        life = Utils.getRandomNumber(life/2,life + life/4);
        if(metadata.has("particleSystem")) {
            try {
                Class<? extends ParticleSystem> clazz = (Class<? extends ParticleSystem>) Class.forName(metadata.get("particleSystem").getAsJsonObject().get("class").getAsString());
                system = clazz.getConstructor(Particle.class, JsonObject.class).newInstance(this, metadata.get("particleSystem").getAsJsonObject());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
          system = new DefaultSystem(this,null);
        }
    }





    @Override
    public void update() {
        if(life > 0){
            system.calculate();
            life --;
        }else{
            remove();
            getLevel().remove(this);
        }
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)getPosition().getX(),(int)getPosition().getY(),sprite);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Vector2d getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2d velocity) {
        this.velocity = velocity;
    }

    public ParticleSystem getSystem() {
        return system;
    }

    public void setSystem(ParticleSystem system) {
        this.system = system;
    }

    public boolean isCollide() {
        return collide;
    }
}

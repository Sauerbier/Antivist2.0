package me.Sauerbier.Antivist.Entity.Entities.Particle;

import com.google.gson.JsonObject;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class FadingSystem extends ParticleSystem {
    private double diff,range;
    private Color start,end;
    private JsonObject metadata;

    public FadingSystem(Particle particle, JsonObject metadata) {
        super(particle, metadata);
        diff = particle.getLife();
        range = particle.getLife();
        this.start = Color.decode(metadata.get("startColor").getAsString());
        this.end = Color.decode(metadata.get("endColor").getAsString());
    }

    @Override
    public void calculate() {
        double ratio = diff / range;
        if(!collision()) {
            getParticle().getPosition().add(getParticle().getVelocity());
        }else{
            getParticle().getVelocity().multiply(-1);
            getParticle().getPosition().add(getParticle().getVelocity());
        }
        int red = (int)Math.abs((ratio * start.getRed()) + ((1 - ratio) * end.getRed()));
        int green = (int)Math.abs((ratio * start.getGreen()) + ((1 - ratio) * end.getGreen()));
        int blue = (int)Math.abs((ratio * start.getBlue()) + ((1 - ratio) * end.getBlue()));
        getParticle().getSprite().changeColor(new Color(red,green,blue));
        if(diff != 0)
            diff --;
        else  diff = 0;
    }

    @Override
    public boolean collision() {
        if(getParticle().isCollide())
            return defaultBlockCollision(getParticle().getPosition().getX(),getParticle().getPosition().getY(),0,0);
        else return false;
        //return false;
    }

}

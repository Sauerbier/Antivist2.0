package me.Sauerbier.Antivist.Entity.Entities.Particle;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.FrameWork.Vector2d;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class FireSystem extends ParticleSystem {
    private double diff,range,vel;
    private Color start,end;

    public FireSystem(Particle particle, JsonObject metadata) {
        super(particle, metadata);
        diff = particle.getLife();
        range = particle.getLife();
        this.start = new Color(0x7C0B00);
        this.end = new Color(0xAA9100);
        particle.setVelocity(new Vector2d(particle.getRandom().nextGaussian()/6,particle.getRandom().nextGaussian()/6));
        particle.setLife(particle.getLife() - 5);
    }

    @Override
    public void calculate() {
        double ratio = diff / range;
        getParticle().getVelocity().add(new Vector2d(0,vel));
        getParticle().getPosition().add(getParticle().getVelocity());
        int red = (int)Math.abs((ratio * start.getRed()) + ((1 - ratio) * end.getRed()));
        int green = (int)Math.abs((ratio * start.getGreen()) + ((1 - ratio) * end.getGreen()));
        int blue = (int)Math.abs((ratio * start.getBlue()) + ((1 - ratio) * end.getBlue()));
        getParticle().getSprite().changeColor(new Color(red,green,blue));

        if(diff != 0) {
            diff--;
        } else {
            diff = 0;
        }

        vel -= 0.001;
    }

    @Override
    public boolean collision() {
        if(getParticle().isCollide())
            return defaultBlockCollision(getParticle().getPosition().getX(),getParticle().getPosition().getY(),0,0);
        else return false;
    }
}

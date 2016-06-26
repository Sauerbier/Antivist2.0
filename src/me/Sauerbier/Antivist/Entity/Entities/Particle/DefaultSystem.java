package me.Sauerbier.Antivist.Entity.Entities.Particle;

import com.google.gson.JsonObject;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class DefaultSystem extends ParticleSystem {
    public DefaultSystem(Particle particle, JsonObject metadata) {
        super(particle, metadata);
    }

    @Override
    public void calculate() {
        getParticle().getPositionD().add(getParticle().getVelocity());
    }

    @Override
    public boolean collision() {
        if(getParticle().isCollide())
            return defaultBlockCollision(getParticle().getPositionD().getX(),getParticle().getPositionD().getY(),0,0);
        else return false;
    }
}

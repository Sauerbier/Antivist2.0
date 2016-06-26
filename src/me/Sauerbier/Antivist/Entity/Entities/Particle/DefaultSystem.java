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
        getParticle().getPosition().add(getParticle().getVelocity());
    }

    @Override
    public boolean collision() {
        if(getParticle().isCollide())
            return defaultBlockCollision(getParticle().getPosition().getX(),getParticle().getPosition().getY(),0,0);
        else return false;
    }
}

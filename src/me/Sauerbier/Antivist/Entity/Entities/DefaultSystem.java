package me.Sauerbier.Antivist.Entity.Entities;

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
}

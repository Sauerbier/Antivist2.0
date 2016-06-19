package me.Sauerbier.Antivist.Entity.Entities;

import com.google.gson.JsonObject;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class ParticleSystem {

        private Particle particle;
        private JsonObject metadata;

        public ParticleSystem(Particle particle, JsonObject metadata){
                this.particle = particle;
                this.metadata = metadata;
        }

        public abstract void calculate();

        public Particle getParticle() {
                return particle;
        }

        public void setParticle(Particle particle) {
                this.particle = particle;
        }

        public JsonObject getMetadata() {
                return metadata;
        }

        public void setMetadata(JsonObject metadata) {
                this.metadata = metadata;
        }
}

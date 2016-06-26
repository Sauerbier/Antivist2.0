package me.Sauerbier.Antivist.Entity.Entities.Particle;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Level.Block;

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
        public abstract boolean collision();

        public boolean defaultBlockCollision(double x, double y, double xOffset, double yOffset) {
                boolean solid = false;
                for (int i = 0; i < 4; i++) {
                        int xt = ((int)x - i % 2 * particle.getSprite().getSizeX() - (int)xOffset) >> particle.getLevel().getScreen().getTileSizeMask();
                        int yt = ( (int)y - (i >> 1) * particle.getSprite().getSizeY() - (int)yOffset) >> particle.getLevel().getScreen().getTileSizeMask();
                        Block block = particle.getLevel().getBlock(xt, yt);
                        if (block.isSolid()){
                                solid = true;
                        }
                }

                return solid;
        }

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

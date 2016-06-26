package me.Sauerbier.Antivist.Entity.Mobs;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Level.Level;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Fluffy extends Mob {

    private int vel = 2;
    private boolean right = true;
    public Fluffy(Level level, JsonObject metadata) {
        super(level, metadata);
        setSprite(getLevel().getResources().getSpriteByName(metadata.get("sprite").getAsString()));

    }


    @Override
    public void move(int xa, int ya) {
        if (xa != 0 && ya != 0) {
            move(0, ya);
            move(xa, 0);
            return;
        }

        if (xa > 0) setDirection(1);
        else if (xa < 0) setDirection(3);
        else if (ya > 0) setDirection(2);
        else if (ya < 0) setDirection(0);

        if (!collision(xa, ya) ) {
            if(getDirection() == 1) right = !right;

            if(right) {
                getPosition().setX(getPosition().getX() - xa);
                getPosition(). setY(getPosition().getY() - ya);
            }else{
                getPosition().setX(getPosition().getX() + xa);
                getPosition(). setY(getPosition().getY() + ya);
            }
        }else{
            if(getDirection() == 3) right = !right;

            if(right) {
                getPosition().setX(getPosition().getX() - xa);
                getPosition(). setY(getPosition().getY() - ya);
            }else{
                getPosition().setX(getPosition().getX() + xa);
                getPosition(). setY(getPosition().getY() + ya);
            }
        }
    }

    @Override
    public boolean collision(int xa, int ya) {
        return defaultBlockCollision(xa,ya,0,0);
    }

    @Override
    public void update() {
            move(vel, 0);
            setMoving(true);

        int mask = getLevel().getScreen().getTileSizeMask();
        setOnGround(getLevel().getBlock((int)getPosition().getX() << mask,((int)getPosition().getY() << mask) + 1).isSolid());

    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int) getPosition().getX(), (int) getPosition().getY(), getSprite());
    }
}

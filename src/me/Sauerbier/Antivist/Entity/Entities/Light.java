package me.Sauerbier.Antivist.Entity.Entities;

import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.Graphics.Screen;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Level.Level;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Light extends Entity implements Cloneable{

    private BufferedImage image;
    private BufferedImage cimage;
    private Sprite sprite;
    private int radius;
    private float luminosity;
    private double angle;
    private Color color;
    private boolean dynamic;

    public Light(Level level, JsonObject metadata) {
        super(level, metadata);
        color = Color.decode(metadata.get("color").getAsString());
        dynamic = metadata.get("dynamic").getAsBoolean();
        radius = metadata.get("radius").getAsInt();
        luminosity = metadata.get("luminosity").getAsFloat();
        int i5 = metadata.get("angle").getAsInt();
        angle = Math.toRadians(i5);

        image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_RGB);
        cimage = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        Graphics2D g3 = (Graphics2D) cimage.getGraphics();

        Point2D center = new Point2D.Float(radius, radius);
        Point2D focus = new Point2D.Float(radius, radius);
        if(i5 != 9001) {
            focus.setLocation(Math.cos(angle) * 3 * radius / 4 + radius, Math.sin(angle) * 3 * radius / 4 + radius);
        }
        float[] dist = {0f, 1f};
        Color[] colors = {new Color(color.getRed(), color.getGreen(), color.getBlue(), 1), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, focus, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        g2.setPaint(rgp);
        g2.fillOval(0, 0, radius * 2, radius * 2);
        if(i5 == 9001) {
            colors = new Color[] {new Color(color.getRed(), color.getGreen(), color.getBlue(), 1 / 4), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
            rgp = new RadialGradientPaint(center, radius / 2, focus, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
            g2.setPaint(rgp);
            g2.fillOval(0, 0, radius * 2, radius * 2);
        }
        else {
            center.setLocation(Math.cos(angle) * radius / 2 + radius, Math.sin(angle) * radius / 2 + radius);
            focus.setLocation(radius + Math.cos(angle) * 3 * radius / 4, radius + Math.sin(angle) * 3 * radius / 4);
            colors = new Color[] {new Color(color.getRed(), color.getGreen(), color.getBlue(), 1 / 4), new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
            rgp = new RadialGradientPaint(center, radius / 2, focus, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
            g2.setPaint(rgp);
            g2.fillOval((int) (center.getX() - radius / 2), (int) (center.getY() - radius / 2), radius, radius);
        }

        colors = new Color[] {color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 0)};
        rgp = new RadialGradientPaint(center, radius, focus, dist, colors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
        g3.setPaint(rgp);
        g3.fillOval(0, 0, radius * 2, radius * 2);

        sprite = new Sprite(cimage,false);
        sprite.changeBrigthness(luminosity);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Screen screen) {
        if(!dynamic){
            screen.renderLight(getPosition().getX() << screen.getTileSizeMask(),getPosition().getY() << screen.getTileSizeMask(),this);
        }else{
            screen.renderLight(getPosition().getX(),getPosition().getY() ,this);
        }
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
            return null;
        }

    }


    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public float getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(int luminosity) {
        this.luminosity = luminosity;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDynamic() {
        return dynamic;
    }

    public void setDynamic(boolean dynamic) {
        this.dynamic = dynamic;
    }

    public BufferedImage getCimage() {
        return cimage;
    }

}

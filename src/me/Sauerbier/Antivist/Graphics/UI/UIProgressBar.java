package me.Sauerbier.Antivist.Graphics.UI;

import me.Sauerbier.Antivist.FrameWork.Vector2i;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class UIProgressBar implements UIComponent {


    private Vector2i position,size;
    private Color color;
    private double progress;
    private UIComponent parent;
    private UIComponent child;
    private boolean blink = false;
    private double percentage;
    private Color blinkColor;
    private int blinkDelay,blinkTime;

    public UIProgressBar(Vector2i position, Vector2i size, Color color) {
        this.position = position;
        this.size = size;
        this.color = color;
    }

    @Override
    public void update() {
        if(blink && progress < percentage) {
            if (blinkDelay > 50) {
                blinkDelay = 0;
                blinkTime = 400;
            } else {
                blinkDelay++;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(getBackgroundColor());
        g.fillRect(position.getX(),position.getY(),size.getX(),size.getY());

        if(blinkTime > 0){
            blinkTime--;
            g.setColor(blinkColor);
            g.fillRect(position.getX(),position.getY(),(int) (progress * size.getX()),size.getY());
        }else {
            g.setColor(color);
            g.fillRect(position.getX(), position.getY(), (int) (progress * size.getX()), size.getY());
        }


        if(child != null) {
            child.render(g);
        }

    }


    public void blink(boolean blink,double percentage,Color blinkColor){
        this.blink = blink;
        this.percentage = percentage;
        this.blinkColor = blinkColor;
    }


    @Override
    public void add(UIComponent component) {
        component.setParent(this);
        Vector2i rel = position.clone();
        rel.add(component.getPosition());
        component.setPosition(rel);
        this.child = component;
    }

    @Override
    public UIComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(UIComponent component) {
        this.parent = component;
    }

    @Override
    public Vector2i getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2i position) {
        this.position = position;
    }

    @Override
    public Color getBackgroundColor() {
        return UIManager.BACKGROUD_COLOR;
    }


    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        if(progress < 0 || progress > 1){
            throw new IndexOutOfBoundsException("Progress "+progress+" is not between 0 and 1");
        }

        this.progress = progress;
    }
}

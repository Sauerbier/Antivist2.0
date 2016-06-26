package me.Sauerbier.Antivist.Graphics.UI;

import me.Sauerbier.Antivist.FrameWork.Vector2i;

import java.awt.*;
import java.util.ArrayList;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class UIPanel implements UIComponent{

    private java.util.List<UIComponent> components = new ArrayList<>();
    private Vector2i position, size;
    private Color backgroundColor;

    public UIPanel(Vector2i position,Vector2i size, Color backgroundColor) {
        this.position = position;
        this.size = size;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void update() {

        for (int i = 0; i < components.size(); i++) {
            components.get(i).update();
        }
    }

    @Override
    public void render(Graphics g) {
        if(backgroundColor.getRGB() != UIManager.TRANSPARENT){
            g.setColor(backgroundColor);
            g.fillRect(position.getX(),position.getY(),size.getX(),size.getY());
        }
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render(g);
        }
    }

    @Override
    public void add(UIComponent component) {
        component.setParent(this);
        Vector2i rel = getPosition().clone();
        rel.add(component.getPosition());
        component.setPosition(rel);
        components.add(component);
    }

    @Override
    public UIComponent getParent() {
        return null;
    }

    @Override
    public void setParent(UIComponent component) {

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
        return backgroundColor;
    }

    public Vector2i getSize() {
        return size;
    }

    public void setSize(Vector2i size) {
        this.size = size;
    }
}

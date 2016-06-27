package me.Sauerbier.Antivist.Graphics.UI;

import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.FrameWork.Mouse;
import me.Sauerbier.Antivist.FrameWork.MouseCallBack;
import me.Sauerbier.Antivist.FrameWork.Vector2i;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class UIButton implements UIComponent {

    private String text;
    private Vector2i position;
    private Vector2i size;
    private Color color,textColor;
    private UIComponent parent;
    private List<UIComponent> childs = new ArrayList<>();
    private UIButtonEvent event;
    private MouseCallBack callBack;
    private Rectangle bounds;
    private boolean inside,pressed,ignorePress;
    private Image icon;

    public UIButton(String text,Vector2i position, Vector2i size, Color color, Color textColor) {
        this.text = text;
        this.position = position;
        this.size = size;
        this.color = color;
        this.textColor = textColor;
        bounds = new Rectangle(position.getX(),position.getY(),size.getX() + 5,size.getY());
        callBack = new MouseCallBack() {
            @Override
            public void call(Mouse e) {
                if (event != null && bounds.contains(e.getX(), e.getY())) {
                    if(!inside) {
                        if(e.getDown() == MouseEvent.BUTTON1) {
                            ignorePress = true;
                        }else ignorePress = false;
                        event.mouseEnter(UIButton.this);
                    }
                    inside = true;

                    if(!pressed && !ignorePress && e.getDown() == MouseEvent.BUTTON1){
                        event.pressed(UIButton.this);
                        pressed = true;
                        callBack.setCanceled(true);
                    }else if( e.getDown() == MouseEvent.NOBUTTON){
                        if(pressed){

                            pressed = false;
                            event.released(UIButton.this);
                            callBack.setCanceled(false);
                        }
                        ignorePress = false;
                    }
                }else{
                    if(inside) {
                        event.mouseLeave(UIButton.this);
                        pressed = false;
                        callBack.setCanceled(false);
                    }
                    inside = false;
                }
            }
        };
        callBack.setCallOnMove(true);
        Antivist.getCurrent().getMouse().addCallBack(1,callBack);
    }

    @Override
    public void update() {
        if(event != null) {

        }


        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).update();
        }
    }

    @Override
    public void render(Graphics g) {
        if(icon == null) {
            g.setColor(color);
            g.fillRoundRect(position.getX(), position.getY(), size.getX() + 5, size.getY(), 5, 5);
            if (!text.equals("")) {
                g.setColor(textColor);
                g.drawString(text, position.getX() + 5, position.getY() + 15);
            }
        }else{
            g.drawImage(icon,position.getX(), position.getY(),null);
        }
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).render(g);
        }
    }

    @Override
    public void add(UIComponent component) {
        component.setParent(this);
        Vector2i rel = position.clone();
        rel.add(component.getPosition());
        component.setPosition(rel);
        childs.add(component);
    }

    @Override
    public UIComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(UIComponent component) {
        this.parent = component;
        bounds = new Rectangle(component.getPosition().getX() + position.getX(), component.getPosition().getY() + position.getY(),size.getX() + 5,size.getY());
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
        return color;
    }

    public UIButtonEvent getEvent() {
        return event;
    }

    public void setEvent(UIButtonEvent event) {
        this.event = event;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        bounds = new Rectangle(getPosition().getX(),getPosition().getY() ,icon.getWidth(null),icon.getHeight(null));
        this.icon = icon;
    }
}

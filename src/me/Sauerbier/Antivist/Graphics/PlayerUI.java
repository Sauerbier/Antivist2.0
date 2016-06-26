package me.Sauerbier.Antivist.Graphics;

import me.Sauerbier.Antivist.Antivist;
import me.Sauerbier.Antivist.Entity.Mobs.Player;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.UI.UILabel;
import me.Sauerbier.Antivist.Graphics.UI.UIManager;
import me.Sauerbier.Antivist.Graphics.UI.UIPanel;
import me.Sauerbier.Antivist.Graphics.UI.UIProgressBar;

import java.awt.*;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class PlayerUI {


    public  UIPanel panel;
    private  UILabel px,py,pName;
    private UIProgressBar healthBar,manaBar,energyBar;
    private  Player player;

    public PlayerUI(Player player){
        this.player = player;
        panel = new UIPanel(new Vector2i(Antivist.WIDTH*Antivist.SCALE-300,0),new Vector2i(300,Antivist.HEIGHT*Antivist.SCALE),new Color(33, 33, 33, 233));
        pName = new UILabel( player.getName() + ":",new Font("Ubuntu",Font.BOLD,24),new Vector2i(5,25), UIManager.BACKGROUD_COLOR);
        panel.add(pName);
        px = new UILabel("X: " + player.getPosition().getX(),new Font("Ubuntu",Font.BOLD,24),new Vector2i(5,275*3),Color.red);
        panel.add(px);
        py = new UILabel("Y: " + player.getPosition().getY(),new Font("Ubuntu",Font.BOLD,24),new Vector2i(5,284*3),Color.red);
        panel.add(py);

        healthBar = new UIProgressBar(new Vector2i(5,30),new Vector2i(panel.getSize().getX()-10,15),new Color(200,0,0));
        UILabel health = new UILabel("Health",new Font("Ubuntu",Font.PLAIN,14),new Vector2i(5,12),Color.white);
        panel.add(healthBar);
        healthBar.add(health);
        healthBar.setProgress(0.7);

        manaBar = new UIProgressBar(new Vector2i(5,50),new Vector2i(panel.getSize().getX()-10,15),new Color(0,200,0));
        UILabel mana = new UILabel("Mana",new Font("Ubuntu",Font.PLAIN,14),new Vector2i(5,12),Color.white);
        panel.add(manaBar);
        manaBar.add(mana);
        manaBar.setProgress(0.3);

        energyBar = new UIProgressBar(new Vector2i(5,70),new Vector2i(panel.getSize().getX()-10,15),new Color(200,150,0));
        UILabel energy = new UILabel("Energy",new Font("Ubuntu",Font.PLAIN,14),new Vector2i(5,12),Color.white);
        panel.add(energyBar);
        energyBar.add(energy);
        energyBar.setProgress(0.8);
        energyBar.blink(true,0.3,Color.red);

    }



    public void update(){
        px.setText("X: " + player.getPosition().getX());
        py.setText("Y: " + player.getPosition().getY());

        energyBar.setProgress(player.getEnergy());
        manaBar.setProgress(player.getMana());
    }


}

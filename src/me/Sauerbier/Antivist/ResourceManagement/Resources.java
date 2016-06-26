package me.Sauerbier.Antivist.ResourceManagement;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.Sauerbier.Antivist.Entity.Entity;
import me.Sauerbier.Antivist.FrameWork.Vector2i;
import me.Sauerbier.Antivist.Graphics.Sprite;
import me.Sauerbier.Antivist.Graphics.SpriteSheet;
import me.Sauerbier.Antivist.Level.Block;
import me.Sauerbier.Antivist.Level.Level;
import me.Sauerbier.Antivist.Level.Tiles.AirBlock;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public class Resources {

    public static Block AIR ;

    private HashMap<String,SpriteSheet> spriteSheets = new HashMap<>();
    private HashMap<String,Sprite> sprites = new HashMap<>();
    //private HashMap<String,Block> blocks = new HashMap<>();
    private Block[] blocks;
    private Level level;

    public Resources(Level level) {
        this.level = level;
       AIR = new AirBlock(level,null);
        AIR.setSprite(new Sprite(32,32, new Color(0x00ffffff)));
    }

    public void loadSprites(String spritesJson) throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader r = new BufferedReader(new FileReader(new File(spritesJson)));
        JsonObject obj = gson.fromJson(r, JsonObject.class);
        JsonArray sheets = obj.getAsJsonArray("spriteSheets");
        JsonArray sprites = obj.getAsJsonArray("sprites");
        int j = 0;
        for (int i = 0; i < sprites.size(); i++) {
            if(sprites.get(i).getAsJsonObject().has("class")) {
               j++;
            }
        }
        blocks = new Block[j];
        computeSpriteSheets(sheets);
        computeSprites(sprites);
    }

    private void computeSpriteSheets(JsonArray sheets){
        for (int i = 0; i < sheets.size(); i++) {
            JsonObject sheet = sheets.get(i).getAsJsonObject();
            String name = sheet.get("name").getAsString();
            String path = sheet.get("path").getAsString();
            int size = sheet.get("size").getAsInt();
            SpriteSheet spriteSheet = new SpriteSheet(path,size);
            spriteSheets.put(name,spriteSheet);
        }
    }


    private void computeSprites(JsonArray sprites){
        for (int i = 0; i < sprites.size(); i++) {
            JsonObject sprite = sprites.get(i).getAsJsonObject();
            String name = sprite.get("name").getAsString();
            int x = sprite.get("x").getAsInt();
            int y = sprite.get("y").getAsInt();
            int sizeX = sprite.get("sizeX").getAsInt();
            int sizeY = sprite.get("sizeY").getAsInt();
            boolean glow = sprite.get("glow").getAsBoolean();
            SpriteSheet sheet = getSheetByName(sprite.get("sheet").getAsString());
            Sprite sprite1 = new Sprite(sizeX,sizeY,x,y,sheet,!glow);
            this.sprites.put(name,sprite1);
            if(sprite.has("class")) {
                String color = sprite.get("color").getAsString();
                try {
                    Class<? extends Block> blockClass = (Class<? extends Block>) Class.forName(sprite.get("class").getAsString());
                    //metadata can be added
                        Block block = blockClass.getConstructor(Level.class,JsonObject.class).newInstance(level, new JsonObject());
                        block.setId(color);
                        block.setSprite(sprite1);
                        blocks[i] = block;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void computeEntites(Level level, JsonArray sprites){
        for (int i = 0; i < sprites.size(); i++) {
            JsonObject sprite = sprites.get(i).getAsJsonObject();
            Vector2i pos = new Vector2i(sprite.get("spawnX").getAsInt(),sprite.get("spawnY").getAsInt());
            pos.multiply(level.getScreen().getTileSize());
            Class<? extends Block> entityClass;
            try {
                 entityClass = (Class<? extends Block>) Class.forName(sprite.get("class").getAsString());
                JsonObject metadata = sprite.get("metadata").getAsJsonObject();
                Entity e = entityClass.getConstructor(Level.class,JsonObject.class).newInstance(level,metadata);
                e.setPosition(pos);
                level.add(e);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public SpriteSheet getSheetByName(String name){
        for(Map.Entry<String,SpriteSheet> entry : spriteSheets.entrySet()){
            if(entry.getKey().equals(name)) return entry.getValue();
        }
        return null;
    }

    public Sprite getSpriteByName(String name){
        for(Map.Entry<String,Sprite> entry : sprites.entrySet()){
            if(entry.getKey().equals(name)) return entry.getValue();
        }
        return null;
    }

    /*public Block getBlockByName(String name){
        for (int i = 0; i < blocks.length; i++) {
            if(entry.getKey().equals(name)) return entry.getValue();
        }
        return null;
    }*/

    public Block getBlockByID(String id){
        for (int i = 0; i < blocks.length; i++) {
            if(id.equals("ff" + blocks[i].getId())) return blocks[i];
        }
        return null;
    }

    public HashMap<String, SpriteSheet> getSpriteSheets() {
        return spriteSheets;
    }

    public void setSpriteSheets(HashMap<String, SpriteSheet> spriteSheets) {
        this.spriteSheets = spriteSheets;
    }

    public HashMap<String, Sprite> getSprites() {
        return sprites;
    }

    public void setSprites(HashMap<String, Sprite> sprites) {
        this.sprites = sprites;
    }

}

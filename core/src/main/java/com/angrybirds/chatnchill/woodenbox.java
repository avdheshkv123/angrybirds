package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class woodenbox extends block{
    public woodenbox(float x, float y, float width, float height,int towerid){
        super(new Texture("woodenbox.jpg"),x,y,width,height,towerid);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);

    }
}

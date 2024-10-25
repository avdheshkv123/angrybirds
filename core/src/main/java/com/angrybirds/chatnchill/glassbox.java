package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class glassbox extends block{
    public glassbox(float x, float y, float width, float height){
        super(new Texture("glassbox.png"),x,y,width,height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);

    }
}

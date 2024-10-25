package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class steelbox extends block{
    public steelbox(float x, float y, float width, float height){
        super(new Texture("steelbox.png"),x,y,width,height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);

    }
}

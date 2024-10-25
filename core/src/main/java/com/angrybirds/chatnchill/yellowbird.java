package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class yellowbird extends bird{
    public yellowbird(float x, float y, float width, float height){
        super(new Texture("yellowbird.png"),x,y,width,height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);
    }
}

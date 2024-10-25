package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class bluebird extends bird{
    public bluebird(float x, float y, float width, float height){
        super(new Texture("bluebird.png"),x,y,width,height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);
    }
}

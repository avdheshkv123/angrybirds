package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class redbird extends bird{
    public redbird(float x, float y, float width, float height){
        super(new Texture("redbird.png"),x,y,width,height);
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);
    }
}

package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class block{
    protected Texture texture;
    protected float x,y,width,height;
    protected int towerID;
    protected boolean isDestoryed = false;

    public block(Texture texture,float x,float y, float width, float height,int towerID){
        this.texture = texture;
        this.towerID = towerID;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,x,y,width,height);
    };

    public void dispose(){
        texture.dispose();
    }
}

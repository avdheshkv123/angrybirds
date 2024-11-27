package com.angrybirds.chatnchill;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class bird {
    protected Texture texture;
    protected float x, y, width, height;
    public int bouncecount = 3;

    public bird(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch){

        batch.draw(texture,x,y,width,height);
    }

    public void dispose() {
        texture.dispose();
    }
}

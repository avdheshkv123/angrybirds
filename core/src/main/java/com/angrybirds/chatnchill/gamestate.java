package com.angrybirds.chatnchill;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class gamestate{
    public ArrayList<bird> birds;
    public ArrayList<pig> pigs;
    public ArrayList<block> blocks;
    public boolean isbirdreleased;
    public Vector2 birdposition;
    public Vector2 birdvelocity;
    public String currentlevel;

    gamestate(ArrayList<bird> birds, ArrayList<pig> pigs, ArrayList<block> blocks, boolean isbirdreleased,
              Vector2 birdposition, Vector2 birdvelocity, String currentlevel){
        this.birds = birds;
        this.pigs = pigs;
        this.blocks = blocks;
        this.isbirdreleased = isbirdreleased;
        this.birdposition = birdposition;
        this.birdvelocity = birdvelocity;
        this.currentlevel = currentlevel;
    }
}

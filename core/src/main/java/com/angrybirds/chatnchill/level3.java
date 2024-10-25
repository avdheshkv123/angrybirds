package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class level3 implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture cloudybg;
    private Texture slingshot;
    private bird redbird;
    private bird bluebird;
    private bird yellowbird;
    private pig pig1;
    private pig pig2;
    private pig pig3;
    private pig pig4;
    private block glassbox;
    private block woodenbox;
    private block steelbox;
    private Texture pausebutton;
    private Texture losebutton;
    private Texture winbutton;
    private Sprite sprite;
    private FitViewport viewport;

    public level3(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        cloudybg = new Texture("cloudy.png");
        slingshot = new Texture("slingshot.png");
        redbird = new redbird(0.85f,1.65f,0.3f,0.3f);
        bluebird = new bluebird(0.13f,1.05f,0.4f,0.4f);
        yellowbird = new yellowbird(0.5f,1.05f,0.4f,0.4f);
        pig1 = new pig1(5.46f,2.33f,0.42f,0.3f);
        pig2 = new pig2(5.97f,2.2f,0.36f,0.3f);
        pig3 = new pig3(8.25f,2.07f,0.36f,0.3f);
        pig4 = new pig2(6.8f,1.7f,0.36f,0.3f);
        woodenbox = new woodenbox(5.5f,1.15f,0.3f,0.3f);
        glassbox = new glassbox(5.9f,1.33f,0.5f,0.5f);
        steelbox = new steelbox(7.4f,1.023f,0.5f,0.5f);
        pausebutton = new Texture("pause.png");
        winbutton = new Texture("w.png");
        losebutton = new Texture("L.png");
        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(cloudybg);
    }

    @Override
    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the pause icon
            float pausex = 0.15f;
            float pausey = 4.66f;
            float pausewidht = 0.4f;
            float pauseheight = 0.4f;

            if (touchPos.x >= pausex && touchPos.x <= pausex + pausewidht &&
                touchPos.y >= pausey && touchPos.y <= pausey + pauseheight) {
                game.setScreen(new pausemenu(game));
            }

            //check if the user clicked W button
            float winx = 8.5f;
            float winy = 4.5f;
            float winwidth = 0.5f;
            float winheight = 0.5f;

            if(touchPos.x>= winx && touchPos.x<=winx+winwidth && touchPos.y>=winy && touchPos.y<=winy+winheight){
                game.setScreen(new winscreen(game));
            }

            //check if the user clicked L button
            float losex = 9.1f;
            float losey = 4.5f;
            float losewidth = 0.5f;
            float loseheight = 0.5f;

            if(touchPos.x>=losex && touchPos.x<=losex+losewidth && touchPos.y>=losey && touchPos.y<=losey+loseheight){
                game.setScreen(new losescreen(game));
            }

        }
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        batch.draw(cloudybg,0,0,worldWidth,worldHeight);

        //slingshot dimension and position
        float slingshotx = 0.5f;
        float slingshoty = 1.05f;
        float slingwidht = 1;
        float slingheight = 0.8f;
        batch.draw(slingshot,slingshotx,slingshoty,slingwidht,slingheight);

        redbird.draw(batch);
        bluebird.draw(batch);
        yellowbird.draw(batch);
        woodenbox.draw(batch);
        glassbox.draw(batch);
        steelbox.draw(batch);
        pig1.draw(batch);
        pig2.draw(batch);
        pig3.draw(batch);
        pig4.draw(batch);


        //woodenbox dimensions and position
        float woodenboxx = 5.5f;
        float woodenboxy = 1.15f;
        float boxwidht = 0.3f;
        float boxheigt = 0.3f;

        //glassbox dimensions and position
        float glassboxx = 5.9f;
        float glassboxy = 1.33f;
        float glassboxwidht = 0.5f;
        float glassboxheight = 0.5f;

        //steelbox dimensions and position
        float steelboxx = 7.4f;
        float steelboxy = 1.023f;
        float steelboxwidht = 0.5f;
        float steelboxheight = 0.5f;

        //vertical glassbox
        int no_of_glassboxes = 3;
        for(int i = 0;i<no_of_glassboxes;i++){
            batch.draw(new Texture("glassbox.png"),5.9f,1.33f+i*(0.5f*0.53f),0.5f,0.5f);
        }

        //vertical woodenbox
        int no_of_woodenboxes = 4;
        for(int i = 0;i<no_of_woodenboxes;i++){
            batch.draw(new Texture("woodenbox.jpg"),woodenboxx,woodenboxy+i*boxheigt,boxwidht,boxheigt);
        }

        //horizontal woodenbox
        int no_of_wbox = 7;
        for(int i = 0;i<no_of_wbox;i++){
            batch.draw(new Texture("woodenbox.jpg"),woodenboxx+i*boxwidht,woodenboxy,boxwidht,boxheigt);
        }

        //horizontal glassbox
        int no_of_gbox = 4;
        for(int i = 0;i<no_of_gbox;i++){
            batch.draw(new Texture("glassbox.png"),glassboxx+(glassboxwidht*0.58f)+i*glassboxwidht*0.58f,glassboxy+(boxheigt*0.09f),glassboxwidht,glassboxheight);
        }

        int no_of_box = 4;
        int rows = 3;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<no_of_box;j++){
                batch.draw(new Texture("steelbox.png"),steelboxx+(steelboxwidht*0.58f) + (j*steelboxwidht*0.58f),steelboxy+(steelboxheight*0.09f)+(i*steelboxheight*0.58f),steelboxwidht,steelboxheight);
            }
        }

        float pausex = 0.15f;
        float pausey = 4.66f;
        float pausewidht = 0.4f;
        float pauseheight = 0.4f;
        batch.draw(pausebutton,pausex,pausey,pausewidht,pauseheight);

        //winning button
        float winx = 8.5f;
        float winy = 4.5f;
        float winwidth = 0.5f;
        float winheight = 0.5f;
        batch.draw(winbutton,winx,winy,winwidth,winheight);

        //lose button
        float losex = 9.1f;
        float losey = 4.5f;
        float losewidth = 0.5f;
        float loseheight = 0.5f;
        batch.draw(losebutton,losex,losey,losewidth,loseheight);
        batch.end();
    }

    public void resize(int width,int height){
        viewport.update(width,height,true);
    }

    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void hide(){
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        cloudybg.dispose();
    }

}

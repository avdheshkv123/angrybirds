package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level2 implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture sunnybg;
    private Texture slingshot;
    private Texture losebutton;
    private Texture winbutton;
    private bird redbird;
    private bird bluebird;
    private bird yellowbird;
    private pig pig1;
    private pig pig2;
    private pig pig3;
    private block glassbox;
    private block woodenbox;
    private Texture pausebutton;
    private Sprite sprite;
    private FitViewport viewport;

    public level2(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        sunnybg = new Texture("sunnybackground.jpg");
        slingshot = new Texture("slingshot.png");
        redbird = new redbird(0.85f,1.2f,0.4f,0.4f);
        bluebird = new bluebird(0.13f,0.63f,0.4f,0.4f);
        yellowbird = new yellowbird(0.5f,0.63f,0.4f,0.4f);
        pig1 = new pig1(5.46f,0.95f,0.42f,0.3f);
        pig2 = new pig2(5.97f,1.72f,0.36f,0.3f);
        pig3 = new pig3(6.85f,2.42f,0.45f,0.4f);
        woodenbox = new woodenbox(5.5f,0.67f,0.3f,0.3f);
        glassbox = new glassbox(5.9f,0.85f,0.5f,0.5f);
        pausebutton = new Texture("pause.png");
        losebutton = new Texture("L.png");
        winbutton = new Texture("w.png");
        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(sunnybg);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the pause icon
            float pausex = 0.17f;
            float pausey = 4.6f;
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
        batch.draw(sunnybg,0,0,worldWidth,worldHeight);

        //slingshot dimension and position
        float slingshotx = 0.5f;
        float slingshoty = 0.65f;
        float slingwidht = 1;
        float slingheight = 0.8f;
        batch.draw(slingshot,slingshotx,slingshoty,slingwidht,slingheight);

        redbird.draw(batch);
        yellowbird.draw(batch);
        bluebird.draw(batch);
        pig1.draw(batch);
        pig2.draw(batch);
        pig3.draw(batch);
        woodenbox.draw(batch);
        glassbox.draw(batch);

        //vertical glassbox
        int no_of_glassboxes = 3;
        for(int i = 0;i<no_of_glassboxes;i++) {
            batch.draw(new Texture("glassbox.png"), 5.9f, 0.85f + i * (0.5f * 0.53f), 0.5f, 0.5f);
        }

        //horizontal woodenbox
        int no_of_wbox = 7;
        for(int i = 0;i<no_of_wbox;i++){
            batch.draw(new Texture("woodenbox.jpg"),5.5f+i*0.3f,0.67f,0.3f,0.3f);
        }

        //horizontal glassbox
        int no_of_gbox = 4;
        for(int i = 0;i<no_of_gbox;i++){
            batch.draw(new Texture("glassbox.png"),5.9f+(0.5f*0.58f)+i*0.5f*0.58f,0.85f+(0.3f*0.09f),0.5f,0.5f);
        }

        //2nd vertical woodenbox
        int no_of_wboxes = 5;
        for(int i = 0;i<no_of_wboxes;i++){
            batch.draw(new Texture("woodenbox.jpg"),5.5f+1.4f,(0.67f*1.42f)+i*0.3f,0.3f,0.3f);
        }

        float pausex = 0.17f;
        float pausey = 4.6f;
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

    public void hide(){
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        sunnybg.dispose();
    }
}

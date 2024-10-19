package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level1 extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture nightbg;
    private Texture slingshot;
    private Texture redbird;
    private Texture bluebird;
    private Texture yellowbird;
    private Texture pig1;
    private Texture pig2;
    private Texture glassbox;
    private Texture woodenbox;
    private Texture pausebutton;
    private FitViewport viewport;
    private Music bgmusic;
    private Sprite sprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        nightbg = new Texture("night.png");
        slingshot = new Texture("slingshot.png");
        redbird = new Texture("redbird.png");
        bluebird = new Texture("bluebird.png");
        yellowbird = new Texture("yellowbird.png");
        pig1 = new Texture("pig1.png");
        pig2 = new Texture("pig2.png");
        woodenbox = new Texture("woodenbox.jpg");
        glassbox = new Texture("glassbox.png");
        pausebutton = new Texture("pause.png");
        bgmusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        bgmusic.setLooping(true);
        bgmusic.play();
        viewport = new FitViewport(10,5);
        sprite = new Sprite(nightbg);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {
        super.pause();
        bgmusic.pause();
    }

    @Override
    public void resume() {
        super.resume();
        bgmusic.play();
    }

    @Override
    public void render() {
        draw();
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldWidth();
        batch.draw(nightbg,0,0,worldWidth,worldHeight);

        //slingshot dimension and position
        float slingshotx = 0.5f;
        float slingshoty = 0.42f;
        float slingwidht = 1;
        float slingheight = 0.8f;
        batch.draw(slingshot,slingshotx,slingshoty,slingwidht,slingheight);

        //redbird dimension and position
        float rbirdx = slingshotx+0.35f;
        float rbirdy = slingshoty+slingheight-0.2f;
        float rbirdwidth = 0.3f;
        float rbirdheight = 0.3f;
        batch.draw(redbird,rbirdx,rbirdy,rbirdwidth,rbirdheight);

        //yellow bird dimension and position
        float ybirdx = 0.5f;
        float ybirdy = 0.42f;
        float ybirdwidth = 0.4f;
        float ybirdheight = 0.4f;
        batch.draw(yellowbird,ybirdx,ybirdy,ybirdwidth,ybirdheight);

        //blue bird dimension and position
        float bbirdx = 0.13f;
        float bbirdy = 0.42f;
        float bbirdwidth = 0.4f;
        float bbirdheight = 0.4f;
        batch.draw(bluebird,bbirdx,bbirdy,bbirdwidth,bbirdheight);

        //woodenbox dimensions and position
        float woodenboxx = 7.3f;
        float woodenboxy = 0.49f;
        float boxwidht = 0.3f;
        float boxheigt = 0.3f;

        //glassbox dimensions and position
        float glassboxx = 7.9f;
        float glassboxy = 0.41f;
        float glassboxwidht = 0.5f;
        float glassboxheight = 0.5f;

        //vertical glassbox
        int no_of_glassboxes = 3;
        for(int i = 0;i<no_of_glassboxes;i++){
            batch.draw(glassbox,glassboxx,glassboxy+i*(glassboxheight*0.53f),glassboxwidht,glassboxheight);
        }

        //vertical woodenbox
        int no_of_woodenboxes = 4;
        for(int i = 0;i<no_of_woodenboxes;i++){
            batch.draw(woodenbox,woodenboxx,woodenboxy+i*boxheigt,boxwidht,boxheigt);
        }


        //pig1 dimension and position
        float pig1x = 7.25f;
        float pig1y = 1.68f;
        float pig1width = 0.42f;
        float pig1height = 0.3f;

        //pig2 dimesnsion and position
        float pig2x = 7.97f;
        float pig2y = 1.27f;
        float pig2width = 0.36f;
        float pig2height = 0.3f;

        batch.draw(pig1,pig1x,pig1y,pig1width,pig1height);
        batch.draw(pig2,pig2x,pig2y,pig2width,pig2height);

        float pausex = 0.13f;
        float pausey = 4.5f;
        float pausewidht = 0.4f;
        float pauseheight = 0.4f;
        batch.draw(pausebutton,pausex,pausey,pausewidht,pauseheight);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        nightbg.dispose();
        bgmusic.dispose();
    }
}

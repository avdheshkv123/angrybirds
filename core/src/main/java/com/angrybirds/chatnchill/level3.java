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
    private Texture redbird;
    private Texture bluebird;
    private Texture yellowbird;
    private Texture pig1;
    private Texture pig2;
    private Texture pig3;
    private Texture glassbox;
    private Texture woodenbox;
    private Texture steelbox;
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
        redbird = new Texture("redbird.png");
        bluebird = new Texture("bluebird.png");
        yellowbird = new Texture("yellowbird.png");
        pig1 = new Texture("pig1.png");
        pig2 = new Texture("pig2.png");
        pig3 = new Texture("pig3.png");
        woodenbox = new Texture("woodenbox.jpg");
        glassbox = new Texture("glassbox.png");
        pausebutton = new Texture("pause.png");
        steelbox = new Texture("steelbox.png");
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

        //redbird dimension and position
        float rbirdx = slingshotx+0.35f;
        float rbirdy = slingshoty+slingheight-0.2f;
        float rbirdwidth = 0.3f;
        float rbirdheight = 0.3f;
        batch.draw(redbird,rbirdx,rbirdy,rbirdwidth,rbirdheight);

        //yellow bird dimension and position
        float ybirdx = 0.5f;
        float ybirdy = 1.05f;
        float ybirdwidth = 0.4f;
        float ybirdheight = 0.4f;
        batch.draw(yellowbird,ybirdx,ybirdy,ybirdwidth,ybirdheight);

        //blue bird dimension and position
        float bbirdx = 0.13f;
        float bbirdy = 1.05f;
        float bbirdwidth = 0.4f;
        float bbirdheight = 0.4f;
        batch.draw(bluebird,bbirdx,bbirdy,bbirdwidth,bbirdheight);

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
            batch.draw(glassbox,glassboxx,glassboxy+i*(glassboxheight*0.53f),glassboxwidht,glassboxheight);
        }

        //vertical woodenbox
        int no_of_woodenboxes = 4;
        for(int i = 0;i<no_of_woodenboxes;i++){
            batch.draw(woodenbox,woodenboxx,woodenboxy+i*boxheigt,boxwidht,boxheigt);
        }

        //horizontal woodenbox
        int no_of_wbox = 7;
        for(int i = 0;i<no_of_wbox;i++){
            batch.draw(woodenbox,woodenboxx+i*boxwidht,woodenboxy,boxwidht,boxheigt);
        }

        //horizontal glassbox
        int no_of_gbox = 4;
        for(int i = 0;i<no_of_gbox;i++){
            batch.draw(glassbox,glassboxx+(glassboxwidht*0.58f)+i*glassboxwidht*0.58f,glassboxy+(boxheigt*0.09f),glassboxwidht,glassboxheight);
        }

        int no_of_box = 4;
        int rows = 3;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<no_of_box;j++){
                batch.draw(steelbox,steelboxx+(steelboxwidht*0.58f) + (j*steelboxwidht*0.58f),steelboxy+(steelboxheight*0.09f)+(i*steelboxheight*0.58f),steelboxwidht,steelboxheight);
            }
        }
        //pig1 dimension and position
        float pig1x = 5.46f;
        float pig1y = 2.33f;
        float pig1width = 0.42f;
        float pig1height = 0.3f;

        //pig2 dimesnsion and position
        float pig2x = 5.97f;
        float pig2y = 2.2f;
        float pig2width = 0.36f;
        float pig2height = 0.3f;

        //pig3 dimension and position
        float pig3x = 8.25f;
        float pig3y = 2.07f;
        float pig3width = 0.34f;
        float pig3height = 0.34f;

        //pig4 dimesnsion and position
        float pig4x = 6.8f;
        float pig4y = 1.7f;
        float pig4width = 0.36f;
        float pig4height = 0.3f;

        batch.draw(pig1,pig1x,pig1y,pig1width,pig1height);
        batch.draw(pig2,pig2x,pig2y,pig2width,pig2height);
        batch.draw(pig3,pig3x,pig3y,pig3width,pig3height);
        batch.draw(pig2,pig4x,pig4y,pig4width,pig4height);

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

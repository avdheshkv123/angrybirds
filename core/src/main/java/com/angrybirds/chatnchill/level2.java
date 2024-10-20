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
    private Texture redbird;
    private Texture bluebird;
    private Texture yellowbird;
    private Texture pig1;
    private Texture pig2;
    private Texture pig3;
    private Texture glassbox;
    private Texture woodenbox;
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
        redbird = new Texture("redbird.png");
        bluebird = new Texture("bluebird.png");
        yellowbird = new Texture("yellowbird.png");
        pig1 = new Texture("pig1.png");
        pig2 = new Texture("pig2.png");
        pig3 = new Texture("pig3.png");
        woodenbox = new Texture("woodenbox.jpg");
        glassbox = new Texture("glassbox.png");
        pausebutton = new Texture("pause.png");
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

        //redbird dimension and position
        float rbirdx = slingshotx+0.35f;
        float rbirdy = slingshoty+slingheight-0.2f;
        float rbirdwidth = 0.3f;
        float rbirdheight = 0.3f;
        batch.draw(redbird,rbirdx,rbirdy,rbirdwidth,rbirdheight);

        //yellow bird dimension and position
        float ybirdx = 0.5f;
        float ybirdy = 0.63f;
        float ybirdwidth = 0.4f;
        float ybirdheight = 0.4f;
        batch.draw(yellowbird,ybirdx,ybirdy,ybirdwidth,ybirdheight);

        //blue bird dimension and position
        float bbirdx = 0.13f;
        float bbirdy = 0.63f;
        float bbirdwidth = 0.4f;
        float bbirdheight = 0.4f;
        batch.draw(bluebird,bbirdx,bbirdy,bbirdwidth,bbirdheight);

        //woodenbox dimensions and position
        float woodenboxx = 5.5f;
        float woodenboxy = 0.67f;
        float boxwidht = 0.3f;
        float boxheigt = 0.3f;

        //glassbox dimensions and position
        float glassboxx = 5.9f;
        float glassboxy = 0.85f;
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

        //2nd vertical woodenbox
        int no_of_wboxes = 5;
        for(int i = 0;i<no_of_wboxes;i++){
            batch.draw(woodenbox,woodenboxx+1.4f,(woodenboxy*1.42f)+i*boxheigt,boxwidht,boxheigt);
        }

        //pig1 dimension and position
        float pig1x = 5.46f;
        float pig1y = 1.85f;
        float pig1width = 0.42f;
        float pig1height = 0.3f;

        //pig2 dimesnsion and position
        float pig2x = 5.97f;
        float pig2y = 1.72f;
        float pig2width = 0.36f;
        float pig2height = 0.3f;

        //pig3 dimension and position
        float pig3x = 6.9f;
        float pig3y = 2.45f;
        float pig3width = 0.34f;
        float pig3height = 0.34f;

        batch.draw(pig1,pig1x,pig1y,pig1width,pig1height);
        batch.draw(pig2,pig2x,pig2y,pig2width,pig2height);
        batch.draw(pig3,pig3x,pig3y,pig3width,pig3height);

        float pausex = 0.17f;
        float pausey = 4.6f;
        float pausewidht = 0.4f;
        float pauseheight = 0.4f;
        batch.draw(pausebutton,pausex,pausey,pausewidht,pauseheight);
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

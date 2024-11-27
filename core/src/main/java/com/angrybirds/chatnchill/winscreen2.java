package com.angrybirds.chatnchill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class winscreen2 implements Screen{
    private Main game;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture winscreen;
    private Texture blurbg;
    private FitViewport viewport;
    private Texture close;

    public winscreen2(Main game){
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        winscreen = new Texture("winscreen2.png");
        close = new Texture("close.png");
        blurbg = new Texture("blurbackground.jpg");
        viewport = new FitViewport(10.1f, 5.2f);
        sprite = new Sprite(blurbg);
    }

    @Override

    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            //if user clicked close
            float closex = 7.8f;
            float closey = 4.26f;
            float closewidth = 0.7f;
            float closeheight = 0.7f;

            if (touchPos.x >= closex && touchPos.x <= closex + closewidth &&
                touchPos.y >= closey && touchPos.y <= closey + closeheight) {
                game.setScreen(new levelselect(game));
            }

        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldWidth();
        batch.draw(blurbg, 0, 0, worldWidth, worldHeight);


        //lose screen
        float winx = 2;
        float winy = 0.45f;
        float winwidth = 6.2f;
        float winheight = 4.2f;
        batch.draw(winscreen,winx,winy,winwidth,winheight);


        //close button;
        float closex = 7.8f;
        float closey = 4.26f;
        float closewidth = 0.7f;
        float closeheight = 0.7f;
        batch.draw(close,closex,closey,closewidth,closeheight);

        batch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }


    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        blurbg.dispose();
    }
}

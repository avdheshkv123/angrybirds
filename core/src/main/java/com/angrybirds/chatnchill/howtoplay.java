package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Vector2;

public class howtoplay implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture blurbg;
    private Texture howtoplay;
    private Texture back;
    private FitViewport viewport;
    private Sprite sprite;

    public howtoplay(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        howtoplay = new Texture("howtoplay.png");
        blurbg = new Texture("blurbackground.jpg");
        back = new Texture("back.png");
        viewport = new FitViewport(10.1f, 5.2f);
        sprite = new Sprite(howtoplay);
    }

    @Override

    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the back button
            float backx = 0.2f;
            float backy = 4.3f;
            float backwidth = 0.5f;
            float backheight = 0.5f;

            if (touchPos.x >= backx && touchPos.x <= backx + backwidth &&
                touchPos.y >= backy && touchPos.y <= backy + backheight) {
                game.setScreen(new Mainscreen(game));
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


        //how to play
        float htpx = 2.8f;
        float htpy = 0.3f;
        float htpwidth = 4.5f;
        float htpheight = 4.5f;
        batch.draw(howtoplay,htpx,htpy,htpwidth,htpheight);

        //back button
        float backx = 0.2f;
        float backy = 4.5f;
        float backwidht = 0.5f;
        float backheight = 0.5f;
        batch.draw(back,backx,backy,backwidht,backheight);

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
        howtoplay.dispose();
    }
}


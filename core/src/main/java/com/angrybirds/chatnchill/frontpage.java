package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class frontpage implements Screen {
    private Main game;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture frontpage;
    private FitViewport viewport;
    private Texture clickhere;

    public frontpage(Main game){
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        frontpage = new Texture("frontpage.jpg");
        clickhere = new Texture("clickhere.png");
        viewport = new FitViewport(10, 5.2f);
        sprite = new Sprite(frontpage);
    }

    @Override

    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);


            float clickx = 3.65f;
            float clicky = 0;
            float clickwidth = 2.8f;
            float clickheight = 0.5f;

            if (touchPos.x >= clickx && touchPos.x <= clickx + clickwidth &&
                touchPos.y >= clicky && touchPos.y <= clicky + clickheight) {
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
        batch.draw(frontpage, 0, 0, worldWidth, worldHeight);

        //click here text;
        float clickx = 3.65f;
        float clicky = 0;
        float clickwidth = 2.8f;
        float clickheight = 0.5f;
        batch.draw(clickhere,clickx,clicky,clickwidth,clickheight);

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
        frontpage.dispose();
    }
}

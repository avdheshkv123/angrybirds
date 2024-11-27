package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import org.w3c.dom.Text;

public class frontpage implements Screen {
    private Main game;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture frontpage;
    private FitViewport viewport;
    private Texture clickhere;
    private Sound click;
    private boolean isloading = false;
    private float loadingprog = 0;

    public frontpage(Main game){
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        frontpage = new Texture("frontpage.png");
        frontpage.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        clickhere = new Texture("continue.png");
        viewport = new FitViewport(10, 5.2f);
        sprite = new Sprite(frontpage);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
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
                click.play();
                isloading = true;
            }
        }

        if(isloading){
            loadingprog +=delta*35;
            if(loadingprog>=100){
                game.setScreen(new Mainscreen(game));
            }
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(frontpage, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        //click here text;
        if(!isloading) {
            float clickx = 4.2f;
            float clicky = 0;
            float clickwidth = 1.5f;
            float clickheight = 0.8f;
            batch.draw(clickhere, clickx, clicky, clickwidth, clickheight);
        }

        if(isloading) {
            float barx = 3.65f;
            float bary = 0;
            float barwidth = 2.8f*(loadingprog/100);
            float barheight = 0.5f;

            Texture loadingbar = new Texture("loadingbar.png");
            batch.draw(loadingbar,barx,bary,barwidth,barheight);

        }

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

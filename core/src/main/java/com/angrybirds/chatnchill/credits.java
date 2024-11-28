package com.angrybirds.chatnchill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class credits implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture background;
    private Texture cross;
    private Texture credits;
    private Sprite sprite;
    private Sound click;
    private FitViewport viewport;

    public credits(Main game){
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        background = new Texture("blursetting.png");
        cross = new Texture("close.png");
        credits = new Texture("credits.png");
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(background);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }


    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);


            float backx = 6.5f;
            float backy = 4.2f;
            float backwidth = 0.5f;
            float backheight = 0.5f;
            if(touchPos.x>=backx && touchPos.x<=backx+backwidth && touchPos.y>=backy &&
                touchPos.y<=backy+backheight){
                click.play();
                game.setScreen(new Settings(game));
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
        batch.draw(background,0,0,worldWidth,worldHeight);

        //credits page
        float creditsx = 3.46f;
        float creditsy = 0.4f;
        float creditswidth = 3.3f;
        float creditsheight = 4.1f;
        batch.draw(credits,creditsx,creditsy,creditswidth,creditsheight);

        //back button
        float backx = 6.5f;
        float backy = 4.2f;
        float backwidth = 0.5f;
        float backheight = 0.5f;
        batch.draw(cross,backx,backy,backwidth,backheight);


        batch.end();
    }

    public void resize(int width,int height){
        viewport.update(width,height,true);
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
        dispose();
    }

    public void dispose() {
        background.dispose();
        batch.dispose();
    }
}

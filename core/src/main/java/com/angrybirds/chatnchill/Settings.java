package com.angrybirds.chatnchill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Settings implements Screen{
    private Main game;
    private SpriteBatch batch;
    private Texture background;
    private Texture settingsmenu;
    private Texture back;
    private Sprite sprite;
    private FitViewport viewport;
    private Sound click;
    private Music music;
    private Texture credit;
    private Texture on;
    private Texture off;

    public Settings(Main game){
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        background = new Texture("blurbackground.jpg");
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        back = new Texture("back.png");
        settingsmenu = new Texture("settingsmenu.png");
        credit = new Texture("creditText.png");
        on = new Texture("on.png");
        off = new Texture("off.png");
        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(background);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));

    }


    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);


            float backx = 4.7f;
            float backy = 0.65f;
            float backwidth = 0.8f;
            float backheight = 0.8f;
            if(touchPos.x>=backx && touchPos.x<=backx+backwidth && touchPos.y>=backy &&
                touchPos.y<=backy+backheight){
                click.play();
                game.setScreen(new Mainscreen(game));
            }

            float creditx = 4.13f;
            float credity = 1.65f;
            float crediwidth = 2.1f;
            float creditheight = 1;
            if(touchPos.x>=creditx && touchPos.x<=creditx+crediwidth && touchPos.y>=credity &&
                touchPos.y<=credity+creditheight){
                click.play();
                game.setScreen(new credits(game));
            }

            float togglex = 5.05f;
            float toggley = 3.02f;
            float togglewidth = 1.3f;
            float toggleheight = 0.5f;
            if(touchPos.x>=togglex && touchPos.x<=togglex+togglewidth && touchPos.y>=toggley &&
                touchPos.y<=toggley+toggleheight){
                click.play();
                game.toggleMusic();
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

        //setting menu
        float menux = 3.13f;
        float menuy = 0;
        float menuwidth = 4f;
        float menuheight = 5.1f;
        batch.draw(settingsmenu,menux,menuy,menuwidth,menuheight);

        //credit Text
        float creditx = 4.13f;
        float credity = 1.65f;
        float crediwidth = 2.1f;
        float creditheight = 1;
        batch.draw(credit,creditx,credity,crediwidth,creditheight);

        //back button
        float backx = 4.7f;
        float backy = 0.65f;
        float backwidth = 0.8f;
        float backheight = 0.8f;
        batch.draw(back,backx,backy,backwidth,backheight);

        //music on button
        float onx = 5.05f;
        float ony = 3.02f;
        float onwidth = 1.3f;
        float onheight = 0.5f;

        float offx = 5.05f;
        float offy = 3.02f;
        float offwidth = 1.3f;
        float offheight = 0.5f;

        if(game.isMusicon()) {
            batch.draw(on, onx, ony, onwidth, onheight);
        }
        else {
            batch.draw(off,offx,offy,offwidth,offheight);
        }


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

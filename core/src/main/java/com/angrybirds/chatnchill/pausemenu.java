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
import com.badlogic.gdx.utils.viewport.Viewport;

public class pausemenu implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture background;
    private Texture pausemenu;
    private Texture home;
    private Texture start;
    private Texture retry;
    private Sprite sprite;
    private FitViewport viewport;
    private Screen currentlevel;
    private Sound click;

    public pausemenu(Main game, Screen currentlevel){
        this.game = game;
        this.currentlevel = currentlevel;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("blurbackground.jpg");
        pausemenu = new Texture("pausemenu.png");
        retry = new Texture("retry.png");
        start = new Texture("start.png");
        home = new Texture("home.png");
        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(background);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }


    @Override
    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the resume button in level1
            float loadx = 3.16f;
            float loady = 1.6f;
            float loadwidth = 0.9f;
            float loadheight = 0.9f;

            if (touchPos.x >= loadx && touchPos.x <= loadx + loadwidth &&
                touchPos.y >= loady && touchPos.y <= loady + loadheight) {
                click.play();
                if(currentlevel instanceof level1) {
                    game.setScreen(new level1(game));
                }
                else if(currentlevel instanceof level2){
                    game.setScreen(new level2(game));
                }
                else if(currentlevel instanceof level3){
                    game.setScreen(new level3(game));
                }
            }

            //check if user clicked retry button in level1
            float retryx = 4.56f;
            float retryy = 1.6f;
            float retrywidth = 1f;
            float retryheight = 0.93f;

            if(touchPos.x>=retryx && touchPos.x<=retryx+retrywidth && touchPos.y>=retryy &&
            touchPos.y<=retryy+retryheight){
                click.play();
                if(currentlevel instanceof level1) {
                    game.setScreen(new level1(game));
                }
                else if(currentlevel instanceof level2){
                    game.setScreen(new level2(game));
                }
                else if(currentlevel instanceof level3){
                    game.setScreen(new level3(game));
                }
            }

            //check if user clicked home button in level1
            float homex = 6.02f;
            float homey = 1.6f;
            float homewidth = 0.9f;
            float homeheight = 0.93f;

            if(touchPos.x>=homex && touchPos.x<=homex+homewidth && touchPos.y>=homey &&
                touchPos.y<=homey+homeheight){
                click.play();
                game.setScreen(new Mainscreen(game));
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

        //pausemenu
        float pausex = 1.82f;
        float pausey = -0.5f;
        float pausewidth = 6.5f;
        float pauseheight = 6;
        batch.draw(pausemenu,pausex,pausey,pausewidth,pauseheight);

        //play button
        float playx = 3.16f;
        float playy = 1.6f;
        float playwidth = 0.9f;
        float playheight = 0.9f;
        batch.draw(start,playx,playy,playwidth,playheight);

        //retry button
        float retryx = 4.56f;
        float retryy = 1.6f;
        float retrywidth = 1f;
        float retryheight = 0.93f;
        batch.draw(retry,retryx,retryy,retrywidth,retryheight);

        //home button
        float homex = 6.02f;
        float homey = 1.6f;
        float homewidth = 0.9f;
        float homeheight = 0.93f;
        batch.draw(home,homex,homey,homewidth,homeheight);


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

    public void hide() {
        dispose();
    }
    @Override
    public void dispose() {
        background.dispose();
        batch.dispose();
    }


}

package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Vector2;


public class Mainscreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture homescreen;
    private Texture htpbutton;
    private Texture exitbutton;
    private Texture playbutton;
    private Texture text;
    private Texture settings;
    private FitViewport viewport;
    private Sprite sprite;

    public Mainscreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        homescreen = new Texture("home screen.png");
        htpbutton = new Texture("htp.png");
        exitbutton = new Texture("exit.png");
        playbutton = new Texture("play.png");
        text = new Texture("text.png");
        settings = new Texture("settings.png");
        viewport = new FitViewport(10.1f, 5.2f);
        sprite = new Sprite(homescreen);
    }

    @Override

    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked play button
            float playx = 4.2f;
            float playy = 2.6f;
            float playwidth = 1.6f;
            float playheight = 1.0f;

            if (touchPos.x >= playx && touchPos.x <= playx + playwidth &&
                touchPos.y >= playy && touchPos.y <= playy + playheight) {
                game.setScreen(new levelselect(game));
            }

            //check if the user clicked howtoplay button
            float htpx = 4.2f;
            float htpy = 1.6f;
            float htpwidth = 1.6f;
            float htpheight = 1.0f;

            if (touchPos.x >= htpx && touchPos.x <= htpx + htpwidth &&
                touchPos.y >= htpy && touchPos.y <= htpy + htpheight) {
                game.setScreen(new howtoplay(game));
            }

            //check if the user clicked exit button
            float exitx = 4.2f;
            float exity = 0.6f;
            float exitwidth = 1.6f;
            float exitheight = 1.0f;

            if(touchPos.x >=exitx && touchPos.x<=exitx+exitwidth && touchPos.y>=exity &&
                touchPos.y<=exity+exitheight){
                Gdx.app.exit();
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
        batch.draw(homescreen, 0, 0, worldWidth, worldHeight);

        //angry birds text
        float textx = 2.4f;
        float texty = 3.6f;
        float textwidth = 5.3f;
        float textheight = 1.3f;
        batch.draw(text, textx, texty, textwidth, textheight);

        //play button
        float playx = 4.2f;
        float playy = 2.6f;
        float playwidth = 1.6f;
        float playheight = 1.0f;
        batch.draw(playbutton, playx, playy, playwidth, playheight);

        //how to play button
        float htpx = 4.2f;
        float htpy = 1.6f;
        float htpwidth = 1.6f;
        float htpheight = 1.0f;
        batch.draw(htpbutton, htpx, htpy, htpwidth, htpheight);

        //exit button
        float exitx = 4.2f;
        float exity = 0.6f;
        float exitwidth = 1.6f;
        float exitheight = 1.0f;
        batch.draw(exitbutton, exitx, exity, exitwidth, exitheight);

//        settings button
//        float settingsx = 0.15f;
//        float settingsy = 4.44f;
//        float settingswidht = 0.5f;
//        float settingsheight = 0.5f;
//        batch.draw(settings, settingsx, settingsy, settingswidht, settingsheight);

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
        homescreen.dispose();
    }
}


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

import javax.swing.*;

public class Mainscreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture homescreen;
    private Texture levelbutton;
    private Texture exitbutton;
    private Texture playbutton;
    private Texture text;
    private Texture settings;
    private FitViewport viewport;
    private Music bgmusic;
    private Sprite sprite;

    public Mainscreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        homescreen = new Texture("home screen.png");
        levelbutton = new Texture("level.png");
        exitbutton = new Texture("exit.png");
        playbutton = new Texture("play.png");
        text = new Texture("text.png");
        settings = new Texture("settings.png");
        bgmusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        bgmusic.setLooping(true);
        bgmusic.play();
        viewport = new FitViewport(10, 5);
        sprite = new Sprite(homescreen);
    }

    @Override

    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the level button
            float levelx = 4.2f;
            float levely = 1.6f;
            float levelwidth = 1.6f;
            float levelheight = 1.0f;

            if (touchPos.x >= levelx && touchPos.x <= levelx + levelwidth &&
                touchPos.y >= levely && touchPos.y <= levely + levelheight) {
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

        //level button
        float levelx = 4.2f;
        float levely = 1.6f;
        float levelwidth = 1.6f;
        float levelheight = 1.0f;
        batch.draw(levelbutton, levelx, levely, levelwidth, levelheight);

        //exit button
        float exitx = 4.2f;
        float exity = 0.6f;
        float exitwidth = 1.6f;
        float exitheight = 1.0f;
        batch.draw(exitbutton, exitx, exity, exitwidth, exitheight);

        //settings button
        float settingsx = 0.42f;
        float settingsy = 4.44f;
        float settingswidht = 0.5f;
        float settingsheight = 0.5f;
        batch.draw(settings, settingsx, settingsy, settingswidht, settingsheight);

        batch.end();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        bgmusic.pause();
    }

    @Override
    public void resume() {
        bgmusic.play();
    }

    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        homescreen.dispose();
        bgmusic.dispose();
    }
}


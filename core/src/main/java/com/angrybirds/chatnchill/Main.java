package com.angrybirds.chatnchill;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    Music backgroundmusic;

    @Override
    public void create() {
        backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundmusic.setLooping(true);
        backgroundmusic.play();
        backgroundmusic.setVolume(0.3f);
        this.setScreen(new frontpage(this));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }


}

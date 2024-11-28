package com.angrybirds.chatnchill;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    Music backgroundmusic;
    private boolean isMusicon = true;
    private float position = 0f;

    @Override
    public void create() {
        backgroundmusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        backgroundmusic.setLooping(true);

        if(isMusicon) {
            backgroundmusic.play();
        }

        backgroundmusic.setVolume(0.4f);
        this.setScreen(new frontpage(this));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    public void playMusic(){
        if(!backgroundmusic.isPlaying()){
            backgroundmusic.setPosition(position);
            backgroundmusic.play();
        }
    }

    public void stopMusic(){
        if(backgroundmusic.isPlaying()){
            position = backgroundmusic.getPosition();
            backgroundmusic.pause();
        }
    }

    public void toggleMusic(){
        isMusicon = !isMusicon;
        if(isMusicon){
            playMusic();
        }
        else {
            stopMusic();
        }
    }

    public boolean isMusicon(){
        return isMusicon;
    }

    @Override
    public void pause() {
        super.pause();
        if(backgroundmusic.isPlaying()){
            position = backgroundmusic.getPosition();
            backgroundmusic.pause();
        }
    }

    @Override
    public void resume() {
        super.resume();
        if(isMusicon && !backgroundmusic.isPlaying()){
            backgroundmusic.setPosition(position);
            backgroundmusic.play();
        }
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        backgroundmusic.dispose();
        super.dispose();
    }


}

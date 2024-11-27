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

public class losescreen implements Screen{
    private Main game;
    private Sprite sprite;
    private SpriteBatch batch;
    private Texture losescreen;
    private Texture starsgained;
    private Texture blurbg;
    private FitViewport viewport;
    private Texture close;
    private Sound click;

    public losescreen(Main game){
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        losescreen = new Texture("losing screen.png");
        close = new Texture("close.png");
        starsgained = new Texture("stars gained.png");
        blurbg = new Texture("blurbackground.jpg");
        viewport = new FitViewport(10.1f, 5.2f);
        sprite = new Sprite(blurbg);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }

    @Override

    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            //if user clicked close
            float closex = 6.4f;
            float closey = 4.6f;
            float closewidth = 0.4f;
            float closeheight = 0.4f;

            if (touchPos.x >= closex && touchPos.x <= closex + closewidth &&
                touchPos.y >= closey && touchPos.y <= closey + closeheight) {
                click.play();
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
        batch.draw(blurbg, 0, 0, worldWidth, worldHeight);


        //lose screen
        float losex = 3.6f;
        float losey = 0.36f;
        float losewidth = 3;
        float loseheight = 4.5f;
        batch.draw(losescreen,losex,losey,losewidth,loseheight);

        //stars gained
        float starsx = 3.98f;
        float starsy = 0.8f;
        float starswidth = 2.2f;
        float starsheight = 1.2f;
        batch.draw(starsgained,starsx,starsy,starswidth,starsheight);

        //close button;
        float closex = 6.4f;
        float closey = 4.6f;
        float closewidth = 0.4f;
        float closeheight = 0.4f;
        batch.draw(close,closex,closey,closewidth,closeheight);

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
        blurbg.dispose();
    }
}
